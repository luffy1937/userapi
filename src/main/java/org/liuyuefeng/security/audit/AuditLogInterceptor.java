package org.liuyuefeng.security.audit;

import org.liuyuefeng.security.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuditLogInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private AuditLogRepository auditLogRepository;
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("this is interceptor audit");
        AuditLog log = new AuditLog();
        log.setMethod(request.getMethod());
        log.setPath(request.getRequestURI());
        User user = (User)request.getAttribute("user");
        if(user != null){
            log.setUsername(user.getUserName());
        }
        auditLogRepository.save(log);
        request.setAttribute("auditLogId", log.getId());
        return true;
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        Long auditLogId = (Long)request.getAttribute("auditLogId");
        AuditLog auditLog = auditLogRepository.findById(auditLogId).get();
        auditLog.setStatus(response.getStatus());
        auditLogRepository.save(auditLog);
    }

    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    }
}
