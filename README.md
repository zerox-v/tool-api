## 前言
该项目是Java版本的短视频去水印服务端，包括用户签到、积分制度、解析各大短视频无水印视频链接等功能，后续我有时间会陆续加入更多功能。最佳支持抖音、快手等短视频去水印
## 演示
![小程序码](https://file.ionic.fun/gh_f721ab07b5df_258.jpg)
## 项目结构
```
ToolApi
├── config -- 工具类及通用代码
├── conrtoller -- 系统接口
├── core -- 核心异常拦截
├── exception -- 断言类及自定义异常
├── mapper -- 数据映射
├── model -- 实体
└── service -- 服务逻辑处理
```
## 技术选型
技术 | 说明 | 官网
--------- | ------------- | -------------
MySql |  数据库 | https://www.mysql.com/
SpringBoot| 容器+MVC框架 | https://spring.io/projects/spring-boot
MyBatis-Plus | ORM框架 | https://mp.baomidou.com/

## 启动流程
### 环境
- Openjdk 16
### 开发工具
- IDEA
### 流程
###### 1. 修改application.yml配置文件
```yml
spring:
  datasource:
    url: #数据库链接字符串
    username: #数据库账号
    password: #数据库密码
wx:
  appId: #填写你自己的小程序appId
  secret: #填写你自己的小程序secret    
```
###### 2、使用你熟悉的MySql管理工具导入 `db` 文件下的 'video_mysql.sql'
###### 3. IDEA启动项目
###### 4. 浏览器访问 http://127.0.0.1:9092/

### 小程序源码
[视频解析小程序端](https://github.com/zerox-v/remove-watermark)
