package cn.lingex.mapper;

import cn.lingex.basic.pojo.domain.User;
import cn.lingex.basic.pojo.dto.UserDto;
import cn.lingex.basic.pojo.query.BaseQuery;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * 用户持久化层
 *
 * @author LiaoJianbo
 * @version 1.0.0
 * @since 1.0.0
 */
public interface UserMapper extends BaseMapper<User> {
    /**
     * 分页查询
     * @param userPage 分页对象
     * @param baseQuery 查询条件
     */
    IPage<UserDto> pageList(Page<UserDto> userPage, @Param("query") BaseQuery baseQuery);
}
