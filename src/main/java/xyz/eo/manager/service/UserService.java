package xyz.eo.manager.service;

import xyz.eo.manager.dto.request.GetUserDetailsRequest;
import xyz.eo.manager.dto.request.LoginRequest;
import xyz.eo.manager.dto.request.AddUpdateUserRequest;
import xyz.eo.manager.dto.response.GetUserPermissionsResponse;
import xyz.eo.manager.dto.response.LoginResponse;
import xyz.eo.manager.dto.response.addUpdateUserResponse;

public interface UserService {
    addUpdateUserResponse addUpdateUser(Integer roleId, AddUpdateUserRequest request);
    AddUpdateUserRequest getUserDetail(Long request);
    LoginResponse login(LoginRequest request);
    GetUserPermissionsResponse getUserPermissions(Long userId);
}
