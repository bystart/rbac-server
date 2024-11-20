package icu.bystart.base.config;

import cn.dev33.satoken.stp.StpInterface;
import icu.bystart.base.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Sa-Token权限认证接口实现
 */
@Component
public class StpInterfaceImpl implements StpInterface {
    
    @Autowired
    private SysUserService userService;

    /**
     * 获取权限列表
     * @param loginId 登录ID
     * @param loginType 登录类型
     * @return 权限编码列表
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return userService.getPermissionList(Long.valueOf(loginId.toString()));
    }

    /**
     * 获取角色列表
     * @param loginId 登录ID
     * @param loginType 登录类型
     * @return 角色编码列表
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        return userService.getRoleList(Long.valueOf(loginId.toString()));
    }
} 