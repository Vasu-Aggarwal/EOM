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
    private AuthenticationManager manager;

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(AuthEndpoints.LOGIN)
    public LoginResponse login(@RequestBody LoginRequest request){
        doAuthenticate(request.getUsername(), request.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        User user = userRepo.findByEmail(request.getUsername()).isPresent() ? userRepo.findByEmail(request.getUsername()).get() : userRepo.findByMobile(request.getUsername()).orElseThrow(() -> new BadCredentialsException("Invalid username or password!!"));

//        if(user == null){
//            throw new BadCredentialsException("Invalid username or password!!");
//        }
        String token = jwtHelper.generateToken(userDetails, user.getRoleId(), user.getUserId());
        LoginResponse loginResponse = LoginResponse.builder()
                .message("User successfully authenticated")
                .status(200)
                .token(token)
                .build();
        return loginResponse;
    }

    public void doAuthenticate(String username, String password){
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
        try{
            manager.authenticate(authentication);
        }catch (BadCredentialsException e){
            throw new BadCredentialsException("Invalid username or password in doAuthenticate !!");
        }
    }

}
