package icu.bystart.base.controller;

import icu.bystart.base.common.Result;
import icu.bystart.base.dto.LoginDTO;
import icu.bystart.base.dto.UserInfoDTO;
import icu.bystart.base.entity.SysUser;
import icu.bystart.base.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 认证管理控制器
 */
@Api(tags = "认证管理")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    /**
     * 用户登录
     * @param loginDTO 登录参数
     * @return token令牌
     */
    @ApiOperation("用户登录")
    @PostMapping("/login")
    public Result<String> login(@Validated @RequestBody LoginDTO loginDTO) {
        String token = authService.login(loginDTO.getUsername(), loginDTO.getPassword());
        return Result.success(token);
    }

    /**
     * 用户注册
     * @param user 用户信息
     */
    @ApiOperation("用户注册")
    @PostMapping("/register")
    public Result<?> register(@Validated @RequestBody SysUser user) {
        authService.register(user);
        return Result.success();
    }

    /**
     * 用户登出
     */
    @ApiOperation("用户登出")
    @PostMapping("/logout")
    public Result<?> logout() {
        authService.logout();
        return Result.success();
    }

    /**
     * 获取当前登录用户信息
     * @return 用户信息和角色信息
     */
    @ApiOperation(value = "获取当前用户信息", notes = "获取当前登录用户的基本信息和角色列表，用于前端页面展示和权限控制")
    @GetMapping("/info")
    public Result<UserInfoDTO> getUserInfo() {
        SysUser user = authService.getCurrentUser();
        List<String> roles = authService.getCurrentUserRoles();
        
        UserInfoDTO userInfo = UserInfoDTO.builder()
                .user(user)
                .roles(roles)
                .build();
        
        return Result.success(userInfo);
    }
} 