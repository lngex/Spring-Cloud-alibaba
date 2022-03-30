package cn.lingex.controller;

import cn.lingex.basic.result.JSONResult;
import cn.lingex.service.IStockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author LiaoJianbo
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController
@RequestMapping("/stock")
@Api(tags = "库存")
public class StockController {

    @Autowired
    private IStockService service;

    @GetMapping("/{id}")
    @ApiOperation("库存减少")
    public JSONResult<String> save(@PathVariable("id") Long id){
        return service.save(id);
    }
}
