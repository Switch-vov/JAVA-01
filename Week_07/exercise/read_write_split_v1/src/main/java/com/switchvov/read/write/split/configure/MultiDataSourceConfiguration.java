package com.switchvov.read.write.split.configure;

import com.switchvov.read.write.split.common.context.MultiDataSourceAspect;
import com.switchvov.read.write.split.common.context.MultiDataSourceContextHolder;
import com.switchvov.read.write.split.common.context.loadbalance.MultiDataSourceLoadBalance;
import com.switchvov.read.write.split.common.context.loadbalance.RoundRobinMultiDataSourceLoadBalance;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author switch
 * @since 2021/5/2
 */
@Configuration
public class MultiDataSourceConfiguration {
    private final MultiDataSourceProperties multiDataSourceProps;

    public MultiDataSourceConfiguration(
            MultiDataSourceProperties multiDataSourceProps
    ) {
        this.multiDataSourceProps = multiDataSourceProps;
    }

    @Bean("masterHikariDataSource")
    public HikariDataSource masterHikariDataSource() {
        return new HikariDataSource(multiDataSourceProps.getHikari().getMaster());
    }

    @Bean("slaveHikariDataSources")
    public List<HikariDataSource> slaveHikariDataSources() {
        return multiDataSourceProps.getHikari().getSlaves().stream()
                .map(HikariDataSource::new)
                .collect(Collectors.toList());
    }

    @Bean
    public MultiDataSourceLoadBalance multiDataSourceLoadBalance() {
        // 随机
        // return new RandomMultiDataSourceLoadBalance();
        // 轮询
        return new RoundRobinMultiDataSourceLoadBalance();
    }

    @Bean
    public MultiDataSourceAspect multiDataSourceAspect() {
        return new MultiDataSourceAspect(slaveHikariDataSources(), multiDataSourceLoadBalance());
    }

    @Bean("routingDataSource")
    public AbstractRoutingDataSource routingDataSource() {
        AbstractRoutingDataSource abstractRoutingDataSource = new AbstractRoutingDataSource() {
            @Override
            protected Object determineCurrentLookupKey() {
                return MultiDataSourceContextHolder.getDataSourceName();
            }
        };
        Map<Object, Object> targetDataSources = new HashMap<>(16);
        HikariDataSource masterHikariDataSource = masterHikariDataSource();
        targetDataSources.put(masterHikariDataSource.getPoolName(), masterHikariDataSource);
        for (HikariDataSource slaveHikariDataSource : slaveHikariDataSources()) {
            targetDataSources.put(slaveHikariDataSource.getPoolName(), slaveHikariDataSource);
        }
        abstractRoutingDataSource.setTargetDataSources(targetDataSources);
        abstractRoutingDataSource.setDefaultTargetDataSource(masterHikariDataSource);
        return abstractRoutingDataSource;
    }
}
