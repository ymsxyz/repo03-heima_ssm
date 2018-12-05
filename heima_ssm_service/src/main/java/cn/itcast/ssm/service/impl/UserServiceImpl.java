package cn.itcast.ssm.service.impl;

import cn.itcast.ssm.dao.IUserDao;
import cn.itcast.ssm.domain.Role;
import cn.itcast.ssm.domain.UserInfo;
import cn.itcast.ssm.service.IUserService;
import cn.itcast.ssm.utils.BCryptPasswordEncoderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    /*
     *   1.获取用户的角色集合
     *   2.获取List<SimpleGrantedAuthority>角色(权限)集合
     *   3.获取UserDetails实现类User对象(构造:用户名,密码,是否有效,x,x,x,List<SimpleGrantedAuthority>)并返回
     * */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserInfo userInfo = userDao.findByUsername(username);
        System.out.println(userInfo);
        List<Role> roleList = userInfo.getRoles();

        List<SimpleGrantedAuthority> authorityList = getAuthority(roleList);
        //User user = new User(userInfo.getUsername(), "{noop}" + userInfo.getPassword(), userInfo.getStatus() == 0 ? false : true, true, true, true, authorityList);
        //加密处理
        User user = new User(userInfo.getUsername(), userInfo.getPassword(), userInfo.getStatus() == 0 ? false : true, true, true, true, authorityList);
        return user;
    }

    //有Role集合返回SimpleGrantedAuthority集合
    private List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {
        List<SimpleGrantedAuthority> authoritys = new ArrayList();
        for (Role role : roles) {
            authoritys.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        }
        return authoritys;
    }

    //查询所有用户
    public List<UserInfo> findAll() {
        List<UserInfo> userList = userDao.findAll();
        return userList;
    }

    //添加一个用户
    public void save(UserInfo user) throws Exception {
        //加密处理
        user.setPassword(BCryptPasswordEncoderUtils.encode(user.getPassword()));
        userDao.save(user);
    }

    //查询详情
    @Override
    public UserInfo findById(String userId) throws Exception {
        UserInfo user = userDao.findById(userId);
        return user;
    }
}
