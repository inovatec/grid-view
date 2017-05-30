/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.component.model;

import br.com.inovatec.grid.entity.Entidade;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author zrobe
 * @param <T>
 */
public abstract class AbstractTableDataModel<T extends Entidade> extends AbstractTableModel {
    
    private List<T> dados;
    
    public AbstractTableDataModel(List<T> dados) {
        this.dados = dados;
    }

    public List<T> getDados() {
        return dados;
    }

    public void setDados(List<T> dados) {
        this.dados = dados;
    }
    
    public abstract String[] getColumns();
    
    public void addRow(T obj){
        this.dados.add(obj);
        this.sort();
    }
    
    public void removeRow(int index) {
        this.dados.remove(index);
        this.sort();
    }
 
    @Override
    public String getColumnName(int num){
        return this.getColumns()[num];
    }
 
    @Override
    public int getRowCount() {
        return dados.size();
    }
 
    @Override
    public int getColumnCount() {
        return this.getColumns().length;
    }
 
    @Override
    public abstract Object getValueAt(int linha, int coluna);
    
    /**
     * Obter objeto selecionado
     * 
     * @param row
     * @return 
     */
    public T getSelected(int row) {
        if (row >= 0) {
            return this.dados.get(row);
        }
        return null;
    }
    
    /**
     * Atualizar os dados
     */
    public void refresh() {
        this.sort();
    }
    
    /**
     * Ordenar lista
     */
    public void sort() {
        Collections.sort(this.dados);
        this.fireTableDataChanged();
    }
    
}
