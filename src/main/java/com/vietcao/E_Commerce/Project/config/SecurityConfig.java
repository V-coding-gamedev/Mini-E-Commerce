package com.vietcao.E_Commerce.Project.config;



import com.vietcao.E_Commerce.Project.jwt.*; 
import com.vietcao.E_Commerce.Project.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;
import org.springframework.http.HttpMethod;

import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    DataSource dataSource;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;
    
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }
    
    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        // chỉ truy cập login.html mà ko cần đăng nhập, các API khác yêu cầu đăng nhập
        // http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
        
//        http.authorizeHttpRequests(authorizeRequests ->
//                authorizeRequests.requestMatchers("/login.html").permitAll()
//                        .requestMatchers("/log-in").permitAll()
//                        .anyRequest().authenticated());
//       
//        // ko lưu session trong server, mỗi request đều phải mang JWT Token (sử dụng token để kiểm tra thay vì qua session ID) 
//        http.sessionManagement(
//                session ->
//                        session.sessionCreationPolicy(
//                                SessionCreationPolicy.STATELESS)
//        );
//        
//        // Nếu token sai/hết hạn → gọi unauthorizedHandler → trả về lỗi 401 Unauthorized.
//        http.exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler));
//        
//        // Cho phép mở H2 console trong trình duyệt (iframe), tránh bị chặn bởi Spring Security.
//        http.headers(headers -> headers
//                .frameOptions(frameOptions -> frameOptions
//                        .sameOrigin()
//                )
//        );
//        
//        // Tắt bảo vệ CSRF (Cross-Site Request Forgery), vì API JWT session + cookie
//        http.csrf(csrf -> csrf.disable());
//        
//        // Tiến hành kiểm tra JWT token trước khi kiểm tra username - password
        http.addFilterBefore(authenticationJwtTokenFilter(),
                UsernamePasswordAuthenticationFilter.class);
//
//
//        return http.build();

        return http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(request -> request
                    .requestMatchers("/login.html").permitAll()
                    .requestMatchers(HttpMethod.POST, "/log-in").permitAll()
                    .requestMatchers(HttpMethod.POST, "/register").permitAll()
                    .requestMatchers(HttpMethod.GET, "/products").permitAll()
                    .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler)) // nếu có
                .addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    
    

    // UserDetailsService là interface trong Spring Security, dùng để tìm thông tin người dùng (username, password, roles) từ một nguồn nào đó (DB, memory, API…).
//    @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource) {
//        return new JdbcUserDetailsManager(dataSource); // JdbcUserDetailsManager sẽ chạy SQL dưới nền để lấy user từ bảng users và authorities (theo cấu trúc mặc định của Spring Security).
//    }

//    @Bean
//    public CommandLineRunner initData(UserDetailsService userDetailsService) {
//        return args -> {
//            JdbcUserDetailsManager manager = (JdbcUserDetailsManager) userDetailsService;
//            UserDetails user1 = User.withUsername("user1")
//                    .password(passwordEncoder().encode("password1"))
//                    .roles("USER")
//                    .build();
//            UserDetails admin = User.withUsername("admin")
//                    //.password(passwordEncoder().encode("adminPass"))
//                    .password(passwordEncoder().encode("adminPass"))
//                    .roles("ADMIN")
//                    .build();
//
//            JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
//            userDetailsManager.createUser(user1);
//            userDetailsManager.createUser(admin);
//        };
//    }

    @Bean
    public PasswordEncoder passwordEncoder(){
         return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

}
