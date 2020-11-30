package aopbasics.advice.afterreturningadvice;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

public class WeakKeyCheckAdvice implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] objects, Object target) throws Throwable {
        if ((target instanceof KeyGenerator) && (method.getName().equals("getKey"))) {
            long key = (Long) returnValue;
            if (key == KeyGenerator.WEAK_KEY)
                throw  new SecurityException("Key Generator generated a weak key. Try again");
        }
    }
}
