/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.entity;

/**
 *
 * @author zrobe
 * @param <I>
 * @param <T>
 */
public interface Entidade<I, T> extends Comparable<T> {
    
    public I getId();
    
    public void setId(I id);
    
}
