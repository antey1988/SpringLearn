package ch5.afterreturningadvice;

import org.springframework.aop.framework.ProxyFactory;

public class AfterAdviceDemo {
    public static void main(String[] args) {
        KeyGenerator keyGen = getKeyGenerator();
        for (int i =0; i < 10; i++) {
            try {
                long key = keyGen.getKey();
                System.out.println("Key: " + key);
            } catch (SecurityException ex) {
                System.out.println("Weak Key Generated!");
            }
        }
    }

    private static KeyGenerator getKeyGenerator() {
        KeyGenerator target = new KeyGenerator();

        ProxyFactory pf = new ProxyFactory();

        pf.addAdvice(new WeakKeyCheckAdvice());
//        pf.addAdvice(new SimpleAfterReturningAdvice());
        pf.setTarget(target);

        KeyGenerator proxy = (KeyGenerator) pf.getProxy();
        return proxy;
    }
}
