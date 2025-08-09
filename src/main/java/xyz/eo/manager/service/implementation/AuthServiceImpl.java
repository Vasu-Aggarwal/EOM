package xyz.eo.manager.service.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import xyz.eo.manager.dto.request.user.LoginRequest;
import xyz.eo.manager.dto.request.user.SignUpRequest;
import xyz.eo.manager.dto.response.user.LoginResponse;
import xyz.eo.manager.dto.response.user.SignUpResponse;
import xyz.eo.manager.entity.User;
import xyz.eo.manager.repository.UserRepository;
import xyz.eo.manager.security.JwtHelper;
import xyz.eo.manager.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public LoginResponse login(LoginRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();
        doAuthenticate(username, password);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        User user = userRepo.findByEmailOrMobile(username)
                .orElseThrow(() -> new BadCredentialsException("Invalid username or password!!"));
        String token = jwtHelper.generateToken(userDetails, user.getRoleId(), user.getUserId());
        return LoginResponse.builder()
                .message("User successfully authenticated")
                .status(200)
                .token(token)
                .build();
    }

    @Override
    public SignUpResponse signUp(SignUpRequest signUpRequest) {
        User user = modelMapper.map(signUpRequest, User.class);
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        userRepository.save(user);
        return SignUpResponse.builder()
                .message("Sign up successfully")
                .status(200)
                .build();
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
