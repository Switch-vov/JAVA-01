package com.switchvov.read.write.split.common.context.loadbalance;

import com.zaxxer.hikari.HikariDataSource;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 轮询负载均衡器
 *
 * @author switch
 * @since 2021/5/2
 */
public class RoundRobinMultiDataSourceLoadBalance implements MultiDataSourceLoadBalance {
    private AtomicInteger counter;

    public RoundRobinMultiDataSourceLoadBalance() {
        this.counter = new AtomicInteger(0);
    }

    @Override
    public String balance(List<HikariDataSource> sources) {
        int index = counter.getAndIncrement() % sources.size();
        return sources.get(index).getPoolName();
    }
}
