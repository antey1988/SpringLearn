package ch3.annotated;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Service;

@Service("injectSimple")
public class InjectSimple {
    @Value("John Mayer")
    private String name;
    @Value("39")
    private int age;
    @Value("1.92")
    private float height;
    @Value("false")
    private boolean programmer;
    @Value("1241401112")
    private Long ageinSeconds;

    public static void main (String ... args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("/spring/app-context-annotation.xml");
        ctx.refresh();
        InjectSimple simple = (InjectSimple) ctx.getBean("injectSimple");
        System.out.println(simple);
        ctx.close();
    }

    public void setAgeinSeconds(Long ageinSeconds) {
        this.ageinSeconds = ageinSeconds;
    }

    public void setProgrammer (boolean programmer) {
        this.programmer = programmer;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "InjectSimple{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                ", programmer=" + programmer +
                ", ageinSeconds=" + ageinSeconds +
                '}';
    }
}
