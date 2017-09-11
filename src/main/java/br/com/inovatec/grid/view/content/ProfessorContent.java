/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.content;

import br.com.inovatec.grid.provider.ServiceProvider;
import br.com.inovatec.grid.entity.Professor;
import br.com.inovatec.grid.service.exception.ServiceException;
import br.com.inovatec.grid.util.StringUtils;
import br.com.inovatec.grid.view.component.factory.ButtonFactory;
import br.com.inovatec.grid.view.component.factory.LabelFactory;
import br.com.inovatec.grid.view.component.factory.TextFieldFactory;
import br.com.inovatec.grid.view.component.form.Button;
import br.com.inovatec.grid.view.component.form.MaskedTextField;
import br.com.inovatec.grid.view.component.form.TextField;
import br.com.inovatec.grid.view.component.listener.ButtonActionListener;
import br.com.inovatec.grid.view.content.exception.FormException;
import br.com.inovatec.grid.view.content.template.DefaultFormContent;
import br.com.inovatec.grid.view.controller.ViewController;
import br.com.inovatec.grid.view.layout.ProfessorView;
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
public class ProfessorContent extends DefaultFormContent<Professor> {

    // Dados do Professor
    private TextField nomeTextField, rgTextField, emailTextField;
    private MaskedTextField cpfTextField, telefoneTextField, celularTextField, ufTextField;
    // Dados do Endereco do Professor
    private TextField logradouroTextField, numeroTextField, complementoTextField,
            bairroTextField, cidadeTextField, paisTextField;

    private Button saveButton, editButton, closeButton, removeButton, disciplinasButton, horariosButton;

    public ProfessorContent(ProfessorView container) {
        super(container, Icons.IC_PROFESSOR, true);
        this.createActionButtons();
    }

