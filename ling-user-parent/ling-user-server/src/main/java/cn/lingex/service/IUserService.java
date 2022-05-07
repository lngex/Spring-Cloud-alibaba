package cn.lingex.service;

import cn.lingex.basic.pojo.domain.User;
import cn.lingex.basic.pojo.dto.UserDto;
import cn.lingex.basic.pojo.query.BaseQuery;
import cn.lingex.basic.result.JSONResult;
import cn.lingex.basic.utils.PageList;
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

    /**
     * 分页查询
     * @param baseQuery 基础条件
     * @return 统一响应对象
     */
    JSONResult<PageList<UserDto>> pageList(BaseQuery baseQuery);
}
