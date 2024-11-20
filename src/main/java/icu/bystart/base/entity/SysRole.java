package icu.bystart.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * 系统角色实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_role")
@ApiModel(description = "系统角色")
public class SysRole extends BaseEntity {
    
    /**
     * 角色名称
     */
    @NotBlank(message = "角色名称不能为空")
    @ApiModelProperty(value = "角色名称", required = true)
    private String roleName;
    
    /**
     * 角色编码
     */
    @NotBlank(message = "角色编码不能为空")
    @ApiModelProperty(value = "角色编码", required = true)
    private String roleCode;
    
    /**
     * 角色描述
     */
    @ApiModelProperty("角色描述")
    private String description;
    
    /**
     * 状态（0：禁用，1：正常）
     */
    @ApiModelProperty("状态（0：禁用，1：正常）")
    private Integer status;
} 