package cn.lingex.controller;

import cn.lingex.basic.pojo.dto.OrderDto;
import cn.lingex.basic.result.JSONResult;
import cn.lingex.service.IOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 订单控制器
 *
 * @author LiaoJianbo
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController
@RequestMapping("/order")
@Api(tags = "订单")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    /**
     * 保存订单
     *
     * @param orderDto 保存订单
     */
    @PutMapping()
    @ApiOperation("订单新增")
    public JSONResult<String> save(@RequestBody @Validated OrderDto orderDto) {
        return orderService.add(orderDto);
    }
}
