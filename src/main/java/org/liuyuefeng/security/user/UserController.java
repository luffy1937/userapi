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
    JdbcTemplate jdbcTemplate;
    @Autowired
    UserRepository userRepository;
    @PostMapping
    public User create(@RequestBody User user){
        return user;
    }
    @PutMapping("/{id}")
    public User update(@RequestBody User user){
        return user;
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){

    }
    @GetMapping("/{id}")
    public User get(@PathVariable Long id){
        return new User();
    }
    @GetMapping()
    public List<User> query(String name){
/*        String sql = "select id,name From user where name = '" + name + "'";
        List data = jdbcTemplate.queryForList(sql);
        return data;*/
        return userRepository.findByName(name);
    }
}
