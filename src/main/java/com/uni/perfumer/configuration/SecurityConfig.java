package com.uni.perfumer.configuration;

import com.uni.perfumer.jwt.TokenProvider;
import com.uni.perfumer.member.model.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private  final TokenProvider tokenProvider;
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

//    @Bean
//    protected AuthenticationManager authConfigure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .userDetailsService(memberService).passwordEncoder(passwordEncoder)
//                .and()
//                .inMemoryAuthentication()
//                .withUser("user").password("{bcrypt}password").roles("USER")
//                .and()
//                .withUser("admin").password("{bcrypt}password").roles("USER", "ADMIN");
//
//                return auth.build();
//
//    }
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .antMatchers("/perfumeimgs/**");
    }
    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .exceptionHandling()
                //.authenticationEntryPoint(jwtAuthenticationEntryPoint)//인증실패
                //.accessDeniedHandler(jwtAccessDeniedHandler)// 엑세스 디나이시
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)//Jwt를 사용하기 때문에 session을 stateless로 설정한다. stateless로 설정 시 Spring Security는 세션을 사용하지 않는다.
                .and()
                .authorizeRequests()// http servletRequest 를 사용하는 요청들에 대한 접근제한을 설정
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/auth/**").permitAll()// 로그인, 회원가입 API 는 토큰이 없는 상태에서 요청이 들어오기 때문에 permitAll 설정
//                .antMatchers("/api/v1/products/**").permitAll()// 제품 누구나 접근가능
//                .antMatchers("/api/v1/reviews/**").permitAll()// 리뷰도 누구나 접근가능
                .antMatchers("/api/**").hasAnyRole("USER", "ADMIN")  // 나머지 API 는 전부 인증 필요
                .and()
                .cors()
                .and()
                .apply(new JwtSecurityConfig(tokenProvider));


        return http.build();

    }
    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();
        // 로컬 React에서 오는 요청은 CORS 허용해준다.
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000" ));// 해당 ip만 응답
        //configuration.setAllowedOrigins(Arrays.asList("http://43.200.33.166:3000" ));// 해당 ip만 응답

        configuration.setAllowedMethods(Arrays.asList("GET", "PUT", "POST", "DELETE"));// 해당메소드만응답하겠다
        configuration.setAllowedHeaders(Arrays.asList("Access-Control-Allow-Origin", "Content-Type", "Access-Control-Allow-Headers", "Authorization", "X-Requested-With"));// 해당 헤더의 응답만허용
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}

