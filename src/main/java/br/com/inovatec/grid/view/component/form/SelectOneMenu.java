/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.component.form;

import br.com.inovatec.grid.view.component.exception.NoItemSelectedException;
import br.com.inovatec.grid.view.component.model.SelectOneMenuModel;
import br.com.inovatec.grid.view.component.render.SelectListCellRenderer;
import br.com.inovatec.grid.view.contract.Field;
import br.com.inovatec.grid.view.values.Colors;
import br.com.inovatec.grid.view.values.Styles;
import java.awt.event.ActionListener;
import java.util.List;

/**
 *
 * @author zrobe
 * @param <T>
 */
public class SelectOneMenu<T> extends javax.swing.JPanel implements Field {

    private final List<T> items;

    /**
     * Creates new form SelectOneMenu
     *
     * @param items
     */
    public SelectOneMenu(List<T> items) {
        initComponents();
        this.items = items;
        this.setModel();
    }

    @Override
    public void setEnabled(boolean enable) {
        this.jComboBox.setEnabled(enable);
    }

    /**
     * Adicionar Listener ao JComboBox
     * 
     * @param listener 
     */
    public void addSelectActionListener(ActionListener listener) {
        this.jComboBox.addActionListener(listener);
    }
    
    /**
     * Modificar o item selecionado
     * 
     * @param selected 
     */
    public void setSelectedItem(T selected) {
        this.jComboBox.setSelectedItem(selected);
    }

    /**
     * Obter o item selecionado
     * 
     * @return
     * @throws NoItemSelectedException 
     */
    public T getSelectedItem() throws NoItemSelectedException {
        if (this.jComboBox.getSelectedItem() != null) {
            return (T) this.jComboBox.getSelectedItem();
        } else {
            throw new NoItemSelectedException();
        }
    }

    /**
     * Remover item da lista
     *
     * @param item
     */
    public void removeItem(T item) {
        this.items.remove(item);
        this.jComboBox.removeItem(item);
    }

    /**
     * Adicionar item a lista
     *
     * @param item
     */
    public void addItem(T item) {
        // Adicionar item
        this.items.add(item);
        // Modificar modelo
        this.setModel();
    }

    /**
     * Modificar modelo
     */
    public void setModel() {
        if (this.items != null) {
            // Ordenar caso seja instancia de Comparable
            this.items.sort((T o1, T o2) -> {
                return ((Comparable) o1).compareTo(o2);
            });
            this.jComboBox.setModel(new SelectOneMenuModel<>((T[]) items.toArray()));
        }
    }
    
    /**
     * Remover o primeiro elemento
     */
    public void clearFirstElement() {
        this.jComboBox.removeItemAt(0);
    }
    
    /**
     * Resetar campo
     */
    public void reset() {
        this.jComboBox.setSelectedItem(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox = new javax.swing.JComboBox<>();

        setBackground(java.awt.Color.white);
        setBorder(javax.swing.BorderFactory.createLineBorder(Colors.COLOR_BORDER_INPUT));
        setLayout(new java.awt.BorderLayout());

        jComboBox.setFont(Styles.FONT_FAMILY);
        jComboBox.setBorder(null);
        jComboBox.setRenderer(new SelectListCellRenderer());
        add(jComboBox, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<T> jComboBox;
    // End of variables declaration//GEN-END:variables
}
