package com.switchvov.read.write.split.common.context.loadbalance;

import com.zaxxer.hikari.HikariDataSource;

import java.util.List;

/**
 * 多数据源负载均衡器
 *
 * @author switch
 * @since 2021/5/2
 */
public interface MultiDataSourceLoadBalance {
    String balance(List<HikariDataSource> sources);
}
