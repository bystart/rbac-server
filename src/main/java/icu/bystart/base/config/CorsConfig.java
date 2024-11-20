package icu.bystart.base.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 跨域配置类
 */
@Configuration
public class CorsConfig {

    /**
     * 配置跨域过滤器
     * @return CorsFilter
     */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        // 允许跨域的cookie
        config.setAllowCredentials(true);
        // 允许所有域名跨域
        config.addAllowedOriginPattern("*");
        // 允许所有头信息跨域
        config.addAllowedHeader("*");
        // 允许所有方法跨域
        config.addAllowedMethod("*");
        // 暴露header中的token
        config.addExposedHeader("Authorization");
        // 设置预检请求的有效期
        config.setMaxAge(3600L);
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
} 