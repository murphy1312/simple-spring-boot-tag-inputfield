package eu.hulsch.simplespringboottaginputfield.web.app.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class ThymeleafConfig implements WebMvcConfigurer, ApplicationContextAware {

    ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    private ITemplateResolver springThymeleafTemplateResolver() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setPrefix("classpath:/templates/");
        resolver.setSuffix(".html");
        resolver.setCacheable(false);
        resolver.setApplicationContext(applicationContext);
        resolver.setOrder(1);
        return resolver;
    }

    private ITemplateResolver jsResolver() {
        SpringResourceTemplateResolver jsResolver = new SpringResourceTemplateResolver();
        jsResolver.setPrefix("classpath:/js/");
        jsResolver.setSuffix(".js");
        jsResolver.setCharacterEncoding("UTF-8");
        jsResolver.setCacheable(false);
        jsResolver.setTemplateMode(TemplateMode.JAVASCRIPT);
        jsResolver.setApplicationContext(applicationContext);
        jsResolver.setOrder(2);
        return jsResolver;
    }

    private ITemplateResolver txtResolver() {
        SpringResourceTemplateResolver txtResolver = new SpringResourceTemplateResolver();
        txtResolver.setPrefix("classpath:/templates/");
        txtResolver.setSuffix(".txt");
        txtResolver.setCharacterEncoding("UTF-8");
        txtResolver.setCacheable(false);
        txtResolver.setTemplateMode(TemplateMode.TEXT);
        txtResolver.setApplicationContext(applicationContext);
        txtResolver.setOrder(3);
        return txtResolver;
    }

    @Bean
    public ThymeleafViewResolver thymeleafViewResolver() {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(thymeleafTemplateEngine());
        resolver.setContentType("text/html");
        resolver.setOrder(1);
        resolver.setCache(false);
        resolver.setExcludedViewNames(new String[]{"*.js", "*.txt"});
        resolver.setApplicationContext(applicationContext);
        return resolver;
    }

    @Bean
    public ThymeleafViewResolver thymeleafJavascriptViewResolver() {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(thymeleafTemplateEngine());
        resolver.setContentType("text/javascript");
        resolver.setOrder(2);
        resolver.setCache(false);
        resolver.setApplicationContext(applicationContext);
        return resolver;
    }

    @Bean
    public ThymeleafViewResolver thymeleafTxtViewResolver() {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(thymeleafTemplateEngine());
        resolver.setContentType("text/plain");
        resolver.setOrder(3);
        resolver.setCache(false);
        resolver.setApplicationContext(applicationContext);
        return resolver;
    }

    @Bean
    public SpringTemplateEngine thymeleafTemplateEngine() {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        Set<ITemplateResolver> a = new HashSet<>();
        a.add(springThymeleafTemplateResolver());
        a.add(jsResolver());
        a.add(txtResolver());
        engine.setTemplateResolvers(a);
        engine.addDialect(new SpringSecurityDialect());
        return engine;
    }

}

