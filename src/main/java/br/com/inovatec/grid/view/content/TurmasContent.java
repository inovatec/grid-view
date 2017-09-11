/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.content;

import br.com.inovatec.grid.provider.ServiceProvider;
import br.com.inovatec.grid.entity.Turma;
import br.com.inovatec.grid.view.component.DataTableEntity;
import br.com.inovatec.grid.view.component.datamodel.TurmaDataModel;
import br.com.inovatec.grid.view.component.util.ComponentUtils;
import br.com.inovatec.grid.view.content.template.DefaultListContent;
import br.com.inovatec.grid.view.controller.ViewController;
import br.com.inovatec.grid.view.layout.filters.TurmasFilterView;
import br.com.inovatec.grid.view.layout.template.DefaultView;
import br.com.inovatec.grid.view.values.Dimens;
import br.com.inovatec.grid.view.values.Icons;
import java.awt.Dimension;

/**
 *
 * @author zrobe
 */
public class TurmasContent extends DefaultListContent<Turma> {

    private DataTableEntity<Turma, TurmaDataModel> dataTable;

    public TurmasContent(DefaultView container) {
        super(
                container,
                Icons.IC_TURMA,
                ServiceProvider.getInstance().getTurmaService(),
                new TurmasFilterView(container),
                true
        );
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
                    getDataTableEntityActionListener(),
                    true,
                    false
            );
        }
        return this.dataTable;
    }

    @Override
    public void newAction() {
        ViewController.showTurmaView(this.getContainer(), true, null);
    }

    @Override
    public void viewAction() {
        ViewController.showTurmaView(this.getContainer(), false, this.getDataTable().getSelected());
    }

}
