/*
 Navicat Premium Data Transfer

 Source Server         : 本机数据库
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : ling_order

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 18/04/2022 17:15:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bse_order
-- ----------------------------
DROP TABLE IF EXISTS `bse_order`;
CREATE TABLE `bse_order`  (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `sn` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单编号',
  `money` decimal(10, 0) NULL DEFAULT NULL COMMENT '金额',
  `type` int(1) NULL DEFAULT NULL COMMENT '订单类型',
  `pay_type` int(1) NULL DEFAULT NULL COMMENT '支付类型(1支付宝，2微信)',
  `third_sn` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '第三方支付编号',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '操作用户',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bse_order
-- ----------------------------
INSERT INTO `bse_order` VALUES (2792658738438209536, '7432b17d2f8e834b08c3d83e2fe926ae', 0, 1, 1, 'cb7eb9d621f9089949338adcf6a26de0', 3, '2022-03-30 15:00:09', '2022-03-30 15:00:09');
INSERT INTO `bse_order` VALUES (2792659884355616768, 'cee7c1ea6df50889553e7e65ed5be530', 0, 1, 1, '85ad7b2d617a19d3f626cc89e98d0102', 1, '2022-03-30 15:02:25', '2022-03-30 15:02:25');

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
  `log_created` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT 'create datetime',
  `log_modified` datetime(6) NOT NULL COMMENT 'modify datetime',
  UNIQUE INDEX `ux_undo_log`(`xid`, `branch_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'AT transaction mode undo table' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of undo_log
-- ----------------------------
INSERT INTO `undo_log` VALUES (252429619498176512, '192.168.31.157:8091:252429610421702656', 'serializer=jackson', 0x7B7D, 1, '2022-03-30 13:46:37.388719', '2022-03-30 13:46:37.388719');

SET FOREIGN_KEY_CHECKS = 1;
