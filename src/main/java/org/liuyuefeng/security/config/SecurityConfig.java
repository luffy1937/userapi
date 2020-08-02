package org.liuyuefeng.security.config;

import org.liuyuefeng.security.audit.AclInterceptor;
import org.liuyuefeng.security.audit.AuditLogInterceptor;
import org.liuyuefeng.security.user.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.ServletRequest;
import java.util.Optional;

@Configuration
public class SecurityConfig implements WebMvcConfigurer {
    @Autowired
    AuditLogInterceptor auditLogInterceptor;
    @Autowired
    AclInterceptor aclInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册顺序也是执行顺序
        registry.addInterceptor(auditLogInterceptor);
        registry.addInterceptor(aclInterceptor);
    }
    @Bean
    public AuditorAware<String> auditorAware(){
        return new AuditorAware<String>() {
            @Override
            public Optional<String> getCurrentAuditor() {
                ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                UserInfo userInfo = (UserInfo)servletRequestAttributes.getRequest().getSession().getAttribute("user");
                String username = null;
                if(userInfo != null){
                    username = userInfo.getUsername();
                }
                //简单写死，可以自己实现
                return Optional.ofNullable(username);
            }
        };
    }
}
