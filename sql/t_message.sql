/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50536
Source Host           : localhost:3306
Source Database       : myblog

Target Server Type    : MYSQL
Target Server Version : 50536
File Encoding         : 65001

Date: 2021-08-03 11:39:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_message
-- ----------------------------
DROP TABLE IF EXISTS `t_message`;
CREATE TABLE `t_message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `parent_message_id` bigint(20) DEFAULT NULL,
  `admin_message` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_message
-- ----------------------------
INSERT INTO `t_message` VALUES ('3', '123123', '2900801226@qq.com', '/images/avatar.png', '11123213', '2021-08-02 14:22:30', '2', '\0');
INSERT INTO `t_message` VALUES ('5', '111', '2900801226@qq.com', '/images/avatar.png', '1231241234', '2021-08-02 14:57:46', '3', '\0');
INSERT INTO `t_message` VALUES ('7', 'YE', '2900801226@qq.com', 'https://tva3.sinaimg.cn/large/006FsYsrly8gt3cn0hv8ij30dw0alq3y.jpg', '欢迎大家留言呀!', '2021-08-03 01:39:21', '-1', '');
