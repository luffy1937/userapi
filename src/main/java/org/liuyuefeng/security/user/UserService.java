package org.liuyuefeng.security.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface UserService {

    UserInfo create(UserInfo user);
    UserInfo update(UserInfo user);
    void delete(Long id);
    UserInfo get(Long id);
    List<UserInfo> query(String name);
}
