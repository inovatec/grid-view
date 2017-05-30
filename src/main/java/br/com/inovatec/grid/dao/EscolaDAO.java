/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.dao;

import br.com.inovatec.grid.dao.exceptions.ListEntityException;
import br.com.inovatec.grid.dao.exceptions.SearchEntityException;
import br.com.inovatec.grid.entity.Escola;
import java.util.List;
import javax.persistence.NoResultException;

/**
 *
 * @author zrobe
 */
public class EscolaDAO extends GenericDAO<Escola> {

    public EscolaDAO() {
        super(Escola.class);
    }

    @Override
    public List<Escola> findAll() throws ListEntityException {
        return this.list("escola.findAll", null);
    }
    
    public Escola get() throws SearchEntityException {
        try {
            return getEm().createNamedQuery("escola.findAll", Escola.class).setMaxResults(1).getSingleResult();
        } catch (NoResultException ex) {
            throw new SearchEntityException(ex);
        }
    }
    
}
