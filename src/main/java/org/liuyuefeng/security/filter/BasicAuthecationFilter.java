package org.liuyuefeng.security.filter;

import org.apache.commons.lang3.StringUtils;
import org.liuyuefeng.security.user.User;
import org.liuyuefeng.security.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class BasicAuthecationFilter extends OncePerRequestFilter{
    @Autowired
    private UserRepository userRepository;
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = httpServletRequest.getHeader("Authorization");
        if(StringUtils.isNoneBlank(authHeader)){
            String token64 = StringUtils.substringAfter(authHeader, "Basic ");
            String token = new String(Base64Utils.decodeFromString(token64));
            //这个方法不报空指针
            String[] items = StringUtils.splitByWholeSeparatorPreserveAllTokens(token, ":");
            String username = items[0];
            String password = items[1];
            User user = userRepository.findByUserName(username);
            System.out.println("filter:" + user);
            if(user != null && StringUtils.equals(password, user.getPassword())){
                httpServletRequest.setAttribute("user", user);
                System.out.println("认证success");
            }
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
    }
}
