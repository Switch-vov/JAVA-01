package com.switchvov.read.write.split.configure;

import com.zaxxer.hikari.HikariConfig;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author switch
 * @since 2021/5/2
 */
@Configuration
@ConfigurationProperties(prefix = "spring.datasource.multi")
@Data
public class MultiDataSourceProperties {
    /**
     * hikari配置
     */
    private MultiHikariDataSourceProperties hikari;

    @Data
    public static class MultiHikariDataSourceProperties {
        /**
         * 主节点(写节点)
         */
        private MultiHikariConfig master;

        /**
         * 从节点列表(读节点列表)
         */
        private List<MultiHikariConfig> slaves;
    }

    public static class MultiHikariConfig extends HikariConfig {

    }
}
