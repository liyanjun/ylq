/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : yunquanlai

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-06-06 11:35:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for comment_delivery
-- ----------------------------
DROP TABLE IF EXISTS `comment_delivery`;
CREATE TABLE `comment_delivery` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `delivery_distributor_id` bigint(20) NOT NULL COMMENT '配送员id',
  `comment` varchar(1024) NOT NULL COMMENT '评论内容',
  `level` int(11) DEFAULT NULL COMMENT '打分，1-5分',
  `creation_time` datetime DEFAULT NULL COMMENT '评论时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='配送员评价';

-- ----------------------------
-- Table structure for comment_product
-- ----------------------------
DROP TABLE IF EXISTS `comment_product`;
CREATE TABLE `comment_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `product_id` bigint(20) NOT NULL COMMENT '商品名称',
  `comment` varchar(1024) NOT NULL COMMENT '评论内容',
  `level` int(11) DEFAULT NULL COMMENT '打分，1-5分',
  `creation_time` datetime DEFAULT NULL COMMENT '评论时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='配送员评价';

-- ----------------------------
-- Table structure for delivery_client_token
-- ----------------------------
DROP TABLE IF EXISTS `delivery_client_token`;
CREATE TABLE `delivery_client_token` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `token` varchar(256) NOT NULL,
  `delivery_distributor_id` bigint(20) NOT NULL,
  `expire_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='配送端token持久化';


