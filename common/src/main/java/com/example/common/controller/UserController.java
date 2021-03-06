package com.example.common.controller;

import com.example.common.entity.Resume;
import com.example.common.entity.SysRole;
import com.example.common.entity.SystemLog;
import com.example.common.entity.User;
import com.example.common.enums.Sex;
import com.example.common.enums.identify;
import com.example.common.service.PermService;
import com.example.common.service.RoleService;
import com.example.common.service.SystemService;
import com.example.common.service.UserService;
import com.example.common.tools.Result;
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
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SystemService systemService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/login")
    public String toLogin(){
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("userName") String username, @RequestParam("passWord") String password,HttpServletRequest request){
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
            User user = userService.getUser(token.getUsername());
            request.getSession().setAttribute("userid",user.getId());
            request.getSession().setAttribute("user",user);
            log.setContent("登陆成功");
            log.setLoginTime(new Timestamp(System.currentTimeMillis()));
            log.setUserName(token.getUsername());
            log.setIpAddress(request.getRemoteAddr());
            systemService.addLog(log);
            return "redirect:/fore";
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

    @GetMapping("/resigter")
    public String toResigter(){
        return "resigter";
    }

    @PostMapping("/resigter")
    @ResponseBody
    public String Resigter(HttpServletRequest request){
        String userName = request.getParameter("userName");
        String passWord = request.getParameter("passWord1");
        User user = new User();
        if(userService.getUser(userName)==null){
            user.setUsername(userName);
            user.setAvailable(false);
            user.setPassword(passWord);
            String roleName = request.getParameter("role");
            if(roleName.equals("HR")){
                user.setIdentify(identify.HR);
            }else if(roleName.equals("USER")){
                user.setIdentify(identify.USER);
            }else if(roleName.equals("ADMIN")){
                user.setIdentify(identify.ADMIN);
            }
            userService.addUser(user);
            SysRole role = new SysRole();
            role.setRole(roleName);
            role.setUsername(userName);
            //roleService.addRole(role);
            return Result.SUCCESS;
        }else{
            return Result.EXITS;
        }
    }
    @GetMapping("/updatepasswd")
    public String updatePassWd1(HttpServletRequest request,Model model){
        return "user/user-password-edit";
    }

    @PostMapping("/updatepasswd")
    @ResponseBody
    public String updatePassWd2(HttpServletRequest request){
        try{
            Subject subject = SecurityUtils.getSubject();
            String passwd = request.getParameter("password");
            String newpasswd=request.getParameter("new-password2");
            String userName =String.valueOf(subject.getSession().getAttribute("userName"));
            userService.updatePawd(passwd,newpasswd,userName);
            return Result.SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR;
        }
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
        try{
            Integer userId = Integer.valueOf(request.getParameter("userId"));
            String userName = request.getParameter("userName");
            String sex = request.getParameter("sex");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            User user = new User();
            user.setUsername(userName); user.setId(userId); user.setSex(Sex.valueOf(sex));
            user.setEmail(email);user.setPhone(phone);
            userService.updateUser(user);
            user = userService.getUser(userName);
            request.getSession().setAttribute("userName",user.getUsername());
            model.addAttribute("user",user);
            return Result.SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR;
        }
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
            return Result.EMPTY;
        }else {
            Subject subject = SecurityUtils.getSubject();
            if (!file.isEmpty()) {
                Resume resume = new Resume();
                if (!file.isEmpty()) {
                    try {
                        File filepath = new File("D:/Path");
                        if (!filepath.exists())
                            filepath.mkdirs();
                        String savePath = file.getOriginalFilename();
                        resume.setUserName(String.valueOf(subject.getSession().getAttribute("userName")));
                        resume.setAddress("/Path/"+savePath);
                        resume.setFileName(file.getOriginalFilename());
                        file.transferTo(new File(savePath));
                        userService.addResume(resume);
                        return Result.SUCCESS;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return Result.ERROR;
                    }
                }
            }
            System.out.println(file.getOriginalFilename());
            return Result.ERROR;
        }
    }

    @DeleteMapping("/resume/{id}")
    @ResponseBody
    public String delResume(@PathVariable(value = "id")String id){
        userService.deleteResume(Integer.valueOf(id));
        return Result.SUCCESS;
    }
}
