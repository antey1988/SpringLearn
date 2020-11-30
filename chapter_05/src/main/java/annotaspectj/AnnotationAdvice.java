package annotaspectj;

import aopbasics.advisorandpointcut.namepointcut.Guitar;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AnnotationAdvice {
//    @Pointcut("execution(* sing*(aopbasics.advisorandpointcut.namepointcut.Guitar))")
//     && args(value)
    @Pointcut("execution(* sing*(aopbasics.advisorandpointcut.namepointcut.Guitar)) && args(value)")
    public void singExecution(Guitar value) {}

//    @Pointcut("bean(john*)")
//    public void isJohn() {}

    @Before("singExecution(value)")
    public void  simpleBeforeAdvice(JoinPoint joinPoint, Guitar value) {
//        if (value.getBrand().equals("Gibson")) {
            System.out.println("Executing: "
                    + joinPoint.getSignature().getDeclaringTypeName()
                    + joinPoint.getSignature().getName());
//        }
    }

    @Around("singExecution(value)")
    public Object simpleAroundAdvice(ProceedingJoinPoint pjp, Guitar value) throws Throwable {
        System.out.println("Before execution: "
                + pjp.getSignature().getDeclaringTypeName() + " "
                + pjp.getSignature().getName()
//                + " argument: " + value.getBrand()
        );
        Object retVal = pjp.proceed();
        System.out.println("After execution: "
                + pjp.getSignature().getDeclaringTypeName() + " "
                + pjp.getSignature().getName()
//                + " argument: " + value.getBrand()
        );
        return retVal;
    }
}
