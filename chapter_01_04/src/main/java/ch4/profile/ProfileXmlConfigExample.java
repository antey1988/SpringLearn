package ch4.profile;


import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;

public class ProfileXmlConfigExample {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.getEnvironment().setActiveProfiles("kindergarden");
        ctx.load("classpath:spring/profile/*-config.xml");
        ctx.refresh();

        FoodProviderService foodProviderService = ctx.getBean("foodProviderService", FoodProviderService.class);
        List<Food> lunchSet = foodProviderService.providerLunchSet();
        lunchSet.forEach(t-> System.out.println("Food: "  +  t.getName()));
        ctx.close();
    }
}
