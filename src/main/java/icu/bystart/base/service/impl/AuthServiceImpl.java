package icu.bystart.base.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import icu.bystart.base.entity.SysUser;
import icu.bystart.base.service.AuthService;
import icu.bystart.base.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 认证服务实现类
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private SysUserService userService;

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return token令牌
     */
    @Override
    public String login(String username, String password) {
        userService.login(username, password);
        return StpUtil.getTokenValue();
    }

    /**
     * 用户注册
     * @param user 用户信息
     */
    @Override
    public void register(SysUser user) {
        userService.register(user);
    }

    /**
     * 用户登出
     */
    @Override
    public void logout() {
        StpUtil.logout();
    }

    /**
     * 获取当前登录用户信息
     * @return 用户信息
     */
    @Override
    public SysUser getCurrentUser() {
        Long userId = StpUtil.getLoginIdAsLong();
        return userService.getById(userId);
    }

    /**
     * 获取当前登录用户角色列表
     * @return 角色编码列表
     */
    @Override
    public List<String> getCurrentUserRoles() {
        Long userId = StpUtil.getLoginIdAsLong();
        return userService.getRoleList(userId);
    }
} 