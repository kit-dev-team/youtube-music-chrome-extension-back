package kr.ac.kumoh.s20210041.example.yt_music_extension_back.config;

import kr.ac.kumoh.s20210041.example.yt_music_extension_back.config.jwt.JwtAuthenticationFilter;
import kr.ac.kumoh.s20210041.example.yt_music_extension_back.config.jwt.OAuth2SuccessHandler;
import kr.ac.kumoh.s20210041.example.yt_music_extension_back.config.jwt.TokenProvider;
import kr.ac.kumoh.s20210041.example.yt_music_extension_back.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;
    private final TokenProvider tokenProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .formLogin(form -> form.disable())
                .httpBasic(basic -> basic.disable())

                // [수정 포인트] 세션 정책 변경
                // OAuth2 로그인 진행 시 세션이 아예 없으면 인증 요청을 유지할 수 없습니다.
                // STATELESS 대신 IF_REQUIRED를 사용하여 로그인 시점에만 세션을 허용합니다.
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                )

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/login/**", "/api/v1/auth/**", "/oauth2/**").permitAll() // /oauth2/** 추가
                        .anyRequest().authenticated()
                )

                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint(userInfo -> userInfo.userService(customOAuth2UserService))
                        .successHandler(oAuth2SuccessHandler)
                )

                .addFilterBefore(new JwtAuthenticationFilter(tokenProvider),
                        UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}