package com.dinhphu.blog.services.common;

import com.dinhphu.blog.exception.specific.ObjectNotFoundException;
import com.dinhphu.blog.model.root.RootClass;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface CommonService<T extends RootClass,ID extends Number> {
    T save(T object);
    T update(T object, ID id) throws ObjectNotFoundException;
    T delete(T object);
    T delete(ID id) throws ObjectNotFoundException;
    List<T> findAll();
    T findById(ID id) throws ObjectNotFoundException;
    Page<T> findAllWithPage(int page, int size, Optional<String> sortBy);
}
