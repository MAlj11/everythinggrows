package cn.everythinggrows.forum.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

@Component
@Aspect
public class SessionAOP {
    @Around(value = "@annotation(cn.everythinggrows.web.NeedSession)")
    public Object aroundManager(ProceedingJoinPoint pj) throws Exception {
        HttpServletRequest request = SysContent.getRequest();
        HttpServletResponse response = SysContent.getResponse();
        HttpSession session = SysContent.getSession();

        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName()
                + ":" + request.getServerPort() + path + "/";

        SessionType type = this.getSessionType(pj);
        if (type == null) {
            throw new Exception("The value of NeedSession is must.");
        }

        Object uobj = session.getAttribute("user");
        Object mobj = session.getAttribute("manager");

        boolean isUser = type == SessionType.USER && uobj != null;
        boolean isManager = type == SessionType.MANAGER && mobj != null;
        boolean isUserOrManager = type == SessionType.OR&& (mobj != null || uobj != null);
        try {
            if (isUser || isManager || isUserOrManager) {
                return pj.proceed();
            } else { // 会话过期或是session中没用户
                if (request.getHeader("x-eg-session") != null
                        && request.getHeader("x-eg-session").equalsIgnoreCase(
                                //ajax处理
                        "XMLHttpRequest")) {
                    response.addHeader("sessionstatus", "timeout");
                    // 解决EasyUi问题
                    response.getWriter().print("{\"rows\":[],\"success\":false,\"total\":0}");
                }else{//http跳转处理
                    response.sendRedirect(basePath + "/nosession");
                }
            }
        } catch (Throwable e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    private SessionType getSessionType(ProceedingJoinPoint pj) {
        // 获取切入的 Method
        MethodSignature joinPointObject = (MethodSignature) pj.getSignature();
        Method method = joinPointObject.getMethod();
        boolean flag = method.isAnnotationPresent(NeedSession.class);
        if (flag) {
            NeedSession annotation = method.getAnnotation(NeedSession.class);
            return annotation.value();
        }
        return null;
    }

}
