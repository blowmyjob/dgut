package com.example.common.controller;

import com.example.common.entity.System;
import com.example.common.entity.SystemLog;
import com.example.common.service.DataDictServcie;
import com.example.common.service.SystemService;
import com.example.common.tools.Result;
import com.example.common.tools.SystemUtils;
import com.example.common.vo.DataDict;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class SystemController {
    @Autowired
    private SystemService systemService;

    @Autowired
    private DataDictServcie dataDictServcie;


    @GetMapping("/system-base")
    public String updateSystem1(Model model){
        model.addAttribute("system", systemService.selectSystem());
        return "system/system-base";
    }

    @PostMapping("/system-base")
    @ResponseBody
    public String updateSystem2(HttpServletRequest request,Model model){
        System newSystem = new System();
        newSystem.setWebsiteId(Integer.valueOf(request.getParameter("website-id")));
        newSystem.setWebsiteTitle(request.getParameter("website-title"));
        newSystem.setWebsiteDescription(request.getParameter("website-description"));
        newSystem.setWebsiteUploadfile(request.getParameter("website-uploadfile"));
        systemService.updateSystem(newSystem);
        SystemUtils.setSystem(newSystem);
        model.addAttribute("system",systemService.selectSystem());
        return "200";
    }

    @GetMapping("/system-log")
    public String selectLog(Model model){
        List<SystemLog>logs = systemService.getLogs();
        model.addAttribute("logs",logs);
        model.addAttribute("count",logs.size());
        return "system/system-log";
    }

    @GetMapping("/exception-log")
    public String exceptionLog(Model model){
        List<SystemLog>logs = systemService.getExceptionLogs();
        model.addAttribute("logs",logs);
        model.addAttribute("count",logs.size());
        return "system/system-log";
    }

    @GetMapping("/datadict")
    public String dataDict(Model model){
        List<DataDict>dataDicts = dataDictServcie.selectDataDicts();
        model.addAttribute("datadicts",dataDicts);
        model.addAttribute("count",dataDicts.size());
        return "system/datadict-list";
    }

    @PostMapping("/adddict")
    @ResponseBody
    public String addDict(HttpServletRequest request){
        String name = request.getParameter("name");
        String value = request.getParameter("value");
        String code = request.getParameter("code");
        DataDict dataDict = new DataDict();
        dataDict.setDictname(name); dataDict.setDictvalue(value);dataDict.setDictcode(code);
        dataDictServcie.insertDict(dataDict);
        return Result.SUCCESS;
    }

    @GetMapping("/adddict")
    public String toAdd(){
        return "system/datadict-add";
    }

    @PostMapping("/deldict/{id}")
    public String delDict(@PathVariable("id")Integer id){
        try{
            dataDictServcie.deleteById(id);
            return Result.SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR;
        }
    }
}
