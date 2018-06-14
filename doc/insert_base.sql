/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : renren-security-boot

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-06-03 14:52:48
*/

INSERT INTO `sys_config` VALUES ('1', 'CLOUD_STORAGE_CONFIG_KEY', '{\"aliyunAccessKeyId\":\"\",\"aliyunAccessKeySecret\":\"\",\"aliyunBucketName\":\"\",\"aliyunDomain\":\"\",\"aliyunEndPoint\":\"\",\"aliyunPrefix\":\"\",\"qcloudBucketName\":\"\",\"qcloudDomain\":\"\",\"qcloudPrefix\":\"\",\"qcloudSecretId\":\"\",\"qcloudSecretKey\":\"\",\"qiniuAccessKey\":\"zYvETuLHzwarNl9ZQQHdJ6ZQDIL0EpYRA0x3jVxb\",\"qiniuBucketName\":\"yunquanlai\",\"qiniuDomain\":\"http://pa23ubi36.bkt.clouddn.com\",\"qiniuPrefix\":\"upload\",\"qiniuSecretKey\":\"yDOw7KMRox8wGZsAyudGRQ8OiFMnJpDAZ_wXWnj7\",\"type\":1}', '0', '云存储配置信息');


-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', '系统管理', null, null, '0', 'fa fa-cog', '0');
INSERT INTO `sys_menu` VALUES ('2', '1', '管理员列表', 'sys/user.html', null, '1', 'fa fa-user', '1');
INSERT INTO `sys_menu` VALUES ('3', '1', '角色管理', 'sys/role.html', null, '1', 'fa fa-user-secret', '2');
INSERT INTO `sys_menu` VALUES ('4', '1', '菜单管理', 'sys/menu.html', null, '1', 'fa fa-th-list', '3');
INSERT INTO `sys_menu` VALUES ('5', '1', 'SQL监控', 'druid/sql.html', null, '1', 'fa fa-bug', '4');
INSERT INTO `sys_menu` VALUES ('6', '1', '定时任务', 'sys/schedule.html', null, '1', 'fa fa-tasks', '5');
INSERT INTO `sys_menu` VALUES ('7', '6', '查看', null, 'sys:schedule:list,sys:schedule:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('8', '6', '新增', null, 'sys:schedule:save', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('9', '6', '修改', null, 'sys:schedule:update', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('10', '6', '删除', null, 'sys:schedule:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('11', '6', '暂停', null, 'sys:schedule:pause', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('12', '6', '恢复', null, 'sys:schedule:resume', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('13', '6', '立即执行', null, 'sys:schedule:run', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('14', '6', '日志列表', null, 'sys:schedule:log', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('15', '2', '查看', null, 'sys:user:list,sys:user:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('16', '2', '新增', null, 'sys:user:save,sys:role:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('17', '2', '修改', null, 'sys:user:update,sys:role:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('18', '2', '删除', null, 'sys:user:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('19', '3', '查看', null, 'sys:role:list,sys:role:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('20', '3', '新增', null, 'sys:role:save,sys:menu:perms', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('21', '3', '修改', null, 'sys:role:update,sys:menu:perms', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('22', '3', '删除', null, 'sys:role:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('23', '4', '查看', null, 'sys:menu:list,sys:menu:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('24', '4', '新增', null, 'sys:menu:save,sys:menu:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('25', '4', '修改', null, 'sys:menu:update,sys:menu:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('26', '4', '删除', null, 'sys:menu:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('27', '1', '参数管理', 'sys/config.html', 'sys:config:list,sys:config:info,sys:config:save,sys:config:update,sys:config:delete', '1', 'fa fa-sun-o', '6');
INSERT INTO `sys_menu` VALUES ('28', '1', '代码生成器', 'sys/generator.html', 'sys:generator:list,sys:generator:code', '1', 'fa fa-rocket', '8');
INSERT INTO `sys_menu` VALUES ('29', '1', '系统日志', 'sys/log.html', 'sys:log:list', '1', 'fa fa-file-text-o', '7');
INSERT INTO `sys_menu` VALUES ('30', '1', '文件上传', 'sys/oss.html', 'sys:oss:all', '1', 'fa fa-file-image-o', '6');
INSERT INTO `sys_menu` VALUES ('34', '59', '商品信息', 'product/productinfo.html', 'productinfo:list', '1', 'fa fa-cube', '6');
INSERT INTO `sys_menu` VALUES ('35', '34', '查看', null, 'productinfo:list,productinfo:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('36', '34', '新增', null, 'productinfo:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('37', '34', '修改', null, 'productinfo:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('38', '34', '删除', null, 'productinfo:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('39', '59', '商品品牌', 'product/productbrand.html', 'productbrand:list', '1', 'fa fa-bandcamp', '6');
INSERT INTO `sys_menu` VALUES ('40', '39', '查看', null, 'productbrand:list,productbrand:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('41', '39', '新增', null, 'productbrand:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('42', '39', '修改', null, 'productbrand:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('43', '39', '删除', null, 'productbrand:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('44', '60', '配送员信息', 'delivery/deliverydistributor.html', 'deliverydistributor:list', '1', 'fa fa-user', '6');
INSERT INTO `sys_menu` VALUES ('45', '44', '查看', null, 'deliverydistributor:list,deliverydistributor:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('46', '44', '新增', null, 'deliverydistributor:save,deliveryendpoint:select', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('47', '44', '修改', null, 'deliverydistributor:update,deliveryendpoint:select', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('48', '44', '删除', null, 'deliverydistributor:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('49', '60', '配送点信息', 'delivery/deliveryendpoint.html', 'deliveryendpoint:list', '1', 'fa fa-map-marker', '6');
INSERT INTO `sys_menu` VALUES ('50', '49', '查看', null, 'deliveryendpoint:list,deliveryendpoint:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('51', '49', '新增', null, 'deliveryendpoint:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('52', '49', '修改', null, 'deliveryendpoint:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('53', '49', '删除', null, 'deliveryendpoint:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('54', '59', '商品库存', 'product/productstock.html', 'productstock:list', '1', 'fa fa-cubes', '6');
INSERT INTO `sys_menu` VALUES ('55', '54', '查看', null, 'productstock:list,productstock:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('56', '54', '新增', null, 'productstock:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('57', '54', '修改', null, 'productstock:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('58', '54', '删除', null, 'productstock:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('59', '0', '商品管理', null, null, '0', 'fa fa-cube', '0');
INSERT INTO `sys_menu` VALUES ('60', '0', '配送管理', null, null, '0', 'fa fa-bicycle', '0');
INSERT INTO `sys_menu` VALUES ('61', '0', '客户管理', null, null, '0', 'fa-users', '0');
INSERT INTO `sys_menu` VALUES ('62', '61', '客户信息', 'user/userinfo.html', 'userinfo:list', '1', 'fa fa-user', '6');
INSERT INTO `sys_menu` VALUES ('63', '62', '查看', null, 'userinfo:list,userinfo:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('64', '61', '押金提现申请', 'user/userwithdrawdeposit.html', 'userwithdrawdeposit:list', '1', 'fa fa-cny', '6');
INSERT INTO `sys_menu` VALUES ('65', '64', '查看', null, 'userwithdrawdeposit:list,userwithdrawdeposit:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('66', '64', '修改', null, 'userwithdrawdeposit:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('67', '0', '评价管理', null, null, '0', 'fa fa-comments-o', '0');
INSERT INTO `sys_menu` VALUES ('68', '67', '配送员评价', 'comment/commentdelivery.html', NULL, '1', 'fa fa-user', '6');
INSERT INTO `sys_menu` VALUES ('69', '68', '查看', null, 'commentdelivery:list,commentdelivery:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('70', '68', '删除', null, 'commentdelivery:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('71', '67', '商品评价', 'comment/commentproduct.html', NULL, '1', 'fa fa-cube', '6');
INSERT INTO `sys_menu` VALUES ('72', '71', '查看', null, 'commentproduct:list,commentproduct:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('73', '71', '删除', null, 'commentproduct:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('74', '0', '订单管理', null, null, '0', 'fa fa-reorder', '0');
INSERT INTO `sys_menu` VALUES ('75', '74', '订单信息', 'order/orderinfo.html', NULL, '1', 'fa fa-info', '6');
INSERT INTO `sys_menu` VALUES ('76', '75', '查看', null, 'orderinfo:list,orderinfo:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('77', '75', '修改', null, 'orderinfo:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('78', '74', '订单配送信息', 'order/orderdeliveryinfo.html', NULL, '1', 'fa fa-bicycle', '6');
INSERT INTO `sys_menu` VALUES ('79', '78', '查看', null, 'orderdeliveryinfo:list,orderdeliveryinfo:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('80', '78', '修改', null, 'orderdeliveryinfo:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('81', '74', '订单商品信息', 'order/orderproductdetail.html', NULL, '1', 'fa fa-cube', '6');
INSERT INTO `sys_menu` VALUES ('82', '81', '查看', null, 'orderproductdetail:list,orderproductdetail:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('83', '81', '修改', null, 'orderproductdetail:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('84', '74', '订单手工处理流水记录', 'order/orderoperateflow.html', NULL, '1', 'fa fa-hand-paper-o', '6');
INSERT INTO `sys_menu` VALUES ('85', '84', '查看', null, 'orderoperateflow:list,orderoperateflow:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('86', '84', '修改', null, 'orderoperateflow:update', '2', null, '6');

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('1', '1', '59');
INSERT INTO `sys_role_menu` VALUES ('2', '1', '39');
INSERT INTO `sys_role_menu` VALUES ('3', '1', '40');
INSERT INTO `sys_role_menu` VALUES ('4', '1', '41');
INSERT INTO `sys_role_menu` VALUES ('5', '1', '43');
INSERT INTO `sys_role_menu` VALUES ('6', '1', '54');
INSERT INTO `sys_role_menu` VALUES ('7', '1', '55');
INSERT INTO `sys_role_menu` VALUES ('8', '1', '57');
INSERT INTO `sys_role_menu` VALUES ('9', '1', '58');

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 'root@renren.io', '13612345678', '1', '1', '2016-11-11 11:11:11');


-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '2', '1');


