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

/*Table structure for table `system_log` */

DROP TABLE IF EXISTS `system_log`;

CREATE TABLE `system_log` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '数据id',
  `clientIp` varchar(32) NOT NULL COMMENT '客户端ip',
  `requertUrl` varchar(32) NOT NULL COMMENT '请求映射路径',
  `metHod` varchar(256) NOT NULL COMMENT '方法',
  `param` varchar(128) DEFAULT NULL COMMENT '参数',
  `operator` varchar(32) DEFAULT NULL COMMENT '操作人',
  `startTime` varchar(32) DEFAULT NULL COMMENT '请求接口时间',
  `endTime` varchar(32) DEFAULT NULL COMMENT '接口返回时间',
  `totalTime` varchar(32) DEFAULT NULL COMMENT '总消耗时间',
  `returnData` text COMMENT '接口返回数据',
  `createTime` datetime NOT NULL COMMENT '数据创建时间',
  `submitTime` datetime NOT NULL COMMENT '提交时间',
  `submitSystem` varchar(12) NOT NULL COMMENT '提交系统',
  `status` int(3) NOT NULL COMMENT '状态:1可使用；0不可使用',
  `retain1` varchar(32) DEFAULT NULL COMMENT '保留字段',
  `retain2` varchar(32) DEFAULT NULL COMMENT '保留字段',
  `retain3` varchar(32) DEFAULT NULL COMMENT '保留字段',
  `retain4` varchar(32) DEFAULT NULL COMMENT '保留字段',
  `retain5` varchar(32) DEFAULT NULL COMMENT '保留字段',
  `retain6` varchar(32) DEFAULT NULL COMMENT '保留字段',
  `retain7` varchar(32) DEFAULT NULL COMMENT '保留字段',
  `retain8` varchar(32) DEFAULT NULL COMMENT '保留字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `system_log` */

