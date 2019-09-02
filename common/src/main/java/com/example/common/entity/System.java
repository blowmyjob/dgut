package com.example.common.entity;

public class System {
    private Integer websiteId;            //网站编号
    private String websiteTitle;   //网站名称
    private String websiteDescription;  //描述
    private String websiteUploadfile;   //上传目录配置

    public String getWebsiteTitle() {
        return websiteTitle;
    }

    public void setWebsiteTitle(String websiteTitle) {
        this.websiteTitle = websiteTitle;
    }

    public String getWebsiteDescription() {
        return websiteDescription;
    }

    public void setWebsiteDescription(String websiteDescription) {
        this.websiteDescription = websiteDescription;
    }

    public String getWebsiteUploadfile() {
        return websiteUploadfile;
    }

    public void setWebsiteUploadfile(String websiteUploadfile) {
        this.websiteUploadfile = websiteUploadfile;
    }

    public Integer getWebsiteId() {
        return websiteId;
    }

    public void setWebsiteId(Integer websiteId) {
        this.websiteId = websiteId;
    }
}
