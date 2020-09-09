package com.yjh.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

public class ThymeleafController {
    @RequestMapping("/test")
    public String test(Model model){
        model.addAttribute("msg","姓羊还是姓杨,旁敲侧击从中计算");
        return "hello";
    }
}
