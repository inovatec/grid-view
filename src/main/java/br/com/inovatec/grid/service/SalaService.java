/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.service;

import br.com.inovatec.grid.entity.Sala;
import br.com.inovatec.grid.service.exception.ServiceException;
import java.util.List;

/**
 *
 * @author zrobe
 */
public interface SalaService extends GenericService<Sala>{
    
    /**
     * Obter todas as Salas de Aula disponíveis
     * 
     * @return
     * @throws ServiceException 
     */
    public List<Sala> listDisponiveis() throws ServiceException ;
    
}
