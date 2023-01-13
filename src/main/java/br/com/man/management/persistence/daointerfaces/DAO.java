package br.com.man.management.persistence.daointerfaces;


import java.io.Serializable;
import java.util.List;

public interface DAO<T> extends Serializable {
    T save(T entity);

    T update(T entity);

    List<T> listAll();


}