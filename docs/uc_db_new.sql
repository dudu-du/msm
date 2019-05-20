CREATE DATABASE `uc` DEFAULT CHARACTER SET utf8 ;

USE `uc`;


DROP TABLE IF EXISTS `login`;
CREATE TABLE `login` (
  `id` varchar(40) NOT NULL,
  `loginName1` VARCHAR(30) NOT NULL,
  `loginName2` VARCHAR(30) ,
  `loginName3` VARCHAR(30) ,
  `loginName4` VARCHAR(30) ,
  `password` VARCHAR(50) NOT NULL,
  `realname` VARCHAR(30) NOT NULL ,
  `tel` VARCHAR(30) NULL ,
  `email` VARCHAR(30) NULL ,
  `del` int(11) NOT NULL DEFAULT 0,
  `createdatetime` datetime NOT NULL,
  `modifydatetime` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;


-- code 超时时间 定为10分钟
DROP TABLE IF EXISTS `access_code`;
CREATE TABLE `access_code` (
  `id` varchar(40) NOT NULL,
  `client_id` VARCHAR(50) NOT NULL,
  `user_id` varchar(40) NOT NULL ,
  `access_code` VARCHAR(50) NOT NULL,
  `expiresin` VARCHAR (50) NOT NULL,
  `createdatetime` DATETIME NOT NULL,
  `modifydatetime` DATETIME NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

--  token 的超时时间定为24小时
DROP TABLE IF EXISTS `access_token`;
CREATE TABLE `access_token` (
  `id` varchar(40) NOT NULL,
  `client_id` varchar(40) NOT NULL,
  `user_id` varchar(40) NOT NULL ,
  `access_token` VARCHAR(50) NOT NULL,
  `expiresin` VARCHAR (50) NOT NULL,
  `createdatetime` DATETIME NOT NULL,
  `modifydatetime` DATETIME NOT NULL,
  `token_type` varchar(20) NOT NULL  COMMENT '0是人员token  1为系统token' ,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;


-- 敏感词表
DROP TABLE IF EXISTS `sensitive`;
CREATE TABLE `sensitive` (
  `id` varchar(40) NOT NULL ,
  `words` varchar(40) NOT NULL comment '敏感词',
  `sort` int(11) NOT NULL DEFAULT 0,
  `del` int(11) NOT NULL DEFAULT 0,
  `type`  int(11) NOT NULL DEFAULT 0 comment '敏感词类型',
  `createdatetime` datetime NOT NULL,
  `modifydatetime` datetime NOT NULL,
  PRIMARY KEY (`id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- 字典表
DROP TABLE IF EXISTS `dict`;
CREATE TABLE `dict` (
  `id` varchar(40) NOT NULL ,
  `parent_id` varchar(40) NOT NULL,
  `code` varchar(30) NOT NULL,
  `name` varchar(30) NOT NULL,
  `sort` int(11) NOT NULL,
  `del` int(11) NOT NULL DEFAULT 0,
  `type`  int(11) NOT NULL DEFAULT 0 comment '字典类型',
  `createdatetime` datetime NOT NULL,
  `modifydatetime` datetime NOT NULL,
  PRIMARY KEY (`id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;
--机构类型 人员类型 角色类型 学校类型 学段 班级

INSERT INTO `dict` VALUES ('0729a7e9983b48c49595d59e9f7e1a85', 'WE_XUEJI', 'XJ_WUNIANJI', '五年级', '5', '0', '7', '2018-11-21 17:09:45', '2018-11-21 17:09:48');
INSERT INTO `dict` VALUES ('0a574bd75b242d794a84d8c4ca059c2', 'WE_XUEDUAN', 'XD_CHUZHONG', '初中', '3', '0', '2', '2018-11-17 16:25:58', '2018-11-17 16:26:01');
INSERT INTO `dict` VALUES ('0fa163009aa340bb8e3fd7975278ef8a', 'WE_PERSONTYPE', 'PT_TEACHER', '教师', '0', '0', '8', '2018-11-19 13:53:44', '2018-11-19 13:54:03');
INSERT INTO `dict` VALUES ('0fb98251e006416c961cb8cea71523b2', 'WE_SCHOOLTYPE', 'ST_GAOZHONG', '高中', '3', '0', '5', '2018-11-19 13:53:44', '2018-11-19 13:54:03');
INSERT INTO `dict` VALUES ('1156373908344fdf9b2b341e6c78814c', 'WE_SCHOOLTYPE', 'ST_XIAOXUE', '小学', '1', '0', '5', '2018-11-19 13:50:37', '2018-11-19 13:50:34');
INSERT INTO `dict` VALUES ('1bb07d5638f2420f9d2945dfe797f628', '0', 'WE_XUEDUAN', '学段', '0', '0', '2', '2018-11-19 10:03:04', '2018-11-19 10:03:07');
INSERT INTO `dict` VALUES ('21890fd21ab34894a3577130b3e21219', '0', 'WE_ROLE', '角色', '0', '0', '3', '2018-11-19 10:23:49', '2018-11-19 10:23:52');
INSERT INTO `dict` VALUES ('22cbe367b8884d53b8a2c26812e4e6ae', 'WE_ROLE', 'ROLE_ORGADMIN', '机构管理员', '0', '0', '3', '2018-11-19 10:37:20', '2018-11-19 10:37:22');
INSERT INTO `dict` VALUES ('262514537b2e4b14a2fecbfdc65f310f', 'WE_PERMISSION', 'PM_USERDELETE', '用户删除', '0', '0', '4', '2018-12-06 08:46:20', '2018-12-06 08:46:20');
INSERT INTO `dict` VALUES ('2fa6cf0881ca463fa2abde8fea2e9135', '0', 'WE_XUEJI', '学级', '0', '0', '7', '2018-11-21 17:08:32', '2018-11-21 17:08:35');
INSERT INTO `dict` VALUES ('31c85152c82a47bb97cef96b29808471', 'WE_XUEJI', 'XJ_YINIANJI', '一年级', '1', '0', '7', '2018-11-21 17:08:40', '2018-11-21 17:09:26');
INSERT INTO `dict` VALUES ('3fcdfcea190d4e88998ba4978d3007a1', 'WE_SCHOOLTYPE', 'ST_CHUZHONG', '初中', '2', '0', '5', '2018-11-19 13:53:40', '2018-11-19 13:53:59');
INSERT INTO `dict` VALUES ('45634b7736c64d779634e8cd821498bf', 'WE_PERMISSION', 'PM_USERADD', '用户添加', '0', '0', '4', '2018-12-05 18:45:13', '2018-12-05 18:45:13');
INSERT INTO `dict` VALUES ('4dcaf4673d29462a8aca5eb1267fd1ad', '0', 'WE_ORGTYPE', '机构类型', '0', '0', '6', '2018-11-20 09:53:42', '2018-11-20 09:53:52');
INSERT INTO `dict` VALUES ('50337ae8fdf447f8a212a1ca672a0d94', 'WE_ROLE', 'ROLE_SHCOOLADMIN', '校级管理员', '0', '0', '3', '2018-11-19 10:36:17', '2018-12-18 18:17:01');
INSERT INTO `dict` VALUES ('54bc1a8481804e3b94d3c18913e9e1b5', 'WE_ORGTYPE', 'OT_DEPARTMENT', '部门', '3', '0', '6', '2018-11-20 10:31:14', '2018-11-20 10:31:23');
INSERT INTO `dict` VALUES ('5b6f47d6465b4980b2b4e9c15c9577ee', '0', 'WE_SCHOOLTYPE', '学校类型', '0', '0', '5', '2018-11-19 11:38:34', '2018-11-19 11:38:37');
INSERT INTO `dict` VALUES ('5c0d89e5621844119fcb0cc81362b6ae', '0', 'WE_PERSONTYPE', '人员类型', '0', '0', '8', '2018-11-19 11:38:34', '2018-11-19 11:38:37');
INSERT INTO `dict` VALUES ('6ec0aaa57ffd4f218c60a852af1d28bf', 'WE_XUEDUAN', 'XD_XIAOXUE', '小学', '2', '0', '2', '2018-11-17 16:24:52', '2018-11-17 16:24:55');
INSERT INTO `dict` VALUES ('762ab9b8052d4989a030105b07dd694e', '0', 'WE_PERMISSION', '权限', '0', '0', '4', '2018-11-19 10:40:32', '2018-11-19 10:40:34');
INSERT INTO `dict` VALUES ('89424336d68544ad8c9627b3e32cb4c2', 'WE_PERMISSION', 'PM_USEREDIT', '用户编辑', '0', '0', '4', '2018-12-06 08:46:37', '2018-12-06 08:46:37');
INSERT INTO `dict` VALUES ('8c1265274b4841b8b9a0f78900fad755', 'WE_ORGTYPE', 'OT_BUREAU', '局单位', '1', '0', '6', '2018-11-20 10:40:26', '2018-11-20 10:40:31');
INSERT INTO `dict` VALUES ('8eb3e38ad322446cbc9dc093d7498a2c', 'WE_XUEQI', 'XQ_DYXQ', '第一学期', '1', '0', '1', '2018-11-19 11:18:04', '2018-11-19 11:18:07');
INSERT INTO `dict` VALUES ('9f5b2dce73644c7e8e7d2b432ba78a9b', 'WE_XUEDUAN', 'XD_ZHIYEGAOZHONG', '职业中学', '5', '0', '2', '2018-11-17 16:26:11', '2018-11-17 16:26:12');
INSERT INTO `dict` VALUES ('9f8cd5264f4d43c091061ea202708957', 'WE_PERSONTYPE', 'PT_PATRIARCH', '家长', '0', '0', '8', '2018-11-19 13:50:37', '2018-11-19 13:50:34');
INSERT INTO `dict` VALUES ('a4aa7396e44d4e2582cc58c4fc8d2f98', 'WE_PERMISSION', 'PM_USERVIEW', '用户查询', '0', '0', '0', '2018-11-28 18:22:46', '2018-11-28 18:22:53');
INSERT INTO `dict` VALUES ('a5df041c628e47bfaafaad425e00179e', 'WE_SCHOOLTYPE', 'ST_JIUNIANYIGUANZHI', '九年一贯制', '4', '0', '5', '2018-11-19 13:53:48', '2018-11-19 13:54:08');
INSERT INTO `dict` VALUES ('b965d72695034fb389c9bd759fc997e0', 'WE_PERSONTYPE', 'PT_STUDENT', '学生', '0', '0', '8', '2018-11-19 13:50:37', '2018-11-19 13:50:34');
INSERT INTO `dict` VALUES ('b993379a17c44da1a354f00979c27a86', 'WE_XUEDUAN', 'XD_YOUERYUAN', '幼儿园', '1', '0', '2', '2018-11-17 16:24:06', '2018-11-17 16:24:10');
INSERT INTO `dict` VALUES ('bb082ee88dc54981a5eae856c289f74d', 'WE_ORGTYPE', 'OT_SCHOOL', '学校', '2', '0', '6', '2018-11-20 10:28:35', '2018-11-20 10:28:41');
INSERT INTO `dict` VALUES ('bb3fd15d9fea408191623740b71b5d1a', 'WE_XUEQI', 'XQ_DEXQ', '第二学期', '2', '0', '1', '2018-11-19 09:47:29', '2018-11-19 09:47:34');
INSERT INTO `dict` VALUES ('c8170e5f852f4cae9291f212d17ec421', 'WE_XUEJI', 'XJ_SINIANJI', '四年级', '4', '0', '7', '2018-11-21 17:09:40', '2018-11-21 17:09:42');
INSERT INTO `dict` VALUES ('da3c430af4ad4f6bb5b0093a01b762a4', 'WE_ROLE', 'ROLE_SUPERADMIN', '超级管理员', '0', '0', '3', '2018-11-19 10:35:10', '2018-11-19 10:35:13');
INSERT INTO `dict` VALUES ('e0ebc7499a71497889bd00c971f27c3a', '0', 'WE_XUEQI', '学期', '0', '0', '1', '2018-11-19 09:58:24', '2018-11-19 09:58:28');
INSERT INTO `dict` VALUES ('f62a055be4a24c67be5a340caf1facf8', 'WE_XUEJI', 'XJ_SANNIANJI', '三年级', '3', '0', '7', '2018-11-21 17:09:35', '2018-11-21 17:09:37');
INSERT INTO `dict` VALUES ('f6692042dbbf467c8f674d0e8524c72b', 'WE_XUEJI', 'XJ_ERNIANJI', '二年级', '2', '0', '7', '2018-11-21 17:09:30', '2018-11-21 17:09:32');
INSERT INTO `dict` VALUES ('f9baf9da3e2b4eff8828f2cad225b92e', 'WE_XUEDUAN', 'XD_GAOZHONG', '高中', '4', '0', '2', '2018-11-17 16:26:06', '2018-11-17 16:26:08');
INSERT INTO `dict` VALUES ('fbea31dfc0ac4a19991e97bb99b2bf11', 'WE_XUEJI', 'XJ_LIUNIANJI', '六年级', '6', '0', '7', '2018-11-21 17:09:57', '2018-11-21 17:09:59');


-- 学校跟学段关系表
DROP TABLE IF EXISTS `school_section`;
CREATE TABLE `school_section` (
   `id` varchar(40) NOT NULL ,
  `school_dict` varchar(40) NOT NULL COMMENT '学校类型字典',
  `section_dict` varchar(30) NOT NULL COMMENT '学段字典',
  `sort`  int(11) NOT NULL ,
  PRIMARY KEY (`id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- 学段跟年级关系表
DROP TABLE IF EXISTS `section_grade`;
CREATE TABLE `section_grade` (
  `id` varchar(40) NOT NULL ,
  `section_dict` varchar(40) NOT NULL COMMENT '学段字典',
  `grade_dict` varchar(30) NOT NULL COMMENT '年级字典',
  `sort`  int(11) NOT NULL ,
  PRIMARY KEY (`id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` varchar(40) NOT NULL,
  `menu_code` varchar(50) NOT NULL COMMENT '菜单编码',
  `parent_id` int(11) DEFAULT NULL COMMENT '父菜单编码',
  `title` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `sysstate` smallint(6) DEFAULT NULL COMMENT '系统状态 0-禁用 1-启用',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `sort` smallint(6) DEFAULT NULL COMMENT '顺序号',
  `is_leaf` smallint(6) DEFAULT NULL COMMENT '是否根节点 0-否 1-是',
  `url` varchar(200) DEFAULT NULL COMMENT '链接地址',
  `is_manage` smallint(6) DEFAULT NULL COMMENT '是否权限控制 0-否 1-是',
  `icon` varchar(200) DEFAULT NULL COMMENT '图标',
  `is_thirdweb` varchar(40) DEFAULT NULL COMMENT '第三方平台标志：0-基础平台， client_id-第三方平台',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1003 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '', '0', '教学管理', '1', '2018-12-13 15:01:54', '6', '0', null, '1', 'icon-jiaoxueguanli', '0');
INSERT INTO `menu` VALUES ('1001', '', '1', '学期管理', '1', '2018-12-13 15:01:57', '0', '1', '/View/listSemester', '1', null, '0');
INSERT INTO `menu` VALUES ('1002', '', '1', '学科管理', '1', '2018-12-13 15:02:00', '0', '1', '/View/listSubjectByOrgId', '1', null, '0');
INSERT INTO `menu` VALUES ('2', '', '0', '教务设置', '1', '2018-12-13 15:02:02', '1', '0', '', '1', 'icon-jiaoxueguanli', '0');
INSERT INTO `menu` VALUES ('2001', '', '2', '校历管理', '1', '2018-12-13 15:02:04', '0', '1', '', '1', null, '0');
INSERT INTO `menu` VALUES ('2002', '', '2', '课时管理', '1', '2018-12-13 15:02:07', '0', '1', '', '1', null, '0');
INSERT INTO `menu` VALUES ('2003', '', '2', '班主任管理', '1', '2018-12-13 15:02:10', '0', '1', '/View/listMaster', '1', null, '0');
INSERT INTO `menu` VALUES ('2004', '', '2', '班级管理', '1', '2018-12-13 15:02:12', '0', '1', '/View/listClass', '1', null, '0');
INSERT INTO `menu` VALUES ('2005', '', '2', '任课管理', '1', '2018-12-13 15:02:16', '0', '1', null, '1', null, '0');
INSERT INTO `menu` VALUES ('2006', '', '2', '课表管理', '1', '2018-12-13 15:02:18', '0', '1', null, '1', null, '0');
INSERT INTO `menu` VALUES ('2007', '', '2', '学科管理', '1', '2018-12-13 15:02:21', '0', '1', '/View/listSubjectByOrgId', '1', null, '0');
INSERT INTO `menu` VALUES ('3', '', '0', '设备管理', '1', '2018-12-13 15:02:24', '3', '0', null, '1', 'icon-device-list', '0');
INSERT INTO `menu` VALUES ('3001', '', '3', '设备管理', '1', '2018-12-13 15:02:26', '0', '1', 'http://192.168.10.215/appView/device/device.html', '1', null, '1547532938');
INSERT INTO `menu` VALUES ('3002', '', '3', '设备控制', '1', '2018-12-13 15:02:30', '0', '1', 'http://192.168.10.215/appView/device/deviceControl.html', '1', null, '1547532938');
INSERT INTO `menu` VALUES ('3003', '', '3', '设备分配', '1', '2018-12-13 15:02:33', '0', '1', null, '1', null, '0');
INSERT INTO `menu` VALUES ('4', '', '0', '机构管理', '1', '2018-12-13 15:02:35', '1', '0', '/View/orgList', '1', 'icon-jigouguanli', '0');
INSERT INTO `menu` VALUES ('5', '', '0', '人员管理', '1', '2018-12-13 15:02:38', '2', '0', null, '1', 'icon-renyuanguanli', '0');
INSERT INTO `menu` VALUES ('5001', '', '5', '局管理员', '1', '2018-12-13 15:02:40', '0', '1', '/View/listOfficeAdmin', '1', null, '0');
INSERT INTO `menu` VALUES ('5002', '', '5', '校管理员', '1', '2018-12-13 15:02:43', '0', '1', '/View/listSchoolAdmin', '1', null, '0');
INSERT INTO `menu` VALUES ('5003', '', '5', '教师管理', '1', '2018-12-13 15:02:45', '0', '1', '/View/listTeacher', '1', null, '0');
INSERT INTO `menu` VALUES ('5004', '', '5', '学生管理', '1', '2018-12-13 15:02:48', '0', '1', '/View/listStudent', '1', null, '0');
INSERT INTO `menu` VALUES ('5005', '', '5', '家长管理', '1', '2018-12-13 15:02:50', '0', '1', null, '1', null, '0');
INSERT INTO `menu` VALUES ('6', '', '0', '角色权限', '1', '2018-12-13 15:02:52', '3', '0', '', '1', 'icon-juesequanxian', '0');
INSERT INTO `menu` VALUES ('6001', '', '6', '角色管理', '1', '2018-12-13 15:02:56', '0', '1', '/View/listRole', '1', '', '0');
INSERT INTO `menu` VALUES ('6002', '', '6', '权限管理', '1', '2018-12-13 15:02:59', '0', '1', '/View/listPermission', '1', null, '0');
INSERT INTO `menu` VALUES ('7', '', '0', '平台设置', '1', '2018-12-13 15:03:01', '7', '0', null, '1', 'icon-pingtaishezhi', '0');
INSERT INTO `menu` VALUES ('7001', '', '7', '学校信息', '1', '2018-12-13 15:03:04', '0', '1', '/View/schoolView', '1', null, '0');
INSERT INTO `menu` VALUES ('8', '', '0', '授权管理', '1', '2018-12-13 15:03:06', '4', '0', null, '1', 'icon-shouquanguanli', '0');
INSERT INTO `menu` VALUES ('8001', '', '8', '局授权', '1', '2018-12-13 15:03:08', '0', '1', '/View/empowerOffic', '1', null, '0');
INSERT INTO `menu` VALUES ('8002', '', '8', '校授权', '1', '2018-12-13 15:03:10', '0', '1', '/View/empowerSch', '1', null, '0');
INSERT INTO `menu` VALUES ('9', '', '0', '开发者中心', '1', '2018-12-13 15:40:40', '5', '0', '', '1', 'icon-kaifazhezhongxin', '0');
INSERT INTO `menu` VALUES ('9001', '', '9', '第三方应用管理', '1', '2018-12-13 15:40:42', '0', '1', '/View/listClient', '1', null, '0');
INSERT INTO `menu` VALUES ('10', '', '0', '日志管理', '1', '2018-12-13 15:40:49', '8', '0', null, '1', 'icon-rizhiguanli', '0');
INSERT INTO `menu` VALUES ('11', '', '0', '书香校园', '1', '2018-12-28 14:28:41', '9', '0', 'http://192.168.10.215/appView/Book/index.html', '1', null, '1545457373');
INSERT INTO `menu` VALUES ('5006', '', '5', '部门管理', '1', '2019-01-15 14:21:14', '0', '1', '/View/listDepartmentAdmin', '1', null, '0');

-- 机构表   机构包含学校  学校会学校类型
DROP TABLE IF EXISTS `org`;
CREATE TABLE `org` (
  `id` varchar(40) NOT NULL,
  `org_type` varchar(30) NOT NULL COMMENT '机构类型',
  `code` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `simple_name` varchar(50) DEFAULT NULL COMMENT '简称',
  `first_py` varchar(200) NOT NULL COMMENT '拼音简拼',
  `full_py` text NOT NULL COMMENT '拼音全拼',
  `parent_id` varchar(40) NOT NULL DEFAULT '0',
  `org_ids` varchar(2000) DEFAULT '' COMMENT '所有父类机构ID，查询用',
  `department_id` varchar(200) DEFAULT NULL COMMENT '部门所属的机构名',
  `remarks_type` varchar(30) NOT NULL COMMENT '补充类型，比如学校类型',
  `domain_name` varchar(255) DEFAULT NULL COMMENT '主页域名',
  `sort` int(11) NOT NULL DEFAULT '1' COMMENT '排序',
  `del` int(11) NOT NULL DEFAULT '0',
  `createdatetime` datetime NOT NULL,
  `modifydatetime` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 人员表（是否添加班级）   关于家长与孩子的关系是多对多。  学段 入学年份 班级序号
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
   `id` varchar(40) NOT NULL ,
  `login_id`varchar(40) NOT NULL,
  `person_type` varchar(40) ,
  `org_id` varchar(40) ,
   `tel` VARCHAR(30) ,
   `number` VARCHAR(30) comment '编号',
  `email` VARCHAR(30) ,
  `identity_no` VARCHAR(30) NULL comment '身份证号',
  `card_no` VARCHAR(30) NULL comment '卡号',
  `student_id` VARCHAR(30) NULL comment '孩子ID 多个用，分割',
  `start_year` VARCHAR(30) NULL comment '入学年份',
  `pic` VARCHAR(30) NULL comment '头像',
  `del` int(11) NOT NULL DEFAULT 0,
  `createdatetime` datetime NOT NULL,
  `modifydatetime` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

insert into `person` (guid,loginName,password,realname,delsign,salt,tel,email,id_number,createdatetime,modifydatetime)
  values (md5('a1720102-76de-410d-bf11-b506d85b0a61'),'admin',md5('admin8335fe5b-5638-11e6-a0ca-1c6f652c67b3'),'超级管理员',1,'8335fe5b-5638-11e6-a0ca-1c6f652c67b3','','','',now(),now());

-- 第三方应用表
DROP TABLE IF EXISTS `client`;
CREATE TABLE `client` (
  `id` varchar(40) NOT NULL,
  `client_id` varchar(40) NOT NULL,
  `client_secret` varchar(50) NOT NULL,
  `client_name` varchar(50) NOT NULL COMMENT '应用名称',
  `home_page` varchar(100) NOT NULL COMMENT '应用首页',
  `sort` smallint(7) NOT NULL DEFAULT '0' COMMENT '排序',
  `logo` varchar(100) NOT NULL COMMENT '应用图标',
  `type` smallint(2) DEFAULT '0' COMMENT '是否在中心平台“第三方应用管理”、“校授权”页面显示  0-显示；1-不显示',
  `del` smallint(2) NOT NULL DEFAULT '0' COMMENT '是否删除,  0未删除/1已删除',
  `createdatetime` datetime NOT NULL COMMENT '创建时间',
  `modifydatetime` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of client
-- ----------------------------
INSERT INTO `client` VALUES ('38daceaee5bb4f34a54084affd187568', '1546847238', '613e0f07871141cbbf2b82fa7dcf288c', '书香校园APP', 'https://www.baidu.com/', '0', 'bp/client/20190107/20190107154652657.jpg', '0', '0', '2019-01-07 15:47:18', '2019-01-07 15:47:18');
INSERT INTO `client` VALUES ('553c142683cf4fc182e25880e891ab91', '1545457373', '1dad1020d5214bb89b2ff2a4ef2e2e49', '书香校园', 'http://192.168.10.215/appView/Book/index.html', '0', 'bp/client/20190103/20190103085334312.png', '0', '0', '2018-12-22 13:42:53', '2019-01-05 15:25:38');
INSERT INTO `client` VALUES ('5d937037189f42dabc19218c42068421', '1542330002', 'ec25558f93044a3b8a42f04849491863', '校园电视台', 'https://www.baidu.com/', '0', 'bp/client/20190103/20190103084058929.png', '0', '0', '2018-11-16 09:00:02', '2019-01-03 08:40:59');
INSERT INTO `client` VALUES ('743b15eb0d2b411bba85fc7208b18019', '1546073065', '94f167e867dd4188ba1c4d9aa069698f', '智慧校牌', 'https://movie.douban.com/', '0', 'bp/client/20190114/20190114110929543.png', '0', '0', '2018-12-29 16:44:25', '2019-01-14 11:09:32');
INSERT INTO `client` VALUES ('88cf98758b1d4b1e96aa20a36c6104c9', '1547532938', '497d4a2c2d614b85b4dea924e012e083', '设备管理', 'https://www.baidu.com/', '0', 'bp/client/20190115/20190115141532389.jpg', '1', '0', '2019-01-15 14:15:38', '2019-01-15 14:15:38');
INSERT INTO `client` VALUES ('8ee8a9cc6409447888f80de5c60468e7', '1546998622', 'eeb8e85ea8c74d40bfcba9a0fd138fd5', '校牌', 'http://192.168.10.215:8080/', '0', 'bp/client/20190109/20190109095020762.jpg', '0', '0', '2019-01-09 09:50:22', '2019-01-09 09:50:22');
INSERT INTO `client` VALUES ('9019b92ccec3456490062841f696c1b5', '1545457637', 'cc561b2517d24e7e9da20aea651459cb', '学生激励平台', 'https://www.baidu.com/', '0', 'bp/client/20181229/20181229164215039.png', '0', '0', '2018-12-22 13:47:17', '2018-12-29 16:42:18');
INSERT INTO `client` VALUES ('a149c15247f74c7eaf849ed7534acad5', 'CLIENT002', 'local', '基础平台', '/base', '0', 'bp/client/20181229/20181229164032683.png', '1', '0', '2018-12-28 14:23:52', '2018-12-29 16:41:18');
INSERT INTO `client` VALUES ('a71f29133acc4f398691ae2bde15b93c', '1546998514', '8b64076f6f584de49399ea8a952ef2ed', '班牌', 'http://192.168.10.215:8080/', '0', 'bp/client/20190109/20190109094829428.jpg', '0', '0', '2019-01-09 09:48:34', '2019-01-09 09:48:34');
INSERT INTO `client` VALUES ('bddd04f5b66d4c4e961789041a3dd51f', '1542329947', '14d707a84c924c72a623de26e9d4e4f7', '智慧班牌', 'https://www.baidu.com/', '0', 'bp/client/20190103/20190103163042184.png', '0', '0', '2018-11-16 08:59:07', '2019-01-03 16:30:43');


-- 科目表
DROP TABLE IF EXISTS `subject`;
CREATE TABLE `subject` (
  `id` varchar(40) NOT NULL,
  `section_code` varchar(40) NOT NULL COMMENT '所属学段',
  `subject_cn` varchar(255) NOT NULL COMMENT '科目拼音',
  `subject_name` varchar(100) NOT NULL COMMENT '科目全拼',
  `school_id` varchar(100) NOT NULL COMMENT '所属学校, ',
  `sort`  int(11) NOT NULL COMMENT '排序',
  `del` int(11) NOT NULL DEFAULT '0' COMMENT '是否删除,  0未删除/1已删除',
  `createdatetime` datetime NOT NULL COMMENT '创建时间',
  `modifydatetime` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 班级表
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class` (
  `id` varchar(40) NOT NULL,
  `org_id` varchar(40) NOT NULL COMMENT '机构id(用于获取学校名称)',
  `start_year` varchar(40) NOT NULL COMMENT '入学年份',
  `dict_code` varchar(40) NOT NULL COMMENT '字典code(用于获取学段)',
  `class_number` varchar(40) NOT NULL COMMENT '班级号',
  `teacher` varchar(40) NOT NULL COMMENT '班主任',
  `del` int(11) NOT NULL DEFAULT '0' COMMENT '是否删除,  0未删除/1已删除',
  `createdatetime` datetime NOT NULL COMMENT '创建时间',
  `modifydatetime` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 学期表
DROP TABLE IF EXISTS `semester`;
CREATE TABLE `semester` (
  `id` varchar(40) NOT NULL DEFAULT '',
  `school_year` varchar(40) NOT NULL COMMENT '学年',
  `semester_number` varchar(40) NOT NULL COMMENT '学期编号',
  `dict_code` varchar(40) NOT NULL COMMENT '字典code(用于获取第一学期还是第二学期)',
  `semester_range` varchar(40) NOT NULL COMMENT '学期时间范围',
  `semester_name` varchar(40) NOT NULL COMMENT '学期全称',
  `del` int(11) NOT NULL DEFAULT '0' COMMENT '是否删除,  0未删除/1已删除',
  `is_current_semester` int(11) NOT NULL DEFAULT '0' COMMENT '是否当前学期 0不是  1是',
  `createdatetime` datetime NOT NULL COMMENT '创建时间',
  `modifydatetime` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 角色权限表
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `pr_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` varchar(40) DEFAULT NULL,
  `permission_id` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`pr_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

INSERT INTO `role_permission` VALUES ('1', 'da3c430af4ad4f6bb5b0093a01b762a4', '1');
INSERT INTO `role_permission` VALUES ('2', 'da3c430af4ad4f6bb5b0093a01b762a4', '1001');
INSERT INTO `role_permission` VALUES ('3', 'da3c430af4ad4f6bb5b0093a01b762a4', '1002');
INSERT INTO `role_permission` VALUES ('4', 'da3c430af4ad4f6bb5b0093a01b762a4', '4');
INSERT INTO `role_permission` VALUES ('5', 'da3c430af4ad4f6bb5b0093a01b762a4', '5');
INSERT INTO `role_permission` VALUES ('6', 'da3c430af4ad4f6bb5b0093a01b762a4', '5001');
INSERT INTO `role_permission` VALUES ('7', 'da3c430af4ad4f6bb5b0093a01b762a4', '5002');
INSERT INTO `role_permission` VALUES ('8', 'da3c430af4ad4f6bb5b0093a01b762a4', '5003');
INSERT INTO `role_permission` VALUES ('9', 'da3c430af4ad4f6bb5b0093a01b762a4', '5004');
INSERT INTO `role_permission` VALUES ('10', 'da3c430af4ad4f6bb5b0093a01b762a4', '5005');
INSERT INTO `role_permission` VALUES ('11', 'da3c430af4ad4f6bb5b0093a01b762a4', '6');
INSERT INTO `role_permission` VALUES ('12', 'da3c430af4ad4f6bb5b0093a01b762a4', '6001');
INSERT INTO `role_permission` VALUES ('13', 'da3c430af4ad4f6bb5b0093a01b762a4', '6002');
INSERT INTO `role_permission` VALUES ('14', 'da3c430af4ad4f6bb5b0093a01b762a4', '8');
INSERT INTO `role_permission` VALUES ('15', 'da3c430af4ad4f6bb5b0093a01b762a4', '8001');
INSERT INTO `role_permission` VALUES ('16', 'da3c430af4ad4f6bb5b0093a01b762a4', '8002');
INSERT INTO `role_permission` VALUES ('17', 'da3c430af4ad4f6bb5b0093a01b762a4', '9');
INSERT INTO `role_permission` VALUES ('18', 'da3c430af4ad4f6bb5b0093a01b762a4', '9001');
INSERT INTO `role_permission` VALUES ('19', 'da3c430af4ad4f6bb5b0093a01b762a4', '10');
INSERT INTO `role_permission` VALUES ('20', 'da3c430af4ad4f6bb5b0093a01b762a4', '11');
INSERT INTO `role_permission` VALUES ('21', 'da3c430af4ad4f6bb5b0093a01b762a4', '3');
INSERT INTO `role_permission` VALUES ('22', 'da3c430af4ad4f6bb5b0093a01b762a4', '3001');
INSERT INTO `role_permission` VALUES ('23', 'da3c430af4ad4f6bb5b0093a01b762a4', '3002');
INSERT INTO `role_permission` VALUES ('24', 'da3c430af4ad4f6bb5b0093a01b762a4', '3003');
INSERT INTO `role_permission` VALUES ('25', '50337ae8fdf447f8a212a1ca672a0d94', '2');
INSERT INTO `role_permission` VALUES ('26', '50337ae8fdf447f8a212a1ca672a0d94', '2001');
INSERT INTO `role_permission` VALUES ('27', '50337ae8fdf447f8a212a1ca672a0d94', '2002');
INSERT INTO `role_permission` VALUES ('28', '50337ae8fdf447f8a212a1ca672a0d94', '2003');
INSERT INTO `role_permission` VALUES ('29', '50337ae8fdf447f8a212a1ca672a0d94', '2004');
INSERT INTO `role_permission` VALUES ('30', '50337ae8fdf447f8a212a1ca672a0d94', '2005');
INSERT INTO `role_permission` VALUES ('31', '50337ae8fdf447f8a212a1ca672a0d94', '2006');
INSERT INTO `role_permission` VALUES ('32', '50337ae8fdf447f8a212a1ca672a0d94', '2007');
INSERT INTO `role_permission` VALUES ('33', '50337ae8fdf447f8a212a1ca672a0d94', '5');
INSERT INTO `role_permission` VALUES ('34', '50337ae8fdf447f8a212a1ca672a0d94', '5003');
INSERT INTO `role_permission` VALUES ('35', '50337ae8fdf447f8a212a1ca672a0d94', '5004');
INSERT INTO `role_permission` VALUES ('36', '50337ae8fdf447f8a212a1ca672a0d94', '5005');
INSERT INTO `role_permission` VALUES ('37', '50337ae8fdf447f8a212a1ca672a0d94', '3');
INSERT INTO `role_permission` VALUES ('38', '50337ae8fdf447f8a212a1ca672a0d94', '3001');
INSERT INTO `role_permission` VALUES ('39', '50337ae8fdf447f8a212a1ca672a0d94', '3002');
INSERT INTO `role_permission` VALUES ('40', '50337ae8fdf447f8a212a1ca672a0d94', '3003');
INSERT INTO `role_permission` VALUES ('41', '50337ae8fdf447f8a212a1ca672a0d94', '7');
INSERT INTO `role_permission` VALUES ('42', '50337ae8fdf447f8a212a1ca672a0d94', '7001');
-- 用户角色表
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `ur_id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(40) DEFAULT NULL,
  `role_id` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`ur_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

INSERT INTO `user_role` VALUES ('4', 'f0e660dbb4214c65a00e54edc8a4a33c', 'da3c430af4ad4f6bb5b0093a01b762a4');

-- 局授权和校授权
DROP TABLE IF EXISTS `client_authorization`;
CREATE TABLE `client_authorization` (
  `id` varchar(40) NOT NULL,
  `client_id` varchar(40) NOT NULL COMMENT '第三方应用id',
  `org_id` varchar(40) NOT NULL COMMENT '局/校id',
  `del` int(11) NOT NULL DEFAULT '0' COMMENT '是否删除,  0未删除/1已删除',
  `org_type` int(11) NOT NULL COMMENT '机构类型 1学校  2教育局',
  `authorized_deadline` varchar(40) NOT NULL COMMENT '授权截止时间',
  `createdatetime` datetime NOT NULL COMMENT '创建时间',
  `modifydatetime` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='局授权和校授权';


-- 学校信息
DROP TABLE IF EXISTS `school`;
CREATE TABLE `school` (
  `id` varchar(40) NOT NULL,
  `org_id` varchar(40) NOT NULL COMMENT '机构id',
  `school_img` varchar(40) NOT NULL COMMENT '学校主图片',
  `school_icon` varchar(40) NOT NULL COMMENT '校徽',
  `school_CN` varchar(40) NOT NULL COMMENT '校名的拼音全称',
  `school_Motto` varchar(40) NOT NULL COMMENT '校训',
  `school_Profile` message_text NOT NULL COMMENT '学校简介',
  `del` int(11) NOT NULL DEFAULT '0' COMMENT '是否删除,  0未删除/1已删除',
  `createdatetime` datetime NOT NULL COMMENT '创建时间',
  `modifydatetime` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学校信息';


-- 设备班级关系表
DROP TABLE IF EXISTS `device_class`;
CREATE TABLE `device_class` (
  `id` varchar(40) NOT NULL,
  `class_id` varchar(40) NOT NULL COMMENT '班级id',
  `device_id` varchar(255) NOT NULL COMMENT '设备id',
  `del` varchar(255) NOT NULL DEFAULT '0' COMMENT '是否删除0未删除/1删除',
  `createdatetime` datetime NOT NULL COMMENT '创建时间',
  `modifydatetime` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
