SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
  `age` int(0)  COMMENT '年龄',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'zhangsan', 34);
INSERT INTO `user` VALUES (2, 'lisi', 33);
INSERT INTO `user` VALUES (3, 'wangwu', 23);
INSERT INTO `user` VALUES (4, 'zhaoliu', 11);
INSERT INTO `user` VALUES (5, 'sunqi', 55);
INSERT INTO `user` VALUES (6, 'zhouba', 34);

SET FOREIGN_KEY_CHECKS = 1;