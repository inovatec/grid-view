/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.content.filters;

import br.com.inovatec.grid.entity.Disciplina;
import br.com.inovatec.grid.util.StringUtils;
import br.com.inovatec.grid.util.object.CriteriaParam;
import br.com.inovatec.grid.view.component.factory.LabelFactory;
import br.com.inovatec.grid.view.component.factory.TextFieldFactory;
import br.com.inovatec.grid.view.component.form.TextField;
import br.com.inovatec.grid.view.content.template.DefaultFilterContent;
import br.com.inovatec.grid.view.layout.template.FilterView;
import br.com.inovatec.grid.view.values.Dimens;

/**
 *
 * @author zrobe
 */
public class DisciplinasFilterContent extends DefaultFilterContent<Disciplina> {

    // Visual Components
    private TextField nomeTextField;

    public DisciplinasFilterContent(FilterView container) {
        super(container);
    }
    
    @Override
    protected void buildMain() {
        this.getMain().add(LabelFactory.getInstance().getLabel("Nome", this.getWidth(), 1, Dimens.WEIGHT_100));
        this.nomeTextField = TextFieldFactory.getInstance().getTextField(this.getWidth(), 1, Dimens.WEIGHT_100, 100);
        this.getMain().add(this.nomeTextField);
    }
    
    @Override
    public void fillCriteriaParams() {
        if (StringUtils.isValid(this.nomeTextField.getText())) {
            getContainer().getCriteriaParams().add(new CriteriaParam("d.nome LIKE :nome", "nome", "%" + this.nomeTextField.getText() + "%"));
        }
    }
    
    @Override
    public void resetParams() {
        this.nomeTextField.reset();
    }

}