-- ----------------------------
-- Table structure for delivery_count_info
-- ----------------------------
DROP TABLE IF EXISTS `delivery_count_info`;
CREATE TABLE `delivery_count_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
  `delivery_distributor_id` bigint(20) NOT NULL COMMENT '配送人 ID',
  `order_count` int(11) NOT NULL DEFAULT '0' COMMENT '配送订单总数量',
  `order_value` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '配送订单总价值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='配送统计类信息';

-- ----------------------------
-- Table structure for delivery_distributor
-- ----------------------------
DROP TABLE IF EXISTS `delivery_distributor`;
CREATE TABLE `delivery_distributor` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL COMMENT '配送员姓名',
  `phone` varchar(32) NOT NULL COMMENT '配送员手机号',
  `password` varchar(64) NOT NULL COMMENT '配送员登录密码',
  `birthday` varchar(10) NOT NULL COMMENT '配送员生日',
  `client_id` varchar(64) DEFAULT NULL COMMENT '用于点对点登录时的推送，由APP在登录的时候一起上传',
  `order_count` int(11) DEFAULT '0' COMMENT '当前在配送订单数',
  `status` tinyint(4) NOT NULL DEFAULT '20' COMMENT '当前状态，10：可配送，20：不可配送',
  `platform` tinyint(4) DEFAULT '0' COMMENT '平台标识，10：安卓，20：苹果',
  `amount` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '配送员分润',
  `identifycation` varchar(32) DEFAULT NULL COMMENT '身份证号（备用）',
  `identifycation_url` varchar(512) DEFAULT NULL COMMENT '身份证照片地址',
  `health_url` varchar(512) DEFAULT NULL COMMENT '健康证地址',
  `delivery_endpoint_id` bigint(20) NOT NULL COMMENT '所属配送点ID',
  `disable` tinyint(2) DEFAULT '2' COMMENT '0:停用    1：启用    2：新创建（默认）',
  `delivery_endpoint_name` varchar(32) NOT NULL COMMENT '配送点名',
  `device_identification` tinyint(4) DEFAULT NULL COMMENT '设备标识，10：安卓，20：苹果',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8 COMMENT='配送员信息';
ALTER TABLE `delivery_distributor`
ADD INDEX `phone_unique` (`phone`) USING BTREE ;
-- ----------------------------
-- Table structure for delivery_distributor_financial_flow
-- ----------------------------
DROP TABLE IF EXISTS `delivery_distributor_financial_flow`;
CREATE TABLE `delivery_distributor_financial_flow` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `before_amount` decimal(20,2) NOT NULL COMMENT '流水前金额',
  `after_amount` decimal(20,2) NOT NULL COMMENT '流水后金额',
  `type` tinyint(4) NOT NULL COMMENT '流水类型，10：收益，20：提现申请，30：提现成功',
  `amount` decimal(10,0) NOT NULL COMMENT '流水金额',
  `delivery_distributor_id` bigint(20) NOT NULL COMMENT '配送员 ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='配送员收入信息流水';

-- ----------------------------
-- Table structure for delivery_endpoint
-- ----------------------------
DROP TABLE IF EXISTS `delivery_endpoint`;
CREATE TABLE `delivery_endpoint` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
  `name` varchar(32) NOT NULL COMMENT '配送点名',
  `location_x` decimal(20,10) NOT NULL COMMENT '配送点坐标x',
  `location_y` decimal(20,10) NOT NULL COMMENT '配送点坐标y',
  `remark` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8 COMMENT='配送点信息';

-- ----------------------------
-- Table structure for delivery_withdraw_apply
-- ----------------------------
DROP TABLE IF EXISTS `delivery_withdraw_apply`;
CREATE TABLE `delivery_withdraw_apply` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `phone` varchar(32) NOT NULL,
  `amount` decimal(20,2) NOT NULL COMMENT '提现时账户金额',
  `withdraw_amount` decimal(20,2) NOT NULL COMMENT '提现金额',
  `status` tinyint(4) NOT NULL COMMENT '状态，10：已处理，20：未处理',
  `creation_time` datetime NOT NULL COMMENT '创建时间',
  `auditor_id` bigint(20) NOT NULL COMMENT '审核人 ID',
  `audirot_name` varchar(32) NOT NULL COMMENT '审核人姓名',
  `delivery_distributor` bigint(20) NOT NULL COMMENT '配送人主键关联',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='配送员提现申请信息';

-- ----------------------------
-- Table structure for order_delivery_info
-- ----------------------------
DROP TABLE IF EXISTS `order_delivery_info`;
CREATE TABLE `order_delivery_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
  `name` varchar(32) NOT NULL COMMENT '收货人姓名',
  `address` varchar(256) NOT NULL COMMENT '订单地址信息（拼凑的用于在订单列表显示的）',
  `sex` int(11) NOT NULL COMMENT '收货人性别',
  `phone` varchar(32) NOT NULL COMMENT '用户手机号',
  `location_x` decimal(20,10) NOT NULL COMMENT '订单配送坐标x',
  `location_y` decimal(20,10) NOT NULL COMMENT '订单配送坐标y',
  `status` tinyint(4) NOT NULL COMMENT '配送单状态，10：未支付，20：未分配，30：分配中，40：配送中，50：配送结束',
  `remark` varchar(1024) DEFAULT NULL COMMENT '配送单备注',
  `creation_time` datetime NOT NULL COMMENT '配送单创建时间',
  `delivery_time` datetime DEFAULT NULL COMMENT '期望配送时间',
  `delivery_distributor_id` bigint(20) DEFAULT NULL COMMENT '关联配送员 ID',
  `order_info_id` bigint(20) NOT NULL COMMENT '关联订单 ID',
  `user_info_id` bigint(20) NOT NULL COMMENT '关联用户 ID',
  PRIMARY KEY (`id`),
  KEY `status_index` (`status`) USING BTREE,
  KEY `order_id_index` (`order_info_id`),
  KEY `distributor_id_index` (`delivery_distributor_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='订单配送信息表';

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
  `amount_total` decimal(20,2) NOT NULL COMMENT '订单总额',
  `amount` decimal(20,2) NOT NULL COMMENT '订单金额（真正付款金额）',
  `amount_benifit` decimal(20,2) DEFAULT NULL COMMENT '订单折扣优惠金额',
  `amount_activity` decimal(20,2) DEFAULT NULL COMMENT '订单活动优惠金额（即除了优惠标价外，使用的活动奖励）',
  `amount_delivery_fee` decimal(10,0) NOT NULL COMMENT '订单配送费',
  `status` tinyint(4) NOT NULL COMMENT '订单状态，10：新创建，20：已支付，待配送，30：配送中，40：已送达，50：已关闭，60：人工处理，70：人工派单',
  `type` tinyint(4) NOT NULL COMMENT '订单状态类型，10：正常，20：异常',
  `pay_type` tinyint(4) NOT NULL COMMENT '订单支付类型，10：现金，20：水票',
  `delivery_distributor_id` bigint(20) DEFAULT NULL COMMENT '关联配送员 ID',
  `delivery_distributor_name` varchar(64) DEFAULT NULL COMMENT '关联配送员名',
  `user_info_id` bigint(20) NOT NULL COMMENT '关联用户 ID',
  `username` varchar(128) NOT NULL COMMENT '用户名',
  `remark` varchar(1024) DEFAULT NULL COMMENT '订单备注',
  `exception` text DEFAULT NULL COMMENT '订单异常信息',
  `creation_time` datetime NOT NULL COMMENT '订单创建时间',
  `paid_time` datetime COMMENT '订单支付时间',
  `distribute_time` datetime COMMENT '订单分配时间',
  `delivery_end_time` datetime COMMENT '订单配送结束时间',
  `close_time` datetime COMMENT '订单关闭时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8 COMMENT='订单信息表';
ALTER TABLE `order_info`
AUTO_INCREMENT=10000;
-- ----------------------------
-- Table structure for order_operate_flow
-- ----------------------------
DROP TABLE IF EXISTS `order_operate_flow`;
CREATE TABLE `order_operate_flow` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
  `type` int(11) NOT NULL COMMENT '处理类型，10：手工选定配送员，20：取消订单',
  `before_status` int(11) NOT NULL COMMENT '手工操作前状态',
  `after_status` int(11) NOT NULL COMMENT '手工操作后状态',
  `remark` varchar(512) DEFAULT NULL COMMENT '操作备注信息',
  `operator_time` datetime NOT NULL COMMENT '操作时间',
  `operator_id` bigint(20) NOT NULL COMMENT '操作人 ID',
  `operator_name` varchar(32) NOT NULL COMMENT '操作人名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单手工处理流水记录表';

-- ----------------------------
-- Table structure for order_product_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_product_detail`;
CREATE TABLE `order_product_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
  `product_info_id` bigint(20) NOT NULL COMMENT '对应商品 ID',
  `product_name` varchar(128) NOT NULL COMMENT '商品名称',
  `count` int(11) NOT NULL COMMENT '商品数量',
  `order_info_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='订单商品信息表';

-- ----------------------------
-- Table structure for order_manual_handling
-- ----------------------------
DROP TABLE IF EXISTS `order_manual_handling`;
CREATE TABLE `order_manual_handling` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `order_id` bigint(20) NOT NULL COMMENT '人工处理订单ID',
  `operator_id` bigint(20) NOT NULL COMMENT '处理人id',
  `operator_name` varchar(32) NOT NULL COMMENT '操作人姓名',
  `operator_time` datetime NOT NULL,
  `processing` varchar(1024) DEFAULT NULL COMMENT '处理过程',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单人工处理记录表';

-- ----------------------------
-- Table structure for product_brand
-- ----------------------------
DROP TABLE IF EXISTS `product_brand`;
CREATE TABLE `product_brand` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
  `name` varchar(64) NOT NULL COMMENT '商品名',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='商品品牌信息表';

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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_info
-- ----------------------------
DROP TABLE IF EXISTS `product_info`;
CREATE TABLE `product_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(128) NOT NULL COMMENT '商品名称',
  `amount_show` decimal(20,2) DEFAULT NULL COMMENT '售价',
  `amount` decimal(20,2) NOT NULL COMMENT '优惠价',
  `img` varchar(512) NOT NULL COMMENT '商品主图',
  `brand_id` bigint(20) NOT NULL COMMENT '商品品牌 ID',
  `brand_name` varchar(64) NOT NULL COMMENT '商品品牌',
  `bucket_type` tinyint(4) NOT NULL COMMENT '规格，10：一次性桶装水，20：循环桶装水，30：瓶装水',
  `delivery_fee` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '配送费',
  `count` int(11) DEFAULT '0' COMMENT '销售数量',
  `sort` int(11) DEFAULT '0' COMMENT '排序',
  `is_quick` tinyint(4) NOT NULL COMMENT '是否一键送水,是：10，否：20',
  `creation_time` datetime NOT NULL COMMENT '商品创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '商品最后一次更新时间',
  `creator_id` bigint(20) NOT NULL COMMENT '商品创建人 ID',
  `creator_name` varchar(32) NOT NULL COMMENT '商品创建人名',
  `update_id` bigint(20) DEFAULT NULL COMMENT '商品更新人ID',
  `update_name` varchar(32) DEFAULT NULL COMMENT '商品更新人名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8 COMMENT='商品信息表表';
ALTER TABLE `product_info`
AUTO_INCREMENT=10000;

DROP TABLE IF EXISTS `product_ticket`;
CREATE TABLE `product_ticket` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(128) NOT NULL COMMENT '水票标题',
  `cout` int(11) NOT NULL COMMENT '包含产品数量（如买5送二）这里就应该是7',
  `amout` decimal(20,2) NOT NULL COMMENT '水票价格',
  `product_info_id` bigint(20) NOT NULL COMMENT '关联产品 ID',
  `creation_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品水票信息表';

-- ----------------------------
-- Table structure for product_stock
-- ----------------------------
DROP TABLE IF EXISTS `product_stock`;
CREATE TABLE `product_stock` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `product_info_id` bigint(20) NOT NULL COMMENT '关联商品ID',
  `product_name` varchar(128) DEFAULT NULL COMMENT '商品名',
  `delivery_endpoint_id` bigint(20) NOT NULL COMMENT '关联配送点 ID',
  `delivery_name` varchar(64) DEFAULT NULL COMMENT '配送点名',
  `count` int(11) NOT NULL COMMENT '库存数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='商品库存信息表';

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
  `operator` varchar(64) NOT NULL COMMENT '操作人名称',
  `operator_id` bigint(20) NOT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 COMMENT='文件上传';

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
-- Table structure for user_address
-- ----------------------------
DROP TABLE IF EXISTS `user_address`;
CREATE TABLE `user_address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
  `location_x` decimal(20,10) NOT NULL COMMENT '地址坐标x值',
  `location_y` decimal(20,10) NOT NULL COMMENT '地址坐标y值',
  `address` varchar(256) NOT NULL COMMENT '地址描述',
  `name` varchar(32) NOT NULL COMMENT '收货人姓名',
  `phone` varchar(32) NOT NULL COMMENT '收货人电话',
  `sex` tinyint(4) NOT NULL COMMENT '性别，10：男，20：女',
  `user_info_id` bigint(20) NOT NULL COMMENT '对应用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='客户地址信息表';

-- ----------------------------
-- Table structure for user_client_token
-- ----------------------------
DROP TABLE IF EXISTS `user_client_token`;
CREATE TABLE `user_client_token` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `token` varchar(128) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `expire_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for user_deposit_flow
-- ----------------------------
DROP TABLE IF EXISTS `user_empty_bucket_flow`;
CREATE TABLE `user_empty_bucket_flow` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` tinyint(4) NOT NULL COMMENT '流水类型，10：归还空桶，20：获取空桶',
  `before_empty_bucket` varchar(512) NOT NULL COMMENT '流水前空桶数',
  `after_empty_bucket` varchar(512) NOT NULL COMMENT '流水后空桶数',
  `empty_bucket_number` varchar(512) NOT NULL COMMENT '操作空桶数',
  `user_info_id` bigint(20) NOT NULL COMMENT '关联用户 ID',
  `operator_id` bigint(20) NOT NULL COMMENT '操作关联 ID（如果为归还关联配送员用户 ID，如果是获取关联订单 ID）',
  `creation_time` datetime NOT NULL COMMENT '流水时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户空桶流水信息表';

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) NOT NULL COMMENT '用户名(从小程序过来是就是微信昵称)',
  `phone` varchar(32) DEFAULT NULL COMMENT '用户绑定手机号',
  `uid` varchar(64) NOT NULL COMMENT '用户微信 ID',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '用户状态：0：启用，1：禁用',
  `deposit_amount` decimal(20,2) DEFAULT '0.00' COMMENT '总押金金额',
  `enable_deposit_amount` decimal(20,2) DEFAULT '0.00' COMMENT '可用押金金额',
  `disable_deposit_amount` decimal(20,2) DEFAULT '0.00' COMMENT '不可用押金金额',
  `empty_bucket_number` int(11) DEFAULT '0' COMMENT '持有空桶数',
  `creation_time` datetime NOT NULL COMMENT '用户注册时间',
  `recommenderID` bigint(20) DEFAULT NULL COMMENT '推荐人ID',
  `recommenderName` varchar(64) DEFAULT NULL COMMENT '推荐人姓名',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uid_unique` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='客户信息表';

-- ----------------------------
-- Table structure for user_withdraw_deposit
-- ----------------------------
DROP TABLE IF EXISTS `user_withdraw_deposit`;
CREATE TABLE `user_withdraw_deposit` (
  `id` bigint(20) NOT NULL COMMENT '主键 ID',
  `user_info_id` bigint(20) NOT NULL COMMENT '对应用户 ID',
  `is_handle` tinyint(1) NOT NULL COMMENT '是否处理，10：未处理，20：已处理',
  `creation_time` datetime DEFAULT NULL COMMENT '创建时间',
  `handle_time` datetime NOT NULL COMMENT '处理时间',
  `handler_id` bigint(20) DEFAULT NULL COMMENT '处理人ID',
  `handler_name` varchar(32) DEFAULT NULL COMMENT '处理人姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户押金提现申请表';

DROP TABLE IF EXISTS `user_product_ticket_flow`;
CREATE TABLE `user_product_ticket_flow` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
  `cout` int(11) NOT NULL COMMENT '使用数量',
  `order_info_id` bigint(20) NOT NULL COMMENT '关联产品 ID',
  `creation_time` datetime NOT NULL COMMENT '购买时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户水票消费流水';

DROP TABLE IF EXISTS `user_product_ticket`;
CREATE TABLE `user_product_ticket` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_ticket_name` varchar(128) NOT NULL COMMENT '水票名称',
  `cout` int(11) NOT NULL COMMENT '剩余数量',
  `amount` decimal(20,2) NOT NULL COMMENT '购买价格',
  `status` tinyint(4) NOT NULL COMMENT '用户水票状态，10：新下单，20：已支付，30：已关闭',
  `product_info_id` bigint(20) NOT NULL COMMENT '关联产品 ID',
  `creation_time` datetime NOT NULL COMMENT '购买时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户水票信息表';