/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.dao;

import br.com.inovatec.grid.dao.exceptions.ListEntityException;
import br.com.inovatec.grid.entity.Turma;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author zrobe
 */
public class TurmaDAO extends GenericDAO<Turma> {

    public TurmaDAO() {
        super(Turma.class);
    }

    @Override
    public List<Turma> findAll() throws ListEntityException {
        return this.list("turma.findAll", null);
    }
    
    @Override
    public String getGenericQuery() {
        return "select t from Turma t where $1";
    }
    
    public List<Turma> findAllByPeriodo(Integer periodoCorrente) throws ListEntityException {
        Map<String, Object> params = new HashMap<>();
        params.put("periodoCorrente", periodoCorrente);
        return this.list("turma.findAllByPeriodo", params);
    }
    
    public List<Turma> findAllByDescricao(Integer periodoCorrente, String descricao) throws ListEntityException {
        Map<String, Object> params = new HashMap<>();
        params.put("periodoCorrente", periodoCorrente);
        params.put("descricao", "%" + descricao + "%");
        return this.list("turma.findAllByDescricao", params);
    }
    
}
