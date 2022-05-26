package cn.lingex.service.impl;

import cn.lingex.basic.exception.BusinessException;
import cn.lingex.basic.pojo.domain.Stock;
import cn.lingex.basic.result.JSONResult;
import cn.lingex.mapper.StockMapper;
import cn.lingex.service.IStockService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 库存业务层实现
 *
 * @author LiaoJianbo
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
public class StockServiceImpl extends ServiceImpl<StockMapper, Stock> implements IStockService {

    @Autowired
    private StockMapper stockMapper;

    @Override
    @Transactional
    public JSONResult<String> save(Long id) {
        Stock stock = stockMapper.selectById(id);
        if (stock == null || stock.getNumber() <= 0) {
            throw new BusinessException("当前商品库存不足");
        }
        stockMapper.updateById(stock.setNumber(stock.getNumber() - 1));
        return JSONResult.getInstance("操作成功");
    }
}
