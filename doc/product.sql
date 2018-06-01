-- ----------------------------
-- Table structure for `product_detail`
-- ----------------------------
DROP TABLE IF EXISTS `product_detail`;
CREATE TABLE `product_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `banner` text NOT NULL,
  `content` text NOT NULL,
  `product_info_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `product_info`
-- ----------------------------
DROP TABLE IF EXISTS `product_info`;
CREATE TABLE `product_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(32) NOT NULL COMMENT '商品名称',
  `amount_show` decimal(20,2) NOT NULL COMMENT '标价',
  `amount` decimal(20,2) NOT NULL COMMENT '实价',
  `img` varchar(512) NOT NULL COMMENT '商品主图',
  `brand_id` int(11) NOT NULL COMMENT '商品品牌 ID',
  `brand_name` varchar(64) NOT NULL COMMENT '商品品牌',
  `bucket_type` tinyint(4) NOT NULL COMMENT '桶类型，10：一次性桶，20：可回收桶',
  `delivery_fee` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '配送费',
  `creation_time` datetime NOT NULL COMMENT '商品创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '商品最后一次更新时间',
  `creator_id` int(11) NOT NULL COMMENT '商品创建人 ID',
  `creator_name` varchar(32) NOT NULL COMMENT '商品创建人名',
  `update_id` int(11) DEFAULT NULL COMMENT '商品更新人ID',
  `update_name` varchar(32) DEFAULT NULL COMMENT '商品更新人名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='商品信息表表';

-- 菜单SQL
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
    VALUES ('1', '商品信息表表', 'product/productinfo.html', NULL, '1', 'fa fa-file-code-o', '6');

-- 按钮父菜单ID
set @parentId = @@identity;

-- 菜单对应按钮SQL
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
    SELECT @parentId, '查看', null, 'productinfo:list,productinfo:info', '2', null, '6';
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
    SELECT @parentId, '新增', null, 'productinfo:save', '2', null, '6';
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
    SELECT @parentId, '修改', null, 'productinfo:update', '2', null, '6';
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
    SELECT @parentId, '删除', null, 'productinfo:delete', '2', null, '6';

------------------------------------------

CREATE TABLE `product_brand` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
  `name` varchar(64) NOT NULL COMMENT '品牌名',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品品牌信息表';

-- 菜单SQL
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
    VALUES ('1', '商品品牌信息表', 'generator/productbrand.html', NULL, '1', 'fa fa-file-code-o', '6');

-- 按钮父菜单ID
set @parentId = @@identity;

-- 菜单对应按钮SQL
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
    SELECT @parentId, '查看', null, 'productbrand:list,productbrand:info', '2', null, '6';
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
    SELECT @parentId, '新增', null, 'productbrand:save', '2', null, '6';
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
    SELECT @parentId, '修改', null, 'productbrand:update', '2', null, '6';
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
    SELECT @parentId, '删除', null, 'productbrand:delete', '2', null, '6';
