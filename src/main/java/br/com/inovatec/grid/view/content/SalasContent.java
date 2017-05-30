/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.content;

import br.com.inovatec.grid.provider.ServiceProvider;
import br.com.inovatec.grid.entity.Sala;
import br.com.inovatec.grid.service.exception.ServiceException;
import br.com.inovatec.grid.view.component.DataTableEntity;
import br.com.inovatec.grid.view.component.datamodel.SalaDataModel;
import br.com.inovatec.grid.view.component.listener.DataTableEntityActionListener;
import br.com.inovatec.grid.view.component.util.ComponentUtils;
import br.com.inovatec.grid.view.content.template.DefaultListContent;
import br.com.inovatec.grid.view.controller.ViewController;
import br.com.inovatec.grid.view.layout.template.DefaultView;
import br.com.inovatec.grid.view.values.Dimens;
import br.com.inovatec.grid.view.values.Icons;
import java.awt.Dimension;
import java.util.List;

/**
 *
 * @author zrobe
 */
public class SalasContent extends DefaultListContent<Sala> {

    private DataTableEntity<Sala, SalaDataModel> dataTable;

    public SalasContent(DefaultView container) {
        super(container, Icons.IC_SALA);
    }

    @Override
    protected DataTableEntity<Sala, SalaDataModel> getDataTable() {
        if (this.dataTable == null) {
            // Dimensoes da Tabela de Horários
            Dimension tableDimension = ComponentUtils.getDefaultComponentDimension(this.getWidth(), Dimens.SALAS_TABLE_HEIGHT);
            // Tabela com os horarios
            this.dataTable = new DataTableEntity<>(
                    new SalaDataModel(this.getData()),
                    tableDimension,
                    new SalaDataTableEntityActionListener(),
                    true
            );
        }
        return this.dataTable;
    }

    @Override
    public List<Sala> getData() {
        try {
            return ServiceProvider.getInstance().getSalaService().findAll();
        } catch (ServiceException ex) {
            this.showInternalErrorMessage(ex);
        }
        return null;
    }

    @Override
    public void newAction() {
        ViewController.showSalaView(this.getContainer(), true, null);
    }

    @Override
    public void viewAction() {
        ViewController.showSalaView(this.getContainer(), false, this.getDataTable().getSelected());
    }

    @Override
    public void filterAction() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private class SalaDataTableEntityActionListener implements DataTableEntityActionListener<Sala> {

        @Override
        public void mainAction(Sala t, int index) {
            viewAction();
        }
        
        @Override
        public void clickRowAction() {
            getViewButton().setEnabled(true);
        }

        @Override
        public boolean deleteAction(Sala t) {
            return true;
        }

    }

}
