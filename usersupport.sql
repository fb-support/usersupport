/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : usersupport

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-03-08 10:49:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `login_user`
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of login_user
-- ----------------------------

-- ----------------------------
-- Table structure for `menu`
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
-- Table structure for `role`
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of role
-- ----------------------------

-- ----------------------------
-- Table structure for `role_menu`
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
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `work_number` int(11) NOT NULL,
  `username` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  `email` varchar(32) NOT NULL,
  `phone` varchar(11) NOT NULL,
  `gmt_create` bigint(20) NOT NULL,
  `gmt_modify` bigint(20) DEFAULT NULL,
  `status` smallint(6) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------

-- ----------------------------
-- Table structure for `user_role`
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- ----------------------------
-- Records of user_role
-- ----------------------------
