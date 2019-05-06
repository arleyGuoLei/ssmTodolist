别问我readme为什么这么丑, 因为从word实验报告复制过来的文本。ui很漂亮，`你相信我, 就把球给我`。

一、实验目的
基于IDEA和Maven通过SSM (Spring + Spring MVC + MyBatis)框架搭建在Tomcat服务器上运行的Web应用程序。

二、设备与环境
硬件：多媒体计算机
软件：IDEA、Mysql、SSM框架(Spring + Spring MVC + MyBatis)、Tomcat、jdk等

三、实验内容及要求
1.实验要求

    1、完成一个TodoList管理系统, 包含注册、登录、任务管理页、退出登录等。
    2、准确设计Mysql数据库,包含建表sql语句。
    3、登录/退出登录包含session操作的正规流程，表单有字段校验, 密码有加密，账号注册有检查是否已被注册等处理。

2.实验内容
①. 用户注册:  http://127.0.0.1:8080/register.html
A. 注册提交前, 前端对用户名和密码进行校验。
- 用户名必须为邮箱格式 (正则校验)
- 密码和再次输入密码是否一致，且长度为6~12位
B.提交注册
  - 用户是否已经存在判断, 后端控制器中对提交的数据进行校验
  - 通过Ajax发送请求 (基于jQuery)
  - 注册之后的密码, 通过md5加密之后存储到数据库

②. 用户登录:  http://127.0.0.1:8080/login.html
   A. 登录前后端都有对字段安全性进行校验，校验同注册。
   B. 登录操作
   - 判断账号和密码是否完全正确, MD5加密之后查询
   - 登录成功添加session, 浏览器记录cookie操作
   - 登录成功之后, 跳转到todo页面
③.  TodoList页面: http://127.0.0.1:8080/index.html
  A. 判断是否已经登录
   - 进入页面先获取session, 判断是否登录, 如果没有登录则跳转到登录页面
   - 如果已经登录, 返回用户名在前端浏览器中显示
B. 支持todolist的增删操作
   - 能分别记录不同用户的todolist , 并且能进行对应的增删
   - 由于时间和精力原因, 数据没放数据库了, 只是简单的前端浏览器localStroge存储(根据用户名)

④. 数据库 （用户表）
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(50) NOT NULL DEFAULT '' COMMENT '邮箱',
  `password` varchar(32) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




