package cn.lingex.feign;

/**
 * TODO
 *
 * @author LiaoJianbo
 * @version 1.0.0
 * @since 1.0.0
 */

import cn.lingex.basic.result.JSONResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 库存fei接口
 * FeignClient=》valuse调用的服务，nacos大小写敏感，eureka大小写不敏感
 * @author LiaoJianbo
 *rsion 1.0.0
 * @since 1.0.0
 */
@FeignClient(value = "stock-server",fallbackFactory = StockFeignFallbackFactory.class)
@RequestMapping("/stock")
public interface StockFeign {

    @GetMapping("/{id}")
    JSONResult<String> save(@PathVariable("id") Long id);
}
