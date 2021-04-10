package base.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;


@Component("myAspect")
@Aspect
public class AuthAspect {

    private static HttpSession httpSession() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest().getSession();
    }

    @Before("execution(* base.*(..)))")
    public void bTask(JoinPoint jp){
        HttpSession httpSession = httpSession();
        System.out.println("Hello");

        System.out.println(httpSession.getAttribute("authorization"));
    }

}
