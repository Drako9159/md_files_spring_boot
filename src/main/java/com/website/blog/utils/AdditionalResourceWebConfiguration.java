package com.website.blog.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
@EnableWebMvc
public class AdditionalResourceWebConfiguration implements WebMvcConfigurer {


    private static final String[] CLASSPATH_RESOURCE_LOCATIONS =
            {
                    "classpath:/META-INF/resources/",
                    "classpath:/resources/",
                    "classpath:/static/",
                    "classpath:/public/",
                    "classpath:/static/vendor/",
                    "classpath:/static/custom/",
                    "classpath:/templates",
                    "file:/" + System.getProperty("user.dir") + "/src/main/posts/"
            };

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/**")
                .addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS)
                .setCachePeriod(3600)
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
    }


}

