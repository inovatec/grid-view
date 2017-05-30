/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.service.impl;

import br.com.inovatec.grid.dao.GradeDAO;
import br.com.inovatec.grid.entity.Grade;
import br.com.inovatec.grid.service.GradeService;

/**
 *
 * @author zrobe
 */
public class GradeServiceImpl extends AbstractService<Grade, GradeDAO> implements GradeService {

    private final GradeDAO gradeDAO;

    public GradeServiceImpl() {
        this.gradeDAO = new GradeDAO();
    }
    
    @Override
    public GradeDAO getGenericDAO() {
        return this.gradeDAO;
    }
    
    public void gerarGrade() {
        
        
        
    }
    
}
