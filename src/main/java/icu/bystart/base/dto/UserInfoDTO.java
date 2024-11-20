package icu.bystart.base.dto;

import icu.bystart.base.entity.SysUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Builder;

import java.util.List;

/**
 * 用户信息数据传输对象
 */
@Data
@Builder
@ApiModel(description = "用户信息响应对象")
public class UserInfoDTO {
    
    @ApiModelProperty(value = "用户基本信息", required = true, position = 1)
    private SysUser user;
    
    @ApiModelProperty(value = "用户角色列表", required = true, example = "[\"admin\", \"user\"]", position = 2)
    private List<String> roles;
} 