package cn.lingex.basic.constant;

/**
 * 业务常量
 *
 * @author LiaoJianbo
 * @version 1.0.0
 * @since 1.0.0
 */
public interface AuthBusinessConstant {
    /**
     * 客户端编码
     */
    String CLIENT_CODE = "clientCode";
    /**
     * 验证码key
     */
    String REDIS_VERIFICATION_CODE = "dianZiZhiBaoKa:verificationCode:";
    /**
     * 登录信息
     */
    String LOGIN_INFO = "dianZiZhiBaoKa:login:";
    /**
     * TOKEN 过期时间，单位
     */
    Long TOKEN_EXPIRATION_TIME = 60 * 60 * 24L;
}