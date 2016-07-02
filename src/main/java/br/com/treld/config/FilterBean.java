package br.com.treld.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.servlet.Filter;

@Configuration
public class FilterBean {

	@Bean
	public Filter optionsFIlter() {
		return new OptionsFilter();
	}
}
