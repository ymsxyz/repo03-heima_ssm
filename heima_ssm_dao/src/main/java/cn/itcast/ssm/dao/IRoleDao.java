package cn.itcast.ssm.dao;

import cn.itcast.ssm.domain.Role;
import cn.itcast.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IRoleDao {

    //查询Role角色集合通过user的id
    @Select("select * from role where id in(select roleId from users_role where userId=#{id})")
    @Results(id = "resultMap", value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions", column = "id", javaType = java.util.List.class,
                    many = @Many(select = "cn.itcast.ssm.dao.IPermissionDao.findByRoleId"))
    })
    List<Role> findByUserId(String userId) throws Exception;

    //查询所有角色
    @Select("select * from role")
    @ResultMap("resultMap")//引用
    List<Role> findAll() throws Exception;

    //添加一个角色
    @Insert("insert into role(roleName,roleDesc) values (#{roleName},#{roleDesc}) ")
    void save(Role role) throws Exception;
}

