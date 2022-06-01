package cn.lingex.basic.basePojo;

import cn.lingex.basic.security.authority.BusinessAuthority;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * Redis值
 * 用户权限对象
 * @author LiaoJianbo
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
@Accessors(chain = true)
public class AuthenticationRedisValue implements Serializable {
    private static final long serialVersionUID = -1918473971943983588L;
    /**
     * 用户权限集合
     */
    private List<BusinessAuthority> authorities;
    /**
     * 用户登录信息
     */
    private LoginUserDto loginUserDto;
}
