package com.dinhphu.blog.services;

import com.dinhphu.blog.exception.specific.EmailExistException;
import com.dinhphu.blog.exception.specific.UserExistException;
import com.dinhphu.blog.model.User;
import com.dinhphu.blog.model.dto.UserDTO;
import com.dinhphu.blog.services.common.CommonService;

public interface UserService extends CommonService<User,Long> {
    User register(UserDTO user) throws UserExistException, EmailExistException;
    User findByUsername(String username);
    User findByEmail(String email);

}
