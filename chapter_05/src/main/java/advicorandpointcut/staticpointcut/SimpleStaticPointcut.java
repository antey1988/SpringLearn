package advicorandpointcut.staticpointcut;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;

public class SimpleStaticPointcut extends StaticMethodMatcherPointcut {
    @Override
    public boolean matches(Method method, Class<?> aClass) {
        return (method.getName().equals("sing"));
    }

    @Override
    public ClassFilter getClassFilter() {
        return aClass -> aClass == GoodGuitarist.class;
    }
}
