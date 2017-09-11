/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.service.impl;

import br.com.inovatec.grid.dao.GenericDAO;
import br.com.inovatec.grid.dao.exceptions.ListEntityException;
import br.com.inovatec.grid.dao.exceptions.PersistEntityException;
import br.com.inovatec.grid.dao.exceptions.RefreshEntityException;
import br.com.inovatec.grid.dao.exceptions.RemoveEntityException;
import br.com.inovatec.grid.dao.exceptions.SearchEntityException;
import br.com.inovatec.grid.dao.exceptions.UpdateEntityException;
import br.com.inovatec.grid.entity.Entidade;
import br.com.inovatec.grid.service.GenericService;
import br.com.inovatec.grid.service.exception.ServiceException;
import br.com.inovatec.grid.util.object.CriteriaParam;
import java.util.List;

/**
 *
 * @author zrobe
 * @param <T>
 * @param <G>
 */
public abstract class AbstractService<T extends Entidade, G extends GenericDAO<T>> implements GenericService<T> {

    public abstract G getGenericDAO();
    
    @Override
    public void save(T obj) throws ServiceException {
        try {
            this.getGenericDAO().persist(obj);
        } catch (PersistEntityException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public T find(Long id) throws ServiceException {
        try {
            return this.getGenericDAO().find(id);
        } catch (SearchEntityException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public void update(T obj) throws ServiceException {
        try {
            this.getGenericDAO().update(obj);
        } catch (UpdateEntityException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public void remove(T obj) throws ServiceException {
        try {
            this.getGenericDAO().remove(obj);
        } catch (RemoveEntityException ex) {
            throw new ServiceException(ex);
        }
    }
    
    @Override
    public void refresh(T obj) throws ServiceException {
        try {
            this.getGenericDAO().refresh(obj);
        } catch (RefreshEntityException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public List<T> findAll() throws ServiceException {
        try {
            return this.getGenericDAO().findAll();
        } catch (ListEntityException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public List<T> findAll(List<CriteriaParam> criteriaParams) throws ServiceException {
        try {
            return this.getGenericDAO().findAll(criteriaParams);
        } catch (ListEntityException ex) {
            throw new ServiceException(ex);
        }
    }
    
}