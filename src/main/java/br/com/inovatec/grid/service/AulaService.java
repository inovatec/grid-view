/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.service;

import br.com.inovatec.grid.entity.Aula;
import br.com.inovatec.grid.entity.Professor;
import br.com.inovatec.grid.service.exception.ServiceException;
import java.util.List;

/**
 *
 * @author zrobe
 */
public interface AulaService extends GenericService<Aula>{
    
    /**
     * Obter as Aulas de um determinado Professor
     * 
     * @param professor
     * @return
     * @throws ServiceException 
     */
    public List<Aula> findAll(Professor professor) throws ServiceException;
    
}
