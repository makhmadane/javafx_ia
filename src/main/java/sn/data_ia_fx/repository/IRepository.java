package sn.data_ia_fx.repository;

import java.util.List;

public interface IRepository<T> {

    void insert(T t);
    void update(T t);
    void delete(int id);
    T findById(int id);
    List<T> findAll();
}
