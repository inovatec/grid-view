/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.content;

import br.com.inovatec.grid.provider.ServiceProvider;
import br.com.inovatec.grid.entity.Aula;
import br.com.inovatec.grid.view.component.DataTableEntity;
import br.com.inovatec.grid.view.component.datamodel.AulaDataModel;
import br.com.inovatec.grid.view.component.util.ComponentUtils;
import br.com.inovatec.grid.view.content.template.DefaultListContent;
import br.com.inovatec.grid.view.layout.filters.AulasFilterView;
import br.com.inovatec.grid.view.layout.template.DefaultView;
import br.com.inovatec.grid.view.values.Dimens;
import br.com.inovatec.grid.view.values.Icons;
import java.awt.Dimension;

/**
 *
 * @author zrobe
 */
public class AulasContent extends DefaultListContent<Aula> {

    private DataTableEntity<Aula, AulaDataModel> dataTable;

    public AulasContent(DefaultView container) {
        super(
                container,
                Icons.IC_GRID_VIEW,
                ServiceProvider.getInstance().getAulaService(),
                new AulasFilterView(container),
                false
        );
    }

    @Override
    protected DataTableEntity<Aula, AulaDataModel> getDataTable() {
        if (this.dataTable == null) {
            // Dimensoes da Tabela de Horários
            Dimension tableDimension = ComponentUtils.getDefaultComponentDimension(this.getWidth(), Dimens.AULAS_TABLE_HEIGHT);
            // Tabela com os horarios
            this.dataTable = new DataTableEntity<>(
                    new AulaDataModel(this.getData()),
                    tableDimension,
                    getDataTableEntityActionListener(),
                    false,
                    true
            );
        }
        return this.dataTable;
    }

    @Override
    public void newAction() {
    }

    @Override
    public void viewAction() {
    }
    
}
