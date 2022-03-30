package cn.lingex.service;

import cn.lingex.basic.pojo.domain.Stock;
import cn.lingex.basic.result.JSONResult;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 库存业务层
 *
 * @author LiaoJianbo
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IStockService extends IService<Stock> {


    JSONResult<String> save(Long id);
}
