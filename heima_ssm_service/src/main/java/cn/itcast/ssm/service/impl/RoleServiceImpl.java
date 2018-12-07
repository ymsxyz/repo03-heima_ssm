package cn.itcast.ssm.service.impl;

import cn.itcast.ssm.dao.IOrdersDao;
import cn.itcast.ssm.dao.IRoleDao;
import cn.itcast.ssm.domain.Permission;
import cn.itcast.ssm.domain.Role;
import cn.itcast.ssm.service.IRoleService;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;

    //查询所有角色
    @Override
    public List<Role> findAll() throws Exception {
        List<Role> roleList = roleDao.findAll();
        return roleList;
    }

    //添加一个角色
    public void save(Role role) throws Exception {
        roleDao.save(role);
    }

    //查询可添加权限集合
    @Override
    public List<Permission> findOtherPermission(String id) throws Exception {
        List<Permission> permissionList = roleDao.findOtherPermissions(id);
        return permissionList;
    }

    //角色和全权限集合添加关联
    @Override
    public void addPermissionToRole(String roleId, String[] ids) throws Exception {
        for (String permissionId : ids) {
            roleDao.addRoleToUser(roleId, permissionId);
        }
    }

    //通过id查询角色
    public Role findById(String id) throws Exception{
        Role role=roleDao.findById(id);
        return role;
    }

}
