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

 Date: 08/02/2021 08:20:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `department_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '部门名称',
  `department_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门描述',
  `father_id` int(11) NULL DEFAULT 0 COMMENT '父部门id 默认为0',
  `department_grade` smallint(1) NULL DEFAULT NULL COMMENT '部门级别',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES (1, '信息化部', '开发软件程序', 0, 1, '2021-01-28 12:30:32', '2021-01-28 13:41:27');
INSERT INTO `department` VALUES (2, '开发部', '代码研发', 1, 2, '2021-01-28 12:32:42', '2021-02-04 07:53:51');
INSERT INTO `department` VALUES (3, '设计部', '设计项目图片方案', 1, 2, '2021-01-28 12:33:10', '2021-01-28 13:41:29');
INSERT INTO `department` VALUES (4, '运维部', '维护项目运行', 1, 2, '2021-01-28 12:33:24', '2021-01-28 13:41:29');
INSERT INTO `department` VALUES (5, '财务部', '管钱的', 0, 1, '2021-01-28 14:49:56', '2021-01-28 14:49:56');
INSERT INTO `department` VALUES (26, '1313', NULL, 0, 1, '2021-02-02 10:27:11', '2021-02-02 10:27:11');
INSERT INTO `department` VALUES (27, '1314', NULL, 26, 2, '2021-02-02 10:42:55', '2021-02-02 10:42:55');

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
INSERT INTO `role_permission` VALUES (2, 1);
INSERT INTO `role_permission` VALUES (2, 2);
INSERT INTO `role_permission` VALUES (2, 3);
INSERT INTO `role_permission` VALUES (2, 4);
INSERT INTO `role_permission` VALUES (2, 5);
INSERT INTO `role_permission` VALUES (2, 6);
INSERT INTO `role_permission` VALUES (2, 7);
INSERT INTO `role_permission` VALUES (2, 8);
INSERT INTO `role_permission` VALUES (2, 9);
INSERT INTO `role_permission` VALUES (2, 10);
INSERT INTO `role_permission` VALUES (2, 13);
INSERT INTO `role_permission` VALUES (2, 14);
INSERT INTO `role_permission` VALUES (1, 1);
INSERT INTO `role_permission` VALUES (1, 2);
INSERT INTO `role_permission` VALUES (1, 3);
INSERT INTO `role_permission` VALUES (1, 4);
INSERT INTO `role_permission` VALUES (1, 5);
INSERT INTO `role_permission` VALUES (1, 6);
INSERT INTO `role_permission` VALUES (1, 7);
INSERT INTO `role_permission` VALUES (1, 8);
INSERT INTO `role_permission` VALUES (1, 9);
INSERT INTO `role_permission` VALUES (1, 10);
INSERT INTO `role_permission` VALUES (1, 11);
INSERT INTO `role_permission` VALUES (1, 12);
INSERT INTO `role_permission` VALUES (1, 13);
INSERT INTO `role_permission` VALUES (1, 14);
INSERT INTO `role_permission` VALUES (1, 15);
INSERT INTO `role_permission` VALUES (1, 17);
INSERT INTO `role_permission` VALUES (1, 18);

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `operation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作',
  `method` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '方法名',
  `params` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数名',
  `ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电脑ip',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES (1, 'admin', '用户登录', 'com.hongseng.app.controller.LoginController.login', '[{\"password\":\"123\",\"userName\":\"admin\"}]', '192.168.131.1', '2021-02-05 11:50:45');
INSERT INTO `sys_log` VALUES (2, 'admin', '用户登录', 'com.hongseng.app.controller.LoginController.login', '[{\"password\":\"123\",\"userName\":\"admin\"}]', '192.168.131.1', '2021-02-05 11:54:06');
INSERT INTO `sys_log` VALUES (3, 'admin', '用户登录', 'com.hongseng.app.controller.LoginController.login', '[{\"password\":\"123\",\"userName\":\"admin\"}]', '192.168.1.103', '2021-02-05 12:07:18');
INSERT INTO `sys_log` VALUES (4, 'lisi', '用户登录', 'com.hongseng.app.controller.LoginController.login', '[{\"password\":\"123\",\"userName\":\"lisi\"}]', '192.168.131.1', '2021-02-05 14:24:26');
INSERT INTO `sys_log` VALUES (5, 'lisi', '用户登录', 'com.hongseng.app.controller.LoginController.login', '[{\"password\":\"234\",\"userName\":\"lisi\"}]', '192.168.131.1', '2021-02-05 14:24:33');
INSERT INTO `sys_log` VALUES (6, 'admin', '用户登录', 'com.hongseng.app.controller.LoginController.login', '[{\"password\":\"123\",\"userName\":\"admin\"}]', '192.168.1.103', '2021-02-05 14:54:14');
INSERT INTO `sys_log` VALUES (7, 'admin', '用户登录', 'com.hongseng.app.controller.LoginController.login', '[{\"password\":\"123\",\"userName\":\"admin\"}]', '192.168.1.106', '2021-02-05 15:06:09');
INSERT INTO `sys_log` VALUES (8, 'admin', '用户登录', 'com.hongseng.app.controller.LoginController.login', '[{\"password\":\"123\",\"userName\":\"admin\"}]', '192.168.1.103', '2021-02-05 15:17:03');
INSERT INTO `sys_log` VALUES (9, 'admin', '用户登录', 'com.hongseng.app.controller.LoginController.login', '[{\"password\":\"123\",\"userName\":\"admin\"}]', '192.168.1.103', '2021-02-05 15:17:07');
INSERT INTO `sys_log` VALUES (10, 'admin', '用户登录', 'com.hongseng.app.controller.LoginController.login', '[{\"password\":\"123\",\"userName\":\"admin\"}]', '192.168.131.1', '2021-02-05 15:17:38');
INSERT INTO `sys_log` VALUES (11, 'admin', '用户登录', 'com.hongseng.app.controller.LoginController.login', '[{\"password\":\"123456\",\"userName\":\"admin\"}]', '192.168.1.103', '2021-02-05 15:19:30');
INSERT INTO `sys_log` VALUES (12, 'admin', '用户登录', 'com.hongseng.app.controller.LoginController.login', '[{\"password\":\"123456\",\"userName\":\"admin\"}]', '192.168.1.103', '2021-02-05 15:28:58');
INSERT INTO `sys_log` VALUES (13, 'admin', '用户登录', 'com.hongseng.app.controller.LoginController.login', '[{\"password\":\"123456\",\"userName\":\"admin\"}]', '192.168.1.103', '2021-02-05 15:40:02');
INSERT INTO `sys_log` VALUES (14, 'admin', '用户登录', 'com.hongseng.app.controller.LoginController.login', '[{\"password\":\"123\",\"userName\":\"admin\"}]', '192.168.1.105', '2021-02-07 13:43:31');
INSERT INTO `sys_log` VALUES (15, 'admin', '用户登录', 'com.hongseng.app.controller.LoginController.login', '[{\"password\":\"a123123\",\"userName\":\"admin\"}]', '192.168.1.105', '2021-02-07 13:43:36');
INSERT INTO `sys_log` VALUES (16, 'admin', '用户登录', 'com.hongseng.app.controller.LoginController.login', '[{\"password\":\"a123123\",\"userName\":\"admin\"}]', '192.168.1.105', '2021-02-07 13:43:41');
INSERT INTO `sys_log` VALUES (17, 'admin', '用户登录', 'com.hongseng.app.controller.LoginController.login', '[{\"password\":\"123456\",\"userName\":\"admin\"}]', '192.168.1.105', '2021-02-07 13:43:44');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名字',
  `code` char(4) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单权限码',
  `menu_grade` tinyint(1) NOT NULL COMMENT '菜单级别',
  `father_id` int(11) NULL DEFAULT 0 COMMENT '父级菜单id 默认为0',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `code_idx`(`code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (1, 'Dashboard', '0001', 1, 0);
INSERT INTO `sys_permission` VALUES (2, '表单页', '0002', 1, 0);
INSERT INTO `sys_permission` VALUES (3, '列表页', '0003', 1, 0);
INSERT INTO `sys_permission` VALUES (4, '详情页', '0004', 1, 0);
INSERT INTO `sys_permission` VALUES (5, '结果页', '0005', 1, 0);
INSERT INTO `sys_permission` VALUES (6, '异常页', '0006', 1, 0);
INSERT INTO `sys_permission` VALUES (7, '个人页', '0007', 1, 0);
INSERT INTO `sys_permission` VALUES (8, '内部管理', '0008', 1, 0);
INSERT INTO `sys_permission` VALUES (9, '角色管理', '0018', 2, 8);
INSERT INTO `sys_permission` VALUES (10, '新增', '0118', 3, 9);
INSERT INTO `sys_permission` VALUES (11, '编辑', '0218', 3, 9);
INSERT INTO `sys_permission` VALUES (12, '删除', '0318', 3, 9);
INSERT INTO `sys_permission` VALUES (13, '设置权限', '0418', 3, 9);
INSERT INTO `sys_permission` VALUES (14, '部门管理', '0028', 2, 8);
INSERT INTO `sys_permission` VALUES (15, '用户管理', '0038', 2, 8);
INSERT INTO `sys_permission` VALUES (17, '查看详情', '0138', 3, 15);
INSERT INTO `sys_permission` VALUES (18, '编辑', '0238', 3, 15);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名',
  `role_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '系统管理员', '我是大Boss，都听我的');
INSERT INTO `sys_role` VALUES (2, '开发工程师', '专注java开发三十年');
INSERT INTO `sys_role` VALUES (8, '1', '234324');

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
  `is_available` tinyint(1) NOT NULL DEFAULT 1 COMMENT '0 禁用 1 可用',
  `department_id` int(11) NULL DEFAULT NULL COMMENT '部门id',
  `sex` int(1) NULL DEFAULT 1 COMMENT '0 女 1 男',
  `telephone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '座机',
  `mobile` bigint(20) NULL DEFAULT NULL COMMENT '手机',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `last_login_time` timestamp(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name_idx`(`user_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '$2a$10$Op3Vb2KJTHbLXzpHEu4vr.mhCNjZQ0Oo4YFkZgZ.uameG.c6eQDUi', '2021-01-21 14:39:08', '2021-02-05 16:33:34', 1, 3, 1, '1100000', 13683758197, 'f13683758197@163.com', '2021-02-07 13:43:44');
INSERT INTO `sys_user` VALUES (2, 'lisi', '$2a$10$q5VuOJhArBLZrDE2wrYBtuIAHHRnDEXxo77HHS1ATomvFhduG5Vby', '2021-01-21 14:39:09', '2021-02-05 15:41:19', 1, 2, 0, '1111111', 13683758197, 'f13683758197@163.com', '2021-02-05 14:24:33');
INSERT INTO `sys_user` VALUES (6, '范保林', '$2a$10$4WkBPi7iUfbsVyKjOxRFz.bZ1w1VOpJa6QFnF4Sfs8VaMdOluKDN6', '2021-02-05 11:59:47', '2021-02-05 16:33:38', 1, 2, 1, '02884449802', 13131313131, '345@adf.v', NULL);
INSERT INTO `sys_user` VALUES (7, 'zhangsan', '$2a$10$w1uHPvlZXg2.9WZWn8hNMeDM3uC8uRnYetcWRPa6IAIXlOaAHWKsm', '2021-02-05 13:41:12', '2021-02-05 16:33:30', 1, 3, 1, '02884449802', 13683758197, '345@adf.v', NULL);
INSERT INTO `sys_user` VALUES (8, '234', '$2a$10$KZEfP6gqz5SxFGNcYY.9jeB/NAJFodjazqaKO/M/WZBU1/kmQe/BS', '2021-02-07 13:44:42', '2021-02-07 13:45:44', 1, 5, 1, '02884449802', 13180810712, '345@adf.v', NULL);

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
INSERT INTO `user_role` VALUES (6, 1);
INSERT INTO `user_role` VALUES (6, 2);
INSERT INTO `user_role` VALUES (6, 8);
INSERT INTO `user_role` VALUES (1, 1);
INSERT INTO `user_role` VALUES (1, 2);
INSERT INTO `user_role` VALUES (2, 2);
INSERT INTO `user_role` VALUES (7, 2);
INSERT INTO `user_role` VALUES (7, 8);
INSERT INTO `user_role` VALUES (8, 1);
INSERT INTO `user_role` VALUES (8, 2);
INSERT INTO `user_role` VALUES (8, 8);

SET FOREIGN_KEY_CHECKS = 1;