    @Override
    public ProfessorView getContainer() {
        return (ProfessorView) super.getContainer(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void buildMain() {
        // Add campos do formulario [BEGIN]
        // Linha 1
        this.getMain().add(LabelFactory.getInstance().getLabel(Strings.PROFESSOR_DIALOG_NOME_FIELD, this.getWidth(), 1, Dimens.WEIGHT_100));
        this.nomeTextField = TextFieldFactory.getInstance().getTextField(this.getWidth(), 1, Dimens.WEIGHT_100, 100);
        this.getMain().add(this.nomeTextField);
        // Linha 2
        this.getMain().add(LabelFactory.getInstance().getLabel(Strings.PROFESSOR_DIALOG_RG_FIELD, this.getWidth(), 2, Dimens.WEIGHT_50));
        this.getMain().add(LabelFactory.getInstance().getLabel(Strings.PROFESSOR_DIALOG_CPF_FIELD, this.getWidth(), 2, Dimens.WEIGHT_50));
        this.rgTextField = TextFieldFactory.getInstance().getTextField(this.getWidth(), 2, Dimens.WEIGHT_50, 50);
        this.cpfTextField = TextFieldFactory.getInstance().getMaskedTextField(this.getWidth(), 2, Dimens.WEIGHT_50, "###.###.###-##");
        this.getMain().add(this.rgTextField);
        this.getMain().add(this.cpfTextField);
        // Linha 3
        this.getMain().add(LabelFactory.getInstance().getLabel(Strings.PROFESSOR_DIALOG_EMAIL_FIELD, this.getWidth(), 3, Dimens.WEIGHT_33));
        this.getMain().add(LabelFactory.getInstance().getLabel(Strings.PROFESSOR_DIALOG_TELEFONE_FIELD, this.getWidth(), 3, Dimens.WEIGHT_33));
        this.getMain().add(LabelFactory.getInstance().getLabel(Strings.PROFESSOR_DIALOG_CELULAR_FIELD, this.getWidth(), 3, Dimens.WEIGHT_33));
        this.emailTextField = TextFieldFactory.getInstance().getTextField(this.getWidth(), 3, Dimens.WEIGHT_33, 100);
        this.telefoneTextField = TextFieldFactory.getInstance().getMaskedTextField(this.getWidth(), 3, Dimens.WEIGHT_33, "(##) ####-####");
        this.celularTextField = TextFieldFactory.getInstance().getMaskedTextField(this.getWidth(), 3, Dimens.WEIGHT_33, "(##) #####-####");
        this.getMain().add(this.emailTextField);
        this.getMain().add(this.telefoneTextField);
        this.getMain().add(this.celularTextField);
        // Linha 4
        this.getMain().add(LabelFactory.getInstance().getLabel(Strings.PROFESSOR_DIALOG_ENDERECO_LOGRADOURO_FIELD, this.getWidth(), 2, Dimens.WEIGHT_75));
        this.getMain().add(LabelFactory.getInstance().getLabel(Strings.PROFESSOR_DIALOG_ENDERECO_NUMERO_FIELD, this.getWidth(), 2, Dimens.WEIGHT_25));
        this.logradouroTextField = TextFieldFactory.getInstance().getTextField(this.getWidth(), 2, Dimens.WEIGHT_75, 100);
        this.numeroTextField = TextFieldFactory.getInstance().getTextField(this.getWidth(), 2, Dimens.WEIGHT_25, 10);
        this.getMain().add(this.logradouroTextField);
        this.getMain().add(this.numeroTextField);
        // Linha 5
        this.getMain().add(LabelFactory.getInstance().getLabel(Strings.PROFESSOR_DIALOG_ENDERECO_COMPLEMENTO_FIELD, this.getWidth(), 2, Dimens.WEIGHT_66));
        this.getMain().add(LabelFactory.getInstance().getLabel(Strings.PROFESSOR_DIALOG_ENDERECO_BAIRRO_FIELD, this.getWidth(), 2, Dimens.WEIGHT_33));
        this.complementoTextField = TextFieldFactory.getInstance().getTextField(this.getWidth(), 2, Dimens.WEIGHT_66, 100);
        this.bairroTextField = TextFieldFactory.getInstance().getTextField(this.getWidth(), 2, Dimens.WEIGHT_33, 100);
        this.getMain().add(this.complementoTextField);
        this.getMain().add(this.bairroTextField);
        // Linha 6
        this.getMain().add(LabelFactory.getInstance().getLabel(Strings.PROFESSOR_DIALOG_ENDERECO_CIDADE_FIELD, this.getWidth(), 3, Dimens.WEIGHT_33));
        this.getMain().add(LabelFactory.getInstance().getLabel(Strings.PROFESSOR_DIALOG_ENDERECO_UF_FIELD, this.getWidth(), 3, Dimens.WEIGHT_33));
        this.getMain().add(LabelFactory.getInstance().getLabel(Strings.PROFESSOR_DIALOG_ENDERECO_PAIS_FIELD, this.getWidth(), 3, Dimens.WEIGHT_33));
        this.cidadeTextField = TextFieldFactory.getInstance().getTextField(this.getWidth(), 3, Dimens.WEIGHT_33, 100);
        this.ufTextField = TextFieldFactory.getInstance().getMaskedTextField(this.getWidth(), 3, Dimens.WEIGHT_33, "UU");
        this.paisTextField = TextFieldFactory.getInstance().getTextField(this.getWidth(), 3, Dimens.WEIGHT_33, 100);
        this.getMain().add(this.cidadeTextField);
        this.getMain().add(this.ufTextField);
        this.getMain().add(this.paisTextField);

        // Add campos do formulario [END]
        // Preencher dados da professor caso exista
        this.fillFieldsByObject(getContainer().getProfessor());
        // Habilitar campos
        this.enableFields(this.getContainer().isForEdition());
    }

    @Override
    public Professor getFilledObject() throws FormException {
        Professor professor = getContainer().getProfessor();

        // Verificar se o nome é válido
        if (StringUtils.isValid(this.nomeTextField.getText())) {
            professor.setNome(this.nomeTextField.getText());
        } else {
            throw new FormException(Strings.VALIDATION_MESSAGE_PROFESSOR_NOME_NOT_NULL);
        }

        // Verificar se o rg é válido
        if (StringUtils.isValid(this.rgTextField.getText())) {
            professor.setRg(this.rgTextField.getText());
        } else {
            throw new FormException(Strings.VALIDATION_MESSAGE_PROFESSOR_RG_NOT_NULL);
        }

        // Verificar se o cpf é válido
        if (StringUtils.isValid(this.cpfTextField.getTextWithoutMask())) {
            professor.setCpf(this.cpfTextField.getTextWithoutMask());
        } else {
            throw new FormException(Strings.VALIDATION_MESSAGE_PROFESSOR_CPF_NOT_NULL);
        }

        // Verificar se o e-mail é válido
        if (StringUtils.isValid(this.emailTextField.getText())) {
            professor.setEmail(this.emailTextField.getText());
        } else {
            throw new FormException(Strings.VALIDATION_MESSAGE_PROFESSOR_EMAIL_NOT_NULL);
        }

        // Verificar se algum telefone foi informado
        if (StringUtils.isValid(this.telefoneTextField.getTextWithoutMask()) || StringUtils.isValid(this.celularTextField.getTextWithoutMask())) {
            professor.setTelefone(StringUtils.isValid(this.telefoneTextField.getTextWithoutMask()) ? this.telefoneTextField.getTextWithoutMask() : null);
            professor.setCelular(StringUtils.isValid(this.celularTextField.getTextWithoutMask()) ? this.celularTextField.getTextWithoutMask() : null);
        } else {
            throw new FormException(Strings.VALIDATION_MESSAGE_PROFESSOR_TELEFONE_CELULAR_NOT_NULL);
        }

        // Verificar se o logradouro do endereco é válido
        if (StringUtils.isValid(this.logradouroTextField.getText())) {
            professor.getEndereco().setLogradouro(this.logradouroTextField.getText());
        } else {
            throw new FormException(Strings.VALIDATION_MESSAGE_PROFESSOR_ENDERECO_LOGRADOURO_NOT_NULL);
        }

        // Verificar se o bairro do endereco é válido
        if (StringUtils.isValid(this.bairroTextField.getText())) {
            professor.getEndereco().setBairro(this.bairroTextField.getText());
        } else {
            throw new FormException(Strings.VALIDATION_MESSAGE_PROFESSOR_ENDERECO_BAIRRO_NOT_NULL);
        }

        // Verificar se a cidade do endereco é válida
        if (StringUtils.isValid(this.cidadeTextField.getText())) {
            professor.getEndereco().setCidade(this.cidadeTextField.getText());
        } else {
            throw new FormException(Strings.VALIDATION_MESSAGE_PROFESSOR_ENDERECO_CIDADE_NOT_NULL);
        }

        // Verificar se a uf do endereco é válida
        if (StringUtils.isValid(this.ufTextField.getText())) {
            professor.getEndereco().setUf(this.ufTextField.getText());
        } else {
            throw new FormException(Strings.VALIDATION_MESSAGE_PROFESSOR_ENDERECO_UF_NOT_NULL);
        }

        // Verificar se o pais do endereco é válido
        if (StringUtils.isValid(this.paisTextField.getText())) {
            professor.getEndereco().setPais(this.paisTextField.getText());
        } else {
            throw new FormException(Strings.VALIDATION_MESSAGE_PROFESSOR_ENDERECO_PAIS_NOT_NULL);
        }

        professor.getEndereco().setNumero(this.numeroTextField.getText());
        professor.getEndereco().setComplemento(this.complementoTextField.getText());

        return professor;
    }

    @Override
    public void fillFieldsByObject(Professor object) {

        if (object.getId() != null) {
            this.nomeTextField.setText(getContainer().getProfessor().getNome());
            this.rgTextField.setText(getContainer().getProfessor().getRg());
            this.emailTextField.setText(getContainer().getProfessor().getEmail());
            this.cpfTextField.setText(getContainer().getProfessor().getCpf());
            this.telefoneTextField.setText(getContainer().getProfessor().getTelefone());
            this.celularTextField.setText(getContainer().getProfessor().getCelular());
            this.logradouroTextField.setText(getContainer().getProfessor().getEndereco().getLogradouro());
            this.numeroTextField.setText(getContainer().getProfessor().getEndereco().getNumero());
            this.complementoTextField.setText(getContainer().getProfessor().getEndereco().getComplemento());
            this.bairroTextField.setText(getContainer().getProfessor().getEndereco().getBairro());
            this.cidadeTextField.setText(getContainer().getProfessor().getEndereco().getCidade());
            this.ufTextField.setText(getContainer().getProfessor().getEndereco().getUf());
            this.paisTextField.setText(getContainer().getProfessor().getEndereco().getPais());
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

        this.disciplinasButton = ButtonFactory.getInstance().increase(
                Dimens.PLUS_30,
                ButtonFactory.getInstance().getDefaultFormButton("Competências", Colors.COLOR_MAIN, new ButtonActionListener() {
                    @Override
                    public void action() {
                        ViewController.showCompetenciasProfessorView(getContainer());
                    }
                })
        );
        this.disciplinasButton.setType(Button.ButtonType.OPTION_LEFT);
        this.getOptions().add(this.disciplinasButton);

        this.horariosButton = ButtonFactory.getInstance().getHorariosButton(Colors.COLOR_MAIN, new ButtonActionListener() {
            @Override
            public void action() {
                ViewController.showHorariosProfessorView(getContainer(), getContainer().getProfessor());
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
            // Professor de Aula preenchida
            Professor professor = getFilledObject();
            // Condicao para fechar a janela apos salvar
            boolean closeCondition = professor.getId() == null;
            // Salvar ou atualizar Professor
            ServiceProvider.getInstance().getProfessorService().save(professor);
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
        MessageFactory.showConfirmMessage(this, Strings.CONFIRM_MESSAGE_REMOVE_PROFESSOR, new ResultAction() {
            @Override
            public void confirm() {
                try {
                    // Remover a professor
                    ServiceProvider.getInstance().getProfessorService().remove(getContainer().getProfessor());
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
