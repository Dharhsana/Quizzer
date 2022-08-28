package com.application;

import java.util.Arrays;

import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.filters.AuthenticationFilter;

@ComponentScan(basePackages = {"com.controller","com.serviceImpl","com.dao.impl"})
@EntityScan(basePackages = {"com.entity"})
@SpringBootApplication
public class QuizApp /*implements CommandLineRunner*/{

	@Autowired
    private ApplicationContext appContext;
	
	public static void main(String [] args){
		SpringApplication.run(QuizApp.class, args);
	}
	
	/* @Override
	    public void run(String... args) throws Exception {

	        String[] beans = ((ListableBeanFactory) appContext).getBeanDefinitionNames();
	        Arrays.sort(beans);
	        for (String bean : beans) {
	            System.out.println(bean);
	        }

	    }*/
	
	@Bean
	public FilterRegistrationBean<AuthenticationFilter> loggingFilter(){
		
	    FilterRegistrationBean<AuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
	        
	    registrationBean.setFilter(new AuthenticationFilter());
	    //registrationBean.addUrlPatterns("/api/user/logout","/api/user/update","/api/user/getByID/*","/api/user/getByName/*","/api/user/remove/*","/api/tutor");
	      registrationBean.addUrlPatterns("/api/user/*","/api/student/*","/api/tutor/*");  
	    return registrationBean;    
	}
}
