package cn.lingex.basic.pojo.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 库存实体
 *
 * @author LiaoJianbo
 * @version 1.0.0
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@TableName("bse_stock")
@Data
@Accessors(chain = true)
public class Stock extends BaseDomain {
    /**
     * 库存数量
     */
    private Integer number;
    /**
     * 商品名
     */
    private String name;
}
