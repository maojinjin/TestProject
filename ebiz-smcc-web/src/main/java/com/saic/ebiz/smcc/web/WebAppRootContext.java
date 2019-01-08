package com.saic.ebiz.smcc.web;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import com.ibm.framework.web.sitemesh.SitemeshFreemarkerDecoratorServlet;
import com.opensymphony.module.sitemesh.filter.PageFilter;
import com.saic.framework.session.filter.DdsSessionCooikeFilter;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class WebAppRootContext {

    // @Bean
    // public ServletContextInitializer initializer() {
    // return new ServletContextInitializer() {
    //
    // @Override
    // public void onStartup(ServletContext servletContext) throws ServletException {
    // servletContext.setInitParameter("p-name", "-value");
    // }
    // };
    // }

    /**
     * 功能描述: <br>
     * dispatcherServlet配置
     *
     * @param dispatcherServlet
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @Bean
    public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
        ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet);
        registration.addUrlMappings("*.htm");
        registration.addUrlMappings("*.ajax");
        registration.addUrlMappings("*.json");
        registration.addInitParameter("contextConfigLocation", "classpath:conf/spring/spring-servlet.xml");
        return registration;
    }

    /**
     * 功能描述: <br>
     * dds过滤器
     *
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @Bean
    public FilterRegistrationBean ddsFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new DdsSessionCooikeFilter());
        registration.addUrlPatterns("*.htm", "*.ajax", "*.json");
        return registration;
    }

    /**
     * 功能描述: <br>
     * springSecurity过滤器
     *
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    // @Bean
    // public FilterRegistrationBean delegateFilterRegistration() {
    // FilterRegistrationBean registration = new FilterRegistrationBean(new DelegatingFilterProxy());
    // registration.setName("delegatingFilterProxy");
    // registration.addUrlPatterns("*.htm");
    // registration.addInitParameter("targetFilterLifecycle", "true");
    // return registration;
    // }

    /**
     * 功能描述: <br>
     * utf8 code
     *
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @Bean
    public FilterRegistrationBean characterFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new CharacterEncodingFilter());
        registration.addInitParameter("encoding", "utf-8");
        registration.addInitParameter("forceEncoding", "true");
        registration.addServletNames("sample");
        return registration;
    }

    /**
     * 功能描述: <br>
     * sitemesh 过滤器
     *
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @SuppressWarnings("deprecation")
    @Bean
    public FilterRegistrationBean sitemeshFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new PageFilter());
        registration.addUrlPatterns("/*");
        return registration;
    }

    /**
     * 功能描述: <br>
     * sitemesh-freemarker
     *
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @Bean
    public ServletRegistrationBean sitemeshFreemarkerServletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new SitemeshFreemarkerDecoratorServlet());
        registration.addInitParameter("TemplatePath", "/");
        registration.addInitParameter("NoCache", "true");
        registration.addInitParameter("default_encoding", "UTF-8");
        registration.addUrlMappings("*.html", "*.ftl");
        registration.setLoadOnStartup(2);
        return registration;
    }

}