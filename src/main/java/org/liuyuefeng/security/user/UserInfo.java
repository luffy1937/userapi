package org.liuyuefeng.security.user;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
//单一职责 输入输出
public class UserInfo {
    private Long id;
    private String name;
    private String username;
    private String password;
}
