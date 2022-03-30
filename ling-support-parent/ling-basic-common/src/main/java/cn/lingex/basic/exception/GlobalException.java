package cn.lingex.basic.exception;


import cn.lingex.basic.BusinessConstant;
import cn.lingex.basic.result.JSONResult;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * 抓取JSR303异常
     *
     * @param e 被捕获的异常
     * @return ResObject 统一返回对象
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public JSONResult<String> captureMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletResponse response) {
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
        logger.error(e.getMessage());
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return JSONResult.getInstance(message).setCode(BusinessConstant.RESULT_FAILED_CODE).setStatus(BusinessConstant.RESULT_FAILED_STATUS);
    }

    /**
     * 抓取自定义业务异常
     *
     * @param e 被捕获的异常
     * @return ResObject 统一返回对象
     */
    @ExceptionHandler(BusinessException.class)
    public JSONResult<String> captureBusinessException(BusinessException e, HttpServletResponse response) {
        logger.error(e.getMessage());
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return JSONResult.getInstance(e.getMessage()).setCode(BusinessConstant.RESULT_FAILED_CODE).setStatus(BusinessConstant.RESULT_FAILED_STATUS);
    }

    /**
     * 抓取Feign调用异常
     *
     * @param e 被捕获的异常
     * @return ResObject 统一返回对象
     */
    @ExceptionHandler(FeignException.class)
    public JSONResult<String> captureBusinessException(FeignException e, HttpServletResponse response) {
        logger.error(e.getMessage());
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        String[] split = e.getMessage().split(",\"data\":\"");
        if (split.length != 2) {
            return JSONResult.getInstance("系统服务繁忙").setCode(BusinessConstant.RESULT_FAILED_CODE).setStatus(BusinessConstant.RESULT_FAILED_STATUS);
        } else {
            return JSONResult.getInstance(split[1].substring(0, split[1].length() - 3)).setCode(BusinessConstant.RESULT_FAILED_CODE).setStatus(BusinessConstant.RESULT_FAILED_STATUS);
        }
    }

    /**
     * 抓取运行时异常
     *
     * @param e 被捕获的异常
     * @return ResObject 统一返回对象
     */
    @ExceptionHandler(RuntimeException.class)
    public JSONResult<String> captureRuntimeException(Exception e, HttpServletResponse response) {
        logger.error(e.getMessage(), e);
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return JSONResult.getInstance("网络繁忙").setCode(BusinessConstant.RESULT_FAILED_CODE).setStatus(BusinessConstant.RESULT_FAILED_STATUS);
    }

    /**
     * 抓取异常
     *
     * @param e 被捕获的异常
     * @return ResObject 统一返回对象
     */
    @ExceptionHandler(Exception.class)
    public JSONResult<String> maxException(Exception e, HttpServletResponse response) {
        logger.error(e.getMessage(), e);
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return JSONResult.getInstance("系统繁忙").setCode(BusinessConstant.RESULT_FAILED_CODE).setStatus(BusinessConstant.RESULT_FAILED_STATUS);
    }
}
