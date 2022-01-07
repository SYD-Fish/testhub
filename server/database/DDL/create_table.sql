/*
 Navicat Premium Data Transfer

 Source Server         : gym
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : rm-uf6n6u176m4zxbd75xo.mysql.rds.aliyuncs.com:3306
 Source Schema         : test_hub

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 03/01/2022 14:12:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu`  (
                           `menu_id` int(11) NOT NULL AUTO_INCREMENT,
                           `menu_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                           `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                           `index` int(11) DEFAULT NULL,
                           `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                           `parent_id` int(11) DEFAULT NULL,
                           `enable` tinyint(4) DEFAULT 1,
                           `create_time` datetime(0) DEFAULT NULL,
                           `update_time` datetime(0) DEFAULT NULL,
                           PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
                           `user_id` int(11) NOT NULL AUTO_INCREMENT,
                           `user_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                           `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                           `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                           `phone` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                           `login_status` tinyint(4) DEFAULT NULL,
                           `enable` tinyint(4) DEFAULT NULL,
                           `create_time` datetime(0) DEFAULT NULL,
                           `update_time` datetime(0) DEFAULT NULL,
                           PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
