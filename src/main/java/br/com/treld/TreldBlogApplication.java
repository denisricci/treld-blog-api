package br.com.treld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import br.com.treld.utils.CryptographyUtils;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
@EnableSwagger2
public class TreldBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(TreldBlogApplication.class, args);
		System.out.println(CryptographyUtils.encrypt("treld"));
	}
	
}
