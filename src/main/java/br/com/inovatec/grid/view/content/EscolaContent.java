/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.content;

import br.com.inovatec.grid.provider.ServiceProvider;
import br.com.inovatec.grid.entity.Escola;
import br.com.inovatec.grid.service.exception.ServiceException;
import br.com.inovatec.grid.util.DateTimeUtils;
import br.com.inovatec.grid.util.StringUtils;
import br.com.inovatec.grid.view.component.factory.ButtonFactory;
import br.com.inovatec.grid.view.component.factory.LabelFactory;
import br.com.inovatec.grid.view.component.factory.TextFieldFactory;
import br.com.inovatec.grid.view.component.form.Button;
import br.com.inovatec.grid.view.component.form.Label;
import br.com.inovatec.grid.view.component.form.MaskedTextField;
import br.com.inovatec.grid.view.component.form.NumberTextField;
import br.com.inovatec.grid.view.component.form.TextField;
import br.com.inovatec.grid.view.component.listener.ButtonActionListener;
import br.com.inovatec.grid.view.component.util.ComponentUtils;
import br.com.inovatec.grid.view.content.exception.FormException;
import br.com.inovatec.grid.view.content.template.DefaultFormContent;
import br.com.inovatec.grid.view.controller.ViewController;
import br.com.inovatec.grid.view.layout.EscolaView;
import br.com.inovatec.grid.view.session.Session;
import br.com.inovatec.grid.view.util.MessageFactory;
import br.com.inovatec.grid.view.util.MessageResultAction;
import br.com.inovatec.grid.view.values.Colors;
import br.com.inovatec.grid.view.values.Dimens;
import br.com.inovatec.grid.view.values.Icons;
import br.com.inovatec.grid.view.values.Strings;
import java.awt.Dimension;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 *
 * @author zrobe
 */
public class EscolaContent extends DefaultFormContent<Escola> {

    // Condicao para encerrar a janela
    private boolean closeCondition = true;

    // Campos do Formulario
    private TextField nomeTextField;
    private NumberTextField periodoCorrenteNumberTextField, semanasLetivasNumberTextField, duracaoAulaNumberTextField;
    private MaskedTextField inicioAulaMaskedTextField;

    // Warnings
    private Label diasAulaLabelWarning, horariosLabelWarning;

    // Botoes
    private Button diasAulaButton, horasAulaButton, editButton, saveButton;

    public EscolaContent(EscolaView container) {
        super(container, Icons.IC_ESCOLA, true);
        this.createActionButtons();
    }

    @Override
    public EscolaView getContainer() {
        return (EscolaView) super.getContainer();
    }

    @Override
    protected void buildMain() {
        // Add campos do formulario [BEGIN]
        // Dimensoes dos Labels da primeira linha
        Dimension labelsLineOneDimension = ComponentUtils.getLabelDimension(this.getWidth(), 1, Dimens.WEIGHT_100);
        // Adicionar Labels da primeira linha
        this.getMain().add(LabelFactory.getInstance().getLabel(Strings.ESCOLA_DIALOG_NOME_FIELD, labelsLineOneDimension));
        // Dimensoes dos TextFields da segunda linha
        Dimension fieldsLineOneDimension = ComponentUtils.getTextFieldDimension(this.getWidth(), 1, Dimens.WEIGHT_100);
        // Adicionar TextFields da segunda linha
        this.nomeTextField = TextFieldFactory.getInstance().getTextField(fieldsLineOneDimension);
        this.getMain().add(this.nomeTextField);

        // Dimensoes dos Labels da segunda linha
        Dimension labelsLineTwoDimension = ComponentUtils.getLabelDimension(this.getWidth(), 4, Dimens.WEIGHT_25);
        // Adicionar Labels da primeira linha
        this.getMain().add(LabelFactory.getInstance().getLabel(Strings.ESCOLA_DIALOG_PERIODO_CORRENTE_FIELD, labelsLineTwoDimension));
        this.getMain().add(LabelFactory.getInstance().getLabel(Strings.ESCOLA_DIALOG_DURACAO_AULA_FIELD, labelsLineTwoDimension));
        this.getMain().add(LabelFactory.getInstance().getLabel(Strings.ESCOLA_DIALOG_SEMANAS_LETIVAS_FIELD, labelsLineTwoDimension));
        this.getMain().add(LabelFactory.getInstance().getLabel(Strings.ESCOLA_DIALOG_INICIO_AULA_FIELD, labelsLineTwoDimension));
        // Dimensoes dos TextFields da segunda linha
        Dimension fieldsLineTwoDimension = ComponentUtils.getTextFieldDimension(this.getWidth(), 4, Dimens.WEIGHT_25);
        // Adicionar TextFields da segunda linha
        this.periodoCorrenteNumberTextField = TextFieldFactory.getInstance().getNumberTextField(fieldsLineTwoDimension, 4);
        this.getMain().add(this.periodoCorrenteNumberTextField);
        this.duracaoAulaNumberTextField = TextFieldFactory.getInstance().getNumberTextField(fieldsLineTwoDimension, 3);
        this.getMain().add(this.duracaoAulaNumberTextField);
        this.semanasLetivasNumberTextField = TextFieldFactory.getInstance().getNumberTextField(fieldsLineTwoDimension, 3);
        this.getMain().add(this.semanasLetivasNumberTextField);
        this.inicioAulaMaskedTextField = TextFieldFactory.getInstance().getTimeMaskedTextField(fieldsLineTwoDimension);
        this.getMain().add(this.inicioAulaMaskedTextField);
        // Add campos do formulario [END]
        // Preencher campos
        if (Session.getInstance().getEscola() != null) {
            this.fillFieldsByObject(Session.getInstance().getEscola());
        }
        // Habilitar campos e botoes de acao
        this.enableFields(this.getContainer().isForEdition() && Session.getInstance().getEscola() == null);
        // Avisos
        showWarnings();
    }

