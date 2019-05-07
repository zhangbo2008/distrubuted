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
代码放置在`hc-basic`模块中的`src/main/java/com/hc/hcbasic/sqlReflect`包中。

#### 实现的功能
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
其返回值支持引用类型（不包括List）与基本类型。

此处的`@SqlParam`其实只起到了标记的作用，注入userName只是根据形参名进行注入的。本来是打算用`@SqlParam`实现，不过写到后面发现写错了，虽然要改也不难，但就先这样，有兴趣的朋友可以自己修改。

防SQL注入处理：可以区分井号符#与美元符$的区别，对于使用井号符#修饰的参数进行防SQL注入处理
#### 未实现功能
没有实现类似`List<User>`的返回值

### SSO单点登录
可参考文章：
关于SSO的基础学习：[单点登录（SSO），从原理到实现](https://cloud.tencent.com/developer/article/1166255)
进阶：[单点登录(一)-----理论-----单点登录SSO的介绍和CAS+选型](https://blog.csdn.net/zzq900503/article/details/54646828)

实现了以下接口
SSO认证中心登录：http://localhost:8889/user/login
SSO认证中心注销：http://localhost:8889/user/logout
SSO认证中心验证：http://localhost:8889/user/valid
子系统注销：http://localhost:8889/user/logout
子系统接收令牌：http://localhost:8889/user/token
