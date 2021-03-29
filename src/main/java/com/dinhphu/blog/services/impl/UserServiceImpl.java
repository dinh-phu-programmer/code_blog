package com.dinhphu.blog.services.impl;

import com.dinhphu.blog.dao.UserDao;
import com.dinhphu.blog.exception.specific.ObjectNotFoundException;
import com.dinhphu.blog.model.User;
import com.dinhphu.blog.services.UserService;
import com.dinhphu.blog.services.common.CommonAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl extends CommonAction<User,Long,UserDao>  implements UserService {

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        super(userDao);
    }

    @Override
    public User register(User user) {
        return null;
    }

    @Override
    public User findByUsername(String username) {
        User foundUser= this.jpaRepository.findByUsername(username);
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
