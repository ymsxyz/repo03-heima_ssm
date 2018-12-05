package cn.itcast.ssm.service;

import cn.itcast.ssm.domain.Role;

import java.util.List;

public interface IRoleService {

    //查询所有
    List<Role> findAll() throws Exception;

    //添加一个角色
    void save(Role role) throws Exception;
}
