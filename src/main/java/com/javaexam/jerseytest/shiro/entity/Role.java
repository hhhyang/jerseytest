package com.javaexam.jerseytest.shiro.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Role implements Serializable{
    private static final long serialVersionUID = -6140090613812307452L;

    /**
     * 角色id
     * */
    private String roleId;

    /**
     * 角色名称，如：admin,user等
     * */
    private String roleName;

}