package com.javaexam.jerseytest.shiro.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRole implements Serializable{

    private static final long serialVersionUID = 1L;

    private String userId;

    private String roleId;

}
