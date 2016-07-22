package br.com.treld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
@EnableSwagger2
@SuppressWarnings("PMD")
public class TreldBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(TreldBlogApplication.class, args);
	}
	
}
