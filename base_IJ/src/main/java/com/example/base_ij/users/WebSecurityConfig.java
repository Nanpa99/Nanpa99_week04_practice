//package com.example.base_ij.users;
//
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class WebSecurityConfig {
//
//    private final JwtUtil jwtUtil;
//
//    @Bean // 패스워드 암호화. 그냥 쓰면 뭐엿더라 파라미터 0 오류 엿나.. 저번에 찾아봣는데; 그거 해결책이엇나.
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//
//    @Bean // h2 연동 가능하게
//    public WebSecurityCustomizer ignoringCustomizer() {
//        return (web) -> web.ignoring().antMatchers("/h2-console/**");
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//
//        http.cors();
//        http.csrf().disable(); //(Cross Site Request Forgery) 사용자의 crud를
//    }
//
//
//}
//
//
//// 스프링시큐리티 Configuration Class를 작성하기 위해서는 WebSecurityConfigurerAdapter를 상속하여 클래스를 생성하고
//// @EnableWebSecurity 애노테이션을 추가합니다.
////스프링시큐리티의 각종 설정은 HttpSecurity로 한다
//// WebSecurity 스프링시큐리티 앞단의 설정들을 하는 객체이므로 HttpSecurity 설정한 스프링시큐리티 설정이 오버라이드 되는 설정이 있는 경우도 있다.
////기억해야될 부분은 스프링시큐리티의 설정은 HttpSecurity 하는 것 입니다.