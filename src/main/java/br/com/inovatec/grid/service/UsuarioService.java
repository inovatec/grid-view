/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.service;

import br.com.inovatec.grid.entity.Usuario;
import br.com.inovatec.grid.service.exception.ServiceException;

/**
 *
 * @author zrobe
 */
public interface UsuarioService extends GenericService<Usuario>{
    
    public Usuario authenticate(String login, String senha) throws ServiceException;
    
}
