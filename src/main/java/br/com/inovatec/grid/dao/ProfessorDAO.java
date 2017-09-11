/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.dao;

import br.com.inovatec.grid.dao.exceptions.ListEntityException;
import br.com.inovatec.grid.entity.Disciplina;
import br.com.inovatec.grid.entity.Professor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author zrobe
 */
public class ProfessorDAO extends GenericDAO<Professor> {

    public ProfessorDAO() {
        super(Professor.class);
    }

    @Override
    public List<Professor> findAll() throws ListEntityException {
        return this.list("professor.findAll", null);
    }
    
    @Override
    public String getGenericQuery() {
        return "select p from Professor p where $1";
    }
    
    public List<Professor> findByNome(String nome) throws ListEntityException {
        Map<String, Object> params = new HashMap<>();
        params.put("nome", "%" + nome + "%");
        return this.list("professor.findByNome", params);
    }
    
    public List<Professor> findByDisciplina(Disciplina disciplina) throws ListEntityException {
        Map<String, Object> params = new HashMap<>();
        params.put("disciplina", disciplina);
        return this.list("professor.findByDisciplina", params);
    }
    
}
