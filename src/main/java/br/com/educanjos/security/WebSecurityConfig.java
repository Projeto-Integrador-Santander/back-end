package br.com.educanjos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	ImplementsUserDetailsService userDetailsService;
	
	@Override
    protected void configure(HttpSecurity http) throws java.lang.Exception {
        http.csrf().disable().antMatcher("/login/v1/atualiza-senha/**").authorizeRequests()
        
        .antMatchers(HttpMethod.GET).hasAnyRole("ADMIN", "USER")
        .antMatchers(HttpMethod.POST).hasAnyRole("ADMIN", "USER")
        .antMatchers(HttpMethod.DELETE).hasRole("ADMIN")
        .antMatchers(HttpMethod.PATCH).hasRole("ADMIN")
        .antMatchers(HttpMethod.PUT).hasRole("ADMIN")
        
        .anyRequest().authenticated().and()
        .httpBasic().and()
        .formLogin().permitAll().and()
        .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
    }
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws java.lang.Exception {
        auth.userDetailsService(userDetailsService)
        .passwordEncoder(new BCryptPasswordEncoder());
		
		auth.inMemoryAuthentication().withUser("admin").password("{noop}1234").roles("ADMIN");
        
    }
    
}
