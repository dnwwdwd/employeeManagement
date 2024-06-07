SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

## 创建数据库
create database if not exists dbtest2;

use dbtest2;

## 部门表
CREATE TABLE if not exists `department` (
  `id` int(10) NOT NULL auto_increment,
  `depId` int(10) NOT NULL,
  `department_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_ci NOT NULL COMMENT '部门名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_as_ci;

BEGIN;
INSERT INTO `department` VALUES (1, 1,'市场部');
INSERT INTO `department` VALUES (2, 2,'技术部');
INSERT INTO `department` VALUES (3, 3,'销售部');
INSERT INTO `department` VALUES (4, 4,'客服部');
INSERT INTO `department` VALUES (5, 5,'公关部');
COMMIT;


## 员工表
CREATE TABLE if not exists `employee` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `employee_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_ci NOT NULL COMMENT '员工姓名',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_ci NOT NULL COMMENT '员工邮箱',
  `gender` int(2) NOT NULL COMMENT '员工性别',
  `department_id` int(10) NOT NULL COMMENT '部门编号',
  `date` date NOT NULL COMMENT '入职日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_as_ci;


BEGIN;
INSERT INTO `employee` VALUES (1, '张三', 'zhangsan@gmail.com', 0, 1, '2020-05-12');
INSERT INTO `employee` VALUES (2, '李四', 'lisi@qq.com', 1, 2, '2020-05-05');
INSERT INTO `employee` VALUES (3, '王五', 'wangwu@126.com', 0, 3, '2020-05-15');
INSERT INTO `employee` VALUES (4, '赵六', 'zhaoliu@163.com', 1, 4, '2020-04-21');
INSERT INTO `employee` VALUES (5, '田七', 'tianqi@foxmail.com', 0, 3, '2020-05-14');
INSERT INTO `employee` VALUES (10, '王伟', 'wangwei@gmail.com', 1, 3, '2020-05-08');
INSERT INTO `employee` VALUES (11, '张伟', 'zhangwei@gmail.com', 1, 2, '2020-05-11');
INSERT INTO `employee` VALUES (12, '李伟', 'liwei@gmail.com', 1, 3, '2020-05-18');
COMMIT;

## 用户表
create table if not exists user
(
    id        int auto_increment
        primary key,
    user_name varchar(255) not null comment '用户名',
    password  varchar(255) not null comment '密码',
    roleId    int          not null comment '角色',
    empId     int          null comment '员工 id'
)
    collate = utf8mb4_0900_as_ci;


## 日志表
CREATE TABLE if not exists log (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      roleId INT NULL,
                      user_name VARCHAR(255) NULL,
                      operation VARCHAR(255) NULL
);