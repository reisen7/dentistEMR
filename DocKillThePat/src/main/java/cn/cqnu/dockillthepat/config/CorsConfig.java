package cn.cqnu.dockillthepat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


/**
 * @author 刘良杰
 */
@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter(){
        //跨域配置对象
        CorsConfiguration corsConfiguration=new CorsConfiguration();
        //过滤规则
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedOrigin("*");
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource=new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**",corsConfiguration);
        return  new CorsFilter(urlBasedCorsConfigurationSource);
    }
}
