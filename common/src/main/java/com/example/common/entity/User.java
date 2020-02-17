package com.example.common.entity;

import com.example.common.enums.Sex;
import com.example.common.enums.identify;

import java.sql.Timestamp;
import java.util.List;

public class User {
    private int id;     //用户id
    private String username;   //用户名
    private String password;   //用户密码
    private Sex sex;           //性别
    private String phone;      //手机
    private identify identify; //身份
    private Boolean available; //可用状态
    private Timestamp entertime; //加入的时间
    private SysRole role;       // 一个用户具有一个角色
    private String email;       //邮箱

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public identify getIdentify() {
        return identify;
    }

    public void setIdentify(identify identify) {
        this.identify = identify;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public SysRole getRole() {
        return role;
    }

    public void setRole(SysRole role) {
        this.role = role;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Timestamp getEntertime() {
        return entertime;
    }

    public void setEntertime(Timestamp entertime) {
        this.entertime = entertime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
