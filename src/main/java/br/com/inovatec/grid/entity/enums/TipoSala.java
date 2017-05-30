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
public enum TipoSala implements Selectable {
    
    SALA("Sala"), LAB("Laboratório");
    
    private final String value;

    private TipoSala(String value) {
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
