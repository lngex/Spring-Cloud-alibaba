package cn.lingex.basic.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 客户端代码
 *
 * @author LiaoJianbo
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode
@Accessors(chain = true)
public class BaseClientCode {
    /**
     * 客户端代码
     */
    @ApiModelProperty(value = "客户端代码",hidden = true)
    private String clientCode;
}
