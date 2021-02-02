# JVM相关笔记

[TOC]

## 在mac中JDK8使用jmap有问题

建议使用jcmd 5805 VM.flags，jcmd 5805 GC.heap_info来查看。

## Parallel GC和CMS GC的最大young区大小如何计算？

默认情况下，大小会受到自适应参数影响，我们先关掉此参数`-XX:-UseAdaptiveSizePolicy`。

然后试验如下：

```bash
java -Xmx1g -Xms1g -XX:-UseAdaptiveSizePolicy -XX:+UseParallelGC -jar gateway-server-0.0.1-SNAPSHOT.jar
MaxHeapSize              = 1073741824 (1024.0MB)
NewSize                     = 357564416 (341.0MB)
MaxNewSize               = 357564416 (341.0MB)
OldSize                      = 716177408 (683.0MB)

java -Xmx1g -Xms1g -XX:-UseAdaptiveSizePolicy -XX:+UseConcMarkSweepGC -jar gateway-server-0.0.1-SNAPSHOT.jar
MaxHeapSize              = 1073741824 (1024.0MB)
NewSize                     = 348913664 (332.75MB)
MaxNewSize               = 348913664 (332.75MB)
OldSize                      = 724828160 (691.25MB)

java -Xmx2g -Xms2g -XX:-UseAdaptiveSizePolicy -XX:+UseConcMarkSweepGC -jar gateway-server-0.0.1-SNAPSHOT.jar
MaxHeapSize              = 2147483648 (2048.0MB)
NewSize                     = 348913664 (332.75MB)
MaxNewSize               = 348913664 (332.75MB)
OldSize                      = 1798569984 (1715.25MB)
```

可以看到`ParallelGC`下，young区大小为`1024/3 = 341.3M`，跟上述显示一致。

CMS情况下则为332.75M，不是1/3，并且在xmx为2048M时，还是332.75M，这说明最大young区大小与Xmx参数无关。

实际上，我的电脑上：`64M * 并发GC线程数(4) * 13 / 10 =332.8M`

**这个式子是jvm代码写死的，只跟GC线程数有关系。**

继续测试：

```bash
-XX:ParallelGCThreads=2
MaxHeapSize              = 2147483648 (2048.0MB)
NewSize                     = 174456832 (166.375MB)
MaxNewSize               = 174456832 (166.375MB)
OldSize                      = 1973026816 (1881.625MB)

-XX:ParallelGCThreads=8
MaxHeapSize              = 2147483648 (2048.0MB)
NewSize                     = 697892864 (665.5625MB)
MaxNewSize               = 697892864 (665.5625MB)
OldSize                      = 1449590784 (1382.4375MB)
```

证实了我们的猜测，并发线程为2时，大小为166M；线程为8时，为665M。

> **注意：线程数超过8的时候，会退化成按newRatio计算。**

##【JVM参数的ratio】
ratio是N的话，一般计算基数就是`1/(N+1)`;

一边是`N/(N+1)`，一边是`1/(N+1)`;

特别地，`newRatio = 2`说的是，new也就是young区最大值占heap的`1/(2+1) = 1/3`, 这里是最大值，也就是MaxNewSize。

xmx=1024m的话，对应于`maxNewSize=1024/3m = 341m`。

survivor因为有两个，所以survivorRatio是N的话，计算基数就是`1/(N+2)`；

每个survivor，不管是s0，还是s1，都占new区的最大值的`1/(N+2)`。

`survivorRatio = 8`对应于s0最大值=s1最大值=new区最大值的1/(8+2) = 1/10，也就是说最大值`eden:s0:s1 = 8:1:1`。

如果maxNewSize = 341m，那么s0和s1的最大值是34m，eden最大值是273m。

## CMS GC的 默认GC进程数是怎么来的？

区分`young`区的`parnew gc`线程数和`old`区的`cms`线程数，分别为以下两参数：
```bash
-XX:ParallelGCThreads=m
-XX:ConcGCThreads=n
```

其中`ParallelGCThreads`参数的默认值是：

- CPU核心数 <= 8，则为 ParallelGCThreads=CPU核心数，比如我的那个旧电脑是4
- CPU核心数 > 8，则为 ParallelGCThreads = CPU核心数 * 5/8 + 3 向下取整
- 16核的情况下，ParallelGCThreads = 13
- 32核的情况下，ParallelGCThreads = 23
- 64核的情况下，ParallelGCThreads = 43
- 72核的情况下，ParallelGCThreads = 48

ConcGCThreads的默认值则为：

`ConcGCThreads = (ParallelGCThreads + 3)/4`向下去整。

- ParallelGCThreads = 1~4时，ConcGCThreads = 1
- ParallelGCThreads = 5~8时，ConcGCThreads = 2
- ParallelGCThreads = 9~12时，ConcGCThreads = 3
- ParallelGCThreads = 13~16时，ConcGCThreads = 4