    @Override
    public Escola getFilledObject() throws FormException {
        Escola escola = Session.getInstance().getEscola();
        // Verificar se existe escola
        if (escola == null) {
            escola = new Escola();
        }
        // Validar e Adicionar dados dos campos ao objeto escola
        if (StringUtils.isValid(this.nomeTextField.getText())) {
            escola.setNome(this.nomeTextField.getText());
        } else {
            throw new FormException(Strings.VALIDATION_MESSAGE_ESCOLA_NOME_NOT_NULL);
        }

        if (this.periodoCorrenteNumberTextField.getValue() != 0) {
            escola.setPeriodoCorrente(this.periodoCorrenteNumberTextField.getValue());
        } else {
            throw new FormException(Strings.VALIDATION_MESSAGE_ESCOLA_PERIODO_CORRENTE_NOT_NULL);
        }

        if (this.duracaoAulaNumberTextField.getValue() != 0) {
            escola.setDuracaoAula(this.duracaoAulaNumberTextField.getValue());
        } else {
            throw new FormException(Strings.VALIDATION_MESSAGE_ESCOLA_DURACAO_AULA_NOT_NULL);
        }

        if (this.semanasLetivasNumberTextField.getValue() != 0) {
            escola.setSemanasLetivas(this.semanasLetivasNumberTextField.getValue());
        } else {
            throw new FormException(Strings.VALIDATION_MESSAGE_ESCOLA_SEMANAS_LETIVAS_NOT_NULL);
        }

        try {
            escola.setInicioAula(DateTimeUtils.getTime(this.inicioAulaMaskedTextField.getText()));
        } catch (DateTimeParseException ex) {
            throw new FormException(Strings.getReplacedString(Strings.VALIDATION_MESSAGE_INVALID_TIME, "de início das aulas"));
        }

        return escola;

    }

    /**
     * Preencher os campos do formulario com os dados da escola
     *
     * @param escola
     */
    @Override
    public void fillFieldsByObject(Escola escola) {
        this.nomeTextField.setText(escola.getNome());
        this.periodoCorrenteNumberTextField.setValue(escola.getPeriodoCorrente() != null ? escola.getPeriodoCorrente() : LocalDate.now().getYear());
        this.semanasLetivasNumberTextField.setValue(escola.getSemanasLetivas());
        this.duracaoAulaNumberTextField.setValue(escola.getDuracaoAula());
        this.inicioAulaMaskedTextField.setText(DateTimeUtils.getMinimalFormattedTime(escola.getInicioAula()));
    }

