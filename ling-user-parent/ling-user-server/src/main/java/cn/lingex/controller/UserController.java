package cn.lingex.controller;

import cn.lingex.basic.pojo.domain.User;
import cn.lingex.basic.pojo.query.BaseQuery;
import cn.lingex.basic.result.JSONResult;
import cn.lingex.basic.utils.PageList;
import cn.lingex.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 基础用户控制器
 *
 * @author LiaoJianbo
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController
@RequestMapping("user")
@Api(tags = "基础用户")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping
    @ApiOperation("用户列表")
    public JSONResult<PageList<User>> list(@RequestBody BaseQuery baseQuery){
        return userService.pageList(baseQuery);
    }

    /**
     * 充值Vip
     * @param id 用户id
     * @return 统一响应对象
     */
    @GetMapping("/vip/{id}")
    @ApiOperation("vip充值")
    public JSONResult<String> vip(@PathVariable("id") Long id){
        return userService.vip(id);
    }
}
