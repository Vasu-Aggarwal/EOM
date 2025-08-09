package xyz.eo.manager.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.eo.manager.exception.ErrorMessageException;
import xyz.eo.manager.repository.UserRepository;

@Service
public class AuthenticateUserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
        return this.userRepository.findByEmailOrMobile(username).orElseThrow(() -> new ErrorMessageException("Username not found", 0));
    }
}
