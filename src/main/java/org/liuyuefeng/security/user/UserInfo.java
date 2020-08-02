package org.liuyuefeng.security.user;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Data
//单一职责 输入输出
public class UserInfo {
    private Long id;
    private String name;
    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;
    private String permissions;

    public boolean hasPermission(String method){
        boolean result = false;
        if(StringUtils.equalsIgnoreCase("get", method)){
            result = StringUtils.contains(permissions, "r");
        }else {
            result = StringUtils.contains(permissions, "w");
        }
        return result;
    }
}
