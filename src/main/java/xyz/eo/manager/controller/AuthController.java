package xyz.eo.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.eo.manager.dto.request.user.LoginRequest;
import xyz.eo.manager.dto.request.user.SignUpRequest;
import xyz.eo.manager.dto.response.user.LoginResponse;
import xyz.eo.manager.dto.response.user.SignUpResponse;
import xyz.eo.manager.service.AuthService;
import xyz.eo.manager.service.UserService;
import xyz.eo.manager.util.endpoints.AuthEndpoints;

@RestController
@RequestMapping(AuthEndpoints.AUTH)
public class AuthController {


    @Autowired
    private AuthService authService;

    @PostMapping(AuthEndpoints.LOGIN)
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return new ResponseEntity<>(authService.login(request), HttpStatus.OK);
    }

    @PostMapping(AuthEndpoints.SIGN_UP)
    public SignUpResponse signUp(@RequestBody SignUpRequest request) {
        return authService.signUp(request);
    }

}
