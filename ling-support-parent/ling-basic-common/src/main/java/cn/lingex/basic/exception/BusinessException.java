package cn.lingex.basic.exception;

/**
 * 业务异常
 * @author LiaoJianbo
 * @version 1.0.0
 * @since 1.0.0
 */
public class BusinessException extends RuntimeException {
    /**
     *  自定义业务异常
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public BusinessException(String message) {
        super(message);
    }
}
