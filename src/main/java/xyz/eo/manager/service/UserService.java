package xyz.eo.manager.service;

import xyz.eo.manager.dto.request.user.LoginRequest;
import xyz.eo.manager.dto.request.user.AddUpdateUserRequest;
import xyz.eo.manager.dto.response.user.GetUserPermissionsResponse;
import xyz.eo.manager.dto.response.user.LoginResponse;
import xyz.eo.manager.dto.response.user.addUpdateUserResponse;

public interface UserService {
    addUpdateUserResponse addUpdateUser(Integer roleId, AddUpdateUserRequest request);
    AddUpdateUserRequest getUserDetail(Long request);
    GetUserPermissionsResponse getUserPermissions(Long userId);
}
