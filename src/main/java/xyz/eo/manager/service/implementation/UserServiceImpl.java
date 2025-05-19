package xyz.eo.manager.service.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.eo.manager.dto.model.userDto;
import xyz.eo.manager.dto.response.addUpdateUserResponse;
import xyz.eo.manager.entity.User;
import xyz.eo.manager.repository.UserRepository;
import xyz.eo.manager.service.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public addUpdateUserResponse addUpdateUser(userDto request) {
        // if(userRepository.findByUser(request.getUserName(), request.getEmail()) == null){
            User user = modelMapper.map(request, User.class);

            userRepository.save(user);
            return new addUpdateUserResponse("success", "User added successfully");
        // }
        // else{
        //     return new addUpdateUserResponse("failed", "User already exists");
        // }
    }
    
}
