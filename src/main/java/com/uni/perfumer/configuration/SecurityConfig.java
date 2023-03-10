package com.uni.perfumer.configuration;

import com.uni.perfumer.jwt.JwtAccessDeniedHandler;
import com.uni.perfumer.jwt.JwtAuthenticationEntryPoint;
import com.uni.perfumer.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
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
   private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

   private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

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
                .formLogin().disable() // security ??? ???????????? ???????????? ??? ????????? ????????? ???????????? ?????????
                .httpBasic().disable() // ??? ???????????? id, pwd ????????? ???????????? ???????????? httpBasic ??? ???????????? ????????? ??????
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)//????????????
                .accessDeniedHandler(jwtAccessDeniedHandler)// ????????? ????????????
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)//Jwt??? ???????????? ????????? session??? stateless??? ????????????. stateless??? ?????? ??? Spring Security??? ????????? ???????????? ?????????.
                .and()
                .authorizeRequests()// http servletRequest ??? ???????????? ???????????? ?????? ??????????????? ??????
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/auth/**").permitAll()// ?????????, ???????????? API ??? ????????? ?????? ???????????? ????????? ???????????? ????????? permitAll ??????
          .antMatchers("/api/v1/boards/**").permitAll()
                .antMatchers("/api/v1/board/**").permitAll()
//                .antMatchers("/api/v1/reviews/**").permitAll()// ????????? ????????? ????????????
                .antMatchers("/api/**").hasAnyRole("USER", "ADMIN")  // ????????? API ??? ?????? ?????? ??????
                .and()
                .cors()// ??????????????? fetch mode : no-cors ????????? application/json ???????????? ????????? ?????????  // ???????????? cors ????????? ???????????? ???
                .and()
                .apply(new JwtSecurityConfig(tokenProvider));


        return http.build();

    }
    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();
        // ?????? React?????? ?????? ????????? CORS ???????????????.
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000" ));// ?????? ip??? ??????
        //configuration.setAllowedOrigins(Arrays.asList("http://43.200.33.166:3000" ));// ?????? ip??? ??????

        configuration.setAllowedMethods(Arrays.asList("GET", "PUT", "POST", "DELETE"));// ?????????????????????????????????
        configuration.setAllowedHeaders(Arrays.asList("Access-Control-Allow-Origin", "Content-Type", "Access-Control-Allow-Headers", "Authorization", "X-Requested-With"));// ?????? ????????? ???????????????
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    /**??????????????? ????????????, ?????????, ?????? ??? ???????????? Origin(??????) ?????? ??????.

     ??? ?????? ?????? ????????? ??? ?????? ????????? ????????? ?????????, ????????? ???????????? ???????????? Cross Origin, ??? ??????????????? ?????? ?????????.

     http://localhost:8080 : Spring Boot   //????????? ???????????? ???????????????  corsConfigurationSource() ????????? ?????????
     http://localhost:3000 : React
     ???????????? ?????????, ??????????????? ?????????????????? ????????? Cross Origin HTTP Request??? ????????????. ???, SOP(Same Origin Policy)??? ?????????.

     React??? Spring Boot??? port ??? ?????? ????????? ????????? cors ?????? ?????? ????????? ????????? ?????????.*/
}

