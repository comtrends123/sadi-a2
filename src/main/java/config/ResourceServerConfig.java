package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId("resource-id");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/questions/**").hasRole("ADMIN")
                .antMatchers("/answers/**").hasRole("ADMIN")
                .antMatchers("/questionAnswerss/**").hasRole("ADMIN")
                .antMatchers("/results/**").hasRole("ADMIN")
                .antMatchers("/resultAnswerss/**").hasRole("ADMIN")
                .antMatchers("/userInfoss/**").hasRole("ADMIN")
                .antMatchers("/userInfoResultServices/**").hasRole("ADMIN");
    }

}