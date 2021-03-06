# 第七周

**周三作业：**

**1.（选做）** 用今天课上学习的知识，分析自己系统的 SQL 和表结构

**2.（必做）** 按自己设计的表结构，插入 100 万订单模拟数据，测试不同方式的插入效率

电脑配置：

- 型号：MacBook Pro (15-inch, 2018)
- CPU：2.2 GHz 六核Intel Core i7
- 内存：32 GB 2400 MHz DDR4
- 磁盘：512GB PCI SSD

| 插入数据方式 | 时间(s) |
| :--- | :---: |
| Navicat批量导入 | 59.17 |
| statement单条插入 | 3745 |
| preparedStatement单条插入 | 3573 |
| preparedStatement批量插入 | 2102 |
| preparedStatement单条SQL批量插入 | 34.11 |
| preparedStatement单条SQL批量插入无索引 | 16.56 |
| preparedStatement单条SQL批量插入无索引无创建时间 | 16.12 |

**3.（选做）** 按自己设计的表结构，插入 1000 万订单模拟数据，测试不同方式的插入效

**4.（选做）** 使用不同的索引或组合，测试不同方式查询效率

**5.（选做）** 调整测试数据，使得数据尽量均匀，模拟 1 年时间内的交易，计算一年的销售报表：销售总额，订单数，客单价，每月销售量，前十的商品等等（可以自己设计更多指标）

**6.（选做）** 尝试自己做一个 ID 生成器（可以模拟 Seq 或 Snowflake）

**7.（选做）** 尝试实现或改造一个非精确分页的程序

**周日作业：**

**1.（选做）** 配置一遍异步复制，半同步复制、组复制

- 异步复制：[master-slave](exercise/mysql/master-slave)
- 半同步复制：TODO
- 组复制：TODO

**2.（必做）** 读写分离 - 动态切换数据源版本 1.0

V1.0代码：[read_write_split_v1](exercise/read_write_split_v1)

核心类：

```java
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
```

**3.（必做）** 读写分离 - 数据库框架版本 2.0

V2.0代码：[read_write_split_v2](exercise/read_write_split_v2)

关键配置：

```yaml
spring:
  shardingsphere:
    datasource:
      names: master,slave1,slave2
      common:
        type: com.zaxxer.hikari.HikariDataSource
        driver-class-name: com.mysql.jdbc.Driver
        username: root
        password: root
      master:
        jdbc-url: jdbc:mysql://localhost:13300/test?serverTimezone=Asia/Shanghai
      slave1:
        jdbc-url: jdbc:mysql://localhost:23300/test?serverTimezone=Asia/Shanghai
      slave2:
        jdbc-url: jdbc:mysql://localhost:33300/test?serverTimezone=Asia/Shanghai
    rules:
      replica-query:
        data-sources:
          readwrite:
            load-balancer-name: roundrobin # 负载均衡算法名称
            primary-data-source-name: master # 主数据源名称
            replica-data-source-names: slave1,slave2 # 从数据源名称，多个从数据源用逗号分隔
        load-balancers:
          roundrobin:
            type: ROUND_ROBIN # 负载均衡算法类型
            props:
              name: switch
    props:
      sql-show: true
```

**4.（选做）** 读写分离 - 数据库中间件版本 3.0

**5.（选做）** 配置 MHA，模拟 master 宕机

**6.（选做）** 配置 MGR，模拟 master 宕机

**7.（选做）** 配置 Orchestrator，模拟 master 宕机，演练 UI 调整拓扑结构
