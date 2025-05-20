package xyz.eo.manager.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import xyz.eo.manager.dto.request.GetUserDetailsRequest;
import xyz.eo.manager.dto.request.UserDto;
import xyz.eo.manager.dto.response.addUpdateUserResponse;
import xyz.eo.manager.service.UserService;
import xyz.eo.manager.util.endpoints.UserEndpoints;

@RestController
@RequestMapping(UserEndpoints.USER)
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(UserEndpoints.ADD_UPDATE_USER)
    public addUpdateUserResponse addUpdateUser(@RequestBody UserDto request) {
        return userService.addUpdateUser(request);
    }

    @PostMapping(UserEndpoints.GET_USER_DETAILS +"/{userId}")
    public UserDto getUserDetails(@Valid @PathVariable("userId") Long userid) {
        return userService.getUserDetail(userid);
    }
}
