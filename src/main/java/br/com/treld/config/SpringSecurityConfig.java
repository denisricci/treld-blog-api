package br.com.treld.config;

import br.com.treld.exceptions.TreldException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

	@Autowired
	private CustomAuthenticationEntryPoint restAuthenticationEntryPoint;
	
	@Autowired
	private CustomAuthenticationFailureHandler restAuthenticationFailureHandler;

	@Autowired
	private MongoDBAuthenticationProvider authenticationProvider;

	@Override
	protected void configure(HttpSecurity http) throws TreldException {
		try{
			http.
					csrf().disable().
					exceptionHandling().
					authenticationEntryPoint(restAuthenticationEntryPoint).
					and().formLogin().
					successHandler(customAuthenticationSuccessHandler).
					failureHandler(restAuthenticationFailureHandler);
		}catch(Exception e){
			throw new TreldException(e);
		}
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws TreldException {
		try{
			auth.authenticationProvider(authenticationProvider);
		}catch(Exception e){
			throw new TreldException(e);
		}
	}
}