/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.content;

import br.com.inovatec.grid.provider.ServiceProvider;
import br.com.inovatec.grid.entity.Disciplina;
import br.com.inovatec.grid.service.exception.ServiceException;
import br.com.inovatec.grid.util.StringUtils;
import br.com.inovatec.grid.view.component.factory.ButtonFactory;
import br.com.inovatec.grid.view.component.factory.LabelFactory;
import br.com.inovatec.grid.view.component.factory.TextAreaFactory;
import br.com.inovatec.grid.view.component.factory.TextFieldFactory;
import br.com.inovatec.grid.view.component.form.Button;
import br.com.inovatec.grid.view.component.form.TextArea;
import br.com.inovatec.grid.view.component.form.TextField;
import br.com.inovatec.grid.view.component.listener.ButtonActionListener;
import br.com.inovatec.grid.view.content.exception.FormException;
import br.com.inovatec.grid.view.content.template.DefaultFormContent;
import br.com.inovatec.grid.view.layout.DisciplinaView;
import br.com.inovatec.grid.view.util.MessageFactory;
import br.com.inovatec.grid.view.util.ResultAction;
import br.com.inovatec.grid.view.values.Colors;
import br.com.inovatec.grid.view.values.Dimens;
import br.com.inovatec.grid.view.values.Icons;
import br.com.inovatec.grid.view.values.Strings;

/**
 *
 * @author zrobe
 */
public class DisciplinaContent extends DefaultFormContent<Disciplina> {

    private TextField nomeTextField;
    private TextArea descricaoTextArea;
    private Button saveButton, editButton, closeButton, removeButton;

    public DisciplinaContent(DisciplinaView container) {
        super(container, Icons.IC_DISCIPLINA, true);
        this.createActionButtons();
    }

    @Override
    public DisciplinaView getContainer() {
        return (DisciplinaView) super.getContainer(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void buildMain() {
        // Add campos do formulario [BEGIN]
        // Adicionar Labels da primeira linha
        this.getMain().add(LabelFactory.getInstance().getLabel(Strings.DISCIPLINA_DIALOG_NOME_FIELD, this.getWidth(), 1, Dimens.WEIGHT_100));
        // Adicionar TextFields da primeira linha
        this.nomeTextField = TextFieldFactory.getInstance().getTextField(this.getWidth(), 1, Dimens.WEIGHT_100);
        this.getMain().add(this.nomeTextField);
        // Adicionar Labels da segunda linha
        this.getMain().add(LabelFactory.getInstance().getLabel(Strings.DISCIPLINA_DIALOG_DESCRICAO_FIELD, this.getWidth(), 1, Dimens.WEIGHT_100));
        // Adicionar TextFields da segunda linha
        this.descricaoTextArea = TextAreaFactory.getInstance().getTextArea(this.getWidth(), 1, Dimens.WEIGHT_100);
        this.getMain().add(this.descricaoTextArea);
        // Add campos do formulario [END]
        // Preencher dados da disciplina caso exista
        this.fillFieldsByObject(getContainer().getDisciplina());
        // Habilitar campos
        this.enableFields(this.getContainer().isForEdition());
    }

    @Override
    public Disciplina getFilledObject() throws FormException {
        Disciplina disciplina = getContainer().getDisciplina() == null ? new Disciplina() : getContainer().getDisciplina();

        if (StringUtils.isValid(this.nomeTextField.getText())) {
            disciplina.setNome(this.nomeTextField.getText());
        } else {
            throw new FormException(Strings.VALIDATION_MESSAGE_DISCIPLINA_NOME_NOT_NULL);
        }

        disciplina.setDescricao(this.descricaoTextArea.getText());

        return disciplina;
    }

    @Override
    public void fillFieldsByObject(Disciplina object) {

        if (object != null) {
            this.nomeTextField.setText(getContainer().getDisciplina().getNome());
            this.descricaoTextArea.setText(getContainer().getDisciplina().getDescricao());
        }

    }

    /**
     * Adicionar os botoes de acao da dialog
     */
    private void createActionButtons() {
        // Acoes da Janela (Lado esquerdo)
        this.removeButton = ButtonFactory.getInstance().getDeleteButton(Colors.COLOR_MAIN, new ButtonActionListener() {
            @Override
            public void action() {
                remove();
            }
        });
        removeButton.setType(Button.ButtonType.OPTION_LEFT);
        this.getOptions().add(this.removeButton);

        // Acoes da Janela (Lado direito)
        this.closeButton = ButtonFactory.getInstance().getCancelButton(Colors.COLOR_MAIN, new ButtonActionListener() {
            @Override
            public void action() {
                getContainer().close();
            }
        });
        closeButton.setType(Button.ButtonType.OPTION_RIGHT);
        this.getOptions().add(closeButton);

        this.editButton = ButtonFactory.getInstance().getEditButton(Colors.COLOR_MAIN, new ButtonActionListener() {
            @Override
            public void action() {
                getContainer().setForEdition(true);
                enableFields(true);
            }
        });
        this.editButton.setType(Button.ButtonType.OPTION_RIGHT);
        this.getOptions().add(this.editButton);

        this.saveButton = ButtonFactory.getInstance().getSaveButton(Colors.COLOR_MAIN, new ButtonActionListener() {
            @Override
            public void action() {
                // Salvar informacoes
                save();
            }
        });
        this.saveButton.setType(Button.ButtonType.OPTION_RIGHT);
        this.getOptions().add(this.saveButton);
    }

    /**
     * Acao do botao salvar
     */
    private void save() {
        try {
            // Salvar ou atualizar Disciplina
            ServiceProvider.getInstance().getDisciplinaService().save(getFilledObject());
            // Fechar Janela
            this.getContainer().close();
        } catch (FormException ex) {
            MessageFactory.showErrorMessage(this, ex.getMessage());
        } catch (ServiceException ex) {
            this.showInternalErrorMessage(ex);
        }
    }

    /**
     * Remover objeto
     */
    public void remove() {
        MessageFactory.showConfirmMessage(this, Strings.CONFIRM_MESSAGE_REMOVE_DISCIPLINA, new ResultAction() {
            @Override
            public void confirm() {
                try {
                    // Remover a disciplina
                    ServiceProvider.getInstance().getDisciplinaService().remove(getContainer().getDisciplina());
                    // Fechar a janela
                    getContainer().close();
                } catch (ServiceException ex) {
                    showInternalErrorMessage(ex);
                }
            }
        });
    }

    @Override
    public void enableFields(boolean enable) {
        super.enableFields(enable);
        this.removeButton.setEnabled(!enable);
        this.saveButton.setEnabled(enable);
        this.editButton.setEnabled(!enable);
    }

}
