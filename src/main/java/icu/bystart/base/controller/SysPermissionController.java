package icu.bystart.base.controller;

import icu.bystart.base.common.Result;
import icu.bystart.base.entity.SysPermission;
import icu.bystart.base.service.SysPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 权限管理控制器
 */
@Api(tags = "权限管理")
@RestController
@RequestMapping("/permission")
public class SysPermissionController {

    @Autowired
    private SysPermissionService permissionService;

    /**
     * 获取权限树形结构
     * @return 权限树
     */
    @ApiOperation("获取权限树")
    @GetMapping("/tree")
    public Result<List<SysPermission>> getPermissionTree() {
        return Result.success(permissionService.getPermissionTree());
    }

    /**
     * 获取角色的权限ID列表
     * @param roleId 角色ID
     * @return 权限ID列表
     */
    @ApiOperation("获取角色的权限ID列表")
    @GetMapping("/role/{roleId}")
    public Result<List<Long>> getPermissionIdsByRoleId(@ApiParam("角色ID") @PathVariable Long roleId) {
        return Result.success(permissionService.getPermissionIdsByRoleId(roleId));
    }

    /**
     * 新增权限
     * @param permission 权限信息
     */
    @ApiOperation("新增权限")
    @PostMapping
    public Result<?> save(@RequestBody SysPermission permission) {
        permissionService.save(permission);
        return Result.success();
    }

    /**
     * 修改权限
     * @param permission 权限信息
     */
    @ApiOperation("修改权限")
    @PutMapping
    public Result<?> update(@RequestBody SysPermission permission) {
        permissionService.updateById(permission);
        return Result.success();
    }

    /**
     * 删除权限
     * @param id 权限ID
     */
    @ApiOperation("删除权限")
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        permissionService.removeById(id);
        return Result.success();
    }
} 