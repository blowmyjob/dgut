package com.example.common.entity;

import com.example.common.enums.identify;

import java.sql.Timestamp;

/**
 * 系统登录日志
 */
public class SystemLog {
    private Integer id;   //记录Id
    private identify identify;  //登陆用户的身份
    private String content;   //登录返回内容
    private String userName;  //登录用户名
    private String ipAddress;  //ip地址
    private Timestamp loginTime;  //登录时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public com.example.common.enums.identify getIdentify() {
        return identify;
    }

    public void setIdentify(com.example.common.enums.identify identify) {
        this.identify = identify;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Timestamp getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Timestamp loginTime) {
        this.loginTime = loginTime;
    }
}
