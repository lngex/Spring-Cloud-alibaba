package cn.lingex.basic.security.provider;


import cn.lingex.basic.basePojo.LoginUserDto;
import cn.lingex.basic.config.RedisService;
import cn.lingex.basic.constant.BusinessConstant;
import cn.lingex.basic.security.token.UsernamePasswordCodeToken;
import cn.lingex.service.impl.UserDetalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


/**
 * pc端用户名、密码、验证码登录Provider
 *
 * @author LiaoJianbo
 * @version 1.0.0
 * @since 1.0.0
 */
@Component
public class UsernamePasswordCodeProvider implements AuthenticationProvider {

    @Autowired
    private UserDetalServiceImpl userDetalService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    /**
     * 用户登录具体处理逻辑
     * 该处理仅允许抛出AuthenticationException异常，否则无法被登录失败处理器捕获
     * @param authentication filter封装的token，
     * @return 登录成功返回一个token
     * @throws AuthenticationException 仅允许抛出AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordCodeToken usernamePasswordCodeToken = (UsernamePasswordCodeToken) authentication;
        String username = usernamePasswordCodeToken.getPrincipal();
        String password = usernamePasswordCodeToken.getCredentials();
        String code = usernamePasswordCodeToken.getCode();
        String clinetCode = usernamePasswordCodeToken.getClinetCode();
        if (!StringUtils.hasText(username)) {
            throw new UsernameNotFoundException("用户名不能为空");
        } else if (!StringUtils.hasText(password)) {
            throw new UsernameNotFoundException("密码不能为空");
        } else if (!StringUtils.hasText(code)) {
            throw new UsernameNotFoundException("验证码不能为空");
        } else if (!StringUtils.hasText(clinetCode)) {
            throw new UsernameNotFoundException("客户端编码不能为空");
        }
        // 校验验证码是否正确
        String o = redisService.get(BusinessConstant.REDIS_VERIFICATION_CODE + clinetCode);
        if (!StringUtils.hasText(o)) {
            throw new UsernameNotFoundException("验证码已过期,请重新获取");
        }
        if (!o.equalsIgnoreCase(code)) {
            throw new UsernameNotFoundException("验证码错误");
        }
        UserDetails userDetails = userDetalService.loadUserByUsername(username);
        boolean matches = passwordEncoder.matches(password, userDetails.getPassword());
        if (!matches) {
            throw new UsernameNotFoundException("密码错误");
        }
        usernamePasswordCodeToken.setAuthenticated(userDetails.getAuthorities());
        // TODO: 2022/5/30 获取用户信息，封装为loginUserDto，设置到 UsernamePasswordCodeToken 中
        LoginUserDto loginUserDto = new LoginUserDto();
        // BeanUtils.copyProperties(user, loginUserDto);
        UsernamePasswordCodeToken usernamePasswordCodeToken1 = new UsernamePasswordCodeToken(username, null, code, clinetCode, userDetails.getAuthorities());
        usernamePasswordCodeToken1.setDetails(loginUserDto);
        return usernamePasswordCodeToken1;
    }

    /**
     * 绑定需要处理的Token，用于Provider的校验
     *
     * @param authentication token超类
     * @return 校验结果，false不使用该Provider，true使用该Provider
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordCodeToken.class.isAssignableFrom(authentication);
    }
}
