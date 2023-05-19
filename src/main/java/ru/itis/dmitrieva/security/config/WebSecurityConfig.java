package ru.itis.dmitrieva.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import javax.sql.DataSource;

//код представляет конфигурацию безопасности веб-приложения с использованием Spring Security.
// В классе WebSecurityConfig, помеченном аннотацией @EnableWebSecurity, мы настраиваем различные аспекты безопасности.


//настраивает основные аспекты безопасности в-п(аунтефикацию,
// авторизацию, контроль доступа к URL, настройки страниц и поддержку "Remember Me"
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    } //настройки аунтефикации, юзер, пароль, сравнение с данными из бд

    @Override
    protected void configure(HttpSecurity http) throws Exception { //настройки доступа к юрл и настройкам для них
                http.csrf().disable();

        http
                .rememberMe()
                .rememberMeParameter("rememberMe") //репозиторий для хранения токенов и время их действия
                .tokenRepository(tokenRepository())
                .tokenValiditySeconds(60 * 60 * 24 * 365)
                .and()
                .authorizeRequests()
                .antMatchers("/signUp").permitAll()
                .antMatchers("/signIn").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/profile/**").authenticated()
                .antMatchers("/classes/**").authenticated()
                .antMatchers("/accounts/**").hasAuthority("ADMIN")
                .antMatchers("/tasks/**").hasAuthority("ADMIN")
                .and()
                .formLogin()
                .loginPage("/signIn")
                .defaultSuccessUrl("/profile")
                .failureUrl("/signIn?error")
                .usernameParameter("email")
                .passwordParameter("password")
                .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/signIn?logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true);
    }
    //Кроме того, мы настраиваем страницу входа (/signIn),
    // страницу успешной аутентификации (/profile), страницу ошибки аутентификации (/signIn?error)
    // и страницу выхода (/logout

    @Bean
    public PersistentTokenRepository tokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }

}
