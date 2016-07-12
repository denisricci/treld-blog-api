package br.com.treld.config;

import br.com.treld.exceptions.TreldException;
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
	protected void configure(HttpSecurity http) throws TreldException {
		try{
			http.
					csrf().disable().
					exceptionHandling().
					authenticationEntryPoint(restAuthenticationEntryPoint).
					and().
					authorizeRequests().anyRequest().authenticated().and().formLogin().
					successHandler(customAuthenticationSuccessHandler).
					failureHandler(restAuthenticationFailureHandler);
		}catch(Exception e){
			throw new TreldException(e);
		}
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws TreldException {
		try{
			auth.inMemoryAuthentication().withUser("treld").password("treld").roles("USER");
		}catch(Exception e){
			throw new TreldException(e);
		}
	}
}