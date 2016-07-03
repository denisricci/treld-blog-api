package br.com.treld.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

	@Autowired
	private CustomAuthenticationEntryPoint restAuthenticationEntryPoint;
	
	@Autowired
	private CustomAuthenticationFailureHandler restAuthenticationFailureHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.
			csrf().disable().
			exceptionHandling().
			authenticationEntryPoint(restAuthenticationEntryPoint).
			and().
			authorizeRequests().anyRequest().authenticated().and().formLogin().
			successHandler(customAuthenticationSuccessHandler).
			failureHandler(restAuthenticationFailureHandler);

	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("treld").password("treld").roles("USER");
	}
}