/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.dao.interfaces;

import br.com.inovatec.grid.dao.exceptions.*;
import br.com.inovatec.grid.entity.Entidade;
import java.util.List;
import java.util.Map;

/**
 *
 * @author zrobe
 * @param <T>
 */
public interface DAOJpa<T extends Entidade> {
    
    /**
     * Inserir uma nova entidade no banco de dados
     * 
     * @param obj
     * @throws CreateEntityException 
     */
    public void insert(T obj) throws CreateEntityException;
    
    /**
     * Buscar entidade
     * 
     * @param id
     * @return
     * @throws SearchEntityException 
     */
    public T find(Object id) throws SearchEntityException;
    
    /**
     * Atualizar a entidade
     * 
     * @param obj
     * @throws UpdateEntityException 
     */
    public void update(T obj) throws UpdateEntityException;
    
    /**
     * Persistir (Inserir ou Atualizar) a entidade
     * 
     * @param obj
     * @throws PersistEntityException 
     */
    public void persist(T obj) throws PersistEntityException;
    
    /**
     * Remover a entidade
     * 
     * @param obj
     * @throws RemoveEntityException 
     */
    public void remove(T obj) throws RemoveEntityException;
    
    /**
     * Listar as entidades de acordo com a query e os parametros passados
     * 
     * @param query
     * @param params
     * @return
     * @throws ListEntityException 
     */
    public List<T> list(String query, Map<String, Object> params) throws ListEntityException;
    
}
