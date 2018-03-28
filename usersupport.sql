/*
Navicat MySQL Data Transfer

Source Server         : krishnna
Source Server Version : 50714
Source Host           : localhost:3306
Source Database       : usersupport

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2018-03-28 17:42:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for launch_form
-- ----------------------------
DROP TABLE IF EXISTS `launch_form`;
CREATE TABLE `launch_form` (
  `form_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '上线工单id',
  `project_id` bigint(20) NOT NULL COMMENT '项目编号',
  `test_form_id` bigint(20) unsigned NOT NULL COMMENT '测试工单id',
  `form_content` text NOT NULL COMMENT '上线内容',
  `create_user` bigint(20) NOT NULL COMMENT '创建人员',
  `create_username` varchar(32) NOT NULL COMMENT '创建人员姓名',
  `accept_develop_user` bigint(20) DEFAULT NULL COMMENT '接单开发人员',
  `accept_develop_username` varchar(32) DEFAULT NULL COMMENT '接受开发人员姓名',
  `accept_test_user` bigint(20) unsigned DEFAULT NULL COMMENT '接单测试人员',
  `accept_test_username` varchar(32) DEFAULT NULL COMMENT '接单测试人员姓名',
  `form_status` int(2) NOT NULL DEFAULT '0' COMMENT '0：待验证（测试人员创建上线工单）    1：重新上线待验证（出现Bug回滚到此状态）    2：已接单（运维接单）    3：上线完成待验证（运维部署完成确认）    4：开发验证中（开发人员验证上线结果）    5：开发验证未通过（开发打回给运维）    6：开发验证通过（开发验证上线结果完成）    7：测试验证中（测试人员验证上线结果）    8：测试验证未通过（测试打回给运维）    9：测试验证通过（测试验证上线结果完成）    10：运维关闭工单（上线工单完成）',
  `note` varchar(512) DEFAULT NULL COMMENT '备注',
  `gmt_create` bigint(32) NOT NULL COMMENT '创建时间',
  `gmt_modify` bigint(32) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`form_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of launch_form
-- ----------------------------

-- ----------------------------
-- Table structure for login_user
-- ----------------------------
DROP TABLE IF EXISTS `login_user`;
CREATE TABLE `login_user` (
  `login_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `login_way` int(2) unsigned NOT NULL DEFAULT '1' COMMENT '登录方式（1用户名登录，2 手机，3 邮箱）',
  `username` varchar(32) NOT NULL COMMENT '用户名',
  `work_number` int(6) NOT NULL COMMENT '工号',
  `gmt_modified` bigint(32) DEFAULT NULL COMMENT '修改时间（UNIX时间戳）',
  `gmt_create` bigint(32) NOT NULL COMMENT '创建时间（UNIX时间戳）',
  `login_time` bigint(32) NOT NULL COMMENT '登录时间（UNIX时间戳）',
  `logout_time` bigint(32) DEFAULT NULL COMMENT '注销时间（UNIX时间戳）',
  PRIMARY KEY (`login_id`)
) ENGINE=InnoDB AUTO_INCREMENT=118 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of login_user
-- ----------------------------
INSERT INTO `login_user` VALUES ('5', '1', 'admin', '100000', '1521017425743', '1521017425743', '1521017425743', '1521018691633');
INSERT INTO `login_user` VALUES ('6', '1', 'admin', '100000', '1521017560718', '1521017560718', '1521017560718', '1521018691633');
INSERT INTO `login_user` VALUES ('7', '1', 'admin', '100000', '1521017849541', '1521017849541', '1521017849541', '1521018691633');
INSERT INTO `login_user` VALUES ('8', '1', 'admin', '100000', '1521018042857', '1521018042857', '1521018042857', '1521018691633');
INSERT INTO `login_user` VALUES ('9', '1', 'admin', '100000', '1521018364531', '1521018364531', '1521018364531', '1521018691633');
INSERT INTO `login_user` VALUES ('10', '1', 'admin', '100000', '1521018498369', '1521018498369', '1521018498369', '1521018691633');
INSERT INTO `login_user` VALUES ('11', '1', 'admin', '100000', '1521018560691', '1521018560691', '1521018560691', '1521018691633');
INSERT INTO `login_user` VALUES ('12', '1', 'admin', '100000', '1521018683021', '1521018683021', '1521018683021', '1521018691633');
INSERT INTO `login_user` VALUES ('13', '1', 'admin', '100000', '1521019309063', '1521019309063', '1521019309063', '683246516115');
INSERT INTO `login_user` VALUES ('14', '1', 'admin', '100000', '1521019785700', '1521019785700', '1521019785700', '1521019793255');
INSERT INTO `login_user` VALUES ('15', '1', 'zhangsan', '100001', '1521019824949', '1521019824949', '1521019824949', '1521019847365');
INSERT INTO `login_user` VALUES ('16', '1', 'zhangsan', '100001', '1521020007989', '1521020007989', '1521020007989', '1521020015860');
INSERT INTO `login_user` VALUES ('17', '1', 'zhangsan', '100001', '1521020041976', '1521020041976', '1521020041976', '1521020043316');
INSERT INTO `login_user` VALUES ('18', '1', 'admin', '100000', '1521020863346', '1521020863346', '1521020863346', null);
INSERT INTO `login_user` VALUES ('19', '1', 'admin', '100000', '1521021095757', '1521021095757', '1521021095757', null);
INSERT INTO `login_user` VALUES ('20', '1', 'admin', '100000', '1521037417524', '1521037417524', '1521037417524', null);
INSERT INTO `login_user` VALUES ('21', '1', 'admin', '100000', '1521037597613', '1521037597613', '1521037597613', '1521037673609');
INSERT INTO `login_user` VALUES ('22', '1', 'admin', '100000', '1521037675772', '1521037675772', '1521037675772', null);
INSERT INTO `login_user` VALUES ('23', '1', 'admin', '100000', '1521037800303', '1521037800303', '1521037800303', null);
INSERT INTO `login_user` VALUES ('24', '1', 'admin', '100000', '1521076243918', '1521076243918', '1521076243918', null);
INSERT INTO `login_user` VALUES ('25', '1', 'admin', '100000', '1521078227284', '1521078227284', '1521078227284', null);
INSERT INTO `login_user` VALUES ('26', '1', 'admin', '100000', '1521078831813', '1521078831813', '1521078831813', null);
INSERT INTO `login_user` VALUES ('27', '1', 'admin', '100000', '1521080475504', '1521080475504', '1521080475504', null);
INSERT INTO `login_user` VALUES ('28', '1', 'admin', '100000', '1521082059479', '1521082059479', '1521082059479', null);
INSERT INTO `login_user` VALUES ('29', '1', 'admin', '100000', '1521083185575', '1521083185575', '1521083185575', null);
INSERT INTO `login_user` VALUES ('30', '1', 'admin', '100000', '1521084263885', '1521084263885', '1521084263885', null);
INSERT INTO `login_user` VALUES ('31', '1', 'admin', '100000', '1521084868624', '1521084868624', '1521084868624', null);
INSERT INTO `login_user` VALUES ('32', '1', 'admin', '100000', '1521084898335', '1521084898335', '1521084898335', null);
INSERT INTO `login_user` VALUES ('33', '1', 'admin', '100000', '1521084933169', '1521084933169', '1521084933169', null);
INSERT INTO `login_user` VALUES ('34', '1', 'admin', '100000', '1521085150486', '1521085150486', '1521085150486', null);
INSERT INTO `login_user` VALUES ('35', '1', 'admin', '100000', '1521085292231', '1521085292231', '1521085292231', null);
INSERT INTO `login_user` VALUES ('36', '1', 'admin', '100000', '1521085319281', '1521085319281', '1521085319281', null);
INSERT INTO `login_user` VALUES ('37', '1', 'admin', '100000', '1521085381811', '1521085381811', '1521085381811', null);
INSERT INTO `login_user` VALUES ('38', '1', 'admin', '100000', '1521085449909', '1521085449909', '1521085449909', null);
INSERT INTO `login_user` VALUES ('39', '1', 'admin', '100000', '1521085710673', '1521085710673', '1521085710673', null);
INSERT INTO `login_user` VALUES ('40', '1', 'admin', '100000', '1521085750847', '1521085750847', '1521085750847', null);
INSERT INTO `login_user` VALUES ('41', '1', 'admin', '100000', '1521085773144', '1521085773144', '1521085773144', null);
INSERT INTO `login_user` VALUES ('42', '1', 'admin', '100000', '1521092116670', '1521092116670', '1521092116670', null);
INSERT INTO `login_user` VALUES ('43', '1', 'admin', '100000', '1521092132464', '1521092132464', '1521092132464', null);
INSERT INTO `login_user` VALUES ('44', '1', 'admin', '100000', '1521092639522', '1521092639522', '1521092639522', null);
INSERT INTO `login_user` VALUES ('45', '1', 'admin', '100000', '1522120857146', '1522120857146', '1522120857146', null);
INSERT INTO `login_user` VALUES ('46', '1', 'admin', '100000', '1522121172350', '1522121172350', '1522121172350', null);
INSERT INTO `login_user` VALUES ('47', '1', 'admin', '100000', '1522132094797', '1522132094797', '1522132094797', null);
INSERT INTO `login_user` VALUES ('48', '1', 'admin', '100000', '1522132239731', '1522132239731', '1522132239731', null);
INSERT INTO `login_user` VALUES ('49', '1', 'admin', '100000', '1522134338658', '1522134338658', '1522134338658', null);
INSERT INTO `login_user` VALUES ('50', '1', 'admin', '100000', '1522136369782', '1522136369782', '1522136369782', null);
INSERT INTO `login_user` VALUES ('51', '1', 'admin', '100000', '1522139354972', '1522139354972', '1522139354972', null);
INSERT INTO `login_user` VALUES ('52', '1', 'admin', '100000', '1522140453032', '1522140453032', '1522140453032', null);
INSERT INTO `login_user` VALUES ('53', '1', 'admin', '100000', '1522140659162', '1522140659162', '1522140659162', null);
INSERT INTO `login_user` VALUES ('54', '1', 'admin', '100000', '1522140837249', '1522140837249', '1522140837249', null);
INSERT INTO `login_user` VALUES ('55', '1', 'admin', '100000', '1522141129400', '1522141129400', '1522141129400', null);
INSERT INTO `login_user` VALUES ('56', '1', 'admin', '100000', '1522141858071', '1522141858071', '1522141858071', null);
INSERT INTO `login_user` VALUES ('57', '1', 'admin', '100000', '1522142138162', '1522142138162', '1522142138162', null);
INSERT INTO `login_user` VALUES ('58', '1', 'admin', '100000', '1522142905693', '1522142905693', '1522142905693', null);
INSERT INTO `login_user` VALUES ('59', '1', 'admin', '100000', '1522143422122', '1522143422122', '1522143422122', null);
INSERT INTO `login_user` VALUES ('60', '1', 'admin', '100000', '1522143940513', '1522143940513', '1522143940513', null);
INSERT INTO `login_user` VALUES ('61', '1', 'admin', '100000', '1522144028634', '1522144028634', '1522144028634', null);
INSERT INTO `login_user` VALUES ('62', '1', 'admin', '100000', '1522144550385', '1522144550385', '1522144550385', null);
INSERT INTO `login_user` VALUES ('63', '1', 'admin', '100000', '1522144811312', '1522144811312', '1522144811312', null);
INSERT INTO `login_user` VALUES ('64', '1', 'admin', '100000', '1522144920057', '1522144920057', '1522144920057', null);
INSERT INTO `login_user` VALUES ('65', '1', 'admin', '100000', '1522145484747', '1522145484747', '1522145484747', null);
INSERT INTO `login_user` VALUES ('66', '1', 'admin', '100000', '1522146975988', '1522146975988', '1522146975988', null);
INSERT INTO `login_user` VALUES ('67', '1', 'admin', '100000', '1522147045495', '1522147045495', '1522147045495', null);
INSERT INTO `login_user` VALUES ('68', '1', 'admin', '100000', '1522147369853', '1522147369853', '1522147369853', null);
INSERT INTO `login_user` VALUES ('69', '1', 'admin', '100000', '1522199436986', '1522199436986', '1522199436986', null);
INSERT INTO `login_user` VALUES ('70', '1', 'admin', '100000', '1522199801441', '1522199801441', '1522199801441', null);
INSERT INTO `login_user` VALUES ('71', '1', 'admin', '100000', '1522199954714', '1522199954714', '1522199954714', null);
INSERT INTO `login_user` VALUES ('72', '1', 'admin', '100000', '1522200749368', '1522200749368', '1522200749368', null);
INSERT INTO `login_user` VALUES ('73', '1', 'admin', '100000', '1522201050503', '1522201050503', '1522201050503', null);
INSERT INTO `login_user` VALUES ('74', '1', 'admin', '100000', '1522201315079', '1522201315079', '1522201315079', null);
INSERT INTO `login_user` VALUES ('75', '1', 'admin', '100000', '1522201441675', '1522201441675', '1522201441675', null);
INSERT INTO `login_user` VALUES ('76', '1', 'admin', '100000', '1522201661657', '1522201661657', '1522201661657', null);
INSERT INTO `login_user` VALUES ('77', '1', 'admin', '100000', '1522202358949', '1522202358949', '1522202358949', null);
INSERT INTO `login_user` VALUES ('78', '1', 'admin', '100000', '1522203380277', '1522203380277', '1522203380277', null);
INSERT INTO `login_user` VALUES ('79', '1', 'admin', '100000', '1522203491035', '1522203491035', '1522203491035', null);
INSERT INTO `login_user` VALUES ('80', '1', 'admin', '100000', '1522203550010', '1522203550010', '1522203550010', null);
INSERT INTO `login_user` VALUES ('81', '1', 'admin', '100000', '1522203664797', '1522203664797', '1522203664797', null);
INSERT INTO `login_user` VALUES ('82', '1', 'admin', '100000', '1522203845810', '1522203845810', '1522203845810', null);
INSERT INTO `login_user` VALUES ('83', '1', 'admin', '100000', '1522204017164', '1522204017164', '1522204017164', null);
INSERT INTO `login_user` VALUES ('84', '1', 'admin', '100000', '1522204307747', '1522204307747', '1522204307747', null);
INSERT INTO `login_user` VALUES ('85', '1', 'admin', '100000', '1522204521203', '1522204521203', '1522204521203', null);
INSERT INTO `login_user` VALUES ('86', '1', 'admin', '100000', '1522204633036', '1522204633036', '1522204633036', null);
INSERT INTO `login_user` VALUES ('87', '1', 'admin', '100000', '1522206847536', '1522206847536', '1522206847537', null);
INSERT INTO `login_user` VALUES ('88', '1', 'admin', '100000', '1522207216622', '1522207216622', '1522207216622', null);
INSERT INTO `login_user` VALUES ('89', '1', 'admin', '100000', '1522207367666', '1522207367666', '1522207367666', null);
INSERT INTO `login_user` VALUES ('90', '1', 'admin', '100000', '1522207452851', '1522207452851', '1522207452851', null);
INSERT INTO `login_user` VALUES ('91', '1', 'admin', '100000', '1522207552121', '1522207552121', '1522207552121', null);
INSERT INTO `login_user` VALUES ('92', '1', 'admin', '100000', '1522208289338', '1522208289338', '1522208289338', null);
INSERT INTO `login_user` VALUES ('93', '1', 'admin', '100000', '1522208396654', '1522208396654', '1522208396654', null);
INSERT INTO `login_user` VALUES ('94', '1', 'admin', '100000', '1522209016605', '1522209016605', '1522209016605', null);
INSERT INTO `login_user` VALUES ('95', '1', 'admin', '100000', '1522209099791', '1522209099791', '1522209099791', null);
INSERT INTO `login_user` VALUES ('96', '1', 'admin', '100000', '1522209222694', '1522209222694', '1522209222694', null);
INSERT INTO `login_user` VALUES ('97', '1', 'admin', '100000', '1522209364185', '1522209364185', '1522209364185', null);
INSERT INTO `login_user` VALUES ('98', '1', 'admin', '100000', '1522209815220', '1522209815220', '1522209815220', null);
INSERT INTO `login_user` VALUES ('99', '1', 'admin', '100000', '1522215446078', '1522215446078', '1522215446078', null);
INSERT INTO `login_user` VALUES ('100', '1', 'admin', '100000', '1522215907297', '1522215907297', '1522215907297', null);
INSERT INTO `login_user` VALUES ('101', '1', 'admin', '100000', '1522216805175', '1522216805175', '1522216805175', null);
INSERT INTO `login_user` VALUES ('102', '1', 'admin', '100000', '1522216870638', '1522216870638', '1522216870638', null);
INSERT INTO `login_user` VALUES ('103', '1', 'admin', '100000', '1522216990345', '1522216990345', '1522216990345', null);
INSERT INTO `login_user` VALUES ('104', '1', 'admin', '100000', '1522217114836', '1522217114836', '1522217114836', null);
INSERT INTO `login_user` VALUES ('105', '1', 'admin', '100000', '1522217869763', '1522217869763', '1522217869763', null);
INSERT INTO `login_user` VALUES ('106', '1', 'admin', '100000', '1522217961536', '1522217961536', '1522217961536', null);
INSERT INTO `login_user` VALUES ('107', '1', 'admin', '100000', '1522219434466', '1522219434466', '1522219434466', null);
INSERT INTO `login_user` VALUES ('108', '1', 'admin', '100000', '1522226072683', '1522226072683', '1522226072683', null);
INSERT INTO `login_user` VALUES ('109', '1', 'admin', '100000', '1522226241086', '1522226241086', '1522226241086', null);
INSERT INTO `login_user` VALUES ('110', '1', 'admin', '100000', '1522226605199', '1522226605199', '1522226605199', null);
INSERT INTO `login_user` VALUES ('111', '1', 'admin', '100000', '1522228550919', '1522228550919', '1522228550919', null);
INSERT INTO `login_user` VALUES ('112', '1', 'admin', '100000', '1522228962785', '1522228962785', '1522228962785', null);
INSERT INTO `login_user` VALUES ('113', '1', 'admin', '100000', '1522229127150', '1522229127150', '1522229127150', null);
INSERT INTO `login_user` VALUES ('114', '1', 'admin', '100000', '1522229248661', '1522229248660', '1522229248661', null);
INSERT INTO `login_user` VALUES ('115', '1', 'admin', '100000', '1522229578913', '1522229578913', '1522229578913', null);
INSERT INTO `login_user` VALUES ('116', '1', 'admin', '100000', '1522229739868', '1522229739868', '1522229739868', null);
INSERT INTO `login_user` VALUES ('117', '1', 'admin', '100000', '1522230020741', '1522230020741', '1522230020741', null);

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menu_url` varchar(128) NOT NULL,
  `status` smallint(6) NOT NULL,
  `gmt_create` bigint(20) NOT NULL,
  `gmt_modify` bigint(20) DEFAULT NULL,
  `creator` varchar(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`menu_id`),
  KEY `AK_Key_3` (`menu_url`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Records of menu
-- ----------------------------

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(32) NOT NULL,
  `status` smallint(6) NOT NULL,
  `gmt_create` bigint(20) NOT NULL,
  `gmt_modify` bigint(20) DEFAULT NULL,
  `creator` varchar(64) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`),
  KEY `AK_Key_2` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'ROLE_ADMIN', '1', '563496856526', null, 'admin', null);
INSERT INTO `role` VALUES ('2', 'ROLE_USER', '1', '53169828122', null, 'admin', null);

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL,
  `menu_id` bigint(20) NOT NULL,
  `gmt_create` bigint(20) NOT NULL,
  `gmt_modify` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `AK_Key_2` (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单表';

-- ----------------------------
-- Records of role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for test_form
-- ----------------------------
DROP TABLE IF EXISTS `test_form`;
CREATE TABLE `test_form` (
  `form_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '测试工单编号',
  `form_service` varchar(32) NOT NULL COMMENT '提测服务',
  `form_branch` varchar(32) NOT NULL COMMENT '提测分支',
  `sql_change` text COMMENT '数据库是否变更',
  `configure_change` text COMMENT '配置文件变更',
  `mq_change` text COMMENT 'MQ是否变更',
  `is_test` int(2) DEFAULT '1' COMMENT '是否已自测，默认1已自测，0未自测',
  `influence_scope` varchar(512) NOT NULL COMMENT '影响范围',
  `is_review` int(2) NOT NULL DEFAULT '1' COMMENT '是否代码review',
  `other_change` varchar(512) DEFAULT NULL COMMENT '其他变更',
  `create_username` varchar(32) NOT NULL COMMENT '创建人员姓名',
  `create_user` bigint(20) NOT NULL COMMENT '创建人员',
  `accept_username` varchar(32) DEFAULT NULL COMMENT '接单人员姓名',
  `accept_user` bigint(20) DEFAULT NULL COMMENT '接单人员',
  `project_id` bigint(20) NOT NULL COMMENT '项目编号',
  `form_status` int(2) NOT NULL DEFAULT '0' COMMENT '0：待测试（开发人员创建测试工单）     1：重新提交待测试（开发人员修改Bug重新提交）     2 : 测试中（测试人员接受测试工单）     3：测试未通过（打回给开发人员）     4：测试通过（测试工单完成）',
  `gmt_create` bigint(32) NOT NULL COMMENT '创建时间',
  `gmt_modify` bigint(32) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`form_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of test_form
-- ----------------------------

-- ----------------------------
-- Table structure for test_project
-- ----------------------------
DROP TABLE IF EXISTS `test_project`;
CREATE TABLE `test_project` (
  `project_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '项目编号',
  `project_name` varchar(32) NOT NULL COMMENT '项目名称',
  `project_code` varchar(32) NOT NULL COMMENT '项目代号',
  `project_url` varchar(255) NOT NULL COMMENT '项目地址',
  `begin_time` bigint(32) NOT NULL COMMENT '起始时间',
  `end_time` bigint(32) NOT NULL COMMENT '结束时间',
  `note` varchar(512) DEFAULT NULL COMMENT '备注',
  `status` int(2) NOT NULL DEFAULT '1' COMMENT '项目状态，默认1进行中，0已删除。2已完成',
  `gmt_create` bigint(32) NOT NULL COMMENT '创建时间',
  `gmt_modify` bigint(32) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`project_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of test_project
-- ----------------------------
INSERT INTO `test_project` VALUES ('1', 'A计划', 'A', 'http://localhost:8080', '135649851348', '453137832453', '无', '1', '641532498651', '4631534615');
INSERT INTO `test_project` VALUES ('4', 'xxxx', 'aaaa', 'http://aaa', '1520697600000', '1541520000000', null, '1', '1522229293132', '1522229293132');
INSERT INTO `test_project` VALUES ('5', 'B计划', '代号B', 'http://baidu.com', '1521129600000', '1521388800000', null, '1', '1522229626613', '1522229626613');
INSERT INTO `test_project` VALUES ('6', 'C计划', '代号C', 'http://c.com', '1520611200000', '1528214400000', null, '1', '1522229777981', '1522229777981');

-- ----------------------------
-- Table structure for test_project_record
-- ----------------------------
DROP TABLE IF EXISTS `test_project_record`;
CREATE TABLE `test_project_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '项目流水编号',
  `operating_content` varchar(512) NOT NULL COMMENT '每次对工单进行的操作内容',
  `operating_people_id` bigint(20) NOT NULL COMMENT '对工单进行操作人员的id',
  `operating_people` varchar(32) NOT NULL COMMENT '对工单进行操作的人员',
  `project_id` bigint(20) NOT NULL COMMENT '项目编号',
  `form_id` bigint(20) NOT NULL COMMENT '工单编号',
  `form_type` int(2) NOT NULL COMMENT '工单类型（1测试工单，2上线工单）',
  `gmt_create` bigint(20) NOT NULL COMMENT '创建时间',
  `gmt_modify` bigint(20) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of test_project_record
-- ----------------------------

-- ----------------------------
-- Table structure for test_project_user
-- ----------------------------
DROP TABLE IF EXISTS `test_project_user`;
CREATE TABLE `test_project_user` (
  `project_user` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `project_id` bigint(20) NOT NULL COMMENT '项目编号',
  `user_id` bigint(20) NOT NULL COMMENT '用户编号',
  `user_role` int(2) DEFAULT NULL COMMENT '用户角色，1为开发，2为测试，3为运维',
  `status` int(2) NOT NULL DEFAULT '1' COMMENT '项目状态，默认1进行中，0已删除，2已完成',
  `gmt_create` bigint(32) NOT NULL COMMENT '创建时间',
  `gmt_modify` bigint(32) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`project_user`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of test_project_user
-- ----------------------------
INSERT INTO `test_project_user` VALUES ('1', '1', '1', '1', '1', '1522229293136', '1522229293136');
INSERT INTO `test_project_user` VALUES ('2', '1', '2', '1', '1', '1522229293136', '1522229293136');
INSERT INTO `test_project_user` VALUES ('3', '1', '8', '2', '1', '1522229293136', '1522229293136');
INSERT INTO `test_project_user` VALUES ('4', '1', '9', '2', '1', '1522229293136', '1522229293136');
INSERT INTO `test_project_user` VALUES ('5', '1', '8', '3', '1', '1522229293136', '1522229293136');
INSERT INTO `test_project_user` VALUES ('6', '1', '9', '3', '1', '1522229293136', '1522229293136');
INSERT INTO `test_project_user` VALUES ('7', '1', '1', '1', '1', '1522229626717', '1522229626717');
INSERT INTO `test_project_user` VALUES ('8', '1', '2', '1', '1', '1522229626717', '1522229626717');
INSERT INTO `test_project_user` VALUES ('9', '1', '9', '2', '1', '1522229626717', '1522229626717');
INSERT INTO `test_project_user` VALUES ('10', '1', '10', '2', '1', '1522229626717', '1522229626717');
INSERT INTO `test_project_user` VALUES ('11', '1', '6', '3', '1', '1522229626717', '1522229626717');
INSERT INTO `test_project_user` VALUES ('12', '1', '5', '1', '1', '1522229777985', '1522229777985');
INSERT INTO `test_project_user` VALUES ('13', '1', '16', '2', '1', '1522229777985', '1522229777985');
INSERT INTO `test_project_user` VALUES ('14', '1', '17', '2', '1', '1522229777985', '1522229777985');
INSERT INTO `test_project_user` VALUES ('15', '1', '12', '3', '1', '1522229777985', '1522229777985');
INSERT INTO `test_project_user` VALUES ('16', '1', '13', '3', '1', '1522229777985', '1522229777985');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `work_number` int(6) NOT NULL,
  `username` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  `email` varchar(32) NOT NULL,
  `phone` varchar(11) NOT NULL,
  `gmt_create` bigint(20) NOT NULL,
  `gmt_modify` bigint(20) DEFAULT NULL,
  `status` smallint(6) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '100000', 'admin', '123', '100001@facebank.cn', '18814384402', '1520783766592', '1521000032866', '0');
INSERT INTO `user` VALUES ('2', '100001', 'zhangsan', '123', '100001facebank.cn', '18814384685', '1520784554360', '1520784554360', '0');
INSERT INTO `user` VALUES ('5', '100005', 'sdfasdff', '123', '100005@facebank.cn', '18814364952', '1520822452672', '1520822452672', '0');
INSERT INTO `user` VALUES ('6', '100006', 'zhangsanfeng', '123', '100006@facebank.cn', '18814384565', '1520823483366', '1520823483366', '0');
INSERT INTO `user` VALUES ('7', '100007', 'zhangsanfengya', '123', '100007@facebank.cn', '18814386545', '1520826863414', '1520826863414', '0');
INSERT INTO `user` VALUES ('8', '100008', 'aaaaa', '123', '100008@facebank.cn', '18814386546', '1520826876598', '1520826876598', '0');
INSERT INTO `user` VALUES ('9', '100009', 'zhangsanyu', '123', '100009@facebank.cn', '18814386555', '1520826894310', '1520826894310', '0');
INSERT INTO `user` VALUES ('10', '100010', 'zhangsou', '123', '100010@facebank.cn', '18814386599', '1520826910007', '1520826910007', '0');
INSERT INTO `user` VALUES ('11', '100011', 'zhangsyy', '123', '100011@facebank.cn', '18814386598', '1520826922744', '1520826922744', '0');
INSERT INTO `user` VALUES ('12', '100012', 'ddddddd', '123', '100012@facebank.cn', '18884626565', '1520834533271', '1520834533271', '0');
INSERT INTO `user` VALUES ('13', '100017', 'youshfnm', '123', '100017@facebank.cn', '18814384444', '1520835587912', '1520835587912', '0');
INSERT INTO `user` VALUES ('14', '100018', 'youshfnmp', '123', '100018@facebank.cn', '18814384447', '1520835597187', '1520835597187', '0');
INSERT INTO `user` VALUES ('15', '100000', '张825fsd', '123', '100000@facebank.cn', '18814324582', '1520841738877', '1520841738877', '0');
INSERT INTO `user` VALUES ('16', '100000', '网22fdf', '123', '100000@facebank.cn', '18814384625', '1520841918576', '1520841918576', '0');
INSERT INTO `user` VALUES ('17', '100088', '张zhangsan', '123', 'zhangningkui@facebank.cn', '18814384488', '1520845129024', '1520845129024', '0');
INSERT INTO `user` VALUES ('18', '105253', '黄天津队ab', '123', '105253@facebank.cn', '18814369545', '1520946942340', '1520946942340', '0');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  `gmt_create` bigint(20) NOT NULL,
  `gmt_modify` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `AK_Key_2` (`user_id`,`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1', '1', '653451343253', '45312345384');
INSERT INTO `user_role` VALUES ('2', '2', '2', '46342373453', '73453783453');
INSERT INTO `user_role` VALUES ('3', '3', '1', '75345378453', '573538453783');
INSERT INTO `user_role` VALUES ('5', '8', '2', '4567234537832', '4237845378');
