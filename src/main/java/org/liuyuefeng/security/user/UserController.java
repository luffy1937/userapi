package org.liuyuefeng.security.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/login")
    public void login( @Validated UserInfo user, HttpServletRequest request){
        UserInfo info = userService.login(user);
        //每次登陆，更新session;避免sesion攻击：即blackman写入自己的sessionid到被攻击者，被攻击者登陆后将用户信息与此sessionId绑定，是的blackman获取权限
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
        request.getSession(true).setAttribute("user", info);

    }

    @PostMapping
    public UserInfo create(@RequestBody @Validated UserInfo user){
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
    public UserInfo get(@PathVariable Long id, HttpServletRequest httpServletRequest){
        User user = (User)  httpServletRequest.getAttribute("user");
        System.out.println("controller:" + user);
        if(user == null || !user.getId().equals(id)){
            throw new RuntimeException("身份认证信息异常，获取用户信息失败");
        }
        UserInfo userInfo;
        userInfo = userService.get(id);
        System.out.println(userInfo.toString());
        return userService.get(id);
    }
    @GetMapping()
    public List<UserInfo> query(String name){

        return userService.query(name);
    }
}
