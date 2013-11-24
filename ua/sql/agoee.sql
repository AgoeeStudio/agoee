# ************************************************************
# Sequel Pro SQL dump
# Version 4096
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: 124.40.117.19 (MySQL 5.5.23-55-log)
# Database: agoee
# Generation Time: 2013-11-24 20:48:09 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table tbl_account
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tbl_account`;

CREATE TABLE `tbl_account` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `uuid` varchar(36) CHARACTER SET ascii NOT NULL DEFAULT '' COMMENT '通用唯一识别码',
  `username` varchar(12) CHARACTER SET ascii NOT NULL DEFAULT '',
  `password` varchar(64) CHARACTER SET ascii NOT NULL DEFAULT '' COMMENT '密码MD5摘要',
  `email` varchar(64) CHARACTER SET ascii NOT NULL DEFAULT '',
  `verify_code` varchar(64) CHARACTER SET ascii DEFAULT '' COMMENT 'Base64激活验证码，含验证超时信息，NULL为已激活',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UINDEX_UID` (`uuid`,`username`,`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
