package icu.bystart.base.service;

import icu.bystart.base.entity.SysUserRole;
import java.util.List;

/**
 * 用户角色关联服务接口
 */
public interface SysUserRoleService extends BaseService<SysUserRole> {
    
    /**
     * 给用户分配角色
     * @param userId 用户ID
     * @param roleIds 角色ID列表
     */
    void assignRoles(Long userId, List<Long> roleIds);
} 