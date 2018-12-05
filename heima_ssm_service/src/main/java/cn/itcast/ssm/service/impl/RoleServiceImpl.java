package cn.itcast.ssm.service.impl;

import cn.itcast.ssm.dao.IOrdersDao;
import cn.itcast.ssm.dao.IRoleDao;
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
}
