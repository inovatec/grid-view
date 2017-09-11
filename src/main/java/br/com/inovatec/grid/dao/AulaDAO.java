/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.dao;

import br.com.inovatec.grid.dao.exceptions.ListEntityException;
import br.com.inovatec.grid.entity.Aula;
import br.com.inovatec.grid.entity.DiaAula;
import br.com.inovatec.grid.entity.Professor;
import br.com.inovatec.grid.entity.Turma;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author zrobe
 */
public class AulaDAO extends GenericDAO<Aula> {

    public AulaDAO() {
        super(Aula.class);
    }

    @Override
    public List<Aula> findAll() throws ListEntityException {
        return this.list("aula.findAll", null);
    }
    
    @Override
    public String getGenericQuery() {
        return "select a from Aula a where $1";
    }
    
    /**
     * Obter Auals por Professor
     * 
     * @param professor
     * @return
     * @throws ListEntityException 
     */
    public List<Aula> findAll(Professor professor) throws ListEntityException {
        Map<String, Object> params = new HashMap<>();
        params.put("professor", professor);
        return this.list("aula.findAllByProfessor", params);
    }
    
    /**
     * Obter Aulas por Turma e DiaAula
     * 
     * @param turma
     * @param periodo
     * @return
     * @throws ListEntityException 
     */
    public List<Aula> findAll(Turma turma, Integer periodo) throws ListEntityException {
        Map<String, Object> params = new HashMap<>();
        params.put("turma", turma);
        params.put("periodo", periodo);
        return this.list("aula.findAllByTurmaAndPeriodo", params);
    }
    
    /**
     * Obter Aulas por Turma e DiaAula
     * 
     * @param turma
     * @param diaAula
     * @return
     * @throws ListEntityException 
     */
    public List<Aula> findAll(Turma turma, DiaAula diaAula) throws ListEntityException {
        Map<String, Object> params = new HashMap<>();
        params.put("turma", turma);
        params.put("diaAula", diaAula);
        return this.list("aula.findAllByTurmaAndDiaAula", params);
    }
    
    /**
     * Obter Aulas por Periodo
     * 
     * @param periodo 
     * @return
     * @throws ListEntityException 
     */
    public List<Aula> findAll(Integer periodo) throws ListEntityException {
        Map<String, Object> params = new HashMap<>();
        params.put("periodo", periodo);
        return this.list("aula.findAllByPeriodo", params);
    }
    
}
