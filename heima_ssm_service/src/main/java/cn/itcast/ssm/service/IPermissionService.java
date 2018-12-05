package cn.itcast.ssm.service;

import cn.itcast.ssm.domain.Permission;
import cn.itcast.ssm.domain.Role;

import java.util.List;

public interface IPermissionService {

    //查询所有
    List<Permission> findAll() throws Exception;

    //添加一个角色
    void save(Permission role) throws Exception;
}
