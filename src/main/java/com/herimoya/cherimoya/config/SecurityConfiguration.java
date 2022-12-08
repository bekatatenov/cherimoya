package com.herimoya.cherimoya.config;


import com.herimoya.cherimoya.enums.RoleStatus;
import com.herimoya.cherimoya.service.UserService;
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
class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${spring.queries.users-query}")
    private String usersQuery;
    @Value("${spring.queries.roles-query}")
    private String rolesQuery;
    @Autowired
    private UserService userService;
    @Autowired
    private DataSource dataSource;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Autowired
    CustomizeAuthenticationSuccessHandler customizeAuthenticationSuccessHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.
                jdbcAuthentication()
                .usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(rolesQuery)
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests()
                .antMatchers("/admin", "/delete-users", "/delete-users-by-email",
                        "/active-users", "/active-users-by-email", "/ban-users", "/ban-users-by-email", "/change-to-moder",
                        "/change-to-moder-by-email", "/change-to-user", "/change-to-user-by-email", "/change-to-recipient",
                        "/change-to-recipient-by-email").hasAnyAuthority(RoleStatus.ADMIN.name())
                .antMatchers( "/moder", "/delete-users", "/delete-users-by-email",
                        "/active-users", "/active-users-by-email", "/ban-users", "/ban-users-by-email", "/change-to-user",
                        "/change-to-user-by-email").hasAnyAuthority(RoleStatus.MODER.name())
                .antMatchers( "/post/add", "/post/edit-save", "/post/edit", "/post-remove-save", "/post/remove",
                        "/comment/add", "/comment/add-save", "/comment/edit", "/login",  "/profile", "/settings",
                        "/forgot-password", "/userPage").hasAnyAuthority(RoleStatus.USER.name(), RoleStatus.RECIPIENT.name())
                .antMatchers("/", "/home", "/home-main", "/login", "/registration", "/organization", "/news", "/post", "/post/**", "/comment",
                        "/comment/**", "/forgot-password").permitAll()
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
                .antMatchers("/resources/**", "/templates/**", "/static/**", "/registration");
    }


}