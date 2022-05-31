package cn.lingex.feign;

import cn.lingex.basic.constant.BusinessConstant;
import cn.lingex.basic.result.JSONResult;
import cn.lingex.basic.result.ResultCodeEnum;
import feign.hystrix.FallbackFactory;

/**
 * 托底数据
 *
 * @author LiaoJianbo
 * @version 1.0.0
 * @since 1.0.0
 */
public class StockFeignFallbackFactory implements FallbackFactory<IStockFeign> {
    @Override
    public IStockFeign create(Throwable throwable) {
        return id -> JSONResult.failure(ResultCodeEnum.SERVICE_FAILURE.getCode(), throwable.getMessage());
    }
}
