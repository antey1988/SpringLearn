package ch5.methodbeforeadvice;

import org.springframework.aop.framework.ProxyFactory;

public class SecurityDemo {
    public static void main(String[] args) {
        SecurityManager mgr = new SecurityManager();
        SecureBean bean = getSecureBean();

        mgr.login("John", "pwd");
        bean.writeSecureMessage();
        mgr.logout();

        try {
            mgr.login("invalid user", "pwd");
            bean.writeSecureMessage();
        } catch (SecurityException ex) {
            System.out.println("Exception Caught: " + ex.getMessage());
        } finally {
            mgr.logout();
        }

        try {
            bean.writeSecureMessage();
        } catch (SecurityException ex) {
            System.out.println("Exception Caught: " + ex.getMessage());
        }
    }

    private static SecureBean getSecureBean() {
        SecureBean target = new SecureBean();
        SecurityAdvice advice = new SecurityAdvice();
        ProxyFactory pf = new ProxyFactory();
        pf.addAdvice(advice);
        pf.setTarget(target);
        SecureBean proxy = (SecureBean) pf.getProxy();
        return  proxy;
    }
}
