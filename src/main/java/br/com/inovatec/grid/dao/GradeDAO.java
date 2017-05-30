/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.dao;

import br.com.inovatec.grid.dao.exceptions.ListEntityException;
import br.com.inovatec.grid.entity.Grade;
import java.util.List;

/**
 *
 * @author zrobe
 */
public class GradeDAO extends GenericDAO<Grade> {

    public GradeDAO() {
        super(Grade.class);
    }

    @Override
    public List<Grade> findAll() throws ListEntityException {
        return this.list("grade.findAll", null);
    }
    
}
