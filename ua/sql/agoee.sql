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

-- table tbl_account 账户表
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
  UNIQUE KEY `UINDEX_UID` (`uuid`),
  UNIQUE KEY `UINDEX_NAME` (`username`),
  UNIQUE KEY `UINDEX_EMAIL` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- OAuth相关
create table oauth_client_details (
  client_id VARCHAR(255) PRIMARY KEY,
  resource_ids VARCHAR(256),
  client_secret VARCHAR(256),
  scope VARCHAR(256),
  authorized_grant_types VARCHAR(256),
  web_server_redirect_uri VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(256)
);

create table oauth_client_token (
  token_id VARCHAR(256),
  token BLOB,
  authentication_id VARCHAR(256),
  user_name VARCHAR(256),
  client_id VARCHAR(256)
);

create table oauth_access_token (
  token_id VARCHAR(256),
  token BLOB,
  authentication_id VARCHAR(256),
  user_name VARCHAR(256),
  client_id VARCHAR(256),
  authentication BLOB,
  refresh_token VARCHAR(256)
);

create table oauth_refresh_token (
  token_id VARCHAR(256),
  token BLOB,
  authentication BLOB
);

create table oauth_code (
  code VARCHAR(256), authentication BLOB
);

create table oauth_approvals (
	userId VARCHAR(256),
	clientId VARCHAR(256),
	scope VARCHAR(256),
	status VARCHAR(10),
	expiresAt TIMESTAMP,
	lastModifiedAt TIMESTAMP
);

-- customized oauth_client_details table
create table ClientDetails (
  appId VARCHAR(255) PRIMARY KEY,
  resourceIds VARCHAR(256),
  appSecret VARCHAR(256),
  scope VARCHAR(256),
  grantTypes VARCHAR(256),
  redirectUrl VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additionalInformation VARCHAR(4096),
  autoApproveScopes VARCHAR(256)
);
-- OAuth相关

--测试数据
insert into tbl_account (uuid,username,password,email,verify_code) values ('uuid-0001','marissa','koala','email@email.com',null)
insert into oauth_client_details (client_id, client_secret, resource_ids, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, autoapprove) values ('ua', 'secret', 'ua', 'read,write', 'authorization_code', 'http://localhost:8080/tonr2/ua/apis', 'ROLE_USER', null, null, null)

