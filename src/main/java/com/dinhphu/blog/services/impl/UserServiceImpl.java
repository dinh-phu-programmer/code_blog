package com.dinhphu.blog.services.impl;

import com.dinhphu.blog.authority.ApplicationUserRole;
import com.dinhphu.blog.authority.UserPrincipal;
import com.dinhphu.blog.dao.UserDao;
import com.dinhphu.blog.exception.specific.EmailExistException;
import com.dinhphu.blog.exception.specific.ObjectNotFoundException;
import com.dinhphu.blog.exception.specific.UserExistException;
import com.dinhphu.blog.model.User;
import com.dinhphu.blog.model.dto.UserDTO;
import com.dinhphu.blog.services.UserService;
import com.dinhphu.blog.services.common.CommonAction;
import com.dinhphu.blog.util.UserTransferUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.dinhphu.blog.constant.ExceptionMessageConstant.*;

@Service("userService")
@Transactional
public class UserServiceImpl extends CommonAction<User, Long, UserDao> implements UserService, UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao,PasswordEncoder passwordEncoder) {
        super(userDao);
        this.passwordEncoder=passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = this.jpaRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(USER_NOT_FOUND_BY_ID + ": email");
        }
        user.setLastLoginDate(new Date());
        this.jpaRepository.save(user);
        UserPrincipal userPrincipal = new UserPrincipal(user);
        return userPrincipal;
    }

    @Override
    public User register(UserDTO userDTO) throws UserExistException, EmailExistException {
        validateRegisterUser(userDTO);
        //do logic register here
        userDTO.setActive(true);
        userDTO.setNotLocked(true);
        userDTO.setProfileImageUrl("");
        userDTO.setLastLoginDate(null);
        userDTO.setJoinDate(new Date());
        userDTO.setRole(ApplicationUserRole.NORMAL_USER.name());
        userDTO.setAuthorities(ApplicationUserRole.NORMAL_USER.getRoles());
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User user = UserTransferUtils.userDtoToUser(userDTO);
        return super.save(user);
    }

    private boolean validateRegisterUser(UserDTO newUser) throws UserExistException, EmailExistException {
        String newUsername = newUser.getUsername();
        String newEmail = newUser.getEmail();
        User userNameExist = findByUsername(newUsername);
        User emailExist = findByEmail(newEmail);

        if (emailExist != null) {
            throw new EmailExistException(EMAIL_EXIST_EXCEPTION + " " + newEmail);
        }

        if (userNameExist != null) {
            throw new UserExistException(USER_NAME_EXIST_EXCEPTION + " " + newUsername);
        }
        return true;
    }

    @Override
    public User findByUsername(String username) {
        com.dinhphu.blog.model.User foundUser = this.jpaRepository.findByUsername(username);
        if (foundUser != null) {
            return foundUser;
        } else {
            return null;
        }
    }

    @Override
    public User findByEmail(String email) {
        User foundUser = this.jpaRepository.findByEmail(email);
        if (foundUser != null) {
            return foundUser;
        } else {
            return null;
        }
    }

    @Override
    public User save(User object) {
        return super.save(object);
    }

    @Override
    public User update(User object, Long id) throws ObjectNotFoundException {
        return super.update(object, id);
    }

    @Override
    public User delete(User object) {
        return super.delete(object);
    }

    @Override
    public User delete(Long id) throws ObjectNotFoundException {
        return super.delete(id);
    }

    @Override
    public List<User> findAll() {
        return super.findAll();
    }

    @Override
    public User findById(Long id) throws ObjectNotFoundException {

        return super.findById(id);
    }

    @Override
    public Page<User> findAllWithPage(int page, int size, Optional<String> sortBy) {
        return super.findAllWithPage(page, size, sortBy);
    }


}
