package cn.itcast.ssm.dao;

import cn.itcast.ssm.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IPermissionDao {

    //查询权限:通过role的id
//查询Role角色集合通过user的id
    @Select("select * from permission where id in(select permissionId from ROLE_PERMISSION where roleId=#{id})")
    List<Permission> findByRoleId(String RoleId) throws Exception;

    //查询所有角色
    @Select("select * from permission")
    List<Permission> findAll() throws Exception;

    //添加一个角色
    @Insert("insert into permission(permissionName,url) values (#{permissionName},#{url}) ")
    void save(Permission permission) throws Exception;
}
