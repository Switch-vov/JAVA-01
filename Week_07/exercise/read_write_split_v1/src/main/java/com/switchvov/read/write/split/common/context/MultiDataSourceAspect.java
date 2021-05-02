package com.switchvov.read.write.split.common.context;

import com.switchvov.read.write.split.common.context.loadbalance.MultiDataSourceLoadBalance;
import com.zaxxer.hikari.HikariDataSource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.List;

/**
 * @author switch
 * @since 2021/5/2
 */
@Aspect
public class MultiDataSourceAspect {
    private final List<HikariDataSource> slaveDataSources;

    private final MultiDataSourceLoadBalance multiDataSourceLoadBalance;

    public MultiDataSourceAspect(
            List<HikariDataSource> slaveDataSources,
            MultiDataSourceLoadBalance multiDataSourceLoadBalance
    ) {
        this.slaveDataSources = slaveDataSources;
        this.multiDataSourceLoadBalance = multiDataSourceLoadBalance;
    }

    @Pointcut("@annotation(com.switchvov.read.write.split.common.context.ReadOnly)")
    public void multiDataSourcePointcut() {

    }

    @Around(value = "multiDataSourcePointcut()")
    public Object multiDataSourceHandler(ProceedingJoinPoint joinPoint) throws Throwable {
        String dataSourceName = multiDataSourceLoadBalance.balance(slaveDataSources);
        MultiDataSourceContextHolder.setDataSourceName(dataSourceName);
        Object result;
        try {
            result = joinPoint.proceed();
        } finally {
            MultiDataSourceContextHolder.clearDataSourceName();
        }
        return result;
    }
}
