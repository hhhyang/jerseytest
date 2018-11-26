package com.javaexam.jerseytest.shiro.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class User  implements Serializable {

    private static final long serialVersionUID = -8736616045315083846L;

    /**
     * 用户id
     * */
    private String userId;

    /**
     * 用户名称
     * */
    private String username;

    /**
     * 密码
     * */
    private String password;

    /**
     * 是否启用
     */
    private Integer enable=0;

}
