package cn.lingex.service;

import cn.lingex.basic.pojo.domain.Order;
import cn.lingex.basic.pojo.dto.OrderDto;
import cn.lingex.basic.result.JSONResult;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 业务层规范
 *
 * @author LiaoJianbo
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IOrderService extends IService<Order> {
    /**
     * 保存订单
     *
     * @param orderDto 保存订单
     */
    JSONResult<String> add(OrderDto orderDto);
}
