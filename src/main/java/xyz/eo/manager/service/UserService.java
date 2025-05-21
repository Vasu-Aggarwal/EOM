package xyz.eo.manager.service;

import xyz.eo.manager.dto.request.GetUserDetailsRequest;
import xyz.eo.manager.dto.request.UserDto;
import xyz.eo.manager.dto.response.addUpdateUserResponse;

public interface UserService {
    addUpdateUserResponse addUpdateUser(Integer roleId, UserDto request);
    UserDto getUserDetail(Long request);

}
