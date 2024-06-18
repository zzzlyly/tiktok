package com.cdut.tiktok.user.config;

/**
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests(authorize -> authorize
                        .antMatchers("/user/user/register").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin().permitAll();
        return http.build();
    }
}
*/