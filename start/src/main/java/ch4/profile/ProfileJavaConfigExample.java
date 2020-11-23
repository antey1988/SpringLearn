package ch4.profile;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class ProfileJavaConfigExample {
    public static void main(String[] args) {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(
                KindergardenConfig.class, HighSchoolConfig.class);
        FoodProviderService foodProviderService = ctx.getBean("foodProviderService", FoodProviderService.class);
        foodProviderService.providerLunchSet().forEach(t-> System.out.println("Food: " + t.getName()));
        ctx.close();
    }
}
