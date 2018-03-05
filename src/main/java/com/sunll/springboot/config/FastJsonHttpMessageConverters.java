package com.sunll.springboot.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunll
 * 作用：springBoot集成FastJson完成覆盖的jackJson
 * on 2018/2/6
 */
@Configuration
public class FastJsonHttpMessageConverters extends WebMvcConfigurerAdapter {
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

        super.configureMessageConverters(converters);

        FastJsonHttpMessageConverter oFastConverter = new FastJsonHttpMessageConverter();

        FastJsonConfig oFastJsonConfig = new FastJsonConfig();
        oFastJsonConfig.setSerializerFeatures(
                SerializerFeature.PrettyFormat
        );
        oFastConverter.setFastJsonConfig(oFastJsonConfig);
        //处理中文乱码问题
        List<MediaType> oFastMediaTypeList = new ArrayList<>();
        oFastMediaTypeList.add(MediaType.APPLICATION_JSON_UTF8);
        oFastConverter.setSupportedMediaTypes(oFastMediaTypeList);

        converters.add(oFastConverter);
    }
}
