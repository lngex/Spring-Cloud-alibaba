package cn.lingex.controller;

import cn.lingex.basic.pojo.dto.UserDto;
import cn.lingex.basic.pojo.query.BaseQuery;
import cn.lingex.basic.result.JSONResult;
import cn.lingex.basic.utils.PageList;
import cn.lingex.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 基础用户控制器
 *
 * @author LiaoJianbo
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController
@RequestMapping("/user")
@Api(tags = "基础用户")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping
    @ApiOperation("用户列表")
    public JSONResult<PageList<UserDto>> list(@RequestBody BaseQuery baseQuery, HttpServletRequest request) {
        System.out.println(HttpHeaders.AUTHORIZATION + ":" + request.getHeader(HttpHeaders.AUTHORIZATION));
        System.out.println(request.getClass());
        /* 505A0A5A728913E67A9B107DF1CD1456 */
        System.out.println("session：" + request.getSession().getId());
        System.out.println("token:" + request.getHeader("token"));
        return userService.pageList(baseQuery);
    }

    /**
     * 充值Vip
     *
     * @param id 用户id
     * @return 统一响应对象
     */
    @GetMapping("/vip/{id}")
    @ApiOperation("vip充值")
    @PreAuthorize("hasAnyAuthority('user:vip')")
    public JSONResult<String> vip(@PathVariable("id") Long id) {
        return userService.vip(id);
    }
}