    /**
     * Adicionar os botoes de acao da dialog
     */
    private void createActionButtons() {
        // Criar Botoes e Adicionar botoes
        // Acoes do Objeto (Lado esquerdo)
        this.diasAulaButton = ButtonFactory.getInstance().increase(
                Dimens.PLUS_40,
                ButtonFactory.getInstance().getDefaultFormButton(
                        Strings.ESCOLA_DIALOG_DIAS_AULA_BUTTON, Colors.COLOR_MAIN, new ButtonActionListener() {
                    @Override
                    public void action() {
                        // Chamar janela de preenchimento dos dias de aula da escola
                        ViewController.showDiasAulaDialog(getContainer());
                    }
                })
        );
        this.diasAulaButton.setType(Button.ButtonType.OPTION_LEFT);
        this.getOptions().add(this.diasAulaButton);

        this.horasAulaButton = ButtonFactory.getInstance().increase(Dimens.PLUS_40,
                ButtonFactory.getInstance().getDefaultFormButton(Strings.ESCOLA_DIALOG_HORAS_AULAS_BUTTON, Colors.COLOR_MAIN, new ButtonActionListener() {
                    @Override
                    public void action() {
                        ViewController.showHorariosEscolaView(getContainer());
                    }
                })
        );
        this.horasAulaButton.setType(Button.ButtonType.OPTION_LEFT);
        this.getOptions().add(this.horasAulaButton);

        // Acoes da Janela (Lado direito)
        Button closeButton = ButtonFactory.getInstance().getCloseButton(Colors.COLOR_MAIN, new ButtonActionListener() {
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
     * Habilitar campos e botoes para acoes da janela
     *
     * @param enable
     */
    @Override
    public void enableFields(boolean enable) {
        super.enableFields(enable);
        this.saveButton.setEnabled(enable);
        this.editButton.setEnabled(!enable);
        this.diasAulaButton.setEnabled(!enable);
        // Verificar se os dias de aula da escola foram configurados
        if (diasAulaIsEmpty()) {
            this.horasAulaButton.setEnabled(false);
        } else {
            this.horasAulaButton.setEnabled(!enable);
        }
    }

    /**
     * Acao do botao salvar
     */
    private void save() {
        try {
            // Resgatar escola por meio do formulario
            Session.getInstance().setEscola(this.getFilledObject());
            // Salvar ou atualizar a escola
            ServiceProvider.getInstance().getEscolaService().save(Session.getInstance().getEscola());
            // Exibir avisos
            this.showWarnings();
            // Fechar janela
            this.enableFields(false);
        } catch (FormException ex) {
            MessageFactory.showErrorMessage(this, ex.getMessage());
        } catch (ServiceException ex) {
            this.showInternalErrorMessage(ex);
        }
    }

    /**
     * Permitir encerramento da janela
     *
     * @return
     */
    public boolean allowClosing() {
        if (diasAulaIsEmpty() || horariosIsEmpty()) {
            MessageFactory.showConfirmMessage(this, Strings.VALIDATION_MESSAGE_ESCOLA_DADOS_INCOMPLETOS, new MessageResultAction() {
                @Override
                public void confirm() {
                    closeCondition = true;
                }

                @Override
                public void cancel() {
                    closeCondition = false;
                }

                @Override
                public void recuse() {
                    closeCondition = false;
                }
            });
        }
        return this.closeCondition;
    }

    /**
     * Verificar se existem dias de aula
     *
     * @return
     */
    private boolean diasAulaIsEmpty() {
        try {
            return Session.getInstance().getEscola() == null || ServiceProvider.getInstance().getDiaAulaService().listBy(Session.getInstance().getEscola().getPeriodoCorrente()).isEmpty();
        } catch (ServiceException ex) {
            this.showInternalErrorMessage(ex);
        }
        return true;
    }

    /**
     * Verificar se existem dias de aula
     *
     * @return
     */
    private boolean horariosIsEmpty() {
        try {
            return Session.getInstance().getEscola() == null || ServiceProvider.getInstance().getHorarioService().getOfPeriodoCorrente(Session.getInstance().getEscola()).isEmpty();
        } catch (ServiceException ex) {
            this.showInternalErrorMessage(ex);
        }
        return true;
    }

    /**
     * Adicionar avisos
     */
    public void showWarnings() {
        // Dimensoes padrao dos warnings
        Dimension labelDimension = ComponentUtils.getLabelDimension(this.getWidth(), 1, Dimens.WEIGHT_100);

        // Verificar se existem dias de aula        
        if (diasAulaIsEmpty()) {
            if (this.diasAulaLabelWarning == null) {
                this.diasAulaLabelWarning = LabelFactory.getInstance().getWarningLabel(Strings.WARNING_MESSAGE_ESCOLA_SEM_DIAS_AULA, labelDimension);
                this.getMain().add(this.diasAulaLabelWarning);
                this.closeCondition = false;
            }
        } else if (this.diasAulaLabelWarning != null) {
            this.getMain().remove(this.diasAulaLabelWarning);
            this.horasAulaButton.setEnabled(true);
            this.diasAulaLabelWarning = null;
            this.closeCondition = true;
        }

        // Verificar se existem horarios de aula        
        if (horariosIsEmpty()) {
            if (this.horariosLabelWarning == null) {
                this.horariosLabelWarning = LabelFactory.getInstance().getWarningLabel(Strings.WARNING_MESSAGE_ESCOLA_SEM_HORARIOS, labelDimension);
                this.getMain().add(this.horariosLabelWarning);
                this.closeCondition = false;
            }
        } else if (this.horariosLabelWarning != null) {
            this.getMain().remove(this.horariosLabelWarning);
            this.horariosLabelWarning = null;
            this.closeCondition = true;
        }

        this.getMain().revalidate();
        this.getMain().repaint();
    }

    /**
     * Atualizar componentes
     */
    public void refresh() {
        // Adicionar avisos
        this.showWarnings();
    }

}
