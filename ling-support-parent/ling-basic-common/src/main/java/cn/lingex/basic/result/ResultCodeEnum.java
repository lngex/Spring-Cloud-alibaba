package cn.lingex.basic.result;


/**
 * 响应结果
 */
public enum ResultCodeEnum {
    /**
     * 返回状态枚举列表
     */
    OK(200, "成功"),
    /**
     * 失败
     */
    FAILURE(500, "失败"),
    /**
     * 服务调用失败
     */
    SERVICE_FAILURE(501, "服务调用失败"),
    /**
     * TOKEN失效
     */
    TOKEN_INVALID(401, "token失效");

    private final int code;
    private final String message;

    ResultCodeEnum(int state, String message) {
        this.code = state;
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
