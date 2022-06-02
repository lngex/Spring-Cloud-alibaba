package cn.lingex.basic.pojo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户Dto
 *
 * @author LiaoJianbo
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
@Accessors(chain = true)
public class UserDto {
    /**
     * 用户id
     */
    private Long id;
    /**
     * 名字
     */
    private String username;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 职位
     */
    private String job;
    /**
     * 0否，1一级vip
     */
    private Integer vip;
}
