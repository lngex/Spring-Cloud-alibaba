package cn.lingex.basic.constant;

/**
 * 业务常量
 *
 * @author LiaoJianbo
 * @version 1.0.0
 * @since 1.0.0
 */
public interface BusinessConstant {
    /**
     * 操作成功代码
     */
    String RESULT_SUCCESS_CODE = "S0000";
    /**
     * 操作成功提示
     */
    boolean RESULT_SUCCESS_STATUS = true;
    /**
     * 操作失败代码
     */
    String RESULT_FAILED_CODE = "F0000";
    /**
     * 操作失败提示
     */
    boolean RESULT_FAILED_STATUS = false;
    /**
     * 客户端代码
     */
    String CLIENT_CODE = "clientCode";
    /**
     * 重复提交时间
     */
    String REPEAT_SUBMIT = "repeatSubmit";
    /**
     * 重复时间过期时间，单位秒
     */
    long REPEAT_SUBMIT_EXPIRATION_TIME = 2;
}
