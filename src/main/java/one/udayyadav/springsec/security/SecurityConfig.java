package one.udayyadav.springsec.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomOidcUserService customOidcUserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .oauth2Login()
                .userInfoEndpoint().oidcUserService(customOidcUserService).and()
                .defaultSuccessUrl("/", true)
                .and()
                .logout(logout -> logout.logoutSuccessUrl("/").deleteCookies("JSESSIONID"))
                .build();
    }

}

//        return http
//                .authorizeHttpRequests()
//                .requestMatchers("/").permitAll().anyRequest().authenticated()
//                .and()
//                .oauth2Login().defaultSuccessUrl("/", true).and()
//                .oauth2Login(oauth2 -> oauth2.userInfoEndpoint(userinfo -> userinfo.oidcUserService(customOidcUserService)))
//                .logout(logout -> logout.logoutSuccessUrl("/s").deleteCookies("JSESSIONID"))
//                .build();