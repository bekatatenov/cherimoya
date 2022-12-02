package com.cherimoya.cherimoya.config;


import com.cherimoya.cherimoya.enums.RoleStatus;
import com.cherimoya.cherimoya.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Autowired
    private DataSource dataSource;



    @Value("${spring.queries.users-query}")
    private String usersQuery;



    @Value("${spring.queries.roles-query}")
    private String rolesQuery;



    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }



    @Autowired
    CustomizeAuthenticationSuccessHandler customizeAuthenticationSuccessHandler;



    @Override
    protected void configure(AuthenticationManagerBuilder builder)
            throws Exception {
        builder.userDetailsService(userService);
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {



        http.
                authorizeRequests()
                .antMatchers("/","/admin").hasAnyAuthority(RoleStatus.ADMIN.name())
                .antMatchers("/","/moder").hasAnyAuthority(RoleStatus.MODER.name())
                .antMatchers("/","user").hasAnyAuthority(RoleStatus.USER.name())
                .antMatchers("/", "/login","/registration","hello","/delete-users","/delete-users-by-email").permitAll()
                .anyRequest()
                .authenticated()
                .and().csrf().disable()
                .formLogin().successHandler(customizeAuthenticationSuccessHandler)
                .loginPage("/login").failureUrl("/login?error=true")
                .usernameParameter("email")
                .passwordParameter("password")
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/").and().exceptionHandling()
                .accessDeniedPage("/access-denied");
    }



    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**","/templates/**" ,"/static/**", "/registration");
    }



}