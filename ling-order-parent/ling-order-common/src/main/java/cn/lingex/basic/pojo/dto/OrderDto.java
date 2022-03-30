package cn.lingex.basic.pojo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 订单dto
 *
 * @author LiaoJianbo
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OrderDto {
    /**
     * 订单号
     */
    @NotBlank(message = "请输入订单编号")
    private String sn;
    /**
     * 订单金额
     */
    @DecimalMin(value = "0.1",message = "最小订单金额不能小于1")
    private BigDecimal money;
    /**
     * 订单类型
     */
    @NotNull(message = "请设置订单类型")
    private Integer type;
    /**
     * 1支付宝，2微信
     */
    @NotNull(message = "请选择支付类型")
    private Integer payType;
    /**
     * 第三方订单号
     */
    @NotBlank(message = "第三方订单号不能为空")
    private String thirdSn;
    /**
     * 操作用户
     */
    @NotNull(message = "操作用户不能为Null")
    private Long userId;
}
