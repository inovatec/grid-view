/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.content;

import br.com.inovatec.grid.entity.Sala;
import br.com.inovatec.grid.provider.ServiceProvider;
import br.com.inovatec.grid.entity.Turma;
import br.com.inovatec.grid.service.exception.ServiceException;
import br.com.inovatec.grid.util.StringUtils;
import br.com.inovatec.grid.view.component.exception.NoItemSelectedException;
import br.com.inovatec.grid.view.component.factory.ButtonFactory;
import br.com.inovatec.grid.view.component.factory.LabelFactory;
import br.com.inovatec.grid.view.component.factory.SelectOneMenuFactory;
import br.com.inovatec.grid.view.component.factory.TextAreaFactory;
import br.com.inovatec.grid.view.component.factory.TextFieldFactory;
import br.com.inovatec.grid.view.component.form.Button;
import br.com.inovatec.grid.view.component.form.NumberTextField;
import br.com.inovatec.grid.view.component.form.SelectOneMenu;
import br.com.inovatec.grid.view.component.form.TextArea;
import br.com.inovatec.grid.view.component.form.TextField;
import br.com.inovatec.grid.view.component.listener.ButtonActionListener;
import br.com.inovatec.grid.view.content.exception.FormException;
import br.com.inovatec.grid.view.content.template.DefaultFormContent;
import br.com.inovatec.grid.view.controller.ViewController;
import br.com.inovatec.grid.view.layout.TurmaView;
import br.com.inovatec.grid.view.session.Session;
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
public class TurmaContent extends DefaultFormContent<Turma> {

    private NumberTextField anoNumberTextField;
    private TextField acronimoTextField;
    private TextArea descricaoTextArea;
    private SelectOneMenu<Sala> salaSelectOneMenu;
    private Button saveButton, editButton, closeButton, removeButton, disciplinasButton, horariosButton;

    public TurmaContent(TurmaView container) {
        super(container, Icons.IC_TURMA, true);
        this.createActionButtons();
    }

