/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.entity.enums;

import br.com.inovatec.grid.view.contract.Selectable;

/**
 *
 * @author zrobe
 */
public enum StatusSala implements Selectable {
    
    DISPONIVEL("Disponível"), INDISPONIVEL("Indisponível");
    
    private final String value;

    private StatusSala(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public String getLabel() {
        return this.value;
    }
    
}
