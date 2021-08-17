package com.ssafy.config;

import com.ssafy.api.service.KakaoAPI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.handler.WebRequestHandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.PathResourceResolver;

import javax.servlet.Filter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // configuration.addAllowedOrigin("*");
        configuration.addAllowedOriginPattern("*"); // CORS 해결을 위한 설정 (고정)
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        // configuration.addExposedHeader(JwtTokenUtil.HEADER_STRING);
        configuration.setAllowCredentials(true); // CORS 해결을 위한 설정 (고정)
        configuration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Value("C:\\Users\\multicampus\\Desktop\\ssafy\\공통프로젝트\\Doglive\\profile_imgs\\")
    private String profileUploadFolder;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);

        registry.addResourceHandler("/profile_imgs/**")
                .addResourceLocations("file:///" + profileUploadFolder)
                .resourceChain(true)
                .addResolver(new PathResourceResolver());

        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/WEB-INF/resources/");
    		
        registry.addResourceHandler("/swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    		
        /*
    	 *
    	 * Front-end에서 참조하는 URL을 /dist로 매핑
    	 *
    	 */
        registry.addResourceHandler("/css/**")
                .addResourceLocations("classpath:/dist/css/");
        registry.addResourceHandler("/fonts/**")
                .addResourceLocations("classpath:/dist/fonts/");
        registry.addResourceHandler("/icons/**")
				.addResourceLocations("classpath:/dist/icons/");
        registry.addResourceHandler("/img/**")
			    .addResourceLocations("classpath:/dist/img/");
        registry.addResourceHandler("/js/**")
				.addResourceLocations("classpath:/dist/js/");
    }

    public Filter requestLoggingFilter() {
        CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
        loggingFilter.setIncludeClientInfo(true);
        loggingFilter.setIncludeQueryString(true);
        loggingFilter.setIncludePayload(true);
        loggingFilter.setIncludeHeaders(true);
        loggingFilter.setMaxPayloadLength(64000);
        return loggingFilter;
    }

    @Bean
    public FilterRegistrationBean loggingFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean(requestLoggingFilter());
        registration.addUrlPatterns("/api/*");
        return registration;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new KakaoAuthHandler())
                .addPathPatterns("/api/v1/adopt/**")
                .addPathPatterns("/api/v1/chatroom/**")
                .addPathPatterns("/api/v1/community/**")
                .addPathPatterns("/api/v1/users/**")
                .excludePathPatterns("/api/v1/community/list/**")
                .excludePathPatterns("/api/v1/community/detail/**")
                .excludePathPatterns("/api/v1/community/comment/**")
                .excludePathPatterns("/api/v1/community/new/**")
                .excludePathPatterns("/api/v1/users/present/**");
    }

    class KakaoAuthHandler extends HandlerInterceptorAdapter {
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            Cookie[] cookies = request.getCookies();
            String accessToken = null;
            String refreshToken = null;

            // 현재 로그인 된 상태인지 체크
            if(cookies == null || cookies.length == 0){
                throw new AuthorizationServiceException("로그인이 필요합니다!");
            }

            for(Cookie cookie : cookies){
                if(cookie.getName().equals("accessToken"))
                    accessToken = cookie.getValue();
                else if(cookie.getName().equals("refreshToken"))
                    refreshToken = cookie.getValue();
            }

            // 현재 로그인 된 상태인지 체크
            if(accessToken == null || refreshToken == null) {
                throw new AuthorizationServiceException("로그인이 필요합니다!");
            }

            KakaoAPI kakaoAPI = new KakaoAPI();
            int responseCode = kakaoAPI.checkAccessToken(accessToken, refreshToken); // 갱신 여부 체크
            System.out.println("WebMvcConfig-ResponseCode : " + responseCode);
            if(responseCode == 400 || responseCode == 401){
                throw new AuthorizationServiceException("로그인이 필요합니다!");
            }
            return true;
        }
    }

}
