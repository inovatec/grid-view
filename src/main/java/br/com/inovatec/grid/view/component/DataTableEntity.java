/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.component;

import br.com.inovatec.grid.entity.Entidade;
import br.com.inovatec.grid.view.component.listener.DataTableEntityActionListener;
import br.com.inovatec.grid.view.component.render.DataTableCellRender;
import br.com.inovatec.grid.view.component.model.AbstractTableDataModel;
import br.com.inovatec.grid.view.util.MessageFactory;
import br.com.inovatec.grid.view.util.ResultAction;
import br.com.inovatec.grid.view.values.Dimens;
import br.com.inovatec.grid.view.values.Strings;
import br.com.inovatec.grid.view.values.Styles;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JTable;

/**
 *
 * @author zrobe
 *
 * @param <T>
 * @param <M>
 */
public class DataTableEntity<T extends Entidade, M extends AbstractTableDataModel<T>> extends javax.swing.JPanel {

    private M model;
    private final DataTableEntityActionListener dataTableEntityActionListener;

    /**
     * Creates new form DataTable
     *
     * @param model
     * @param dataTableEntityActionListener
     * @param deletePermission
     */
    public DataTableEntity(M model, DataTableEntityActionListener dataTableEntityActionListener, boolean deletePermission) {
        initComponents();
        this.model = model;
        this.dataTableEntityActionListener = dataTableEntityActionListener;
        this.jTable.setModel(this.model);
        // Configuracoes iniciais
        this.init(dataTableEntityActionListener, deletePermission);
    }

    /**
     * Creates new form DataTable
     *
     * @param model
     * @param dimension
     * @param dataTableEntityActionListener
     * @param deletePermission
     */
    public DataTableEntity(M model, Dimension dimension, DataTableEntityActionListener dataTableEntityActionListener, boolean deletePermission) {
        this(model, dataTableEntityActionListener, deletePermission);
        this.setPreferredSize(dimension);
        this.jTable.setDefaultRenderer(Object.class, new DataTableCellRender());
        this.jTable.setRowHeight(Dimens.DEFAULT_TABLE_ROW_HEIGHT);
    }

    private void init(DataTableEntityActionListener dataTableEntityActionListener, boolean deletePermission) {
        this.jTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    executeMainAction();
                } else if (evt.getClickCount() == 1) {
                    dataTableEntityActionListener.clickRowAction();
                }
            }
        });
        // Logica para remocao de itens da tabela, caso haja permissao
        if (deletePermission) {
            this.jTable.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent evt) {
                    switch (evt.getKeyCode()) {
                        case KeyEvent.VK_DELETE: {
                            removeSelected();
                        } break;
                        case KeyEvent.VK_DOWN: {
                            dataTableEntityActionListener.clickRowAction();
                        } break;
                        case KeyEvent.VK_ENTER: {
                            executeMainAction();
                        } break;
                        case KeyEvent.VK_UP: {
                            dataTableEntityActionListener.clickRowAction();
                        } break;
                        case KeyEvent.VK_TAB: {
                            dataTableEntityActionListener.clickRowAction();
                        } break;
                    }
                }
            });
        }
    }

    public M getModel() {
        return this.model;
    }

    public JTable getTable() {
        return this.jTable;
    }

    public void setColumnWidth(int columnIndex, int width, int maxWidth, int minWidth) {
        this.jTable.getColumnModel().getColumn(columnIndex).setPreferredWidth(width);
        this.jTable.getColumnModel().getColumn(columnIndex).setMaxWidth(maxWidth);
        this.jTable.getColumnModel().getColumn(columnIndex).setMinWidth(minWidth);
    }

    /**
     * Obter lista de dados da tabela
     *
     * @return
     */
    public List<T> getData() {
        return this.getModel().getDados();
    }
    
    /**
     * Alterar os dados da tabela
     * 
     * @param data 
     */
    public void setData(List<T> data) {
        // Modificar os dados do modelo
        this.getModel().setDados(data);
        // Ordenar
        this.getModel().sort();
    }

    /**
     * Adicionar um item a tabela
     *
     * @param item
     */
    public void addItem(T item) {
        this.model.addRow(item);
    }

    /**
     * Executar o evento principal
     */
    public void executeMainAction() {
        int row = jTable.getSelectedRow();
        if (row >= 0) {
            dataTableEntityActionListener.mainAction(getModel().getDados().get(row), row);
        }
    }

    /**
     * Remover selecionado
     */
    public void removeSelected() {
        int row = jTable.getSelectedRow();
        if (row >= 0) {
            MessageFactory.showYesNoMessage(jTable, Strings.REMOVE_ROW_MESSAGE, new ResultAction() {
                @Override
                public void confirm() {
                    if (row >= 0) {
                        // Verificar se a remocao podera ser realizada
                        if (dataTableEntityActionListener.deleteAction(getModel().getDados().get(row))) {
                            getModel().removeRow(row);
                        }
                    }
                }
            });
        }
    }

    /**
     * Atualizar a tabela
     */
    public void refreshTable() {
        this.model.refresh();
    }

    /**
     * Obter item selecionado
     *
     * @return
     */
    public T getSelected() {
        int row = this.jTable.getSelectedRow();
        return row >= 0 ? this.getModel().getSelected(row) : null;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        actionsPanel = new javax.swing.JPanel();
        tipJLabel = new javax.swing.JLabel();

        setBackground(null);
        setLayout(new java.awt.BorderLayout());

        jScrollPane.setBackground(null);

        jTable.setFont(Styles.FONT_FAMILY);
        jTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane.setViewportView(jTable);

        add(jScrollPane, java.awt.BorderLayout.CENTER);

        actionsPanel.setBackground(null);
        actionsPanel.setPreferredSize(new java.awt.Dimension(100, 30));
        actionsPanel.setLayout(new java.awt.BorderLayout());

        tipJLabel.setBackground(null);
        tipJLabel.setFont(Styles.FONT_FAMILY);
        tipJLabel.setText("* Clique duas vezes para visualizar/editar. [DEL] para remover.");
        actionsPanel.add(tipJLabel, java.awt.BorderLayout.CENTER);

        add(actionsPanel, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel actionsPanel;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JTable jTable;
    private javax.swing.JLabel tipJLabel;
    // End of variables declaration//GEN-END:variables

}
