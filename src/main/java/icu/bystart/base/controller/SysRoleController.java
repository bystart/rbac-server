package icu.bystart.base.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import icu.bystart.base.common.Result;
import icu.bystart.base.entity.SysRole;
import icu.bystart.base.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色管理控制器
 */
@Api(tags = "角色管理")
@RestController
@RequestMapping("/role")
public class SysRoleController {

    @Autowired
    private SysRoleService roleService;

    /**
     * 分页查询角色列表
     * @param current 当前页
     * @param size 每页大小
     * @param roleName 角色名称（模糊查询）
     * @return 分页结果
     */
    @ApiOperation("分页查询角色列表")
    @GetMapping("/page")
    public Result<Page<SysRole>> page(
            @ApiParam("当前页") @RequestParam(defaultValue = "1") Integer current,
            @ApiParam("每页大小") @RequestParam(defaultValue = "10") Integer size,
            @ApiParam("角色名称") @RequestParam(required = false) String roleName) {
        Page<SysRole> page = roleService.page(new Page<>(current, size),
                new LambdaQueryWrapper<SysRole>()
                        .like(roleName != null, SysRole::getRoleName, roleName)
                        .orderByDesc(SysRole::getCreateTime));
        return Result.success(page);
    }

    /**
     * 新增角色
     * @param role 角色信息
     */
    @ApiOperation("新增角色")
    @PostMapping
    public Result<?> save(@RequestBody SysRole role) {
        roleService.save(role);
        return Result.success();
    }

    /**
     * 修改角色
     * @param role 角色信息
     */
    @ApiOperation("修改角色")
    @PutMapping
    public Result<?> update(@RequestBody SysRole role) {
        roleService.updateById(role);
        return Result.success();
    }

    /**
     * 删除角色
     * @param id 角色ID
     */
    @ApiOperation("删除角色")
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        roleService.removeById(id);
        return Result.success();
    }

    /**
     * 给角色分配权限
     * @param roleId 角色ID
     * @param permissionIds 权限ID列表
     */
    @ApiOperation("给角色分配权限")
    @PostMapping("/{roleId}/permissions")
    public Result<?> assignPermissions(
            @ApiParam("角色ID") @PathVariable Long roleId,
            @ApiParam("权限ID列表") @RequestBody List<Long> permissionIds) {
        roleService.assignPermissions(roleId, permissionIds);
        return Result.success();
    }
} 