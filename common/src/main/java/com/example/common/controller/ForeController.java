package com.example.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/fore")
public class ForeController {
    @RequestMapping("/")
    public String index(){
        return "front/index";
    }
}
