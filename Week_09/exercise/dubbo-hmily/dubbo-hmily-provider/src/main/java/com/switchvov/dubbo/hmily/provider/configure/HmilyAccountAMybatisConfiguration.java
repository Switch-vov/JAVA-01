package com.switchvov.dubbo.hmily.provider.configure;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @author switch
 * @since 2021/5/10
 */
@Configuration
@MapperScan(basePackages = "com.switchvov.dubbo.hmily.provider.mapper.hmilyaccounta", sqlSessionTemplateRef = "hmilyAccountASqlSessionTemplate")
public class HmilyAccountAMybatisConfiguration {
    @Bean("hmilyAccountASqlSessionFactory")
    public SqlSessionFactory hmilyAccountASqlSessionFactory(
            @Qualifier("hmilyAccountADataSource") DataSource dataSource
    ) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath*:mapper/hmilyaccounta/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "hmilyAccountASqlSessionTemplate")
    public SqlSessionTemplate hmilyAccountASqlSessionTemplate(
            @Qualifier("hmilyAccountASqlSessionFactory") SqlSessionFactory sqlSessionFactory
    ) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}