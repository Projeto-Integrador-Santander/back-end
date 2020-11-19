package br.com.educanjos.infra.cors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class CorsConfig implements WebMvcConfigurer {
	@Override
    public void addCorsMappings( CorsRegistry registry ) {
        registry.addMapping("/**")        
        .allowedMethods("GET","POST","PUT","PATCH","DELETE") 
        .allowedOrigins("http://localhost:4200")
        .allowedHeaders("Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                "Access-Control-Request-Headers");
        
    }
}
