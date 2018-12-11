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
  `name` varchar(20) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `user_phone` varchar(12) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_phone` (`user_phone`),
  CONSTRAINT `fk_phone` FOREIGN KEY (`user_phone`) REFERENCES `phone_user` (`phone`)
)ENGINE = InnoDB CHARSET = utf8;

