package io.kimmking.rpcfx.demo.provider;

import io.kimmking.rpcfx.api.RpcfxResolver;
import lombok.SneakyThrows;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

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
