# Host: localhost  (Version 5.7.21-log)
# Date: 2018-04-12 15:13:10
# Generator: MySQL-Front 6.0  (Build 2.20)


#
# Structure for table "dept"
#

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

#
# Data for table "dept"
#

INSERT INTO `dept` VALUES (1,10001,'技术部',NULL,0,0,0),(2,10001,'人事部',NULL,0,0,0);
