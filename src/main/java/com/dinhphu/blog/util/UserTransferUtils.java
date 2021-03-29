package com.dinhphu.blog.util;

import com.dinhphu.blog.model.User;
import com.dinhphu.blog.model.dto.UserDTO;

public class UserTransferUtils {
    public static User userDtoToUser(UserDTO userDTO){
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPassword(userDTO.getPassword());
        user.setUsername(userDTO.getUsername());
        user.setProfileImageUrl(userDTO.getProfileImageUrl());
        user.setActive(userDTO.isActive());
        user.setNotLocked(userDTO.isNotLocked());
        user.setRole(userDTO.getRole());
        user.setAuthorities(userDTO.getAuthorities());
        user.setJoinDate(userDTO.getJoinDate());
        user.setLastLoginDate(userDTO.getLastLoginDate());
        return user;
    }
}
