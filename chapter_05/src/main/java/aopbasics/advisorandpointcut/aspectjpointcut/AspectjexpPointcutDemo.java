package aopbasics.advisorandpointcut.aspectjpointcut;

import aopbasics.advisorandpointcut.jdkregexppointcut.Guitarist;
import aopbasics.advisorandpointcut.staticpointcut.SimpleAdvice;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class AspectjexpPointcutDemo {
    public static void main(String[] args) {
        Guitarist johnmayer = new Guitarist();
        AspectJExpressionPointcut pc = new AspectJExpressionPointcut();
        pc.setExpression("execution(* sing*(..))");
        Advisor advisor = new DefaultPointcutAdvisor(pc, new SimpleAdvice());

        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(johnmayer);
        pf.addAdvisor(advisor);
        Guitarist proxy = (Guitarist)pf.getProxy();

        proxy.sing();
        proxy.sing2();
        proxy.rest();
    }
}
