package pres.jeremy.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import pres.jeremy.security.filter.AuthenticationSuccessHandler;
import pres.jeremy.security.filter.MyAuthenticationFailureHandler;
import pres.jeremy.security.filter.VerificationCodeFilter;

import javax.servlet.http.HttpServletRequest;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> authenticationDetailsSource;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .antMatchers("/customer/**").hasAuthority("CUSTOMER")
                .antMatchers("/app/**", "/captcha.jpg").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .authenticationDetailsSource(authenticationDetailsSource)
                .loginPage("/myLogin.html")
                .successHandler(authenticationSuccessHandler())
                .failureHandler(myAuthenticationFailureHandler())
                .loginProcessingUrl("/auth/form").permitAll()
                .failureHandler(new MyAuthenticationFailureHandler());
//        http.addFilterBefore(new VerificationCodeFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public static AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new AuthenticationSuccessHandler();
    }

    @Bean
    public static MyAuthenticationFailureHandler myAuthenticationFailureHandler() {
        return new MyAuthenticationFailureHandler();
    }
}