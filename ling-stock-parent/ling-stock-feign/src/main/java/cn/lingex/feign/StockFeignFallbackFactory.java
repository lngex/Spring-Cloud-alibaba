package cn.lingex.feign;

import cn.lingex.basic.BusinessConstant;
import cn.lingex.basic.result.JSONResult;
import feign.hystrix.FallbackFactory;

/**
 * 托底数据
 *
 * @author LiaoJianbo
 * @version 1.0.0
 * @since 1.0.0
 */
public class StockFeignFallbackFactory  implements FallbackFactory<IStockFeign> {
    @Override
    public IStockFeign create(Throwable throwable) {
        return id -> JSONResult.getInstance(throwable.getMessage()).setCode(BusinessConstant.RESULT_FAILED_CODE).setStatus(BusinessConstant.RESULT_FAILED_STATUS);
    }
}
