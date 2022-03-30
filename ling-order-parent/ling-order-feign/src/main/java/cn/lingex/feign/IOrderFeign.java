package cn.lingex.feign;

import cn.lingex.basic.pojo.dto.OrderDto;
import cn.lingex.basic.result.JSONResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 订单fei接口
 * FeignClient=》valuse调用的服务，nacos大小写敏感，eureka大小写不敏感
 * @author LiaoJianbo
 * @version 1.0.0
 * @since 1.0.0
 */
@FeignClient(value = "order-server",fallbackFactory = OrderFeignFallbackFactory.class)
public interface IOrderFeign {

    /**
     * 保存订单
     *
     * @param orderDto 保存订单
     * @return 统一响应对象
     */
    @PutMapping("/order")
    JSONResult<String> save(@RequestBody @Validated OrderDto orderDto);
}
