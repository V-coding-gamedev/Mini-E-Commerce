package com.vietcao.E_Commerce.Project.controllers;

//import com.example.securitydemo.jwt.JwtUtils;
//import com.example.securitydemo.jwt.LoginRequest;
//import com.example.securitydemo.jwt.LoginResponse;
import com.vietcao.E_Commerce.Project.jwt.JwtUtils;
import com.vietcao.E_Commerce.Project.jwt.LoginRequest;
import com.vietcao.E_Commerce.Project.jwt.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.RequestParam;

@RestController 
public class LoginController {
    
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/user")
    public String userEndpoint(){
        return "Hello, User!";
    }
    
    @PostMapping("/log-in") // để trả về thông tin người dùng và token 
    public ResponseEntity<?> authenticateUser(@RequestParam String username, @RequestParam String password) {
        Authentication authentication;
        try {
            // Luồng xác thực: AuthenticationManager gọi DaoAuthenticationProvider, 
            // provider dùng CustomUserDetailsService.loadUserByUsername() lấy UserDetails từ DB,
            // so sánh password và trả về Authentication object nếu hợp lệ.
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (AuthenticationException exception) {
            Map<String, Object> map = new HashMap<>();
            map.put("message", "Bad credentials");
            map.put("status", false);
            map.put("username", username); 
            map.put("password", password);         
            return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String jwtToken = jwtUtils.generateTokenFromUsername(userDetails);

        // Lấy ra role đầu tiên của user từ danh sách authorities.
        // userDetails.getAuthorities() trả về Collection<GrantedAuthority>
        // → stream() duyệt qua collection
        // → findFirst() lấy phần tử đầu tiên (nếu có)
        // → map(GrantedAuthority::getAuthority) lấy tên role dưới dạng String
        // → orElse(null) trả về null nếu user không có role nào
        String role = userDetails.getAuthorities().stream()
                .findFirst().map(GrantedAuthority :: getAuthority)
                .orElse(null); 

        LoginResponse response = new LoginResponse(userDetails.getUsername(), role, jwtToken);

        return ResponseEntity.ok(response);
    }
}
