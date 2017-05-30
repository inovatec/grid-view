/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.session;

import br.com.inovatec.grid.entity.Escola;
import br.com.inovatec.grid.entity.Usuario;

/**
 *
 * @author zrobe
 */
public class Session {
    
    private Usuario usuario;
    private Escola escola;
    private static Session instance;
    
    private Session() {
    }
    
    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Escola getEscola() {
        return escola;
    }

    public void setEscola(Escola escola) {
        this.escola = escola;
    }
    
    public boolean isLogged() {
        return this.usuario != null;
    }
    
}
