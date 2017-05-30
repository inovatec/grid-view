/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.dao;

import br.com.inovatec.grid.dao.exceptions.ListEntityException;
import br.com.inovatec.grid.entity.Sala;
import br.com.inovatec.grid.entity.enums.StatusSala;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author zrobe
 */
public class SalaDAO extends GenericDAO<Sala> {

    public SalaDAO() {
        super(Sala.class);
    }

    @Override
    public List<Sala> findAll() throws ListEntityException {
        return this.list("sala.findAll", null);
    }
    
    /**
     * Obter todas as salas pelo status
     * 
     * @param status
     * @return
     * @throws ListEntityException 
     */
    public List<Sala> findAll(StatusSala status) throws ListEntityException {
        Map<String, Object> params = new HashMap<>();
        params.put("status", status);
        return this.list("sala.findAllByStatus", params);
    }
    
}
