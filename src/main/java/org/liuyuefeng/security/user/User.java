package org.liuyuefeng.security.user;

import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    //NotBlank是应用层面，nullable是数据库层面
    @NotBlank(message = "用户名不能为空")
    @Column(unique = true, nullable = true)
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;
    public UserInfo buildInfo(){
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(this, userInfo);
        return userInfo;
    }
}
