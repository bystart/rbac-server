package icu.bystart.base.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 系统权限实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_permission")
@ApiModel(description = "系统权限")
public class SysPermission extends BaseEntity {
    
    /**
     * 父级ID
     */
    @ApiModelProperty("父级ID")
    private Long parentId;
    
    /**
     * 权限名称
     */
    @NotBlank(message = "权限名称不能为空")
    @ApiModelProperty(value = "权限名称", required = true)
    private String name;
    
    /**
     * 权限编码
     */
    @NotBlank(message = "权限编码不能为空")
    @ApiModelProperty(value = "权限编码", required = true)
    private String permissionCode;
    
    /**
     * 路由地址
     */
    @ApiModelProperty("路由地址")
    private String path;
    
    /**
     * 类型（1：菜单，2：按钮）
     */
    @NotNull(message = "权限类型不能为空")
    @ApiModelProperty(value = "类型（1：菜单，2：按钮）", required = true)
    private Integer type;
    
    /**
     * 状态（0：禁用，1：正常）
     */
    @ApiModelProperty("状态（0：禁用，1：正常）")
    private Integer status;
    
    /**
     * 排序
     */
    @ApiModelProperty("排序")
    private Integer sort;
    
    /**
     * 子权限列表
     */
    @TableField(exist = false)
    @ApiModelProperty("子权限列表")
    private List<SysPermission> children;
} 