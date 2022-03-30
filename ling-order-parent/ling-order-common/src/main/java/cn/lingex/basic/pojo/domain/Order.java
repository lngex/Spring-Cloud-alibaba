package cn.lingex.basic.pojo.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * 订单Dto
 *
 * @author LiaoJianbo
 * @version 1.0.0
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@TableName("bse_order")
@Data
@Accessors(chain = true)
public class Order extends BaseDomain {
    /**
     * 订单号
     */
    @TableField("sn")
    private String sn;
    /**
     * 订单金额
     */
    @TableField("money")
    private BigDecimal money;
    /**
     * 订单类型
     */
    @TableField("type")
    private Integer type;
    /**
     * 1支付宝，2微信
     */
    @TableField("pay_type")
    private Integer payType;
    /**
     * 第三方订单号
     */
    @TableField("third_sn")
    private String thirdSn;
    /**
     * 操作用户
     */
    @TableField("user_id")
    private Long userId;
}
