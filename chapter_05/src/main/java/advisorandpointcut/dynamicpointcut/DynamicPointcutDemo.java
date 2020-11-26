package advisorandpointcut.dynamicpointcut;

import advisorandpointcut.staticpointcut.SimpleAdvice;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class DynamicPointcutDemo {
    public static void main(String[] args) {
        SampleBean target = new SampleBean();

        Advisor advisor = new DefaultPointcutAdvisor(new SimpleDynamicPointcut(), new SimpleAdvice());
        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(target);
        pf.addAdvisor(advisor);

        SampleBean sb = (SampleBean)pf.getProxy();
        sb.foo(1);
        sb.foo(10);
        sb.foo(100);

        sb.bar();
        sb.bar();
        sb.bar();
    }
}
