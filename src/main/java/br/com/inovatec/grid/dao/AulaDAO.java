/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.dao;

import br.com.inovatec.grid.dao.exceptions.ListEntityException;
import br.com.inovatec.grid.entity.Aula;
import java.util.List;

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
    
}
