package advicorandpointcut.dynamicpointcut;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.DynamicMethodMatcherPointcut;

import java.lang.reflect.Method;

public class SimpleDynamicPointcut extends DynamicMethodMatcherPointcut {
    @Override
    public ClassFilter getClassFilter() {
        return cls -> cls == SampleBean.class;
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        System.out.println("Static check for " + method.getName());
        return method.getName().equals("foo");
    }

    @Override
    public boolean matches(Method method, Class<?> aClass, Object... objects) {
        System.out.println("Dynamic check for " + method.getName());
        int x = (Integer)objects[0];
        return x != 100;
    }
}
