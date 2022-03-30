package cn.lingex.basic.pojo.query;

//import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 基础查询
 *
 * @author LiaoJianbo
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
@Accessors(chain = true)
public class BaseQuery {
    /**
     * 当前页
     */
    @ApiModelProperty("当前页")
    private Integer current = 1;
    /**
     * 每页展示记录
     */
    @ApiModelProperty("每页展示记录")
    private Integer size = 10;
}
