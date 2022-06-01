package cn.lingex.basic.security.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

import java.util.Collection;

/**
 * 用户基础认证授权Token
 *
 * @author LiaoJianbo
 * @version 1.0.0
 * @since 1.0.0
 */
public class BaseAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    /**
     * 一般为用户名
     */
    private final String username;
    /**
     * 一般为密码
     */
    private String password;
    /**
     * 客户端编码
     */
    private String clinetCode;


    /**
     * This constructor can be safely used by any code that wishes to create a
     * <code>UsernamePasswordAuthenticationToken</code>, as the {@link #isAuthenticated()}
     * will return <code>false</code>.
     */
    public BaseAuthenticationToken(String principal, String credentials, String clinetCode) {
        super(null);
        this.username = principal;
        this.password = credentials;
        this.clinetCode = clinetCode;
        setAuthenticated(false);
    }

    /**
     * This constructor should only be used by <code>AuthenticationManager</code> or
     * <code>AuthenticationProvider</code> implementations that are satisfied with
     * producing a trusted (i.e. {@link #isAuthenticated()} = <code>true</code>)
     * authentication token.
     *
     * @param principal
     * @param credentials
     * @param authorities
     */
    public BaseAuthenticationToken(String principal,
                                     String credentials,
                                     String clinetCode,
                                     Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.username = principal;
        this.password = credentials;
        this.clinetCode = clinetCode;
        super.setAuthenticated(true); // must use super, as we override
    }

    public BaseAuthenticationToken setAuthenticated(Collection<? extends GrantedAuthority>authorities){
        super.setAuthenticated(true);
        return this;
    }


    @Override
    public String getCredentials() {
        return this.password;
    }

    @Override
    public String getPrincipal() {
        return this.username;
    }

    public String getClinetCode() {
        return clinetCode;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }

        super.setAuthenticated(false);
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        password = null;
    }
}