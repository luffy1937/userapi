package org.liuyuefeng.security.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping
    public UserInfo create(@RequestBody UserInfo user){

        return userService.create(user);
    }
    @PutMapping("/{id}")
    public UserInfo update(@RequestBody UserInfo user){

        return userService.update(user);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        userService.delete(id);
    }
    @GetMapping("/{id}")
    public UserInfo get(@PathVariable Long id){

        return userService.get(id);
    }
    @GetMapping()
    public List<UserInfo> query(String name){

        return userService.query(name);
    }
}
