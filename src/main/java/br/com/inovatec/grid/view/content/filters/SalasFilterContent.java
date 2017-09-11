/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.content.filters;

import br.com.inovatec.grid.entity.Sala;
import br.com.inovatec.grid.entity.enums.StatusSala;
import br.com.inovatec.grid.entity.enums.TipoSala;
import br.com.inovatec.grid.util.StringUtils;
import br.com.inovatec.grid.util.object.CriteriaParam;
import br.com.inovatec.grid.view.component.exception.NoItemSelectedException;
import br.com.inovatec.grid.view.component.factory.LabelFactory;
import br.com.inovatec.grid.view.component.factory.SelectOneMenuFactory;
import br.com.inovatec.grid.view.component.factory.TextFieldFactory;
import br.com.inovatec.grid.view.component.form.SelectOneMenu;
import br.com.inovatec.grid.view.component.form.TextField;
import br.com.inovatec.grid.view.content.template.DefaultFilterContent;
import br.com.inovatec.grid.view.layout.template.FilterView;
import br.com.inovatec.grid.view.values.Dimens;
import java.util.Arrays;

/**
 *
 * @author zrobe
 */
public class SalasFilterContent extends DefaultFilterContent<Sala> {

    // Visual Components
    private TextField nomeTextField;
    private SelectOneMenu<TipoSala> tipoSalaSelectOneMenu;
    private SelectOneMenu<StatusSala> statusSalaSelectOneMenu;

    public SalasFilterContent(FilterView container) {
        super(container);
    }
    
    @Override
    protected void buildMain() {
        // Linha 1
        this.getMain().add(LabelFactory.getInstance().getLabel("Nome", this.getWidth(), 1, Dimens.WEIGHT_100));
        this.nomeTextField = TextFieldFactory.getInstance().getTextField(this.getWidth(), 1, Dimens.WEIGHT_100, 100);
        this.getMain().add(this.nomeTextField);
        // Linha 2
        this.getMain().add(LabelFactory.getInstance().getLabel("Tipo", this.getWidth(), 1, Dimens.WEIGHT_100));
        this.tipoSalaSelectOneMenu = SelectOneMenuFactory.getInstance().getSelectOneMenu(this.getWidth(), 1, Dimens.WEIGHT_100, Arrays.asList(TipoSala.values()));
        this.getMain().add(this.tipoSalaSelectOneMenu);
        // Linha 3
        this.getMain().add(LabelFactory.getInstance().getLabel("Situação", this.getWidth(), 1, Dimens.WEIGHT_100));
        this.statusSalaSelectOneMenu = SelectOneMenuFactory.getInstance().getSelectOneMenu(this.getWidth(), 1, Dimens.WEIGHT_100, Arrays.asList(StatusSala.values()));
        this.getMain().add(this.statusSalaSelectOneMenu);
    }
    
    @Override
    public void fillCriteriaParams() {
        if (StringUtils.isValid(this.nomeTextField.getText())) {
            getContainer().getCriteriaParams().add(new CriteriaParam("s.nome LIKE :nome", "nome", "%" + this.nomeTextField.getText() + "%"));
        }
        
        try {
            getContainer().getCriteriaParams().add(new CriteriaParam("s.tipoSala = :tipoSala", "tipoSala", this.tipoSalaSelectOneMenu.getSelectedItem()));
        } catch (NoItemSelectedException ex) {
        }
        
        try {
            getContainer().getCriteriaParams().add(new CriteriaParam("s.status = :status", "status", this.statusSalaSelectOneMenu.getSelectedItem()));
        } catch (NoItemSelectedException ex) {
        }
    }
    
    @Override
    public void resetParams() {
        this.nomeTextField.reset();
        this.tipoSalaSelectOneMenu.reset();
        this.statusSalaSelectOneMenu.reset();
    }

}
