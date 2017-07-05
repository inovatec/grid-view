/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.service.impl;

import br.com.inovatec.grid.dao.ProfessorDAO;
import br.com.inovatec.grid.dao.exceptions.ListEntityException;
import br.com.inovatec.grid.entity.Aula;
import br.com.inovatec.grid.entity.Disciplina;
import br.com.inovatec.grid.entity.Horario;
import br.com.inovatec.grid.entity.Professor;
import br.com.inovatec.grid.provider.ServiceProvider;
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

    @Override
    public boolean isFreeFor(Professor professor, Horario horario) throws ServiceException {
        // Verificar se o professor tem horario disponivel
        for (Horario h : professor.getHorarios()) {
            // Verificar se o horario requerido é igual ou esta no intervalo de inicio e fim
            if (h.equals(horario) || h.between(horario)) {
                // Verificar se o professor ja esta com alguma aula nesse horario
                for (Aula a : ServiceProvider.getInstance().getAulaService().findAll(professor)) {
                    // Verificar se o horario requerido é igual ou esta no intervalo de inicio e fim
                    if (a.getHorario().equals(horario) || a.getHorario().between(horario)) {
                        return false;
                    }
                }
                // Condicoes ideais foram atendidas
                return true;
            }
        }
        return false;
    }

}
