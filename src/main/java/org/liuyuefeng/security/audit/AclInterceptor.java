package org.liuyuefeng.security.audit;

import org.liuyuefeng.security.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AclInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean result = true;
        User user = (User) request.getAttribute("user");
        if(user == null){
            response.setContentType("text/plain");
            response.getWriter().write("need authentication");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }else {
            String method = request.getMethod();
            if(!user.hasPermission(method)){
                response.setContentType("text/plain");
                response.getWriter().write("forbiden");
                response.setStatus(HttpStatus.FORBIDDEN.value());
                result = false;
            }
        }
        return result;
    }
}
