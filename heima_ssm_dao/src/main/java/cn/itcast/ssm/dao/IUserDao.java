package cn.itcast.ssm.dao;

import cn.itcast.ssm.domain.Role;
import cn.itcast.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IUserDao {

    //查询用户:通过username
    @Select("select * from users where username=#{username}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "email", column = "email"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id", javaType = java.util.List.class,
                    many = @Many(select = "cn.itcast.ssm.dao.IRoleDao.findByUserId"))
    })
    UserInfo findByUsername(String username);

    //查询所有用户
    @Select("select * from users")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "email", column = "email"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status")
    })
    List<UserInfo> findAll();

    //添加一个用户
    @Insert("insert into users(email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo user) throws Exception;

    //查询用户详情:通过id,字段中只有id唯一
    @Select("select * from users where id=#{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "email", column = "email"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id", javaType = java.util.List.class,
                    many = @Many(select = "cn.itcast.ssm.dao.IRoleDao.findByUserId"))
    })
    UserInfo findById(String id) throws Exception;

    //查询用户可添加角色
    @Select("select * from role where id not in(select roleid from users_role where userid=#{userId})")
    List<Role> findOtherRoles(String userId) throws Exception;

    //添加用户角色关联
    @Insert("insert into users_role (userid,roleid) values (#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") String userId,@Param("roleId") String roleId) throws Exception;
}
