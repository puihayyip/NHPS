package com.team42.NHPS.api.users.service;

import com.team42.NHPS.api.users.shared.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsersService extends UserDetailsService {
    UserDTO createUser(UserDTO userDTO);
    UserDTO getUserDetailsByEmailAddress(String emailAddress);

    UserDTO getUserByUserId(String userId);
}
