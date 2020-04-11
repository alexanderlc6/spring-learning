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
    private User user;

    @GetMapping("/index.html")
    public String index(Model model){
        model.addAttribute("user", user);
        return "index";
    }
}
