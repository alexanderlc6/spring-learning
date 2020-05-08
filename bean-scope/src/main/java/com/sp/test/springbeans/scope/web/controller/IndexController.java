package com.sp.test.springbeans.scope.web.controller;

import com.sp.test.ioc.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Index MVC controller
 * Created by AlexLc on 2020/4/8.
 */
@Controller
public class IndexController {
    @Autowired
    private User user;  //CGLIB代理后的对象(不变的)

    @GetMapping("/index.html")
    public String index(Model model){
//        JSP EL变量搜索路径：page -> request -> session -> application(ServletContext)
        //userObject -> 在渲染上下文中
        //user对象在ServletContext中，上下文名称：scopedTarget.user == 新生成的Bean名称(拷贝的Bean)
        model.addAttribute("userObject", user);
        return "index";
    }
}
