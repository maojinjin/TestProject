package com.saic.ebiz.smcc.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactory;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import freemarker.template.TemplateException;
import freemarker.template.utility.HtmlEscape;
import freemarker.template.utility.XmlEscape;

@Configuration
public class FreeMarkerViewResolverConfig {

    @Value("${ebiz.aic.resRoot}")
    private String restRoot;

    @Value("${ebiz.aic.uaaResRoot}")
    private String uaaRestRoot;

    /**
     * 
     * 功能描述: <br>
     * freemarker的视图配置
     *
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @Bean
    public FreeMarkerViewResolver viewResolver() {
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setCache(false);
        resolver.setViewClass(com.saic.framework.web.freemarker.CustomFreeMarkerView.class);
        resolver.setRequestContextAttribute("request");
        resolver.setExposeSpringMacroHelpers(true);
        resolver.setExposeRequestAttributes(true);
        resolver.setExposeSessionAttributes(true);
        resolver.setAllowSessionOverride(true);
        resolver.setViewNames("*.ftl", "*.html");
        // resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".html");
        resolver.setContentType("text/html; charset=UTF-8");
        return resolver;
    }

    /**
     * 
     * 功能描述: <br>
     * 配置模板属性
     *
     * @return
     * @throws IOException
     * @throws TemplateException
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @Bean
    public FreeMarkerConfigurer freemarkerConfig() throws IOException, TemplateException {
        FreeMarkerConfigurationFactory factory = new FreeMarkerConfigurationFactory();
        // factory.setTemplateLoaderPaths("classpath:/ftl/", "src/main/resources/ftl");
        // 模板加载路径
        factory.setTemplateLoaderPaths("/WEB-INF/", "/WEB-INF/views/", "/WEB-INF/auth_views/", "/html/");
        factory.setDefaultEncoding("UTF-8");
        // 全局变量部分
        Map<String,Object> variablesMap=new HashMap<>();
        variablesMap.put("lookup.scope.session", false);
        variablesMap.put("xml_escape", XmlEscape.class);
        variablesMap.put("html_escape", HtmlEscape.class);
        variablesMap.put("resRoot", restRoot);
        variablesMap.put("uaaResRoot", uaaRestRoot);
        //variablesMap.put("remoteUrl", RemoteSiteContent.);
        factory.setFreemarkerVariables(variablesMap);
        FreeMarkerConfigurer result = new FreeMarkerConfigurer();
        freemarker.template.Configuration configuration = factory.createConfiguration();
        configuration.setClassicCompatible(true);
        result.setConfiguration(configuration);
        // 设置模板属性
        Properties settings = new Properties();
        settings.put("template_update_delay", "0");
        settings.put("default_encoding", "UTF-8");
        settings.put("number_format", "#");
        settings.put("datetime_format", "yyyy-MM-dd HH:mm:ss");
        settings.put("date_format", "yyyy-MM-dd");
        settings.put("time_format", "HH:mm:ss");
        settings.put("tag_syntax", "auto_detect");
        settings.put("url_escaping_charset", "UTF-8");
        settings.put("whitespace_stripping", true);
        settings.put("classic_compatible", true);
        settings.put("template_exception_handler", "ignore");
        settings.put("locale", "zh-CN");
        result.setFreemarkerSettings(settings);
        return result;
    }

    @Bean
    public UrlBasedViewResolver jstlViewResolver() {
        UrlBasedViewResolver jstlViewResolver = new UrlBasedViewResolver();
        jstlViewResolver.setOrder(2);
        jstlViewResolver.setPrefix("/WEB-INF/jsp/");
        jstlViewResolver.setViewClass(org.springframework.web.servlet.view.JstlView.class);
        return jstlViewResolver;
    }

    /**
     * 
     * 功能描述: <br>
     * 设置multipartResolver才能完成文件上传
     *
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setDefaultEncoding("utf-8");
        multipartResolver.setMaxUploadSize(32505856);
        multipartResolver.setMaxInMemorySize(4096);
        return multipartResolver;
    }

    /**
     * 
     * 功能描述: <br>
     * 设置异常返回处理
     *
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @Bean
    public SimpleMappingExceptionResolver handlerExceptionResolver() {
        SimpleMappingExceptionResolver handlerExceptionResolver = new SimpleMappingExceptionResolver();
        handlerExceptionResolver.setDefaultErrorView("error/500.html");
        Properties mappings = new Properties();
        mappings.put("java.lang.Exception", "error/500.html");
        mappings.put("java.lang.Throwable", "error/500.html");
        handlerExceptionResolver.setExceptionMappings(mappings);
        Properties statusCodes = new Properties();
        statusCodes.put("errors/error", 500);
        statusCodes.put("errors/404", 404);
        handlerExceptionResolver.setStatusCodes(statusCodes);
        handlerExceptionResolver.setWarnLogCategory("WARN");
        handlerExceptionResolver.setDefaultStatusCode(500);
        return handlerExceptionResolver;
    }

    @Bean
    public FixedLocaleResolver localeResolver() {
        FixedLocaleResolver localResolver = new FixedLocaleResolver();
        Locale defaultLocale = new Locale("zh_CN");
        localResolver.setDefaultLocale(defaultLocale);
        return localResolver;
    }

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:i18n/messages");
        return messageSource;
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(messageSource());
        return validator;
    }


}
