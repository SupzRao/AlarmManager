package com.alarmmanager.model.entity;

import org.greenrobot.greendao.annotation.*;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit.

/**
 * Entity mapped to table "USERS".
 */
@Entity
public class Users {

    @Id(autoincrement = true)
    private Long id;
    private java.util.Date createTime;
    private String name;
    private String email;
    private String image;

    @Generated(hash = 2146996206)
    public Users() {
    }

    public Users(Long id) {
        this.id = id;
    }

    @Generated(hash = 463550251)
    public Users(Long id, java.util.Date createTime, String name, String email, String image) {
        this.id = id;
        this.createTime = createTime;
        this.name = name;
        this.email = email;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public java.util.Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
