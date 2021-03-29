package com.dinhphu.blog.services.impl;

import com.dinhphu.blog.dao.UserDao;
import com.dinhphu.blog.exception.specific.EmailExistException;
import com.dinhphu.blog.exception.specific.ObjectNotFoundException;
import com.dinhphu.blog.exception.specific.UserExistException;
import com.dinhphu.blog.model.User;
import com.dinhphu.blog.services.UserService;
import com.dinhphu.blog.services.common.CommonAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.dinhphu.blog.constant.ExceptionMessageConstant.EMAIL_EXIST_EXCEPTION;
import static com.dinhphu.blog.constant.ExceptionMessageConstant.USER_NAME_EXIST_EXCEPTION;

@Service
@Transactional
public class UserServiceImpl extends CommonAction<User,Long,UserDao>  implements UserService {

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        super(userDao);
    }

    @Override
    public User register(User user) throws UserExistException, EmailExistException {
        validateRegisterUser(user);

        return super.save(user);
    }

    private boolean validateRegisterUser(User newUser) throws UserExistException, EmailExistException {
        String newUsername= newUser.getUsername();
        String newEmail= newUser.getEmail();
        User userNameExist = findByUsername(newUsername);
        User emailExist=findByEmail(newEmail);

        if (emailExist != null){
            throw new EmailExistException(EMAIL_EXIST_EXCEPTION+" "+newEmail);
        }

        if (userNameExist != null){
            throw new UserExistException(USER_NAME_EXIST_EXCEPTION+" "+newUsername);
        }
        return true;
    }

    @Override
    public User findByUsername(String username) {
        com.dinhphu.blog.model.User foundUser = this.jpaRepository.findByUsername(username);
        if (foundUser != null){
            return foundUser;
        }else{
            return null;
        }
    }

    @Override
    public User findByEmail(String email) {
        User foundUser = this.jpaRepository.findByEmail(email);
        if (foundUser != null){
            return foundUser;
        }else{
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
       return super.findAllWithPage(page,size,sortBy);
    }


}
