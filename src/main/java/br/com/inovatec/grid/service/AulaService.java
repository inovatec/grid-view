/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.service;

import br.com.inovatec.grid.entity.Aula;
import br.com.inovatec.grid.entity.DiaAula;
import br.com.inovatec.grid.entity.Professor;
import br.com.inovatec.grid.entity.Turma;
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
    
    /**
     * Obter as Aulas de uma determinada turma e periodo
     * 
     * @param turma
     * @param periodo
     * @return
     * @throws ServiceException 
     */
    public List<Aula> findAll(Turma turma, Integer periodo) throws ServiceException;
    
    /**
     * Obter as Aulas de uma determinada turma do periodo corrente
     * 
     * @param turma
     * @return
     * @throws ServiceException 
     */
    public List<Aula> findAllByPeriodoCorrente(Turma turma) throws ServiceException;
    
    /**
     * Obter as Aulas de uma determinada turma e dia da semana
     * 
     * @param turma
     * @param diaAula
     * @return
     * @throws ServiceException 
     */
    public List<Aula> findAll(Turma turma, DiaAula diaAula) throws ServiceException;
    
    /**
     * Obter todas as Aulas da Escola por período
     * 
     * @param periodo
     * @return
     * @throws ServiceException 
     */
    public List<Aula> findAll(Integer periodo) throws ServiceException;
    
}
