package xyz.eo.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xyz.eo.manager.dto.model.userDto;
import xyz.eo.manager.dto.response.addUpdateUserResponse;
import xyz.eo.manager.service.UserService;
import xyz.eo.manager.util.endpoints.UserEndpoints;

@RestController
@RequestMapping(UserEndpoints.USER)
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(UserEndpoints.ADD_UPDATE_USER)
    public addUpdateUserResponse addUpdateUser(@RequestBody userDto request) {
        return userService.addUpdateUser(request);
        
    }
}
