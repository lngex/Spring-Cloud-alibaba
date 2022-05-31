package cn.lingex.basic.security.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

import java.util.Collection;

/**
 * 后台管理系统，用户名，密码。验证码登录token
 *
 * @author LiaoJianbo
 * @version 1.0.0
 * @since 1.0.0
 */
public class UsernamePasswordCodeToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;


    private final String principal;
    private String credentials;
    private String code;
    private String clinetCode;


    /**
     * This constructor can be safely used by any code that wishes to create a
     * <code>UsernamePasswordAuthenticationToken</code>, as the {@link #isAuthenticated()}
     * will return <code>false</code>.
     */
    public UsernamePasswordCodeToken(String principal, String credentials, String code, String clinetCode) {
        super(null);
        this.principal = principal;
        this.credentials = credentials;
        this.code = code;
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
    public UsernamePasswordCodeToken(String principal,
                                     String credentials,
                                     String code, String clinetCode,
                                  Collection<? extends GrantedAuthority>authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        this.code = code;
        this.clinetCode = clinetCode;
        super.setAuthenticated(true); // must use super, as we override
    }

    public UsernamePasswordCodeToken setAuthenticated(Collection<? extends GrantedAuthority>authorities){
        super.setAuthenticated(true);
        return this;
    }


    @Override
    public String getCredentials() {
        return this.credentials;
    }

    @Override
    public String getPrincipal() {
        return this.principal;
    }

    public String getCode() {
        return code;
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
        credentials = null;
    }
}
