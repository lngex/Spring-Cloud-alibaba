package cn.lingex.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 用户登录信息获取
 *
 * @author LiaoJianbo
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
public class UserDetalServiceImpl implements UserDetailsService {



    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        // TODO: 2022/5/23 查询用户信息以及权限，封装成User
        return null;
    }
}