/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.service.impl;

import br.com.inovatec.grid.dao.UsuarioDAO;
import br.com.inovatec.grid.dao.exceptions.AuthenticationException;
import br.com.inovatec.grid.entity.Usuario;
import br.com.inovatec.grid.service.exception.ServiceException;
import br.com.inovatec.grid.service.UsuarioService;

/**
 *
 * @author zrobe
 */
public class UsuarioServiceImpl extends AbstractService<Usuario, UsuarioDAO> implements UsuarioService {

    private final UsuarioDAO usuarioDAO;

    public UsuarioServiceImpl() {
        this.usuarioDAO = new UsuarioDAO();
    }
    
    @Override
    public UsuarioDAO getGenericDAO() {
        return this.usuarioDAO;
    }

    @Override
    public Usuario authenticate(String login, String senha) throws ServiceException {
        try {
            return this.usuarioDAO.autenticar(login, senha);
        } catch (AuthenticationException ex) {
            throw new ServiceException(ex);
        }
    }
    
}
