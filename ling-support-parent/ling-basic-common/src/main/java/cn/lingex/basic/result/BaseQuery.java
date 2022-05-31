package cn.lingex.basic.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import cn.lingex.basic.result.BaseClientCode;

/**
 * 分页查询基础
 *
 * @author LiaoJianbo
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BaseQuery extends BaseClientCode {

    /**
     * 页码
     */
    @ApiModelProperty(value = "页码", example = "1")
    private long pageNo;

    /**
     * 每页展示数量
     */
    @ApiModelProperty(value = "每页展示数量", example = "10")
    private long pageSize;
}
