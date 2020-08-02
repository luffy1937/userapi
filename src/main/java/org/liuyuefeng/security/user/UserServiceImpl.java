package org.liuyuefeng.security.user;

import com.lambdaworks.crypto.SCryptUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserInfo login(UserInfo userInfo) {
        User user = userRepository.findByUsername(userInfo.getUsername());
        if(user != null && SCryptUtil.check(userInfo.getPassword(), user.getPassword())){
            System.out.println("login success");
            return user.buildInfo();
        } else {
            System.out.println("login fail");
            return null;
        }
    }

    public UserInfo create(UserInfo userInfo){
        User user = new User();
        BeanUtils.copyProperties(userInfo, user);
        user.setPassword(SCryptUtil.scrypt(user.getPassword(), 32768, 8, 1));
        userRepository.save(user);
        userInfo.setId(user.getId());
        return userInfo;
    }

    public UserInfo update(UserInfo userInfo) {
        return null;
    }

    public void delete(Long id) {

    }

    public UserInfo get(Long id) {
        return userRepository.findById(id).get().buildInfo();
    }

    public List<UserInfo> query(String name) {
        return null;
    }
}
