package com.uni.perfumer.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${image.add-resource-locations}")
    private String ADD_RESOURCE_LOCATION;
    @Value("${image.add-resource-handler}")
    private String ADD_RESOURCE_HANDLER;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler(ADD_RESOURCE_HANDLER)
                .addResourceLocations("file://" +ADD_RESOURCE_LOCATION);//내 컴퓨터 c드라이브에 퍼퓸이미지 폴더가 생성됨
    }

}