insert  into `system_log`(`id`,`clientIp`,`requertUrl`,`metHod`,`param`,`operator`,`startTime`,`endTime`,`totalTime`,`returnData`,`createTime`,`submitTime`,`submitSystem`,`status`,`retain1`,`retain2`,`retain3`,`retain4`,`retain5`,`retain6`,`retain7`,`retain8`) values 
(1,'127.0.0.1','/ login ','com.payProject.system.contorller.LoginContorller.login ()',NULL,'pp','2019-08-02 15 :24 :47 ','2019-08-02 15 :24 :47','1970-01-01 08 :00 :00','java.io.StringReader @5 f5dfada (StringReader)','2019-08-02 15:29:55','2019-08-02 15:29:55','PP',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(2,'127.0.0.1','/login','com.payProject.system.contorller.LoginContorller.login()',NULL,'pp','2019-08-02 15:30:30','2019-08-02 15:30:30','1970-01-01 08:00:00','login','2019-08-02 15:30:30','2019-08-02 15:30:30','PP',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(3,'127.0.0.1','/login','com.payProject.system.contorller.LoginContorller.login()',NULL,'pp','2019-08-02 15:30:31','2019-08-02 15:30:31','1970-01-01 08:00:00','login','2019-08-02 15:30:31','2019-08-02 15:30:31','PP',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(4,'127.0.0.1','/login','com.payProject.system.contorller.LoginContorller.login()',NULL,'pp','2019-08-02 15:30:32','2019-08-02 15:30:32','1970-01-01 08:00:00','login','2019-08-02 15:30:32','2019-08-02 15:30:32','PP',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(5,'127.0.0.1','/login','com.payProject.system.contorller.LoginContorller.login()',NULL,'pp','2019-08-02 15:30:32','2019-08-02 15:30:32','1970-01-01 08:00:00','login','2019-08-02 15:30:32','2019-08-02 15:30:32','PP',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(6,'127.0.0.1','/login','com.payProject.system.contorller.LoginContorller.login()',NULL,'系统未登录刷新','2019-08-02 15:32:32','2019-08-02 15:32:32','1970-01-01 08:00:00','login','2019-08-02 15:32:32','2019-08-02 15:32:32','PP',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(7,'127.0.0.1','/login','com.payProject.system.contorller.LoginContorller.login()',NULL,'系统未登录刷新','2019-08-02 15:33:48','2019-08-02 15:33:48','4','login','2019-08-02 15:33:48','2019-08-02 15:33:48','PP',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `system_resources` */

DROP TABLE IF EXISTS `system_resources`;

CREATE TABLE `system_resources` (
  `resourcesId` int(32) NOT NULL AUTO_INCREMENT COMMENT '资源id',
  `resourcesName` varchar(64) NOT NULL COMMENT '资源名称',
  `resourcesType` int(3) DEFAULT NULL COMMENT '资源类型:按钮、目录等',
  `resourcesKey` varchar(32) NOT NULL COMMENT '资源分类Key',
  `parentId` int(12) DEFAULT NULL COMMENT '父级菜单id',
  `resourcesUrl` varchar(64) DEFAULT NULL COMMENT '资源Url',
  `description` varchar(128) DEFAULT NULL COMMENT '资源描述',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `submitTime` datetime NOT NULL COMMENT '提交时间(保留字段)',
  `submitSystem` varchar(12) NOT NULL COMMENT '提交系统(全文索引)',
  `status` int(3) NOT NULL COMMENT '状态:1可使用；0不可使用',
  `rank` int(12) DEFAULT NULL COMMENT '资源排名',
  `level` int(3) NOT NULL COMMENT '资源等级',
  `retain1` varchar(32) DEFAULT NULL COMMENT '备用字段',
  `retain2` varchar(32) DEFAULT NULL COMMENT '备用字段',
  `retain3` varchar(32) DEFAULT NULL COMMENT '备用字段',
  `retain4` varchar(32) DEFAULT NULL COMMENT '备用字段',
  `retain5` varchar(32) DEFAULT NULL COMMENT '备用字段',
  `retain6` varchar(32) DEFAULT NULL COMMENT '备用字段',
  UNIQUE KEY `唯一索引` (`resourcesId`),
  FULLTEXT KEY `全文索引数据分类` (`submitSystem`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='菜单资源列表';

/*Data for the table `system_resources` */

insert  into `system_resources`(`resourcesId`,`resourcesName`,`resourcesType`,`resourcesKey`,`parentId`,`resourcesUrl`,`description`,`createTime`,`submitTime`,`submitSystem`,`status`,`rank`,`level`,`retain1`,`retain2`,`retain3`,`retain4`,`retain5`,`retain6`) values 
(1,'系统管理',NULL,'system-manage',NULL,'','这是测试数据','2019-08-06 18:36:47','2019-08-06 18:36:47','PP',1,0,0,NULL,NULL,NULL,NULL,NULL,NULL),
(2,'角色管理',NULL,'system-role-manage',1,'/system/role/roleShow','这是测试数据','2019-08-06 18:45:49','2019-08-06 19:49:27','PP',1,1,1,NULL,NULL,NULL,NULL,NULL,NULL),
(3,'用户管理',NULL,'system-user-manage',1,'/system/user/userShow','这是测试数据','2019-08-06 18:48:26','2019-08-06 19:45:28','PP',1,1,1,NULL,NULL,NULL,NULL,NULL,NULL),
(4,'资源管理',NULL,'system-resources-manage',1,'/system/resources/resourcesShow','这是测试数据','2019-08-06 18:49:38','2019-08-06 19:45:26','PP',1,1,1,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `system_role` */

DROP TABLE IF EXISTS `system_role`;

CREATE TABLE `system_role` (
  `roleId` int(32) NOT NULL AUTO_INCREMENT COMMENT '角色id(唯一索引)',
  `roleName` varchar(64) NOT NULL COMMENT '角色名称',
  `remark` varchar(32) DEFAULT NULL COMMENT '角色备注',
  `createTime` datetime NOT NULL COMMENT '角色创建时间',
  `submitTime` datetime NOT NULL COMMENT '角色提交时间(保留字段)',
  `SubmitSystem` varchar(12) NOT NULL COMMENT '提交系统(全文索引)',
  `status` int(3) DEFAULT NULL COMMENT '状态:1可使用；0不可使用',
  UNIQUE KEY `roleId` (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `system_role` */

insert  into `system_role`(`roleId`,`roleName`,`remark`,`createTime`,`submitTime`,`SubmitSystem`,`status`) values 
(1,'系统管理员','系统源数据不要删除','2019-08-06 11:55:52','2019-08-06 14:32:04','PP',1),
(2,'运营','这是测试运营角色','2019-08-06 13:48:39','2019-08-06 13:48:39','PP',1),
(3,'客服','这是测试数据','2019-08-06 14:18:46','2019-08-06 14:18:46','PP',1);

/*Table structure for table `system_role_resources` */

DROP TABLE IF EXISTS `system_role_resources`;

CREATE TABLE `system_role_resources` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '数据id',
  `roleId` int(32) NOT NULL COMMENT '角色id',
  `resourcesId` int(32) NOT NULL COMMENT '资源id',
  `createTime` datetime NOT NULL COMMENT '角色\\资源关联时间',
  `submitTime` datetime NOT NULL COMMENT '数据提交时间',
  `submitSystem` varchar(12) NOT NULL COMMENT '提交系统(全文索引\\保留字段)',
  `status` int(3) NOT NULL COMMENT '状态:1可使用；0不可使用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='角色资源关联表';

/*Data for the table `system_role_resources` */

insert  into `system_role_resources`(`id`,`roleId`,`resourcesId`,`createTime`,`submitTime`,`submitSystem`,`status`) values 
(4,2,2,'2019-08-07 19:34:23','2019-08-07 19:34:23','PP',1),
(5,2,1,'2019-08-07 19:34:23','2019-08-07 19:34:23','PP',1),
(6,2,4,'2019-08-07 19:34:23','2019-08-07 19:34:23','PP',1),
(7,3,3,'2019-08-07 19:42:28','2019-08-07 19:42:28','PP',1),
(8,3,1,'2019-08-07 19:42:28','2019-08-07 19:42:28','PP',1),
(9,3,3,'2019-08-07 19:43:38','2019-08-07 19:43:38','PP',1),
(10,3,1,'2019-08-07 19:43:38','2019-08-07 19:43:38','PP',1),
(11,3,4,'2019-08-07 19:43:38','2019-08-07 19:43:38','PP',1),
(12,1,2,'2019-08-09 18:48:44','2019-08-09 18:48:44','PP',1),
(13,1,1,'2019-08-09 18:48:44','2019-08-09 18:48:44','PP',1),
(14,1,3,'2019-08-09 18:48:44','2019-08-09 18:48:44','PP',1),
(15,1,4,'2019-08-09 18:48:44','2019-08-09 18:48:44','PP',1),
(16,1,2,'2019-08-09 18:54:28','2019-08-09 18:54:28','PP',1),
(17,1,1,'2019-08-09 18:54:30','2019-08-09 18:54:30','PP',1),
(18,1,4,'2019-08-09 18:54:31','2019-08-09 18:54:31','PP',1);

/*Table structure for table `system_user` */

DROP TABLE IF EXISTS `system_user`;

CREATE TABLE `system_user` (
  `id` int(64) NOT NULL AUTO_INCREMENT COMMENT '数据id(唯一索引)',
  `userId` varchar(32) NOT NULL COMMENT '用户id(唯一索引)',
  `userName` varchar(32) DEFAULT NULL COMMENT '用户昵称',
  `userPassword` varchar(72) NOT NULL COMMENT '用户登录密码',
  `userSalt` varchar(72) NOT NULL COMMENT '用户加密盐值(这个值确保每个用户不存在一样的加密值)',
  `userMail` varchar(64) DEFAULT NULL COMMENT '用户邮箱',
  `userPhone` varchar(32) DEFAULT NULL COMMENT '用户电话',
  `userQQ` varchar(20) DEFAULT NULL COMMENT '用户QQ',
  `userWechar` varchar(20) DEFAULT NULL COMMENT '用户微信',
  `userType` int(3) DEFAULT NULL COMMENT '用户类型:保留字段',
  `userAddress` varchar(32) DEFAULT NULL COMMENT '用户联系地址',
  `userCity` varchar(32) DEFAULT NULL COMMENT '用户所在城市',
  `createTime` datetime NOT NULL COMMENT '用户创建时间',
  `submitTime` datetime NOT NULL COMMENT '用户提交时间:保留字段',
  `submitSystem` varchar(12) NOT NULL COMMENT '提交系统(全文索引)',
  `payPassword` varchar(72) NOT NULL COMMENT '支付密码',
  `status` int(2) DEFAULT NULL COMMENT '状态:1可使用；0不可使用',
  `retain1` varchar(32) DEFAULT NULL COMMENT '保留字段1',
  `retain2` varchar(32) DEFAULT NULL COMMENT '保留字段2',
  `retain3` varchar(32) DEFAULT NULL COMMENT '保留字段3',
  `retain4` varchar(32) DEFAULT NULL COMMENT '保留字段4',
  `retain5` varchar(32) DEFAULT NULL COMMENT '保留字段5',
  `retain6` varchar(32) DEFAULT NULL COMMENT '保留字段6',
  `retain7` varchar(32) DEFAULT NULL COMMENT '保留字段7',
  `retain8` varchar(32) DEFAULT NULL COMMENT '保留字段8',
  `retain9` varchar(32) DEFAULT NULL COMMENT '保留字段9',
  `retain10` varchar(32) DEFAULT NULL COMMENT '保留字段10',
  UNIQUE KEY `id` (`id`,`userId`),
  FULLTEXT KEY `SubmitSystem` (`submitSystem`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COMMENT='用户基础登录信息表';

/*Data for the table `system_user` */

insert  into `system_user`(`id`,`userId`,`userName`,`userPassword`,`userSalt`,`userMail`,`userPhone`,`userQQ`,`userWechar`,`userType`,`userAddress`,`userCity`,`createTime`,`submitTime`,`submitSystem`,`payPassword`,`status`,`retain1`,`retain2`,`retain3`,`retain4`,`retain5`,`retain6`,`retain7`,`retain8`,`retain9`,`retain10`) values 
(1,'5999384236','测试','587b91991792944687ebb6a0f6451fb3','59993842366829791ecd68eccd830d95de5adf190c','1111@qq.com',NULL,NULL,NULL,0,NULL,NULL,'2019-08-01 00:00:00','2019-08-01 00:00:00','PP','f33ab060b6743285d1ac1839189145d9',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(5,'8674263101','测试数据','72cd2fafb1d241e0a8cb282ec3175125','8674263101bd2cb4e5fdad3ddd72535ce5043e7e59','qqqq@qq.com','','','',0,'','','2019-08-05 14:18:44','2019-08-05 14:18:44','PP','6f4186fc9d65f94132317f085d19758c',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(12,'6077333166','123,','a5b31bb0d8d929f7df4724925e9b5906','6077333166afb12c3c2a0fe7ba9847b03173bb0b9a','123@qq.com','','',NULL,0,'','','2019-08-05 14:13:07','2019-08-05 14:13:07','PP','6e07e031f543423359bcc5a29f628341',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(14,'6810782049','我是测试','f3bc2c675af701030de897f1c01b03de','68107820490c5f5d5abbd3ca0311c842a60b128fb2','11@mail.com','','','',0,'','','2019-08-05 14:20:21','2019-08-05 14:20:21','PP','aa689532a293b17efe35755d04d20110',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(15,'8918616157','我是测试','a94be8f83f5515eea4b68b04a7ff0ca8','89186161577ac6cc7352aedc70ff2118002c4e52ea','11@mail.com',NULL,NULL,NULL,0,NULL,NULL,'2019-08-01 16:25:29','2019-08-01 16:25:29','PP','9751eb38035fc14d6ac729c58b9734ca',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(18,'8206906427','123,','16ecbd1684feebcce5c17627a81d82d8','8206906427095a044531bf52f609b411670dea427f','2@qq.com','','',NULL,0,'','','2019-08-05 14:14:41','2019-08-05 14:14:41','PP','b0398aea8e0b03897385a30a7cc7a39a',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(19,'4718318463','测试KENT,','241c0867a3f56e75deead6153a8e86dc','47183184639fc93bc544033e279b8b842daa1f0a31','123456@QQ.COM','','',NULL,0,'','','2019-08-05 14:16:40','2019-08-05 14:16:40','PP','11c74a4f02671a91a3fda4caed2eccbf',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(20,'3299026706','测试kent1,','8af581befdd0577481b1a9765583e995','3299026706b416fe26eb884057f44225c8156638ac','123456@qq.com','','',NULL,0,'','','2019-08-05 14:14:47','2019-08-05 14:14:47','PP','5a791b1bcff34cb645812cb412c31dea',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(21,'9891867314','1111','5d620aaf6d458ccbb305d3da2acb253b','98918673146ac3ff1569a05050108c52fbd54ce01b','111@qq.com','','','',0,'','','2019-08-05 14:27:28','2019-08-05 14:27:28','PP','e6f45e0129780bb6c41125c2554cd053',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(22,'7943561489','我是张学友','1b1a2f2fcceb703d4b4e19c8c6489c6d','79435614898fc4b18485777503bf5df8d8f41de2cb','2222@qq.com','1355555555','8989696996','848486869',0,'香港中环','北京市','2019-08-03 20:55:41','2019-08-05 14:33:53','PP','ec087d5b7773b90d63d86b57b6386241',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(23,'4389002318','11111','47698eee94b3578a54b16515560f5790','438900231817ffd933d5b9a849df82ce99e9cbc82e','222','','','',0,'','','2019-08-05 14:28:29','2019-08-05 14:28:29','PP','238188c39ee46aa38d15827a5f533734',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(24,'1687875836','刘德华','a35c25866ce00bd307767f45601f687b','168787583678fdb72c3023e4a78d8262f0e8eb01b9','5544544@asjdh.com','13565656665','123123121','213211',0,'北京5环','上海内环','2019-08-05 14:30:40','2019-08-05 14:30:40','PP','c10b6edee71a786654ea6859f8630da4',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(29,'3708979384','kent','6617561734d91f768a57d7b75eb33d71','3708979384613535ace516e85e4b289d545c81338b','123@qq.com',NULL,NULL,NULL,0,NULL,NULL,'2019-08-05 10:16:31','2019-08-05 10:16:31','PP','324fefaabc497916a59e401d08a174c1',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(30,'7752784115','我是kent测试数据','bc991f9729619ed7f16340c87fbdbdd4','77527841155856e5ebdc8ed2fe7ae6c6faa568d7b7','123456@qq.com','15333335555','898546322','微信',0,'北京市','马尼拉','2019-08-07 14:52:02','2019-08-07 14:53:08','PP','0b8e09b4218d230202ca698b4ab9f945',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `system_user_role` */

DROP TABLE IF EXISTS `system_user_role`;

CREATE TABLE `system_user_role` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '数据id',
  `userId` varchar(64) NOT NULL COMMENT '用户id',
  `role` int(32) NOT NULL COMMENT '角色id',
  `createTime` datetime NOT NULL COMMENT '用户\\角色关联时间',
  `submitTime` datetime NOT NULL COMMENT '数据提交时间',
  `submitSystem` varchar(12) NOT NULL COMMENT '数据提交系统',
  `status` int(3) NOT NULL COMMENT '数据状态:1可使用；0不可使用',
  PRIMARY KEY (`id`),
  FULLTEXT KEY `数据分类索引` (`submitSystem`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

/*Data for the table `system_user_role` */

insert  into `system_user_role`(`id`,`userId`,`role`,`createTime`,`submitTime`,`submitSystem`,`status`) values 
(1,'20',1,'2019-08-08 16:58:18','2019-08-08 16:58:18','PP',1),
(9,'12',1,'2019-08-08 17:18:48','2019-08-08 17:18:48','PP',1),
(10,'12',2,'2019-08-08 17:18:48','2019-08-08 17:18:48','PP',1),
(11,'30',1,'2019-08-09 19:07:36','2019-08-09 19:07:36','PP',1),
(12,'30',2,'2019-08-09 19:07:36','2019-08-09 19:07:36','PP',1),
(13,'30',3,'2019-08-09 19:07:36','2019-08-09 19:07:36','PP',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
