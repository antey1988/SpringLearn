package advice.afterreturningadvice;

import advice.methodbeforeadvice.Guitarist;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

public class SimpleAfterReturningAdvice implements AfterReturningAdvice {
    public static void main(String[] args) {
        Guitarist target = new Guitarist();

        ProxyFactory factory = new ProxyFactory();

        factory.addAdvice(new SimpleAfterReturningAdvice());
        factory.setTarget(target);

        Guitarist proxy = (Guitarist) factory.getProxy();
        proxy.sing();
    }

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("After '" + method.getName() + "' put down guitar");
    }
}
