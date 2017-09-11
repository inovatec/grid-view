/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.content;

import br.com.inovatec.grid.provider.ServiceProvider;
import br.com.inovatec.grid.entity.Professor;
import br.com.inovatec.grid.view.component.DataTableEntity;
import br.com.inovatec.grid.view.component.datamodel.ProfessorDataModel;
import br.com.inovatec.grid.view.component.util.ComponentUtils;
import br.com.inovatec.grid.view.content.template.DefaultListContent;
import br.com.inovatec.grid.view.controller.ViewController;
import br.com.inovatec.grid.view.layout.filters.ProfessoresFilterView;
import br.com.inovatec.grid.view.layout.template.DefaultView;
import br.com.inovatec.grid.view.values.Dimens;
import br.com.inovatec.grid.view.values.Icons;
import java.awt.Dimension;

/**
 *
 * @author zrobe
 */
public class ProfessoresContent extends DefaultListContent<Professor> {

    private DataTableEntity<Professor, ProfessorDataModel> dataTable;

    public ProfessoresContent(DefaultView container) {
        super(
                container, 
                Icons.IC_PROFESSOR,
                ServiceProvider.getInstance().getProfessorService(),
                new ProfessoresFilterView(container),
                true);
    }

    @Override
    protected DataTableEntity<Professor, ProfessorDataModel> getDataTable() {
        if (this.dataTable == null) {
            // Dimensoes da Tabela de Horários
            Dimension tableDimension = ComponentUtils.getDefaultComponentDimension(this.getWidth(), Dimens.PROFESSORES_TABLE_HEIGHT);
            // Tabela com os horarios
            this.dataTable = new DataTableEntity<>(
                    new ProfessorDataModel(this.getData()),
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
        ViewController.showProfessorView(this.getContainer(), true, null);
    }

    @Override
    public void viewAction() {
        ViewController.showProfessorView(this.getContainer(), false, this.getDataTable().getSelected());
    }

}
