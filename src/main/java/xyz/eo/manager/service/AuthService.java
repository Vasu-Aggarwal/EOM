package xyz.eo.manager.service;

import xyz.eo.manager.dto.request.user.AddUpdateUserRequest;
import xyz.eo.manager.dto.request.user.LoginRequest;
import xyz.eo.manager.dto.request.user.SignUpRequest;
import xyz.eo.manager.dto.response.user.LoginResponse;
import xyz.eo.manager.dto.response.user.SignUpResponse;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);
    SignUpResponse signUp(SignUpRequest signUpRequest);
}
