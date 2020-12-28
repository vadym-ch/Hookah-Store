package com.kpi.internetshop.service;

import java.util.List;

public interface GenericService<T, K> {

    T get(K id);

    List<T> getAll();

    T update(T item);

    void delete(K id);
}
