package cn.itcast.ssm.controller;

import cn.itcast.ssm.domain.Permission;
import cn.itcast.ssm.domain.Role;
import cn.itcast.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    //查询所有角色,集合存到request域,并在role-list.jsp页面展示
    @RolesAllowed("ROLE_USER")//jsr250方式配置权限注解
    @RequestMapping("findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Role> roleList = roleService.findAll();
        mv.addObject("roleList", roleList);
        mv.setViewName("role-list");
        return mv;
    }

    //添加一个角色:成功后重定向到findAll.do
    @DenyAll//jsr250:拒绝所有访问
    @RequestMapping("save.do")
    public String save(Role role) throws Exception {
        roleService.save(role);
        return "redirect:findAll.do";
    }

    //查询角色可添加的权限
    @PermitAll//jsr250:允许所有访问
    @RequestMapping("findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Permission> permissionList = roleService.findOtherPermission(id);
        Role role = roleService.findById(id);
        mv.addObject("role", role);
        mv.addObject("permissionList", permissionList);
        mv.setViewName("role-permission-add");
        return mv;
    }

    //添加页面点击保存实现用户关联权限:获取角色id,权限ids,关联表insert
    @RequestMapping("addPermissionToRole.do")
    public String addPermissionToRole(@RequestParam(value = "roleId", required = true) String roleId, @RequestParam(value = "ids", required = true) String[] ids
    ) throws Exception {
        roleService.addPermissionToRole(roleId, ids);
        return "redirect:findAll.do";
    }
}
