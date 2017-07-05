/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.dao;

import br.com.inovatec.grid.dao.exceptions.ListEntityException;
import br.com.inovatec.grid.entity.Aula;
import br.com.inovatec.grid.entity.Professor;
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
    
}
