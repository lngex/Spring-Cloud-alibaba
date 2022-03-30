package cn.lingex.service;

import cn.lingex.basic.pojo.domain.User;
import cn.lingex.basic.result.JSONResult;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 用户持久化层
 *
 * @author LiaoJianbo
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IUserService extends IService<User> {
    /**
     * 充值Vip
     * @param id 用户id
     * @return 统一响应对象
     */
    JSONResult<String> vip(Long id);
}
