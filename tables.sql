-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.7.17-log - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 mydb 的数据库结构
CREATE DATABASE IF NOT EXISTS `mydb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `mydb`;

-- 导出  表 mydb.cost_method_info 结构
CREATE TABLE IF NOT EXISTS `cost_method_info` (
  `project_id`        int(11)          DEFAULT NULL,
  `method_id`         int(11) NOT NULL AUTO_INCREMENT,
  `method_class`      varchar(128)     DEFAULT NULL
  COMMENT '用户配置的项目方法所在class全路径',
  `method_name`       varchar(128)     DEFAULT NULL,
  `if_cost_need_warn` varchar(128)     DEFAULT 'no'
  COMMENT '是否需要方法超时报警',
  `cost_warn_num`     int(11)          DEFAULT '0'
  COMMENT '报警指标',
  PRIMARY KEY (`method_id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8
  COMMENT = '需要统计性能的方法表';

-- 正在导出表  mydb.cost_method_info 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `cost_method_info`
  DISABLE KEYS */;
INSERT INTO `cost_method_info` (`project_id`,
                                `method_id`,
                                `method_class`,
                                `method_name`,
                                `if_cost_need_warn`,
                                `cost_warn_num`)
VALUES (666, 1, 'com.cbw.UserClass', 'print', 'no', 999),
       (666, 2, 'com.cbw.UserClass1', 'print1', 'no', 999);
/*!40000 ALTER TABLE `cost_method_info`
  ENABLE KEYS */;

