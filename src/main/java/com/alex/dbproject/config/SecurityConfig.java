package com.alex.dbproject.config;

import com.alex.dbproject.domain.UserDao;
import com.alex.dbproject.model.User;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDao userDao;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(passwordEncoder());
        authProvider.setUserDetailsService(userDetailsService());
        return authProvider;
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetailsService detailsService = new UserDetailsService() {

            @Override
            public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
                User user = null;
                try {
                    user = userDao.loadByUsername(string);
                } catch (Exception ex) {
                    System.out.println("user not found or error");
                    throw new UsernameNotFoundException("can't find user@username" + string);
                }
                if (user == null) {
                    throw new UsernameNotFoundException("can't find user@username" + string);
                }
                return (UserDetails) user;
            }
        };
        return detailsService;
    }

    @Bean
    public AuthenticationDetailsSource authenticationDetailsSource() {
        return new WebAuthenticationDetailsSource() {
            @Override
            public WebAuthenticationDetails buildDetails(HttpServletRequest context) {
                return new WebAuthenticationDetails(context);
            }
        };

    }

    @Bean
    public AuthenticationTrustResolver getAuthenticationTrustResolver() {
        return new AuthenticationTrustResolverImpl();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/user/**").access("hasRole('USER')")
                .antMatchers("/admin/**").access("hasRole('ADMIN')")
                .and().formLogin().loginPage("/login").permitAll()
                .defaultSuccessUrl("/check", false)
                .usernameParameter("email")
                .and().logout().logoutUrl("/logout").permitAll();
        http.formLogin();
        http.cors();
        http.headers().frameOptions().disable();
        http.headers().httpStrictTransportSecurity().disable();
    }
}
