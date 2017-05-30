/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.service.impl;

import br.com.inovatec.grid.dao.DisciplinaDAO;
import br.com.inovatec.grid.dao.exceptions.ListEntityException;
import br.com.inovatec.grid.entity.Disciplina;
import br.com.inovatec.grid.entity.Turma;
import br.com.inovatec.grid.service.DisciplinaService;
import br.com.inovatec.grid.service.exception.ServiceException;
import java.util.List;

/**
 *
 * @author zrobe
 */
public class DisciplinaServiceImpl extends AbstractService<Disciplina, DisciplinaDAO> implements DisciplinaService {

    private final DisciplinaDAO disciplinaDAO;

    public DisciplinaServiceImpl() {
        this.disciplinaDAO = new DisciplinaDAO();
    }
    
    @Override
    public DisciplinaDAO getGenericDAO() {
        return this.disciplinaDAO;
    }

    @Override
    public List<Disciplina> listByTurma(Turma turma) throws ServiceException {
        try {
            return this.disciplinaDAO.findBy(turma);
        } catch (ListEntityException ex) {
            throw new ServiceException(ex);
        }
    }
}