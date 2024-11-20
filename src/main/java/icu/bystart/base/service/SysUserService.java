package icu.bystart.base.service;

import icu.bystart.base.entity.SysUser;

import java.util.List;

/**
 * 用户服务接口
 */
public interface SysUserService extends BaseService<SysUser> {
    
    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     */
    void login(String username, String password);
    
    /**
     * 用户注册
     * @param user 用户信息
     */
    void register(SysUser user);
    
    /**
     * 获取用户权限列表
     * @param userId 用户ID
     * @return 权限编码列表
     */
    List<String> getPermissionList(Long userId);
    
    /**
     * 获取用户角色列表
     * @param userId 用户ID
     * @return 角色编码列表
     */
    List<String> getRoleList(Long userId);
} 