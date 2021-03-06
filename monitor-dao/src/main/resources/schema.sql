DROP DATABASE IF EXISTS monitor;

CREATE DATABASE monitor
  DEFAULT CHARACTER SET utf8
  COLLATE utf8_general_ci;

USE monitor;

-- ----------------------------
--  Table structure for user
-- ----------------------------
DROP TABLE
IF EXISTS user;

CREATE TABLE user
(
  id            BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL
  COMMENT '主键',
  username      VARCHAR(20)                           NOT NULL
  COMMENT '用户名',
  realname      VARCHAR(32)                           NOT NULL                    DEFAULT ''
  COMMENT '真实姓名',
  password      VARCHAR(64)                           NOT NULL
  COMMENT '密码',
  salt          VARCHAR(64)                           NOT NULL
  COMMENT '密码盐',
  small_avatar  VARCHAR(256)                          NOT NULL                    DEFAULT ''
  COMMENT '小头像',
  medium_avatar VARCHAR(256)                          NOT NULL                    DEFAULT ''
  COMMENT '中头像',
  large_avatar  VARCHAR(256)                          NOT NULL                    DEFAULT ''
  COMMENT '大头像',
  email         VARCHAR(64)                           NOT NULL                    DEFAULT ''
  COMMENT '电子邮箱',
  is_deleted    TINYINT                               NOT NULL                    DEFAULT 0
  COMMENT '逻辑删除',
  created_time  TIMESTAMP                             NOT NULL                    DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  updated_time  TIMESTAMP                             NOT NULL                    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '更新时间'
)
  COMMENT '用户表';
CREATE UNIQUE INDEX id_UNIQUE
  ON user (id);
CREATE INDEX created_time_ix
  ON user (created_time);
CREATE UNIQUE INDEX username_UNIQUE
  ON user (username);

-- ----------------------------
--  Table structure for role
-- ----------------------------
DROP TABLE
IF EXISTS role;

CREATE TABLE role
(
  id           BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL
  COMMENT '主键',
  code         VARCHAR(32)                           NOT NULL
  COMMENT '角色代码',
  name         VARCHAR(32)                           NOT NULL
  COMMENT '角色名称',
  is_deleted   TINYINT                               NOT NULL                DEFAULT 0
  COMMENT '逻辑删除',
  created_time TIMESTAMP                             NOT NULL                DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  updated_time TIMESTAMP                             NOT NULL                DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '更新时间'
)
  COMMENT '角色表';
CREATE UNIQUE INDEX id_UNIQUE
  ON role (id);
CREATE INDEX created_time_ix
  ON role (created_time);
CREATE UNIQUE INDEX code_UNIQUE
  ON role (code);

-- ----------------------------
--  Table structure for menu
-- ----------------------------
DROP TABLE
IF EXISTS menu;

CREATE TABLE menu
(
  id           BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL
  COMMENT '主键',
  code         VARCHAR(32)                           NOT NULL
  COMMENT '菜单代码',
  name         VARCHAR(32)                           NOT NULL
  COMMENT '菜单名称',
  pcode        VARCHAR(32)                           NOT NULL                DEFAULT ''
  COMMENT '父菜单代码',
  url          VARCHAR(128)                          NOT NULL                DEFAULT ''
  COMMENT '菜单地址',
  sort         INT(11)                               NOT NULL                DEFAULT 0
  COMMENT '菜单排序(从0开始)',
  icon         VARCHAR(128)                          NOT NULL                DEFAULT ''
  COMMENT '菜单图标的样式',
  is_deleted   TINYINT                               NOT NULL                DEFAULT 0
  COMMENT '逻辑删除',
  created_time TIMESTAMP                             NOT NULL                DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  updated_time TIMESTAMP                             NOT NULL                DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '更新时间'
)
  COMMENT '菜单表';
CREATE UNIQUE INDEX id_UNIQUE
  ON menu (id);
CREATE INDEX created_time_ix
  ON menu (created_time);
CREATE INDEX sort_ix
  ON menu (sort);
CREATE UNIQUE INDEX code_UNIQUE
  ON menu (code);

-- ----------------------------
--  Table structure for user_role
-- ----------------------------
DROP TABLE
IF EXISTS user_role;

CREATE TABLE user_role
(
  username  VARCHAR(15) NOT NULL
  COMMENT '用户名',
  role_code VARCHAR(32) NOT NULL
  COMMENT '角色代码',
  PRIMARY KEY (username, role_code)
)
  COMMENT '用户角色表';

