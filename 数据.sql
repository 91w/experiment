use shop;
SET FOREIGN_KEY_CHECKS=0;


DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `id` int PRIMARY KEY AUTO_INCREMENT COMMENT 'id',
  `userid` int NOT NULL COMMENT '用户id',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `name` varchar(64),
  `phone` varchar(64) ,
  `createtime` DATETIME NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifytime` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更改时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart` (
  `id` int PRIMARY KEY AUTO_INCREMENT COMMENT '主键标识id',
  `userid` int DEFAULT NULL COMMENT '用户id',
  `commid` int DEFAULT NULL COMMENT '商品id',
  `createtime` DATETIME NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifytime` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `status` int(10) DEFAULT '1' COMMENT '0没有选择 1选择'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for collect
-- ----------------------------
DROP TABLE IF EXISTS `collect`;
CREATE TABLE `collect` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '收藏id',
  `commid` int NOT NULL COMMENT '商品id',
  `userid` int NOT NULL COMMENT '用户id',
  `createtime` DATETIME NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `status` int(10) DEFAULT '1' COMMENT '0失效 1正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `commid` int NOT NULL COMMENT '商品id',
  `userid` int NOT NULL COMMENT '评论者id',
  `username` varchar(64) DEFAULT NULL COMMENT '评论人名',
  `userimage` varchar(255) DEFAULT NULL COMMENT '头像',
  `createtime` DATETIME NULL DEFAULT CURRENT_TIMESTAMP COMMENT '评论时间',
  `content` varchar(255) NOT NULL COMMENT '内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of comment

-- ----------------------------
-- Table structure for commimages
-- ----------------------------
DROP TABLE IF EXISTS `commimages`;
CREATE TABLE `commimages` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '图片id',
  `commid` int DEFAULT NULL COMMENT '商品id',
  `image` varchar(255) DEFAULT NULL COMMENT '图片',
  `createtime` DATETIME NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上传时间',
  `imagestatus` int(11) DEFAULT '1' COMMENT '1正常 2删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- ----------------------------
-- Table structure for commodity
-- ----------------------------
DROP TABLE IF EXISTS `commodity`;
CREATE TABLE `commodity` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `commname` varchar(255) DEFAULT NULL COMMENT '商品名',
  `commdesc` varchar(512) DEFAULT NULL COMMENT '商品描述',
  `videourl` varchar(255) DEFAULT NULL COMMENT '视频',
  `orimoney` decimal(55,2) DEFAULT NULL COMMENT '原价',
  `thinkmoney` decimal(55,2) DEFAULT NULL COMMENT '售价',
  `province` varchar(255) DEFAULT NULL COMMENT '所在地区',
  `createtime` DATETIME NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  `modifytime` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `commstatus` int(10) DEFAULT '1' COMMENT '0违规 1正常 2删除  3待审核  4已售出',
  `common` varchar(255) DEFAULT NULL COMMENT '常用选项：自提，可小刀，不议价等选项',
  `category` varchar(255) DEFAULT NULL COMMENT '商品类别',
  `mainimg` varchar(255) DEFAULT NULL COMMENT '主页轮播图',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- ----------------------------
-- Table structure for news
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '新闻id',
  `newstitle` varchar(255) DEFAULT NULL COMMENT '新闻标题',
  `newsdesc` varchar(255) DEFAULT NULL COMMENT '新闻简介',
  `newscontent` longtext COMMENT '新闻内容',
  `createtime` DATETIME NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of news
-- ----------------------------


-- ----------------------------
-- Table structure for reply
-- ----------------------------
DROP TABLE IF EXISTS `reply`;
CREATE TABLE `reply` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `cid` int NOT NULL COMMENT '评论id',
  `commid` int NOT NULL COMMENT '商品id',
  `recontent` varchar(255) DEFAULT NULL COMMENT '回复内容',
  `ruserid` int NOT NULL COMMENT '回复者id',
  `replytime` DATETIME NULL DEFAULT CURRENT_TIMESTAMP COMMENT '回复时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


DROP TABLE IF EXISTS `sale`;
CREATE TABLE `sale` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `userid` int NOT NULL COMMENT '用户id',
  `type` varchar(255) DEFAULT NULL ,
  `used` varchar(255) DEFAULT NULL ,
  `storage` varchar(255) DEFAULT NULL ,
  `channel` varchar(255) DEFAULT NULL ,
  `official` varchar(255) DEFAULT NULL ,
  `battery` varchar(255) DEFAULT NULL ,
  `screen` varchar(255) DEFAULT NULL ,
  `frame` varchar(255) DEFAULT NULL ,
  `accessories` varchar(255) DEFAULT NULL ,
  `other` varchar(255) DEFAULT NULL ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;	


DROP TABLE IF EXISTS `msg`;
CREATE TABLE `msg` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `userid` int NOT NULL COMMENT '用户id',
  `servicecontent` varchar(255) DEFAULT NULL COMMENT '回复内容',
  `usercontent` varchar(255) DEFAULT NULL COMMENT '回复内容',
  `createtime` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT into `msg`(`id`, `userid`, `servicecontent`, `usercontent`)
values (1, 1, '最新款的苹果手机是iPhone 15系列，包括iPhone 15、iPhone 15 Plus、iPhone 15 Pro和iPhone 15 Pro Max。', '苹果手机现在的最新款是什么');



DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `userid` int NOT NULL COMMENT '用户id',
  `status` int(10) DEFAULT '1' COMMENT '1待支付 2进行中(运输什么的) 3已完成',
  `createtime` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatetime` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `expiretime` DATETIME COMMENT '过期时间',
  `paytime` DATETIME COMMENT '支付时间',
  `shiptime` DATETIME COMMENT '运输时间',
  `finishtime` DATETIME COMMENT '结束时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


DROP TABLE IF EXISTS `detail`;
CREATE TABLE `detail` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `orderid` int NOT NULL COMMENT '订单id',
  `commid` int NOT NULL COMMENT '商品id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int(64) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(255) NOT NULL COMMENT '用户昵称',
  `password` varchar(255) NOT NULL COMMENT '用户密码',
  `mobilephone` varchar(255) DEFAULT NULL COMMENT '手机号',
  `email` varchar(255) DEFAULT NULL COMMENT '用户邮箱',
  `uimage` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `sex` varchar(255) DEFAULT NULL COMMENT '用户性别',
  `address` varchar(255) DEFAULT NULL COMMENT '地区',
  `userstatus` int(10) DEFAULT '1' COMMENT '1正常 0封号',
  `createtime` DATETIME NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `updatetime` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` varchar(255) DEFAULT 'offline' COMMENT '用户状态',
  `sign` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user_info
-- ----------------------------


-- ----------------------------
-- Table structure for user_perm
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `userid` int DEFAULT NULL COMMENT '用户ID',
  `roleid` int DEFAULT NULL COMMENT '角色ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `rolekey` int DEFAULT NULL COMMENT '角色标识',
  `rolename` varchar(64) DEFAULT NULL COMMENT '角色名称'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `roleid` int DEFAULT NULL COMMENT '角色ID',
  `roletype` varchar(64) DEFAULT NULL COMMENT '权限'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- SELECT * from commodity ;
--   `id`'商品id',
--   `commname`'商品名',
--   `commdesc`'商品描述',
--   `videourl`'视频',
--   `orimoney`'原价',
--   `thinkmoney`'售价',
--   `province`'所在地区',
--   `createtime`'发布时间',
--   `modifytime`'修改时间',
--   `commstatus` default '0违规 1正常 2删除  3待审核  4已售出',
--   `common` varchar(255) DEFAULT NULL COMMENT '常用选项：自提，可小刀，不议价等选项',
--   `category` varchar(255) DEFAULT NULL COMMENT '商品类别',
--   `mainimg` varchar(255) DEFAULT NULL COMMENT '主页轮播图',
-- update commodity set mainimg = "honor1.jpg" where id = 3;
INSERT INTO `commodity` VALUES 
('1', 'iPhone 13 Pro Max', '95新 iPhone 13 Pro Max 苍岭绿色 256 国行 85%电池665次充电', null, '8999.00', '5199.00', '浙江', '2024-02-15 19:48:39', '2024-02-25 10:55:45', 
'1', '偏远地区包邮 一年质保', '苹果', 'apple1.jpg');
INSERT INTO `commodity` VALUES 
('2', '华为畅享 60 Pro', '99新 华为畅享 60 Pro 星河银 16+512 带包装配件 100%电池', null, '1299.00', '899.00', '北京', '2024-02-15 19:48:39', '2024-02-25 10:55:45', 
'1', '偏远地区包邮 一年质保', '安卓华为', 'huawei1.jpg');
INSERT INTO `commodity` VALUES 
('3', '荣耀Magic6 至臻版', '95新 荣耀Magic6 至臻版 天穹紫 16+1T 带包装配件 100%电池', null, '6799.00', '6599.00', '北京', '2024-02-15 19:48:39', '2024-02-25 10:55:45', 
'1', '偏远地区包邮 一年质保', '安卓荣耀', 'honor1.jpg');


SELECT * from commodity;


INSERT INTO `commodity` VALUES 
('4', '荣耀 Magic6 Pro', '95新 荣耀Magic6 Pro 海湖青 16+1T 带包装配件 100%电池', null, '6299.00', '5499.00', '北京', '2024-02-15 19:48:39', '2024-02-25 10:55:45', 
'1', '偏远地区包邮 一年质保', '安卓荣耀', 'honor4.jpg');
INSERT INTO `commodity` VALUES 
('5', '华为mate60 RS 非凡大师', '99新 华为mate60 RS 非凡大师 玄黑 16+1T 100%电池', null, '12999.00', '10199.00', '北京', '2024-02-15 19:48:39', '2024-02-25 10:55:45', 
'1', '偏远地区包邮 一年质保', '安卓华为', 'huawei4.jpg');
INSERT INTO `commodity` VALUES 
('6', '华为Pocket2 洛可可白', '95新 华为Pocket2 洛可可白 12+512 带包装配件 100%电池', null, '7299.00', '6799.00', '北京', '2024-02-15 19:48:39', '2024-02-25 10:55:45', 
'1', '偏远地区包邮 一年质保', '安卓华为', 'huawei7.jpg');
INSERT INTO `commodity` VALUES 
('7', '华为 Mate60 南糯紫', '99新 华为 Mate60 南糯紫 12+256 带包装配件 96%电池', null, '6299.00', '5899.00', '北京', '2024-02-15 19:48:39', '2024-02-25 10:55:45', 
'1', '偏远地区包邮 一年质保', '安卓华为', 'huawei10.jpg');
INSERT INTO `commodity` VALUES 
('8', 'OPPO Find N3', '99新 OPPO Find N3 潜航黑 12+512 带包装配件 96%电池', null, '8299.00', '6899.00', '上海', '2024-02-15 19:48:39', '2024-02-25 10:55:45', 
'1', '偏远地区包邮 一年质保', '安卓OPPO', 'oppo1.jpg');
INSERT INTO `commodity` VALUES 
('10', 'OPPO Find X7 Ultra', '99新 OPPO Find X7 Ultra 松影墨韵 16+512 带包装配件 100%电池', null, '6299.00', '5199.00', '北京', '2024-02-15 19:48:39', '2024-02-25 10:55:45', 
'1', '偏远地区包邮 一年质保', '安卓OPPO', 'oppo4.jpg');
INSERT INTO `commodity` VALUES 
('11', '小米MIX Fold3', '95新 小米MIX Fold3 月影黑 16+512 带包装配件 100%电池', null, '7299.00', '6199.00', '上海', '2024-02-15 19:48:39', '2024-02-25 10:55:45', 
'1', '偏远地区包邮 一年质保', '安卓小米', 'xiaomi1.jpg');
INSERT INTO `commodity` VALUES 
('12', '小米14 Ultra', '99新 小米14 Ultra 龙晶蓝 12+512 带包装配件 100%电池', null, '6299.00', '5899.00', '深圳', '2024-02-15 19:48:39', '2024-02-25 10:55:45', 
'1', '偏远地区包邮 一年质保', '安卓小米', 'xiaomi4.jpg');
INSERT INTO `commodity` VALUES 
('13', '小米14 Pro', '95新 小米14 Pro 岩石青 16+1T 带包装配件 100%电池', null, '6299.00', '4699.00', '北京', '2024-02-15 19:48:39', '2024-02-25 10:55:45', 
'1', '偏远地区包邮 一年质保', '安卓华为', 'xiaomi7.jpg');
INSERT INTO `commodity` VALUES 
('14', 'vivo X Fold3', '99新 vivo X Fold3 薄翼黑 16+512 带包装配件 100%电池', null, '7299.00', '6899.00', '上海', '2024-02-15 19:48:39', '2024-02-25 10:55:45', 
'1', '偏远地区包邮 一年质保', '安卓VIVO', 'vivo1.jpg');
INSERT INTO `commodity` VALUES 
('15', 'vivo X100 Pro', '99新 vivo X100 Pro 落日橙 16+1T 带包装配件 100%电池', null, '5299.00', '4899.00', '北京', '2024-02-15 19:48:39', '2024-02-25 10:55:45', 
'1', '偏远地区包邮 一年质保', '安卓VIVO', 'vivo4.jpg');
INSERT INTO `commodity` VALUES 
('16', 'iPhone 15', '99新 iPhone 15 绿色 512 国行 资源机', null, '7299.00', '6899.00', '北京', '2024-02-15 19:48:39', '2024-02-25 10:55:45', 
'1', '偏远地区包邮 一年质保', '苹果', 'apple4.jpg');
INSERT INTO `commodity` VALUES 
('17', 'iPhone 15', '99新 iPhone 15 黑色 512 国行 带包装配件 100%电池', null, '7299.00', '6899.00', '上海', '2024-02-15 19:48:39', '2024-02-25 10:55:45', 
'1', '偏远地区包邮 一年质保', '苹果', 'apple7.jpg');
INSERT INTO `commodity` VALUES 
('18', 'iPhone 15', '99新 iPhone 15 蓝色 128 带包装配件 100%电池 已换苹果原装摄像头', null, '6299.00', '4599.00', '北京', '2024-02-15 19:48:39', '2024-02-25 10:55:45', 
'1', '偏远地区包邮 一年质保', '苹果', 'apple10.jpg');
INSERT INTO `commodity` VALUES 
('19', 'iPhone 15 Pro', '95新 iPhone 15 Pro 黑色钛金属 128 国行 100%电池77次充电', null, '8299.00', '6899.00', '北京', '2024-02-15 19:48:39', '2024-02-25 10:55:45', 
'1', '偏远地区包邮 一年质保', '安卓华为', 'apple13.jpg');
INSERT INTO `commodity` VALUES 
('20', 'iPhone 15 Pro', '95新 iPhone 15 Pro 原色钛金属 256 国行 95%电池', null, '9299.00', '7299.00', '北京', '2024-02-15 19:48:39', '2024-02-25 10:55:45', 
'1', '偏远地区包邮 一年质保', '苹果', 'apple16.jpg');
INSERT INTO `commodity` VALUES 
('21', '华为 MatePad Air', '99新 华为 MatePad Air 11.5英寸 曜石黑 8+128 带包装配件', null, '2299.00', '2099.00', '北京', '2024-02-15 19:48:39', '2024-02-25 10:55:45', 
'1', '偏远地区包邮 一年质保', '安卓平板', 'pad1.jpg');
INSERT INTO `commodity` VALUES 
('22', '华为 MatePad Pro 10.8英寸', '95新 华为 MatePad Pro 10.8英寸 夜阑黑 8+512 带包装配件', null, '2699.00', '1699.00', '北京', '2024-02-15 19:48:39', '2024-02-25 10:55:45', 
'1', '偏远地区包邮 一年质保', '安卓平板', 'pad4.jpg');
INSERT INTO `commodity` VALUES 
('23', '华为 MatePad Pro 13.2英寸', '95新 华为 MatePad Pro 13.2英寸 WIFI版 耀金黑 12+512 带包装配件和键盘', null, '6299.00', '5199.00', '北京', '2024-02-15 19:48:39', '2024-02-25 10:55:45', 
'1', '偏远地区包邮 一年质保', '安卓平板', 'pad7.jpg');
INSERT INTO `commodity` VALUES 
('25', '华为 MatePad Pro 11英寸', '95新 华为 MatePad Pro 11英寸 2024款 耀金黑 12+512 带包装配件和键盘', null, '5299.00', '4799.00', '北京', '2024-02-15 19:48:39', '2024-02-25 10:55:45', 
'1', '偏远地区包邮 一年质保', '安卓平板', 'pad10.jpg');
INSERT INTO `commodity` VALUES 
('26', '华为 MatePad Pro 11英寸', '95新 华为 MatePad Pro 11英寸 2024款 晶钻白 12+512 带包装配件', null, '3499.00', '3299.00', '北京', '2024-02-15 19:48:39', '2024-02-25 10:55:45', 
'1', '偏远地区包邮 一年质保', '安卓平板', 'pad13.jpg');
INSERT INTO `commodity` VALUES 
('27', '联想 拯救者 Y900', '95新 联想 拯救者 Y900 天玑9000 钛晶灰 12+512 带包装配件', null, '4299.00', '3899.00', '北京', '2024-02-15 19:48:39', '2024-02-25 10:55:45', 
'1', '偏远地区包邮 一年质保', '安卓平板', 'pad16.jpg');

INSERT INTO `commodity` VALUES 
('28', 'iPad Pro 12.9寸', '99新 iPad Pro 12.9寸 深空灰 256 官换机 100%电池', null, '8299.00', '6199.00', '北京', '2024-02-15 19:48:39', '2024-02-25 10:55:45', 
'1', '偏远地区包邮 一年质保', '苹果平板', 'ipad1.jpg');
INSERT INTO `commodity` VALUES 
('29', 'iPad Pro 12.9寸', '未激活 iPad Pro 12.9寸 银色 256 国行 100%电池', null, '7299.00', '6899.00', '北京', '2024-02-15 19:48:39', '2024-02-25 10:55:45', 
'1', '偏远地区包邮 一年质保', '苹果平板', 'ipad4.jpg');
INSERT INTO `commodity` VALUES 
('30', 'iPad 2022款 10.9寸', '9新 iPad 2022款 10.9寸 银色 64G 国行 93%电池', null, '4299.00', '2499.00', '北京', '2024-02-15 19:48:39', '2024-02-25 10:55:45', 
'1', '偏远地区包邮 一年质保', '苹果平板', 'ipad7.jpg');
INSERT INTO `commodity` VALUES 
('31', 'iPad 2022款 10.9寸', '9新 iPad 2022款 10.9寸 蓝色 256G 带包装配件 94%电池', null, '4299.00', '3499.00', '北京', '2024-02-15 19:48:39', '2024-02-25 10:55:45', 
'1', '偏远地区包邮 一年质保', '苹果平板', 'ipad10.jpg');
INSERT INTO `commodity` VALUES 
('32', 'iPad 2022款 10.9寸', '9新 iPad 2022款 10.9寸 粉色 256G 国行 95%电池', null, '4299.00', '3599.00', '北京', '2024-02-15 19:48:39', '2024-02-25 10:55:45', 
'1', '偏远地区包邮 一年质保', '苹果平板', 'ipad13.jpg');

select id, commname, category, mainimg from commodity;
INSERT INTO `commodity` VALUES 
('33', 'iPhone 14 Pro', '95新 iPhone 14 Pro 银色 512G 国行 95%电池449次充电', null, '7299.00', '6799.00', '北京', '2024-02-15 19:48:39', '2024-02-25 10:55:45', 
'1', '偏远地区包邮 一年质保', '苹果', 'apple19.jpg');
INSERT INTO `commodity` VALUES 
('34', 'iPhone 14 Pro', '准新品 iPhone 14 Pro 金色 128G 国行 100%电池9次充电 资源机', null, '7299.00', '5399.00', '北京', '2024-02-15 19:48:39', '2024-02-25 10:55:45', 
'1', '偏远地区包邮 一年质保', '苹果', 'apple22.jpg');
INSERT INTO `commodity` VALUES 
('35', 'iPhone 14 Pro', '95新 iPhone 14 Pro 暗紫色 512G 国行 85%电池449次充电', null, '7299.00', '6799.00', '北京', '2024-02-15 19:48:39', '2024-02-25 10:55:45', 
'1', '偏远地区包邮 一年质保', '苹果', 'apple25.jpg');
INSERT INTO `commodity` VALUES 
('36', 'iPhone 15 Pro Max', '99新 iPhone 15 Pro Max 白色钛金属 1TB 国行 100%电池', null, '13299.00', '10099.00', '北京', '2024-02-15 19:48:39', '2024-02-25 10:55:45', 
'1', '偏远地区包邮 一年质保', '苹果', 'apple28.jpg');
INSERT INTO `commodity` VALUES 
('37', 'iPhone 14 Plus', '95新 iPhone 14 Plus 紫色 512G 国行 91%电池349次充电', null, '6299.00', '4699.00', '北京', '2024-02-15 19:48:39', '2024-02-25 10:55:45', 
'1', '偏远地区包邮 一年质保', '苹果', 'apple31.jpg');
INSERT INTO `commodity` VALUES 
('38', 'iPhone 13 mini', '9新 iPhone 13 mini 星光色 128G 国行 95%电池149次充电', null, '5299.00', '2799.00', '北京', '2024-02-15 19:48:39', '2024-02-25 10:55:45', 
'1', '偏远地区包邮 一年质保', '苹果', 'apple34.jpg');
INSERT INTO `commodity` VALUES 
('39', '华为 MagicPad 13寸', '95新 华为 MagicPad 13寸 星空灰 8+256 带包装配件', null, '3299.00', '2199.00', '北京', '2024-02-15 19:48:39', '2024-02-25 10:55:45', 
'1', '偏远地区包邮 一年质保', '安卓平板', 'pad19.jpg');
INSERT INTO `commodity` VALUES 
('40', '荣耀 V8 Pro 12.1寸', '9新 荣耀 V8 Pro 12.1寸 燃橙色 8+128 带包装配件', null, '3299.00', '1199.00', '北京', '2024-02-15 19:48:39', '2024-02-25 10:55:45', 
'1', '偏远地区包邮 一年质保', '安卓平板', 'pad22.jpg');
INSERT INTO `commodity` VALUES 
('41', 'OPPO Pad2 11.6寸', '95新 OPPO Pad2 11.6寸 光羽金 8+256 带包装配件', null, '3299.00', '2199.00', '北京', '2024-02-15 19:48:39', '2024-02-25 10:55:45', 
'1', '偏远地区包邮 一年质保', '安卓平板', 'pad25.jpg');
INSERT INTO `commodity` VALUES 
('46', 'OPPO Pad2 11.6寸', '95新 OPPO Pad2 11.6寸 光羽金 8+256 带包装配件', null, '3299.00', '2199.00', '北京', '2024-02-15 19:48:39', '2024-02-25 10:55:45', 
'1', '偏远地区包邮 一年质保', '安卓平板', 'pad25.jpg');


INSERT INTO `commodity` VALUES 
('42', 'iPad Pro 12.9寸', '99新 iPad Pro 12.9寸 深空灰 256 官换机 100%电池', null, '8299.00', '6199.00', '北京', '2024-02-15 19:48:39', '2024-02-25 10:55:45', 
'1', '偏远地区包邮 一年质保', '苹果平板', 'ipad1.jpg');
INSERT INTO `commodity` VALUES 
('43', 'iPad Pro 12.9寸', '99新 iPad Pro 12.9寸 深空灰 256 官换机 100%电池', null, '8299.00', '6199.00', '北京', '2024-02-15 19:48:39', '2024-02-25 10:55:45', 
'1', '偏远地区包邮 一年质保', '苹果平板', 'ipad1.jpg');
INSERT INTO `commodity` VALUES 
('44', 'iPad Pro 12.9寸', '99新 iPad Pro 12.9寸 深空灰 256 官换机 100%电池', null, '8299.00', '6199.00', '北京', '2024-02-15 19:48:39', '2024-02-25 10:55:45', 
'1', '偏远地区包邮 一年质保', '苹果平板', 'ipad1.jpg');
INSERT INTO `commodity` VALUES 
('45', 'iPad Pro 12.9寸', '99新 iPad Pro 12.9寸 深空灰 256 官换机 100%电池', null, '8299.00', '6199.00', '北京', '2024-02-15 19:48:39', '2024-02-25 10:55:45', 
'1', '偏远地区包邮 一年质保', '苹果平板', 'ipad1.jpg');
--   `id` int NOT NULL AUTO_INCREMENT COMMENT '图片id',
--   `commid` int DEFAULT NULL COMMENT '商品id',
--   `image` varchar(255) DEFAULT NULL COMMENT '图片',
--   `createtime` DATETIME NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上传时间',
--   `imagestatus` int(11) DEFAULT '1' COMMENT '1正常 2删除',
SELECT id, commname ,category FROM  commodity;
-- UPDATE commodity set category = "平板" where id BETWEEN 21 and 27;

INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (1, 'apple2.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (1, 'apple1.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (1, 'apple3.jpg', '2024-12-31 19:48:39', '1');

INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (2, 'huawei2.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (2, 'huawei3.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (2, 'huawei1.jpg', '2024-12-31 19:48:39', '1');

INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (3, 'honor2.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (3, 'honor3.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (3, 'honor1.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (4, 'honor4.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (4, 'honor5.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (4, 'honor6.jpg', '2024-12-31 19:48:39', '1');

INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (5, 'huawei4.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (5, 'huawei5.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (5, 'huawei6.jpg', '2024-12-31 19:48:39', '1');

INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (6, 'huawei7.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (6, 'huawei8.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (6, 'huawei9.jpg', '2024-12-31 19:48:39', '1');

INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (7, 'huawei10.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (7, 'huawei11.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (7, 'huawei12.jpg', '2024-12-31 19:48:39', '1');

INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (8, 'oppo1.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (8, 'oppo2.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (8, 'oppo3.jpg', '2024-12-31 19:48:39', '1');

INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (10, 'oppo4.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (10, 'oppo5.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (10, 'oppo6.jpg', '2024-12-31 19:48:39', '1');

INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (11, 'xiaomi1.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (11, 'xiaomi2.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (11, 'xiaomi3.jpg', '2024-12-31 19:48:39', '1');

INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (12, 'xiaomi4.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (12, 'xiaomi5.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (12, 'xiaomi6.jpg', '2024-12-31 19:48:39', '1');

INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (13, 'xiaomi7.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (13, 'xiaomi8.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (13, 'xiaomi9.jpg', '2024-12-31 19:48:39', '1');

INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (16, 'apple4.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (16, 'apple5.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (16, 'apple6.jpg', '2024-12-31 19:48:39', '1');

INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (17, 'apple7.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (17, 'apple8.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (17, 'apple9.jpg', '2024-12-31 19:48:39', '1');

INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (18, 'apple10.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (18, 'apple11.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (18, 'apple12.jpg', '2024-12-31 19:48:39', '1');

INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (19, 'apple13.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (19, 'apple14.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (19, 'apple15.jpg', '2024-12-31 19:48:39', '1');

INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (20, 'apple16.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (20, 'apple17.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (20, 'apple18.jpg', '2024-12-31 19:48:39', '1');

INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (33, 'apple19.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (33, 'apple20.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (33, 'apple21.jpg', '2024-12-31 19:48:39', '1');

INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (34, 'apple22.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (34, 'apple23.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (34, 'apple24.jpg', '2024-12-31 19:48:39', '1');

INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (35, 'apple25.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (35, 'apple26.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (35, 'apple27.jpg', '2024-12-31 19:48:39', '1');

INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (36, 'apple28.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (36, 'apple29.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (36, 'apple30.jpg', '2024-12-31 19:48:39', '1');

INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (37, 'apple31.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (37, 'apple32.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (37, 'apple33.jpg', '2024-12-31 19:48:39', '1');

INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (38, 'apple34.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (38, 'apple35.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (38, 'apple36.jpg', '2024-12-31 19:48:39', '1');


INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (21, 'pad1.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (21, 'pad2.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (21, 'pad3.jpg', '2024-12-31 19:48:39', '1');

INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (22, 'pad4.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (22, 'pad5.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (22, 'pad6.jpg', '2024-12-31 19:48:39', '1');

INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (23, 'pad7.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (23, 'pad8.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (23, 'pad9.jpg', '2024-12-31 19:48:39', '1');

INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (25, 'pad10.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (25, 'pad11.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (25, 'pad12.jpg', '2024-12-31 19:48:39', '1');

INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (26, 'pad13.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (26, 'pad14.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (26, 'pad15.jpg', '2024-12-31 19:48:39', '1');

INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (27, 'pad16.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (27, 'pad17.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (27, 'pad18.jpg', '2024-12-31 19:48:39', '1');

INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (39, 'pad19.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (39, 'pad20.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (39, 'pad21.jpg', '2024-12-31 19:48:39', '1');

INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (40, 'pad22.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (40, 'pad23.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (40, 'pad24.jpg', '2024-12-31 19:48:39', '1');

INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (41, 'pad25.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (41, 'pad26.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (41, 'pad27.jpg', '2024-12-31 19:48:39', '1');

INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (46, 'pad25.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (46, 'pad26.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (46, 'pad27.jpg', '2024-12-31 19:48:39', '1');


INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (28, 'ipad1.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (28, 'ipad2.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (28, 'ipad3.jpg', '2024-12-31 19:48:39', '1');

INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (29, 'ipad4.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (29, 'ipad5.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (29, 'ipad6.jpg', '2024-12-31 19:48:39', '1');

INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (30, 'ipad7.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (30, 'ipad8.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (30, 'ipad9.jpg', '2024-12-31 19:48:39', '1');

INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (31, 'ipad10.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (31, 'ipad11.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (31, 'ipad12.jpg', '2024-12-31 19:48:39', '1');

INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (32, 'ipad13.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (32, 'ipad14.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (32, 'ipad15.jpg', '2024-12-31 19:48:39', '1');

INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (42, 'ipad1.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (42, 'ipad2.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (42, 'ipad3.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (43, 'ipad1.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (43, 'ipad2.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (43, 'ipad3.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (44, 'ipad1.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (44, 'ipad2.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (44, 'ipad3.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (45, 'ipad1.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (45, 'ipad2.jpg', '2024-12-31 19:48:39', '1');
INSERT INTO `commimages` (`commid`, `image`, `createtime`, `imagestatus`) 
VALUES (45, 'ipad3.jpg', '2024-12-31 19:48:39', '1');

SELECT id, category, commname from commodity where category like '%安卓%';
INSERT INTO `cart` values (1, 1, 1, '2024-12-31 19:48:39', null,1);
INSERT into `comment` (`commid`, `userid`, `username`, `userimage`, `content`)
values (1, 1, 'test', 'userimage.jpg', '版本的神！');

SELECT * from address;

INSERT into address (`userid`, `address`, `name`, `phone`)
values (1, '江苏省南京市玄武区紫金山路88号', '张三', '13835107109');

-- 
--   `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
--   `userid` int NOT NULL COMMENT '用户id',
--   `status` int(10) DEFAULT '1' COMMENT '1待支付 2进行中(运输什么的) 3已完成',
--   `commid` int NOT NULL COMMENT '商品id',
--   `createtime` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
--   `updatetime` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
--   `expiretime` DATETIME COMMENT '过期时间',
--   `paytime` DATETIME COMMENT '支付时间',
--   `shiptime` DATETIME COMMENT '运输时间',
--   `finishtime` DATETIME COMMENT '结束时间',
 
INSERT into orders (`userid`, `status`, `createtime`, `finishtime`)
values (1, 3, '2024-02-28 19:48:39', '2024-12-31 19:48:39');

INSERT into detail  (`orderid`, `commid`)
values (1, 38);
SELECT * from commodity;

SELECT * FROM role;
insert into role values (1, 2, '管理员');
insert into role values (2, 1, '用户');
insert into user_info values (2, 'admin', '734d2f66b73652996d2104ac306a33fe', null, null, null, "女", "北京",
                 1,null, null, 1, '这个人很懒什么也没有留下。。。');
