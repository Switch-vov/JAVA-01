# 第六周

**周三作业：**

**1.（选做）** 尝试使用 Lambda/Stream/Guava 优化之前作业的代码。

**2.（选做）** 尝试使用 Lambda/Stream/Guava 优化工作中编码的代码。

**3.（选做）** 根据课上提供的材料，系统性学习一遍设计模式，并在工作学习中思考如何用设计模式解决问题。

**4.（选做）** 根据课上提供的材料，深入了解 Google 和 Alibaba 编码规范，并根据这些规范，检查自己写代码是否符合规范，有什么可以改进的。

**周日作业：**

**1.（选做）** 基于课程中的设计原则和最佳实践，分析是否可以将自己负责的业务系统进行数据库设计或是数据库服务器方面的优化

**2.（必做）** 基于电商交易场景（用户、商品、订单），设计一套简单的表结构，提交 DDL 的 SQL 文件到 Github（后面 2 周的作业依然要是用到这个表结构）。

```sql
CREATE TABLE `user`
(
    `id`          bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_id`     bigint(20)  NOT NULL COMMENT '用户ID',
    `name`        varchar(20) NOT NULL DEFAULT '' COMMENT '用户名',
    `create_time` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`   int(11)     NOT NULL DEFAULT '0' COMMENT '创建人',
    `update_time` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`   int(11)     NOT NULL DEFAULT '0' COMMENT '更新人',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='用户表(简化)';
```

```sql
CREATE TABLE `order`
(
    `id`          bigint(20)     NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `order_id`    varchar(30)    NOT NULL DEFAULT '' COMMENT '订单ID',
    `user_id`     bigint(20)     NOT NULL DEFAULT '0' COMMENT '用户ID',
    `sku_number`  varchar(30)    NOT NULL DEFAULT '' COMMENT 'sku编码',
    `total`       decimal(12, 2) NOT NULL DEFAULT '0.00' COMMENT '订单总金额',
    `create_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`   int(11)        NOT NULL DEFAULT '0' COMMENT '创建人',
    `update_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`   int(11)        NOT NULL DEFAULT '0' COMMENT '更新人',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_order_id` (`order_id`) USING BTREE,
    KEY `idx_user_id` (`user_id`) USING BTREE,
    KEY `idx_sku_number` (`sku_number`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='订单表(简化)';
```

```sql
CREATE TABLE `sku`
(
    `id`          bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `sku_number`  varchar(30) NOT NULL DEFAULT '' COMMENT 'sku编码',
    `title`       varchar(50) NOT NULL DEFAULT '' COMMENT '标题',
    `create_time` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`   int(11)     NOT NULL DEFAULT '0' COMMENT '创建人',
    `update_time` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`   int(11)     NOT NULL DEFAULT '0' COMMENT '更新人',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_sku_number` (`sku_number`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='商品sku表(简化)';
```

**3.（选做）** 尽可能多的从“常见关系数据库”中列的清单，安装运行，并使用上一题的 SQL 测试简单的增删改查。

**4.（选做）** 基于上一题，尝试对各个数据库测试 100 万订单数据的增删改查性能。

**5.（选做）** 尝试对 MySQL 不同引擎下测试 100 万订单数据的增删改查性能。

**6.（选做）** 模拟 1000 万订单数据，测试不同方式下导入导出（数据备份还原）MySQL 的速度，包括 jdbc 程序处理和命令行处理。思考和实践，如何提升处理效率。

**7.（选做）** 对 MySQL 配置不同的数据库连接池（DBCP、C3P0、Druid、Hikari），测试增删改查 100 万次，对比性能，生成报告。
