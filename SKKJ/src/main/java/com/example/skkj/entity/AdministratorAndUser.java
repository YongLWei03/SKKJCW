package com.example.skkj.entity;
/**
 * 管理员添加用户的关系
 * @Author ZhouNan
 * @Description
 * @params
 * @Date 16:41 2017/12/11 0011
 */
public class AdministratorAndUser {
    //ID
    private String auId;

    //管理员ID
    private String adminId;

    //管理员添加用户的Id
    private String userId;

    public String getAuId() {
        return auId;
    }

    public void setAuId(String auId) {
        this.auId = auId;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "AdministratorAndUser{" +
                "auId='" + auId + '\'' +
                ", adminId='" + adminId + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
