package icu.bystart.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色权限关联实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_role_permission")
@ApiModel(description = "角色权限关联")
public class SysRolePermission extends BaseEntity {
    
    /**
     * 角色ID
     */
    @ApiModelProperty("角色ID")
    private Long roleId;
    
    /**
     * 权限ID
     */
    @ApiModelProperty("权限ID")
    private Long permissionId;
} 