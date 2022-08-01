/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : video

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2022-07-25 21:46:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for parsing_info
-- ----------------------------
DROP TABLE IF EXISTS `parsing_info`;
CREATE TABLE `parsing_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_open_id` varchar(100) DEFAULT NULL COMMENT 'openId',
  `download_url` varchar(1000) DEFAULT NULL COMMENT '视频链接',
  `title` varchar(500) DEFAULT NULL COMMENT '视频标题',
  `author` varchar(200) DEFAULT NULL COMMENT '视频作者',
  `cover` varchar(1000) DEFAULT NULL COMMENT '视频封面地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for wx_user
-- ----------------------------
DROP TABLE IF EXISTS `wx_user`;
CREATE TABLE `wx_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(30) DEFAULT NULL COMMENT '用户名',
  `video_number` int(11) DEFAULT NULL COMMENT '解析次数',
  `sign_in_sum` int(11) DEFAULT NULL COMMENT '累计签到次数',
  `end_sign_in_time` datetime DEFAULT NULL COMMENT '最后签到时间',
  `open_id` varchar(50) DEFAULT NULL COMMENT '用户openId',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_parsing_time` datetime DEFAULT NULL COMMENT '最后解析时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1551551534896685059 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;
