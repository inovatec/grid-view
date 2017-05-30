/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.service.impl;

import br.com.inovatec.grid.dao.DisciplinaTurmaDAO;
import br.com.inovatec.grid.dao.exceptions.ListEntityException;
import br.com.inovatec.grid.entity.DisciplinaTurma;
import br.com.inovatec.grid.entity.Turma;
import br.com.inovatec.grid.service.DisciplinaTurmaService;
import br.com.inovatec.grid.service.exception.ServiceException;
import java.util.List;

/**
 *
 * @author zrobe
 */
public class DisciplinaTurmaServiceImpl extends AbstractService<DisciplinaTurma, DisciplinaTurmaDAO> implements DisciplinaTurmaService {

    private final DisciplinaTurmaDAO disciplinaTurmaDAO;

    public DisciplinaTurmaServiceImpl() {
        this.disciplinaTurmaDAO = new DisciplinaTurmaDAO();
    }
    
    @Override
    public DisciplinaTurmaDAO getGenericDAO() {
        return this.disciplinaTurmaDAO;
    }

    @Override
    public List<DisciplinaTurma> findByTurma(Turma turma) throws ServiceException {
        try {
            return this.disciplinaTurmaDAO.findByTurma(turma);
        } catch (ListEntityException ex) {
            throw new ServiceException(ex);
        }
    }
    
}
