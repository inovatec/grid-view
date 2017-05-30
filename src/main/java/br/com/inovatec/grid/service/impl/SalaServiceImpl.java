/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.service.impl;

import br.com.inovatec.grid.dao.SalaDAO;
import br.com.inovatec.grid.dao.exceptions.ListEntityException;
import br.com.inovatec.grid.entity.Sala;
import br.com.inovatec.grid.entity.enums.StatusSala;
import br.com.inovatec.grid.service.SalaService;
import br.com.inovatec.grid.service.exception.ServiceException;
import java.util.List;

/**
 *
 * @author zrobe
 */
public class SalaServiceImpl extends AbstractService<Sala, SalaDAO> implements SalaService {

    private final SalaDAO salaDAO;

    public SalaServiceImpl() {
        this.salaDAO = new SalaDAO();
    }
    
    @Override
    public SalaDAO getGenericDAO() {
        return this.salaDAO;
    }

    @Override
    public List<Sala> listDisponiveis() throws ServiceException {
        try {
            return this.salaDAO.findAll(StatusSala.DISPONIVEL);
        } catch (ListEntityException ex) {
            throw new ServiceException(ex);
        }
    }
    
}
