package com.dinhphu.blog.services.common;

import com.dinhphu.blog.constant.ExceptionMessageConstant;
import com.dinhphu.blog.exception.specific.UserNotFoundException;
import com.dinhphu.blog.model.root.RootClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

import static com.dinhphu.blog.constant.ExceptionMessageConstant.*;

public abstract class CommonAction<T extends RootClass, ID extends Number, E extends JpaRepository> {

    protected E jpaRepository;

    public CommonAction(E jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

//    public abstract void setJpaRepository(E obj);

    public T save(T object) {
        return (T) jpaRepository.save(object);
    }

    public T update(T object, ID id) {
        T current =findById(id);
        Long currentID= current.getId();
        current=object;
        current.setId(currentID);
        return (T) jpaRepository.save(current);
    }

    public T delete(T object) {
        this.jpaRepository.delete(object);
        return object;
    }

    public T delete(ID id) {
        T current= findById(id);
        this.jpaRepository.deleteById(id);
        return current;
    }

    public List<T> findAll() {
        return this.jpaRepository.findAll();
    }

    public T findById(ID id) {
        T current=null;
        try {
            current = (T) this.jpaRepository.findById(id).orElseThrow(
                    () -> new UserNotFoundException(USER_NOT_FOUND_BY_ID + id)
            );
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return current;
    }
}
