package com.example.webrtc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RtcController {

    @RequestMapping("/faceTalk")
    public String faceTalk(){
        return "index";
    }

    @RequestMapping("/hi")
    @ResponseBody
    public String hi(){
        return "hi";
    }
}
