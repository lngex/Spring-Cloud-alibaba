package cn.lingex.basic.basePojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 登录用户Dto
 *
 * @author LiaoJianbo
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
@Accessors(chain = true)
@ApiModel("登录用户Dto对象")
public class LoginUserDto implements Serializable {
    private static final long serialVersionUID = -7738504317844361207L;
    /**
     * 主键id
     */
    @ApiModelProperty("用户id")
    private Long id;
    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String username;
    /**
     * 头像
     */
    @ApiModelProperty("头像")
    private String header;
    /**
     * 性别，0女性，1男性
     */
    @ApiModelProperty("0女性，1男性，2保密")
    private Integer sex;
    /**
     * 地区
     */
    @ApiModelProperty("地区")
    private String city;
    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    private String phone;
    /**
     * 用户授权凭证
     */
    @ApiModelProperty("用户授权凭证")
    private String token;
}
