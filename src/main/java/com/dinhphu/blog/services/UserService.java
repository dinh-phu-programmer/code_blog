package com.dinhphu.blog.services;

import com.dinhphu.blog.model.User;
import com.dinhphu.blog.services.common.CommonService;

import java.util.Set;

public interface UserService extends CommonService<User,Long> {
    User register(User user);
    User findByUsername(String username);
    User findByEmail(String email);

}