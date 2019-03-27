package pl.coderslab.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
@ComponentScan("pl.coderslab")
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return passwordEncoder;
    }

    @Autowired
    private SimpleAuthenticationSuccessHandler successHandler;


    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/*").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/css/**", "/js/**", "/images/**", "/webjars/**", "/webapp/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/institution/**").hasAnyRole("ADMIN","USER")
                .antMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/donation/**").hasAnyRole("USER","ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").successHandler(successHandler).permitAll()
                .and().logout().logoutSuccessUrl("/").invalidateHttpSession(true);


        http.csrf().disable();
        http.headers().frameOptions().disable();
    }


}
