/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50536
Source Host           : localhost:3306
Source Database       : myblog

Target Server Type    : MYSQL
Target Server Version : 50536
File Encoding         : 65001

Date: 2021-08-03 11:39:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_type
-- ----------------------------
DROP TABLE IF EXISTS `t_type`;
CREATE TABLE `t_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_type
-- ----------------------------
INSERT INTO `t_type` VALUES ('1', 'Java');
INSERT INTO `t_type` VALUES ('2', 'C');
INSERT INTO `t_type` VALUES ('3', 'C++');
INSERT INTO `t_type` VALUES ('4', 'pthyon');
INSERT INTO `t_type` VALUES ('5', 'Linux');
INSERT INTO `t_type` VALUES ('6', 'JavaScript');
INSERT INTO `t_type` VALUES ('7', 'springboot');
INSERT INTO `t_type` VALUES ('8', 'redis');
INSERT INTO `t_type` VALUES ('9', 'JavaWeb');
INSERT INTO `t_type` VALUES ('10', '算法');
INSERT INTO `t_type` VALUES ('11', 'springcloud');
INSERT INTO `t_type` VALUES ('12', 'mybatis_plus');
INSERT INTO `t_type` VALUES ('13', 'vue');
INSERT INTO `t_type` VALUES ('14', 'ssm');
