/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.content;

import br.com.inovatec.grid.provider.ServiceProvider;
import br.com.inovatec.grid.entity.DiaAula;
import br.com.inovatec.grid.entity.Horario;
import br.com.inovatec.grid.service.exception.ServiceException;
import br.com.inovatec.grid.util.DateTimeUtils;
import br.com.inovatec.grid.view.component.FooterOptionsLayout;
import br.com.inovatec.grid.view.component.factory.ButtonFactory;
import br.com.inovatec.grid.view.component.factory.LabelFactory;
import br.com.inovatec.grid.view.component.factory.SelectOneMenuFactory;
import br.com.inovatec.grid.view.component.factory.TextFieldFactory;
import br.com.inovatec.grid.view.component.form.Button;
import br.com.inovatec.grid.view.component.form.Button.ButtonType;
import br.com.inovatec.grid.view.component.form.MaskedTextField;
import br.com.inovatec.grid.view.component.form.SelectOneMenu;
import br.com.inovatec.grid.view.component.listener.ButtonActionListener;
import br.com.inovatec.grid.view.component.util.ComponentUtils;
import br.com.inovatec.grid.view.content.exception.FormException;
import br.com.inovatec.grid.view.content.template.DefaultFormContent;
import br.com.inovatec.grid.view.content.validation.HorarioFormValidation;
import br.com.inovatec.grid.view.layout.HorarioEditView;
import br.com.inovatec.grid.view.session.Session;
import br.com.inovatec.grid.view.util.MessageFactory;
import br.com.inovatec.grid.view.values.Colors;
import br.com.inovatec.grid.view.values.Dimens;
import br.com.inovatec.grid.view.values.Strings;
import java.awt.Dimension;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author zrobe
 */
public class HorarioEditContent extends DefaultFormContent<Horario> {

    // Visual Components
    private SelectOneMenu<DiaAula> diaAulaSelectOneMenu;
    private MaskedTextField horaInicioMaskedTextField, horaFimMaskedTextField;

    public HorarioEditContent(HorarioEditView container) {
        super(container, null, false);
        this.createActionButtons();
    }

    @Override
    public HorarioEditView getContainer() {
        return (HorarioEditView) super.getContainer(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void initComponents() {
        super.initComponents();
        ((FooterOptionsLayout) this.getFooter()).hideOptionsLeftPanel();
        ((FooterOptionsLayout) this.getFooter()).hideOptionsRightPanel();
    }

    @Override
    protected void buildMain() {
        try {
            // Dias de aula da escola
            List<DiaAula> diasAula = ServiceProvider.getInstance().getDiaAulaService().listBy(Session.getInstance().getEscola().getPeriodoCorrente());
            Collections.sort(diasAula);
            // Dimensoes dos Labels da primeira linha
            Dimension labelsLineOneDimension = ComponentUtils.getLabelDimension(this.getWidth(), 1, Dimens.WEIGHT_100);
            // Adicionar Labels da primeira linha
            this.getMain().add(LabelFactory.getInstance().getLabel(Strings.HORARIOS_DIALOG_DIA_LABEL, labelsLineOneDimension));
            // Dimensoes dos Fields da primeira linha
            Dimension selectLineOneDimension = ComponentUtils.getSelectOneMenuDimension(this.getWidth(), 1, Dimens.WEIGHT_100);
            // Adicionar Fields da primeira linha
            this.diaAulaSelectOneMenu = SelectOneMenuFactory.getInstance().getSelectOneMenu(selectLineOneDimension, diasAula);
            this.diaAulaSelectOneMenu.setSelectedItem(getContainer().getHorario().getDiaAula());
            this.getMain().add(this.diaAulaSelectOneMenu);
            // Dimensoes dos Labels da primeira linha
            Dimension labelsLineTwoDimension = ComponentUtils.getLabelDimension(this.getWidth(), 2, Dimens.WEIGHT_50);
            // Adicionar Labels da primeira linha
            this.getMain().add(LabelFactory.getInstance().getLabel(Strings.HORARIOS_DIALOG_HORA_INICIO_LABEL, labelsLineTwoDimension));
            this.getMain().add(LabelFactory.getInstance().getLabel(Strings.HORARIOS_DIALOG_HORA_FIM_LABEL, labelsLineTwoDimension));
            // Dimensoes dos Fields da primeira linha
            Dimension fieldsLineTwoDimension = ComponentUtils.getTextFieldDimension(this.getWidth(), 2, Dimens.WEIGHT_50);
            // Campos
            this.horaInicioMaskedTextField = TextFieldFactory.getInstance().getTimeMaskedTextField(fieldsLineTwoDimension);
            this.horaInicioMaskedTextField.setText(DateTimeUtils.getMinimalFormattedTime(getContainer().getHorario().getInicio()));
            this.horaFimMaskedTextField = TextFieldFactory.getInstance().getTimeMaskedTextField(fieldsLineTwoDimension);
            this.horaFimMaskedTextField.setText(DateTimeUtils.getMinimalFormattedTime(getContainer().getHorario().getFim()));
            this.getMain().add(this.horaInicioMaskedTextField);
            this.getMain().add(this.horaFimMaskedTextField);
        } catch (ServiceException ex) {
            this.showInternalErrorMessage(ex);
        }
    }

    @Override
    public Horario getFilledObject() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void fillFieldsByObject(Horario object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Adicionar os botoes de acao da dialog
     */
    private void createActionButtons() {
        // Criar Botoes e Adicionar botoes
        // Acoes da Janela (Centro)
        // Dimensoes do botao
        Dimension buttonLineDimension = ComponentUtils.getFormButtonDimension(this.getWidth(), 2, Dimens.WEIGHT_50);
        // Botao de Remover
        // Botao de cancelar
        Button cancelButton = ButtonFactory.getInstance().getCancelButton(buttonLineDimension, Colors.COLOR_MAIN, new ButtonActionListener() {
            @Override
            public void action() {
                getContainer().close();
            }
        });
        cancelButton.setType(ButtonType.OPTION_CENTER);
        this.getOptions().add(cancelButton);
        // Botao de realizar login
        Button saveButton = ButtonFactory.getInstance().getSaveButton(buttonLineDimension, Colors.COLOR_MAIN, new ButtonActionListener() {
            @Override
            public void action() {
                save();
            }
        });
        saveButton.setType(ButtonType.OPTION_CENTER);
        this.getOptions().add(saveButton);
        // Definir botao de login como o principal
        this.getContainer().getDialog().getRootPane().setDefaultButton(saveButton);
    }

    /**
     * Metodo para realizar login
     */
    public void save() {
        try {
            // Adicionar horario a tabela
            Horario horario = HorarioFormValidation
                    .getHorarioByForm(
                            getContainer().getHorarios(),
                            getContainer().getIndex(),
                            diaAulaSelectOneMenu,
                            horaInicioMaskedTextField,
                            horaFimMaskedTextField
                    );
            // Atualizar os dados do Horario
            getContainer().getHorario().setDiaAula(horario.getDiaAula());
            getContainer().getHorario().setInicio(horario.getInicio());
            getContainer().getHorario().setFim(horario.getFim());
            // Fechar a janela
            getContainer().close();
        } catch (FormException ex) {
            MessageFactory.showErrorMessage(this, ex.getMessage());
        }
    }

}
