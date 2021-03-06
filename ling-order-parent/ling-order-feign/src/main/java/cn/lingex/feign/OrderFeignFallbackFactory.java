package cn.lingex.feign;

import cn.lingex.basic.constant.BusinessConstant;
import cn.lingex.basic.result.JSONResult;
import cn.lingex.basic.result.ResultCodeEnum;
import feign.hystrix.FallbackFactory;

/**
 * 拖地回调
 * 直接实现IFeign为默认回调
 * 实现FallbackFactory为熔断回调
 *
 * @author LiaoJianbo
 * @version 1.0.0
 * @since 1.0.0
 */
public class OrderFeignFallbackFactory implements FallbackFactory<IOrderFeign> {
    @Override
    public IOrderFeign create(Throwable throwable) {
        return orderDto -> JSONResult.failure(ResultCodeEnum.SERVICE_FAILURE.getCode(), throwable.getMessage());
    }
}
