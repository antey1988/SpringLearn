package advice.interceptor;

import org.springframework.aop.framework.ProxyFactory;

public class ProfilingExample {
    private static WorkerBean getWorkerBean() {
        WorkerBean target = new WorkerBean();

        ProxyFactory pf = new ProxyFactory();

        pf.addAdvice(new ProfilingInterceptor());
        pf.setTarget(target);

        return (WorkerBean) pf.getProxy();
    }

    public static void main(String[] args) {
        WorkerBean bean = getWorkerBean();
        bean.doSomeWork(100_000);
    }
}
