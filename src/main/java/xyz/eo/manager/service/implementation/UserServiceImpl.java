package xyz.eo.manager.service.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import xyz.eo.manager.dto.request.UserDto;
import xyz.eo.manager.dto.response.addUpdateUserResponse;
import xyz.eo.manager.entity.User;
import xyz.eo.manager.exception.ErrorMessageException;
import xyz.eo.manager.repository.UserRepository;
import xyz.eo.manager.security.JwtHelper;
import xyz.eo.manager.service.UserService;

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

    @Override
    public addUpdateUserResponse addUpdateUser(Integer roleId, UserDto request) {
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
        User user1 = userRepository.findByUser(request.getUserName(), request.getEmail()).orElse(null);
        if (user1 == null) {
            request.setPassword(passwordEncoder.encode(request.getPassword()));
            User user = modelMapper.map(request, User.class);
            userRepository.save(user);
            return new addUpdateUserResponse("success", "User added successfully");
        } else {
            return new addUpdateUserResponse("failed", "User already exists");
        }
    }

    @Override
    public UserDto getUserDetail(Long userId) {
        User user = userRepository.findByUserId(userId).orElseThrow(() -> new ErrorMessageException("User Not Found", 404));
        return modelMapper.map(user, UserDto.class);
    }

}
