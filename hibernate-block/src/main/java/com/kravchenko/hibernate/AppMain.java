package com.kravchenko.hibernate;

import com.kravchenko.hibernate.configuration.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppMain {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        GetDataFromService getData = context.getBean(GetDataFromService.class);
        getData.getData();
    }


}
