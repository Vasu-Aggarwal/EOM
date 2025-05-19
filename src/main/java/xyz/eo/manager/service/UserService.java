package xyz.eo.manager.service;

import xyz.eo.manager.dto.model.userDto;
import xyz.eo.manager.dto.response.addUpdateUserResponse;

// @Service
public interface UserService {
    addUpdateUserResponse addUpdateUser(userDto request);
}