    @Override
    public TurmaView getContainer() {
        return (TurmaView) super.getContainer(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void buildMain() {
        try {
            // Add campos do formulario [BEGIN]
            // Adicionar Labels da primeira linha
            this.getMain().add(LabelFactory.getInstance().getLabel(Strings.TURMA_DIALOG_ANO_FIELD, this.getWidth(), 2, Dimens.WEIGHT_50));
            this.getMain().add(LabelFactory.getInstance().getLabel(Strings.TURMA_DIALOG_ACRONIMO_FIELD, this.getWidth(), 2, Dimens.WEIGHT_50));
            // Adicionar TextFields da primeira linha
            this.anoNumberTextField = TextFieldFactory.getInstance().getNumberTextField(this.getWidth(), 2, Dimens.WEIGHT_50);
            this.getMain().add(this.anoNumberTextField);
            this.acronimoTextField = TextFieldFactory.getInstance().getTextField(this.getWidth(), 2, Dimens.WEIGHT_50);
            this.getMain().add(this.acronimoTextField);
            // Adicionar Labels da segunda linha
            this.getMain().add(LabelFactory.getInstance().getLabel(Strings.TURMA_DIALOG_SALA_FIELD, this.getWidth(), 1, Dimens.WEIGHT_100));
            // Adicionar TextFields da segunda linha
            this.salaSelectOneMenu = SelectOneMenuFactory
                    .getInstance()
                    .getSelectOneMenu(
                            this.getWidth(), 1, Dimens.WEIGHT_100,
                            ServiceProvider.getInstance().getSalaService().listDisponiveis()
                    );
            this.getMain().add(this.salaSelectOneMenu);
            // Adicionar Labels da quarta linha
            this.getMain().add(LabelFactory.getInstance().getLabel(Strings.TURMA_DIALOG_DESCRICAO_FIELD, this.getWidth(), 1, Dimens.WEIGHT_100));
            // Adicionar TextFields da quarta linha
            this.descricaoTextArea = TextAreaFactory.getInstance().getTextArea(this.getWidth(), 1, Dimens.WEIGHT_100);
            this.getMain().add(this.descricaoTextArea);
            // Add campos do formulario [END]
            // Preencher dados da turma caso exista
            this.fillFieldsByObject(getContainer().getTurma());
            // Habilitar campos
            this.enableFields(this.getContainer().isForEdition());
        } catch (ServiceException ex) {
            this.showInternalErrorMessage(ex);
        }
    }

    @Override
    public Turma getFilledObject() throws FormException {
        Turma turma = getContainer().getTurma() == null ? new Turma() : getContainer().getTurma();

        // Verificar se o ano da turma é válido
        if (this.anoNumberTextField.getValue() > 0) {
            turma.setAno(this.anoNumberTextField.getValue());
        } else {
            throw new FormException(Strings.VALIDATION_MESSAGE_TURMA_ANO_NOT_NULL);
        }

        // Verificar se o acronimo da turma é válido
        if (StringUtils.isValid(this.acronimoTextField.getText())) {
            turma.setAcronimo(this.acronimoTextField.getText());
        } else {
            throw new FormException(Strings.VALIDATION_MESSAGE_TURMA_ACRONIMO_NOT_NULL);
        }

        try {
            // Atrelar Sala de Aula a Turma
            turma.setSala(this.salaSelectOneMenu.getSelectedItem());
        } catch (NoItemSelectedException ex) {
            throw new FormException(Strings.VALIDATION_MESSAGE_TURMA_SALA_NOT_NULL);
        }

        turma.setDescricao(this.descricaoTextArea.getText());
        turma.setPeriodoCorrente(Session.getInstance().getEscola().getPeriodoCorrente());

        return turma;
    }

    @Override
    public void fillFieldsByObject(Turma object) {

        if (object != null) {
            this.anoNumberTextField.setValue(getContainer().getTurma().getAno());
            this.acronimoTextField.setText(getContainer().getTurma().getAcronimo());
            this.salaSelectOneMenu.setSelectedItem(getContainer().getTurma().getSala());
            this.descricaoTextArea.setText(getContainer().getTurma().getDescricao());
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

        this.disciplinasButton = ButtonFactory.getInstance().getDefaultFormButton("Disciplinas", Colors.COLOR_MAIN, new ButtonActionListener() {
            @Override
            public void action() {
                ViewController.showTurmaDisciplinasView(getContainer(), getContainer().getTurma());
            }
        });
        this.disciplinasButton.setType(Button.ButtonType.OPTION_LEFT);
        this.getOptions().add(this.disciplinasButton);

        this.horariosButton = ButtonFactory.getInstance().getHorariosButton(Colors.COLOR_MAIN, new ButtonActionListener() {
            @Override
            public void action() {
                ViewController.showHorariosTurmaView(getContainer(), getContainer().getTurma());
            }
        });
        this.horariosButton.setType(Button.ButtonType.OPTION_LEFT);
        this.getOptions().add(this.horariosButton);

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
            // Turma de Aula preenchida
            Turma turma = getFilledObject();
            // Condicao para fechar a janela apos salvar
            boolean closeCondition = turma.getId() == null;
            // Salvar ou atualizar Turma
            ServiceProvider.getInstance().getTurmaService().save(turma);
            // Verificar se a janela devera ser fechada
            if (closeCondition) {
                // Fechar janela
                getContainer().close();
            } else {
                // Desabilitar campos
                this.enableFields(false);
            }
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
        MessageFactory.showConfirmMessage(this, Strings.CONFIRM_MESSAGE_REMOVE_TURMA, new ResultAction() {
            @Override
            public void confirm() {
                try {
                    // Remover a turma
                    ServiceProvider.getInstance().getTurmaService().remove(getContainer().getTurma());
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
        this.disciplinasButton.setEnabled(!enable);
        this.horariosButton.setEnabled(!enable);
    }

}
