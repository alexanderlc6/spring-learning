package com.sp.test.spring.bean.scope.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Index MVC controller
 * Created by AlexLc on 2020/4/8.
 */
@Controller
public class IndexController {
    @GetMapping("/index.html")
    public String index(){
        return "index";
    }
}
