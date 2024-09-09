/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package io.dellevin.controller;


import cn.hutool.crypto.digest.DigestUtil;
import io.dellevin.annotation.Login;
import io.dellevin.common.utils.Result;
import io.dellevin.common.validator.ValidatorUtils;
import io.dellevin.dto.LoginDTO;
import io.dellevin.dto.RegisterDTO;
import io.dellevin.entity.UserEntity;
import io.dellevin.service.TokenService;
import io.dellevin.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Date;
import java.util.Map;

/**
 * 登录接口
 *
 */
@RestController
@RequestMapping("/api")
@Api(tags = "登录-注册接口")
@AllArgsConstructor
public class ApiLoginController {
    private final UserService userService;
    private final TokenService tokenService;


    @PostMapping("login")
    @ApiOperation("登录")
    public Result<Map<String, Object>> login(@RequestBody LoginDTO dto) {
        //表单校验
        ValidatorUtils.validateEntity(dto);

        //用户登录
        Map<String, Object> map = userService.login(dto);

        return new Result().ok(map);
    }

    @Login
    @PostMapping("logout")
    @ApiOperation("退出")
    public Result logout(@ApiIgnore @RequestAttribute("userId") Long userId) {
        tokenService.expireToken(userId);
        return new Result();
    }


    @PostMapping("register")
    @ApiOperation("注册")
    public Result register(@RequestBody RegisterDTO dto) {
        //表单校验
        ValidatorUtils.validateEntity(dto);

        UserEntity user = new UserEntity();
        user.setMobile(dto.getMobile());
        user.setUsername(dto.getMobile());
        user.setPassword(DigestUtil.sha256Hex(dto.getPassword()));
        user.setCreateDate(new Date());
        userService.insert(user);

        return new Result();
    }

}