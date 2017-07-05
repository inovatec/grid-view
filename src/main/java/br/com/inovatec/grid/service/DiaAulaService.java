/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.service;

import br.com.inovatec.grid.entity.DiaAula;
import br.com.inovatec.grid.service.exception.ServiceException;
import java.util.List;

/**
 *
 * @author zrobe
 */
public interface DiaAulaService extends GenericService<DiaAula>{
    
    public List<DiaAula> listByPeriodoCorrente() throws ServiceException;
    public List<DiaAula> listBy(Integer periodoCorrente) throws ServiceException;
    public Integer getMaxQuantidadeAulas() throws ServiceException;
    
}
