/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.service;

import br.com.inovatec.grid.entity.DiaAula;
import br.com.inovatec.grid.entity.Gerenciavel;
import br.com.inovatec.grid.entity.Horario;
import br.com.inovatec.grid.service.exception.ServiceException;
import java.util.List;

/**
 *
 * @author zrobe
 */
public interface HorarioService extends GenericService<Horario>{
    
    public List<Horario> findBy(Gerenciavel gerenciavel, Integer periodo) throws ServiceException;
    public List<Horario> getOfPeriodoCorrente(Gerenciavel gerenciavel) throws ServiceException;
    public Boolean havingHorario(DiaAula diaAula) throws ServiceException;
    
}
