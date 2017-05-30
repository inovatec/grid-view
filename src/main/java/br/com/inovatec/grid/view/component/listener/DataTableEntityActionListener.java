/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.component.listener;

import br.com.inovatec.grid.entity.Entidade;

/**
 *
 * @author zrobe
 * @param <T>
 */
public interface DataTableEntityActionListener<T extends Entidade> {
    
    /**
     * Acao para executar quando o evento da tabela for disparado
     * 
     * @param t
     * @param index
     */
    public void mainAction(T t, int index);
    
    /**
     * Acao para executar quando clicar na linha da tabela
     */
    public void clickRowAction();
    
    /**
     * Acao para executar quando remover dado da tabela
     * @param t
     * @return 
     */
    public boolean deleteAction(T t);
    
}
