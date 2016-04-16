BEGIN;
INSERT INTO `role_info` VALUES ('1', '系统管理员', 'sys_manager');
INSERT INTO `user_info` VALUES ('1', 'test', '098f6bcd4621d373cade4e832627b4f6', '098f6bcd4621d373cade4e832627b4f6', '098f6bcd4621d373cade4e832627b4f6');
INSERT INTO `user_role` VALUES ('1', '1', '1', 'sys_manager');
COMMIT;