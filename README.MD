# 基于SpringCloud alibaba的微服务基础环境

## 项目环境

|Spring Cloud|Spring Cloud Alibaba| Spring boot |      jdk    |    NACOS    |    seata    |
|------------| -------------------| ------------| ------------| ------------| ------------|
| Hoxton.SR9 |    2.2.6.RELEASE   |2.3.2.RELEASE|       1.8   |    1.40    |     1.30     |

## 组件功能

1. nacos：用于服务的注册与发现，配置中心
2. gateway：用作服务为网关
3. seata：用于分布式事务解决
4. redisson用于分布式锁

## 项目完成

1. 集成网关，NACOS注册中心与配置中心(已完成)
2. 集成seata分布式事务解决(已完成)
3. 基于knife4j的分布式接口文档(已完成)
4. 基于SpringSecurity权限管理(已完成)
5. 基于redis使用redisson开源工具实现分布式锁(已完成)
6. 日志系统

## 目录结构

### 基础服务目录结构

    support-parent---------------------------------服务父级目录
            |
            common---------------------------------项目基础依赖
            |
            gateway-server-------------------------网关服务

    auth-parent------------------------------------认证授权服务
            |
            common---------------------------------服务基础依赖
            |
            auth-server----------------------------服务认证主体

### 业务服务目录结构

    parent---------------------------------服务父级目录
         |
         common----------------------------服务基础依赖
         |
         server----------------------------主要服务
         |
         feign-----------------------------用于其他服务调用的接口

## 权限服务使用
第一步 引入ling-auth-common依赖

第二步 创建Security配置类继承WebSecurityCommonConfig

第三步 开启@EnableWebSecurity；设置优先级@Order(90)，value值越小优先级越高，默认100,@EnableGlobalMethodSecurity(prePostEnabled = true)开启权限校验

第四步 重写configure方法，首先写入super.configure(http);(父级configure方法做了授权是失败处理、跨域配置、全局放行配置，若需要自己定义可无需写入)

第五步 根据权限配置选择验证策略
## 接口地址
    网关接口地址/doc.html
    

    
    
        
