package cn.itcast.ssm.service.impl;

import cn.itcast.ssm.dao.IPermissionDao;
import cn.itcast.ssm.domain.Permission;
import cn.itcast.ssm.domain.Role;
import cn.itcast.ssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private IPermissionDao permissionDao;

    //查询所有资源权限
    @Override
    public List<Permission> findAll() throws Exception {
        List<Permission> permissionList = permissionDao.findAll();
        return permissionList;
    }

    //添加一个资源权限
    public void save(Permission permission) throws Exception {
        permissionDao.save(permission);
    }
}
