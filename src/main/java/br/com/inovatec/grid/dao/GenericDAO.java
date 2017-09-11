/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.dao;

import br.com.inovatec.grid.dao.connection.ConnectionFactory;
import br.com.inovatec.grid.dao.exceptions.CreateEntityException;
import br.com.inovatec.grid.dao.exceptions.EntityNotFoundException;
import br.com.inovatec.grid.dao.exceptions.ListEntityException;
import br.com.inovatec.grid.dao.exceptions.PersistEntityException;
import br.com.inovatec.grid.dao.exceptions.RefreshEntityException;
import br.com.inovatec.grid.dao.exceptions.RemoveEntityException;
import br.com.inovatec.grid.dao.exceptions.SearchEntityException;
import br.com.inovatec.grid.dao.exceptions.UpdateEntityException;
import br.com.inovatec.grid.dao.interfaces.DAOJpa;
import br.com.inovatec.grid.entity.Entidade;
import br.com.inovatec.grid.util.object.CriteriaParam;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 *
 * @author zrobe
 * @param <T>
 */
public abstract class GenericDAO<T extends Entidade> implements DAOJpa<T> {

    private Class entityClass;
    private final EntityManager em;

    public GenericDAO(Class entityClass) {
        this.entityClass = entityClass;
        this.em = ConnectionFactory.getInstance().getEntityManager();
    }

    public Class getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class entityClass) {
        this.entityClass = entityClass;
    }

    public EntityManager getEm() {
        return em;
    }

    @Override
    public void insert(T obj) throws CreateEntityException {
        EntityTransaction tx = this.em.getTransaction();
        try {
            tx.begin();
            this.em.persist(obj);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new CreateEntityException(ex);
        }
    }

    @Override
    public T find(Object id) throws SearchEntityException {
        try {
            return (T) this.em.find(this.entityClass, id);
        } catch (NullPointerException ex) { // Caso nao seja retornado nenhum objeto
            throw new SearchEntityException(new EntityNotFoundException());
        } catch (Exception ex) {
            throw new SearchEntityException(ex);
        }
    }

    @Override
    public void update(T obj) throws UpdateEntityException {
        EntityTransaction tx = this.em.getTransaction();
        try {
            tx.begin();
            this.em.merge(obj);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new UpdateEntityException(ex);
        }
    }

    @Override
    public void persist(T obj) throws PersistEntityException {
        try {
            if (obj.getId() != null) {
                this.update(obj);
            } else {
                this.insert(obj);
            }
        } catch (UpdateEntityException | CreateEntityException ex) {
            throw new PersistEntityException(ex);
        }
    }

    @Override
    public void remove(T obj) throws RemoveEntityException {
        EntityTransaction tx = this.em.getTransaction();
        try {
            tx.begin();
            this.em.remove(this.find(obj.getId()));
            tx.commit();
        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RemoveEntityException(ex);
        }
    }
    
    @Override
    public void refresh(T obj) throws RefreshEntityException {
        try {
            // Limpar o cache
            this.em.clear();
            // Obter dados atualizados
            this.em.merge(obj);
        } catch (Exception ex) {
            throw new RefreshEntityException(ex);
        }
    }

    @Override
    public List<T> list(String query, Map<String, Object> params) throws ListEntityException {
        try {
            // Limpar cache
            this.em.getEntityManagerFactory().getCache().evictAll();
            this.em.clear();
            // Montar consulta
            Query namedQuery = this.em.createNamedQuery(query, this.entityClass);
            // Atribuir parametros, caso exista
            if (params != null) {
                params.forEach((k, v) -> {
                    namedQuery.setParameter(k, v);
                });
            }
            // Executar consulta
            return namedQuery.getResultList();
        } catch (Exception ex) {
            throw new ListEntityException(ex);
        }
    }

    @Override
    public List<T> findAll(List<CriteriaParam> criteriaParams) throws ListEntityException {
        List<T> resultList = new ArrayList<>();
        try {
            // Iniciar criterios da query
            StringBuilder query = new StringBuilder("1 = 1");
            // Construir parametros
            if (criteriaParams != null && !criteriaParams.isEmpty()) {
                criteriaParams.forEach(c -> {
                    query.append(" AND ").append(c.getClause());
                });
            }
            // Montar consulta
            Query newQuery = this.em.createQuery(getGenericQuery().replace("$1", query), this.entityClass);
            // Atribuir parametros, caso exista
            if (criteriaParams != null && !criteriaParams.isEmpty()) {
                criteriaParams.forEach(c -> {
                    newQuery.setParameter(c.getParamName(), c.getParamValue());
                });
            }
            // Executar consulta
            resultList = newQuery.getResultList();
        } catch (Exception ex) {
            throw new ListEntityException(ex);
        }
        return resultList;
    }

    /**
     * Metodo a ser implementado pelas classes filhas para que seja garantida a
     * funcionalidade de listagem de todas as entidades presentes no banco de
     * dados
     *
     * @return
     * @throws ListEntityException
     */
    public abstract List<T> findAll() throws ListEntityException;

    /**
     * Obter a query generic para montar as consultas
     *
     * @return
     */
    public abstract String getGenericQuery();

}
