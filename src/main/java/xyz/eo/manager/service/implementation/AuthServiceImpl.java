package xyz.eo.manager.service.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.eo.manager.dto.request.AddUpdateUserRequest;
import xyz.eo.manager.dto.response.LoginResponse;
import xyz.eo.manager.dto.response.SignUpResponse;
import xyz.eo.manager.entity.User;
import xyz.eo.manager.repository.UserRepository;
import xyz.eo.manager.service.AuthService;

public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public LoginResponse login(String username, String password) {
        return null;
    }

    @Override
    public SignUpResponse signUp(AddUpdateUserRequest signUpRequest) {
        User user = modelMapper.map(signUpRequest, User.class);
        userRepository.save(user);
        return SignUpResponse.builder()
                .message("Sign up successfully")
                .status(200)
                .build();
    }
}
