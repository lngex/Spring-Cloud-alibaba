/*
 Navicat Premium Data Transfer

 Source Server         : 本机数据库
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : ling_user

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 18/04/2022 17:15:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bse_user
-- ----------------------------
DROP TABLE IF EXISTS `bse_user`;
CREATE TABLE `bse_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名字',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `job` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职业',
  `vip` int(1) NULL DEFAULT NULL COMMENT '0否，1级vip',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bse_user
-- ----------------------------
INSERT INTO `bse_user` VALUES (1, 'lingex', 69, 'java开发攻城狮', 1, '2022-03-22 18:12:49', '2022-03-30 15:02:25');
INSERT INTO `bse_user` VALUES (2, 'lingex', 69, 'java开发攻城狮', 0, '2022-03-22 18:12:49', '2022-03-24 12:35:43');
INSERT INTO `bse_user` VALUES (3, 'lingex', 69, 'java开发攻城狮', 1, '2022-03-22 18:12:49', '2022-03-30 15:00:08');
INSERT INTO `bse_user` VALUES (4, 'lingex', 69, 'java开发攻城狮', 0, '2022-03-22 18:12:49', '2022-03-22 18:12:51');
INSERT INTO `bse_user` VALUES (5, 'lingex', 69, 'java开发攻城狮', 0, '2022-03-22 18:12:49', '2022-03-22 18:12:51');

-- ----------------------------
-- Table structure for undo_log
-- ----------------------------
DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE `undo_log`  (
  `branch_id` bigint(20) NOT NULL COMMENT 'branch transaction id',
  `xid` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'global transaction id',
  `context` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'undo_log context,such as serialization',
  `rollback_info` longblob NOT NULL COMMENT 'rollback info',
  `log_status` int(11) NOT NULL COMMENT '0:normal status,1:defense status',
  `log_created` datetime(6) NOT NULL COMMENT 'create datetime',
  `log_modified` datetime(6) NOT NULL COMMENT 'modify datetime',
  UNIQUE INDEX `ux_undo_log`(`xid`, `branch_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'AT transaction mode undo table' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of undo_log
-- ----------------------------
INSERT INTO `undo_log` VALUES (252429614360154112, '192.168.31.157:8091:252429610421702656', 'serializer=jackson', 0x7B7D, 1, '2022-03-30 13:57:48.376063', '2022-03-30 13:57:48.376063');

SET FOREIGN_KEY_CHECKS = 1;
