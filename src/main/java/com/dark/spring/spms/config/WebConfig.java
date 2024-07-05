package com.dark.spring.spms.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Collections;

@Configuration
public class WebConfig extends DelegatingWebMvcConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(WebConfig.class);

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        LOG.info("TEST Inside addCorsMappings");
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }

    @Override
    protected RequestMappingHandlerMapping createRequestMappingHandlerMapping() {
        RequestMappingHandlerMapping mapping = new RequestMappingHandlerMapping();
        LOG.info("TEST Inside createRequestMappingHandlerMapping");
        mapping.setPathPrefixes(Collections.singletonMap("/api/v1", clazz -> clazz.getPackage().getName().startsWith("com.dark.spring.spms")));
        return mapping;
    }
}