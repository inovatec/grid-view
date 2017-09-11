/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.content;

import br.com.inovatec.grid.provider.ServiceProvider;
import br.com.inovatec.grid.entity.Sala;
import br.com.inovatec.grid.entity.enums.StatusSala;
import br.com.inovatec.grid.entity.enums.TipoSala;
import br.com.inovatec.grid.service.exception.ServiceException;
import br.com.inovatec.grid.util.StringUtils;
import br.com.inovatec.grid.view.component.exception.NoItemSelectedException;
import br.com.inovatec.grid.view.component.factory.ButtonFactory;
import br.com.inovatec.grid.view.component.factory.LabelFactory;
import br.com.inovatec.grid.view.component.factory.SelectOneMenuFactory;
import br.com.inovatec.grid.view.component.factory.TextAreaFactory;
import br.com.inovatec.grid.view.component.factory.TextFieldFactory;
import br.com.inovatec.grid.view.component.form.Button;
import br.com.inovatec.grid.view.component.form.SelectOneMenu;
import br.com.inovatec.grid.view.component.form.TextArea;
import br.com.inovatec.grid.view.component.form.TextField;
import br.com.inovatec.grid.view.component.listener.ButtonActionListener;
import br.com.inovatec.grid.view.content.exception.FormException;
import br.com.inovatec.grid.view.content.template.DefaultFormContent;
import br.com.inovatec.grid.view.controller.ViewController;
import br.com.inovatec.grid.view.layout.SalaView;
import br.com.inovatec.grid.view.util.MessageFactory;
import br.com.inovatec.grid.view.util.ResultAction;
import br.com.inovatec.grid.view.values.Colors;
import br.com.inovatec.grid.view.values.Dimens;
import br.com.inovatec.grid.view.values.Icons;
import br.com.inovatec.grid.view.values.Strings;
import java.util.Arrays;

/**
 *
 * @author zrobe
 */
public class SalaContent extends DefaultFormContent<Sala> {

    private TextField nomeTextField;
    private TextArea descricaoTextArea;
    private SelectOneMenu<TipoSala> tipoSalaSelectOneMenu;
    private SelectOneMenu<StatusSala> statusSalaSelectOneMenu;
    private Button saveButton, editButton, closeButton, removeButton, horariosButton;

    public SalaContent(SalaView container) {
        super(container, Icons.IC_SALA, true);
        this.createActionButtons();
    }

    @Override
    public SalaView getContainer() {
        return (SalaView) super.getContainer(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void buildMain() {
        // Add campos do formulario [BEGIN]
        // Adicionar Labels da primeira linha
        this.getMain().add(LabelFactory.getInstance().getLabel(Strings.SALA_DIALOG_NOME_FIELD, this.getWidth(), 1, Dimens.WEIGHT_100));
        // Adicionar TextFields da primeira linha
        this.nomeTextField = TextFieldFactory.getInstance().getTextField(this.getWidth(), 1, Dimens.WEIGHT_100, 100);
        this.getMain().add(this.nomeTextField);
        // Adicionar Labels da segunda linha
        this.getMain().add(LabelFactory.getInstance().getLabel(Strings.SALA_DIALOG_TIPO_FIELD, this.getWidth(), 2, Dimens.WEIGHT_50));
        this.getMain().add(LabelFactory.getInstance().getLabel(Strings.SALA_DIALOG_STATUS_FIELD, this.getWidth(), 2, Dimens.WEIGHT_50));
        // Adicionar TextFields da segunda linha
        this.tipoSalaSelectOneMenu = SelectOneMenuFactory.getInstance().getSelectOneMenu(this.getWidth(), 2, Dimens.WEIGHT_50, Arrays.asList(TipoSala.values()));
        this.getMain().add(this.tipoSalaSelectOneMenu);
        this.statusSalaSelectOneMenu = SelectOneMenuFactory.getInstance().getSelectOneMenu(this.getWidth(), 2, Dimens.WEIGHT_50, Arrays.asList(StatusSala.values()));
        this.getMain().add(this.statusSalaSelectOneMenu);
        // Adicionar Labels da terceira linha
        this.getMain().add(LabelFactory.getInstance().getLabel(Strings.SALA_DIALOG_DESCRICAO_FIELD, this.getWidth(), 1, Dimens.WEIGHT_100));
        // Adicionar TextFields da terceira linha
        this.descricaoTextArea = TextAreaFactory.getInstance().getTextArea(this.getWidth(), 1, Dimens.WEIGHT_100, 255);
        this.getMain().add(this.descricaoTextArea);
        // Add campos do formulario [END]
        // Preencher dados da sala caso exista
        this.fillFieldsByObject(getContainer().getSala());
        // Habilitar campos
        this.enableFields(this.getContainer().isForEdition());
    }

    @Override
    public Sala getFilledObject() throws FormException {
        Sala sala = getContainer().getSala() == null ? new Sala() : getContainer().getSala();

        // Verificar se o nome é válido
        if (StringUtils.isValid(this.nomeTextField.getText())) {
            sala.setNome(this.nomeTextField.getText());
        } else {
            throw new FormException(Strings.VALIDATION_MESSAGE_SALA_NOME_NOT_NULL);
        }

        try {
            // Verificar se o tipo da sala foi selecionado
            sala.setTipoSala(this.tipoSalaSelectOneMenu.getSelectedItem());
        } catch (NoItemSelectedException ex) {
            throw new FormException(Strings.VALIDATION_MESSAGE_SALA_TIPO_NOT_NULL);
        }

        try {
            // Verificar se o tipo da sala foi selecionado
            sala.setStatus(this.statusSalaSelectOneMenu.getSelectedItem());
        } catch (NoItemSelectedException ex) {
            throw new FormException(Strings.VALIDATION_MESSAGE_SALA_STATUS_NOT_NULL);
        }

        sala.setDescricao(this.descricaoTextArea.getText());

        return sala;
    }

    @Override
    public void fillFieldsByObject(Sala object) {

        if (object != null) {
            this.nomeTextField.setText(getContainer().getSala().getNome());
            this.tipoSalaSelectOneMenu.setSelectedItem(getContainer().getSala().getTipoSala());
            this.statusSalaSelectOneMenu.setSelectedItem(getContainer().getSala().getStatus());
            this.descricaoTextArea.setText(getContainer().getSala().getDescricao());
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

        this.horariosButton = ButtonFactory.getInstance().getHorariosButton(Colors.COLOR_MAIN, new ButtonActionListener() {
            @Override
            public void action() {
                ViewController.showHorariosSalaView(getContainer(), getContainer().getSala());
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
            // Sala de Aula preenchida
            Sala sala = getFilledObject();
            // Condicao para fechar a janela apos salvar
            boolean closeCondition = sala.getId() == null;
            // Salvar ou atualizar Sala
            ServiceProvider.getInstance().getSalaService().save(sala);
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
        MessageFactory.showConfirmMessage(this, Strings.CONFIRM_MESSAGE_REMOVE_SALA, new ResultAction() {
            @Override
            public void confirm() {
                try {
                    // Remover a sala
                    ServiceProvider.getInstance().getSalaService().remove(getContainer().getSala());
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
        this.horariosButton.setEnabled(!enable);
    }

}
