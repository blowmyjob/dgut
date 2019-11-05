package com.example.common.controller;

import com.example.common.entity.Resume;
import com.example.common.entity.SystemLog;
import com.example.common.entity.User;
import com.example.common.enums.Sex;
import com.example.common.enums.identify;
import com.example.common.service.SystemService;
import com.example.common.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SystemService systemService;

    @GetMapping("/login")
    public String login1(){
        return "login";
    }

    @PostMapping("/login")
    public String login2(@RequestParam("userName") String username, @RequestParam("passWord") String password,HttpServletRequest request){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        SystemLog log  =new SystemLog();
        try {
            subject.login(token);
        } catch (UnknownAccountException uae) {
            return "未知账户";
        } catch (IncorrectCredentialsException ice) {
            return "密码不正确";
        } catch (LockedAccountException lae) {
            return "账户已锁定";
        } catch (ExcessiveAttemptsException eae) {
            return "用户名或密码错误次数过多";
        } catch (AuthenticationException ae) {
            return "用户名或密码不正确！";
        }
        if (subject.isAuthenticated()) {
            token.setRememberMe(true);
            subject.getSession().setAttribute("userName",token.getUsername());
            log.setContent("登陆成功");
            log.setLoginTime(new Timestamp(System.currentTimeMillis()));
            log.setUserName(token.getUsername());
            log.setIpAddress(request.getRemoteAddr());
            systemService.addLog(log);
            return "redirect:/index";
        } else {
            log.setContent("登陆失败");
            log.setLoginTime(new Timestamp(System.currentTimeMillis()));
            log.setUserName(token.getUsername());
            log.setIpAddress(request.getRemoteAddr());
            systemService.addLog(log);
            token.clear();
            return "登录失败";
        }
    }

    @PostMapping("/logout")
    public String logout( ){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }

    @GetMapping("/updatepasswd")
    public String updatePassWd1(HttpServletRequest request,Model model){
        return "user/user-password-edit";
    }

    @PostMapping("/updatepasswd")
    @ResponseBody
    public String updatePassWd2(HttpServletRequest request){
        Subject subject = SecurityUtils.getSubject();
        String passwd=request.getParameter("new-password2");
        String userName =String.valueOf(subject.getSession().getAttribute("userName"));
        userService.updatePawd(passwd,userName);
        return "200";
    }
    @GetMapping("/updateuser")
    public String updateUser1(HttpServletRequest request,Model model){
        String userName = (String)request.getSession().getAttribute("userName");
        User user = userService.getUser(userName);
        model.addAttribute("user",user);
        return "user/user-information-edit";
    }

    @PostMapping("/updateuser")
    @ResponseBody
    public String updateUser2(HttpServletRequest request,Model model){
        Integer userId = Integer.valueOf(request.getParameter("userId"));
        String userName = request.getParameter("userName");
        String sex = request.getParameter("sex");
        User user = new User();
        user.setUsername(userName); user.setId(userId); user.setSex(Sex.valueOf(sex));
        userService.updateUser(user);
        user = userService.getUser(userName);
        request.getSession().setAttribute("userName",user.getUsername());
        model.addAttribute("user",user);
        return "200";
    }

    @GetMapping("/user-resume")
    public String getResume(Model model){
        Subject subject = SecurityUtils.getSubject();
        String userName = String.valueOf(subject.getSession().getAttribute("userName"));
        List<Resume>resumes = userService.getResumes(userName);
        model.addAttribute("count",resumes.size());
        model.addAttribute("resumes",resumes);
        return "user/user-resume-list";
    }

    @GetMapping("/add-resume")
    public String getResume1(){
        return "user/user-resume-add";
    }

    @PostMapping("/add-resume")
    @ResponseBody
    public String addResume(@RequestParam("Resume") MultipartFile file,HttpServletRequest request){
        if(file.isEmpty()){
            return "";
        }else {
            Subject subject = SecurityUtils.getSubject();
            String path = request.getSession().getServletContext().getRealPath("/upload/");
            if (!file.isEmpty()) {
                Resume resume = new Resume();
                if (!file.isEmpty()) {
                    try {
                        File filepath = new File(path);
                        if (!filepath.exists())
                            filepath.mkdirs();
                        String savePath = path + file.getOriginalFilename();
                        resume.setUserName(String.valueOf(subject.getSession().getAttribute("userName")));
                        resume.setAddress(savePath);
                        resume.setFileName(file.getOriginalFilename());
                        file.transferTo(new File(savePath));
                        userService.addResume(resume);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println(file.getOriginalFilename());
        }
        return "200";
    }

    @DeleteMapping("/resume/{id}")
    @ResponseBody
    public String delResume(@PathVariable(value = "id")String id){
        userService.deleteResume(Integer.valueOf(id));
        return "1";
    }

    @DeleteMapping("/user/{id}")
    @ResponseBody
    public String delUser(@PathVariable("id")String id){
        try{
            Integer userId = Integer.valueOf(id);
            userService.delUser(userId);
            return "200";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "500";
    }

    /*********面试进度**********/
    @RequestMapping("/user/process/{id}/{state}")
    public String selectProcess(@PathVariable("id")String id, @PathVariable("state")String state, Model model){
        Integer userId = Integer.valueOf(id);

        return "";
    }


}
