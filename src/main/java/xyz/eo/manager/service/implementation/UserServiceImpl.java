package xyz.eo.manager.service.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import xyz.eo.manager.dto.request.user.LoginRequest;
import xyz.eo.manager.dto.request.user.AddUpdateUserRequest;
import xyz.eo.manager.dto.response.user.GetUserPermissionsResponse;
import xyz.eo.manager.dto.response.user.LoginResponse;
import xyz.eo.manager.dto.response.user.addUpdateUserResponse;
import org.springframework.web.ErrorResponseException;
import xyz.eo.manager.entity.User;
import xyz.eo.manager.exception.ErrorMessageException;
import xyz.eo.manager.repository.UserRepository;
import xyz.eo.manager.security.JwtHelper;
import xyz.eo.manager.service.UserService;
import xyz.eo.manager.util.Permissions;

import static xyz.eo.manager.util.HierarchyCheckMap.checkHierarchy;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private AuthenticationManager manager;

    @Override
    public addUpdateUserResponse addUpdateUser(Integer roleId, AddUpdateUserRequest request) {

        if(!checkHierarchy(roleId, request.getRoleId())){
            throw new ErrorMessageException("You are not allowed to add/update user with roleId "+request.getRoleId(), 403);
        }

        if(request.getUserId() != null){
            User user = userRepository.findByUserId(request.getUserId()).orElseThrow(() -> new ErrorMessageException("User Not Found", 404));
            user.setName(request.getName());
            user.setEmail(request.getEmail());
            user.setMobile(request.getMobile());
            user.setPassword(request.getPassword());
            userRepository.saveAndFlush(user);
            return new addUpdateUserResponse("success", "User updated successfully");
        }

        if (userRepository.findByUser(request.getUserName(), request.getEmail()).orElse(null) == null) {
            request.setPassword(passwordEncoder.encode(request.getPassword()));
            User user = modelMapper.map(request, User.class);
            userRepository.save(user);
            return new addUpdateUserResponse("success", "User added successfully");
        } else {
            return new addUpdateUserResponse("failed", "User already exists");
        }
    }

    @Override
    public AddUpdateUserRequest getUserDetail(Long userId) {
        User user = userRepository.findByUserId(userId).orElseThrow(() -> new ErrorMessageException("User Not Found", 404));
        return modelMapper.map(user, AddUpdateUserRequest.class);
    }

    @Override
    public GetUserPermissionsResponse getUserPermissions(Long userId) {
        Permissions permissions = userRepository.findByUserId(userId)
               .orElseThrow(() -> new ErrorMessageException("User Not Found", 404))
               .getPermissions();
        return modelMapper.map(permissions, GetUserPermissionsResponse.class);
    }

}
