package com.switchvov.dubbo.hmily.provider.configure;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * 多数据源配置
 *
 * @author switch
 * @since 2021/5/10
 */
@Configuration
public class DataSourceConfiguration {

    @Configuration
    @ConfigurationProperties(prefix = "spring.datasource.hmily-account-a")
    static class HmilyAccountADataSourceProperties extends HikariConfig {
    }

    @Configuration
    @ConfigurationProperties(prefix = "spring.datasource.hmily-account-b")
    static class HmilyAccountBDataSourceProperties extends HikariConfig {
    }

    @Configuration
    @ConfigurationProperties(prefix = "spring.datasource.hmily-account-freeze")
    static class HmilyAccountFreezeDataSourceProperties extends HikariConfig {
    }


    private final HmilyAccountADataSourceProperties hmilyAccountADataSourceProperties;
    private final HmilyAccountBDataSourceProperties hmilyAccountBDataSourceProperties;
    private final HmilyAccountFreezeDataSourceProperties hmilyAccountFreezeDataSourceProperties;

    public DataSourceConfiguration(
            HmilyAccountADataSourceProperties hmilyAccountADataSourceProperties,
            HmilyAccountBDataSourceProperties hmilyAccountBDataSourceProperties,
            HmilyAccountFreezeDataSourceProperties hmilyAccountFreezeDataSourceProperties
    ) {
        this.hmilyAccountADataSourceProperties = hmilyAccountADataSourceProperties;
        this.hmilyAccountBDataSourceProperties = hmilyAccountBDataSourceProperties;
        this.hmilyAccountFreezeDataSourceProperties = hmilyAccountFreezeDataSourceProperties;
    }

    @Bean(name = "hmilyAccountADataSource")
    @Primary
    public DataSource hmilyAccountADataSource() {
        return new HikariDataSource(hmilyAccountADataSourceProperties);
    }

    @Bean(name = "hmilyAccountBDataSource")
    public DataSource hmilyAccountBDataSource() {
        return new HikariDataSource(hmilyAccountBDataSourceProperties);
    }

    @Bean(name = "hmilyAccountFreezeDataSource")
    public DataSource hmilyAccountFreezeDataSource() {
        return new HikariDataSource(hmilyAccountFreezeDataSourceProperties);
    }
}