-- ----------------------------
--  Table structure for role_menu
-- ----------------------------
DROP TABLE
IF EXISTS role_menu;

CREATE TABLE role_menu
(
  role_code VARCHAR(32) NOT NULL
  COMMENT '角色代码',
  menu_code VARCHAR(32) NOT NULL
  COMMENT '菜单代码',
  PRIMARY KEY (role_code, menu_code)
)
  COMMENT '角色菜单表';

-- ----------------------------
--  Table structure for monitor
-- ----------------------------
DROP TABLE
IF EXISTS monitor;

CREATE TABLE monitor
(
  id           BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL
  COMMENT '主键',
  app          VARCHAR(20)                           NOT NULL                    DEFAULT ''
  COMMENT '系统名称',
  type         VARCHAR(32)                           NOT NULL                    DEFAULT ''
  COMMENT '监控类型',
  description  VARCHAR(128)                          NOT NULL                    DEFAULT ''
  COMMENT '描述',
  package_name VARCHAR(128)                          NOT NULL                    DEFAULT ''
  COMMENT '包名',
  class_name   VARCHAR(32)                           NOT NULL                    DEFAULT ''
  COMMENT '类名',
  method_name  VARCHAR(32)                           NOT NULL                    DEFAULT ''
  COMMENT '方法名',
  begin_time   BIGINT                                NOT NULL
  COMMENT '开始时间',
  end_time     BIGINT                                NOT NULL
  COMMENT '结束时间',
  return_type  VARCHAR(256)                          NOT NULL
  COMMENT '返回值类型',
  arg_types    VARCHAR(1024)                         NOT NULL
  COMMENT '参数类型',
  is_deleted   TINYINT                               NOT NULL                    DEFAULT 0
  COMMENT '逻辑删除',
  created_time TIMESTAMP                             NOT NULL                    DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  updated_time TIMESTAMP                             NOT NULL                    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '更新时间'
)
  COMMENT '监控表';
CREATE UNIQUE INDEX id_UNIQUE
  ON monitor (id);
CREATE INDEX created_time_ix
  ON monitor (created_time);
CREATE INDEX app_ix
  ON monitor (app);
CREATE INDEX begin_time_ix
  ON monitor (begin_time);
CREATE INDEX end_time_ix
  ON monitor (end_time);

#====================初始数据====================#

# 用户 admin
INSERT INTO user
(username, realname, password, salt, email)
VALUES
  ('admin', '管理员', '25500f5b85a66895e0b99117a12cd51b6d07eb13', '93fab0ba521763fc', 'java@kangyonggan.com');

-- ----------------------------
--  data for role
-- ----------------------------
INSERT INTO role
(code, name)
VALUES
  ('ROLE_ADMIN', '管理员'),
  ('ROLE_USER', '普通用户');

-- ----------------------------
--  data for menu
-- ----------------------------
INSERT INTO menu
(code, name, pcode, url, sort, icon)
VALUES
  ('DASHBOARD', '工作台', '', 'index', 0, 'menu-icon fa fa-dashboard'),

  ('SYSTEM', '系统', 'DASHBOARD', 'system', 1, 'menu-icon fa fa-cogs'),
  ('SYSTEM_USER', '用户管理', 'SYSTEM', 'system/user', 0, ''),
  ('SYSTEM_ROLE', '角色管理', 'SYSTEM', 'system/role', 1, ''),
  ('SYSTEM_MENU', '菜单管理', 'SYSTEM', 'system/menu', 2, ''),

  ('MONITOR', '监控', 'DASHBOARD', 'monitor', 2, 'menu-icon fa fa-bar-chart-o'),
  ('MONITOR_QUERY', '监控查询', 'MONITOR', 'monitor/query', 0, ''),
  ('MONITOR_STAT', '实时监控', 'MONITOR', 'monitor/stat', 1, ''),

  ('USER', '我的', 'DASHBOARD', 'user', 3, 'menu-icon fa fa-user'),
  ('USER_INFO', '基本信息', 'USER', 'user/info', 0, '');

-- ----------------------------
--  data for user_role
-- ----------------------------
INSERT INTO user_role
VALUES
  ('admin', 'ROLE_ADMIN');

-- ----------------------------
--  data for role_menu
-- ----------------------------
INSERT INTO role_menu SELECT
                        'ROLE_ADMIN',
                        code
                      FROM menu;

INSERT INTO role_menu SELECT
                        'ROLE_USER',
                        code
                      FROM menu
                      WHERE code LIKE 'USER%' OR code = 'DASHBOARD';
