/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : renren-security-boot

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-06-03 14:50:26
*/

SET FOREIGN_KEY_CHECKS=0;

CREATE TABLE `user_info` (
`id` int NOT NULL AUTO_INCREMENT,
`username` varchar(32) NOT NULL COMMENT '用户名(从小程序过来是就是微信昵称)',
`phone` varchar(32) NULL COMMENT '用户绑定手机号',
`uid` varchar(64) NOT NULL COMMENT '用户微信 ID',
`status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '用户状态：0：启用，1：禁用',
`deposit_amount` decimal(20,2) NULL DEFAULT 0.00 COMMENT '总押金金额',
`enable_deposit_amount` decimal(20,2) NULL DEFAULT 0.00 COMMENT '可用押金金额',
`disable_deposit_amout` decimal(20,2) NULL DEFAULT 0.00 COMMENT '不可用押金金额',
`empty_bucket_number` int(11) NULL DEFAULT 0 COMMENT '持有空桶数',
`creation_time` datetime NOT NULL COMMENT '用户注册时间',
PRIMARY KEY (`id`)
)
COMMENT='客户信息表'
;

CREATE TABLE `user_address` (
`id` int NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
`location_x` decimal(20,2) NOT NULL COMMENT '地址坐标x值',
`location_y` decimal(20,2) NOT NULL COMMENT '地址坐标y值',
`address` varchar(256) NOT NULL COMMENT '地址描述',
`name` varchar(32) NOT NULL COMMENT '收货人姓名',
`phone` varchar(32) NOT NULL COMMENT '收货人电话',
`user_info_id` int NOT NULL COMMENT '对应用户ID',
PRIMARY KEY (`id`)
)
COMMENT='客户地址信息表'
;

CREATE TABLE `user_deposit` (
`id` int NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
`user_info_id` int NOT NULL COMMENT '对应用户 ID',
`deposit_amount` decimal(20,2) NOT NULL DEFAULT 0 COMMENT '押金总额',
`enable_deposit_amount` decimal(20,2) NOT NULL DEFAULT 0 COMMENT '有效的押金',
`disable_deposit_amount` decimal(20,2) NOT NULL DEFAULT 0 COMMENT '无效的押金（空桶没退回）',
`empty_bucket_number` int NOT NULL DEFAULT 0 COMMENT '用户持有空桶数',
PRIMARY KEY (`id`)
)
COMMENT='客户押金信息表'
;

CREATE TABLE `user_deposit_flow` (
`id` int NOT NULL AUTO_INCREMENT,
`type` tinyint(4) NOT NULL COMMENT '流水类型，101：押金充值，2：押金提现，3：归还空桶，4：订水（等价于获取空桶）',
`before_deposit` varchar(512) NOT NULL COMMENT '流水前押金信息',
`after_deposit` varchar(512) NOT NULL COMMENT '流水号押金信息',
`user_info_id` int NOT NULL COMMENT '关联用户 ID',
`creation_time` datetime NOT NULL COMMENT '流水时间',
PRIMARY KEY (`id`)
)
COMMENT='客户押金流水信息表'
;

CREATE TABLE `user_withdraw_deposit` (
`id` int NOT NULL COMMENT '主键 ID',
`user_info_id` int NOT NULL COMMENT '对应用户 ID',
`is_handle` tinyint(1) NOT NULL COMMENT '是否处理，0：未处理，1：已处理',
`creation_time` datetime NULL COMMENT '创建时间',
`handle_time` datetime NOT NULL COMMENT '处理时间',
PRIMARY KEY (`id`)
)
COMMENT='客户押金提现申请表'
;

-- ----------------------------
-- Table structure for delivery_count_info
-- ----------------------------
DROP TABLE IF EXISTS `delivery_count_info`;
CREATE TABLE `delivery_count_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
  `delivery_distributor_id` int(11) NOT NULL COMMENT '配送人 ID',
  `order_count` int(11) NOT NULL DEFAULT '0' COMMENT '配送订单总数量',
  `order_value` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '配送订单总价值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='配送统计类信息';

-- ----------------------------
-- Table structure for delivery_distributor
-- ----------------------------
DROP TABLE IF EXISTS `delivery_distributor`;
CREATE TABLE `delivery_distributor` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL COMMENT '配送员姓名',
  `phone` varchar(32) NOT NULL COMMENT '配送员手机号',
  `password` varchar(64) NOT NULL COMMENT '配送员登录密码',
  `birthday` datetime NOT NULL COMMENT '配送员生日',
  `clientId` varchar(64) DEFAULT NULL COMMENT '用于点对点登录时的推送，由APP在登录的时候一起上传',
  `status` tinyint(4) NOT NULL DEFAULT '20' COMMENT '当前状态，10：可配送，20：不可配送',
  `identifycation` varchar(32) DEFAULT NULL COMMENT '身份证号（备用）',
  `identifycation_url` varchar(512) DEFAULT NULL COMMENT '身份证照片地址',
  `health_url` varchar(512) DEFAULT NULL COMMENT '健康证地址',
  `delivery_endpoint_id` bigint(11) NOT NULL COMMENT '所属配送点',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='配送员信息';

-- ----------------------------
-- Table structure for delivery_distributor_financial_flow
-- ----------------------------
DROP TABLE IF EXISTS `delivery_distributor_financial_flow`;
CREATE TABLE `delivery_distributor_financial_flow` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `before_amount` decimal(20,2) NOT NULL COMMENT '流水前金额',
  `before_disable_amount` decimal(20,2) NOT NULL COMMENT '流水前冻结金额',
  `before_enable_amount` decimal(20,2) NOT NULL COMMENT '流水前可用金额',
  `after_enable_amount` decimal(20,2) NOT NULL COMMENT '流水后可用金额',
  `after_disable_amount` decimal(20,2) NOT NULL COMMENT '流水后冻结金额',
  `after_amount` decimal(20,2) NOT NULL COMMENT '流水后金额',
  `type` tinyint(4) NOT NULL COMMENT '流水类型，10：收益，20：提现申请，30：提现成功',
  `amount` decimal(10,0) NOT NULL COMMENT '流水金额',
  `delivery_distributor_id` int(11) NOT NULL COMMENT '配送员 ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='配送员收入信息流水';

-- ----------------------------
-- Table structure for delivery_distributor_financial_info
-- ----------------------------
DROP TABLE IF EXISTS `delivery_distributor_financial_info`;
CREATE TABLE `delivery_distributor_financial_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `amount` decimal(20,2) NOT NULL COMMENT '当前金额',
  `disable_amount` decimal(20,2) NOT NULL COMMENT '当前冻结金额',
  `enable_amount` decimal(20,2) NOT NULL COMMENT '当前可用金额',
  `delivery_distributor_id` int(11) NOT NULL COMMENT '配送员 ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='配送员收入信息';

-- ----------------------------
-- Table structure for delivery_endpoint
-- ----------------------------
DROP TABLE IF EXISTS `delivery_endpoint`;
CREATE TABLE `delivery_endpoint` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
  `name` varchar(32) NOT NULL COMMENT '配送点名',
  `location_x` decimal(20,10) NOT NULL COMMENT '配送点坐标x',
  `location_y` decimal(20,10) NOT NULL COMMENT '配送点坐标y',
  `remark` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='配送点信息';

-- ----------------------------
-- Table structure for delivery_withdraw_apply
-- ----------------------------
DROP TABLE IF EXISTS `delivery_withdraw_apply`;
CREATE TABLE `delivery_withdraw_apply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `phone` varchar(32) NOT NULL,
  `amount` decimal(20,2) NOT NULL COMMENT '提现时账户金额',
  `withdraw_amount` decimal(20,2) NOT NULL COMMENT '提现金额',
  `status` tinyint(4) NOT NULL COMMENT '状态，10：已处理，20：未处理',
  `creation_time` datetime NOT NULL COMMENT '创建时间',
  `auditor_id` int(11) NOT NULL COMMENT '审核人 ID',
  `audirot_name` varchar(32) NOT NULL COMMENT '审核人姓名',
  `delivery_distributor` int(11) NOT NULL COMMENT '配送人主键关联',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='配送员提现申请信息';

-- ----------------------------
-- Table structure for product_brand
-- ----------------------------
DROP TABLE IF EXISTS `product_brand`;
CREATE TABLE `product_brand` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
  `name` varchar(64) NOT NULL COMMENT '商品名',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='商品品牌信息表';

-- ----------------------------
-- Table structure for product_detail
-- ----------------------------
DROP TABLE IF EXISTS `product_detail`;
CREATE TABLE `product_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `banner` text NOT NULL,
  `content` text NOT NULL,
  `product_info_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_info
-- ----------------------------
DROP TABLE IF EXISTS `product_info`;
CREATE TABLE `product_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(128) NOT NULL COMMENT '商品名称',
  `amount_show` decimal(20,2) NOT NULL COMMENT '标价',
  `amount` decimal(20,2) NOT NULL COMMENT '实价',
  `img` varchar(512) NOT NULL COMMENT '商品主图',
  `brand_id` int(11) NOT NULL COMMENT '商品品牌 ID',
  `brand_name` varchar(64) NOT NULL COMMENT '商品品牌',
  `bucket_type` tinyint(4) NOT NULL COMMENT '桶类型，10：一次性桶，20：可回收桶',
  `delivery_fee` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '配送费',
  `count` int(11) DEFAULT '0' COMMENT '销售数量',
  `is_quick` tinyint(4) NOT NULL,
  `creation_time` datetime NOT NULL COMMENT '商品创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '商品最后一次更新时间',
  `creator_id` int(11) NOT NULL COMMENT '商品创建人 ID',
  `creator_name` varchar(32) NOT NULL COMMENT '商品创建人名',
  `update_id` int(11) DEFAULT NULL COMMENT '商品更新人ID',
  `update_name` varchar(32) DEFAULT NULL COMMENT '商品更新人名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=utf8 COMMENT='商品信息表表';

-- ----------------------------
-- Table structure for product_stock
-- ----------------------------
DROP TABLE IF EXISTS `product_stock`;
CREATE TABLE `product_stock` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `product_info_id` bigint(11) NOT NULL COMMENT '关联商品ID',
  `product_name` varchar(128) DEFAULT NULL COMMENT '商品名',
  `delivery_endpoint_id` bigint(11) NOT NULL COMMENT '关联配送点 ID',
  `delivery_name` varchar(64) DEFAULT NULL COMMENT '配送点名',
  `count` int(11) NOT NULL COMMENT '库存数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='商品库存信息表';

-- ----------------------------
-- Table structure for product_stock_flow
-- ----------------------------
DROP TABLE IF EXISTS `product_stock_flow`;
CREATE TABLE `product_stock_flow` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `product_stock_id` bigint(20) NOT NULL COMMENT '关联商品ID',
  `count` int(11) NOT NULL COMMENT '库存数',
  `type` tinyint(4) NOT NULL COMMENT '库存变动类型，0：添加，1：减少',
  `before_count` int(11) NOT NULL COMMENT '改变前值',
  `after_count` int(11) NOT NULL COMMENT '改变后值',
  `creation_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '库存变动时间',
  `opreator` varchar(64) NOT NULL COMMENT '操作人名称',
  `opreator_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='商品库存流水信息表';

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `key` varchar(50) DEFAULT NULL COMMENT 'key',
  `value` varchar(2000) DEFAULT NULL COMMENT 'value',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态   0：隐藏   1：显示',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `key` (`key`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='系统配置信息表';

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) DEFAULT NULL COMMENT '用户操作',
  `method` varchar(200) DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) DEFAULT NULL COMMENT '请求参数',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 COMMENT='系统日志';

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8 COMMENT='菜单管理';

-- ----------------------------
-- Table structure for sys_oss
-- ----------------------------
DROP TABLE IF EXISTS `sys_oss`;
CREATE TABLE `sys_oss` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(200) DEFAULT NULL COMMENT 'URL地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COMMENT='文件上传';

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='系统用户';

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

-- ----------------------------
-- Table structure for tb_token
-- ----------------------------
DROP TABLE IF EXISTS `tb_token`;
CREATE TABLE `tb_token` (
  `user_id` bigint(20) NOT NULL,
  `token` varchar(100) NOT NULL COMMENT 'token',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `token` (`token`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户Token';

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `mobile` varchar(20) NOT NULL COMMENT '手机号',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户';

-- 定时任务相关

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(120) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`),
  KEY `IDX_QRTZ_FT_TRIG_INST_NAME` (`SCHED_NAME`,`INSTANCE_NAME`) USING BTREE,
  KEY `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY` (`SCHED_NAME`,`INSTANCE_NAME`,`REQUESTS_RECOVERY`) USING BTREE,
  KEY `IDX_QRTZ_FT_J_G` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_FT_JG` (`SCHED_NAME`,`JOB_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_FT_T_G` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_FT_TG` (`SCHED_NAME`,`TRIGGER_GROUP`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_J_REQ_RECOVERY` (`SCHED_NAME`,`REQUESTS_RECOVERY`) USING BTREE,
  KEY `IDX_QRTZ_J_GRP` (`SCHED_NAME`,`JOB_GROUP`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_J` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_T_JG` (`SCHED_NAME`,`JOB_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_T_C` (`SCHED_NAME`,`CALENDAR_NAME`) USING BTREE,
  KEY `IDX_QRTZ_T_G` (`SCHED_NAME`,`TRIGGER_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_T_STATE` (`SCHED_NAME`,`TRIGGER_STATE`) USING BTREE,
  KEY `IDX_QRTZ_T_N_STATE` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`) USING BTREE,
  KEY `IDX_QRTZ_T_N_G_STATE` (`SCHED_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`) USING BTREE,
  KEY `IDX_QRTZ_T_NEXT_FIRE_TIME` (`SCHED_NAME`,`NEXT_FIRE_TIME`) USING BTREE,
  KEY `IDX_QRTZ_T_NFT_ST` (`SCHED_NAME`,`TRIGGER_STATE`,`NEXT_FIRE_TIME`) USING BTREE,
  KEY `IDX_QRTZ_T_NFT_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`) USING BTREE,
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_STATE`) USING BTREE,
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_GROUP`,`TRIGGER_STATE`) USING BTREE,
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for schedule_job
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job`;
CREATE TABLE `schedule_job` (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务id',
  `bean_name` varchar(200) DEFAULT NULL COMMENT 'spring bean名称',
  `method_name` varchar(100) DEFAULT NULL COMMENT '方法名',
  `params` varchar(2000) DEFAULT NULL COMMENT '参数',
  `cron_expression` varchar(100) DEFAULT NULL COMMENT 'cron表达式',
  `status` tinyint(4) DEFAULT NULL COMMENT '任务状态',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='定时任务';

-- ----------------------------
-- Table structure for schedule_job_log
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job_log`;
CREATE TABLE `schedule_job_log` (
  `log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志id',
  `job_id` bigint(20) NOT NULL COMMENT '任务id',
  `bean_name` varchar(200) DEFAULT NULL COMMENT 'spring bean名称',
  `method_name` varchar(100) DEFAULT NULL COMMENT '方法名',
  `params` varchar(2000) DEFAULT NULL COMMENT '参数',
  `status` tinyint(4) NOT NULL COMMENT '任务状态    0：成功    1：失败',
  `error` varchar(2000) DEFAULT NULL COMMENT '失败信息',
  `times` int(11) NOT NULL COMMENT '耗时(单位：毫秒)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`log_id`),
  KEY `job_id` (`job_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COMMENT='定时任务日志';

-- ----------------------------
-- Table structure for user_address
-- ----------------------------
DROP TABLE IF EXISTS `user_address`;
CREATE TABLE `user_address` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
  `location_x` decimal(20,2) NOT NULL COMMENT '地址坐标x值',
  `location_y` decimal(20,2) NOT NULL COMMENT '地址坐标y值',
  `address` varchar(256) NOT NULL COMMENT '地址描述',
  `name` varchar(32) NOT NULL COMMENT '收货人姓名',
  `phone` varchar(32) NOT NULL COMMENT '收货人电话',
  `user_info_id` bigint(11) NOT NULL COMMENT '对应用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户地址信息表';

-- ----------------------------
-- Table structure for user_client_token
-- ----------------------------
DROP TABLE IF EXISTS `user_client_token`;
CREATE TABLE `user_client_token` (
  `id` bigint(20) NOT NULL,
  `token` varchar(256) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `expire_time` datetime NOT NULL,
  `creation_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_deposit
-- ----------------------------
DROP TABLE IF EXISTS `user_deposit`;
CREATE TABLE `user_deposit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
  `user_info_id` bigint(20) NOT NULL COMMENT '对应用户 ID',
  `deposit_amount` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '押金总额',
  `enable_deposit_amount` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '有效的押金',
  `disable_deposit_amount` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '无效的押金（空桶没退回）',
  `empty_bucket_number` int(11) NOT NULL DEFAULT '0' COMMENT '用户持有空桶数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户押金信息表';

-- ----------------------------
-- Table structure for user_deposit_flow
-- ----------------------------
DROP TABLE IF EXISTS `user_deposit_flow`;
CREATE TABLE `user_deposit_flow` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` tinyint(4) NOT NULL COMMENT '流水类型，101：押金充值，2：押金提现，3：归还空桶，4：订水（等价于获取空桶）',
  `before_deposit` varchar(512) NOT NULL COMMENT '流水前押金信息',
  `after_deposit` varchar(512) NOT NULL COMMENT '流水号押金信息',
  `user_info_id` bigint(20) NOT NULL COMMENT '关联用户 ID',
  `creation_time` datetime NOT NULL COMMENT '流水时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户押金流水信息表';

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) NOT NULL COMMENT '用户名(从小程序过来是就是微信昵称)',
  `phone` varchar(32) DEFAULT NULL COMMENT '用户绑定手机号',
  `uid` varchar(64) NOT NULL COMMENT '用户微信 ID',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '用户状态：0：启用，1：禁用',
  `deposit_amount` decimal(20,2) DEFAULT '0.00' COMMENT '总押金金额',
  `enable_deposit_amount` decimal(20,2) DEFAULT '0.00' COMMENT '可用押金金额',
  `disable_deposit_amout` decimal(20,2) DEFAULT '0.00' COMMENT '不可用押金金额',
  `empty_bucket_number` int(11) DEFAULT '0' COMMENT '持有空桶数',
  `creation_time` datetime NOT NULL COMMENT '用户注册时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户信息表';

-- ----------------------------
-- Table structure for user_withdraw_deposit
-- ----------------------------
DROP TABLE IF EXISTS `user_withdraw_deposit`;
CREATE TABLE `user_withdraw_deposit` (
  `id` bigint(20) NOT NULL COMMENT '主键 ID',
  `user_info_id` bigint(20) NOT NULL COMMENT '对应用户 ID',
  `is_handle` tinyint(1) NOT NULL COMMENT '是否处理，0：未处理，1：已处理',
  `creation_time` datetime DEFAULT NULL COMMENT '创建时间',
  `handle_time` datetime NOT NULL COMMENT '处理时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户押金提现申请表';
