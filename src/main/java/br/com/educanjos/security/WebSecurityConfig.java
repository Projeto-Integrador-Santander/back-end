package br.com.educanjos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
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
        http.csrf().disable().authorizeRequests()
        
        .antMatchers().permitAll()
        .antMatchers("endereco/v1").hasAnyRole("ADMIN", "PROFESSOR", "ALUNO")
        .antMatchers("login/v1").hasAnyRole("ADMIN", "PROFESSOR", "ALUNO")
        .antMatchers("materia/v1").hasAnyRole("ADMIN", "PROFESSOR", "ALUNO")
        .antMatchers("perfil/v1").hasAnyRole("ADMIN", "PROFESSOR", "ALUNO")
        .antMatchers("agenda/v1").hasAnyRole("ADMIN", "PROFESSOR", "ALUNO")
        .antMatchers("pessoa/v1").hasAnyRole("ADMIN", "PROFESSOR", "ALUNO")
        .antMatchers("professor-materia/v1").hasAnyRole("ADMIN", "PROFESSOR", "ALUNO")
        
        .antMatchers().authenticated().and()
        .formLogin().permitAll().and()
        .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
    }
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws java.lang.Exception {
        auth.userDetailsService(userDetailsService)
        .passwordEncoder(new BCryptPasswordEncoder());
		
		auth.inMemoryAuthentication().withUser("admin").password("{noop}1234").roles("ADMIN");
        
    }
    
	@Override
    public void configure(WebSecurity web) throws java.lang.Exception {
    	
    }
    
}
