package peaksoft.laptop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.concurrent.TimeUnit;
@Configuration
@EnableWebSecurity
public class LaptopSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public LaptopSecurityConfig(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
//                .antMatchers("/", "index", "/css*", "/js*").permitAll()
//
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
//                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/api/v1/laptops", true)
                .passwordParameter("password")
                .usernameParameter("username")

                .and()
                .rememberMe()
                .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))
                .key("SomeThingVerySecured")
                .rememberMeParameter("remember-me")

                .and()
                .logout()
                .logoutUrl("/logout")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID", "remember-me")
                .logoutSuccessUrl("/login")

                .and()
                .httpBasic();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

}
