package com.dinhphu.blog.services.common;

import com.dinhphu.blog.constant.ExceptionMessageConstant;
import com.dinhphu.blog.model.root.RootClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

import static com.dinhphu.blog.constant.ExceptionMessageConstant.*;

public abstract class CommonAction<T extends RootClass,ID extends Number> {

    private JpaRepository jpaRepository;

    public CommonAction(JpaRepository jpaRepository){
        this.jpaRepository=jpaRepository;
    }

    public T save(T object){
        return (T)jpaRepository.save(object);
    }

    public T update(T object, ID id){

        return null;
    }

    public T delete(T object){
        return null;
    }

    public  T delete(ID id){
        return null;
    }

    public Set<T> findAll(){
        return null;
    }

    public T findById(ID id){
        try {
            T current= (T) this.jpaRepository.findById(id).orElseThrow(
                    () -> new IllegalStateException(USER_NOT_FOUND_BY_ID +id)
            );
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }
}
