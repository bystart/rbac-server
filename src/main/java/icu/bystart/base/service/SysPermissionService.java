package icu.bystart.base.service;

import icu.bystart.base.entity.SysPermission;
import java.util.List;

/**
 * 权限服务接口
 */
public interface SysPermissionService extends BaseService<SysPermission> {
    
    /**
     * 获取权限树形结构
     * @return 权限树
     */
    List<SysPermission> getPermissionTree();
    
    /**
     * 获取角色的权限ID列表
     * @param roleId 角色ID
     * @return 权限ID列表
     */
    List<Long> getPermissionIdsByRoleId(Long roleId);
} 