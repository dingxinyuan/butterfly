package com.ayding.butterfly.auth.controller;

import com.ayding.butterfly.auth.domain.vo.AuthUserVO;
import com.ayding.butterfly.auth.service.OauthService;
import com.ayding.common.base.domain.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Created with IntelliJ IDEA.
 * @author: dingxy
 * @date: 2020/09/16
 * @Description:
 */
@RestController
@RequestMapping("/auth")
public class AuthUserController {

    @Autowired
    private OauthService oauthService;


    @PostMapping("/admin/v1/login")
    public Result adminLogin(@RequestBody AuthUserVO authUserVO) {
        return oauthService.login(authUserVO);
    }
}
