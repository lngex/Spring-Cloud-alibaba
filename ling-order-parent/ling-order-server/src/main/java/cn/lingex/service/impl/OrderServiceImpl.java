package cn.lingex.service.impl;

import cn.lingex.basic.pojo.domain.Order;
import cn.lingex.basic.pojo.dto.OrderDto;
import cn.lingex.basic.result.JSONResult;
import cn.lingex.basic.utils.SnowflakeUtil;
import cn.lingex.mapper.OrderMapper;
import cn.lingex.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * 订单业务层实现
 *
 * @author LiaoJianbo
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;

    /**
     * 保存订单
     *
     * @param orderDto 保存订单
     */
    @Override
    @Transactional
    public JSONResult<String> add(OrderDto orderDto) {
        Order order = new Order();
        BeanUtils.copyProperties(orderDto,order);
        order.setId(SnowflakeUtil.getInstance().nextId()).setCreateTime(LocalDateTime.now()).setUpdateTime(LocalDateTime.now());
        orderMapper.insert(order);
        return JSONResult.success("操作成功");
    }
}
