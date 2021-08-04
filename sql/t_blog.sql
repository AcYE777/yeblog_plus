/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50536
Source Host           : localhost:3306
Source Database       : myblog

Target Server Type    : MYSQL
Target Server Version : 50536
File Encoding         : 65001

Date: 2021-08-03 11:38:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_blog
-- ----------------------------
DROP TABLE IF EXISTS `t_blog`;
CREATE TABLE `t_blog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `appreciation` bit(1) NOT NULL,
  `commentabled` bit(1) NOT NULL,
  `content` longtext,
  `create_time` datetime DEFAULT NULL,
  `description` varchar(525) DEFAULT NULL,
  `first_picture` varchar(525) DEFAULT NULL,
  `flag` varchar(255) DEFAULT NULL,
  `published` bit(1) NOT NULL,
  `recommend` bit(1) NOT NULL,
  `share_statement` bit(1) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `views` int(11) DEFAULT NULL,
  `type_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `comment_count` int(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_blog
-- ----------------------------
INSERT INTO `t_blog` VALUES ('1', '', '', 'xsfdasadf', '2021-08-02 23:21:14', null, 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fwww.idctx.cn%2Fd%2Ffile%2Fp%2F2018-11-20%2Fb5c2eb93e03cd594e9cda6e056ddcd06.jpg&refer=http%3A%2F%2Fwww.idctx.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1630509541&t=b2dfccafe6079361ba10d55eed977c55', null, '', '', '', 'Linux常用命令', '2021-08-02 23:22:08', '23', '5', '1', '0');
INSERT INTO `t_blog` VALUES ('2', '', '', 'sdfsdfsdf', '2021-08-02 23:38:02', null, 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic1.zhimg.com%2Fv2-1b2f4bbab5617fca4bb118562ec97b4f_1200x500.jpg&refer=http%3A%2F%2Fpic1.zhimg.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1630511212&t=97c1566e5097d9c3b31dd4d670b78ec7', null, '', '', '', 'SpringBoot笔记', '2021-08-02 23:38:58', '20', '7', '1', '0');
INSERT INTO `t_blog` VALUES ('3', '', '', 'sdfsafd', '2021-08-02 23:40:37', null, 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fupload-images.jianshu.io%2Fupload_images%2F15083002-fc86084af0504b2e.jpg&refer=http%3A%2F%2Fupload-images.jianshu.io&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1630510857&t=7325e87432e43010f8efb58200b1366c', null, '', '', '', 'Redis相关知识', '2021-08-02 23:41:29', '20', '8', '1', null);
INSERT INTO `t_blog` VALUES ('4', '', '', 'dsfsdf', '2021-08-02 23:50:03', null, 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fi1.51cto.com%2Fimages%2F201508%2F25f6a3b971a0d2dc19e104061fcd4d68cd17b3_big.jpg&refer=http%3A%2F%2Fi1.51cto.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1630511429&t=1edf968c52edc0420083310b59323838', null, '', '', '', 'JavaWeb相关知识', '2021-08-02 23:50:48', '21', '9', '1', '0');
INSERT INTO `t_blog` VALUES ('5', '', '', 'sdfsdfsfd', '2021-08-02 23:52:11', null, 'https://pic.rmb.bdstatic.com/5da751880d6acc39c36604bcb09da63f.jpeg', null, '', '\0', '', '算法', '2021-08-02 23:52:53', '20', '10', '1', null);
INSERT INTO `t_blog` VALUES ('6', '', '', 'sdfsdf', '2021-08-02 23:53:51', null, 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic4.zhimg.com%2Fv2-d88de831dc20cbcbf68fa5c8034d95d8_1440w.jpg%3Fsource%3D172ae18b&refer=http%3A%2F%2Fpic4.zhimg.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1630511644&t=e1e899f932178fe338823626cdccca29', null, '', '\0', '', 'SpringCloud相关知识', '2021-08-02 23:54:22', '20', '11', '1', null);
INSERT INTO `t_blog` VALUES ('7', '', '', '水电费水电费高', '2021-08-02 23:55:30', null, 'https://img1.baidu.com/it/u=1218057473,4082790063&fm=26&fmt=auto&gp=0.jpg', null, '', '\0', '', 'Mybatis_Plus相关知识', '2021-08-02 23:56:08', '20', '12', '1', null);
INSERT INTO `t_blog` VALUES ('8', '', '', 'sdfsdfsfd', '2021-08-02 23:57:14', null, 'https://pics0.baidu.com/feed/94cad1c8a786c91729e9427b0f890ac939c757c0.jpeg?token=f811af2bf588936fdbe9b1286c848eed&s=45912C72C3B07D9000C400DE0000C0B2', null, '', '\0', '', 'Vue相关知识', '2021-08-02 23:57:47', '20', '13', '1', null);
INSERT INTO `t_blog` VALUES ('9', '', '', 'sdfsdfsfd', '2021-08-02 23:58:39', null, 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fit-resourse-hare.oss-cn-hangzhou.aliyuncs.com%2Fvideo%2F19%2F02%2F01%2Fccb6c024983e290066c5b7601a0f0cc5.jpg%3Fx-oss-process%3Dstyle%2F1aa67468f13711e8849060a44c2c6fbb&refer=http%3A%2F%2Fit-resourse-hare.oss-cn-hangzhou.aliyuncs.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1630511938&t=114694c7f643182e2d32eb7d22648762', null, '', '\0', '', 'SSM相关依赖', '2021-08-02 23:59:23', '20', '14', '1', null);
