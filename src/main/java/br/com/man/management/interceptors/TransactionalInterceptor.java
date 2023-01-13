package br.com.man.management.interceptors;

import br.com.man.management.annotations.Transactional;
import lombok.extern.slf4j.Slf4j;

import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.io.Serializable;

@Interceptor
@Transactional
public class TransactionalInterceptor implements Serializable {

    @Inject
    private EntityManager em;

    @AroundInvoke
    public Object invoke(InvocationContext context) throws Exception{
        EntityTransaction transaction = em.getTransaction();
        Object result = null;
        try {
            if(!transaction.isActive()){
                transaction.begin();
                result = context.proceed();
                if(!transaction.getRollbackOnly()){
                    transaction.commit();
                } else {
                    transaction.rollback();
                }
            }
        } catch (Exception e){
            e.printStackTrace();
            e.getLocalizedMessage();
            if (transaction.isActive()){
                transaction.rollback();
            }
        }
        return result;
    }
}
