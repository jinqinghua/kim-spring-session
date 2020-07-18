package kim.springsession.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;
import org.springframework.session.security.web.authentication.SpringSessionRememberMeServices;

@EnableWebSecurity
public class SecurityConfiguration<S extends Session> extends WebSecurityConfigurerAdapter {

    @Autowired
    private FindByIndexNameSessionRepository<S> findByIndexNameSessionRepository;

    @Override
    public void configure(WebSecurity web) {
        // web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .requestMatchers(
                        //PathRequest.toStaticResources().atCommonLocations()
                ).permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic()//<1>
                .and()
                // https://docs.spring.io/spring-session/docs/current/reference/html5/index.html#spring-security-rememberme
                .rememberMe((rememberMe) -> rememberMe.rememberMeServices(rememberMeServices()))
                // https://docs.spring.io/spring-session/docs/current/reference/html5/index.html#spring-security-concurrent-sessions
                .sessionManagement((sessionManagement) -> sessionManagement.maximumSessions(1).sessionRegistry(sessionRegistry())
                );
    }

    @Bean
    public SpringSessionRememberMeServices rememberMeServices() {
        SpringSessionRememberMeServices rememberMeServices = new SpringSessionRememberMeServices();
        // optionally customize
        rememberMeServices.setAlwaysRemember(true);
        return rememberMeServices;
    }

    @Bean
    public SpringSessionBackedSessionRegistry<S> sessionRegistry() {
        return new SpringSessionBackedSessionRegistry<>(this.findByIndexNameSessionRepository);
    }

//    @Bean
//    public CookieSerializer cookieSerializer() {
//        DefaultCookieSerializer serializer = new DefaultCookieSerializer();
//        serializer.setCookieName("JSESSIONID");
//        serializer.setCookiePath("/");
//        serializer.setDomainNamePattern("^.+?\\.(\\w+\\.[a-z]+)$");
//        return serializer;
//    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser(User.withUsername("user").password("{noop}password").roles("USER").build())
                .withUser(User.withUsername("admin").password("{noop}password").roles("ADMIN", "USER").build());
    }

}
