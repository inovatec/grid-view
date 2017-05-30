/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.service.impl;

import br.com.inovatec.grid.dao.EscolaDAO;
import br.com.inovatec.grid.dao.exceptions.SearchEntityException;
import br.com.inovatec.grid.entity.Escola;
import br.com.inovatec.grid.service.exception.ServiceException;
import br.com.inovatec.grid.service.EscolaService;

/**
 *
 * @author zrobe
 */
public class EscolaServiceImpl extends AbstractService<Escola, EscolaDAO> implements EscolaService {

    private final EscolaDAO escolaDAO;

    public EscolaServiceImpl() {
        this.escolaDAO = new EscolaDAO();
    }
    
    @Override
    public EscolaDAO getGenericDAO() {
        return this.escolaDAO;
    }

    @Override
    public Escola get() throws ServiceException {
        try {
            return this.getGenericDAO().get();
        } catch (SearchEntityException ex) {
            throw new ServiceException(ex);
        }
    }
    
}
