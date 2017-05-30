/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.contract;

/**
 *
 * @author zrobe
 */
public interface View {
    
    /**
     * Atualizar componentes de visao
     */
    public void refresh();
    
    /**
     * Exibir janela
     */
    public void display();
    
    /**
     * Fechar janela
     */
    public void close();
    
}
