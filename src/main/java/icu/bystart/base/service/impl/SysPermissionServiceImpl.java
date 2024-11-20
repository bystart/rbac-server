package icu.bystart.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import icu.bystart.base.entity.SysPermission;
import icu.bystart.base.entity.SysRolePermission;
import icu.bystart.base.mapper.SysPermissionMapper;
import icu.bystart.base.service.SysPermissionService;
import icu.bystart.base.service.SysRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 权限服务实现类
 */
@Service
public class SysPermissionServiceImpl extends BaseServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {

    @Autowired
    private SysRolePermissionService rolePermissionService;

    /**
     * 获取权限树形结构
     * @return 权限树
     */
    @Override
    public List<SysPermission> getPermissionTree() {
        // 获取所有权限
        List<SysPermission> allPermissions = this.list(new LambdaQueryWrapper<SysPermission>()
                .orderByAsc(SysPermission::getSort));
        
        // 构建树形结构
        Map<Long, List<SysPermission>> parentMap = allPermissions.stream()
                .collect(Collectors.groupingBy(SysPermission::getParentId));
        
        // 获取顶级菜单
        return buildTree(0L, parentMap);
    }

    /**
     * 获取角色的权限ID列表
     * @param roleId 角色ID
     * @return 权限ID列表
     */
    @Override
    public List<Long> getPermissionIdsByRoleId(Long roleId) {
        return rolePermissionService.list(new LambdaQueryWrapper<SysRolePermission>()
                .eq(SysRolePermission::getRoleId, roleId))
                .stream()
                .map(SysRolePermission::getPermissionId)
                .collect(Collectors.toList());
    }

    /**
     * 构建权限树
     * @param parentId 父级ID
     * @param parentMap 父级权限Map
     * @return 权限树列表
     */
    private List<SysPermission> buildTree(Long parentId, Map<Long, List<SysPermission>> parentMap) {
        List<SysPermission> children = parentMap.get(parentId);
        if (children == null) {
            return new ArrayList<>();
        }
        
        for (SysPermission permission : children) {
            List<SysPermission> childList = buildTree(permission.getId(), parentMap);
            permission.setChildren(childList);
        }
        
        return children;
    }
} 