/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.content.filters;

import br.com.inovatec.grid.entity.Professor;
import br.com.inovatec.grid.util.StringUtils;
import br.com.inovatec.grid.util.object.CriteriaParam;
import br.com.inovatec.grid.view.component.factory.LabelFactory;
import br.com.inovatec.grid.view.component.factory.TextFieldFactory;
import br.com.inovatec.grid.view.component.form.MaskedTextField;
import br.com.inovatec.grid.view.component.form.TextField;
import br.com.inovatec.grid.view.content.template.DefaultFilterContent;
import br.com.inovatec.grid.view.layout.template.FilterView;
import br.com.inovatec.grid.view.values.Dimens;

/**
 *
 * @author zrobe
 */
public class ProfessoresFilterContent extends DefaultFilterContent<Professor> {

    private TextField nomeTextField, emailTextField;
    private MaskedTextField cpfTextField, ufTextField;
    private TextField logradouroTextField, bairroTextField, cidadeTextField;

    public ProfessoresFilterContent(FilterView container) {
        super(container);
    }
    
    @Override
    protected void buildMain() {
        // Add campos do formulario [BEGIN]
        // Linha 1
        this.getMain().add(LabelFactory.getInstance().getLabel("Nome", this.getWidth(), 1, Dimens.WEIGHT_100));
        this.nomeTextField = TextFieldFactory.getInstance().getTextField(this.getWidth(), 1, Dimens.WEIGHT_100, 100);
        this.getMain().add(this.nomeTextField);
        
        // Linha 2
        this.getMain().add(LabelFactory.getInstance().getLabel("CPF", this.getWidth(), 2, Dimens.WEIGHT_50));
        this.getMain().add(LabelFactory.getInstance().getLabel("E-mail", this.getWidth(), 2, Dimens.WEIGHT_50));
        this.cpfTextField = TextFieldFactory.getInstance().getMaskedTextField(this.getWidth(), 2, Dimens.WEIGHT_50, "###.###.###-##");
        this.emailTextField = TextFieldFactory.getInstance().getTextField(this.getWidth(), 3, Dimens.WEIGHT_50, 100);
        this.getMain().add(this.cpfTextField);
        this.getMain().add(this.emailTextField);
        
        // Linha 3
        this.getMain().add(LabelFactory.getInstance().getLabel("Endereço", this.getWidth(), 2, Dimens.WEIGHT_50));
        this.getMain().add(LabelFactory.getInstance().getLabel("Bairro", this.getWidth(), 2, Dimens.WEIGHT_50));
        this.logradouroTextField = TextFieldFactory.getInstance().getTextField(this.getWidth(), 2, Dimens.WEIGHT_50, 100);
        this.bairroTextField = TextFieldFactory.getInstance().getTextField(this.getWidth(), 2, Dimens.WEIGHT_50, 100);
        this.getMain().add(this.logradouroTextField);
        this.getMain().add(this.bairroTextField);
        
        // Linha 4
        this.getMain().add(LabelFactory.getInstance().getLabel("Cidade", this.getWidth(), 2, Dimens.WEIGHT_50));
        this.getMain().add(LabelFactory.getInstance().getLabel("UF", this.getWidth(), 2, Dimens.WEIGHT_50));
        this.cidadeTextField = TextFieldFactory.getInstance().getTextField(this.getWidth(), 2, Dimens.WEIGHT_50, 100);
        this.ufTextField = TextFieldFactory.getInstance().getMaskedTextField(this.getWidth(), 2, Dimens.WEIGHT_50, "UU");
        this.getMain().add(this.cidadeTextField);
        this.getMain().add(this.ufTextField);
    }
    
    @Override
    public void fillCriteriaParams() {
        if (StringUtils.isValid(this.nomeTextField.getText())) {
            getContainer().getCriteriaParams().add(new CriteriaParam("p.nome LIKE :nome", "nome", "%" + this.nomeTextField.getText() + "%"));
        }
        
        if (StringUtils.isValid(this.emailTextField.getText())) {
            getContainer().getCriteriaParams().add(new CriteriaParam("p.email LIKE :email", "email", "%" + this.emailTextField.getText() + "%"));
        }
        
        if (StringUtils.isValid(this.cpfTextField.getTextWithoutMask())) {
            getContainer().getCriteriaParams().add(new CriteriaParam("p.cpf = :cpf", "cpf", this.cpfTextField.getTextWithoutMask()));
        }
        
        if (StringUtils.isValid(this.logradouroTextField.getText())) {
            getContainer().getCriteriaParams().add(new CriteriaParam("p.endereco.logradouro LIKE :logradouro", "logradouro", "%" + this.logradouroTextField.getText() + "%"));
        }
        
        if (StringUtils.isValid(this.bairroTextField.getText())) {
            getContainer().getCriteriaParams().add(new CriteriaParam("p.endereco.bairro LIKE :bairro", "bairro", "%" + this.bairroTextField.getText() + "%"));
        }
        
        if (StringUtils.isValid(this.cidadeTextField.getText())) {
            getContainer().getCriteriaParams().add(new CriteriaParam("p.endereco.cidade LIKE :cidade", "cidade", "%" + this.cidadeTextField.getText() + "%"));
        }
        
        if (StringUtils.isValid(this.ufTextField.getText())) {
            getContainer().getCriteriaParams().add(new CriteriaParam("p.endereco.uf LIKE :uf", "uf", "%" + this.ufTextField.getText() + "%"));
        }
    }
    
    @Override
    public void resetParams() {
        this.nomeTextField.reset();
        this.emailTextField.reset();
        this.cpfTextField.reset();
        this.logradouroTextField.reset();
        this.bairroTextField.reset();
        this.cidadeTextField.reset();
        this.ufTextField.reset();
    }

}
