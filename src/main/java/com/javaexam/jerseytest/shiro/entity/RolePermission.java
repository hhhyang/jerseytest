package com.javaexam.jerseytest.shiro.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class RolePermission implements Serializable{
    private static final long serialVersionUID = 1L;

    /**
     * role id
     * */
    private String roleId;

    /**
     * permission id
     * */
    private String permissionId;

}
