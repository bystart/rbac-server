package icu.bystart.base.service.impl;

import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import icu.bystart.base.entity.SysUser;
import icu.bystart.base.mapper.SysUserMapper;
import icu.bystart.base.service.SysUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户服务实现类
 */
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     */
    @Override
    public void login(String username, String password) {
        SysUser user = this.getOne(new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getUsername, username));
        
        if (user == null || !BCrypt.checkpw(password, user.getPassword())) {
            throw new RuntimeException("用户名或密码错误");
        }
        
        if (user.getStatus() == 0) {
            throw new RuntimeException("账号已被禁用");
        }
        
        StpUtil.login(user.getId());
    }

    /**
     * 用户注册
     * @param user 用户信息
     */
    @Override
    public void register(SysUser user) {
        // 检查用户名是否已存在
        long count = this.count(new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getUsername, user.getUsername()));
        if (count > 0) {
            throw new RuntimeException("用户名已存在");
        }
        
        // 密码加密
        user.setPassword(BCrypt.hashpw(user.getPassword()));
        user.setStatus(1);
        this.save(user);
    }

    /**
     * 获取用户权限列表
     * @param userId 用户ID
     * @return 权限编码列表
     */
    @Override
    public List<String> getPermissionList(Long userId) {
        return baseMapper.getPermissionList(userId);
    }

    /**
     * 获取用户角色列表
     * @param userId 用户ID
     * @return 角色编码列表
     */
    @Override
    public List<String> getRoleList(Long userId) {
        return baseMapper.getRoleList(userId);
    }
} 