package cn.lingex.basic.result;


/**
 * @Description: 返回枚举
 * @Author SUHAO
 * @Date 2020-12-13
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
    DUPLICATE_ACCOUNT(8401,"用户已存在"),
    UNAUTHORIZED(8402, "token校验失败"),
    FORBIDDEN(8403, "无权限访问"),
    USER_DELETE_BAN(8404, "用户被删除或封禁"),
    INTERNAL_SERVER_ERROR(8500, "系统繁忙！"),
    PARAMETER_ERROR(8503, "参数错误"),
    USER_NOT_EXISTENT(8405,"用户名不存在"),
    TOKEN_EXPIRE(401,"token已过期"),
    ERRO_PHONE(8407,"电话号码格式错误"),
    send_massage_fail(8408,"发送短信失败"),
    ERROR_PASSWORD(8409,"密码错误");

    private final int code;
    private String message;

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
