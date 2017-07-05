/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.service.impl;

import br.com.inovatec.grid.dao.AulaDAO;
import br.com.inovatec.grid.dao.exceptions.ListEntityException;
import br.com.inovatec.grid.entity.Aula;
import br.com.inovatec.grid.entity.Professor;
import br.com.inovatec.grid.service.AulaService;
import br.com.inovatec.grid.service.exception.ServiceException;
import java.util.List;

/**
 *
 * @author zrobe
 */
public class AulaServiceImpl extends AbstractService<Aula, AulaDAO> implements AulaService {

    private final AulaDAO aulaDAO;

    public AulaServiceImpl() {
        this.aulaDAO = new AulaDAO();
    }
    
    @Override
    public AulaDAO getGenericDAO() {
        return this.aulaDAO;
    }

    @Override
    public List<Aula> findAll(Professor professor) throws ServiceException {
        try {
            return this.aulaDAO.findAll(professor);
        } catch (ListEntityException ex) {
            throw new ServiceException(ex);
        }
    }
    
}
