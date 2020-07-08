package br.com.indra.avaliacao.apirest.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// Disable CSRF (cross site request forgery)
		http.csrf().disable();

		// No session will be created or used by spring security
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		// Entry points
		http.authorizeRequests()//
		.antMatchers("/h2/**/**").permitAll()
		.antMatchers("/v1/uploadFile").permitAll()
		.antMatchers("/v1/city/**").permitAll()
		.antMatchers("/v1/state/**").permitAll()
		.antMatchers("/v1/region/**").permitAll()
		.antMatchers("/v1/banner/**").permitAll()
		.antMatchers("/v1/distributor/**").permitAll()
		.antMatchers("/v1/fuelpricehistory/**").permitAll()
		.antMatchers("/v1/fuelpricehistoryIitem/**").permitAll()
		.antMatchers("/v1/product/**").permitAll()
		.antMatchers("/v1/user/**").permitAll()
		.antMatchers("/v1/downloadFile/**").permitAll()
		
		// Disallow everything else..
		.anyRequest().authenticated();

		// If a user try to access a resource without having enough permissions
		http.exceptionHandling().accessDeniedPage("/v1/user/signin");

		
		// Optional, if you want to test the API from a browser
		// http.httpBasic();
	}

	@Override
	public void configure(WebSecurity wceb) throws Exception {
		// Allow swagger to be accessed without authentication
		wceb.ignoring().antMatchers("/v2/api-docs")//
		.antMatchers("/swagger-resources/**")//
		.antMatchers("/swagger-ui.html")//
		.antMatchers("/configuration/**")//
		.antMatchers("/webjars/**")//
		.antMatchers("/public")

		// Un-secure H2 Database (for testing purposes, H2 console shouldn't be unprotected in production)
		.and()
		.ignoring()
		.antMatchers("/h2/**/**");;
		
			
	}
	
	
}
