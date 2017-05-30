/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.service.impl;

import br.com.inovatec.grid.dao.TurmaDAO;
import br.com.inovatec.grid.dao.exceptions.ListEntityException;
import br.com.inovatec.grid.entity.Turma;
import br.com.inovatec.grid.service.TurmaService;
import br.com.inovatec.grid.service.exception.ServiceException;
import java.util.List;

/**
 *
 * @author zrobe
 */
public class TurmaServiceImpl extends AbstractService<Turma, TurmaDAO> implements TurmaService {

    private final TurmaDAO turmaDAO;

    public TurmaServiceImpl() {
        this.turmaDAO = new TurmaDAO();
    }
    
    @Override
    public TurmaDAO getGenericDAO() {
        return this.turmaDAO;
    }

    @Override
    public List<Turma> findAll() throws ServiceException {
        return super.findAll(); //To change body of generated methods, choose Tools | Templates.
    }    
    
    @Override
    public List<Turma> findByPeriodo(String periodoCorrente) throws ServiceException {
        try {
            return this.turmaDAO.findAllByPeriodo(periodoCorrente);
        } catch (ListEntityException ex) {
            throw new ServiceException(ex);
        }
    }
    
    @Override
    public List<Turma> findAllByDescricao(String periodoCorrente, String descricao) throws ServiceException {
        try {
            return this.turmaDAO.findAllByDescricao(periodoCorrente, descricao);
        } catch (ListEntityException ex) {
            throw new ServiceException();
        }
    }
    
}
