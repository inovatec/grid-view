/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.service.impl;

import br.com.inovatec.grid.dao.ProfessorDAO;
import br.com.inovatec.grid.dao.exceptions.ListEntityException;
import br.com.inovatec.grid.entity.Disciplina;
import br.com.inovatec.grid.entity.Professor;
import br.com.inovatec.grid.service.ProfessorService;
import br.com.inovatec.grid.service.exception.ServiceException;
import java.util.List;

/**
 *
 * @author zrobe
 */
public class ProfessorServiceImpl extends AbstractService<Professor, ProfessorDAO> implements ProfessorService {

    private final ProfessorDAO professorDAO;

    public ProfessorServiceImpl() {
        this.professorDAO = new ProfessorDAO();
    }
    
    @Override
    public ProfessorDAO getGenericDAO() {
        return this.professorDAO;
    }

    @Override
    public List<Professor> findByDisciplina(Disciplina disciplina) throws ServiceException {
        try {
            return this.professorDAO.findByDisciplina(disciplina);
        } catch (ListEntityException ex) {
            throw new ServiceException(ex);
        }
    }
    
    @Override
    public List<Professor> findByNome(String nome) throws ServiceException {
        try {
            return this.professorDAO.findByNome(nome);
        } catch (ListEntityException ex) {
            throw new ServiceException(ex);
        }
    }
    
}
