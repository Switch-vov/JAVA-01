package com.switchvov.read.write.split.common.context.loadbalance;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 随机负载均衡器
 *
 * @author switch
 * @since 2021/5/2
 */
public class RandomMultiDataSourceLoadBalance implements MultiDataSourceLoadBalance {
    private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();

    @Override
    public String balance(List<HikariDataSource> sources) {
        if (CollectionUtils.isEmpty(sources)) {
            return null;
        }
        return sources.get(RANDOM.nextInt(sources.size())).getPoolName();
    }
}
