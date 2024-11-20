# RBAC权限管理系统

基于Spring Boot + Sa-Token + MyBatis-Plus实现的RBAC权限管理系统，提供完整的用户权限管理功能。

## 项目介绍

本项目是一个基于RBAC（Role-Based Access Control）模型的权限管理系统，采用Spring Boot + Sa-Token + MyBatis-Plus等主流技术栈开发。系统提供用户管理、角色管理、权限管理等功能，可以灵活地控制用户的访问权限。

## 前端仓库
[rbac-admin: https://github.com/bystart/rbac-admin](https://github.com/bystart/rbac-admin)

## 技术栈

- Spring Boot 2.7.x
- Sa-Token 1.34.0
- MyBatis-Plus 3.5.x
- MySQL 8.0
- Redis
- Knife4j 3.0.x
- Lombok
- Maven

## 主要功能

- [√] 用户认证
  - 登录
  - 注册
  - 登出
  - 获取用户信息
- [√] 用户管理
  - 用户列表
  - 新增用户
  - 修改用户
  - 删除用户
- [√] 角色管理
  - 角色列表
  - 新增角色
  - 修改角色
  - 删除角色
- [√] 权限管理
  - 权限列表
  - 新增权限
  - 修改权限
  - 删除权限
- [√] 权限分配
  - 给用户分配角色
  - 给角色分配权限

## 快速开始

### 环境要求

- JDK 1.8+
- Maven 3.6+
- MySQL 8.0+
- Redis
