/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.content.filters;

import br.com.inovatec.grid.entity.Sala;
import br.com.inovatec.grid.entity.Turma;
import br.com.inovatec.grid.provider.ServiceProvider;
import br.com.inovatec.grid.service.exception.ServiceException;
import br.com.inovatec.grid.util.StringUtils;
import br.com.inovatec.grid.util.object.CriteriaParam;
import br.com.inovatec.grid.view.component.exception.NoItemSelectedException;
import br.com.inovatec.grid.view.component.factory.LabelFactory;
import br.com.inovatec.grid.view.component.factory.SelectOneMenuFactory;
import br.com.inovatec.grid.view.component.factory.TextFieldFactory;
import br.com.inovatec.grid.view.component.form.NumberTextField;
import br.com.inovatec.grid.view.component.form.SelectOneMenu;
import br.com.inovatec.grid.view.component.form.TextField;
import br.com.inovatec.grid.view.content.template.DefaultFilterContent;
import br.com.inovatec.grid.view.layout.template.FilterView;
import br.com.inovatec.grid.view.values.Dimens;

/**
 *
 * @author zrobe
 */
public class TurmasFilterContent extends DefaultFilterContent<Turma> {

    // Visual Components
    private NumberTextField anoNumberTextField;
    private TextField acronimoTextField;
    private SelectOneMenu<Sala> salaSelectOneMenu;

    public TurmasFilterContent(FilterView container) {
        super(container);
    }

    @Override
    protected void buildMain() {
        this.getMain().add(LabelFactory.getInstance().getLabel("Ano", this.getWidth(), 1, Dimens.WEIGHT_100));
        this.anoNumberTextField = TextFieldFactory.getInstance().getNumberTextField(this.getWidth(), 1, Dimens.WEIGHT_100);
        this.getMain().add(this.anoNumberTextField);

        this.getMain().add(LabelFactory.getInstance().getLabel("Acrônimo", this.getWidth(), 1, Dimens.WEIGHT_100));
        this.acronimoTextField = TextFieldFactory.getInstance().getTextField(this.getWidth(), 1, Dimens.WEIGHT_100);
        this.getMain().add(this.acronimoTextField);

        try {
            this.getMain().add(LabelFactory.getInstance().getLabel("Sala", this.getWidth(), 1, Dimens.WEIGHT_100));
            // Adicionar TextFields da segunda linha
            this.salaSelectOneMenu = SelectOneMenuFactory
                    .getInstance()
                    .getSelectOneMenu(
                            this.getWidth(), 1, Dimens.WEIGHT_100,
                            ServiceProvider.getInstance().getSalaService().listDisponiveis()
                    );
            this.getMain().add(this.salaSelectOneMenu);
        } catch (ServiceException ex) {
            this.showInternalErrorMessage(ex);
        }
    }

    @Override
    public void fillCriteriaParams() {
        if (this.anoNumberTextField.getValue() != null) {
            getContainer().getCriteriaParams().add(new CriteriaParam("t.ano = :ano", "ano", this.anoNumberTextField.getValue()));
        }
        
        if (StringUtils.isValid(this.acronimoTextField.getText())) {
            getContainer().getCriteriaParams().add(new CriteriaParam("t.acronimo LIKE :acronimo", "acronimo", "%" + this.acronimoTextField.getText() + "%"));
        }
        
        try {
            getContainer().getCriteriaParams().add(new CriteriaParam("t.sala = :sala", "sala", this.salaSelectOneMenu.getSelectedItem()));
        } catch (NoItemSelectedException ex) {
        }        
    }

    @Override
    public void resetParams() {
        this.anoNumberTextField.reset();
        this.acronimoTextField.reset();
        this.salaSelectOneMenu.reset();
    }
    
}
