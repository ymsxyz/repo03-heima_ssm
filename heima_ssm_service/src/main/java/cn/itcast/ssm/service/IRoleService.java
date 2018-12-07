package cn.itcast.ssm.service;

import cn.itcast.ssm.domain.Permission;
import cn.itcast.ssm.domain.Role;

import java.util.List;

public interface IRoleService {

    //查询所有
    List<Role> findAll() throws Exception;

    //添加一个角色
    void save(Role role) throws Exception;

    //查询可添加权限集合
    List<Permission> findOtherPermission(String id) throws Exception;

    //添加角色和权限集合关联
    void addPermissionToRole(String roleId,String [] ids) throws Exception;

    //通过id查询角色
    Role findById(String id) throws Exception;
}
