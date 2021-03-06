# 第九周

**周三作业：**

**1.（选做）** 实现简单的 Protocol Buffer/Thrift/gRPC(选任一个) 远程调用 demo。

**2.（选做）** 实现简单的 WebService-Axis2/CXF 远程调用 demo。

**3.（必做）** 改造自定义 RPC 的程序，提交到 GitHub：

- 尝试将服务端写死查找接口实现类变成泛型和反射；

```java
public class DemoResolver implements RpcfxResolver, ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
    
    @Override
    @SneakyThrows
    public <T> T resolve(String serviceClass) {
        Class<T> clazz = (Class<T>) this.getClass().getClassLoader().loadClass(serviceClass);
        return this.applicationContext.getBean(clazz);
    }
}
```

- 尝试将客户端动态代理改成 AOP，添加异常处理；
  
```java
@Aspect
@Component
public class ReferencedAspect {
    @Before("execution(* io.kimmking.rpcfx.demo.consumer..*.*(..))")
    public void setReference(JoinPoint joinPoint) throws Exception {
        Object target = joinPoint.getTarget();
        Field[] fields = target.getClass().getDeclaredFields();
        for (Field field : fields) {
            Reference reference = field.getAnnotation(Reference.class);
            if (reference != null) {
                field.setAccessible(true);
                if (field.get(target) == null) {
                    field.set(target, Rpcfx.create(field.getType(), reference.url()));
                }
            }
        }
    }
}
```

- 尝试使用 Netty+HTTP 作为 client 端传输方式。

    - `io.kimmking.rpcfx.demo.provider.RpcfxServerApplication.main`
```java
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup(10);

        ConfigurableApplicationContext context = SpringApplication.run(RpcfxServerApplication.class, args);

        RpcfxInvoker rpcfxInvoker = context.getBean(RpcfxInvoker.class);

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new DemoServerInitializer(rpcfxInvoker));
            ChannelFuture channelFuture = serverBootstrap.bind(8081).sync();
            channelFuture.channel().closeFuture().sync();

        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
```

```java
public class DemoServerInitializer extends ChannelInitializer<SocketChannel> {
    private final RpcfxInvoker rpcfxInvoker;

    public DemoServerInitializer(RpcfxInvoker rpcfxInvoker) {
        this.rpcfxInvoker = rpcfxInvoker;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        // 向管道加入处理器
        ChannelPipeline pipeline = ch.pipeline();
        
        // 1. HttpServerCodec 是netty 提供的处理http的 编-解码器
        pipeline.addLast("httpServerCodec", new HttpServerCodec());
        // 2.聚合 http header and http content
        pipeline.addLast("httpObjectAggregator", new HttpObjectAggregator(10 * 1024 * 1024));
        // 3. 增加一个自定义的handler
        pipeline.addLast("httpServerHandler", new DemoHttpServerHandler(rpcfxInvoker));
    }
}
```

```java
@Slf4j
public class DemoHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {
    private final RpcfxInvoker rpcfxInvoker;

    public DemoHttpServerHandler(RpcfxInvoker rpcfxInvoker) {
        this.rpcfxInvoker = rpcfxInvoker;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        // 判断 msg 是不是 HttpRequest 请求
        if (msg instanceof FullHttpRequest) {
            FullHttpRequest httpRequest = (FullHttpRequest) msg;
            // 获取uri, 过滤指定的资源
            URI uri = new URI(httpRequest.uri());
            if ("/favicon.ico".equals(uri.getPath())) {
                log.info("请求了 favicon.ico, 不做响应");
                return;
            }

            // 构建 RpcfxRequest 请求对象
            String requestBody = httpRequest.content().toString(CharsetUtil.UTF_8);
            RpcfxRequest rpcfxRequest = JSON.parseObject(requestBody, RpcfxRequest.class);

            // 调用实际方法
            RpcfxResponse rpcfxResponse = rpcfxInvoker.invoke(rpcfxRequest);

            //构造 http 响应
            String responseBody = JSON.toJSONString(rpcfxResponse);
            ByteBuf content = Unpooled.copiedBuffer(responseBody, CharsetUtil.UTF_8);
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "application/json; charset=utf-8");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());
            ctx.writeAndFlush(response);
        }
    }
}
```

**4.（选做☆☆）** 升级自定义 RPC 的程序：

- 尝试使用压测并分析优化 RPC 性能；
- 尝试使用 Netty+TCP 作为两端传输方式；
- 尝试自定义二进制序列化；
- 尝试压测改进后的 RPC 并分析优化，有问题欢迎群里讨论；
- 尝试将 fastjson 改成 xstream；
- 尝试使用字节码生成方式代替服务端反射。

**周日作业：**

**1.（选做）** 按课程第二部分练习各个技术点的应用。

**2.（选做）** 按 dubbo-samples 项目的各个 demo 学习具体功能使用。

**3.（必做）** 结合 dubbo+hmily，实现一个 TCC 外汇交易处理，代码提交到 GitHub:

- 用户 A 的美元账户和人民币账户都在 A 库，使用 1 美元兑换 7 人民币 ;
- 用户 B 的美元账户和人民币账户都在 B 库，使用 7 人民币兑换 1 美元 ;
- 设计账户表，冻结资产表，实现上述两个本地事务的分布式事务。


项目链接： [dubbo-hmily](exercise/dubbo-hmily)

**4.（挑战☆☆）** 尝试扩展 Dubbo

- 基于上次作业的自定义序列化，实现 Dubbo 的序列化扩展 ;
- 基于上次作业的自定义 RPC，实现 Dubbo 的 RPC 扩展 ;
- 在 Dubbo 的 filter 机制上，实现 REST 权限控制，可参考 dubbox;
- 实现一个自定义 Dubbo 的 Cluster/Loadbalance 扩展，如果一分钟内调用某个服务 / 提供者超过 10 次，则拒绝提供服务直到下一分钟 ;
- 整合 Dubbo+Sentinel，实现限流功能 ;
- 整合 Dubbo 与 Skywalking，实现全链路性能监控。
