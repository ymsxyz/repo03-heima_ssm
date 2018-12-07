package cn.itcast.ssm.controller;

import cn.itcast.ssm.domain.Product;
import cn.itcast.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @Secured("{ROLE_CEO,ROLE_ADMIN,ROLE_COO}")//开启权限控制,ROLE_前缀不能省略
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Product> products = productService.findAll();
        mv.addObject("products", products);
        mv.setViewName("product-list");
        return mv;
    }

    //添加产品并跳转到添加后的产品页面
    @PreAuthorize("authentication.principal.username =='tom'")//只有tom有权限
    @RequestMapping("/save.do")
    public String save(Product product) throws Exception {
        productService.save(product);
        return "redirect:findAll.do";
    }
}
