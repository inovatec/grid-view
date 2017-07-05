/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.service;

import br.com.inovatec.grid.entity.Disciplina;
import br.com.inovatec.grid.entity.Horario;
import br.com.inovatec.grid.entity.Professor;
import br.com.inovatec.grid.service.exception.ServiceException;
import java.util.List;

/**
 *
 * @author zrobe
 */
public interface ProfessorService extends GenericService<Professor>{
    
    public List<Professor> findByDisciplina(Disciplina disciplina) throws ServiceException;
    public List<Professor> findByNome(String nome) throws ServiceException;
    public boolean isFreeFor(Professor professor, Horario horario) throws ServiceException;
    
}
