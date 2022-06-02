package cn.lingex.basic.exception;


import cn.lingex.basic.result.JSONResult;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * 全局异常处理
 *
 * @author LiaoJianbo
 * @version 1.0.0
 * @since 1.0.0
 */
@RestControllerAdvice
public class GlobalException {

    public static Class<?> aClass = null;

    static {
        try {
            aClass = Class.forName("org.springframework.security.access.AccessDeniedException");
        } catch (ClassNotFoundException e) {
            LoggerFactory.getLogger(GlobalException.class).error("创建AccessDeniedException字节码对象时失败");
        }
    }

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 抓取JSR303异常
     *
     * @param e 被捕获的异常
     * @return ResObject 统一返回对象
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public JSONResult<String> captureMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request, HttpServletResponse response) {
        // 获取所有的异常信息
        List<FieldError> errors = e.getBindingResult().getFieldErrors();
        // 使用map去重
        HashMap<String, String> map = new HashMap<>();
        errors.stream().forEach(fieldError -> {
            map.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        // 获取值集和
        Collection<String> values = map.values();
        String message = null;
        for (String v : values) {
            message = v;
        }
        this.error(e, request);
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return JSONResult.failure(message);
    }

    /**
     * 抓取自定义业务异常
     *
     * @param e 被捕获的异常
     * @return ResObject 统一返回对象
     */
    @ExceptionHandler(BusinessException.class)
    public JSONResult<String> captureBusinessException(BusinessException e, HttpServletRequest request, HttpServletResponse response) {
        this.error(e, request);
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return JSONResult.failure(e.getMessage());
    }

    /**
     * 抓取Feign调用异常
     *
     * @param e 被捕获的异常
     * @return ResObject 统一返回对象
     */
    @ExceptionHandler(FeignException.class)
    public JSONResult<String> captureBusinessException(FeignException e, HttpServletRequest request, HttpServletResponse response) {
        this.error(e, request);
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        String[] split = e.getMessage().split(",\"data\":\"");
        if (split.length != 2) {
            return JSONResult.failure("系统服务繁忙");
        } else {
            return JSONResult.failure(split[1].substring(0, split[1].length() - 3));
        }
    }

    /**
     * 抓取运行时异常
     *
     * @param e 被捕获的异常
     * @return ResObject 统一返回对象
     */
    @ExceptionHandler(RuntimeException.class)
    public JSONResult<String> captureRuntimeException(Exception e, HttpServletRequest request, HttpServletResponse response) {
        this.error(e, request);
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        if (e.getClass().equals(aClass)) {
            return JSONResult.failure(e.getMessage());
        }
        return JSONResult.failure("网络繁忙");
    }

    /**
     * 抓取异常
     *
     * @param e 被捕获的异常
     * @return ResObject 统一返回对象
     */
    @ExceptionHandler(Exception.class)
    public JSONResult<String> maxException(Exception e, HttpServletRequest request, HttpServletResponse response) {
        this.error(e, request);
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return JSONResult.failure("系统繁忙");
    }

    public void error(Exception e, HttpServletRequest request) {
        logger.error("异常接口{},异常原因{}{}", request.getRequestURI(), e.getMessage(), e);
    }
}
