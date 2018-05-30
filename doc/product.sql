CREATE TABLE `product_info` (
`id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
`name` varchar(32) NOT NULL COMMENT '商品名称',
`amount_show` decimal(20,2) NOT NULL COMMENT '标价',
`amount` decimal(20,2) NOT NULL COMMENT '实价',
`brand_id` int NOT NULL COMMENT '商品品牌 ID',
`brand_name` varchar(64) NOT NULL COMMENT '商品品牌',
`bucket_type` tinyint(4) NULL COMMENT '桶类型，10：一次性桶，20：可回收桶',
`delivery_fee` decimal(20,2) NULL COMMENT '配送费',
`creation_time` datetime NULL COMMENT '商品创建时间',
`update_time` datetime NULL COMMENT '商品最后一次更新时间',
`creator_id` int NULL COMMENT '商品创建人 ID',
`creator_name` varchar(32) NULL COMMENT '商品创建人名',
`updator_id` int NULL COMMENT '商品更新人ID',
`updator_name` varchar(32) NULL COMMENT '商品更新人名',
PRIMARY KEY (`id`)
)
COMMENT='商品信息表表'
;

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