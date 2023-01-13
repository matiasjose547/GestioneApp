package br.com.man.management.persistence.dao;

import br.com.man.management.persistence.daointerfaces.DAO;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.InjectionPoint;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.lang.reflect.ParameterizedType;

public class DAOFactory {
    @Inject
    private EntityManager em;

    @Produces
    @Dependent
    @SuppressWarnings(value = {"rawtypes, uncheced"})
    public <T> DAO<T> createDAO(InjectionPoint point) {
        ParameterizedType type = (ParameterizedType) point.getType();
        Class classe = (Class) type.getActualTypeArguments()[0];
        return new DAOImpl<T>(classe, em);

    }
}
