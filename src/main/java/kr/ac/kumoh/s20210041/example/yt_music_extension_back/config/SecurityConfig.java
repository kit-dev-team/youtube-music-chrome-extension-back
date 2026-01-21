package kr.ac.kumoh.s20210041.example.yt_music_extension_back.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // 테스트 중인 API 경로를 추가해줘야 합니다.
                        .requestMatchers("/", "/login/**", "/api/v1/auth/**").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth2 -> oauth2
                        // 성공 시 우리 컨트롤러의 success 엔드포인트로 이동하게 설정
                        .defaultSuccessUrl("/api/v1/auth/success", true)
                );

        return http.build();
    }
}