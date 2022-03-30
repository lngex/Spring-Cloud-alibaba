package cn.lingex.basic.pojo.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 用户类
 *
 * @author LiaoJianbo
 * @version 1.0.0
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@TableName("bse_user")
@Data
@Accessors(chain = true)
public class User extends BaseDomain implements Serializable {
    /**
     * 名字
     */
    @TableField("name")
    private String name;
    /**
     * 年龄
     */
    @TableField("age")
    private Integer age;
    /**
     * 职位
     */
    @TableField("job")
    private String job;
    /**
     * 0否，1一级vip
     */
    @TableField("vip")
    private Integer vip;
}
