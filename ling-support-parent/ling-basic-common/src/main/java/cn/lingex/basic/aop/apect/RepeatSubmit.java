package cn.lingex.basic.aop.apect;


import cn.lingex.basic.constant.BusinessConstant;
import cn.lingex.basic.exception.BusinessException;
import cn.lingex.basic.config.RedisService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 表头校验与表单重复提交检验
 *
 * @author LiaoJianbo
 * @version 1.0.0
 * @since 1.0.0
 */
@Component
@Aspect
public class RepeatSubmit {

    @Autowired
    private RedisService redisService;

    /**
     *     TODO: 2022/5/30 此处需要更改项目包名
     */
    @Pointcut("@annotation(cn.lingex.basic.aop.annotation.RepeatSubmit)")
    public void poin() {
    }

    @Before("poin()")
    public void doBefor(JoinPoint point) {
        // 获取当前请求对象
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        MethodSignature signature = (MethodSignature) point.getSignature();
        HttpServletRequest request = requestAttributes.getRequest();
        String clientCode = request.getHeader(BusinessConstant.CLIENT_CODE);
        if (!StringUtils.hasText(clientCode)) {
            throw new BusinessException("clientCode为null");
        }
        Method method = signature.getMethod();
        cn.lingex.basic.aop.annotation.RepeatSubmit annotation = method.getAnnotation(cn.lingex.basic.aop.annotation.RepeatSubmit.class);
        String requestURI = request.getRequestURI();
        if (annotation.isReSubmit()) {
            String o = redisService.get(BusinessConstant.REPEAT_SUBMIT + requestURI + clientCode);
            if (StringUtils.hasText(o)) {
                throw new BusinessException("请勿重复提交表单数据!");
            }
            redisService.set(BusinessConstant.REPEAT_SUBMIT + requestURI + clientCode, clientCode, BusinessConstant.REPEAT_SUBMIT_EXPIRATION_TIME);
        }
    }
}
