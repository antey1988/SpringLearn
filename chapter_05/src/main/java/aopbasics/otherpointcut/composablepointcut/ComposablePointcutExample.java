package aopbasics.otherpointcut.composablepointcut;

import aopbasics.advisorandpointcut.namepointcut.Guitar;
import org.springframework.aop.Advisor;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcher;
import aopbasics.otherpointcut.controlflowpointcut.SimpleBeforeAdvice;

import java.lang.reflect.Method;

public class ComposablePointcutExample {
    public static void main(String[] args) {
        GrammyGuitarist gg = new GrammyGuitarist();

        ComposablePointcut pc = new ComposablePointcut(ClassFilter.TRUE, new SingMethodMather());

        System.out.println("test 1 >> ");
        GrammyGuitarist proxy = getProxy(pc,gg);
        testInvoke(proxy);
        System.out.println();

        System.out.println("test 2 >> ");
        pc.union(new TalkMethodMatcher());
        proxy = getProxy(pc,gg);
        testInvoke(proxy);
        System.out.println();

        System.out.println("test 3 >> ");
        pc.intersection(new RestMethodMather());
        proxy = getProxy(pc,gg);
        testInvoke(proxy);
    }

    private  static GrammyGuitarist getProxy(ComposablePointcut pc, GrammyGuitarist target) {
        Advisor advisor = new DefaultPointcutAdvisor(pc, new SimpleBeforeAdvice());
        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(target);
        pf.addAdvisor(advisor);
        return (GrammyGuitarist)pf.getProxy();
    }

    private  static void testInvoke(GrammyGuitarist proxy) {
        proxy.sing();
        proxy.sing(new Guitar());
        proxy.talk();
        proxy.rest();
    }

    private static class SingMethodMather extends StaticMethodMatcher {
        @Override
        public boolean matches(Method method, Class<?> aClass) {
            return method.getName().startsWith("si");
        }
    }

    private static class TalkMethodMatcher extends StaticMethodMatcher {
        @Override
        public boolean matches(Method method, Class<?> aClass) {
            return method.getName().equals("talk");
        }
    }

    private static class RestMethodMather extends StaticMethodMatcher {
        @Override
        public boolean matches(Method method, Class<?> aClass) {
            return method.getName().endsWith("st");
        }
    }
}
