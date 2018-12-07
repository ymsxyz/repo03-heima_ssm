package cn.itcast.ssm.controller;

import cn.itcast.ssm.domain.Role;
import cn.itcast.ssm.domain.UserInfo;
import cn.itcast.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

@Component
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    //查询所有展示到user-list.jsp页面
    @RequestMapping("findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<UserInfo> userList = userService.findAll();
        mv.addObject("userList", userList);
        mv.setViewName("user-list");
        return mv;
    }

    //新建一个用户:成功后跳转到展示页面
    @RequestMapping("/save.do")
    public String save(UserInfo user) throws Exception {
        userService.save(user);
        return "redirect:findAll.do";
    }

    //查询详情
    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        UserInfo user = userService.findById(id);
        mv.addObject("user", user);
        mv.setViewName("user-show");
        return mv;
    }

    //查询用户可添加的角色,模型:List<role>,视图:user-role-add.jsp
    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Role> roleList = userService.findOtherRoles(id);
        UserInfo user = userService.findById(id);
        mv.addObject("user",user);
        mv.addObject("roleList", roleList);
        mv.setViewName("user-role-add");
        return mv;
    }

    //添加页面点击保存实现用户关联角色:获取用户id,角色ids,关联表insert
    @RequestMapping("addRoleToUser.do")
    public String  addRoleToUser(@RequestParam(value = "userId", required = true) String userId, @RequestParam(value = "ids", required = true) String[] ids
    ) throws Exception {
        //System.out.println(userId+""+ Arrays.toStrig(ids));
        userService.addRoleToUser(userId, ids);
        return "redirect:findAll.do";
    }
}
