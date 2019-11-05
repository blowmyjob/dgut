package com.example.webrtc.entity;

public class Hire {
    private int id;
    private String workname;
    private String description;
    private int hirecount;
    private int companyid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWorkname() {
        return workname;
    }

    public void setWorkname(String workname) {
        this.workname = workname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getHirecount() {
        return hirecount;
    }

    public void setHirecount(int hirecount) {
        this.hirecount = hirecount;
    }

    public int getCompanyid() {
        return companyid;
    }

    public void setCompanyid(int companyid) {
        this.companyid = companyid;
    }
}
