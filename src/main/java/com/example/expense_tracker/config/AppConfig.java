// package com.example.expense_tracker.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.thymeleaf.templatemode.TemplateMode;
// import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

// @Configuration
// public class AppConfig {
//     @Bean
//     public ClassLoaderTemplateResolver secondaryTemplateResolver() {
//         ClassLoaderTemplateResolver secondaryTemplateResolver = new ClassLoaderTemplateResolver();
//         System.out.print("Innnnnnnnnnnnnn");
//         secondaryTemplateResolver.setPrefix("templates-2/");
//         secondaryTemplateResolver.setSuffix(".html");
//         secondaryTemplateResolver.setTemplateMode(TemplateMode.HTML);
//         secondaryTemplateResolver.setCharacterEncoding("UTF-8");
//         secondaryTemplateResolver.setOrder(1);
//         secondaryTemplateResolver.setCheckExistence(true);
            
//         return secondaryTemplateResolver;
//     }
// }
