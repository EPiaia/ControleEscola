package com.br.educacional.services;

import java.util.List;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Piaia
 */
public abstract class BaseService<T> {

    @PersistenceContext(name = "BD_ESCOLA")
    protected EntityManager em;

    public EntityManager getEntityManager() {
        return em;
    }

    public T save(T obj) {
        return getEntityManager().merge(obj);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void delete(Class<T> classe, Object pk) {
        T objectManaged = getEntityManager().find(classe, pk);
        getEntityManager().remove(objectManaged);
    }

    public void executeNativeUpdate(String query) {
        Query q = em.createNativeQuery(query);
        q.executeUpdate();
    }

    public List<Object> executeNativeQuery(String query) {
        Query q = em.createNativeQuery(query);
        return q.getResultList();
    }

    public List executeNativeQuery(Class<?> classe, String query) {
        Query q = em.createNativeQuery(query, classe);
        return q.getResultList();
    }
}
