/*
 Navicat Premium Data Transfer

 Source Server         : fbl
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : fire_control

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 26/01/2021 08:14:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES (1, 1);
INSERT INTO `role_permission` VALUES (1, 2);
INSERT INTO `role_permission` VALUES (1, 3);
INSERT INTO `role_permission` VALUES (2, 1);
INSERT INTO `role_permission` VALUES (1, 4);
INSERT INTO `role_permission` VALUES (1, 5);

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名字',
  `code` char(4) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单权限码',
  `menu_grade` tinyint(1) NOT NULL COMMENT '菜单级别',
  `father_id` int(11) NULL DEFAULT NULL COMMENT '父级菜单id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (1, '内部管理', '0001', 1, 0);
INSERT INTO `sys_permission` VALUES (2, '列表页', '0002', 1, 0);
INSERT INTO `sys_permission` VALUES (3, '角色管理', '0011', 2, 1);
INSERT INTO `sys_permission` VALUES (4, '新增', '0111', 3, 3);
INSERT INTO `sys_permission` VALUES (5, '编辑', '0112', 3, 3);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名',
  `role_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 'admin', '管理员');
INSERT INTO `sys_role` VALUES (2, 'user', '普通用户');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `is_disable` tinyint(1) NOT NULL DEFAULT 0 COMMENT '0 禁用 1 可用',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name_idx`(`user_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '$2a$10$KPfacMGkjaiHXefOL0/Nl.B96TyNa1.IIejJi/pBh8ygI/WEplbca', '2021-01-21 14:39:09', '2021-01-21 14:45:56', 1);
INSERT INTO `sys_user` VALUES (2, 'user', '$2a$10$KPfacMGkjaiHXefOL0/Nl.B96TyNa1.IIejJi/pBh8ygI/WEplbca', '2021-01-21 14:39:09', '2021-01-21 14:47:33', 1);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, 1);
INSERT INTO `user_role` VALUES (1, 2);
INSERT INTO `user_role` VALUES (2, 2);

SET FOREIGN_KEY_CHECKS = 1;
