package br.com.man.management.persistence.config;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("local");

    @Produces
    @RequestScoped
    public EntityManager criarEntityManager()   {

        return emf.createEntityManager();
    }

    public void fecharEntityManager(@Disposes EntityManager em) {
        if (em != null && em.isOpen()) {
            em.close();
        }
    }

}
