-- 创建hmily数据库
CREATE DATABASE hmily CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;
-- 创建账号数据库A
CREATE DATABASE hmily_account_a CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;
-- 创建账号数据库B
CREATE DATABASE hmily_account_b CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;
-- 创建冻结账号数据库
CREATE DATABASE hmily_account_freeze CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

-- 在账号数据库A创建账号表
CREATE TABLE `hmily_account_a`.`account`
(
    `id`      BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键 id',
    `user_id` VARCHAR(20)         NOT NULL DEFAULT '' COMMENT '用户ID',
    `cny`     decimal(10, 2)      NOT NULL DEFAULT '0.00' COMMENT '人民币',
    `usd`     decimal(10, 2)      NOT NULL DEFAULT '0.00' COMMENT '美元',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT = '账号表';

-- 在账号数据库B创建账号表
CREATE TABLE `hmily_account_b`.`account`
(
    `id`      BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键 id',
    `user_id` VARCHAR(20)         NOT NULL DEFAULT '' COMMENT '用户ID',
    `cny`     decimal(10, 2)      NOT NULL DEFAULT '0.00' COMMENT '人民币',
    `usd`     decimal(10, 2)      NOT NULL DEFAULT '0.00' COMMENT '美元',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT = '账号表';

-- 在冻结账号数据库创建冻结账号表
CREATE TABLE `hmily_account_freeze`.`account_freeze`
(
    `id`      BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键 id',
    `user_id` VARCHAR(20)         NOT NULL DEFAULT '' COMMENT '用户ID',
    `cny`     decimal(10, 2)      NOT NULL DEFAULT '0.00' COMMENT '人民币',
    `usd`     decimal(10, 2)      NOT NULL DEFAULT '0.00' COMMENT '美元',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT = '账号表';

-- 插入初始数据
INSERT INTO `hmily_account_a`.`account`(id, user_id, cny, usd)
VALUES (1, 'UA00001', 0, 10000);
INSERT INTO `hmily_account_b`.`account`(id, user_id, cny, usd)
VALUES (1, 'UB00001', 10000, 0);