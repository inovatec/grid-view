/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.service;

import br.com.inovatec.grid.entity.Turma;
import br.com.inovatec.grid.service.exception.ServiceException;
import java.util.List;

/**
 *
 * @author zrobe
 */
public interface TurmaService extends GenericService<Turma>{
    
    public List<Turma> findAllBy(Integer peridodoCorrente) throws ServiceException;
    public List<Turma> findAllByPeriodoCorrente() throws ServiceException;
    
}
