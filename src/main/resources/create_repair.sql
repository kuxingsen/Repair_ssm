# 建立数据库
CREATE DATABASE repair;
USE repair;

CREATE TABLE `phone_user` (
  `phone` varchar(12) NOT NULL,
  `code` varchar(7) DEFAULT NULL COMMENT '验证码，相当于密码',
  PRIMARY KEY (`phone`)
) ENGINE = InnoDB CHARSET = utf8;

CREATE TABLE `record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL COMMENT '申请人姓名',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `content` varchar(255) DEFAULT NULL COMMENT '维修内容',
  `time` datetime DEFAULT NULL COMMENT '维修时间',
  `sellerName` varchar(20) DEFAULT NULL COMMENT '商家姓名',
  `sellerPhone` varchar(12) DEFAULT NULL COMMENT '商家电话',
  `partPrice` double DEFAULT NULL COMMENT '零件价格',
  `serviceDuration` varchar(12) DEFAULT NULL COMMENT '服务时长',
  `servicePrice` double DEFAULT NULL COMMENT '服务总价',
  `status` TINYINT DEFAULT 0 COMMENT '记录状态，0-未维修，1-商家接单，2-维修中（用户同意商家接单），3-待支付（商家以给出零件价和服务价），4-已支付',
  `user_phone` varchar(12) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_phone` (`user_phone`),
  CONSTRAINT `fk_phone` FOREIGN KEY (`user_phone`) REFERENCES `phone_user` (`phone`)
)ENGINE = InnoDB CHARSET = utf8;

