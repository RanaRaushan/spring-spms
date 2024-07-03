package com.dark.spring.spms.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Collections;

@Configuration
public class WebConfig extends DelegatingWebMvcConfiguration {

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        System.out.println("TEST Inside addCorsMappings");
//        registry.addMapping("/**")
//                .allowedOrigins("http://example.com")
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//                .allowedHeaders("*")
//                .allowCredentials(true);
//    }

    @Override
    protected RequestMappingHandlerMapping createRequestMappingHandlerMapping() {
        RequestMappingHandlerMapping mapping = new RequestMappingHandlerMapping();
        System.out.println("TEST Inside createRequestMappingHandlerMapping");
        mapping.setPathPrefixes(Collections.singletonMap("/api/v1", clazz -> clazz.getPackage().getName().startsWith("com.dark.spring.spms")));
        return mapping;
    }
}