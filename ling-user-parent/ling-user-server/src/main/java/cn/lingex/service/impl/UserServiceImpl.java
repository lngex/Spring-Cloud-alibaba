package cn.lingex.service.impl;

import cn.lingex.basic.exception.BusinessException;
import cn.lingex.basic.pojo.domain.User;
import cn.lingex.basic.pojo.dto.OrderDto;
import cn.lingex.basic.pojo.dto.UserDto;
import cn.lingex.basic.pojo.query.BaseQuery;
import cn.lingex.basic.result.JSONResult;
import cn.lingex.basic.utils.PageList;
import cn.lingex.basic.utils.SnUtils;
import cn.lingex.feign.IOrderFeign;
import cn.lingex.feign.IStockFeign;
import cn.lingex.mapper.UserMapper;
import cn.lingex.service.IUserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.seata.spring.annotation.GlobalTransactional;
import org.checkerframework.checker.units.qual.A;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 用户持久化层
 *
 * @author LiaoJianbo
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IOrderFeign orderFeign;

    @Autowired
    private IStockFeign stockFeign;

    @Autowired
    private RedissonClient redissonClient;

    /**
     * 充值Vip
     *
     * @param id 用户id
     * @return 统一响应对象
     */
    @Override
    @GlobalTransactional
    public JSONResult<String> vip(Long id) {
        User user = userMapper.selectById(id);
        if (user == null) {
            throw new BusinessException("用户不存在");
        }
        if (user.getVip() > 0) {
            throw new BusinessException("当前用户已经是Vip");
        }
        user.setVip(1).setUpdateTime(LocalDateTime.now());
        userMapper.updateById(user);
        OrderDto orderDto = new OrderDto().setMoney(new BigDecimal("0.1"))
                .setType(1)
                .setThirdSn(SnUtils.getRandomIdByUUID())
                .setSn(SnUtils.getRandomIdByUUID())
                .setUserId(id)
                .setPayType(1);
        // 添加订单信息
        JSONResult<String> save = orderFeign.save(orderDto);
        // 减少库存
        JSONResult<String> save1 = stockFeign.save(1L);
        if (!save.getStatus() || !save1.getStatus()) {
            throw new BusinessException(save.getData());
        }
        int i = 1 / 0;
        return JSONResult.getInstance("操作成功");
    }

    /**
     * 分页查询
     *
     * @param baseQuery 基础条件
     * @return 统一响应对象
     */
    @Override
    public JSONResult<PageList<UserDto>> pageList(BaseQuery baseQuery) {
        RLock lock = redissonClient.getLock("user:lock");
        System.out.println("是否获取到锁：" + lock.tryLock());
        Page<UserDto> userPage = new Page<>(baseQuery.getCurrent(), baseQuery.getSize());
        System.out.println("userPage===================>" + userPage.getRecords());
        userMapper.pageList(userPage, baseQuery);
        System.out.println("userPage===================>" + userPage.getRecords());
        PageList<UserDto> userPageList = new PageList<>();
        PageList<UserDto> userPageList1 = userPageList.setTotal(userPage.getTotal()).setContent(userPage.getRecords());
        return JSONResult.getInstance(userPageList1);
    }
}
