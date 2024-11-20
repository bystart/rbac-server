package icu.bystart.base.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import icu.bystart.base.common.Result;
import icu.bystart.base.entity.SysUser;
import icu.bystart.base.service.SysUserRoleService;
import icu.bystart.base.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户管理控制器
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    private SysUserService userService;

    @Autowired
    private SysUserRoleService userRoleService;

    /**
     * 分页查询用户列表
     * @param current 当前页
     * @param size 每页大小
     * @param username 用户名（模糊查询）
     * @return 分页结果
     */
    @ApiOperation("分页查询用户列表")
    @GetMapping("/page")
    public Result<Page<SysUser>> page(
            @ApiParam("当前页") @RequestParam(defaultValue = "1") Integer current,
            @ApiParam("每页大小") @RequestParam(defaultValue = "10") Integer size,
            @ApiParam("用户名") @RequestParam(required = false) String username) {
        Page<SysUser> page = userService.page(new Page<>(current, size),
                new LambdaQueryWrapper<SysUser>()
                        .like(username != null, SysUser::getUsername, username)
                        .orderByDesc(SysUser::getCreateTime));
        return Result.success(page);
    }

    /**
     * 新增用户
     * @param user 用户信息
     */
    @ApiOperation("新增用户")
    @PostMapping
    public Result<?> save(@RequestBody SysUser user) {
        userService.save(user);
        return Result.success();
    }

    /**
     * 修改用户
     * @param user 用户信息
     */
    @ApiOperation("修改用户")
    @PutMapping
    public Result<?> update(@RequestBody SysUser user) {
        userService.updateById(user);
        return Result.success();
    }

    /**
     * 删除用户
     * @param id 用户ID
     */
    @ApiOperation("删除用户")
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        userService.removeById(id);
        return Result.success();
    }

    /**
     * 给用户分配角色
     * @param userId 用户ID
     * @param roleIds 角色ID列表
     */
    @ApiOperation("给用户分配角色")
    @PostMapping("/{userId}/roles")
    public Result<?> assignRoles(
            @ApiParam("用户ID") @PathVariable Long userId,
            @ApiParam("角色ID列表") @RequestBody List<Long> roleIds) {
        userRoleService.assignRoles(userId, roleIds);
        return Result.success();
    }
} 