package br.com.man.management.persistence.dao;

import br.com.man.management.persistence.daointerfaces.DAO;
import lombok.extern.slf4j.Slf4j;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DAOImpl<T> implements DAO<T> {
    @PersistenceContext
    private EntityManager em;
    private final Class<T> classe;


    public DAOImpl(Class<T> classe, EntityManager em) {
        this.classe = classe;
        this.em = em;
    }

    @Override
    public T save(T entity) {
        em.persist(entity);
        em.flush();
        return entity;
    }

    @Override
    public T update(T entity) {
        em.merge(entity);
        em.flush();
        return entity;
    }

    @Override
    public List<T> listAll() {
        StringBuilder sb = new StringBuilder();
        sb.append("Select e from ").append(this.classe.getSimpleName()).append(" e");
        TypedQuery<T> query = em.createQuery(sb.toString(), this.classe);
        return query.getResultList();
    }


}
