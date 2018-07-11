package com.gubang;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@EnableAutoConfiguration
@EnableConfigurationProperties
@Configuration
@SpringBootApplication
@EnableWebMvc
@ComponentScan(basePackages={"com"})
@MapperScan(basePackages = { "com.gubang.mapper" })
@ServletComponentScan
public class Application {
	
    public static void main(String[] args) throws Exception {
    	SpringApplication.run(Application.class, args);
    }

    @Bean
    RestTemplate getRestTemplate() {
    	return new RestTemplate();
    }
}