/*
SQLyog Ultimate v12.2.6 (64 bit)
MySQL - 5.6.43-log : Database - payproject
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`payproject` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `payproject`;

/*Table structure for table `system_resources` */

DROP TABLE IF EXISTS `system_resources`;

CREATE TABLE `system_resources` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '数据id',
  `resourcesId` int(32) NOT NULL COMMENT '资源id',
  `resourcesName` varchar(64) NOT NULL COMMENT '资源名称',
  `resourcesType` int(3) NOT NULL COMMENT '资源类型:按钮、目录等',
  `resourcesKey` varchar(32) NOT NULL COMMENT '资源分类Key',
  `resourcesUrl` varchar(64) NOT NULL COMMENT '资源Url',
  `description` varchar(128) DEFAULT NULL COMMENT '资源描述',
  `createTime` date NOT NULL COMMENT '创建时间',
  `submitTime` date NOT NULL COMMENT '提交时间(保留字段)',
  `submitSystem` varchar(12) NOT NULL COMMENT '提交系统(全文索引)',
  `status` int(3) NOT NULL COMMENT '状态:1可使用；0不可使用',
  UNIQUE KEY `唯一索引` (`id`,`resourcesId`),
  FULLTEXT KEY `全文索引数据分类` (`submitSystem`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜摊资源列表';

/*Data for the table `system_resources` */

/*Table structure for table `system_role` */

DROP TABLE IF EXISTS `system_role`;

CREATE TABLE `system_role` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '数据id',
  `roleId` int(32) NOT NULL COMMENT '角色id(唯一索引)',
  `roleName` varchar(64) NOT NULL COMMENT '角色名称',
  `roleCreateTime` date NOT NULL COMMENT '角色创建时间',
  `roleSubmitTime` date NOT NULL COMMENT '角色提交时间(保留字段)',
  `SubmitSystem` varchar(12) NOT NULL COMMENT '提交系统(全文索引)',
  `status` int(3) DEFAULT NULL COMMENT '状态:1可使用；0不可使用',
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `roleId` (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `system_role` */

/*Table structure for table `system_role_resources` */

DROP TABLE IF EXISTS `system_role_resources`;

CREATE TABLE `system_role_resources` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '数据id',
  `roleId` int(32) NOT NULL COMMENT '角色id',
  `resourcesId` int(32) NOT NULL COMMENT '资源id',
  `createTime` date NOT NULL COMMENT '角色\\资源关联时间',
  `submitTime` date NOT NULL COMMENT '数据提交时间',
  `submitSystem` varchar(12) NOT NULL COMMENT '提交系统(全文索引\\保留字段)',
  `status` int(3) NOT NULL COMMENT '状态:1可使用；0不可使用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色资源关联表';

/*Data for the table `system_role_resources` */

/*Table structure for table `system_user` */

DROP TABLE IF EXISTS `system_user`;

CREATE TABLE `system_user` (
  `id` int(64) NOT NULL AUTO_INCREMENT COMMENT '数据id(唯一索引)',
  `userId` varchar(32) NOT NULL COMMENT '用户id(唯一索引)',
  `userName` varchar(32) DEFAULT NULL COMMENT '用户昵称',
  `userPassword` varchar(72) NOT NULL COMMENT '用户登录密码',
  `userMail` varchar(64) DEFAULT NULL COMMENT '用户邮箱',
  `userPhone` varchar(32) DEFAULT NULL COMMENT '用户电话',
  `userQQ` varchar(20) DEFAULT NULL COMMENT '用户QQ',
  `userWechar` varchar(20) DEFAULT NULL COMMENT '用户微信',
  `userType` int(3) DEFAULT NULL COMMENT '用户类型:保留字段',
  `userAddress` varchar(32) DEFAULT NULL COMMENT '用户联系地址',
  `userCity` varchar(32) DEFAULT NULL COMMENT '用户所在城市',
  `createTime` date NOT NULL COMMENT '用户创建时间',
  `submitTime` date NOT NULL COMMENT '用户提交时间:保留字段',
  `submitSystem` varchar(12) NOT NULL COMMENT '提交系统(全文索引)',
  `status` int(2) DEFAULT NULL COMMENT '状态:1可使用；0不可使用',
  UNIQUE KEY `id` (`id`,`userId`),
  FULLTEXT KEY `SubmitSystem` (`submitSystem`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户基础登录信息表';

/*Data for the table `system_user` */

/*Table structure for table `system_user_role` */

DROP TABLE IF EXISTS `system_user_role`;

CREATE TABLE `system_user_role` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '数据id',
  `userId` varchar(64) NOT NULL COMMENT '用户id',
  `role` int(32) NOT NULL COMMENT '角色id',
  `createTime` date NOT NULL COMMENT '用户\\角色关联时间',
  `submitTime` date NOT NULL COMMENT '数据提交时间',
  `submitSystem` varchar(12) NOT NULL COMMENT '数据提交系统',
  `status` int(3) NOT NULL COMMENT '数据状态:1可使用；0不可使用',
  PRIMARY KEY (`id`),
  FULLTEXT KEY `数据分类索引` (`submitSystem`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

/*Data for the table `system_user_role` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
