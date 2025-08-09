package xyz.eo.manager.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import xyz.eo.manager.dto.request.AddUpdateUserRequest;
import xyz.eo.manager.dto.response.GetUserPermissionsResponse;
import xyz.eo.manager.dto.response.addUpdateUserResponse;
import xyz.eo.manager.service.UserService;
import xyz.eo.manager.util.endpoints.UserEndpoints;

@RestController
@RequestMapping(UserEndpoints.USER)
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(UserEndpoints.ADD_UPDATE_USER)
    public addUpdateUserResponse addUpdateUser(HttpServletRequest roleId, @RequestBody AddUpdateUserRequest request) {
        return userService.addUpdateUser((Integer) roleId.getAttribute("roleId"), request);
    }

    @PostMapping(UserEndpoints.GET_USER_DETAILS +"/{userId}")
    public AddUpdateUserRequest getUserDetails(@Valid @PathVariable("userId") Long userid) {
        return userService.getUserDetail(userid);
    }

    @GetMapping(UserEndpoints.GET_USER_PERMISSIONS+"/{userId}")
    public GetUserPermissionsResponse getUserPermissions(@Valid @PathVariable("userId") Long userId) {
        return userService.getUserPermissions(userId);
    }
}
