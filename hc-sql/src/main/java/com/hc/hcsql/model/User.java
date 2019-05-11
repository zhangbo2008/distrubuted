package com.hc.hcsql.model;

public class User {

    private int userId;
    private String userName;
    private String userPhone;
    private String userImage;
    private String userPassword;

    public User() {
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserImage() {
        return userImage;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}

