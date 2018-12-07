package cn.itcast.ssm.service;

import cn.itcast.ssm.domain.Role;
import cn.itcast.ssm.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {

    //查询所有用户
    List<UserInfo> findAll() throws Exception;

    //添加一个用户
    void save(UserInfo user) throws Exception;

    //查询详情
    UserInfo findById(String userId) throws Exception;

    //查询可添加角色集合
    List<Role> findOtherRoles(String id) throws Exception;

    //添加用户和角色集合关联
    void addRoleToUser(String userId,String [] ids) throws Exception;
}
