package xyz.eo.manager.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.eo.manager.dto.request.LoginRequest;
import xyz.eo.manager.dto.response.LoginResponse;
import xyz.eo.manager.entity.User;
import xyz.eo.manager.repository.UserRepository;
import xyz.eo.manager.security.JwtHelper;
import xyz.eo.manager.service.UserService;
import xyz.eo.manager.util.endpoints.AuthEndpoints;

@RestController
@RequestMapping(AuthEndpoints.AUTH)
public class AuthController {


    @Autowired
    private UserService userService;

    @PostMapping(AuthEndpoints.LOGIN)
    public LoginResponse login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }



}
