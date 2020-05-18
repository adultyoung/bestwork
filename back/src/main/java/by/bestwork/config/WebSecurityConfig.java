package by.bestwork.config;

import by.bestwork.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
//@EnableOAuth2Sso
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private final CustomAuthenticationSuccessHandler handler;

    public WebSecurityConfig(UserService userService, CustomAuthenticationSuccessHandler handler) {
        this.userService = userService;
        this.handler = handler;
    }

    @Bean
    BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf().disable()
//                .ignoringAntMatchers("/auth*")
//                .ignoringAntMatchers("/", "/login")
//                .ignoringAntMatchers("/registration")
//                .ignoringAntMatchers("/contract-request-api")
//                .ignoringAntMatchers("/request-user")
//                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                .and()
                .authorizeRequests()
                .antMatchers("/", "/registration/*", "/contract-request-api", "/request-user", "/login", "/order*", "viberapi").permitAll()
                .and().formLogin().loginPage("/login").permitAll().successHandler(handler).failureForwardUrl("/")
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth

                .userDetailsService(userService)
                .passwordEncoder(encoder());
    }


}
