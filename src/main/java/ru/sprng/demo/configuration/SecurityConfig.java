package ru.sprng.demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private DataSource dataSource;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{ //(1)
        auth.jdbcAuthentication().dataSource(dataSource);
    }
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception{ //(2)
//        User.UserBuilder user = User.withDefaultPasswordEncoder();
//        auth.inMemoryAuthentication()
//                .withUser(user.username("user1").password("pass1").roles("USER", "ADMIN"))
//                .withUser(user.username("user2").password("pass2").roles("USER"));
//    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/products/show/**").hasAnyRole("ADMIN")
                .anyRequest().permitAll()
                .and()
                .formLogin()
//                    .loginPage("/login")
//                    .loginProcessingUrl("authenticateTheUser")
                .permitAll();
    }
}
