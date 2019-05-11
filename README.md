# distrubuted
## 组织结构
```lua
distrubuted
├── hc-common -- 公共模块
├── hc-basic  -- 一些练习
├── hc-sso    -- 单点登录
```
## 项目介绍
主要是本人平时的一些练习，可能写得有点乱，这里会提一下个人认为比较重要的部分。

### 技术说明
代码风格使用的是晓风轻的代码规范：[我的编码习惯 - Controller规范](https://zhuanlan.zhihu.com/p/28717374)

### 使用动态代理封装数据库操作
代码放置在`hc-sql`模块中

实现的功能

可使用`@AutoDao`实现实例的注入，注入的类需要是经过`@DaoMapper`注释的：
```java
@AutoDao
HelloWordDao helloWordDao;
```
```java
@DaoMapper
public interface HelloWordDao {}
```
可使用`@SqlAnnotation`在接口方法上进行SQL语句的书写：
```java
@SqlAnnotation("SELECT * FROM user WHERE user_name = #{userName}")
User sayHello(@SqlParam("userName") String userName);
```
其返回值支持引用类型、基本类型与List。

使用`@SqlParam`确定参数类型

防SQL注入处理：可以区分井号符#与美元符$的区别，对于使用井号符#修饰的参数进行防SQL注入处理

加入连接池减少建立连接产生的性能消耗

使用.yml文件配置数据库

未实现功能

不支持引用类型的传参

实现教程:
- [利用反射、静态代理、动态代理封装数据库操作（一）：整体框架介绍](https://blog.csdn.net/weixin_41973131/article/details/90050623)

### SSO单点登录
可参考文章：
- 关于SSO的基础学习：[单点登录（SSO），从原理到实现](https://cloud.tencent.com/developer/article/1166255)
- 进阶：[单点登录(一)-----理论-----单点登录SSO的介绍和CAS+选型](https://blog.csdn.net/zzq900503/article/details/54646828)

实现了以下接口
- SSO认证中心登录：http://localhost:8889/user/login
- SSO认证中心注销：http://localhost:8889/user/logout
- SSO认证中心验证：http://localhost:8889/user/valid
- 子系统注销：http://localhost:8889/user/logout
- 子系统接收令牌：http://localhost:8889/user/token

实现教程:
- [基于Springboot、Java的SSO单点登陆系统的登陆操作实战](https://blog.csdn.net/weixin_41973131/article/details/89956210)
- [基于Springboot、Java的SSO单点登陆系统的注销操作实战](https://blog.csdn.net/weixin_41973131/article/details/89960871)