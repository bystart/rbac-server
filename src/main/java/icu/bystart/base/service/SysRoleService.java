package icu.bystart.base.service;

import icu.bystart.base.entity.SysRole;
import java.util.List;

/**
 * 角色服务接口
 */
public interface SysRoleService extends BaseService<SysRole> {
    
    /**
     * 给角色分配权限
     * @param roleId 角色ID
     * @param permissionIds 权限ID列表
     */
    void assignPermissions(Long roleId, List<Long> permissionIds);
} 