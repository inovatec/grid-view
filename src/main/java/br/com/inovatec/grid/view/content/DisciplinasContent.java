/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.content;

import br.com.inovatec.grid.provider.ServiceProvider;
import br.com.inovatec.grid.entity.Disciplina;
import br.com.inovatec.grid.view.component.DataTableEntity;
import br.com.inovatec.grid.view.component.datamodel.DisciplinaDataModel;
import br.com.inovatec.grid.view.component.util.ComponentUtils;
import br.com.inovatec.grid.view.content.template.DefaultListContent;
import br.com.inovatec.grid.view.controller.ViewController;
import br.com.inovatec.grid.view.layout.filters.DisciplinasFilterView;
import br.com.inovatec.grid.view.layout.template.DefaultView;
import br.com.inovatec.grid.view.values.Dimens;
import br.com.inovatec.grid.view.values.Icons;
import java.awt.Dimension;

/**
 *
 * @author zrobe
 */
public class DisciplinasContent extends DefaultListContent<Disciplina> {

    private DataTableEntity<Disciplina, DisciplinaDataModel> dataTable;

    public DisciplinasContent(DefaultView container) {
        super(
                container, 
                Icons.IC_DISCIPLINA, 
                ServiceProvider.getInstance().getDisciplinaService(),
                new DisciplinasFilterView(container),
                true);
    }

    @Override
    protected DataTableEntity<Disciplina, DisciplinaDataModel> getDataTable() {
        if (this.dataTable == null) {
            // Dimensoes da Tabela de Horários
            Dimension tableDimension = ComponentUtils.getDefaultComponentDimension(this.getWidth(), Dimens.DISCIPLINAS_TABLE_HEIGHT);
            // Tabela com os horarios
            this.dataTable = new DataTableEntity<>(
                    new DisciplinaDataModel(this.getData()),
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
        ViewController.showDisciplinaView(this.getContainer(), true, null);
    }

    @Override
    public void viewAction() {
        ViewController.showDisciplinaView(this.getContainer(), false, this.getDataTable().getSelected());
    }

}
