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



INSERT INTO `qrtz_cron_triggers` VALUES ('RenrenScheduler', 'TASK_1', 'DEFAULT', '0 0/30 * * * ?', 'Asia/Shanghai');
INSERT INTO `qrtz_cron_triggers` VALUES ('RenrenScheduler', 'TASK_2', 'DEFAULT', '0 0/30 * * * ?', 'Asia/Shanghai');


INSERT INTO `qrtz_job_details` VALUES ('RenrenScheduler', 'TASK_1', 'DEFAULT', null, 'com.yunquanlai.utils.ScheduleJob', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B45597400AA7B226265616E4E616D65223A22746573745461736B222C2263726561746554696D65223A313438303630353430363030302C2263726F6E45787072657373696F6E223A223020302F3330202A202A202A203F222C226A6F624964223A312C226D6574686F644E616D65223A2274657374222C22706172616D73223A2272656E72656E222C2272656D61726B223A22E69C89E58F82E695B0E6B58BE8AF95222C22737461747573223A307D7800);
INSERT INTO `qrtz_job_details` VALUES ('RenrenScheduler', 'TASK_2', 'DEFAULT', null, 'com.yunquanlai.utils.ScheduleJob', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B45597400997B226265616E4E616D65223A22746573745461736B222C2263726561746554696D65223A313438303734383135363030302C2263726F6E45787072657373696F6E223A223020302F3330202A202A202A203F222C226A6F624964223A322C226D6574686F644E616D65223A227465737432222C2272656D61726B223A22E697A0E58F82E695B0E6B58BE8AF95222C22737461747573223A317D7800);

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks` VALUES ('RenrenScheduler', 'STATE_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('RenrenScheduler', 'TRIGGER_ACCESS');


-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------
INSERT INTO `qrtz_scheduler_state` VALUES ('RenrenScheduler', 'T1EFX5SVBR05ZCO1527994089370', '1527994598628', '15000');



-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------
INSERT INTO `qrtz_triggers` VALUES ('RenrenScheduler', 'TASK_1', 'DEFAULT', 'TASK_1', 'DEFAULT', null, '1527994800000', '-1', '5', 'WAITING', 'CRON', '1525529628000', '0', null, '2', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B45597400AA7B226265616E4E616D65223A22746573745461736B222C2263726561746554696D65223A313438303630353430363030302C2263726F6E45787072657373696F6E223A223020302F3330202A202A202A203F222C226A6F624964223A312C226D6574686F644E616D65223A2274657374222C22706172616D73223A2272656E72656E222C2272656D61726B223A22E69C89E58F82E695B0E6B58BE8AF95222C22737461747573223A307D7800);
INSERT INTO `qrtz_triggers` VALUES ('RenrenScheduler', 'TASK_2', 'DEFAULT', 'TASK_2', 'DEFAULT', null, '1525530600000', '-1', '5', 'PAUSED', 'CRON', '1525529628000', '0', null, '2', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B45597400997B226265616E4E616D65223A22746573745461736B222C2263726561746554696D65223A313438303734383135363030302C2263726F6E45787072657373696F6E223A223020302F3330202A202A202A203F222C226A6F624964223A322C226D6574686F644E616D65223A227465737432222C2272656D61726B223A22E697A0E58F82E695B0E6B58BE8AF95222C22737461747573223A317D7800);

-- ----------------------------
-- Records of schedule_job
-- ----------------------------
INSERT INTO `schedule_job` VALUES ('1', 'testTask', 'test', 'renren', '0 0/30 * * * ?', '0', '有参数测试', '2016-12-01 23:16:46');
INSERT INTO `schedule_job` VALUES ('2', 'testTask', 'test2', null, '0 0/30 * * * ?', '1', '无参数测试', '2016-12-03 14:55:56');


-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES ('1', 'CLOUD_STORAGE_CONFIG_KEY', '{\"aliyunAccessKeyId\":\"\",\"aliyunAccessKeySecret\":\"\",\"aliyunBucketName\":\"\",\"aliyunDomain\":\"\",\"aliyunEndPoint\":\"\",\"aliyunPrefix\":\"\",\"qcloudBucketName\":\"\",\"qcloudDomain\":\"\",\"qcloudPrefix\":\"\",\"qcloudSecretId\":\"\",\"qcloudSecretKey\":\"\",\"qiniuAccessKey\":\"nLtdODV1d9_FAfALJo_YcsDJllb5EEyJf79LQpM4\",\"qiniuBucketName\":\"3090717419\",\"qiniuDomain\":\"http://oqvkyp7u2.bkt.clouddn.com\",\"qiniuPrefix\":\"upload\",\"qiniuSecretKey\":\"tffLkwkkud2oichlZTRM7yzJdvFsM3-Nhubqo7d2\",\"type\":1}', '0', '云存储配置信息');


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
INSERT INTO `sys_menu` VALUES ('34', '59', '商品信息管理', 'product/productinfo.html', 'productinfo:list', '1', 'fa fa-file-code-o', '6');
INSERT INTO `sys_menu` VALUES ('35', '34', '查看', null, 'productinfo:list,productinfo:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('36', '34', '新增', null, 'productinfo:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('37', '34', '修改', null, 'productinfo:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('38', '34', '删除', null, 'productinfo:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('39', '59', '商品品牌管理', 'product/productbrand.html', 'productbrand:list', '1', 'fa fa-file-code-o', '6');
INSERT INTO `sys_menu` VALUES ('40', '39', '查看', null, 'productbrand:list,productbrand:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('41', '39', '新增', null, 'productbrand:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('42', '39', '修改', null, 'productbrand:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('43', '39', '删除', null, 'productbrand:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('44', '60', '配送员信息', 'delivery/deliverydistributor.html', 'deliverydistributor:list', '1', 'fa fa-file-code-o', '6');
INSERT INTO `sys_menu` VALUES ('45', '44', '查看', null, 'deliverydistributor:list,deliverydistributor:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('46', '44', '新增', null, 'deliverydistributor:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('47', '44', '修改', null, 'deliverydistributor:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('48', '44', '删除', null, 'deliverydistributor:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('49', '60', '配送点信息', 'delivery/deliveryendpoint.html', 'deliveryendpoint:list', '1', 'fa fa-file-code-o', '6');
INSERT INTO `sys_menu` VALUES ('50', '49', '查看', null, 'deliveryendpoint:list,deliveryendpoint:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('51', '49', '新增', null, 'deliveryendpoint:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('52', '49', '修改', null, 'deliveryendpoint:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('53', '49', '删除', null, 'deliveryendpoint:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('54', '59', '商品库存管理', 'product/productstock.html', 'productstock:list', '1', 'fa fa-file-code-o', '6');
INSERT INTO `sys_menu` VALUES ('55', '54', '查看', null, 'productstock:list,productstock:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('56', '54', '新增', null, 'productstock:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('57', '54', '修改', null, 'productstock:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('58', '54', '删除', null, 'productstock:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('59', '0', '商品管理', null, null, '0', null, '0');
INSERT INTO `sys_menu` VALUES ('60', '0', '配送管理', null, null, '0', null, '0');


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

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', 'mark', '13612345678', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', '2017-03-23 22:37:41');
