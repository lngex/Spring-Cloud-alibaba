package cn.lingex.service.impl;

import cn.lingex.basic.security.authority.BusinessAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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
        String password = "$2a$10$uhJKnU2/s9H1II9KkeB7WesBZuSgF8QWj3HbMMXXkzEkN4N5DBdwe";
        BusinessAuthority businessAuthority = new BusinessAuthority("Role_test:test");
        ArrayList<BusinessAuthority> list = new ArrayList<>();
        list.add(businessAuthority);
        return new User("user",password,list);
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode("123456");
        System.out.println(encode);
    }
}