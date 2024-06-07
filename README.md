# 员工管理系统（SpringBoot + Mybatis + Thymeleaf）

SpringBoot整合CRUD实现员工管理案例，将Mybatis整合到原项目中，加入了数据库，添加了日期选项的控件。

## 项目介绍

### 员工管理功能

![image-20240607130728055](https://hejiajun-img-bucket.oss-cn-wuhan-lr.aliyuncs.com/img/20240607130728.png)

### 添加员工![image-20240607130805562](https://hejiajun-img-bucket.oss-cn-wuhan-lr.aliyuncs.com/img/20240607130805.png)

### 修改员工![image-20240607130821353](https://hejiajun-img-bucket.oss-cn-wuhan-lr.aliyuncs.com/img/20240607130821.png)

### 部门管理功能

![image-20240607130842139](https://hejiajun-img-bucket.oss-cn-wuhan-lr.aliyuncs.com/img/20240607130842.png)

### 添加部门

![image-20240607130856999](https://hejiajun-img-bucket.oss-cn-wuhan-lr.aliyuncs.com/img/20240607130857.png)

### 修改部门

![image-20240607130914000](https://hejiajun-img-bucket.oss-cn-wuhan-lr.aliyuncs.com/img/20240607130914.png)

### 日志查看

![image-20240607130950686](https://hejiajun-img-bucket.oss-cn-wuhan-lr.aliyuncs.com/img/20240607130950.png)

### 查看员工登录信息

![image-20240607131010923](https://hejiajun-img-bucket.oss-cn-wuhan-lr.aliyuncs.com/img/20240607131011.png)

### 添加员工登录信息

![image-20240607131021961](https://hejiajun-img-bucket.oss-cn-wuhan-lr.aliyuncs.com/img/20240607131022.png)

### 管理员个人信息

![image-20240607131047351](https://hejiajun-img-bucket.oss-cn-wuhan-lr.aliyuncs.com/img/20240607131047.png)

### 员工查看同部门员工信息

![image-20240607131127331](https://hejiajun-img-bucket.oss-cn-wuhan-lr.aliyuncs.com/img/20240607131127.png)

### 员工个人信息

![image-20240607131139102](https://hejiajun-img-bucket.oss-cn-wuhan-lr.aliyuncs.com/img/20240607131139.png)

## 项目跑通流程

#### 环境要求

- JDK8以上
- IDEA
- MySQL8 
- Maven3  
- 需要熟练掌握MySQL数据库，SpringBoot及MyBatis知识，简单的前端知识；

#### 数据库环境

创建案例所使用的数据库

```sql
create database if not exists dbtest2;

use dbtest2;
```

创建登陆用户数据表

```sql
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

```



创建部门信息的数据库表

```sql
## 部门表
CREATE TABLE if not exists `department` (
  `id` int(10) NOT NULL,
  `department_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_ci NOT NULL COMMENT '部门名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_as_ci;

BEGIN;
INSERT INTO `department` VALUES (1, '市场部');
INSERT INTO `department` VALUES (2, '技术部');
INSERT INTO `department` VALUES (3, '销售部');
INSERT INTO `department` VALUES (4, '客服部');
INSERT INTO `department` VALUES (5, '公关部');
COMMIT;
```



创建存放员工信息的数据库表

```sql
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
```

存放日志信息的数据库表

```sql
## 日志表
CREATE TABLE if not exists logs (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      roleId INT NULL,
                      user_name VARCHAR(255) NULL,
                      operation VARCHAR(255) NULL
);
```





#### 基本环境搭建

1. 新建Spring项目， 添加Lombok，Spring Web，Thymeleaf，Mybatis，MySQL Driver的支持
2. 相关的pom依赖

```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
                        <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>2.1.2</version>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
            <exclusions>
                <exclusion>
                    <groupId>org.junit.vintage</groupId>
                    <artifactId>junit-vintage-engine</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
```

3. 建立基本结构和配置框架

`com/`
 `|-- godfrey
      |-- config
      |-- controller
      |-- dto
      |-- mapper
      |-- pojo
      |-- service`

4. application.yml里配置数据库连接信息及Mapper映射文件信息

```yml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost/dbtest2?useSSL=true&useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8
    username: root
    password: 123456
  thymeleaf:
    cache: false
  messages:
    basename: i18n.login
server:
  port: 8081

mybatis:
  type-aliases-package: com.godfrey.pojo
  mapper-locations: classpath:com.godfrey.mapper/*Mapper.xml
  configuration:
    map-underscore-to-camel-case: true
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
```

5. 测试数据库连接

```java
package com.godfrey;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class ApplicationTests {

    @Autowired
    DataSource dataSource;

    @Test
    void contextLoads() throws SQLException {
        System.out.println("数据源>>>>>>" + dataSource.getClass());
        Connection connection = dataSource.getConnection();
        System.out.println("连接>>>>>>>>>" + connection);
        System.out.println("连接地址>>>>>" + connection.getMetaData().getURL());
        connection.close();
    }

}
```

**查看输出结果，数据库配置ok**

#### 创建pojo实体类

1. 创建User实体

```java
@Data
public class User {
    private Integer id;
    private String userName;
    private String password;
    private Integer roleId;
    private Integer empId;
}

```

2. 创建Department实体

```java
@Data
public class Department {
    private Integer id;
    private Integer depId;
    private Integer newDepId;
    private String departmentName;
}
```

3. 创建Employee实体

```java
@Data
public class Employee {
    private Integer id;
    private String employeeName;
    private String email;
    private Integer gender; //0:女  1:男
    private Date date;
    private Integer departmentId;
}
```

4. 创建Log实体

```java
@Data
@AllArgsConstructor
public class Log {
    private int roleId;
    private String user_name;
    private String operation;
}
```

#### Mapper层

文件存放目录：

com.godfrey.mapper 相关接口

resources/com.godfrey.mapper 相关mapper.xml

1. 编写User的Mapper接口：UserMapper

```java
@Mapper
@Repository
public interface UserMapper {
    User selectPasswordByName(@Param("userName") String userName, @Param("password") String password);
    User selectUserInfoById(@Param("id") Integer id);
    List<User> empInfoFromSameDep(@Param("empId") Integer empId);
    int updateUserInfo(@Param("id") Integer id, @Param("user_name") String user_name,@Param("password") String password);
    List<User> selectEmpInfoList();
    int deleteEmp(@Param("id") Integer id);
    int addUser(@Param("id") Integer id, @Param("user_name") String user_name,
                @Param("password") String password, @Param("empId") Integer empId);
}

```

2. 编写接口对应的Mapper.xml文件：UserMapper.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.godfrey.mapper.UserMapper">
    <resultMap id="EmployeeDTO" type="com.godfrey.dto.EmployeeDTO">
        <id column="id" jdbcType="INTEGER" property="id"/>
        <result column="employee_name" jdbcType="VARCHAR" property="employeeName"/>
        <result column="email" jdbcType="VARCHAR" property="email"/>
        <result column="gender" jdbcType="INTEGER" property="gender"/>
        <result column="department_name" jdbcType="VARCHAR" property="departmentName"/>
        <result column="date" jdbcType="DATE" property="date"/>
    </resultMap>

    <select id="selectPasswordByName" resultType="User">
        select * from user where user_name = #{userName} and password = #{password};
    </select>

    <select id="selectUserInfoById" resultType="User">
        select * from user where id = #{id}
    </select>

    <select id="empInfoFromSameDep" resultMap="EmployeeDTO">
        select e.id, e.employee_name, e.email, e.gender, d.department_name, e.date
        from employee as e left join department as d
        on e.department_id = d.id
        where department_id = (select department_id from employee where id = #{empId})
    </select>

    <update id="updateUserInfo" parameterType="User">
        update user set user_name = #{user_name}, password = #{password} where id = #{id}
    </update>

    <select id="selectEmpInfoList" resultType="User">
        select * from user where roleId = 0
    </select>

    <delete id="deleteEmp" parameterType="int">
        delete from user where id = #{id}
    </delete>
    
    <insert id="addUser" parameterType="User">
        insert into user(user_name, password, roleId, empId) values(#{user_name}, #{password}, 0, #{empId})
    </insert>
</mapper>
```

3. 编写Department的Mapper接口：DepaertmentMapper

```java
@Mapper
@Repository
public interface DepartmentMapper {

    List<DepartmentDTO> selectAllDepartment();
    DepartmentDTO selectDepartmentById(@Param("depId") Integer depId);
    int addDepartment(DepartmentDTO departmentDTO);
    //修改一个部门信息
    int updateDepartment(@Param("depId") Integer depId, @Param("newDepId") Integer newDepId, @Param("department_name") String department_name);
    //根据id删除部门信息
    int deleteDepartment(@Param("depId") Integer depId);
}
```

4. 编写接口对应的Mapper.xml文件：DepaertmentMapper.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.godfrey.mapper.DepartmentMapper">

    <resultMap id="DepartmentDTO" type="com.godfrey.dto.DepartmentDTO">
        <id column="id" jdbcType="BIGINT" property="id"/>
        <result column="depId" jdbcType="BIGINT" property="depId"/>
        <result column="department_name" jdbcType="VARCHAR" property="department_name"/>
    </resultMap>

    <select id="selectAllDepartment" resultMap="DepartmentDTO">
        select * from department
    </select>

    <select id="selectDepartmentById" resultMap="DepartmentDTO">
        select * from department where depId = #{depId}
    </select>

    <insert id="addDepartment" parameterType="Department">
        insert into department(depId, department_name) values(#{depId}, #{department_name})
    </insert>

    <update id="updateDepartment" parameterType="Department">
        update department set depId = #{newDepId}, department_name = #{department_name} where depId = #{depId}
    </update>

    <delete id="deleteDepartment" parameterType="Department">
        delete from department where depId = #{depId}
    </delete>
</mapper>
```

5. 编写Employee的Mapper接口：EmployeeMapper

```java
@Mapper
@Repository
public interface EmployeeMapper {
    //查询全部员工信息
    List<EmployeeDTO> selectAllEmployeeDTO();

    //根据id查询员工信息
    Employee selectEmployeeById(@Param("id") Integer id);

    //添加一个员工信息
    int addEmployee(Employee employee);

    //修改一个员工信息
    int updateEmployee(Employee employee);

    //根据id删除员工信息
    int deleteEmployee(@Param("id") Integer id);
}
```

6. 编写接口对应的Mapper.xml文件：EmployeeMapper.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.godfrey.mapper.EmployeeMapper">

    <resultMap id="EmployeeDTO" type="com.godfrey.dto.EmployeeDTO">
        <id column="id" jdbcType="INTEGER" property="id"/>
        <result column="employee_name" jdbcType="VARCHAR" property="employeeName"/>
        <result column="email" jdbcType="VARCHAR" property="email"/>
        <result column="gender" jdbcType="INTEGER" property="gender"/>
        <result column="department_name" jdbcType="VARCHAR" property="departmentName"/>
        <result column="date" jdbcType="DATE" property="date"/>
    </resultMap>

    <select id="selectAllEmployeeDTO" resultMap="EmployeeDTO">
        select e.id, e.employee_name, e.email, e.gender, d.department_name, e.date
        from employee as e left join department as d
        on e.department_id = d.id
    </select>

    <select id="selectEmployeeById" resultType="Employee">
        select *
        from employee
        where id = #{id}
    </select>

    <insert id="addEmployee" parameterType="Employee">
        insert into employee (id, employee_name, email, gender, department_id, date)
        values (#{id}, #{employeeName}, #{email}, #{gender}, #{departmentId}, #{date})
    </insert>

    <update id="updateEmployee" parameterType="Employee">
        update employee
        set employee_name=#{employeeName},
            email=#{email},
            gender=#{gender},
            department_id=#{departmentId},
            date=#{date}
        where id = #{id}
    </update>

    <delete id="deleteEmployee" parameterType="int">
        delete
        from employee
        where id = #{id}
    </delete>
</mapper>
```

7. 编写Log的Mapper接口：LogMapper

```java
@Mapper
@Repository
public interface LogMapper {
    int insertLogOperation(@Param("roleId") int roleId, @Param("user_name") String user_name, @Param("operation") String operation);
    List<LogDTO> selectAllLogOperations();
}

```

8. 编写接口对应的Mapper.xml文件：LogMapper.xml

```java
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.godfrey.mapper.LogMapper">

    <resultMap id="LogDTO" type="com.godfrey.dto.LogDTO">
        <id column="id" jdbcType="INTEGER" property="id"/>
        <result column="roleId" jdbcType="INTEGER" property="roleId"/>
        <result column="user_name" jdbcType="VARCHAR" property="user_name"/>
        <result column="operation" jdbcType="VARCHAR" property="operation"/>
    </resultMap>

    <insert id="insertLogOperation" parameterType="Log">
        insert into log(roleId, user_name, operation) values(#{roleId}, #{user_name} , #{operation})
    </insert>

    <select id="selectAllLogOperations" resultMap="LogDTO">
        select * from log
    </select>

</mapper>
```

