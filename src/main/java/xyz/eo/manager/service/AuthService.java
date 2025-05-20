package xyz.eo.manager.service;

import xyz.eo.manager.dto.response.LoginResponse;

public interface AuthService {
    LoginResponse login(String username, String password);
}