-- 导出  表 mydb.user_info 结构
CREATE TABLE IF NOT EXISTS `user_info` (
  `phone`    varchar(128) NOT NULL,
  `email`    varchar(128) NOT NULL,
  `password` varchar(128) NOT NULL,
  PRIMARY KEY (`phone`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT = '用户的电话，email等信息';

-- 正在导出表  mydb.user_info 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `user_info`
  DISABLE KEYS */;
INSERT INTO `user_info` (`phone`, `email`, `password`)
VALUES ('13168015499', 'scu_cbw@qq.com', 'cangsang666');
/*!40000 ALTER TABLE `user_info`
  ENABLE KEYS */;

-- 导出  表 mydb.user_project_info 结构
CREATE TABLE IF NOT EXISTS `user_project_info` (
  `phone`          varchar(128) DEFAULT NULL,
  `project_id`     int(11)      DEFAULT NULL,
  `project_name`   varchar(128) DEFAULT NULL,
  `hostname`       varchar(128) DEFAULT NULL,
  `port`           varchar(128) DEFAULT NULL,
  `username`       varchar(128) DEFAULT NULL,
  `password`       varchar(128) DEFAULT NULL,
  `db_name`        varchar(128) DEFAULT NULL,
  `cpu_need_warn`  varchar(128) DEFAULT 'no'
  COMMENT '是否需要cpu报警',
  `mem_need_warn`  varchar(128) DEFAULT 'no'
  COMMENT '是否需要mem报警',
  `disk_need_warn` varchar(128) DEFAULT 'no'
  COMMENT '是否需要disk报警',
  `cpu_warn_num`   double       DEFAULT '0',
  `mem_warn_num`   double       DEFAULT '0'
  COMMENT '报警指标',
  `disk_warn_num`  double       DEFAULT '0'
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT = '用户创建的project信息';

-- 正在导出表  mydb.user_project_info 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `user_project_info`
  DISABLE KEYS */;
INSERT INTO `user_project_info` (`phone`,
                                 `project_id`,
                                 `project_name`,
                                 `hostname`,
                                 `port`,
                                 `username`,
                                 `password`,
                                 `db_name`,
                                 `cpu_need_warn`,
                                 `mem_need_warn`,
                                 `disk_need_warn`,
                                 `cpu_warn_num`,
                                 `mem_warn_num`,
                                 `disk_warn_num`)
VALUES ('13168015499',
        666,
        'testAgent',
        'localhost',
        '3306',
        'root',
        'root',
        'userdb',
        'yes',
        'yes',
        'yes',
        100,
        100,
        100);
/*!40000 ALTER TABLE `user_project_info`
  ENABLE KEYS */;

/*!40101 SET SQL_MODE = IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS = IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;

-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.7.17-log - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 userdb 的数据库结构
CREATE DATABASE IF NOT EXISTS `userdb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `userdb`;

-- 导出  表 userdb.params 结构
CREATE TABLE IF NOT EXISTS `params` (
  `project_id`   int(11)        DEFAULT NULL,
  `method_id`    int(11)        DEFAULT NULL,
  `method_class` varchar(128)   DEFAULT NULL,
  `method_name`  varchar(128)   DEFAULT NULL,
  `time_s`       varchar(128)   DEFAULT NULL
  COMMENT '执行时间，粒度为s',
  `content`      varchar(16384) DEFAULT NULL
  COMMENT '上传内容，大于16384不允许上传'
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- 正在导出表  userdb.params 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `params`
  DISABLE KEYS */;
INSERT INTO `params` (`project_id`, `method_id`, `method_class`, `method_name`, `time_s`, `content`)
VALUES (666, 1, 'com.cbw.UserClass', 'print', '2018-12-15 21:28:02', 'xioaming');
/*!40000 ALTER TABLE `params`
  ENABLE KEYS */;

-- 导出  表 userdb.server_performance 结构
CREATE TABLE IF NOT EXISTS `server_performance` (
  `project_id`        int(11)      DEFAULT NULL
  COMMENT '用户项目唯一id',
  `time_min`          varchar(128) DEFAULT NULL
  COMMENT '采集时间，分钟粒度',
  `if_cpu_need_warn`  varchar(128) DEFAULT NULL,
  `if_mem_need_warn`  varchar(128) DEFAULT NULL,
  `if_disk_need_warn` varchar(128) DEFAULT NULL,
  `cpu_warn_num`      double       DEFAULT NULL,
  `mem_warn_num`      double       DEFAULT NULL,
  `disk_warn_num`     double       DEFAULT NULL,
  `cpu_usage`         double       DEFAULT NULL,
  `mem_usage`         double       DEFAULT NULL,
  `disk_usage`        double       DEFAULT NULL,
  `if_cpu_warned`     varchar(128) DEFAULT NULL,
  `if_mem_warned`     varchar(128) DEFAULT NULL,
  `if_disk_warned`    varchar(128) DEFAULT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT = '用户服务器性能表\r\n';

-- 正在导出表  userdb.server_performance 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `server_performance`
  DISABLE KEYS */;
INSERT INTO `server_performance` (`project_id`,
                                  `time_min`,
                                  `if_cpu_need_warn`,
                                  `if_mem_need_warn`,
                                  `if_disk_need_warn`,
                                  `cpu_warn_num`,
                                  `mem_warn_num`,
                                  `disk_warn_num`,
                                  `cpu_usage`,
                                  `mem_usage`,
                                  `disk_usage`,
                                  `if_cpu_warned`,
                                  `if_mem_warned`,
                                  `if_disk_warned`)
VALUES (666, '2018-12-15 18:10', 'yes', 'yes', 'yes', 100, 100, 100, 39.75, 67.54, 13.46, 'no', 'no', 'no');
/*!40000 ALTER TABLE `server_performance`
  ENABLE KEYS */;

-- 导出  表 userdb.time_cost 结构
CREATE TABLE IF NOT EXISTS `time_cost` (
  `project_id`        int(11)      DEFAULT NULL
  COMMENT '用户项目唯一id',
  `method_id`         int(11)      DEFAULT NULL
  COMMENT '用户项目方法唯一id',
  `class_name`        varchar(128) DEFAULT NULL
  COMMENT '用户方法所在类全路径',
  `method_name`       varchar(128) DEFAULT NULL
  COMMENT '用户方法名',
  `start_nano_time`   bigint(20)   DEFAULT NULL
  COMMENT '用户方法被调用纳秒值',
  `start_user_time`   varchar(128) DEFAULT NULL
  COMMENT '用户方法被调用时间，粒度为秒',
  `end_nano_time`     bigint(20)   DEFAULT NULL
  COMMENT '用户方法调用结束纳秒值',
  `if_cost_need_warn` varchar(128) DEFAULT 'no',
  `cost_warn_num`     int(11)      DEFAULT '0',
  `cost`              int(11)      DEFAULT NULL
  COMMENT '用户方法耗时，单位为毫秒',
  `if_warned`         varchar(128) DEFAULT NULL
  COMMENT '是否报警，yes/no'
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT = '用户API接口响应时间表';

-- 正在导出表  userdb.time_cost 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `time_cost`
  DISABLE KEYS */;
INSERT INTO `time_cost` (`project_id`,
                         `method_id`,
                         `class_name`,
                         `method_name`,
                         `start_nano_time`,
                         `start_user_time`,
                         `end_nano_time`,
                         `if_cost_need_warn`,
                         `cost_warn_num`,
                         `cost`,
                         `if_warned`)
VALUES (666,
        1,
        'com.cbw.UserClass',
        'print',
        635522562866018,
        '2018-12-15 20:49:38',
        635522822367852,
        'no',
        999,
        259,
        'no'),
       (666,
        1,
        'com.cbw.UserClass',
        'print',
        637826184414314,
        '2018-12-15 21:28:02',
        637826584727272,
        'no',
        999,
        400,
        'no');
/*!40000 ALTER TABLE `time_cost`
  ENABLE KEYS */;

/*!40101 SET SQL_MODE = IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS = IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;