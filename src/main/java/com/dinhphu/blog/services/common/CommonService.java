package com.dinhphu.blog.services.common;

import com.dinhphu.blog.model.root.RootClass;

import java.util.Set;

public interface CommonService<T extends RootClass,ID extends Number> {
    T save(T object);
    T update(T object, ID id);
    T delete(T object);
    T delete(ID id);
    Set<T> findAll();
    T findById(ID id);
}
