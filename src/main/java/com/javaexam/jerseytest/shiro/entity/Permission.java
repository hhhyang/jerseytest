package com.javaexam.jerseytest.shiro.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

@Data
public class Permission implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * ID
     * */
    public Integer permissionId;

    /**
     *权限，如：update、delete、add、query等
     * */
    public String permission;

    /**
     * 角色名称，如：admin,user等
     * */
    @JsonIgnore
    private String roleName;
}
