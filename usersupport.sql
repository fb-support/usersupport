/*
Navicat MySQL Data Transfer

Source Server         : usersupport
Source Server Version : 50721
Source Host           : 192.168.0.200:3306
Source Database       : usersupport

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-04-18 16:18:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for apply_record
-- ----------------------------
DROP TABLE IF EXISTS `apply_record`;
CREATE TABLE `apply_record` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '申请记录编号',
  `work_number` int(16) NOT NULL COMMENT '员工号',
  `emp_name` varchar(16) NOT NULL COMMENT '员工姓名',
  `start_time` datetime NOT NULL COMMENT '开始时间',
  `end_time` datetime NOT NULL COMMENT '结束时间',
  `apply_duration` float(4,1) NOT NULL DEFAULT '0.0' COMMENT '共计时长（单位：小时）',
  `apply_date` date NOT NULL COMMENT '申请日期',
  `apply_cause` varchar(128) NOT NULL DEFAULT '' COMMENT '申请原因',
  `apply_type` int(2) NOT NULL COMMENT '申请类型（0：加班，1：请假，2：调休）',
  `status` int(2) NOT NULL DEFAULT '0' COMMENT '0：待审核，1：审核中，2：通过，3：未通过',
  `notpass_cause` varchar(128) DEFAULT NULL COMMENT '未通过原因',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8 COMMENT='申请记录表';

-- ----------------------------
-- Records of apply_record
-- ----------------------------
INSERT INTO `apply_record` VALUES ('1', '100001', 'zhangsan', '2018-03-28 10:10:00', '2018-03-30 21:10:00', '0.0', '2018-03-28', '的接口连接奥斯卡就开了房间卡拉科技风口浪尖阿卡丽', '1', '1', null);
INSERT INTO `apply_record` VALUES ('2', '100001', 'zhangsan', '2018-03-28 19:00:00', '2018-03-29 19:00:00', '0.0', '2018-03-28', '有病', '1', '2', null);
INSERT INTO `apply_record` VALUES ('3', '100001', 'zhangsan', '2018-03-28 11:20:00', '2018-03-31 11:20:00', '0.0', '2018-03-28', '喝酒', '1', '3', null);
INSERT INTO `apply_record` VALUES ('4', '100001', 'zhangsan', '2018-03-29 00:00:00', '2018-03-29 00:00:00', '3.5', '2018-03-29', '发的发大水发放', '1', '0', null);
INSERT INTO `apply_record` VALUES ('5', '100001', 'zhangsan', '2018-03-30 14:20:00', '2018-03-29 17:12:00', '23.0', '2018-03-30', '积极发挥的时间啦回复即可哈迪斯几号放假卡号666', '1', '0', null);
INSERT INTO `apply_record` VALUES ('6', '100001', 'zhangsan', '2018-03-28 11:30:00', '2018-03-29 11:30:00', '0.0', '2018-03-28', '喝酒', '1', '0', null);
INSERT INTO `apply_record` VALUES ('7', '100001', 'zhangsan', '2018-03-28 11:40:00', '2018-03-29 20:40:00', '0.0', '2018-03-30', '喝酒', '1', '0', null);
INSERT INTO `apply_record` VALUES ('8', '100001', 'zhangsan', '2018-03-28 11:40:00', '2018-03-29 20:40:00', '0.0', '2018-03-28', '喝酒', '1', '0', null);
INSERT INTO `apply_record` VALUES ('9', '100001', 'zhangsan', '2018-03-28 11:45:00', '2018-03-29 11:45:00', '0.0', '2018-03-28', '有事', '1', '0', null);
INSERT INTO `apply_record` VALUES ('10', '100001', 'zhangsan', '2018-03-28 11:45:00', '2018-03-29 11:45:00', '0.0', '2018-03-28', '有事', '1', '0', null);
INSERT INTO `apply_record` VALUES ('11', '100001', 'zhangsan', '2018-03-28 11:45:00', '2018-03-29 11:45:00', '0.0', '2018-03-28', '有事', '1', '0', null);
INSERT INTO `apply_record` VALUES ('12', '100001', 'zhangsan', '2018-03-28 11:55:00', '2018-03-29 11:55:00', '0.0', '2018-03-28', '辅导辅导', '1', '0', null);
INSERT INTO `apply_record` VALUES ('13', '100001', 'zhangsan', '2018-03-28 12:00:00', '2018-03-29 12:00:00', '0.0', '2018-03-28', '黄金黄金进口', '1', '0', null);
INSERT INTO `apply_record` VALUES ('14', '100001', 'zhangsan', '2018-03-28 12:05:00', '2018-03-29 12:05:00', '0.0', '2018-03-28', '发生的范德萨', '1', '0', null);
INSERT INTO `apply_record` VALUES ('15', '100001', 'zhangsan', '2018-03-28 13:40:00', '2018-03-29 13:40:00', '0.0', '2018-03-28', '噶地方都是', '1', '0', null);
INSERT INTO `apply_record` VALUES ('16', '100001', 'zhangsan', '2018-03-28 13:40:00', '2018-03-29 13:40:00', '0.0', '2018-03-28', '噶地方都是', '1', '0', null);
INSERT INTO `apply_record` VALUES ('17', '100001', 'zhangsan', '2018-03-28 13:50:00', '2018-03-28 13:50:00', '0.0', '2018-03-28', '发大水', '1', '0', null);
INSERT INTO `apply_record` VALUES ('18', '100001', 'zhangsan', '2018-03-28 13:50:00', '2018-03-28 13:50:00', '0.0', '2018-03-28', '发大水', '1', '0', null);
INSERT INTO `apply_record` VALUES ('19', '100001', 'zhangsan', '2018-03-28 13:50:00', '2018-03-28 13:55:00', '0.0', '2018-03-28', 'fdasfa', '1', '0', null);
INSERT INTO `apply_record` VALUES ('20', '100001', 'zhangsan', '2018-03-28 13:50:00', '2018-03-28 13:55:00', '0.0', '2018-03-28', 'fdasfa', '1', '0', null);
INSERT INTO `apply_record` VALUES ('21', '100001', 'zhangsan', '2018-03-28 13:55:00', '2018-03-30 13:55:00', '0.0', '2018-03-28', 'ddfa', '1', '0', null);
INSERT INTO `apply_record` VALUES ('22', '100001', 'zhangsan', '2018-03-28 14:00:00', '2018-03-29 14:00:00', '0.0', '2018-03-28', 'fasfa', '1', '0', null);
INSERT INTO `apply_record` VALUES ('23', '100001', 'zhangsan', '2018-03-28 14:00:00', '2018-03-28 15:00:00', '0.0', '2018-03-28', 'safadsfas', '1', '0', null);
INSERT INTO `apply_record` VALUES ('24', '100001', 'zhangsan', '2018-03-28 14:00:00', '2018-03-28 15:00:00', '0.0', '2018-03-28', 'safadsfas', '1', '0', null);
INSERT INTO `apply_record` VALUES ('25', '100001', 'zhangsan', '2018-03-28 14:05:00', '2018-03-28 16:05:00', '0.0', '2018-03-28', 'dafadsfas', '1', '0', null);
INSERT INTO `apply_record` VALUES ('26', '100001', 'zhangsan', '2018-03-28 14:05:00', '2018-03-28 16:05:00', '0.0', '2018-03-28', 'dafadsfas', '1', '0', null);
INSERT INTO `apply_record` VALUES ('27', '100001', 'zhangsan', '2018-03-28 20:05:00', '2018-03-29 14:05:00', '0.0', '2018-03-28', 'asdfafads', '1', '0', null);
INSERT INTO `apply_record` VALUES ('28', '100001', 'zhangsan', '2018-03-29 15:45:00', '2018-03-29 18:05:00', '1.5', '2018-03-29', '有事', '1', '0', null);
INSERT INTO `apply_record` VALUES ('29', '100001', 'zhangsan', '2018-04-03 09:00:00', '2018-04-03 18:00:00', '8.0', '2018-04-02', '生病', '1', '2', null);
INSERT INTO `apply_record` VALUES ('30', '100001', 'zhangsan', '2018-04-03 09:00:00', '2018-04-04 18:00:00', '16.0', '2018-04-02', '有事回家', '1', '0', null);
INSERT INTO `apply_record` VALUES ('31', '100001', 'zhangsan', '2018-04-03 09:00:00', '2018-04-04 18:00:00', '16.0', '2018-04-09', '发发发的说法都是', '1', '2', null);
INSERT INTO `apply_record` VALUES ('32', '100001', 'zhangsan', '2018-04-03 09:00:00', '2018-04-04 18:00:00', '16.0', '2018-04-02', 'youshoifsoahjofs', '1', '3', null);
INSERT INTO `apply_record` VALUES ('33', '100001', 'zhangsan', '2018-04-03 09:00:00', '2018-04-07 18:00:00', '40.0', '2018-04-02', '要加快国家建立科技', '1', '3', '去玩儿辅导辅导');
INSERT INTO `apply_record` VALUES ('34', '100006', 'zhangsanfeng', '2018-04-03 09:00:00', '2018-04-07 09:00:00', '32.0', '2018-04-02', '大DAd阿达的', '1', '0', null);
INSERT INTO `apply_record` VALUES ('35', '100006', 'zhangsanfeng', '2018-04-03 09:00:00', '2018-04-07 09:00:00', '32.0', '2018-04-02', '大DAd阿达的', '1', '0', null);
INSERT INTO `apply_record` VALUES ('36', '100006', 'zhangsanfeng', '2018-04-02 09:30:00', '2018-04-06 18:00:00', '32.0', '2018-04-02', '范德萨发所发生的', '1', '0', null);
INSERT INTO `apply_record` VALUES ('37', '100006', 'zhangsanfeng', '2018-04-03 09:30:00', '2018-04-07 18:00:00', '40.0', '2018-04-02', '案发反打法伤符', '1', '0', null);
INSERT INTO `apply_record` VALUES ('38', '100001', 'zhangsan', '2018-04-03 09:00:00', '2018-04-04 18:00:00', '8.0', '2018-04-09', '发发发的说法都是', '1', '2', null);
INSERT INTO `apply_record` VALUES ('39', '100001', 'zhangsan', '2018-04-03 09:00:00', '2018-04-04 18:00:00', '16.0', '2018-04-09', '发发发的说法都是', '1', '2', null);
INSERT INTO `apply_record` VALUES ('40', '100001', 'zhangsan', '2018-04-03 09:00:00', '2018-04-04 18:00:00', '25.0', '2018-04-09', '发发发的说法都是', '1', '0', null);
INSERT INTO `apply_record` VALUES ('41', '100001', 'zhangsan', '2018-04-03 09:00:00', '2018-04-04 18:00:00', '8.0', '2018-04-09', '发发发的说法都是', '1', '2', null);
INSERT INTO `apply_record` VALUES ('42', '100001', 'zhangsan', '2018-04-09 09:00:00', '2018-04-13 18:00:00', '32.0', '2018-04-09', '去玩儿', '1', '2', null);
INSERT INTO `apply_record` VALUES ('43', '100006', 'zhangsanfeng', '2018-04-09 09:00:00', '2018-04-13 18:00:00', '40.0', '2018-04-09', '有病', '1', '0', null);
INSERT INTO `apply_record` VALUES ('44', '100001', 'zhangsan', '2018-04-12 09:00:00', '2018-04-12 12:00:00', '3.0', '2018-04-11', '生病', '2', '2', null);
INSERT INTO `apply_record` VALUES ('45', '100001', 'zhangsan', '2018-04-11 13:30:00', '2018-04-13 18:00:00', '22.0', '2018-04-11', '回学校考试', '1', '0', null);
INSERT INTO `apply_record` VALUES ('46', '100001', 'zhangsan', '2018-04-12 09:00:00', '2018-04-16 09:00:00', '16.0', '2018-04-11', '有事回家', '2', '0', null);
INSERT INTO `apply_record` VALUES ('47', '100001', 'zhangsan', '2018-04-12 09:00:00', '2018-04-16 09:00:00', '16.0', '2018-04-11', '有事回家', '2', '0', null);
INSERT INTO `apply_record` VALUES ('48', '100001', 'zhangsan', '2018-04-12 09:00:00', '2018-04-18 09:00:00', '32.0', '2018-04-11', '加班太多了', '2', '2', null);
INSERT INTO `apply_record` VALUES ('49', '100001', 'zhangsan', '2018-04-12 09:00:00', '2018-04-18 09:00:00', '32.0', '2018-04-11', '手机号放假回家啊', '1', '0', null);
INSERT INTO `apply_record` VALUES ('50', '100001', 'zhangsan', '2018-04-12 18:00:00', '2018-04-13 22:00:00', '7.5', '2018-04-11', '工作紧急', '0', '1', null);
INSERT INTO `apply_record` VALUES ('51', '100001', 'zhangsan', '2018-04-12 18:00:00', '2018-04-12 22:00:00', '4.0', '2018-04-11', '工作紧急', '0', '0', null);
INSERT INTO `apply_record` VALUES ('52', '100001', 'zhangsan', '2018-04-12 09:00:00', '2018-04-13 18:00:00', '16.0', '2018-04-11', '加班太多了，调休一下', '2', '0', null);
INSERT INTO `apply_record` VALUES ('53', '100001', 'zhangsan', '2018-04-12 13:30:00', '2018-04-17 09:00:00', '20.0', '2018-04-12', 'qwerqwrqwwqrw', '1', '0', null);
INSERT INTO `apply_record` VALUES ('54', '100006', 'zhangsanfeng', '2018-04-12 10:26:00', '2018-04-12 18:00:00', '6.0', '2018-04-12', '有急事需走', '1', '0', null);
INSERT INTO `apply_record` VALUES ('55', '100001', 'zhangsan', '2018-04-12 18:00:00', '2018-04-12 23:30:00', '7.5', '2018-04-12', '未完成工作', '0', '0', null);
INSERT INTO `apply_record` VALUES ('56', '100001', 'zhangsan', '2018-04-13 09:00:00', '2018-04-13 18:00:00', '8.0', '2018-04-12', '全文', '1', '0', null);
INSERT INTO `apply_record` VALUES ('57', '100001', 'zhangsan', '2018-04-13 09:00:00', '2018-04-16 18:00:00', '16.0', '2018-04-12', '1231而且', '1', '0', null);
INSERT INTO `apply_record` VALUES ('58', '100001', 'zhangsan', '2018-04-13 18:00:00', '2018-04-14 02:00:00', '7.5', '2018-04-12', '太多工作要赶', '0', '0', null);
INSERT INTO `apply_record` VALUES ('59', '100001', 'zhangsan', '2018-04-12 18:00:00', '2018-04-12 21:30:00', '7.5', '2018-04-12', '赶进度', '0', '0', null);
INSERT INTO `apply_record` VALUES ('60', '100001', 'zhangsan', '2018-04-13 09:00:00', '2018-04-13 18:00:00', '8.0', '2018-04-12', 'qwer', '2', '2', null);
INSERT INTO `apply_record` VALUES ('61', '100001', 'zhangsan', '2018-04-13 09:00:00', '2018-04-18 18:00:00', '32.0', '2018-04-12', 'fffdsafdasfafafa价格将拉开高科技', '2', '0', null);
INSERT INTO `apply_record` VALUES ('62', '100001', 'zhangsan', '2018-04-16 09:00:00', '2018-04-20 18:00:00', '40.0', '2018-04-12', '县那个忙阿卡', '1', '0', null);
INSERT INTO `apply_record` VALUES ('63', '100001', 'zhangsan', '2018-04-12 14:57:00', '2018-04-16 14:57:00', '5.0', '2018-04-12', 'ajhjfajjkfajks', '0', '0', null);
INSERT INTO `apply_record` VALUES ('64', '100001', 'zhangsan', '2018-04-12 14:58:00', '2018-04-19 14:58:00', '18.0', '2018-04-12', 'affga发生发大水', '2', '0', null);
INSERT INTO `apply_record` VALUES ('65', '100001', 'zhangsan', '2018-04-12 15:00:00', '2018-04-17 15:00:00', '16.0', '2018-04-12', '去玩儿确认', '1', '0', null);
INSERT INTO `apply_record` VALUES ('66', '100000', '管理员', '2018-04-18 18:00:00', '2018-04-18 22:00:00', '4.0', '2018-04-18', '没完成任务', '0', '0', null);
INSERT INTO `apply_record` VALUES ('67', '100000', '管理员', '2018-04-18 11:25:00', '2018-04-18 12:25:00', '1.0', '2018-04-18', '没做完任务', '0', '0', null);
INSERT INTO `apply_record` VALUES ('68', '100001', 'zhangsan', '2018-04-18 11:31:00', '2018-04-18 12:31:00', '2.0', '2018-04-18', '没做完', '0', '0', null);
INSERT INTO `apply_record` VALUES ('69', '100000', '管理员', '2018-04-18 12:02:00', '2018-04-18 13:02:00', '1.0', '2018-04-18', '纳多一点', '0', '0', null);
INSERT INTO `apply_record` VALUES ('70', '100001', 'zhangsan', '2018-04-16 12:02:00', '2018-04-18 13:02:00', '2.0', '2018-04-18', '纳多一点', '0', '0', null);

-- ----------------------------
-- Table structure for customer_problem_picture
-- ----------------------------
DROP TABLE IF EXISTS `customer_problem_picture`;
CREATE TABLE `customer_problem_picture` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '问题图片id',
  `problem_id` bigint(20) NOT NULL COMMENT '问题表id',
  `pic_url` varchar(128) DEFAULT NULL COMMENT '图片url',
  `status` smallint(6) NOT NULL DEFAULT '1' COMMENT '状态（备用字段）',
  `description` varchar(512) DEFAULT NULL COMMENT '图片描述',
  `gmt_create` bigint(20) NOT NULL COMMENT '创建时间（时间戳）',
  `gmt_modified` bigint(20) DEFAULT NULL COMMENT '修改时间（时间戳）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of customer_problem_picture
-- ----------------------------
INSERT INTO `customer_problem_picture` VALUES ('1', '1', '/images/upload/09418bed-3760-48be-aee8-95317d29c296.png', '1', null, '1524031265434', null);
INSERT INTO `customer_problem_picture` VALUES ('2', '2', '/images/upload/22ecd235-8cf0-411f-acf6-638817336720.png', '1', null, '1524031378200', null);

-- ----------------------------
-- Table structure for customer_problem_solve
-- ----------------------------
DROP TABLE IF EXISTS `customer_problem_solve`;
CREATE TABLE `customer_problem_solve` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '处理表',
  `problem_id` bigint(20) unsigned NOT NULL COMMENT '问题表id',
  `description` varchar(512) DEFAULT NULL COMMENT '处理描述',
  `status` smallint(6) DEFAULT '1' COMMENT '状态（备用字段）',
  `gmt_create` bigint(20) NOT NULL COMMENT '创建时间（UNIX时间戳）',
  `gmt_modified` bigint(20) DEFAULT NULL COMMENT '修改时间（NIX时间戳）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of customer_problem_solve
-- ----------------------------
INSERT INTO `customer_problem_solve` VALUES ('1', '1', '问题处理1', '1', '1524031265439', null);
INSERT INTO `customer_problem_solve` VALUES ('2', '2', '问题处理2', '1', '1524031378242', null);

-- ----------------------------
-- Table structure for customer_problem_type
-- ----------------------------
DROP TABLE IF EXISTS `customer_problem_type`;
CREATE TABLE `customer_problem_type` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '问题类型id',
  `problem_type` varchar(32) NOT NULL COMMENT '问题类型',
  `status` smallint(6) NOT NULL DEFAULT '1' COMMENT '状态（备用字段）',
  `description` varchar(512) DEFAULT NULL COMMENT '类型描述',
  `gmt_create` bigint(20) unsigned NOT NULL DEFAULT '1522652032' COMMENT '创建时间',
  `gmt_modified` bigint(20) unsigned DEFAULT '1522652032' COMMENT '修改时间',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '上一级菜单id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=131 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of customer_problem_type
-- ----------------------------
INSERT INTO `customer_problem_type` VALUES ('1', '平台信息', '1', null, '1522652032', '1522652032', '0');
INSERT INTO `customer_problem_type` VALUES ('2', '安全性', '1', null, '1522652032', '1522652032', '1');
INSERT INTO `customer_problem_type` VALUES ('3', '平台资质', '1', null, '1522652032', '1522652032', '2');
INSERT INTO `customer_problem_type` VALUES ('4', '平台安全', '1', null, '1522652032', '1522652032', '2');
INSERT INTO `customer_problem_type` VALUES ('5', '平台活动', '1', null, '1522652032', '1522652032', '0');
INSERT INTO `customer_problem_type` VALUES ('6', '活动咨询', '1', null, '1522652032', '1522652032', '5');
INSERT INTO `customer_problem_type` VALUES ('7', '活动的奖励发放', '1', null, '1522652032', '1522652032', '6');
INSERT INTO `customer_problem_type` VALUES ('8', '新用户活动', '1', null, '1522652032', '1522652032', '6');
INSERT INTO `customer_problem_type` VALUES ('9', '老用户活动', '1', null, '1522652032', '1522652032', '6');
INSERT INTO `customer_problem_type` VALUES ('10', '体验金', '1', null, '1522652032', '1522652032', '5');
INSERT INTO `customer_problem_type` VALUES ('11', '如何获得资格', '1', null, '1522652032', '1522652032', '10');
INSERT INTO `customer_problem_type` VALUES ('12', '如何获得体验金', '1', null, '1522652032', '1522652032', '10');
INSERT INTO `customer_problem_type` VALUES ('13', '笑脸币', '1', null, '1522652032', '1522652032', '0');
INSERT INTO `customer_problem_type` VALUES ('14', '笑脸币领取', '1', null, '1522652032', '1522652032', '13');
INSERT INTO `customer_problem_type` VALUES ('15', '笑脸币兑换', '1', null, '1522652032', '1522652032', '13');
INSERT INTO `customer_problem_type` VALUES ('16', '领取失败', '1', null, '1522652032', '1522652032', '14');
INSERT INTO `customer_problem_type` VALUES ('17', '领取查询', '1', null, '1522652032', '1522652032', '14');
INSERT INTO `customer_problem_type` VALUES ('18', '兑换失败', '1', null, '1522652032', '1522652032', '15');
INSERT INTO `customer_problem_type` VALUES ('19', '兑换查询', '1', null, '1522652032', '1522652032', '15');
INSERT INTO `customer_problem_type` VALUES ('20', '合伙人', '1', null, '1522652032', '1522652032', '0');
INSERT INTO `customer_problem_type` VALUES ('21', '合伙人海报', '1', null, '1522652032', '1522652032', '20');
INSERT INTO `customer_problem_type` VALUES ('22', '合伙人关系', '1', null, '1522652032', '1522652032', '20');
INSERT INTO `customer_problem_type` VALUES ('23', '合伙人收益', '1', null, '1522652032', '1522652032', '20');
INSERT INTO `customer_problem_type` VALUES ('24', '如何生成', '1', null, '1522652032', '1522652032', '21');
INSERT INTO `customer_problem_type` VALUES ('25', '生成失败', '1', null, '1522652032', '1522652032', '21');
INSERT INTO `customer_problem_type` VALUES ('26', '关系查询', '1', null, '1522652032', '1522652032', '22');
INSERT INTO `customer_problem_type` VALUES ('27', '合伙人邀请失败', '1', null, '1522652032', '1522652032', '22');
INSERT INTO `customer_problem_type` VALUES ('28', '发放时间', '1', null, '1522652032', '1522652032', '23');
INSERT INTO `customer_problem_type` VALUES ('29', '计算方式', '1', null, '1522652032', '1522652032', '23');
INSERT INTO `customer_problem_type` VALUES ('30', 'APP相关问题', '1', null, '1522652032', '1522652032', '0');
INSERT INTO `customer_problem_type` VALUES ('31', 'APP界面显示', '1', null, '1522652032', '1522652032', '30');
INSERT INTO `customer_problem_type` VALUES ('32', '系统问题', '1', null, '1522652032', '1522652032', '30');
INSERT INTO `customer_problem_type` VALUES ('33', 'APP下载', '1', null, '1522652032', '1522652032', '30');
INSERT INTO `customer_problem_type` VALUES ('34', '名词变更', '1', null, '1522652032', '1522652032', '31');
INSERT INTO `customer_problem_type` VALUES ('35', '界面BUG', '1', null, '1522652032', '1522652032', '31');
INSERT INTO `customer_problem_type` VALUES ('36', '无法登陆', '1', null, '1522652032', '1522652032', '32');
INSERT INTO `customer_problem_type` VALUES ('37', '平台停服、维护', '1', null, '1522652032', '1522652032', '32');
INSERT INTO `customer_problem_type` VALUES ('38', '系统BUG', '1', null, '1522652032', '1522652032', '32');
INSERT INTO `customer_problem_type` VALUES ('39', 'ios发版问题', '1', null, '1522652032', '1522652032', '33');
INSERT INTO `customer_problem_type` VALUES ('40', '无法下载', '1', null, '1522652032', '1522652032', '33');
INSERT INTO `customer_problem_type` VALUES ('41', '注册', '1', null, '1522652032', '1522652032', '0');
INSERT INTO `customer_problem_type` VALUES ('42', '注册失败', '1', null, '1522652032', '1522652032', '41');
INSERT INTO `customer_problem_type` VALUES ('43', '注册渠道', '1', null, '1522652032', '1522652032', '41');
INSERT INTO `customer_problem_type` VALUES ('44', '身份信息重复', '1', null, '1522652032', '1522652032', '42');
INSERT INTO `customer_problem_type` VALUES ('45', '四要素不对', '1', null, '1522652032', '1522652032', '42');
INSERT INTO `customer_problem_type` VALUES ('46', '收不到验证码', '1', null, '1522652032', '1522652032', '42');
INSERT INTO `customer_problem_type` VALUES ('47', '合伙人邀请注册', '1', null, '1522652032', '1522652032', '43');
INSERT INTO `customer_problem_type` VALUES ('48', 'app、网站注册', '1', null, '1522652032', '1522652032', '43');
INSERT INTO `customer_problem_type` VALUES ('49', '账户', '1', null, '1522652032', '1522652032', '0');
INSERT INTO `customer_problem_type` VALUES ('50', '银行卡', '1', null, '1522652032', '1522652032', '49');
INSERT INTO `customer_problem_type` VALUES ('51', '变更手机号', '1', null, '1522652032', '1522652032', '49');
INSERT INTO `customer_problem_type` VALUES ('52', '账户', '1', null, '1522652032', '1522652032', '49');
INSERT INTO `customer_problem_type` VALUES ('53', '绑卡失败', '1', null, '1522652032', '1522652032', '50');
INSERT INTO `customer_problem_type` VALUES ('54', '解绑银行卡', '1', null, '1522652032', '1522652032', '50');
INSERT INTO `customer_problem_type` VALUES ('55', '换绑银行卡', '1', null, '1522652032', '1522652032', '50');
INSERT INTO `customer_problem_type` VALUES ('56', '银行预留手机号', '1', null, '1522652032', '1522652032', '51');
INSERT INTO `customer_problem_type` VALUES ('57', '注册手机号', '1', null, '1522652032', '1522652032', '51');
INSERT INTO `customer_problem_type` VALUES ('58', '合并账户', '1', null, '1522652032', '1522652032', '52');
INSERT INTO `customer_problem_type` VALUES ('59', '注销', '1', null, '1522652032', '1522652032', '52');
INSERT INTO `customer_problem_type` VALUES ('60', '登录密码', '1', null, '1522652032', '1522652032', '52');
INSERT INTO `customer_problem_type` VALUES ('61', '充值', '1', null, '1522652032', '1522652032', '0');
INSERT INTO `customer_problem_type` VALUES ('62', '充值失败', '1', null, '1522652032', '1522652032', '61');
INSERT INTO `customer_problem_type` VALUES ('63', '充未未到账', '1', null, '1522652032', '1522652032', '61');
INSERT INTO `customer_problem_type` VALUES ('64', '咨询充值', '1', null, '1522652032', '1522652032', '61');
INSERT INTO `customer_problem_type` VALUES ('65', '银行限额', '1', null, '1522652032', '1522652032', '62');
INSERT INTO `customer_problem_type` VALUES ('66', '未收到充值验证码', '1', null, '1522652032', '1522652032', '62');
INSERT INTO `customer_problem_type` VALUES ('67', '未开通银联在线支付功能', '1', null, '1522652032', '1522652032', '62');
INSERT INTO `customer_problem_type` VALUES ('68', '卡状态异常', '1', null, '1522652032', '1522652032', '62');
INSERT INTO `customer_problem_type` VALUES ('69', '操作超时', '1', null, '1522652032', '1522652032', '62');
INSERT INTO `customer_problem_type` VALUES ('70', '三方渠道系统延迟', '1', null, '1522652032', '1522652032', '63');
INSERT INTO `customer_problem_type` VALUES ('71', '咨询充值', '1', null, '1522652032', '1522652032', '64');
INSERT INTO `customer_problem_type` VALUES ('72', '投资', '1', null, '1522652032', '1522652032', '0');
INSERT INTO `customer_problem_type` VALUES ('73', '投资收益', '1', null, '1522652032', '1522652032', '72');
INSERT INTO `customer_problem_type` VALUES ('74', '债转资质', '1', null, '1522652032', '1522652032', '72');
INSERT INTO `customer_problem_type` VALUES ('75', '标的问题', '1', null, '1522652032', '1522652032', '72');
INSERT INTO `customer_problem_type` VALUES ('76', '投资证明', '1', null, '1522652032', '1522652032', '72');
INSERT INTO `customer_problem_type` VALUES ('77', '投资咨询', '1', null, '1522652032', '1522652032', '72');
INSERT INTO `customer_problem_type` VALUES ('78', '红包使用问题', '1', null, '1522652032', '1522652032', '73');
INSERT INTO `customer_problem_type` VALUES ('79', '对冲资金', '1', null, '1522652032', '1522652032', '73');
INSERT INTO `customer_problem_type` VALUES ('80', '计算方式', '1', null, '1522652032', '1522652032', '73');
INSERT INTO `customer_problem_type` VALUES ('81', '会员加息及特权', '1', null, '1522652032', '1522652032', '73');
INSERT INTO `customer_problem_type` VALUES ('82', '债券转让', '1', null, '1522652032', '1522652032', '74');
INSERT INTO `customer_problem_type` VALUES ('83', '售罄', '1', null, '1522652032', '1522652032', '75');
INSERT INTO `customer_problem_type` VALUES ('84', '上线时间', '1', null, '1522652032', '1522652032', '75');
INSERT INTO `customer_problem_type` VALUES ('85', '投资证明', '1', null, '1522652032', '1522652032', '85');
INSERT INTO `customer_problem_type` VALUES ('86', '投资流程', '1', null, '1522652032', '1522652032', '77');
INSERT INTO `customer_problem_type` VALUES ('87', '回款', '1', null, '1522652032', '1522652032', '0');
INSERT INTO `customer_problem_type` VALUES ('88', '回款未到账', '1', null, '1522652032', '1522652032', '87');
INSERT INTO `customer_problem_type` VALUES ('89', '回款咨询', '1', null, '1522652032', '1522652032', '87');
INSERT INTO `customer_problem_type` VALUES ('90', '提前回款', '1', null, '1522652032', '1522652032', '87');
INSERT INTO `customer_problem_type` VALUES ('91', '代偿', '1', null, '1522652032', '1522652032', '88');
INSERT INTO `customer_problem_type` VALUES ('92', '回款时间', '1', null, '1522652032', '1522652032', '89');
INSERT INTO `customer_problem_type` VALUES ('93', '回款金额', '1', null, '1522652032', '1522652032', '89');
INSERT INTO `customer_problem_type` VALUES ('94', '清退', '1', null, '1522652032', '1522652032', '90');
INSERT INTO `customer_problem_type` VALUES ('95', '借款人提前回款', '1', null, '1522652032', '1522652032', '90');
INSERT INTO `customer_problem_type` VALUES ('96', '部分回款', '1', null, '1522652032', '1522652032', '90');
INSERT INTO `customer_problem_type` VALUES ('97', '提现', '1', null, '1522652032', '1522652032', '0');
INSERT INTO `customer_problem_type` VALUES ('98', '提现失败', '1', null, '1522652032', '1522652032', '97');
INSERT INTO `customer_problem_type` VALUES ('99', '提现未到账', '1', null, '1522652032', '1522652032', '97');
INSERT INTO `customer_problem_type` VALUES ('100', '提现退回', '1', null, '1522652032', '1522652032', '98');
INSERT INTO `customer_problem_type` VALUES ('101', '银行卡限额', '1', null, '1522652032', '1522652032', '98');
INSERT INTO `customer_problem_type` VALUES ('102', '未收到提现验证码', '1', null, '1522652032', '1522652032', '98');
INSERT INTO `customer_problem_type` VALUES ('103', '操作超时', '1', null, '1522652032', '1522652032', '98');
INSERT INTO `customer_problem_type` VALUES ('104', '三方渠道系统延迟', '1', null, '1522652032', '1522652032', '99');
INSERT INTO `customer_problem_type` VALUES ('105', '提现时效', '1', null, '1522652032', '1522652032', '99');
INSERT INTO `customer_problem_type` VALUES ('106', '特殊情况', '1', null, '1522652032', '1522652032', '0');
INSERT INTO `customer_problem_type` VALUES ('107', '无效', '1', null, '1522652032', '1522652032', '106');
INSERT INTO `customer_problem_type` VALUES ('108', '其他', '1', null, '1522652032', '1522652032', '106');
INSERT INTO `customer_problem_type` VALUES ('109', '投诉', '1', null, '1522652032', '1522652032', '106');
INSERT INTO `customer_problem_type` VALUES ('110', '无效', '1', null, '1522652032', '1522652032', '107');
INSERT INTO `customer_problem_type` VALUES ('111', '其他（具体问题下方备注）', '1', null, '1522652032', '1522652032', '108');
INSERT INTO `customer_problem_type` VALUES ('112', '业务', '1', null, '1522652032', '1522652032', '109');
INSERT INTO `customer_problem_type` VALUES ('113', '坐席', '1', null, '1522652032', '1522652032', '109');
INSERT INTO `customer_problem_type` VALUES ('114', '用户反馈', '1', null, '1522652032', '1522652032', '0');
INSERT INTO `customer_problem_type` VALUES ('115', '意见、建议', '1', null, '1522652032', '1522652032', '114');
INSERT INTO `customer_problem_type` VALUES ('116', '推广合作', '1', null, '1522652032', '1522652032', '114');
INSERT INTO `customer_problem_type` VALUES ('117', '产品', '1', null, '1522652032', '1522652032', '115');
INSERT INTO `customer_problem_type` VALUES ('118', '用户体验', '1', null, '1522652032', '1522652032', '115');
INSERT INTO `customer_problem_type` VALUES ('119', '推广合作（具体备注）', '1', null, '1522652032', '1522652032', '116');
INSERT INTO `customer_problem_type` VALUES ('120', '微信公众号', '1', null, '1522652032', '1522652032', '0');
INSERT INTO `customer_problem_type` VALUES ('121', '签到', '1', null, '1522652032', '1522652032', '120');
INSERT INTO `customer_problem_type` VALUES ('122', '账户绑定、解绑', '1', null, '1522652032', '1522652032', '120');
INSERT INTO `customer_problem_type` VALUES ('123', '信息咨询', '1', null, '1522652032', '1522652032', '120');
INSERT INTO `customer_problem_type` VALUES ('124', '签到奖励计算', '1', null, '1522652032', '1522652032', '121');
INSERT INTO `customer_problem_type` VALUES ('125', '签到未到账', '1', null, '1522652032', '1522652032', '121');
INSERT INTO `customer_problem_type` VALUES ('126', '签到流程', '1', null, '1522652032', '1522652032', '121');
INSERT INTO `customer_problem_type` VALUES ('127', '签到失败', '1', null, '1522652032', '1522652032', '121');
INSERT INTO `customer_problem_type` VALUES ('128', '绑定相关问题', '1', null, '1522652032', '1522652032', '122');
INSERT INTO `customer_problem_type` VALUES ('129', '解绑相关问题', '1', null, '1522652032', '1522652032', '122');
INSERT INTO `customer_problem_type` VALUES ('130', '信息咨询', '1', null, '1522652032', '1522652032', '123');

-- ----------------------------
-- Table structure for customer_service_journal
-- ----------------------------
DROP TABLE IF EXISTS `customer_service_journal`;
CREATE TABLE `customer_service_journal` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '流水表id',
  `service_id` bigint(20) NOT NULL COMMENT '服务id',
  `worker_number` int(11) unsigned NOT NULL COMMENT '致电客服人员的工号',
  `name` varchar(16) NOT NULL COMMENT '客户姓名',
  `phone_number` varchar(16) DEFAULT NULL COMMENT '致电客户号码',
  `type_id` int(2) DEFAULT '0' COMMENT '服务类型（预留）',
  `note` varchar(512) DEFAULT NULL COMMENT '备注',
  `begin_time` bigint(20) NOT NULL COMMENT '通话开始时间（时间戳）',
  `end_time` bigint(20) NOT NULL COMMENT '通话结束时间（时间戳）',
  `gmt_create` bigint(20) NOT NULL COMMENT '创建时间（时间戳）',
  `gmt_modified` bigint(20) DEFAULT NULL COMMENT '修改时间（时间戳）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of customer_service_journal
-- ----------------------------
INSERT INTO `customer_service_journal` VALUES ('1', '1', '100000', '张宁奎', null, '0', null, '1524031265256', '1524031265258', '1524031265411', null);
INSERT INTO `customer_service_journal` VALUES ('2', '1', '100000', '张宁奎', null, '0', null, '0', '0', '1524031315043', '1524031315043');
INSERT INTO `customer_service_journal` VALUES ('3', '2', '100000', '张三', null, '0', null, '1524031377986', '1524031377986', '1524031378079', null);

-- ----------------------------
-- Table structure for customer_service_phone_record
-- ----------------------------
DROP TABLE IF EXISTS `customer_service_phone_record`;
CREATE TABLE `customer_service_phone_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '客户来电记录编号',
  `service_no` bigint(20) NOT NULL COMMENT '服务单号',
  `phone_type` varchar(64) DEFAULT NULL COMMENT '手机类型',
  `phone_number` varchar(16) NOT NULL COMMENT '致电客户号码',
  `name` varchar(16) DEFAULT NULL COMMENT '客户姓名',
  `type_id` int(2) DEFAULT '1' COMMENT '预留（服务类型）',
  `worker_number` int(11) NOT NULL COMMENT '接听客服人员的工号',
  `is_satisfied` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否满意（0-默认，1-不满意，2满意）',
  `user_comment` int(2) DEFAULT NULL COMMENT '客户评价（0-10分）',
  `comment_content` varchar(512) DEFAULT NULL COMMENT '用户评价描述',
  `status` int(6) NOT NULL DEFAULT '0' COMMENT '状态（0-草稿，1-待处理，2-已处理，3-待确认，4-待评价，5-完成服务）',
  `note` varchar(512) DEFAULT NULL COMMENT '备注',
  `gmt_create` bigint(20) NOT NULL COMMENT '用户来电开始时间',
  `gmt_modified` bigint(20) DEFAULT NULL COMMENT '用户来电结束时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of customer_service_phone_record
-- ----------------------------
INSERT INTO `customer_service_phone_record` VALUES ('1', '6261201804181404403', '', '13421519977', '张宁奎', '70', '100000', '0', null, null, '2', null, '1524031315035', '1524031315035');
INSERT INTO `customer_service_phone_record` VALUES ('2', '518420180418140473', null, '18814384402', '张三', '26', '100000', '0', null, null, '1', null, '1524031378073', '1524031378073');

-- ----------------------------
-- Table structure for customer_service_problem
-- ----------------------------
DROP TABLE IF EXISTS `customer_service_problem`;
CREATE TABLE `customer_service_problem` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '问题id',
  `service_id` bigint(20) NOT NULL COMMENT '服务id',
  `type_id` bigint(20) NOT NULL COMMENT '类型id',
  `title` varchar(16) NOT NULL COMMENT '小标题',
  `gmt_create` bigint(20) unsigned NOT NULL COMMENT '创建时间（UNIX时间戳）',
  `gmt_modified` bigint(20) DEFAULT NULL COMMENT '修改时间（NIX时间戳）',
  `note` varchar(512) DEFAULT NULL COMMENT '备注',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '（预留状态）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of customer_service_problem
-- ----------------------------
INSERT INTO `customer_service_problem` VALUES ('1', '1', '70', '问题标题1', '1524031265417', '1524031315046', null, '1');
INSERT INTO `customer_service_problem` VALUES ('2', '2', '26', '问题标题2', '1524031378137', '1524031378137', null, '1');

-- ----------------------------
-- Table structure for customer_service_problem_description
-- ----------------------------
DROP TABLE IF EXISTS `customer_service_problem_description`;
CREATE TABLE `customer_service_problem_description` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '问题描述id',
  `problem_id` bigint(20) NOT NULL COMMENT '问题id',
  `description` varchar(512) DEFAULT NULL COMMENT '问题描述',
  `status` smallint(6) NOT NULL DEFAULT '1' COMMENT '（预留状态）',
  `gmt_create` bigint(20) unsigned NOT NULL COMMENT '创建时间',
  `gmt_modified` bigint(20) unsigned DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of customer_service_problem_description
-- ----------------------------
INSERT INTO `customer_service_problem_description` VALUES ('1', '1', '问题详情1', '1', '1524031265425', '1524031315050');
INSERT INTO `customer_service_problem_description` VALUES ('2', '2', '问题详情2', '1', '1524031378143', null);

-- ----------------------------
-- Table structure for deal_record
-- ----------------------------
DROP TABLE IF EXISTS `deal_record`;
CREATE TABLE `deal_record` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '审核记录编号',
  `apply_id` bigint(20) unsigned NOT NULL COMMENT '申请记录编号',
  `work_number` int(16) NOT NULL COMMENT '审核人员工号',
  `deal_level` int(2) NOT NULL COMMENT '审核顺序',
  `deal_time` datetime DEFAULT NULL COMMENT '审核时间',
  `status` int(2) DEFAULT '0' COMMENT '状态（0：新建，1：待审核，2：审核通过，3，审核不通过）',
  `notpass_cause` varchar(128) DEFAULT NULL COMMENT '未通过原因',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8 COMMENT='申请处理记录表';

-- ----------------------------
-- Records of deal_record
-- ----------------------------
INSERT INTO `deal_record` VALUES ('1', '29', '100006', '1', '2018-04-09 11:55:13', '2', null);
INSERT INTO `deal_record` VALUES ('6', '32', '100006', '1', null, '1', '');
INSERT INTO `deal_record` VALUES ('7', '32', '100007', '2', null, '0', null);
INSERT INTO `deal_record` VALUES ('8', '33', '100006', '1', '2018-04-09 14:28:29', '2', '去玩儿辅导辅导');
INSERT INTO `deal_record` VALUES ('9', '33', '100007', '2', null, '0', null);
INSERT INTO `deal_record` VALUES ('10', '33', '100009', '3', null, '0', null);
INSERT INTO `deal_record` VALUES ('11', '34', '100007', '1', null, '1', null);
INSERT INTO `deal_record` VALUES ('12', '34', '100009', '2', null, '1', null);
INSERT INTO `deal_record` VALUES ('13', '35', '100007', '1', null, '1', null);
INSERT INTO `deal_record` VALUES ('14', '35', '100009', '2', null, '1', null);
INSERT INTO `deal_record` VALUES ('15', '36', '100007', '1', null, '1', null);
INSERT INTO `deal_record` VALUES ('16', '36', '100009', '2', null, '0', null);
INSERT INTO `deal_record` VALUES ('17', '37', '100007', '1', null, '1', null);
INSERT INTO `deal_record` VALUES ('18', '37', '100009', '2', null, '0', null);
INSERT INTO `deal_record` VALUES ('19', '38', '100006', '1', '2018-04-10 15:43:10', '2', null);
INSERT INTO `deal_record` VALUES ('20', '39', '100006', '1', '2018-04-11 17:43:22', '2', null);
INSERT INTO `deal_record` VALUES ('21', '39', '100007', '2', '2018-04-11 17:44:58', '2', null);
INSERT INTO `deal_record` VALUES ('27', '31', '100006', '1', '2018-04-10 15:47:45', '2', null);
INSERT INTO `deal_record` VALUES ('28', '31', '100007', '2', '2018-04-11 17:44:56', '2', null);
INSERT INTO `deal_record` VALUES ('29', '41', '100006', '1', '2018-04-10 15:49:05', '2', null);
INSERT INTO `deal_record` VALUES ('30', '40', '100006', '1', '2018-04-10 15:44:41', '2', null);
INSERT INTO `deal_record` VALUES ('31', '40', '100007', '2', null, '1', null);
INSERT INTO `deal_record` VALUES ('32', '40', '100009', '3', null, '0', null);
INSERT INTO `deal_record` VALUES ('33', '42', '100006', '1', '2018-04-09 16:23:06', '2', null);
INSERT INTO `deal_record` VALUES ('34', '42', '100007', '2', '2018-04-09 16:23:54', '2', null);
INSERT INTO `deal_record` VALUES ('35', '42', '100009', '3', '2018-04-09 16:25:06', '2', null);
INSERT INTO `deal_record` VALUES ('36', '43', '100007', '1', null, '1', null);
INSERT INTO `deal_record` VALUES ('37', '43', '100009', '2', null, '0', null);
INSERT INTO `deal_record` VALUES ('38', '44', '100006', '1', '2018-04-11 14:19:46', '2', null);
INSERT INTO `deal_record` VALUES ('39', '45', '100006', '1', null, '1', null);
INSERT INTO `deal_record` VALUES ('40', '45', '100007', '2', null, '0', null);
INSERT INTO `deal_record` VALUES ('42', '47', '100006', '1', null, '1', null);
INSERT INTO `deal_record` VALUES ('43', '47', '100007', '2', null, '1', null);
INSERT INTO `deal_record` VALUES ('44', '48', '100006', '1', '2018-04-11 14:53:12', '2', null);
INSERT INTO `deal_record` VALUES ('45', '48', '100007', '2', '2018-04-11 14:56:22', '2', null);
INSERT INTO `deal_record` VALUES ('49', '49', '100006', '1', null, '1', null);
INSERT INTO `deal_record` VALUES ('50', '49', '100007', '2', null, '0', null);
INSERT INTO `deal_record` VALUES ('51', '49', '100009', '3', null, '0', null);
INSERT INTO `deal_record` VALUES ('52', '50', '100006', '1', '2018-04-12 14:25:57', '2', null);
INSERT INTO `deal_record` VALUES ('53', '50', '100007', '2', null, '1', null);
INSERT INTO `deal_record` VALUES ('54', '51', '100006', '1', null, '1', null);
INSERT INTO `deal_record` VALUES ('55', '51', '100007', '2', null, '0', null);
INSERT INTO `deal_record` VALUES ('58', '46', '100006', '1', null, '0', null);
INSERT INTO `deal_record` VALUES ('59', '46', '100007', '2', null, '0', null);
INSERT INTO `deal_record` VALUES ('60', '53', '100006', '1', null, '1', null);
INSERT INTO `deal_record` VALUES ('61', '53', '100007', '2', null, '0', null);
INSERT INTO `deal_record` VALUES ('62', '54', '100007', '1', null, '1', null);
INSERT INTO `deal_record` VALUES ('65', '57', '100006', '1', null, '1', null);
INSERT INTO `deal_record` VALUES ('66', '57', '100007', '2', null, '0', null);
INSERT INTO `deal_record` VALUES ('69', '58', '100006', '1', null, '1', null);
INSERT INTO `deal_record` VALUES ('70', '58', '100007', '2', null, '0', null);
INSERT INTO `deal_record` VALUES ('71', '58', '100009', '3', null, '0', null);
INSERT INTO `deal_record` VALUES ('74', '60', '100006', '1', '2018-04-12 14:27:03', '2', null);
INSERT INTO `deal_record` VALUES ('77', '61', '100006', '1', null, '1', null);
INSERT INTO `deal_record` VALUES ('78', '61', '100007', '2', null, '0', null);
INSERT INTO `deal_record` VALUES ('79', '61', '100009', '3', null, '0', null);
INSERT INTO `deal_record` VALUES ('80', '62', '100006', '1', '2018-04-12 14:08:54', '2', null);
INSERT INTO `deal_record` VALUES ('81', '62', '100007', '2', null, '1', null);
INSERT INTO `deal_record` VALUES ('82', '62', '100009', '3', null, '0', null);
INSERT INTO `deal_record` VALUES ('83', '56', '100006', '1', null, '1', null);
INSERT INTO `deal_record` VALUES ('86', '59', '100006', '1', null, '1', null);
INSERT INTO `deal_record` VALUES ('87', '59', '100007', '2', null, '0', null);
INSERT INTO `deal_record` VALUES ('88', '59', '100009', '3', null, '0', null);
INSERT INTO `deal_record` VALUES ('89', '63', '100006', '1', null, '1', null);
INSERT INTO `deal_record` VALUES ('90', '63', '100007', '2', null, '0', null);
INSERT INTO `deal_record` VALUES ('94', '64', '100006', '1', null, '1', null);
INSERT INTO `deal_record` VALUES ('95', '64', '100007', '2', null, '0', null);
INSERT INTO `deal_record` VALUES ('99', '65', '100006', '1', null, '1', null);
INSERT INTO `deal_record` VALUES ('100', '65', '100007', '2', null, '0', null);
INSERT INTO `deal_record` VALUES ('101', '68', '100006', '1', null, '1', null);
INSERT INTO `deal_record` VALUES ('102', '70', '100006', '1', null, '1', null);

-- ----------------------------
-- Table structure for dept
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `dept_number` int(16) NOT NULL COMMENT '部门号',
  `dept_name` varchar(16) NOT NULL COMMENT '部门名称',
  `parent_number` int(16) DEFAULT NULL COMMENT '上级部门号',
  `gmt_create` bigint(32) unsigned NOT NULL COMMENT '创建时间',
  `gmt_update` bigint(32) NOT NULL COMMENT '修改时间',
  `status` int(2) NOT NULL DEFAULT '0' COMMENT '状态（0：正常，1：停用）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='部门表';

-- ----------------------------
-- Records of dept
-- ----------------------------
INSERT INTO `dept` VALUES ('1', '10001', '技术部', null, '0', '0', '0');
INSERT INTO `dept` VALUES ('2', '10001', '人事部', null, '0', '0', '0');

-- ----------------------------
-- Table structure for emp
-- ----------------------------
DROP TABLE IF EXISTS `emp`;
CREATE TABLE `emp` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `work_number` int(16) NOT NULL COMMENT '员工号',
  `name` varchar(16) NOT NULL COMMENT '员工姓名',
  `telephone` varchar(11) NOT NULL COMMENT '手机号',
  `email` varchar(32) NOT NULL COMMENT '邮箱',
  `dept_number` int(16) DEFAULT NULL COMMENT '所属部门号',
  `overtime_duration` float(4,1) NOT NULL DEFAULT '0.0' COMMENT '加班总时长（单位：小时）',
  `rest_duration` float(4,1) NOT NULL DEFAULT '0.0' COMMENT '调休总时长（单位：小时）',
  `leave_duration` float(4,1) NOT NULL DEFAULT '0.0' COMMENT '请假总时长（单位：小时）',
  `parent_number` int(16) DEFAULT NULL COMMENT '上级员工号',
  `gmt_create` bigint(32) unsigned NOT NULL COMMENT '创建时间',
  `gmt_update` bigint(32) unsigned NOT NULL COMMENT '修改时间',
  `status` int(2) NOT NULL DEFAULT '0' COMMENT '状态（0：正常，1：停用）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='员工表';

-- ----------------------------
-- Records of emp
-- ----------------------------
INSERT INTO `emp` VALUES ('1', '100000', '管理员', '18814384402', '100001@facebank.cn', null, '0.0', '0.0', '0.0', null, '0', '0', '0');
INSERT INTO `emp` VALUES ('2', '100001', 'zhangsan', '18814384685', '100001@facebank.com', null, '0.0', '40.0', '51.0', '100006', '0', '0', '0');
INSERT INTO `emp` VALUES ('3', '100006', 'zhangsanfeng', '18814384565', '100006@facebank.cn', null, '0.0', '0.0', '0.0', '100007', '0', '0', '0');
INSERT INTO `emp` VALUES ('4', '100007', 'zhangsanfengya', '18814386545', '100007@facebank.cn', null, '0.0', '0.0', '0.0', '100009', '0', '0', '0');
INSERT INTO `emp` VALUES ('5', '100009', 'zhangsanyu', '18814386555', '100009@facebank.cn', null, '0.0', '0.0', '0.0', '100000', '0', '0', '0');
INSERT INTO `emp` VALUES ('6', '89', '杨海龙', '18814383222', '100000@facebank.com', '10001', '22.0', '33.0', '44.0', '100000', '0', '0', '0');
INSERT INTO `emp` VALUES ('7', '29', '王茂青', '18814383266', '100949@facebank.com', '10001', '66.0', '34.0', '12.0', '100000', '0', '0', '0');
INSERT INTO `emp` VALUES ('8', '61', '王晓东', '18814384377', '100044@facebank.com', '10001', '0.0', '0.0', '0.0', '100000', '0', '0', '0');

-- ----------------------------
-- Table structure for emp_attendance
-- ----------------------------
DROP TABLE IF EXISTS `emp_attendance`;
CREATE TABLE `emp_attendance` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '员工考勤记录编号',
  `work_number` int(16) NOT NULL COMMENT '员工号',
  `emp_name` varchar(16) NOT NULL COMMENT '员工姓名',
  `dept_name` varchar(16) DEFAULT NULL COMMENT '所属部门',
  `attendance_date` date NOT NULL COMMENT '考勤日期',
  `start_time` time DEFAULT NULL COMMENT '上班打卡时间',
  `end_time` time DEFAULT NULL COMMENT '下班打卡时间',
  `status` int(2) NOT NULL DEFAULT '0' COMMENT '状态（0：正常，1：迟到，2：早退，3：迟到早退，4：旷工，5：异常，6：请假，7：调休）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=416 DEFAULT CHARSET=utf8 COMMENT='考勤表';

-- ----------------------------
-- Records of emp_attendance
-- ----------------------------
INSERT INTO `emp_attendance` VALUES ('298', '29', '王茂青', '技术部', '2018-01-02', '09:56:00', '19:45:00', '1');
INSERT INTO `emp_attendance` VALUES ('299', '29', '王茂青', '技术部', '2018-01-03', '10:13:00', '19:32:00', '1');
INSERT INTO `emp_attendance` VALUES ('300', '29', '王茂青', '技术部', '2018-01-04', '10:04:00', null, '5');
INSERT INTO `emp_attendance` VALUES ('301', '29', '王茂青', '技术部', '2018-01-05', '00:25:00', '22:13:00', '0');
INSERT INTO `emp_attendance` VALUES ('302', '29', '王茂青', '技术部', '2018-01-08', '09:59:00', '19:22:00', '1');
INSERT INTO `emp_attendance` VALUES ('303', '29', '王茂青', '技术部', '2018-01-09', '13:41:00', '19:35:00', '1');
INSERT INTO `emp_attendance` VALUES ('304', '29', '王茂青', '技术部', '2018-01-10', '10:06:00', '19:27:00', '1');
INSERT INTO `emp_attendance` VALUES ('305', '29', '王茂青', '技术部', '2018-01-11', '10:04:00', '19:32:00', '1');
INSERT INTO `emp_attendance` VALUES ('306', '29', '王茂青', '技术部', '2018-01-12', '10:02:00', '19:14:00', '1');
INSERT INTO `emp_attendance` VALUES ('307', '29', '王茂青', '技术部', '2018-01-15', '09:45:00', null, '5');
INSERT INTO `emp_attendance` VALUES ('308', '29', '王茂青', '技术部', '2018-01-16', '04:07:00', null, '5');
INSERT INTO `emp_attendance` VALUES ('309', '29', '王茂青', '技术部', '2018-01-17', '09:51:00', '19:15:00', '1');
INSERT INTO `emp_attendance` VALUES ('310', '29', '王茂青', '技术部', '2018-01-18', '19:43:00', null, '5');
INSERT INTO `emp_attendance` VALUES ('311', '29', '王茂青', '技术部', '2018-01-19', '09:59:00', '19:14:00', '1');
INSERT INTO `emp_attendance` VALUES ('312', '29', '王茂青', '技术部', '2018-01-22', '09:57:00', '22:02:00', '1');
INSERT INTO `emp_attendance` VALUES ('313', '29', '王茂青', '技术部', '2018-01-23', '10:24:00', '21:07:00', '1');
INSERT INTO `emp_attendance` VALUES ('314', '29', '王茂青', '技术部', '2018-01-24', '11:38:00', '20:30:00', '1');
INSERT INTO `emp_attendance` VALUES ('315', '29', '王茂青', '技术部', '2018-01-25', '08:55:00', '18:12:00', '0');
INSERT INTO `emp_attendance` VALUES ('316', '29', '王茂青', '技术部', '2018-01-26', '10:13:00', '20:04:00', '1');
INSERT INTO `emp_attendance` VALUES ('317', '29', '王茂青', '技术部', '2018-01-29', '10:00:00', '15:42:00', '3');
INSERT INTO `emp_attendance` VALUES ('318', '29', '王茂青', '技术部', '2018-01-31', '09:44:00', '20:44:00', '1');
INSERT INTO `emp_attendance` VALUES ('319', '61', '王晓东', '技术部', '2018-01-02', '08:54:00', '19:42:00', '0');
INSERT INTO `emp_attendance` VALUES ('320', '61', '王晓东', '技术部', '2018-01-03', '08:39:00', '19:58:00', '0');
INSERT INTO `emp_attendance` VALUES ('321', '61', '王晓东', '技术部', '2018-01-04', '08:45:00', '19:00:00', '0');
INSERT INTO `emp_attendance` VALUES ('322', '61', '王晓东', '技术部', '2018-01-06', '13:02:00', null, '5');
INSERT INTO `emp_attendance` VALUES ('323', '61', '王晓东', '技术部', '2018-01-08', '08:46:00', '20:06:00', '0');
INSERT INTO `emp_attendance` VALUES ('324', '61', '王晓东', '技术部', '2018-01-09', '08:59:00', '19:55:00', '0');
INSERT INTO `emp_attendance` VALUES ('325', '61', '王晓东', '技术部', '2018-01-17', '08:38:00', '20:18:00', '0');
INSERT INTO `emp_attendance` VALUES ('326', '61', '王晓东', '技术部', '2018-01-18', '09:01:00', '19:54:00', '1');
INSERT INTO `emp_attendance` VALUES ('327', '61', '王晓东', '技术部', '2018-01-22', '09:00:00', '14:34:00', '2');
INSERT INTO `emp_attendance` VALUES ('328', '61', '王晓东', '技术部', '2018-01-23', '08:40:00', '20:20:00', '0');
INSERT INTO `emp_attendance` VALUES ('329', '61', '王晓东', '技术部', '2018-01-24', '09:03:00', '19:44:00', '1');
INSERT INTO `emp_attendance` VALUES ('330', '61', '王晓东', '技术部', '2018-01-25', '09:40:00', '20:04:00', '1');
INSERT INTO `emp_attendance` VALUES ('331', '61', '王晓东', '技术部', '2018-01-26', '09:46:00', '20:36:00', '1');
INSERT INTO `emp_attendance` VALUES ('332', '61', '王晓东', '技术部', '2018-01-30', '09:38:00', '19:00:00', '1');
INSERT INTO `emp_attendance` VALUES ('333', '61', '王晓东', '技术部', '2018-01-31', '09:48:00', '19:00:00', '1');
INSERT INTO `emp_attendance` VALUES ('334', '89', '杨海龙', '技术部', '2018-01-02', '09:45:00', '19:43:00', '1');
INSERT INTO `emp_attendance` VALUES ('335', '89', '杨海龙', '技术部', '2018-01-03', '13:06:00', '20:32:00', '1');
INSERT INTO `emp_attendance` VALUES ('336', '89', '杨海龙', '技术部', '2018-01-04', '09:27:00', '19:00:00', '1');
INSERT INTO `emp_attendance` VALUES ('337', '89', '杨海龙', '技术部', '2018-01-08', '09:51:00', '19:28:00', '1');
INSERT INTO `emp_attendance` VALUES ('338', '89', '杨海龙', '技术部', '2018-01-09', '09:46:00', '19:19:00', '1');
INSERT INTO `emp_attendance` VALUES ('339', '89', '杨海龙', '技术部', '2018-01-10', '09:44:00', '21:11:00', '1');
INSERT INTO `emp_attendance` VALUES ('340', '89', '杨海龙', '技术部', '2018-01-11', '09:37:00', '20:39:00', '1');
INSERT INTO `emp_attendance` VALUES ('341', '89', '杨海龙', '技术部', '2018-01-12', '09:44:00', '21:10:00', '1');
INSERT INTO `emp_attendance` VALUES ('342', '89', '杨海龙', '技术部', '2018-01-15', '09:34:00', '19:03:00', '1');
INSERT INTO `emp_attendance` VALUES ('343', '89', '杨海龙', '技术部', '2018-01-16', '09:20:00', '20:38:00', '1');
INSERT INTO `emp_attendance` VALUES ('344', '89', '杨海龙', '技术部', '2018-01-17', '09:35:00', '19:45:00', '1');
INSERT INTO `emp_attendance` VALUES ('345', '89', '杨海龙', '技术部', '2018-01-18', '09:49:00', '19:16:00', '1');
INSERT INTO `emp_attendance` VALUES ('346', '89', '杨海龙', '技术部', '2018-01-19', '08:22:00', '19:38:00', '0');
INSERT INTO `emp_attendance` VALUES ('347', '89', '杨海龙', '技术部', '2018-01-20', '08:27:00', '18:19:00', '0');
INSERT INTO `emp_attendance` VALUES ('348', '89', '杨海龙', '技术部', '2018-01-21', '09:51:00', '19:23:00', '1');
INSERT INTO `emp_attendance` VALUES ('349', '89', '杨海龙', '技术部', '2018-01-22', '09:41:00', '21:03:00', '1');
INSERT INTO `emp_attendance` VALUES ('350', '89', '杨海龙', '技术部', '2018-01-23', '09:37:00', '19:00:00', '1');
INSERT INTO `emp_attendance` VALUES ('351', '89', '杨海龙', '技术部', '2018-01-24', '04:58:00', '17:00:00', '2');
INSERT INTO `emp_attendance` VALUES ('352', '89', '杨海龙', '技术部', '2018-01-25', '09:41:00', '20:24:00', '1');
INSERT INTO `emp_attendance` VALUES ('353', '89', '杨海龙', '技术部', '2018-01-26', '09:31:00', '19:00:00', '1');
INSERT INTO `emp_attendance` VALUES ('354', '89', '杨海龙', '技术部', '2018-01-29', '09:41:00', '19:09:00', '1');
INSERT INTO `emp_attendance` VALUES ('355', '89', '杨海龙', '技术部', '2018-01-30', '08:16:00', '19:20:00', '0');
INSERT INTO `emp_attendance` VALUES ('356', '89', '杨海龙', '技术部', '2018-01-31', '09:33:00', '19:01:00', '1');
INSERT INTO `emp_attendance` VALUES ('357', '29', '王茂青', '技术部', '2018-03-02', '09:56:00', '19:45:00', '1');
INSERT INTO `emp_attendance` VALUES ('358', '29', '王茂青', '技术部', '2018-03-03', '10:13:00', '19:32:00', '1');
INSERT INTO `emp_attendance` VALUES ('359', '29', '王茂青', '技术部', '2018-03-04', '10:04:00', null, '5');
INSERT INTO `emp_attendance` VALUES ('360', '29', '王茂青', '技术部', '2018-03-05', '00:25:00', '22:13:00', '0');
INSERT INTO `emp_attendance` VALUES ('361', '29', '王茂青', '技术部', '2018-03-08', '09:59:00', '19:22:00', '1');
INSERT INTO `emp_attendance` VALUES ('362', '29', '王茂青', '技术部', '2018-03-09', '13:41:00', '19:35:00', '1');
INSERT INTO `emp_attendance` VALUES ('363', '29', '王茂青', '技术部', '2018-03-10', '10:06:00', '19:27:00', '1');
INSERT INTO `emp_attendance` VALUES ('364', '29', '王茂青', '技术部', '2018-03-11', '10:04:00', '19:32:00', '1');
INSERT INTO `emp_attendance` VALUES ('365', '29', '王茂青', '技术部', '2018-03-12', '10:02:00', '19:14:00', '1');
INSERT INTO `emp_attendance` VALUES ('366', '29', '王茂青', '技术部', '2018-03-15', '09:45:00', null, '5');
INSERT INTO `emp_attendance` VALUES ('367', '29', '王茂青', '技术部', '2018-03-16', '04:07:00', null, '5');
INSERT INTO `emp_attendance` VALUES ('368', '29', '王茂青', '技术部', '2018-03-17', '09:51:00', '19:15:00', '1');
INSERT INTO `emp_attendance` VALUES ('369', '29', '王茂青', '技术部', '2018-03-18', '19:43:00', null, '5');
INSERT INTO `emp_attendance` VALUES ('370', '29', '王茂青', '技术部', '2018-03-19', '09:59:00', '19:14:00', '1');
INSERT INTO `emp_attendance` VALUES ('371', '29', '王茂青', '技术部', '2018-03-22', '09:57:00', '22:02:00', '1');
INSERT INTO `emp_attendance` VALUES ('372', '29', '王茂青', '技术部', '2018-03-23', '10:24:00', '21:07:00', '1');
INSERT INTO `emp_attendance` VALUES ('373', '29', '王茂青', '技术部', '2018-03-24', '11:38:00', '20:30:00', '1');
INSERT INTO `emp_attendance` VALUES ('374', '29', '王茂青', '技术部', '2018-03-25', '08:55:00', '18:12:00', '0');
INSERT INTO `emp_attendance` VALUES ('375', '29', '王茂青', '技术部', '2018-03-26', '10:13:00', '20:04:00', '1');
INSERT INTO `emp_attendance` VALUES ('376', '29', '王茂青', '技术部', '2018-03-29', '10:00:00', '15:42:00', '3');
INSERT INTO `emp_attendance` VALUES ('377', '29', '王茂青', '技术部', '2018-03-31', '09:44:00', '20:44:00', '1');
INSERT INTO `emp_attendance` VALUES ('378', '61', '王晓东', '技术部', '2018-03-02', '08:54:00', '19:42:00', '0');
INSERT INTO `emp_attendance` VALUES ('379', '61', '王晓东', '技术部', '2018-03-03', '08:39:00', '19:58:00', '0');
INSERT INTO `emp_attendance` VALUES ('380', '61', '王晓东', '技术部', '2018-03-04', '08:45:00', '19:00:00', '0');
INSERT INTO `emp_attendance` VALUES ('381', '61', '王晓东', '技术部', '2018-03-06', '13:02:00', null, '5');
INSERT INTO `emp_attendance` VALUES ('382', '61', '王晓东', '技术部', '2018-03-08', '08:46:00', '20:06:00', '0');
INSERT INTO `emp_attendance` VALUES ('383', '61', '王晓东', '技术部', '2018-03-09', '08:59:00', '19:55:00', '0');
INSERT INTO `emp_attendance` VALUES ('384', '61', '王晓东', '技术部', '2018-03-17', '08:38:00', '20:18:00', '0');
INSERT INTO `emp_attendance` VALUES ('385', '61', '王晓东', '技术部', '2018-03-18', '09:01:00', '19:54:00', '1');
INSERT INTO `emp_attendance` VALUES ('386', '61', '王晓东', '技术部', '2018-03-22', '09:00:00', '14:34:00', '2');
INSERT INTO `emp_attendance` VALUES ('387', '61', '王晓东', '技术部', '2018-03-23', '08:40:00', '20:20:00', '0');
INSERT INTO `emp_attendance` VALUES ('388', '61', '王晓东', '技术部', '2018-03-24', '09:03:00', '19:44:00', '1');
INSERT INTO `emp_attendance` VALUES ('389', '61', '王晓东', '技术部', '2018-03-25', '09:40:00', '20:04:00', '1');
INSERT INTO `emp_attendance` VALUES ('390', '61', '王晓东', '技术部', '2018-03-26', '09:46:00', '20:36:00', '1');
INSERT INTO `emp_attendance` VALUES ('391', '61', '王晓东', '技术部', '2018-03-30', '09:38:00', '19:00:00', '1');
INSERT INTO `emp_attendance` VALUES ('392', '61', '王晓东', '技术部', '2018-03-31', '09:48:00', '19:00:00', '1');
INSERT INTO `emp_attendance` VALUES ('393', '89', '杨海龙', '技术部', '2018-03-02', '09:45:00', '19:43:00', '1');
INSERT INTO `emp_attendance` VALUES ('394', '89', '杨海龙', '技术部', '2018-03-03', '13:06:00', '20:32:00', '1');
INSERT INTO `emp_attendance` VALUES ('395', '89', '杨海龙', '技术部', '2018-03-04', '09:27:00', '19:00:00', '1');
INSERT INTO `emp_attendance` VALUES ('396', '89', '杨海龙', '技术部', '2018-03-08', '09:51:00', '19:28:00', '1');
INSERT INTO `emp_attendance` VALUES ('397', '89', '杨海龙', '技术部', '2018-03-09', '09:46:00', '19:19:00', '1');
INSERT INTO `emp_attendance` VALUES ('398', '89', '杨海龙', '技术部', '2018-03-10', '09:44:00', '21:11:00', '1');
INSERT INTO `emp_attendance` VALUES ('399', '89', '杨海龙', '技术部', '2018-03-11', '09:37:00', '20:39:00', '1');
INSERT INTO `emp_attendance` VALUES ('400', '89', '杨海龙', '技术部', '2018-03-12', '09:44:00', '21:10:00', '1');
INSERT INTO `emp_attendance` VALUES ('401', '89', '杨海龙', '技术部', '2018-03-15', '09:34:00', '19:03:00', '1');
INSERT INTO `emp_attendance` VALUES ('402', '89', '杨海龙', '技术部', '2018-03-16', '09:20:00', '20:38:00', '1');
INSERT INTO `emp_attendance` VALUES ('403', '89', '杨海龙', '技术部', '2018-03-17', '09:35:00', '19:45:00', '1');
INSERT INTO `emp_attendance` VALUES ('404', '89', '杨海龙', '技术部', '2018-03-18', '09:49:00', '19:16:00', '1');
INSERT INTO `emp_attendance` VALUES ('405', '89', '杨海龙', '技术部', '2018-03-19', '08:22:00', '19:38:00', '0');
INSERT INTO `emp_attendance` VALUES ('406', '89', '杨海龙', '技术部', '2018-03-20', '08:27:00', '18:19:00', '0');
INSERT INTO `emp_attendance` VALUES ('407', '89', '杨海龙', '技术部', '2018-03-21', '09:51:00', '19:23:00', '1');
INSERT INTO `emp_attendance` VALUES ('408', '89', '杨海龙', '技术部', '2018-03-22', '09:41:00', '21:03:00', '1');
INSERT INTO `emp_attendance` VALUES ('409', '89', '杨海龙', '技术部', '2018-03-23', '09:37:00', '19:00:00', '1');
INSERT INTO `emp_attendance` VALUES ('410', '89', '杨海龙', '技术部', '2018-03-24', '04:58:00', '17:00:00', '2');
INSERT INTO `emp_attendance` VALUES ('411', '89', '杨海龙', '技术部', '2018-03-25', '09:41:00', '20:24:00', '1');
INSERT INTO `emp_attendance` VALUES ('412', '89', '杨海龙', '技术部', '2018-03-26', '09:31:00', '19:00:00', '1');
INSERT INTO `emp_attendance` VALUES ('413', '89', '杨海龙', '技术部', '2018-03-29', '09:41:00', '19:09:00', '1');
INSERT INTO `emp_attendance` VALUES ('414', '89', '杨海龙', '技术部', '2018-03-30', '08:16:00', '19:20:00', '0');
INSERT INTO `emp_attendance` VALUES ('415', '89', '杨海龙', '技术部', '2018-03-31', '09:33:00', '19:01:00', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=612 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of login_user
-- ----------------------------
INSERT INTO `login_user` VALUES ('169', '1', 'admin', '100000', '1521423014920', '1521423014920', '1521423014920', '1521423030939');
INSERT INTO `login_user` VALUES ('170', '1', 'admin', '100000', '1521423036092', '1521423036092', '1521423036092', '1521424371408');
INSERT INTO `login_user` VALUES ('171', '1', 'zzzz', '100004', '1521425044500', '1521425044500', '1521425044500', '1521425052664');
INSERT INTO `login_user` VALUES ('172', '1', 'zzzz', '100004', '1521425073041', '1521425073041', '1521425073041', '1521425091827');
INSERT INTO `login_user` VALUES ('173', '1', 'admin', '100000', '1521425392518', '1521425392518', '1521425392518', null);
INSERT INTO `login_user` VALUES ('174', '1', 'zzzz', '100004', '1521425400821', '1521425400821', '1521425400821', null);
INSERT INTO `login_user` VALUES ('175', '1', 'zzzz', '100004', '1521425616893', '1521425616893', '1521425616893', '1521425622551');
INSERT INTO `login_user` VALUES ('176', '1', 'admin', '100000', '1521425627710', '1521425627710', '1521425627710', null);
INSERT INTO `login_user` VALUES ('177', '1', 'admin', '100000', '1521425867177', '1521425867177', '1521425867177', null);
INSERT INTO `login_user` VALUES ('178', '1', 'admin', '100000', '1521426384157', '1521426384157', '1521426384157', '1521426409119');
INSERT INTO `login_user` VALUES ('179', '1', 'zzz', '100004', '1521426459801', '1521426459801', '1521426459801', null);
INSERT INTO `login_user` VALUES ('180', '1', 'admin', '100000', '1521426730548', '1521426730548', '1521426730548', '1521427015493');
INSERT INTO `login_user` VALUES ('181', '1', 'zhangsanfeng', '100006', '1521427064963', '1521427064963', '1521427064963', '1521427080827');
INSERT INTO `login_user` VALUES ('182', '1', 'zzz', '100004', '1521427319269', '1521427319269', '1521427319269', null);
INSERT INTO `login_user` VALUES ('183', '1', 'admin', '100000', '1521427814822', '1521427814340', '1521427816715', null);
INSERT INTO `login_user` VALUES ('184', '1', 'zzz', '100004', '1521428068142', '1521428068142', '1521428068142', null);
INSERT INTO `login_user` VALUES ('185', '1', 'zzz', '100004', '1521428457547', '1521428457547', '1521428457547', null);
INSERT INTO `login_user` VALUES ('186', '1', 'zzz', '100004', '1521428697636', '1521428697636', '1521428697636', null);
INSERT INTO `login_user` VALUES ('187', '1', 'zzz', '100004', '1521428811033', '1521428811033', '1521428811033', null);
INSERT INTO `login_user` VALUES ('188', '1', 'zzz', '100004', '1521428895080', '1521428895080', '1521428895080', null);
INSERT INTO `login_user` VALUES ('189', '1', '改个好听的名字', '100000', '1521429048173', '1521429048173', '1521429048173', null);
INSERT INTO `login_user` VALUES ('190', '1', '改个好听的名字', '100000', '1521429084034', '1521429084034', '1521429084034', null);
INSERT INTO `login_user` VALUES ('191', '1', '改个好听的名字', '100000', '1521429262771', '1521429262771', '1521429262771', null);
INSERT INTO `login_user` VALUES ('192', '1', 'zzz', '100004', '1521429421560', '1521429421560', '1521429421560', null);
INSERT INTO `login_user` VALUES ('193', '1', 'admin', '100000', '1521429430116', '1521429430116', '1521429430116', null);
INSERT INTO `login_user` VALUES ('194', '1', 'zzz', '100004', '1521429579106', '1521429579106', '1521429579106', null);
INSERT INTO `login_user` VALUES ('195', '1', 'zzz', '100004', '1521429528090', '1521429528090', '1521429528090', null);
INSERT INTO `login_user` VALUES ('196', '1', '改个好听的名字', '100000', '1521429547666', '1521429547666', '1521429547666', null);
INSERT INTO `login_user` VALUES ('197', '1', 'zzz', '100004', '1521429766494', '1521429766494', '1521429766494', null);
INSERT INTO `login_user` VALUES ('198', '1', '改个好听的名字', '100000', '1521429954672', '1521429954672', '1521429954672', null);
INSERT INTO `login_user` VALUES ('199', '1', 'admin', '100000', '1521430127188', '1521430127188', '1521430127188', '1521430338173');
INSERT INTO `login_user` VALUES ('200', '1', '改个好听的名字', '100000', '1521430359157', '1521430359157', '1521430359157', null);
INSERT INTO `login_user` VALUES ('201', '1', 'admin', '100000', '1521430440766', '1521430440766', '1521430440766', null);
INSERT INTO `login_user` VALUES ('202', '1', '改个好听的名字', '100000', '1521430677329', '1521430677329', '1521430677329', null);
INSERT INTO `login_user` VALUES ('203', '1', '改个好听的名字', '100000', '1521430827874', '1521430827874', '1521430827874', null);
INSERT INTO `login_user` VALUES ('204', '1', '改个好听的名字', '100000', '1521431204858', '1521431204858', '1521431204858', null);
INSERT INTO `login_user` VALUES ('205', '1', 'admin', '100000', '1521431308317', '1521431308317', '1521431308317', null);
INSERT INTO `login_user` VALUES ('206', '1', 'admin', '100000', '1521431977841', '1521431977841', '1521431977841', null);
INSERT INTO `login_user` VALUES ('207', '1', '改个好听的名字', '100000', '1521432770227', '1521432770227', '1521432770227', null);
INSERT INTO `login_user` VALUES ('208', '1', '改个好听的名字', '100000', '1521433248155', '1521433248155', '1521433248155', null);
INSERT INTO `login_user` VALUES ('209', '1', 'admin', '100000', '1521433787651', '1521433787651', '1521433787651', null);
INSERT INTO `login_user` VALUES ('210', '1', 'zzz', '100004', '1521438009194', '1521438009194', '1521438009194', '1521438033573');
INSERT INTO `login_user` VALUES ('211', '1', 'admin', '100000', '1521438041498', '1521438041498', '1521438041498', null);
INSERT INTO `login_user` VALUES ('212', '1', 'zzz', '100004', '1521438247705', '1521438247705', '1521438247705', null);
INSERT INTO `login_user` VALUES ('213', '1', 'zzz', '100004', '1521438438733', '1521438438733', '1521438438733', null);
INSERT INTO `login_user` VALUES ('214', '1', '改个好听的名字', '100000', '1521438685937', '1521438685937', '1521438685937', null);
INSERT INTO `login_user` VALUES ('215', '1', 'zhangsan', '100001', '1521440030937', '1521440030937', '1521440030937', null);
INSERT INTO `login_user` VALUES ('216', '1', 'zzz', '100004', '1521440286285', '1521440286285', '1521440286285', null);
INSERT INTO `login_user` VALUES ('217', '1', 'zhangsan', '100001', '1521440274055', '1521440274055', '1521440274055', null);
INSERT INTO `login_user` VALUES ('218', '1', 'zhangsan', '100001', '1521440392644', '1521440392644', '1521440392644', null);
INSERT INTO `login_user` VALUES ('219', '1', 'zzz', '100004', '1521440571190', '1521440571190', '1521440571190', '1521440581354');
INSERT INTO `login_user` VALUES ('220', '1', 'zzz', '100004', '1521440636293', '1521440636293', '1521440636293', null);
INSERT INTO `login_user` VALUES ('221', '1', 'zzz', '100004', '1521440731858', '1521440731858', '1521440731858', null);
INSERT INTO `login_user` VALUES ('222', '1', 'zzz', '100004', '1521440785510', '1521440785510', '1521440785510', null);
INSERT INTO `login_user` VALUES ('223', '1', 'zzz', '100004', '1521440924261', '1521440924261', '1521440924261', null);
INSERT INTO `login_user` VALUES ('224', '1', 'zzz', '100004', '1521441365345', '1521441365345', '1521441365345', null);
INSERT INTO `login_user` VALUES ('225', '1', 'zzz', '100004', '1521441471117', '1521441471117', '1521441471117', null);
INSERT INTO `login_user` VALUES ('226', '1', 'zzz', '100004', '1521442480023', '1521442480023', '1521442480023', null);
INSERT INTO `login_user` VALUES ('227', '1', 'zzz', '100004', '1521443366116', '1521443366116', '1521443366116', null);
INSERT INTO `login_user` VALUES ('228', '1', 'zzz', '100004', '1521443451790', '1521443451790', '1521443451790', null);
INSERT INTO `login_user` VALUES ('229', '1', 'zzz', '100004', '1521443581940', '1521443581940', '1521443581940', null);
INSERT INTO `login_user` VALUES ('230', '1', 'zzz', '100004', '1521444573961', '1521444573961', '1521444573961', null);
INSERT INTO `login_user` VALUES ('231', '1', 'zzz', '100004', '1521444676920', '1521444676920', '1521444676920', null);
INSERT INTO `login_user` VALUES ('232', '1', 'zzz', '100004', '1521444835182', '1521444835182', '1521444835182', null);
INSERT INTO `login_user` VALUES ('233', '1', 'zzz', '100004', '1521445001598', '1521445001598', '1521445001598', null);
INSERT INTO `login_user` VALUES ('234', '1', 'zzz', '100004', '1521445220859', '1521445220859', '1521445220859', null);
INSERT INTO `login_user` VALUES ('235', '1', 'zzz', '100004', '1521445366061', '1521445366061', '1521445366061', null);
INSERT INTO `login_user` VALUES ('236', '1', 'zzz', '100004', '1521445416967', '1521445416967', '1521445416967', null);
INSERT INTO `login_user` VALUES ('237', '1', 'zzz', '100004', '1521445583637', '1521445583637', '1521445583637', null);
INSERT INTO `login_user` VALUES ('238', '1', 'zzz', '100004', '1521445699567', '1521445699567', '1521445699567', null);
INSERT INTO `login_user` VALUES ('239', '1', 'zzz', '100004', '1521445828290', '1521445828290', '1521445828290', null);
INSERT INTO `login_user` VALUES ('240', '1', 'zzz', '100004', '1521446458785', '1521446458785', '1521446458785', null);
INSERT INTO `login_user` VALUES ('241', '1', 'zzz', '100004', '1521446531455', '1521446531455', '1521446531455', null);
INSERT INTO `login_user` VALUES ('242', '1', 'zzz', '100004', '1521446579250', '1521446579250', '1521446579250', null);
INSERT INTO `login_user` VALUES ('243', '1', 'zzz', '100004', '1521446648420', '1521446648420', '1521446648420', null);
INSERT INTO `login_user` VALUES ('244', '1', 'zzz', '100004', '1521446705258', '1521446705258', '1521446705258', null);
INSERT INTO `login_user` VALUES ('245', '1', 'zzz', '100004', '1521446752527', '1521446752527', '1521446752527', null);
INSERT INTO `login_user` VALUES ('246', '1', 'zzz', '100004', '1521446860565', '1521446860565', '1521446860565', null);
INSERT INTO `login_user` VALUES ('247', '1', '改个好听的名字', '100000', '1521447215800', '1521447215800', '1521447215800', null);
INSERT INTO `login_user` VALUES ('248', '1', 'zzz', '100004', '1521447411431', '1521447411431', '1521447411431', null);
INSERT INTO `login_user` VALUES ('249', '1', 'zhangsan', '100001', '1521447412816', '1521447412816', '1521447412816', null);
INSERT INTO `login_user` VALUES ('250', '1', 'zzz', '100004', '1521449292161', '1521449292161', '1521449292161', null);
INSERT INTO `login_user` VALUES ('251', '1', 'zzz', '100004', '1521449564510', '1521449564510', '1521449564510', null);
INSERT INTO `login_user` VALUES ('252', '1', 'zzz', '100004', '1521450157395', '1521450157395', '1521450157395', null);
INSERT INTO `login_user` VALUES ('253', '1', 'zzz', '100004', '1521450261724', '1521450261724', '1521450261724', null);
INSERT INTO `login_user` VALUES ('254', '1', 'admin', '100000', '1521450906115', '1521450906115', '1521450906115', '1521450914161');
INSERT INTO `login_user` VALUES ('255', '1', '改个好听的名字', '100000', '1521450912920', '1521450912920', '1521450912920', null);
INSERT INTO `login_user` VALUES ('256', '1', 'admin', '100000', '1521450923695', '1521450923695', '1521450923695', '1521450944625');
INSERT INTO `login_user` VALUES ('257', '1', 'zhangsanfeng', '100006', '1521450954859', '1521450954859', '1521450954859', '1521450958593');
INSERT INTO `login_user` VALUES ('258', '1', 'admin', '100000', '1521450967459', '1521450967459', '1521450967459', null);
INSERT INTO `login_user` VALUES ('259', '1', 'zzz', '100004', '1521451130180', '1521451130180', '1521451130180', null);
INSERT INTO `login_user` VALUES ('260', '1', 'admin', '100000', '1521451177029', '1521451177029', '1521451177029', null);
INSERT INTO `login_user` VALUES ('261', '1', 'admin', '100000', '1521451263709', '1521451263709', '1521451263709', '1521451396457');
INSERT INTO `login_user` VALUES ('262', '1', 'zzz', '100004', '1521451342269', '1521451342269', '1521451342269', null);
INSERT INTO `login_user` VALUES ('263', '1', 'zzz', '100004', '1521451413603', '1521451413603', '1521451413603', null);
INSERT INTO `login_user` VALUES ('264', '1', 'zzz', '100004', '1521451427662', '1521451427662', '1521451427662', null);
INSERT INTO `login_user` VALUES ('265', '1', 'zzz', '100004', '1521451442934', '1521451442934', '1521451442934', null);
INSERT INTO `login_user` VALUES ('266', '1', 'zzz', '100004', '1521451547983', '1521451547983', '1521451547983', '1521451592152');
INSERT INTO `login_user` VALUES ('267', '1', 'zzz', '100004', '1521451598313', '1521451598313', '1521451598313', null);
INSERT INTO `login_user` VALUES ('268', '1', 'admin', '100000', '1521451555403', '1521451555403', '1521451555403', '1521451557447');
INSERT INTO `login_user` VALUES ('269', '1', 'zzz', '100004', '1521451565040', '1521451565040', '1521451565040', null);
INSERT INTO `login_user` VALUES ('270', '1', 'zzz', '100004', '1521451838219', '1521451838219', '1521451838219', '1521451876921');
INSERT INTO `login_user` VALUES ('271', '1', 'zzz', '100004', '1521451882106', '1521451882106', '1521451882106', null);
INSERT INTO `login_user` VALUES ('272', '1', 'admin', '100000', '1521452017749', '1521452017749', '1521452017749', '1521452124709');
INSERT INTO `login_user` VALUES ('273', '1', 'zzz', '100004', '1521452129796', '1521452129796', '1521452129796', '1521452172604');
INSERT INTO `login_user` VALUES ('274', '1', 'zzz', '100004', '1521452178671', '1521452178671', '1521452178671', '1521452205642');
INSERT INTO `login_user` VALUES ('275', '1', 'zzz', '100004', '1521452331653', '1521452331653', '1521452331653', null);
INSERT INTO `login_user` VALUES ('276', '1', '改个好听的名字', '100000', '1521452288900', '1521452288900', '1521452288900', '1521452321258');
INSERT INTO `login_user` VALUES ('277', '1', 'admin', '100000', '1521452319420', '1521452319420', '1521452319420', '1521452363077');
INSERT INTO `login_user` VALUES ('278', '1', '改个好听的名字', '100000', '1521452340709', '1521452340709', '1521452340709', '1521452403763');
INSERT INTO `login_user` VALUES ('279', '1', 'zzz', '100004', '1521452374499', '1521452374499', '1521452374499', null);
INSERT INTO `login_user` VALUES ('280', '1', 'zzz', '100004', '1521452458080', '1521452458080', '1521452458080', null);
INSERT INTO `login_user` VALUES ('281', '1', 'admin', '100000', '1521452410918', '1521452410918', '1521452410918', null);
INSERT INTO `login_user` VALUES ('282', '1', 'admin', '100000', '1521452497911', '1521452497911', '1521452497911', null);
INSERT INTO `login_user` VALUES ('283', '1', 'zzz', '100004', '1521452530738', '1521452530738', '1521452530738', null);
INSERT INTO `login_user` VALUES ('284', '1', 'admin', '100000', '1521452568418', '1521452568418', '1521452568418', null);
INSERT INTO `login_user` VALUES ('285', '1', 'admin', '100000', '1521452641226', '1521452641226', '1521452641226', null);
INSERT INTO `login_user` VALUES ('286', '1', 'zzz', '100004', '1521452698160', '1521452698160', '1521452698160', null);
INSERT INTO `login_user` VALUES ('287', '1', 'admin', '100000', '1521452693776', '1521452693776', '1521452693776', null);
INSERT INTO `login_user` VALUES ('288', '1', 'zzz', '100004', '1521452864966', '1521452864966', '1521452864966', null);
INSERT INTO `login_user` VALUES ('289', '1', 'zzz', '100004', '1521453122988', '1521453122988', '1521453122988', null);
INSERT INTO `login_user` VALUES ('290', '1', 'zzz', '100004', '1521453249522', '1521453249522', '1521453249522', '1521453305373');
INSERT INTO `login_user` VALUES ('291', '1', 'zzz', '100004', '1521453310648', '1521453310648', '1521453310648', null);
INSERT INTO `login_user` VALUES ('292', '1', 'admin', '100000', '1521453266575', '1521453266575', '1521453266575', '1521453302524');
INSERT INTO `login_user` VALUES ('293', '1', 'zzz', '100004', '1521453342822', '1521453342822', '1521453342822', null);
INSERT INTO `login_user` VALUES ('294', '1', 'admin', '100000', '1521453316380', '1521453316380', '1521453316380', null);
INSERT INTO `login_user` VALUES ('295', '1', 'zzz', '100004', '1521453450356', '1521453450356', '1521453450356', null);
INSERT INTO `login_user` VALUES ('296', '1', 'zzz', '100004', '1521453599257', '1521453599257', '1521453599257', '1521453644080');
INSERT INTO `login_user` VALUES ('297', '1', 'zzz', '100004', '1521453648084', '1521453648084', '1521453648084', null);
INSERT INTO `login_user` VALUES ('298', '1', 'zhangsan', '100001', '1521507210961', '1521507210961', '1521507210961', null);
INSERT INTO `login_user` VALUES ('299', '1', 'zhangsan', '100001', '1521507217869', '1521507217869', '1521507217869', null);
INSERT INTO `login_user` VALUES ('300', '1', 'zhangsanfeng', '100006', '1521507228278', '1521507228278', '1521507228278', '1521507293740');
INSERT INTO `login_user` VALUES ('301', '1', 'zhangsan123', '100001', '1521507304799', '1521507304799', '1521507304799', '1521507333951');
INSERT INTO `login_user` VALUES ('302', '1', '改个好听的名字', '100000', '1521507347625', '1521507347625', '1521507347625', null);
INSERT INTO `login_user` VALUES ('303', '1', '改个好听的名字', '100000', '1521508155061', '1521508155061', '1521508155061', null);
INSERT INTO `login_user` VALUES ('304', '1', '改个好听的名字', '100000', '1521508435929', '1521508435929', '1521508435929', null);
INSERT INTO `login_user` VALUES ('305', '1', '改个好听的名字', '100000', '1521509152776', '1521509152776', '1521509152776', null);
INSERT INTO `login_user` VALUES ('306', '1', 'zhangsan123', '100001', '1521509160392', '1521509160392', '1521509160392', null);
INSERT INTO `login_user` VALUES ('307', '1', 'zhangsan123', '100001', '1521509173038', '1521509173038', '1521509173038', null);
INSERT INTO `login_user` VALUES ('308', '1', 'zhangsan123', '100001', '1521509185266', '1521509185266', '1521509185266', '1521509200131');
INSERT INTO `login_user` VALUES ('309', '1', 'zhangsan', '100001', '1521509205113', '1521509205113', '1521509205113', null);
INSERT INTO `login_user` VALUES ('310', '1', 'zzz', '100004', '1521509218059', '1521509218059', '1521509218059', null);
INSERT INTO `login_user` VALUES ('311', '1', 'admin', '100000', '1521509270054', '1521509270054', '1521509270054', null);
INSERT INTO `login_user` VALUES ('312', '1', 'admin', '100000', '1521509270062', '1521509270062', '1521509270062', null);
INSERT INTO `login_user` VALUES ('313', '1', 'zzz', '100004', '1521509361351', '1521509361351', '1521509361351', null);
INSERT INTO `login_user` VALUES ('314', '1', 'zzz', '100004', '1521509383253', '1521509383253', '1521509383253', null);
INSERT INTO `login_user` VALUES ('315', '1', 'zzz', '100004', '1521509453766', '1521509453766', '1521509453766', '1521509423478');
INSERT INTO `login_user` VALUES ('316', '1', 'zzz', '100004', '1521509432365', '1521509432365', '1521509432365', null);
INSERT INTO `login_user` VALUES ('317', '1', 'admin', '100000', '1521509517452', '1521509517452', '1521509517452', null);
INSERT INTO `login_user` VALUES ('318', '1', 'zzz', '100004', '1521509599604', '1521509599604', '1521509599604', null);
INSERT INTO `login_user` VALUES ('319', '1', 'zzz', '100004', '1521509749738', '1521509749738', '1521509749738', '1521509791430');
INSERT INTO `login_user` VALUES ('320', '1', 'zzz', '100004', '1521509798143', '1521509798143', '1521509798143', null);
INSERT INTO `login_user` VALUES ('321', '1', 'zzz', '100004', '1521509930942', '1521509930942', '1521509930942', null);
INSERT INTO `login_user` VALUES ('322', '1', 'zzz', '100004', '1521509999063', '1521509999063', '1521509999063', null);
INSERT INTO `login_user` VALUES ('323', '1', 'zzz', '100004', '1521510159669', '1521510159669', '1521510159669', null);
INSERT INTO `login_user` VALUES ('324', '1', 'zzz', '100004', '1521510448693', '1521510448693', '1521510448693', null);
INSERT INTO `login_user` VALUES ('325', '1', 'zhangsan', '100001', '1521510471947', '1521510471947', '1521510471947', null);
INSERT INTO `login_user` VALUES ('326', '1', 'zzz', '100004', '1521510478990', '1521510478990', '1521510478990', '1521510491629');
INSERT INTO `login_user` VALUES ('327', '1', 'zzz', '100004', '1521510498399', '1521510498399', '1521510498399', null);
INSERT INTO `login_user` VALUES ('328', '1', 'zzz', '100004', '1521510764061', '1521510764061', '1521510764061', null);
INSERT INTO `login_user` VALUES ('329', '1', 'zzz', '100004', '1521511033630', '1521511033630', '1521511033630', null);
INSERT INTO `login_user` VALUES ('330', '1', 'zzz', '100004', '1521510982116', '1521510982116', '1521510982116', null);
INSERT INTO `login_user` VALUES ('331', '1', 'zzz', '100004', '1521511412688', '1521511412688', '1521511412688', null);
INSERT INTO `login_user` VALUES ('332', '1', 'zzz', '100004', '1521511568590', '1521511568590', '1521511568590', null);
INSERT INTO `login_user` VALUES ('333', '1', 'zzz', '100004', '1521511769954', '1521511769954', '1521511769954', null);
INSERT INTO `login_user` VALUES ('334', '1', 'zzz', '100004', '1521512135530', '1521512135530', '1521512135530', null);
INSERT INTO `login_user` VALUES ('335', '1', 'zzz', '100004', '1521512668779', '1521512668779', '1521512668779', null);
INSERT INTO `login_user` VALUES ('336', '1', 'zzz', '100004', '1521513395052', '1521513395052', '1521513395052', null);
INSERT INTO `login_user` VALUES ('337', '1', 'zzz', '100004', '1521513653328', '1521513653328', '1521513653328', null);
INSERT INTO `login_user` VALUES ('338', '1', 'zzz', '100004', '1521513926753', '1521513926753', '1521513926753', null);
INSERT INTO `login_user` VALUES ('339', '1', 'zzz', '100004', '1521514072459', '1521514072459', '1521514072459', null);
INSERT INTO `login_user` VALUES ('340', '1', 'zzz', '100004', '1521514465874', '1521514465874', '1521514465874', '1521514735143');
INSERT INTO `login_user` VALUES ('341', '1', 'zzz', '100004', '1521515315768', '1521515315768', '1521515315768', '1521515285671');
INSERT INTO `login_user` VALUES ('342', '1', 'zzz', '100004', '1521515269492', '1521515269492', '1521515269492', null);
INSERT INTO `login_user` VALUES ('343', '1', 'admin', '100000', '1521516092852', '1521516092852', '1521516092852', null);
INSERT INTO `login_user` VALUES ('344', '1', 'zzz', '100004', '1521516890866', '1521516890866', '1521516890866', null);
INSERT INTO `login_user` VALUES ('345', '1', 'zzz', '100004', '1521524462395', '1521524462395', '1521524462395', null);
INSERT INTO `login_user` VALUES ('346', '1', 'zzz', '100004', '1521525678726', '1521525678726', '1521525678726', '1521526670737');
INSERT INTO `login_user` VALUES ('347', '1', 'zzz', '100004', '1521526673104', '1521526673104', '1521526673104', '1521526694713');
INSERT INTO `login_user` VALUES ('348', '1', 'zhangsan', '100001', '1521527864765', '1521527864765', '1521527864765', null);
INSERT INTO `login_user` VALUES ('349', '1', 'zhangsan', '100001', '1521528239407', '1521528239407', '1521528239407', null);
INSERT INTO `login_user` VALUES ('350', '1', 'zhangsan', '100001', '1521528999491', '1521528999491', '1521528999491', null);
INSERT INTO `login_user` VALUES ('351', '1', 'zhangsan', '100001', '1521529230331', '1521529230331', '1521529230331', null);
INSERT INTO `login_user` VALUES ('352', '1', 'zzz', '100004', '1521531026405', '1521531026405', '1521531026405', null);
INSERT INTO `login_user` VALUES ('353', '1', 'zzz', '100004', '1521531199231', '1521531199231', '1521531199231', '1521532322083');
INSERT INTO `login_user` VALUES ('354', '1', 'zzz', '100004', '1521533042162', '1521533042162', '1521533042162', null);
INSERT INTO `login_user` VALUES ('355', '1', 'zzz', '100004', '1521536029605', '1521536029605', '1521536029605', null);
INSERT INTO `login_user` VALUES ('356', '1', 'zzz', '100004', '1521598782076', '1521598782076', '1521598782076', null);
INSERT INTO `login_user` VALUES ('357', '1', 'zzz', '100004', '1521680193096', '1521680193096', '1521680193096', null);
INSERT INTO `login_user` VALUES ('358', '1', 'zzz', '100004', '1521683306789', '1521683306789', '1521683306789', null);
INSERT INTO `login_user` VALUES ('359', '1', 'zzz', '100004', '1521774491438', '1521774491438', '1521774491438', null);
INSERT INTO `login_user` VALUES ('360', '1', 'zzz', '100004', '1521774978282', '1521774978282', '1521774978282', null);
INSERT INTO `login_user` VALUES ('361', '1', 'zzz', '100004', '1521775082948', '1521775082948', '1521775082948', null);
INSERT INTO `login_user` VALUES ('362', '1', 'zzz', '100004', '1521792862794', '1521792862794', '1521792862794', null);
INSERT INTO `login_user` VALUES ('363', '1', 'zzz', '100004', '1521794265308', '1521794265308', '1521794265308', null);
INSERT INTO `login_user` VALUES ('364', '1', 'zzz', '100004', '1521795191345', '1521795191345', '1521795191345', null);
INSERT INTO `login_user` VALUES ('365', '1', 'zhangsan', '100001', '1521795428352', '1521795428352', '1521795428352', null);
INSERT INTO `login_user` VALUES ('366', '1', '改个好听的名字', '100000', '1521795444530', '1521795444530', '1521795444530', null);
INSERT INTO `login_user` VALUES ('367', '1', 'admin', '100000', '1523266439867', '1523266439867', '1523266439867', null);
INSERT INTO `login_user` VALUES ('368', '1', 'admin', '100000', '1523266575194', '1523266575194', '1523266575194', null);
INSERT INTO `login_user` VALUES ('369', '1', 'admin', '100000', '1523266788329', '1523266788329', '1523266788329', null);
INSERT INTO `login_user` VALUES ('370', '1', 'admin', '100000', '1523266995038', '1523266995038', '1523266995038', null);
INSERT INTO `login_user` VALUES ('371', '1', 'admin', '100000', '1523267090998', '1523267090998', '1523267090998', null);
INSERT INTO `login_user` VALUES ('372', '1', 'admin', '100000', '1523267389204', '1523267389204', '1523267389204', null);
INSERT INTO `login_user` VALUES ('373', '1', 'admin', '100000', '1523267663974', '1523267663974', '1523267663974', null);
INSERT INTO `login_user` VALUES ('374', '1', 'admin', '100000', '1523267986554', '1523267986554', '1523267986554', null);
INSERT INTO `login_user` VALUES ('375', '1', 'admin', '100000', '1523268219076', '1523268219076', '1523268219076', null);
INSERT INTO `login_user` VALUES ('376', '1', 'admin', '100000', '1523268399261', '1523268399261', '1523268399261', null);
INSERT INTO `login_user` VALUES ('377', '1', 'admin', '100000', '1523268721229', '1523268721229', '1523268721229', null);
INSERT INTO `login_user` VALUES ('378', '1', 'admin', '100000', '1523269023293', '1523269023293', '1523269023293', null);
INSERT INTO `login_user` VALUES ('379', '1', 'admin', '100000', '1523269152360', '1523269152360', '1523269152360', null);
INSERT INTO `login_user` VALUES ('380', '1', 'admin', '100000', '1523269196488', '1523269196488', '1523269196488', null);
INSERT INTO `login_user` VALUES ('381', '1', 'admin', '100000', '1523269214946', '1523269214946', '1523269214946', null);
INSERT INTO `login_user` VALUES ('382', '1', 'admin', '100000', '1523269374454', '1523269374454', '1523269374454', null);
INSERT INTO `login_user` VALUES ('383', '1', 'admin', '100000', '1523269476856', '1523269476856', '1523269476856', null);
INSERT INTO `login_user` VALUES ('384', '1', 'admin', '100000', '1523269544609', '1523269544609', '1523269544609', null);
INSERT INTO `login_user` VALUES ('385', '1', 'admin', '100000', '1523269616344', '1523269616344', '1523269616344', null);
INSERT INTO `login_user` VALUES ('386', '1', 'admin', '100000', '1523269665831', '1523269665831', '1523269665831', null);
INSERT INTO `login_user` VALUES ('387', '1', 'admin', '100000', '1523270080434', '1523270080434', '1523270080434', null);
INSERT INTO `login_user` VALUES ('388', '1', 'admin', '100000', '1523270288888', '1523270288888', '1523270288888', null);
INSERT INTO `login_user` VALUES ('389', '1', 'admin', '100000', '1523271062358', '1523271062358', '1523271062358', null);
INSERT INTO `login_user` VALUES ('390', '1', 'admin', '100000', '1523321652368', '1523321652368', '1523321652368', null);
INSERT INTO `login_user` VALUES ('391', '1', 'admin', '100000', '1523325171411', '1523325171411', '1523325171411', null);
INSERT INTO `login_user` VALUES ('392', '1', 'admin', '100000', '1523325639788', '1523325639787', '1523325639788', '1523325793653');
INSERT INTO `login_user` VALUES ('393', '1', 'admin', '100000', '1523325861535', '1523325861535', '1523325861535', null);
INSERT INTO `login_user` VALUES ('394', '1', 'admin', '100000', '1523326143919', '1523326143919', '1523326143919', null);
INSERT INTO `login_user` VALUES ('395', '1', 'admin', '100000', '1523326150354', '1523326150354', '1523326150354', null);
INSERT INTO `login_user` VALUES ('396', '1', 'admin', '100000', '1523326897133', '1523326897133', '1523326897133', null);
INSERT INTO `login_user` VALUES ('397', '1', 'admin', '100000', '1523327023856', '1523327023856', '1523327023856', null);
INSERT INTO `login_user` VALUES ('398', '1', 'admin', '100000', '1523327171667', '1523327171667', '1523327171667', null);
INSERT INTO `login_user` VALUES ('399', '1', 'admin', '100000', '1523327752209', '1523327752209', '1523327752209', null);
INSERT INTO `login_user` VALUES ('400', '1', 'admin', '100000', '1523328079864', '1523328079864', '1523328079864', null);
INSERT INTO `login_user` VALUES ('401', '1', 'admin', '100000', '1523328218130', '1523328218130', '1523328218130', null);
INSERT INTO `login_user` VALUES ('402', '1', 'admin', '100000', '1523328314146', '1523328314146', '1523328314146', null);
INSERT INTO `login_user` VALUES ('403', '1', 'admin', '100000', '1523328420385', '1523328420385', '1523328420385', null);
INSERT INTO `login_user` VALUES ('404', '1', 'admin', '100000', '1523328410688', '1523328410688', '1523328410688', null);
INSERT INTO `login_user` VALUES ('405', '1', 'admin', '100000', '1523328740183', '1523328740183', '1523328740183', null);
INSERT INTO `login_user` VALUES ('406', '1', 'admin', '100000', '1523328870595', '1523328870595', '1523328870595', null);
INSERT INTO `login_user` VALUES ('407', '1', 'admin', '100000', '1523329059933', '1523329059933', '1523329059933', null);
INSERT INTO `login_user` VALUES ('408', '1', 'admin', '100000', '1523329137031', '1523329137031', '1523329137031', null);
INSERT INTO `login_user` VALUES ('409', '1', 'admin', '100000', '1523329458927', '1523329458927', '1523329458927', null);
INSERT INTO `login_user` VALUES ('410', '1', 'admin', '100000', '1523329641391', '1523329641391', '1523329641391', null);
INSERT INTO `login_user` VALUES ('411', '1', 'admin', '100000', '1523329892124', '1523329892124', '1523329892124', null);
INSERT INTO `login_user` VALUES ('412', '1', 'admin', '100000', '1523330714320', '1523330714320', '1523330714320', null);
INSERT INTO `login_user` VALUES ('413', '1', 'admin', '100000', '1523330769307', '1523330769307', '1523330769307', null);
INSERT INTO `login_user` VALUES ('414', '1', 'admin', '100000', '1523330835935', '1523330835935', '1523330835935', null);
INSERT INTO `login_user` VALUES ('415', '1', 'admin', '100000', '1523330906504', '1523330906504', '1523330906504', null);
INSERT INTO `login_user` VALUES ('416', '1', 'admin', '100000', '1523330895975', '1523330895975', '1523330895975', null);
INSERT INTO `login_user` VALUES ('417', '1', 'admin', '100000', '1523330932655', '1523330932655', '1523330932655', null);
INSERT INTO `login_user` VALUES ('418', '1', 'admin', '100000', '1523331063068', '1523331063068', '1523331063068', null);
INSERT INTO `login_user` VALUES ('419', '1', 'admin', '100000', '1523331347416', '1523331347416', '1523331347416', null);
INSERT INTO `login_user` VALUES ('420', '1', 'admin', '100000', '1523331592555', '1523331592555', '1523331592555', null);
INSERT INTO `login_user` VALUES ('421', '1', 'admin', '100000', '1523331966538', '1523331966538', '1523331966538', null);
INSERT INTO `login_user` VALUES ('422', '1', 'admin', '100000', '1523332499175', '1523332499175', '1523332499175', null);
INSERT INTO `login_user` VALUES ('423', '1', 'admin', '100000', '1523332651066', '1523332651066', '1523332651066', null);
INSERT INTO `login_user` VALUES ('424', '1', 'admin', '100000', '1523333088792', '1523333088792', '1523333088792', null);
INSERT INTO `login_user` VALUES ('425', '1', 'admin', '100000', '1523333246002', '1523333246002', '1523333246002', null);
INSERT INTO `login_user` VALUES ('426', '1', 'admin', '100000', '1523333426420', '1523333426420', '1523333426420', null);
INSERT INTO `login_user` VALUES ('427', '1', 'admin', '100000', '1523333614428', '1523333614428', '1523333614428', null);
INSERT INTO `login_user` VALUES ('428', '1', 'admin', '100000', '1523337978254', '1523337978254', '1523337978254', null);
INSERT INTO `login_user` VALUES ('429', '1', 'admin', '100000', '1523338459502', '1523338459502', '1523338459502', null);
INSERT INTO `login_user` VALUES ('430', '1', 'admin', '100000', '1523338677719', '1523338677719', '1523338677719', null);
INSERT INTO `login_user` VALUES ('431', '1', 'admin', '100000', '1523338848778', '1523338848778', '1523338848778', null);
INSERT INTO `login_user` VALUES ('432', '1', 'admin', '100000', '1523339205434', '1523339205434', '1523339205434', null);
INSERT INTO `login_user` VALUES ('433', '1', 'admin', '100000', '1523339916281', '1523339916281', '1523339916281', null);
INSERT INTO `login_user` VALUES ('434', '1', 'admin', '100000', '1523342154709', '1523342154709', '1523342154709', null);
INSERT INTO `login_user` VALUES ('435', '1', 'admin', '100000', '1523342139403', '1523342139403', '1523342139403', null);
INSERT INTO `login_user` VALUES ('436', '1', 'admin', '100000', '1523342372808', '1523342372808', '1523342372808', null);
INSERT INTO `login_user` VALUES ('437', '1', 'admin', '100000', '1523342434130', '1523342434130', '1523342434130', null);
INSERT INTO `login_user` VALUES ('438', '1', 'admin', '100000', '1523342535890', '1523342535890', '1523342535890', null);
INSERT INTO `login_user` VALUES ('439', '1', 'admin', '100000', '1523343452242', '1523343452242', '1523343452242', null);
INSERT INTO `login_user` VALUES ('440', '1', 'admin', '100000', '1523343674448', '1523343674448', '1523343674448', null);
INSERT INTO `login_user` VALUES ('441', '1', 'admin', '100000', '1523343922424', '1523343922424', '1523343922424', null);
INSERT INTO `login_user` VALUES ('442', '1', 'admin', '100000', '1523344034102', '1523344034102', '1523344034102', null);
INSERT INTO `login_user` VALUES ('443', '1', 'admin', '100000', '1523344204283', '1523344204283', '1523344204283', null);
INSERT INTO `login_user` VALUES ('444', '1', 'admin', '100000', '1523345193879', '1523345193879', '1523345193879', null);
INSERT INTO `login_user` VALUES ('445', '1', 'admin', '100000', '1523345621213', '1523345621213', '1523345621213', null);
INSERT INTO `login_user` VALUES ('446', '1', 'admin', '100000', '1523345947485', '1523345947485', '1523345947485', null);
INSERT INTO `login_user` VALUES ('447', '1', 'admin', '100000', '1523346525469', '1523346525469', '1523346525469', null);
INSERT INTO `login_user` VALUES ('448', '1', 'admin', '100000', '1523346657407', '1523346657407', '1523346657407', null);
INSERT INTO `login_user` VALUES ('449', '1', 'admin', '100000', '1523346967751', '1523346967751', '1523346967751', null);
INSERT INTO `login_user` VALUES ('450', '1', 'admin', '100000', '1523347131802', '1523347131802', '1523347131802', null);
INSERT INTO `login_user` VALUES ('451', '1', 'admin', '100000', '1523347248793', '1523347248793', '1523347248793', null);
INSERT INTO `login_user` VALUES ('452', '1', 'admin', '100000', '1523347219891', '1523347219891', '1523347219891', null);
INSERT INTO `login_user` VALUES ('453', '1', 'admin', '100000', '1523347331254', '1523347331254', '1523347331254', null);
INSERT INTO `login_user` VALUES ('454', '1', 'admin', '100000', '1523347390922', '1523347390922', '1523347390922', null);
INSERT INTO `login_user` VALUES ('455', '1', 'admin', '100000', '1523347311683', '1523347311683', '1523347311683', null);
INSERT INTO `login_user` VALUES ('456', '1', 'admin', '100000', '1523347535237', '1523347535237', '1523347535237', null);
INSERT INTO `login_user` VALUES ('457', '1', 'admin', '100000', '1523347838355', '1523347838355', '1523347838355', null);
INSERT INTO `login_user` VALUES ('458', '1', 'admin', '100000', '1523347943007', '1523347943007', '1523347943007', null);
INSERT INTO `login_user` VALUES ('459', '1', 'admin', '100000', '1523348018159', '1523348018159', '1523348018159', null);
INSERT INTO `login_user` VALUES ('460', '1', 'admin', '100000', '1523348195032', '1523348195032', '1523348195032', null);
INSERT INTO `login_user` VALUES ('461', '1', 'admin', '100000', '1523348411485', '1523348411485', '1523348411485', null);
INSERT INTO `login_user` VALUES ('462', '1', 'admin', '100000', '1523348449838', '1523348449838', '1523348449838', null);
INSERT INTO `login_user` VALUES ('463', '1', 'admin', '100000', '1523348832712', '1523348832712', '1523348832712', null);
INSERT INTO `login_user` VALUES ('464', '1', 'admin', '100000', '1523348889560', '1523348889560', '1523348889560', null);
INSERT INTO `login_user` VALUES ('465', '1', 'admin', '100000', '1523349233409', '1523349233409', '1523349233409', null);
INSERT INTO `login_user` VALUES ('466', '1', 'admin', '100000', '1523349446069', '1523349446069', '1523349446069', null);
INSERT INTO `login_user` VALUES ('467', '1', 'admin', '100000', '1523349622978', '1523349622978', '1523349622978', null);
INSERT INTO `login_user` VALUES ('468', '1', 'admin', '100000', '1523349789350', '1523349789350', '1523349789350', null);
INSERT INTO `login_user` VALUES ('469', '1', 'admin', '100000', '1523349923685', '1523349923685', '1523349923685', null);
INSERT INTO `login_user` VALUES ('470', '1', 'admin', '100000', '1523350067701', '1523350067701', '1523350067701', null);
INSERT INTO `login_user` VALUES ('471', '1', 'admin', '100000', '1523350064951', '1523350064951', '1523350064951', null);
INSERT INTO `login_user` VALUES ('472', '1', 'admin', '100000', '1523350223768', '1523350223768', '1523350223768', null);
INSERT INTO `login_user` VALUES ('473', '1', 'admin', '100000', '1523350583783', '1523350583783', '1523350583783', null);
INSERT INTO `login_user` VALUES ('474', '1', 'admin', '100000', '1523350660705', '1523350660705', '1523350660705', null);
INSERT INTO `login_user` VALUES ('475', '1', 'admin', '100000', '1523350939750', '1523350939750', '1523350939750', null);
INSERT INTO `login_user` VALUES ('476', '1', 'admin', '100000', '1523351215499', '1523351215499', '1523351215499', null);
INSERT INTO `login_user` VALUES ('477', '1', 'admin', '100000', '1523351516188', '1523351516188', '1523351516188', null);
INSERT INTO `login_user` VALUES ('478', '1', 'admin', '100000', '1523351671599', '1523351671599', '1523351671599', null);
INSERT INTO `login_user` VALUES ('479', '1', 'admin', '100000', '1523352480215', '1523352480215', '1523352480215', null);
INSERT INTO `login_user` VALUES ('480', '1', 'admin', '100000', '1523352802124', '1523352802124', '1523352802124', null);
INSERT INTO `login_user` VALUES ('481', '1', 'admin', '100000', '1523352902202', '1523352902202', '1523352902202', null);
INSERT INTO `login_user` VALUES ('482', '1', 'admin', '100000', '1523352907972', '1523352907972', '1523352907972', null);
INSERT INTO `login_user` VALUES ('483', '1', 'admin', '100000', '1523353075827', '1523353075827', '1523353075827', null);
INSERT INTO `login_user` VALUES ('484', '1', 'admin', '100000', '1523353292430', '1523353292430', '1523353292430', null);
INSERT INTO `login_user` VALUES ('485', '1', 'admin', '100000', '1523353342491', '1523353342491', '1523353342491', null);
INSERT INTO `login_user` VALUES ('486', '1', 'admin', '100000', '1523353480702', '1523353480702', '1523353480702', null);
INSERT INTO `login_user` VALUES ('487', '1', 'admin', '100000', '1523353553474', '1523353553474', '1523353553474', null);
INSERT INTO `login_user` VALUES ('488', '1', 'admin', '100000', '1523353661588', '1523353661588', '1523353661588', null);
INSERT INTO `login_user` VALUES ('489', '1', 'admin', '100000', '1523353678908', '1523353678908', '1523353678908', null);
INSERT INTO `login_user` VALUES ('490', '1', 'admin', '100000', '1523353804043', '1523353804043', '1523353804043', null);
INSERT INTO `login_user` VALUES ('491', '1', 'admin', '100000', '1523353892568', '1523353892568', '1523353892568', null);
INSERT INTO `login_user` VALUES ('492', '1', 'admin', '100000', '1523354179686', '1523354179686', '1523354179686', null);
INSERT INTO `login_user` VALUES ('493', '1', 'admin', '100000', '1523354241555', '1523354241555', '1523354241555', null);
INSERT INTO `login_user` VALUES ('494', '1', 'admin', '100000', '1523354301461', '1523354301461', '1523354301461', null);
INSERT INTO `login_user` VALUES ('495', '1', 'admin', '100000', '1523354499329', '1523354499329', '1523354499329', null);
INSERT INTO `login_user` VALUES ('496', '1', 'admin', '100000', '1523354618827', '1523354618827', '1523354618827', null);
INSERT INTO `login_user` VALUES ('497', '1', 'admin', '100000', '1523409641158', '1523409641158', '1523409641158', null);
INSERT INTO `login_user` VALUES ('498', '1', 'admin', '100000', '1523409776344', '1523409776344', '1523409776344', null);
INSERT INTO `login_user` VALUES ('499', '1', 'admin', '100000', '1523410620857', '1523410620857', '1523410620857', null);
INSERT INTO `login_user` VALUES ('500', '1', 'admin', '100000', '1523411003779', '1523411003779', '1523411003779', null);
INSERT INTO `login_user` VALUES ('501', '1', 'admin', '100000', '1523411361283', '1523411361283', '1523411361283', null);
INSERT INTO `login_user` VALUES ('502', '1', 'admin', '100000', '1523411905749', '1523411905749', '1523411905749', null);
INSERT INTO `login_user` VALUES ('503', '1', 'admin', '100000', '1523411849181', '1523411849181', '1523411849181', null);
INSERT INTO `login_user` VALUES ('504', '1', 'admin', '100000', '1523412060542', '1523412060542', '1523412060542', null);
INSERT INTO `login_user` VALUES ('505', '1', 'admin', '100000', '1523412191871', '1523412191871', '1523412191871', null);
INSERT INTO `login_user` VALUES ('506', '1', 'admin', '100000', '1523412137781', '1523412137781', '1523412137781', null);
INSERT INTO `login_user` VALUES ('507', '1', 'admin', '100000', '1523412275171', '1523412275171', '1523412275171', null);
INSERT INTO `login_user` VALUES ('508', '1', 'admin', '100000', '1523412328248', '1523412328248', '1523412328248', null);
INSERT INTO `login_user` VALUES ('509', '1', 'admin', '100000', '1523412564836', '1523412564836', '1523412564836', null);
INSERT INTO `login_user` VALUES ('510', '1', 'admin', '100000', '1523412686791', '1523412686791', '1523412686791', null);
INSERT INTO `login_user` VALUES ('511', '1', 'admin', '100000', '1523412636460', '1523412636460', '1523412636460', null);
INSERT INTO `login_user` VALUES ('512', '1', 'admin', '100000', '1523412969554', '1523412969554', '1523412969554', null);
INSERT INTO `login_user` VALUES ('513', '1', 'admin', '100000', '1523413188897', '1523413188897', '1523413188897', null);
INSERT INTO `login_user` VALUES ('514', '1', 'admin', '100000', '1523413313849', '1523413313849', '1523413313849', null);
INSERT INTO `login_user` VALUES ('515', '1', 'admin', '100000', '1523413598782', '1523413598782', '1523413598782', null);
INSERT INTO `login_user` VALUES ('516', '1', 'admin', '100000', '1523413674637', '1523413674637', '1523413674637', null);
INSERT INTO `login_user` VALUES ('517', '1', 'admin', '100000', '1523413692083', '1523413692083', '1523413692083', null);
INSERT INTO `login_user` VALUES ('518', '1', 'admin', '100000', '1523413993837', '1523413993837', '1523413993837', null);
INSERT INTO `login_user` VALUES ('519', '1', 'admin', '100000', '1523413988810', '1523413988810', '1523413988810', null);
INSERT INTO `login_user` VALUES ('520', '1', 'admin', '100000', '1523414333155', '1523414333155', '1523414333155', null);
INSERT INTO `login_user` VALUES ('521', '1', 'admin', '100000', '1523414498880', '1523414498880', '1523414498880', null);
INSERT INTO `login_user` VALUES ('522', '1', 'admin', '100000', '1523415098391', '1523415098391', '1523415098391', null);
INSERT INTO `login_user` VALUES ('523', '1', 'admin', '100000', '1523415195336', '1523415195336', '1523415195336', null);
INSERT INTO `login_user` VALUES ('524', '1', 'admin', '100000', '1523415387534', '1523415387534', '1523415387534', null);
INSERT INTO `login_user` VALUES ('525', '1', 'admin', '100000', '1523415648063', '1523415648063', '1523415648063', null);
INSERT INTO `login_user` VALUES ('526', '1', 'admin', '100000', '1523415759554', '1523415759554', '1523415759554', null);
INSERT INTO `login_user` VALUES ('527', '1', 'admin', '100000', '1523415924102', '1523415924102', '1523415924102', null);
INSERT INTO `login_user` VALUES ('528', '1', 'admin', '100000', '1523416169130', '1523416169130', '1523416169130', null);
INSERT INTO `login_user` VALUES ('529', '1', 'admin', '100000', '1523416835689', '1523416835689', '1523416835689', null);
INSERT INTO `login_user` VALUES ('530', '1', 'admin', '100000', '1523416916149', '1523416916149', '1523416916149', null);
INSERT INTO `login_user` VALUES ('531', '1', 'admin', '100000', '1523426641379', '1523426641379', '1523426641379', null);
INSERT INTO `login_user` VALUES ('532', '1', 'admin', '100000', '1523427686465', '1523427686465', '1523427686465', null);
INSERT INTO `login_user` VALUES ('533', '1', 'admin', '100000', '1523427740249', '1523427740249', '1523427740249', null);
INSERT INTO `login_user` VALUES ('534', '1', 'admin', '100000', '1523427854568', '1523427854568', '1523427854568', null);
INSERT INTO `login_user` VALUES ('535', '1', 'admin', '100000', '1523427939153', '1523427939153', '1523427939153', null);
INSERT INTO `login_user` VALUES ('536', '1', 'admin', '100000', '1523428149059', '1523428149059', '1523428149059', null);
INSERT INTO `login_user` VALUES ('537', '1', 'admin', '100000', '1523428385007', '1523428385007', '1523428385007', null);
INSERT INTO `login_user` VALUES ('538', '1', 'admin', '100000', '1523428628993', '1523428628993', '1523428628993', null);
INSERT INTO `login_user` VALUES ('539', '1', 'admin', '100000', '1523428663937', '1523428663937', '1523428663937', null);
INSERT INTO `login_user` VALUES ('540', '1', 'admin', '100000', '1523428766230', '1523428766230', '1523428766230', null);
INSERT INTO `login_user` VALUES ('541', '1', 'admin', '100000', '1523428948422', '1523428948422', '1523428948422', null);
INSERT INTO `login_user` VALUES ('542', '1', 'admin', '100000', '1523429161946', '1523429161946', '1523429161946', null);
INSERT INTO `login_user` VALUES ('543', '1', 'admin', '100000', '1523429343969', '1523429343969', '1523429343969', null);
INSERT INTO `login_user` VALUES ('544', '1', 'admin', '100000', '1523429423206', '1523429423206', '1523429423206', null);
INSERT INTO `login_user` VALUES ('545', '1', 'admin', '100000', '1523429464963', '1523429464963', '1523429464963', null);
INSERT INTO `login_user` VALUES ('546', '1', 'admin', '100000', '1523429657236', '1523429657236', '1523429657236', null);
INSERT INTO `login_user` VALUES ('547', '1', 'admin', '100000', '1523429720384', '1523429720384', '1523429720384', null);
INSERT INTO `login_user` VALUES ('548', '1', 'admin', '100000', '1523429841307', '1523429841307', '1523429841307', null);
INSERT INTO `login_user` VALUES ('549', '1', 'admin', '100000', '1523429951182', '1523429951182', '1523429951183', null);
INSERT INTO `login_user` VALUES ('550', '1', 'admin', '100000', '1523429910110', '1523429910110', '1523429910110', null);
INSERT INTO `login_user` VALUES ('551', '1', 'admin', '100000', '1523430019738', '1523430019738', '1523430019738', null);
INSERT INTO `login_user` VALUES ('552', '1', 'admin', '100000', '1523429946890', '1523429946890', '1523429946890', null);
INSERT INTO `login_user` VALUES ('553', '1', 'admin', '100000', '1523430091327', '1523430091327', '1523430091327', null);
INSERT INTO `login_user` VALUES ('554', '1', 'admin', '100000', '1523430027615', '1523430027615', '1523430027615', null);
INSERT INTO `login_user` VALUES ('555', '1', 'admin', '100000', '1523430174701', '1523430174701', '1523430174701', null);
INSERT INTO `login_user` VALUES ('556', '1', 'admin', '100000', '1523430335127', '1523430335127', '1523430335127', null);
INSERT INTO `login_user` VALUES ('557', '1', 'admin', '100000', '1523430510593', '1523430510593', '1523430510593', null);
INSERT INTO `login_user` VALUES ('558', '1', 'admin', '100000', '1523430427708', '1523430427708', '1523430427708', null);
INSERT INTO `login_user` VALUES ('559', '1', 'admin', '100000', '1523430606224', '1523430606224', '1523430606224', null);
INSERT INTO `login_user` VALUES ('560', '1', 'admin', '100000', '1523430688980', '1523430688980', '1523430688980', null);
INSERT INTO `login_user` VALUES ('561', '1', 'admin', '100000', '1523430810599', '1523430810599', '1523430810599', null);
INSERT INTO `login_user` VALUES ('562', '1', 'admin', '100000', '1523430904240', '1523430904240', '1523430904240', null);
INSERT INTO `login_user` VALUES ('563', '1', 'admin', '100000', '1523431294153', '1523431294153', '1523431294153', null);
INSERT INTO `login_user` VALUES ('564', '1', 'admin', '100000', '1523431538563', '1523431538563', '1523431538563', null);
INSERT INTO `login_user` VALUES ('565', '1', 'admin', '100000', '1523431851851', '1523431851851', '1523431851851', null);
INSERT INTO `login_user` VALUES ('566', '1', 'admin', '100000', '1523432111286', '1523432111286', '1523432111286', null);
INSERT INTO `login_user` VALUES ('567', '1', 'admin', '100000', '1523434721660', '1523434721660', '1523434721660', null);
INSERT INTO `login_user` VALUES ('568', '1', 'admin', '100000', '1523435607193', '1523435607193', '1523435607193', null);
INSERT INTO `login_user` VALUES ('569', '1', 'admin', '100000', '1523435796573', '1523435796573', '1523435796573', null);
INSERT INTO `login_user` VALUES ('570', '1', 'admin', '100000', '1523436090533', '1523436090533', '1523436090533', null);
INSERT INTO `login_user` VALUES ('571', '1', 'admin', '100000', '1523436508251', '1523436508251', '1523436508251', null);
INSERT INTO `login_user` VALUES ('572', '1', 'admin', '100000', '1523437180690', '1523437180690', '1523437180690', null);
INSERT INTO `login_user` VALUES ('573', '1', 'admin', '100000', '1523437268663', '1523437268663', '1523437268663', null);
INSERT INTO `login_user` VALUES ('574', '1', 'admin', '100000', '1524017928843', '1524017928843', '1524017928843', null);
INSERT INTO `login_user` VALUES ('575', '1', 'admin', '100000', '1524018366614', '1524018366614', '1524018366614', null);
INSERT INTO `login_user` VALUES ('576', '1', 'admin', '100000', '1524019603706', '1524019603706', '1524019603706', null);
INSERT INTO `login_user` VALUES ('577', '1', 'admin', '100000', '1524020014657', '1524020014657', '1524020014657', null);
INSERT INTO `login_user` VALUES ('578', '1', 'admin', '100000', '1524020792836', '1524020792836', '1524020792836', null);
INSERT INTO `login_user` VALUES ('579', '1', 'admin', '100000', '1524020888266', '1524020888266', '1524020888266', '1524020958399');
INSERT INTO `login_user` VALUES ('580', '1', 'admin', '100000', '1524021325729', '1524021325729', '1524021325729', null);
INSERT INTO `login_user` VALUES ('581', '1', 'admin', '100000', '1524021901922', '1524021901922', '1524021901922', null);
INSERT INTO `login_user` VALUES ('582', '1', 'zhangsan', '100001', '1524022255684', '1524022255684', '1524022255684', null);
INSERT INTO `login_user` VALUES ('583', '1', 'zhangsan', '100001', '1524022282898', '1524022282898', '1524022282898', '1524022595825');
INSERT INTO `login_user` VALUES ('584', '1', 'admin', '100000', '1524022599271', '1524022599271', '1524022599271', null);
INSERT INTO `login_user` VALUES ('585', '1', 'admin', '100000', '1524022777011', '1524022777011', '1524022777011', '1524022790013');
INSERT INTO `login_user` VALUES ('586', '1', 'admin', '100000', '1524022794103', '1524022794103', '1524022794103', null);
INSERT INTO `login_user` VALUES ('587', '1', 'admin', '100000', '1524023628704', '1524023628704', '1524023628704', null);
INSERT INTO `login_user` VALUES ('588', '1', 'admin', '100000', '1524023956842', '1524023956842', '1524023956842', '1524024146064');
INSERT INTO `login_user` VALUES ('589', '1', 'zhangsan', '100001', '1524024159088', '1524024159088', '1524024159088', null);
INSERT INTO `login_user` VALUES ('590', '1', 'admin', '100000', '1524029803849', '1524029803849', '1524029803849', null);
INSERT INTO `login_user` VALUES ('591', '1', 'admin', '100000', '1524031105886', '1524031105886', '1524031105886', '1524031435341');
INSERT INTO `login_user` VALUES ('592', '1', 'admin', '100000', '1524031725411', '1524031725411', '1524031725411', null);
INSERT INTO `login_user` VALUES ('593', '1', 'admin', '100000', '1524031995372', '1524031995372', '1524031995372', null);
INSERT INTO `login_user` VALUES ('594', '1', 'admin', '100000', '1524032136074', '1524032136074', '1524032136074', null);
INSERT INTO `login_user` VALUES ('595', '1', 'admin', '100000', '1524032482030', '1524032482030', '1524032482030', null);
INSERT INTO `login_user` VALUES ('596', '1', 'admin', '100000', '1524032824103', '1524032824103', '1524032824103', null);
INSERT INTO `login_user` VALUES ('597', '1', 'admin', '100000', '1524034284602', '1524034284602', '1524034284602', null);
INSERT INTO `login_user` VALUES ('598', '1', 'admin', '100000', '1524034494988', '1524034494988', '1524034494988', null);
INSERT INTO `login_user` VALUES ('599', '1', 'admin', '100000', '1524035881967', '1524035881967', '1524035881967', null);
INSERT INTO `login_user` VALUES ('600', '1', 'admin', '100000', '1524036199636', '1524036199636', '1524036199636', null);
INSERT INTO `login_user` VALUES ('601', '1', 'admin', '100000', '1524036506635', '1524036506635', '1524036506635', null);
INSERT INTO `login_user` VALUES ('602', '1', 'admin', '100000', '1524036552721', '1524036552721', '1524036552721', null);
INSERT INTO `login_user` VALUES ('603', '1', 'admin', '100000', '1524036605718', '1524036605718', '1524036605718', null);
INSERT INTO `login_user` VALUES ('604', '1', 'admin', '100000', '1524036783290', '1524036783290', '1524036783290', null);
INSERT INTO `login_user` VALUES ('605', '1', 'admin', '100000', '1524037749385', '1524037749385', '1524037749385', null);
INSERT INTO `login_user` VALUES ('606', '1', 'admin', '100000', '1524037783704', '1524037783704', '1524037783704', null);
INSERT INTO `login_user` VALUES ('607', '1', 'admin', '100000', '1524037914722', '1524037914722', '1524037914722', null);
INSERT INTO `login_user` VALUES ('608', '1', 'admin', '100000', '1524038064102', '1524038064102', '1524038064102', null);
INSERT INTO `login_user` VALUES ('609', '1', 'admin', '100000', '1524038855627', '1524038855627', '1524038855627', null);
INSERT INTO `login_user` VALUES ('610', '1', 'admin', '100000', '1524038945530', '1524038945530', '1524038945530', null);
INSERT INTO `login_user` VALUES ('611', '1', 'admin', '100000', '1524039209925', '1524039209925', '1524039209925', '1524039439586');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(64) NOT NULL,
  `menu_url` varchar(128) NOT NULL,
  `status` smallint(6) NOT NULL,
  `gmt_create` bigint(20) NOT NULL,
  `gmt_modify` bigint(20) DEFAULT NULL,
  `creator` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`menu_id`),
  KEY `AK_Key_3` (`menu_url`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('2', 'log', '/log/**', '1', '1520783766592', '1521509807235', '1', '日志管理');
INSERT INTO `menu` VALUES ('6', 'service', '/service/**', '1', '1521179879687', '1521184103790', '3', '业务查询');
INSERT INTO `menu` VALUES ('9', 'money', '/money/**', '1', '1521182382615', '1521509813678', '3', '金额查询');
INSERT INTO `menu` VALUES ('10', 'menu', '/menu/**', '1', '1521182406136', null, '3', '菜单管理');
INSERT INTO `menu` VALUES ('11', 'role', '/role/**', '1', '1521182433152', null, '3', '角色管理');
INSERT INTO `menu` VALUES ('14', 'user', '/user/**', '1', '1521422243130', null, '3', '用户管理');

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
  `creator` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`),
  KEY `AK_Key_2` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'ROLE_ADMIN', '1', '563496856526', null, '1', null);
INSERT INTO `role` VALUES ('2', 'ROLE_USER', '1', '53169828122', null, '1', null);

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
) ENGINE=InnoDB AUTO_INCREMENT=113 DEFAULT CHARSET=utf8 COMMENT='角色菜单表';

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES ('104', '1', '2', '1521512706635', null);
INSERT INTO `role_menu` VALUES ('105', '1', '6', '1521512706667', null);
INSERT INTO `role_menu` VALUES ('106', '1', '9', '1521512706692', null);
INSERT INTO `role_menu` VALUES ('107', '1', '10', '1521512706724', null);
INSERT INTO `role_menu` VALUES ('108', '1', '11', '1521512706748', null);
INSERT INTO `role_menu` VALUES ('109', '1', '14', '1521512706782', null);
INSERT INTO `role_menu` VALUES ('110', '2', '2', '1521514115138', null);
INSERT INTO `role_menu` VALUES ('111', '2', '6', '1521514115188', null);
INSERT INTO `role_menu` VALUES ('112', '2', '14', '1521514115221', null);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `work_number` int(11) NOT NULL,
  `username` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  `email` varchar(32) NOT NULL,
  `phone` varchar(16) NOT NULL,
  `gmt_create` bigint(20) NOT NULL,
  `gmt_modify` bigint(20) DEFAULT NULL,
  `status` smallint(6) NOT NULL COMMENT '0启用中，1被禁用',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '100000', '改个好听的名字', '456', '100000@facebank.cn', '18814384402', '1520783766592', '1520783766592', '0');
INSERT INTO `user` VALUES ('2', '100001', 'zhangsan123', '123', '100001facebank.cn', '18814384685', '1520784554360', '1520784554360', '0');
INSERT INTO `user` VALUES ('3', '100004', 'zzz', '123', '100004@facebank.cn', '18814389648', '1520821220479', '1521425449614', '1');
INSERT INTO `user` VALUES ('4', '100004', 'zhangaasd', '123', '10002', '1881438964', '1520821259711', '1520821259711', '0');
INSERT INTO `user` VALUES ('5', '100005', 'sdfasdff', '123', '100005@facebank.cn', '18814364952', '1520822452672', '1520822452672', '1');
INSERT INTO `user` VALUES ('6', '100006', 'zhangsanfeng', '123', '100006@facebank.cn', '18814384565', '1520823483366', '1520823483366', '0');
INSERT INTO `user` VALUES ('9', '100009', 'zhangsanyu', '123', '100009@facebank.cn', '18814386555', '1520826894310', '1520826894310', '0');
INSERT INTO `user` VALUES ('10', '100010', 'zhangsou', '123', '100010@facebank.cn', '18814386599', '1520826910007', '1520826910007', '0');
INSERT INTO `user` VALUES ('11', '100011', 'zhangsyy', '123', '100011@facebank.cn', '18814386598', '1520826922744', '1520826922744', '0');
INSERT INTO `user` VALUES ('12', '100000', 'admin', '123', '100018@facebank.cn', '18814382222', '1520588597785', '1524031739733', '0');
INSERT INTO `user` VALUES ('13', '100001', 'zhangsan', '456', '100001facebank.cn', '18814384402', '1520589892625', '1521019614187', '0');
INSERT INTO `user` VALUES ('14', '100019', 'qqqqq1', '123', '100019@facebank.cn', '18814382222', '1520836072647', '1520836072647', '1');
INSERT INTO `user` VALUES ('15', '100020', 'qqqqq2', '123', '100020@facebank.cn', '18814382223', '1520836078964', '1520836078964', '1');
INSERT INTO `user` VALUES ('16', '100021', 'qqqqq3', '123', '100021@facebank.cn', '18814382224', '1520836090839', '1520836090839', '1');
INSERT INTO `user` VALUES ('17', '100022', 'pppp', '123', '100022@facebank.cn', '18814362666', '1520836446019', '1520836446019', '0');
INSERT INTO `user` VALUES ('18', '100023', 'pppp1', '123', '100023@facebank.cn', '18814362667', '1520836479505', '1520836479505', '0');
INSERT INTO `user` VALUES ('19', '100024', '张宁奎ddd', '123', '100024@facebank.cn', '18814369558', '1520836971697', '1521012866363', '0');
INSERT INTO `user` VALUES ('20', '100025', '张宁奎abce', '123', '100025@facebank.cn', '18814369559', '1520836978030', '1520836978030', '0');
INSERT INTO `user` VALUES ('21', '100026', '陈qwer2', '123', '100026@facebank.cn', '18845626666', '1520837143167', '1520837143167', '0');
INSERT INTO `user` VALUES ('22', '100027', '陈qwer23', '123', '100027@facebank.cn', '18845626667', '1520837147672', '1520837147672', '0');
INSERT INTO `user` VALUES ('23', '100028', '网dfsrfe', '123', '100028@facebank.cn', '18814389696', '1520837486150', '1520837486150', '0');
INSERT INTO `user` VALUES ('24', '100028', '网dfsrfe', '123', '100028@facebank.cn', '18814389696', '1520837494445', '1520837494445', '0');
INSERT INTO `user` VALUES ('25', '100030', '陈asdlkkk', '123', '100030@facebank.cn', '13421516666', '1520837809646', '1520837809646', '0');
INSERT INTO `user` VALUES ('26', '100030', '陈asdlkkk', '123', '100030@facebank.cn', '13421516666', '1520837820343', '1520837820343', '0');
INSERT INTO `user` VALUES ('27', '100033', '成立pdfger', '123', '100033@facebank.cn', '18834625845', '1520837967521', '1520837967521', '0');
INSERT INTO `user` VALUES ('28', '100033', '成立pdfger', '123', '100033@facebank.cn', '18834625845', '1520841170231', '1520841170231', '0');
INSERT INTO `user` VALUES ('29', '100033', '成立pdfger', '123', '100033@facebank.cn', '18834625845', '1520841173173', '1520841173173', '0');
INSERT INTO `user` VALUES ('30', '100000', '张dfgkweevs', '123', '100000@facebank.cn', '18814384402', '1520841416369', '1520841416369', '0');
INSERT INTO `user` VALUES ('31', '185624', '笑脸金融abc', '123', '18814384406@163.com', '18814384406', '1521179972427', '1521179972427', '0');

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
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('2', '13', '2', '153151231', '31513215');
INSERT INTO `user_role` VALUES ('5', '2', '2', '1521433293435', '1521433293435');
INSERT INTO `user_role` VALUES ('12', '10', '2', '1521441390662', '1521441390662');
INSERT INTO `user_role` VALUES ('13', '9', '2', '1521441406098', '1521441406098');
INSERT INTO `user_role` VALUES ('20', '12', '1', '1521445853331', '1521445853331');
INSERT INTO `user_role` VALUES ('22', '31', '2', '1521445870958', '1521445870958');
INSERT INTO `user_role` VALUES ('28', '11', '2', '1521447424316', '1521447424316');
INSERT INTO `user_role` VALUES ('30', '27', '2', '1521447446643', '1521447446643');
INSERT INTO `user_role` VALUES ('41', '4', '1', '1521512682437', null);
INSERT INTO `user_role` VALUES ('42', '4', '2', '1521512682487', null);
INSERT INTO `user_role` VALUES ('47', '1', '1', '1521526644779', null);
INSERT INTO `user_role` VALUES ('48', '1', '2', '1521526644839', null);
INSERT INTO `user_role` VALUES ('49', '3', '1', '1521526683299', null);
INSERT INTO `user_role` VALUES ('50', '3', '2', '1521526683358', null);
