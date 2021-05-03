CREATE DATABASE order_0 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;
CREATE DATABASE order_1 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

-- 数据库 order0

CREATE TABLE `order_0`.`order_0`
(
    `id`          bigint         NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `order_id`    varchar(30)    NOT NULL DEFAULT '' COMMENT '订单ID',
    `user_id`     bigint         NOT NULL DEFAULT '0' COMMENT '用户ID',
    `sku_number`  varchar(30)    NOT NULL DEFAULT '' COMMENT 'sku编码',
    `total`       decimal(12, 2) NOT NULL DEFAULT '0.00' COMMENT '订单总金额',
    `create_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`   int            NOT NULL DEFAULT '0' COMMENT '创建人',
    `update_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`   int            NOT NULL DEFAULT '0' COMMENT '更新人',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_order_id` (`order_id`) USING BTREE,
    KEY `idx_user_id` (`user_id`) USING BTREE,
    KEY `idx_sku_number` (`sku_number`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='订单表(简化)';

CREATE TABLE `order_0`.`order_1`
(
    `id`          bigint         NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `order_id`    varchar(30)    NOT NULL DEFAULT '' COMMENT '订单ID',
    `user_id`     bigint         NOT NULL DEFAULT '0' COMMENT '用户ID',
    `sku_number`  varchar(30)    NOT NULL DEFAULT '' COMMENT 'sku编码',
    `total`       decimal(12, 2) NOT NULL DEFAULT '0.00' COMMENT '订单总金额',
    `create_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`   int            NOT NULL DEFAULT '0' COMMENT '创建人',
    `update_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`   int            NOT NULL DEFAULT '0' COMMENT '更新人',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_order_id` (`order_id`) USING BTREE,
    KEY `idx_user_id` (`user_id`) USING BTREE,
    KEY `idx_sku_number` (`sku_number`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='订单表(简化)';


CREATE TABLE `order_0`.`order_2`
(
    `id`          bigint         NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `order_id`    varchar(30)    NOT NULL DEFAULT '' COMMENT '订单ID',
    `user_id`     bigint         NOT NULL DEFAULT '0' COMMENT '用户ID',
    `sku_number`  varchar(30)    NOT NULL DEFAULT '' COMMENT 'sku编码',
    `total`       decimal(12, 2) NOT NULL DEFAULT '0.00' COMMENT '订单总金额',
    `create_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`   int            NOT NULL DEFAULT '0' COMMENT '创建人',
    `update_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`   int            NOT NULL DEFAULT '0' COMMENT '更新人',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_order_id` (`order_id`) USING BTREE,
    KEY `idx_user_id` (`user_id`) USING BTREE,
    KEY `idx_sku_number` (`sku_number`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='订单表(简化)';

CREATE TABLE `order_0`.`order_3`
(
    `id`          bigint         NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `order_id`    varchar(30)    NOT NULL DEFAULT '' COMMENT '订单ID',
    `user_id`     bigint         NOT NULL DEFAULT '0' COMMENT '用户ID',
    `sku_number`  varchar(30)    NOT NULL DEFAULT '' COMMENT 'sku编码',
    `total`       decimal(12, 2) NOT NULL DEFAULT '0.00' COMMENT '订单总金额',
    `create_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`   int            NOT NULL DEFAULT '0' COMMENT '创建人',
    `update_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`   int            NOT NULL DEFAULT '0' COMMENT '更新人',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_order_id` (`order_id`) USING BTREE,
    KEY `idx_user_id` (`user_id`) USING BTREE,
    KEY `idx_sku_number` (`sku_number`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='订单表(简化)';

CREATE TABLE `order_0`.`order_4`
(
    `id`          bigint         NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `order_id`    varchar(30)    NOT NULL DEFAULT '' COMMENT '订单ID',
    `user_id`     bigint         NOT NULL DEFAULT '0' COMMENT '用户ID',
    `sku_number`  varchar(30)    NOT NULL DEFAULT '' COMMENT 'sku编码',
    `total`       decimal(12, 2) NOT NULL DEFAULT '0.00' COMMENT '订单总金额',
    `create_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`   int            NOT NULL DEFAULT '0' COMMENT '创建人',
    `update_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`   int            NOT NULL DEFAULT '0' COMMENT '更新人',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_order_id` (`order_id`) USING BTREE,
    KEY `idx_user_id` (`user_id`) USING BTREE,
    KEY `idx_sku_number` (`sku_number`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='订单表(简化)';

CREATE TABLE `order_0`.`order_5`
(
    `id`          bigint         NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `order_id`    varchar(30)    NOT NULL DEFAULT '' COMMENT '订单ID',
    `user_id`     bigint         NOT NULL DEFAULT '0' COMMENT '用户ID',
    `sku_number`  varchar(30)    NOT NULL DEFAULT '' COMMENT 'sku编码',
    `total`       decimal(12, 2) NOT NULL DEFAULT '0.00' COMMENT '订单总金额',
    `create_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`   int            NOT NULL DEFAULT '0' COMMENT '创建人',
    `update_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`   int            NOT NULL DEFAULT '0' COMMENT '更新人',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_order_id` (`order_id`) USING BTREE,
    KEY `idx_user_id` (`user_id`) USING BTREE,
    KEY `idx_sku_number` (`sku_number`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='订单表(简化)';

CREATE TABLE `order_0`.`order_6`
(
    `id`          bigint         NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `order_id`    varchar(30)    NOT NULL DEFAULT '' COMMENT '订单ID',
    `user_id`     bigint         NOT NULL DEFAULT '0' COMMENT '用户ID',
    `sku_number`  varchar(30)    NOT NULL DEFAULT '' COMMENT 'sku编码',
    `total`       decimal(12, 2) NOT NULL DEFAULT '0.00' COMMENT '订单总金额',
    `create_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`   int            NOT NULL DEFAULT '0' COMMENT '创建人',
    `update_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`   int            NOT NULL DEFAULT '0' COMMENT '更新人',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_order_id` (`order_id`) USING BTREE,
    KEY `idx_user_id` (`user_id`) USING BTREE,
    KEY `idx_sku_number` (`sku_number`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='订单表(简化)';

CREATE TABLE `order_0`.`order_7`
(
    `id`          bigint         NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `order_id`    varchar(30)    NOT NULL DEFAULT '' COMMENT '订单ID',
    `user_id`     bigint         NOT NULL DEFAULT '0' COMMENT '用户ID',
    `sku_number`  varchar(30)    NOT NULL DEFAULT '' COMMENT 'sku编码',
    `total`       decimal(12, 2) NOT NULL DEFAULT '0.00' COMMENT '订单总金额',
    `create_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`   int            NOT NULL DEFAULT '0' COMMENT '创建人',
    `update_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`   int            NOT NULL DEFAULT '0' COMMENT '更新人',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_order_id` (`order_id`) USING BTREE,
    KEY `idx_user_id` (`user_id`) USING BTREE,
    KEY `idx_sku_number` (`sku_number`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='订单表(简化)';

CREATE TABLE `order_0`.`order_8`
(
    `id`          bigint         NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `order_id`    varchar(30)    NOT NULL DEFAULT '' COMMENT '订单ID',
    `user_id`     bigint         NOT NULL DEFAULT '0' COMMENT '用户ID',
    `sku_number`  varchar(30)    NOT NULL DEFAULT '' COMMENT 'sku编码',
    `total`       decimal(12, 2) NOT NULL DEFAULT '0.00' COMMENT '订单总金额',
    `create_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`   int            NOT NULL DEFAULT '0' COMMENT '创建人',
    `update_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`   int            NOT NULL DEFAULT '0' COMMENT '更新人',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_order_id` (`order_id`) USING BTREE,
    KEY `idx_user_id` (`user_id`) USING BTREE,
    KEY `idx_sku_number` (`sku_number`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='订单表(简化)';

CREATE TABLE `order_0`.`order_9`
(
    `id`          bigint         NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `order_id`    varchar(30)    NOT NULL DEFAULT '' COMMENT '订单ID',
    `user_id`     bigint         NOT NULL DEFAULT '0' COMMENT '用户ID',
    `sku_number`  varchar(30)    NOT NULL DEFAULT '' COMMENT 'sku编码',
    `total`       decimal(12, 2) NOT NULL DEFAULT '0.00' COMMENT '订单总金额',
    `create_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`   int            NOT NULL DEFAULT '0' COMMENT '创建人',
    `update_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`   int            NOT NULL DEFAULT '0' COMMENT '更新人',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_order_id` (`order_id`) USING BTREE,
    KEY `idx_user_id` (`user_id`) USING BTREE,
    KEY `idx_sku_number` (`sku_number`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='订单表(简化)';

CREATE TABLE `order_0`.`order_10`
(
    `id`          bigint         NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `order_id`    varchar(30)    NOT NULL DEFAULT '' COMMENT '订单ID',
    `user_id`     bigint         NOT NULL DEFAULT '0' COMMENT '用户ID',
    `sku_number`  varchar(30)    NOT NULL DEFAULT '' COMMENT 'sku编码',
    `total`       decimal(12, 2) NOT NULL DEFAULT '0.00' COMMENT '订单总金额',
    `create_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`   int            NOT NULL DEFAULT '0' COMMENT '创建人',
    `update_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`   int            NOT NULL DEFAULT '0' COMMENT '更新人',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_order_id` (`order_id`) USING BTREE,
    KEY `idx_user_id` (`user_id`) USING BTREE,
    KEY `idx_sku_number` (`sku_number`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='订单表(简化)';

CREATE TABLE `order_0`.`order_11`
(
    `id`          bigint         NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `order_id`    varchar(30)    NOT NULL DEFAULT '' COMMENT '订单ID',
    `user_id`     bigint         NOT NULL DEFAULT '0' COMMENT '用户ID',
    `sku_number`  varchar(30)    NOT NULL DEFAULT '' COMMENT 'sku编码',
    `total`       decimal(12, 2) NOT NULL DEFAULT '0.00' COMMENT '订单总金额',
    `create_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`   int            NOT NULL DEFAULT '0' COMMENT '创建人',
    `update_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`   int            NOT NULL DEFAULT '0' COMMENT '更新人',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_order_id` (`order_id`) USING BTREE,
    KEY `idx_user_id` (`user_id`) USING BTREE,
    KEY `idx_sku_number` (`sku_number`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='订单表(简化)';

CREATE TABLE `order_0`.`order_12`
(
    `id`          bigint         NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `order_id`    varchar(30)    NOT NULL DEFAULT '' COMMENT '订单ID',
    `user_id`     bigint         NOT NULL DEFAULT '0' COMMENT '用户ID',
    `sku_number`  varchar(30)    NOT NULL DEFAULT '' COMMENT 'sku编码',
    `total`       decimal(12, 2) NOT NULL DEFAULT '0.00' COMMENT '订单总金额',
    `create_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`   int            NOT NULL DEFAULT '0' COMMENT '创建人',
    `update_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`   int            NOT NULL DEFAULT '0' COMMENT '更新人',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_order_id` (`order_id`) USING BTREE,
    KEY `idx_user_id` (`user_id`) USING BTREE,
    KEY `idx_sku_number` (`sku_number`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='订单表(简化)';

CREATE TABLE `order_0`.`order_13`
(
    `id`          bigint         NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `order_id`    varchar(30)    NOT NULL DEFAULT '' COMMENT '订单ID',
    `user_id`     bigint         NOT NULL DEFAULT '0' COMMENT '用户ID',
    `sku_number`  varchar(30)    NOT NULL DEFAULT '' COMMENT 'sku编码',
    `total`       decimal(12, 2) NOT NULL DEFAULT '0.00' COMMENT '订单总金额',
    `create_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`   int            NOT NULL DEFAULT '0' COMMENT '创建人',
    `update_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`   int            NOT NULL DEFAULT '0' COMMENT '更新人',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_order_id` (`order_id`) USING BTREE,
    KEY `idx_user_id` (`user_id`) USING BTREE,
    KEY `idx_sku_number` (`sku_number`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='订单表(简化)';

CREATE TABLE `order_0`.`order_14`
(
    `id`          bigint         NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `order_id`    varchar(30)    NOT NULL DEFAULT '' COMMENT '订单ID',
    `user_id`     bigint         NOT NULL DEFAULT '0' COMMENT '用户ID',
    `sku_number`  varchar(30)    NOT NULL DEFAULT '' COMMENT 'sku编码',
    `total`       decimal(12, 2) NOT NULL DEFAULT '0.00' COMMENT '订单总金额',
    `create_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`   int            NOT NULL DEFAULT '0' COMMENT '创建人',
    `update_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`   int            NOT NULL DEFAULT '0' COMMENT '更新人',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_order_id` (`order_id`) USING BTREE,
    KEY `idx_user_id` (`user_id`) USING BTREE,
    KEY `idx_sku_number` (`sku_number`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='订单表(简化)';

CREATE TABLE `order_0`.`order_15`
(
    `id`          bigint         NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `order_id`    varchar(30)    NOT NULL DEFAULT '' COMMENT '订单ID',
    `user_id`     bigint         NOT NULL DEFAULT '0' COMMENT '用户ID',
    `sku_number`  varchar(30)    NOT NULL DEFAULT '' COMMENT 'sku编码',
    `total`       decimal(12, 2) NOT NULL DEFAULT '0.00' COMMENT '订单总金额',
    `create_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`   int            NOT NULL DEFAULT '0' COMMENT '创建人',
    `update_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`   int            NOT NULL DEFAULT '0' COMMENT '更新人',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_order_id` (`order_id`) USING BTREE,
    KEY `idx_user_id` (`user_id`) USING BTREE,
    KEY `idx_sku_number` (`sku_number`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='订单表(简化)';

-- 数据库 order1

CREATE TABLE `order_1`.`order_0`
(
    `id`          bigint         NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `order_id`    varchar(30)    NOT NULL DEFAULT '' COMMENT '订单ID',
    `user_id`     bigint         NOT NULL DEFAULT '0' COMMENT '用户ID',
    `sku_number`  varchar(30)    NOT NULL DEFAULT '' COMMENT 'sku编码',
    `total`       decimal(12, 2) NOT NULL DEFAULT '0.00' COMMENT '订单总金额',
    `create_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`   int            NOT NULL DEFAULT '0' COMMENT '创建人',
    `update_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`   int            NOT NULL DEFAULT '0' COMMENT '更新人',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_order_id` (`order_id`) USING BTREE,
    KEY `idx_user_id` (`user_id`) USING BTREE,
    KEY `idx_sku_number` (`sku_number`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='订单表(简化)';

CREATE TABLE `order_1`.`order_1`
(
    `id`          bigint         NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `order_id`    varchar(30)    NOT NULL DEFAULT '' COMMENT '订单ID',
    `user_id`     bigint         NOT NULL DEFAULT '0' COMMENT '用户ID',
    `sku_number`  varchar(30)    NOT NULL DEFAULT '' COMMENT 'sku编码',
    `total`       decimal(12, 2) NOT NULL DEFAULT '0.00' COMMENT '订单总金额',
    `create_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`   int            NOT NULL DEFAULT '0' COMMENT '创建人',
    `update_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`   int            NOT NULL DEFAULT '0' COMMENT '更新人',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_order_id` (`order_id`) USING BTREE,
    KEY `idx_user_id` (`user_id`) USING BTREE,
    KEY `idx_sku_number` (`sku_number`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='订单表(简化)';


CREATE TABLE `order_1`.`order_2`
(
    `id`          bigint         NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `order_id`    varchar(30)    NOT NULL DEFAULT '' COMMENT '订单ID',
    `user_id`     bigint         NOT NULL DEFAULT '0' COMMENT '用户ID',
    `sku_number`  varchar(30)    NOT NULL DEFAULT '' COMMENT 'sku编码',
    `total`       decimal(12, 2) NOT NULL DEFAULT '0.00' COMMENT '订单总金额',
    `create_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`   int            NOT NULL DEFAULT '0' COMMENT '创建人',
    `update_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`   int            NOT NULL DEFAULT '0' COMMENT '更新人',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_order_id` (`order_id`) USING BTREE,
    KEY `idx_user_id` (`user_id`) USING BTREE,
    KEY `idx_sku_number` (`sku_number`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='订单表(简化)';

CREATE TABLE `order_1`.`order_3`
(
    `id`          bigint         NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `order_id`    varchar(30)    NOT NULL DEFAULT '' COMMENT '订单ID',
    `user_id`     bigint         NOT NULL DEFAULT '0' COMMENT '用户ID',
    `sku_number`  varchar(30)    NOT NULL DEFAULT '' COMMENT 'sku编码',
    `total`       decimal(12, 2) NOT NULL DEFAULT '0.00' COMMENT '订单总金额',
    `create_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`   int            NOT NULL DEFAULT '0' COMMENT '创建人',
    `update_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`   int            NOT NULL DEFAULT '0' COMMENT '更新人',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_order_id` (`order_id`) USING BTREE,
    KEY `idx_user_id` (`user_id`) USING BTREE,
    KEY `idx_sku_number` (`sku_number`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='订单表(简化)';

CREATE TABLE `order_1`.`order_4`
(
    `id`          bigint         NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `order_id`    varchar(30)    NOT NULL DEFAULT '' COMMENT '订单ID',
    `user_id`     bigint         NOT NULL DEFAULT '0' COMMENT '用户ID',
    `sku_number`  varchar(30)    NOT NULL DEFAULT '' COMMENT 'sku编码',
    `total`       decimal(12, 2) NOT NULL DEFAULT '0.00' COMMENT '订单总金额',
    `create_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`   int            NOT NULL DEFAULT '0' COMMENT '创建人',
    `update_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`   int            NOT NULL DEFAULT '0' COMMENT '更新人',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_order_id` (`order_id`) USING BTREE,
    KEY `idx_user_id` (`user_id`) USING BTREE,
    KEY `idx_sku_number` (`sku_number`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='订单表(简化)';

CREATE TABLE `order_1`.`order_5`
(
    `id`          bigint         NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `order_id`    varchar(30)    NOT NULL DEFAULT '' COMMENT '订单ID',
    `user_id`     bigint         NOT NULL DEFAULT '0' COMMENT '用户ID',
    `sku_number`  varchar(30)    NOT NULL DEFAULT '' COMMENT 'sku编码',
    `total`       decimal(12, 2) NOT NULL DEFAULT '0.00' COMMENT '订单总金额',
    `create_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`   int            NOT NULL DEFAULT '0' COMMENT '创建人',
    `update_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`   int            NOT NULL DEFAULT '0' COMMENT '更新人',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_order_id` (`order_id`) USING BTREE,
    KEY `idx_user_id` (`user_id`) USING BTREE,
    KEY `idx_sku_number` (`sku_number`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='订单表(简化)';

CREATE TABLE `order_1`.`order_6`
(
    `id`          bigint         NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `order_id`    varchar(30)    NOT NULL DEFAULT '' COMMENT '订单ID',
    `user_id`     bigint         NOT NULL DEFAULT '0' COMMENT '用户ID',
    `sku_number`  varchar(30)    NOT NULL DEFAULT '' COMMENT 'sku编码',
    `total`       decimal(12, 2) NOT NULL DEFAULT '0.00' COMMENT '订单总金额',
    `create_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`   int            NOT NULL DEFAULT '0' COMMENT '创建人',
    `update_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`   int            NOT NULL DEFAULT '0' COMMENT '更新人',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_order_id` (`order_id`) USING BTREE,
    KEY `idx_user_id` (`user_id`) USING BTREE,
    KEY `idx_sku_number` (`sku_number`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='订单表(简化)';

CREATE TABLE `order_1`.`order_7`
(
    `id`          bigint         NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `order_id`    varchar(30)    NOT NULL DEFAULT '' COMMENT '订单ID',
    `user_id`     bigint         NOT NULL DEFAULT '0' COMMENT '用户ID',
    `sku_number`  varchar(30)    NOT NULL DEFAULT '' COMMENT 'sku编码',
    `total`       decimal(12, 2) NOT NULL DEFAULT '0.00' COMMENT '订单总金额',
    `create_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`   int            NOT NULL DEFAULT '0' COMMENT '创建人',
    `update_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`   int            NOT NULL DEFAULT '0' COMMENT '更新人',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_order_id` (`order_id`) USING BTREE,
    KEY `idx_user_id` (`user_id`) USING BTREE,
    KEY `idx_sku_number` (`sku_number`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='订单表(简化)';

CREATE TABLE `order_1`.`order_8`
(
    `id`          bigint         NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `order_id`    varchar(30)    NOT NULL DEFAULT '' COMMENT '订单ID',
    `user_id`     bigint         NOT NULL DEFAULT '0' COMMENT '用户ID',
    `sku_number`  varchar(30)    NOT NULL DEFAULT '' COMMENT 'sku编码',
    `total`       decimal(12, 2) NOT NULL DEFAULT '0.00' COMMENT '订单总金额',
    `create_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`   int            NOT NULL DEFAULT '0' COMMENT '创建人',
    `update_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`   int            NOT NULL DEFAULT '0' COMMENT '更新人',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_order_id` (`order_id`) USING BTREE,
    KEY `idx_user_id` (`user_id`) USING BTREE,
    KEY `idx_sku_number` (`sku_number`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='订单表(简化)';

CREATE TABLE `order_1`.`order_9`
(
    `id`          bigint         NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `order_id`    varchar(30)    NOT NULL DEFAULT '' COMMENT '订单ID',
    `user_id`     bigint         NOT NULL DEFAULT '0' COMMENT '用户ID',
    `sku_number`  varchar(30)    NOT NULL DEFAULT '' COMMENT 'sku编码',
    `total`       decimal(12, 2) NOT NULL DEFAULT '0.00' COMMENT '订单总金额',
    `create_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`   int            NOT NULL DEFAULT '0' COMMENT '创建人',
    `update_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`   int            NOT NULL DEFAULT '0' COMMENT '更新人',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_order_id` (`order_id`) USING BTREE,
    KEY `idx_user_id` (`user_id`) USING BTREE,
    KEY `idx_sku_number` (`sku_number`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='订单表(简化)';

CREATE TABLE `order_1`.`order_10`
(
    `id`          bigint         NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `order_id`    varchar(30)    NOT NULL DEFAULT '' COMMENT '订单ID',
    `user_id`     bigint         NOT NULL DEFAULT '0' COMMENT '用户ID',
    `sku_number`  varchar(30)    NOT NULL DEFAULT '' COMMENT 'sku编码',
    `total`       decimal(12, 2) NOT NULL DEFAULT '0.00' COMMENT '订单总金额',
    `create_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`   int            NOT NULL DEFAULT '0' COMMENT '创建人',
    `update_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`   int            NOT NULL DEFAULT '0' COMMENT '更新人',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_order_id` (`order_id`) USING BTREE,
    KEY `idx_user_id` (`user_id`) USING BTREE,
    KEY `idx_sku_number` (`sku_number`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='订单表(简化)';

CREATE TABLE `order_1`.`order_11`
(
    `id`          bigint         NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `order_id`    varchar(30)    NOT NULL DEFAULT '' COMMENT '订单ID',
    `user_id`     bigint         NOT NULL DEFAULT '0' COMMENT '用户ID',
    `sku_number`  varchar(30)    NOT NULL DEFAULT '' COMMENT 'sku编码',
    `total`       decimal(12, 2) NOT NULL DEFAULT '0.00' COMMENT '订单总金额',
    `create_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`   int            NOT NULL DEFAULT '0' COMMENT '创建人',
    `update_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`   int            NOT NULL DEFAULT '0' COMMENT '更新人',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_order_id` (`order_id`) USING BTREE,
    KEY `idx_user_id` (`user_id`) USING BTREE,
    KEY `idx_sku_number` (`sku_number`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='订单表(简化)';

CREATE TABLE `order_1`.`order_12`
(
    `id`          bigint         NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `order_id`    varchar(30)    NOT NULL DEFAULT '' COMMENT '订单ID',
    `user_id`     bigint         NOT NULL DEFAULT '0' COMMENT '用户ID',
    `sku_number`  varchar(30)    NOT NULL DEFAULT '' COMMENT 'sku编码',
    `total`       decimal(12, 2) NOT NULL DEFAULT '0.00' COMMENT '订单总金额',
    `create_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`   int            NOT NULL DEFAULT '0' COMMENT '创建人',
    `update_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`   int            NOT NULL DEFAULT '0' COMMENT '更新人',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_order_id` (`order_id`) USING BTREE,
    KEY `idx_user_id` (`user_id`) USING BTREE,
    KEY `idx_sku_number` (`sku_number`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='订单表(简化)';

CREATE TABLE `order_1`.`order_13`
(
    `id`          bigint         NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `order_id`    varchar(30)    NOT NULL DEFAULT '' COMMENT '订单ID',
    `user_id`     bigint         NOT NULL DEFAULT '0' COMMENT '用户ID',
    `sku_number`  varchar(30)    NOT NULL DEFAULT '' COMMENT 'sku编码',
    `total`       decimal(12, 2) NOT NULL DEFAULT '0.00' COMMENT '订单总金额',
    `create_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`   int            NOT NULL DEFAULT '0' COMMENT '创建人',
    `update_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`   int            NOT NULL DEFAULT '0' COMMENT '更新人',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_order_id` (`order_id`) USING BTREE,
    KEY `idx_user_id` (`user_id`) USING BTREE,
    KEY `idx_sku_number` (`sku_number`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='订单表(简化)';

CREATE TABLE `order_1`.`order_14`
(
    `id`          bigint         NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `order_id`    varchar(30)    NOT NULL DEFAULT '' COMMENT '订单ID',
    `user_id`     bigint         NOT NULL DEFAULT '0' COMMENT '用户ID',
    `sku_number`  varchar(30)    NOT NULL DEFAULT '' COMMENT 'sku编码',
    `total`       decimal(12, 2) NOT NULL DEFAULT '0.00' COMMENT '订单总金额',
    `create_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`   int            NOT NULL DEFAULT '0' COMMENT '创建人',
    `update_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`   int            NOT NULL DEFAULT '0' COMMENT '更新人',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_order_id` (`order_id`) USING BTREE,
    KEY `idx_user_id` (`user_id`) USING BTREE,
    KEY `idx_sku_number` (`sku_number`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='订单表(简化)';

CREATE TABLE `order_1`.`order_15`
(
    `id`          bigint         NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `order_id`    varchar(30)    NOT NULL DEFAULT '' COMMENT '订单ID',
    `user_id`     bigint         NOT NULL DEFAULT '0' COMMENT '用户ID',
    `sku_number`  varchar(30)    NOT NULL DEFAULT '' COMMENT 'sku编码',
    `total`       decimal(12, 2) NOT NULL DEFAULT '0.00' COMMENT '订单总金额',
    `create_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`   int            NOT NULL DEFAULT '0' COMMENT '创建人',
    `update_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`   int            NOT NULL DEFAULT '0' COMMENT '更新人',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_order_id` (`order_id`) USING BTREE,
    KEY `idx_user_id` (`user_id`) USING BTREE,
    KEY `idx_sku_number` (`sku_number`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='订单表(简化)';