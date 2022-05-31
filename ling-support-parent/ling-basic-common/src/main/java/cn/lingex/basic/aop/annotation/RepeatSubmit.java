package cn.lingex.basic.aop.annotation;

import java.lang.annotation.*;

/**
 * <br>默认检验请求携带clientCode</br>
 * <br>isReSubmit为true时重复校验</br>
 *
 * @author LiaoJianbo
 * @version 1.0.0
 * @since 1.0.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RepeatSubmit {
    /**
     * 是否开启表单重复校验
     *
     * @return false否，true是
     */
    boolean isReSubmit() default false;
}
