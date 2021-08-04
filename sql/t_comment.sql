/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50536
Source Host           : localhost:3306
Source Database       : myblog

Target Server Type    : MYSQL
Target Server Version : 50536
File Encoding         : 65001

Date: 2021-08-03 11:38:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `blog_id` bigint(20) DEFAULT NULL,
  `parent_comment_id` bigint(20) DEFAULT NULL,
  `admin_comment` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_comment
-- ----------------------------
INSERT INTO `t_comment` VALUES ('31', '111', '2900801226@qq.com', '111', '/images/avatar.png', '2021-08-01 23:24:05', '15', '-1', '\0');
INSERT INTO `t_comment` VALUES ('32', '222', '2900801226@qq.com', '222', '/images/avatar.png', '2021-08-01 23:24:10', '15', '-1', '\0');
INSERT INTO `t_comment` VALUES ('33', '333', '2900801226@qq.com', '333', '/images/avatar.png', '2021-08-01 23:24:19', '15', '31', '\0');
INSERT INTO `t_comment` VALUES ('34', '444', '2900801226@qq.com', '444', '/images/avatar.png', '2021-08-01 23:25:06', '15', '31', '\0');
INSERT INTO `t_comment` VALUES ('35', '555', '2900801226@qq.com', '123', '/images/avatar.png', '2021-08-02 23:40:04', '14', '33', '\0');
INSERT INTO `t_comment` VALUES ('36', '666', '2900801226@qq.com', '666', '/images/avatar.png', '2021-08-02 09:39:43', '15', '32', '\0');
INSERT INTO `t_comment` VALUES ('37', '777', '997075213@qq.com', '777', '/images/avatar.png', '2021-08-02 09:55:11', '15', '36', '\0');
