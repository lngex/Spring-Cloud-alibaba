package cn.lingex.basic.security.authority;

import org.springframework.security.core.GrantedAuthority;

import java.util.Objects;

/**
 * 自定义权限类，相当于SimpleGrantedAuthority
 * 解决SimpleGrantedAuthority无法放序列化情况
 *
 * @author LiaoJianbo
 * @version 1.0.0
 * @since 1.0.0
 */
public class BusinessAuthority implements GrantedAuthority {
    private static final long serialVersionUID = 6832938028563053077L;
    /**
     * 权限
     */
    private String role;
    /**
     * 获取权限
     */
    @Override
    public String getAuthority() {
        return this.role;
    }

    public BusinessAuthority() {
    }

    public BusinessAuthority(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public BusinessAuthority setRole(String role) {
        this.role = role;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BusinessAuthority)) {
            return false;
        }
        BusinessAuthority that = (BusinessAuthority) o;
        return Objects.equals(getRole(), that.getRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRole());
    }

    @Override
    public String toString() {
        return "BusinessAuthority{" +
                "role='" + role + '\'' +
                '}';
    }
}
