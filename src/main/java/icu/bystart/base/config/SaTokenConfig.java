package icu.bystart.base.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Sa-Token配置类
 */
@Configuration
public class SaTokenConfig implements WebMvcConfigurer {
    
    /**
     * 注册Sa-Token拦截器，打开注解式鉴权功能
     * @param registry 拦截器注册表
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册Sa-Token的路由拦截器，并排除登录注册接口和Swagger相关接口
        registry.addInterceptor(new SaInterceptor(handle -> {
            // 检查登录
            StpUtil.checkLogin(); // 确保此处能正确验证Token
        }))
        .addPathPatterns("/**")
        .excludePathPatterns(
            "/doc.html",
            "/auth/login",
            "/auth/register",
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs/**",
            "/webjars/**"
        );
    }
} 