package icu.bystart.base.mapper;

import icu.bystart.base.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户Mapper接口
 */
@Mapper
public interface SysUserMapper extends CommonMapper<SysUser> {
    
    /**
     * 获取用户权限列表
     * @param userId 用户ID
     * @return 权限编码列表
     */
    List<String> getPermissionList(@Param("userId") Long userId);
    
    /**
     * 获取用户角色列表
     * @param userId 用户ID
     * @return 角色编码列表
     */
    List<String> getRoleList(@Param("userId") Long userId);
} 