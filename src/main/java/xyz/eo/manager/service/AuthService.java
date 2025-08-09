package xyz.eo.manager.service;

import xyz.eo.manager.dto.request.AddUpdateUserRequest;
import xyz.eo.manager.dto.response.LoginResponse;
import xyz.eo.manager.dto.response.SignUpResponse;

public interface AuthService {
    LoginResponse login(String username, String password);
    SignUpResponse signUp(AddUpdateUserRequest signUpRequest);
}
