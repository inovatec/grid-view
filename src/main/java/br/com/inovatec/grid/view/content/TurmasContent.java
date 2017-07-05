/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.content;

import br.com.inovatec.grid.provider.ServiceProvider;
import br.com.inovatec.grid.entity.Turma;
import br.com.inovatec.grid.service.exception.ServiceException;
import br.com.inovatec.grid.view.component.DataTableEntity;
import br.com.inovatec.grid.view.component.datamodel.TurmaDataModel;
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
public class TurmasContent extends DefaultListContent<Turma> {

    private DataTableEntity<Turma, TurmaDataModel> dataTable;

    public TurmasContent(DefaultView container) {
        super(container, Icons.IC_TURMA);
    }

    @Override
    protected DataTableEntity<Turma, TurmaDataModel> getDataTable() {
        if (this.dataTable == null) {
            // Dimensoes da Tabela de Horários
            Dimension tableDimension = ComponentUtils.getDefaultComponentDimension(this.getWidth(), Dimens.TURMAS_TABLE_HEIGHT);
            // Tabela com os horarios
            this.dataTable = new DataTableEntity<>(
                    new TurmaDataModel(this.getData()),
                    tableDimension,
                    new TurmaDataTableEntityActionListener(),
                    true
            );
        }
        return this.dataTable;
    }

    @Override
    public List<Turma> getData() {
        try {
            return ServiceProvider.getInstance().getTurmaService().findAllByPeriodoCorrente();
        } catch (ServiceException ex) {
            this.showInternalErrorMessage(ex);
        }
        return null;
    }

    @Override
    public void newAction() {
        ViewController.showTurmaView(this.getContainer(), true, null);
    }

    @Override
    public void viewAction() {
        ViewController.showTurmaView(this.getContainer(), false, this.getDataTable().getSelected());
    }

    @Override
    public void filterAction() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private class TurmaDataTableEntityActionListener implements DataTableEntityActionListener<Turma> {

        @Override
        public void mainAction(Turma t, int index) {
            viewAction();
        }
        
        @Override
        public void clickRowAction() {
            getViewButton().setEnabled(true);
        }

        @Override
        public boolean deleteAction(Turma t) {
            return true;
        }

    }

}
