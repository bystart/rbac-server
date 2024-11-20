CREATE DATABASE IF NOT EXISTS rbac_demo DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

USE rbac_demo;

-- 用户表
CREATE TABLE `sys_user` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `username` varchar(50) NOT NULL COMMENT '用户名',
    `password` varchar(100) NOT NULL COMMENT '密码',
    `nickname` varchar(50) DEFAULT NULL COMMENT '昵称',
    `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
    `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
    `status` tinyint(4) DEFAULT 1 COMMENT '状态 0:禁用 1:正常',
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- 角色表
CREATE TABLE `sys_role` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `role_name` varchar(50) NOT NULL COMMENT '角色名称',
    `role_code` varchar(50) NOT NULL COMMENT '角色编码',
    `description` varchar(200) DEFAULT NULL COMMENT '角色描述',
    `status` tinyint(4) DEFAULT 1 COMMENT '状态 0:禁用 1:正常',
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_role_code` (`role_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- 权限表
CREATE TABLE `sys_permission` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `parent_id` bigint(20) DEFAULT 0 COMMENT '父级ID',
    `name` varchar(50) NOT NULL COMMENT '权限名称',
    `permission_code` varchar(50) NOT NULL COMMENT '权限编码',
    `path` varchar(200) DEFAULT NULL COMMENT '路由地址',
    `type` tinyint(4) NOT NULL COMMENT '类型 1:菜单 2:按钮',
    `status` tinyint(4) DEFAULT 1 COMMENT '状态 0:禁用 1:正常',
    `sort` int(11) DEFAULT 0 COMMENT '排序',
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_permission_code` (`permission_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限表';

-- 用户角色关联表
CREATE TABLE `sys_user_role` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_id` bigint(20) NOT NULL COMMENT '用户ID',
    `role_id` bigint(20) NOT NULL COMMENT '角色ID',
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_user_role` (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色关联表';

-- 角色权限关联表
CREATE TABLE `sys_role_permission` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `role_id` bigint(20) NOT NULL COMMENT '角色ID',
    `permission_id` bigint(20) NOT NULL COMMENT '权限ID',
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_role_permission` (`role_id`,`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色权限关联表'; 


-- 插入超级管理员用户（密码为：admin123）
INSERT INTO `sys_user` 
(`username`, `password`, `nickname`, `email`, `phone`, `status`, `create_time`, `update_time`) 
VALUES 
('admin', '$2a$10$li7S1appMgegqZaABIaTWe8Qg8zgxtnCQv9Q.k8DO56dIiUTDThUS', '超级管理员', 'admin@example.com', '13800138000', 1, NOW(), NOW());

-- 插入角色
INSERT INTO `sys_role` 
(`role_name`, `role_code`, `description`, `status`, `create_time`, `update_time`) 
VALUES 
('超级管理员', 'ROLE_ADMIN', '系统超级管理员', 1, NOW(), NOW());

-- 插入权限
INSERT INTO `sys_permission` 
(`parent_id`, `name`, `permission_code`, `path`, `type`, `status`, `sort`, `create_time`, `update_time`) 
VALUES 
(0, '系统管理', 'system', '/system', 1, 1, 1, NOW(), NOW()),
(1, '用户管理', 'system:user', '/system/user', 1, 1, 1, NOW(), NOW()),
(1, '角色管理', 'system:role', '/system/role', 1, 1, 2, NOW(), NOW()),
(1, '权限管理', 'system:permission', '/system/permission', 1, 1, 3, NOW(), NOW());

-- 获取用户ID和角色ID
SET @userId = (SELECT id FROM sys_user WHERE username = 'admin');
SET @roleId = (SELECT id FROM sys_role WHERE role_code = 'ROLE_ADMIN');

-- 关联用户和角色
INSERT INTO `sys_user_role` 
(`user_id`, `role_id`, `create_time`) 
VALUES 
(@userId, @roleId, NOW());

-- 获取所有权限ID
SET @permissionIds = (SELECT GROUP_CONCAT(id) FROM sys_permission);

-- 关联角色和权限
INSERT INTO `sys_role_permission` 
(`role_id`, `permission_id`, `create_time`)
SELECT @roleId, id, NOW()
FROM sys_permission;