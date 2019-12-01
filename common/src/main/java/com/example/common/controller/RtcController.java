package com.example.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RtcController {

    @RequestMapping("/faceTalk")
    public String faceTalk(Model model, HttpServletRequest request){
        Integer userid = (Integer) request.getSession().getAttribute("userid");
        model.addAttribute("userid",userid);
        return "webrtc/index";
    }

    @RequestMapping("/hi")
    @ResponseBody
    public String hi(){
        return "hi";
    }


}
