package com.javaexam.jerseytest.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 既可以生成json数据，也可以生成xml数据
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "user")
public class User {

    private String name;
    private int age;

}
