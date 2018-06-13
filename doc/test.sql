/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : yunquanlai

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-06-09 22:11:11
*/

SET FOREIGN_KEY_CHECKS=0;


-- ----------------------------
-- Records of delivery_endpoint
-- ----------------------------
INSERT INTO `delivery_endpoint` VALUES ('1', '广西大学配送站', '22.8313000000', '108.2919490000', null);
INSERT INTO `delivery_endpoint` VALUES ('2', '琅东客运站配送点', '22.8119830000', '108.4192760000', null);


-- ----------------------------
-- Records of product_brand
-- ----------------------------
INSERT INTO `product_brand` VALUES ('1', '哇哈哈', '爱的就是你');
INSERT INTO `product_brand` VALUES ('2', '农夫山泉', '有点甜');
INSERT INTO `product_brand` VALUES ('3', '云泉来', '云泉来自营');

INSERT INTO `product_detail` VALUES ('1', 'null,null,null,null', '<p>爱的就是你，哇哈哈桶装水</p>', '10001');
INSERT INTO `product_detail` VALUES ('2', 'null,null,null,null', '<p>农夫山泉，有点甜</p>', '10002');
INSERT INTO `product_detail` VALUES ('3', 'null,null,null,null', '<p>云泉来自营瓶装水</p>', '10000');

INSERT INTO `product_info` VALUES ('10001', '哇哈哈桶装水', '28.00', '18.00', 'http://pa23n5htw.bkt.clouddn.com/upload/20180609/81a226a2703941f4a8e0aa8806b84547', '1', '哇哈哈', '10', '2.00', '8', null, '10', '2018-06-09 20:48:11', '2018-06-09 21:46:17', '1', 'admin', '1', 'admin');
INSERT INTO `product_info` VALUES ('10002', '农夫山泉桶装水', '20.00', '10.00', 'http://pa23ubi36.bkt.clouddn.com/upload/20180609/34eb2e56c3fb4abebddd51d5d13d22ee', '2', '农夫山泉', '20', '2.00', '0', null, '20', '2018-06-09 20:53:21', null, '1', 'admin', null, null);
INSERT INTO `product_info` VALUES ('10000', '云泉来自营瓶装水', '6.00', '3.00', 'http://pa23ubi36.bkt.clouddn.com/upload/20180609/6c35aa47a0e44c6fb946b0cdef52927a', '3', '云泉来', '30', '1.00', '1', null, '10', '2018-06-09 21:10:49', '2018-06-09 21:43:14', '1', 'admin', '1', 'admin');


INSERT INTO `product_stock` VALUES ('1', '10002', '农夫山泉桶装水', '1', '广西大学配送站', '100');
INSERT INTO `product_stock` VALUES ('2', '10001', '哇哈哈桶装水', '1', '广西大学配送站', '100');
INSERT INTO `product_stock` VALUES ('3', '10000', '云泉来自营瓶装水', '1', '广西大学配送站', '100');
INSERT INTO `product_stock` VALUES ('4', '10000', '云泉来自营瓶装水', '2', '琅东客运站配送点', '100');
INSERT INTO `product_stock` VALUES ('5', '10002', '农夫山泉桶装水', '2', '琅东客运站配送点', '100');
INSERT INTO `product_stock` VALUES ('6', '10001', '哇哈哈桶装水', '2', '琅东客运站配送点', '100');

INSERT INTO `product_stock_flow` VALUES ('1', '4', '3', '0', '0', '3', '2018-06-09 21:12:08', 'admin', '1');

INSERT INTO `sys_config` VALUES ('2', 'banner', 'http://pa23ubi36.bkt.clouddn.com/upload/20180609/120f31b821d74673ab29b44d7253fc6f,http://img02.tooopen.com/images/20150928/tooopen_sy_143912755726.jpg, http://img06.tooopen.com/images/20160818/tooopen_sy_175866434296.jpg, http://img06.tooopen.com/images/20160818/tooopen_sy_175833047715.jpg', '1', '首页banner图使用‘,’隔开');
INSERT INTO `sys_config` VALUES ('3', 'emptyValue', '10', '1', '单个空桶价值');


INSERT INTO `sys_log` VALUES ('1', 'admin', '保存配置', 'com.yunquanlai.admin.system.contorller.SysConfigController.save()', '{\"key\":\"banner\",\"value\":\"http://img02.tooopen.com/images/20150928/tooopen_sy_143912755726.jpg, http://img06.tooopen.com/images/20160818/tooopen_sy_175866434296.jpg, http://img06.tooopen.com/images/20160818/tooopen_sy_175833047715.jpg\"}', '0:0:0:0:0:0:0:1', '2018-06-09 22:04:43');
INSERT INTO `sys_log` VALUES ('2', 'admin', '修改配置', 'com.yunquanlai.admin.system.contorller.SysConfigController.update()', '{\"id\":2,\"key\":\"banner\",\"value\":\"http://pa23ubi36.bkt.clouddn.com/upload/20180609/120f31b821d74673ab29b44d7253fc6f,http://img02.tooopen.com/images/20150928/tooopen_sy_143912755726.jpg, http://img06.tooopen.com/images/20160818/tooopen_sy_175866434296.jpg, http://img06.tooopen.com/images/20160818/tooopen_sy_175833047715.jpg\"}', '0:0:0:0:0:0:0:1', '2018-06-09 22:10:53');


INSERT INTO `sys_oss` VALUES ('1', 'http://pa23n5htw.bkt.clouddn.com/upload/20180609/81a226a2703941f4a8e0aa8806b84547', '2018-06-09 20:47:31');
INSERT INTO `sys_oss` VALUES ('2', 'http://pa23n5htw.bkt.clouddn.com/upload/20180609/ca2f51ef2086460d8f1d932c294a372e', '2018-06-09 20:48:35');
INSERT INTO `sys_oss` VALUES ('3', 'http://pa23n5htw.bkt.clouddn.com/upload/20180609/5d457705ef60439787529403fe1826e3', '2018-06-09 20:48:42');
INSERT INTO `sys_oss` VALUES ('4', 'http://pa23n5htw.bkt.clouddn.com/upload/20180609/4bb4dc4c651a465ea04d37f077b46763', '2018-06-09 20:50:35');
INSERT INTO `sys_oss` VALUES ('5', 'http://pa23ubi36.bkt.clouddn.com/upload/20180609/34eb2e56c3fb4abebddd51d5d13d22ee', '2018-06-09 20:52:20');
INSERT INTO `sys_oss` VALUES ('6', 'http://pa23ubi36.bkt.clouddn.com/upload/20180609/6c35aa47a0e44c6fb946b0cdef52927a', '2018-06-09 21:10:21');
INSERT INTO `sys_oss` VALUES ('7', 'http://pa23ubi36.bkt.clouddn.com/upload/20180609/120f31b821d74673ab29b44d7253fc6f', '2018-06-09 22:08:37');


INSERT INTO `delivery_distributor` VALUES ('10000', '包大人', '18154661991', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', '2018-01-15', '2265ae4097f587c3a95087985e568aa2', 0, '10', '20', 0.00, null, null, null, '1', '1', '广西大学配送站', null);
INSERT INTO `delivery_distributor` VALUES ('11', '章鱼', '15677187487', 'bcb15f821479b4d5772bd0ca866c00ad5f926e3580720659cc80d39c9d09802a', '2018-06-10', 'c758f22fe07a1d2fcb9c2bf100882647', 0, '10',10,0.00, null, null, null, '1', '1', '广西大学配送站', null);