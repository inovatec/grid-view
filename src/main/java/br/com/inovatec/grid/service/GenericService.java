/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.service;

import br.com.inovatec.grid.service.exception.ServiceException;
import br.com.inovatec.grid.util.object.CriteriaParam;
import java.util.List;

/**
 *
 * @author zrobe
 * @param <T>
 */
public interface GenericService<T> {
    
    public void save(T obj) throws ServiceException;
    public T find(Long id) throws ServiceException;
    public void update(T obj) throws ServiceException;
    public void remove(T obj) throws ServiceException;
    public void refresh(T obj) throws ServiceException;
    public List<T> findAll() throws ServiceException;
    public List<T> findAll(List<CriteriaParam> criteriaParams) throws ServiceException;
    
}
