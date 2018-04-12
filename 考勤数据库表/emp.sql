# Host: localhost  (Version 5.7.21-log)
# Date: 2018-04-12 15:13:20
# Generator: MySQL-Front 6.0  (Build 2.20)


#
# Structure for table "emp"
#

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

#
# Data for table "emp"
#

INSERT INTO `emp` VALUES (1,100000,'管理员','18814384402','100001@facebank.cn',NULL,0.0,0.0,0.0,NULL,0,0,0),(2,100001,'zhangsan','18814384685','100001@facebank.com',NULL,0.0,40.0,51.0,100006,0,0,0),(3,100006,'zhangsanfeng','18814384565','100006@facebank.cn',NULL,0.0,0.0,0.0,100007,0,0,0),(4,100007,'zhangsanfengya','18814386545','100007@facebank.cn',NULL,0.0,0.0,0.0,100009,0,0,0),(5,100009,'zhangsanyu','18814386555','100009@facebank.cn',NULL,0.0,0.0,0.0,NULL,0,0,0),(6,89,'杨海龙','18814383222','100000@facebank.com',10001,22.0,33.0,44.0,NULL,0,0,0),(7,29,'王茂青','18814383266','100949@facebank.com',10001,66.0,34.0,12.0,NULL,0,0,0),(8,61,'王晓东','18814384377','100044@facebank.com',10001,0.0,0.0,0.0,NULL,0,0,0);
