package cn.lingex.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author LiaoJianbo
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping
    @PreAuthorize("hasAnyAuthority('test:test','vip3')")
    public String test(){
        return "操作成功";
    }
}
