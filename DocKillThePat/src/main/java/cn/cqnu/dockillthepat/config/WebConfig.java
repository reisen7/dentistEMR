package cn.cqnu.dockillthepat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 刘良杰
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/profiles/**").addResourceLocations("file:D:\\java\\doctorKillPatient\\src\\main\\resources\\static\\file");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOriginPatterns("*").allowCredentials(true)
                .allowedMethods("PUT","GET","POST","DELETE","OPTIONS","HEAD").allowedHeaders("*").maxAge(3600);
        WebMvcConfigurer.super.addCorsMappings(registry);
    }
}
