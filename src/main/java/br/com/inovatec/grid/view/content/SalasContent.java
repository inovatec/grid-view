/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.content;

import br.com.inovatec.grid.provider.ServiceProvider;
import br.com.inovatec.grid.entity.Sala;
import br.com.inovatec.grid.view.component.DataTableEntity;
import br.com.inovatec.grid.view.component.datamodel.SalaDataModel;
import br.com.inovatec.grid.view.component.util.ComponentUtils;
import br.com.inovatec.grid.view.content.template.DefaultListContent;
import br.com.inovatec.grid.view.controller.ViewController;
import br.com.inovatec.grid.view.layout.filters.SalasFilterView;
import br.com.inovatec.grid.view.layout.template.DefaultView;
import br.com.inovatec.grid.view.values.Dimens;
import br.com.inovatec.grid.view.values.Icons;
import java.awt.Dimension;

/**
 *
 * @author zrobe
 */
public class SalasContent extends DefaultListContent<Sala> {

    private DataTableEntity<Sala, SalaDataModel> dataTable;

    public SalasContent(DefaultView container) {
        super(
                container, 
                Icons.IC_SALA, 
                ServiceProvider.getInstance().getSalaService(),
                new SalasFilterView(container),
                true);
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
                    getDataTableEntityActionListener(),
                    true,
                    false
            );
        }
        return this.dataTable;
    }

    @Override
    public void newAction() {
        ViewController.showSalaView(this.getContainer(), true, null);
    }

    @Override
    public void viewAction() {
        ViewController.showSalaView(this.getContainer(), false, this.getDataTable().getSelected());
    }

}
