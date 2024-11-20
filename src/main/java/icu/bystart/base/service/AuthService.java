package icu.bystart.base.service;

import icu.bystart.base.entity.SysUser;

import java.util.List;

/**
 * 认证服务接口
 */
public interface AuthService {
    
    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return token令牌
     */
    String login(String username, String password);
    
    /**
     * 用户注册
     * @param user 用户信息
     */
    void register(SysUser user);
    
    /**
     * 用户登出
     */
    void logout();
    
    /**
     * 获取当前登录用户信息
     * @return 用户信息
     */
    SysUser getCurrentUser();
    
    /**
     * 获取当前用户角色列表
     * @return 角色编码列表
     */
    List<String> getCurrentUserRoles();
} 