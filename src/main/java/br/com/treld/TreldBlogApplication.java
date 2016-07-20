package br.com.treld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
@SuppressWarnings("PMD")
public class TreldBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(TreldBlogApplication.class, args);
	}
}