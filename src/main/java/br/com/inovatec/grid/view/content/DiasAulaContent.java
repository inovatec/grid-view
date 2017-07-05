/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.content;

import br.com.inovatec.grid.provider.ServiceProvider;
import br.com.inovatec.grid.entity.DiaAula;
import br.com.inovatec.grid.service.exception.ServiceException;
import br.com.inovatec.grid.util.DateTimeUtils;
import br.com.inovatec.grid.view.component.factory.ButtonFactory;
import br.com.inovatec.grid.view.component.factory.CheckboxFactory;
import br.com.inovatec.grid.view.component.factory.LabelFactory;
import br.com.inovatec.grid.view.component.factory.TextFieldFactory;
import br.com.inovatec.grid.view.component.form.Button;
import br.com.inovatec.grid.view.component.form.Checkbox;
import br.com.inovatec.grid.view.component.form.NumberTextField;
import br.com.inovatec.grid.view.component.listener.ButtonActionListener;
import br.com.inovatec.grid.view.component.util.ComponentUtils;
import br.com.inovatec.grid.view.content.template.DefaultFormContent;
import br.com.inovatec.grid.view.layout.template.DefaultView;
import br.com.inovatec.grid.view.session.Session;
import br.com.inovatec.grid.view.util.MessageFactory;
import br.com.inovatec.grid.view.values.Colors;
import br.com.inovatec.grid.view.values.Dimens;
import br.com.inovatec.grid.view.values.Icons;
import br.com.inovatec.grid.view.values.Strings;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zrobe
 */
public class DiasAulaContent extends DefaultFormContent<List<DiaAula>> {

    private List<DiaAula> diasAula;
    private final List<DiaAulaContainer> diaAulaContainers;

    public DiasAulaContent(DefaultView container) {
        super(container, Icons.IC_DIAS_AULA, true);
        this.createActionButtons();
        this.diaAulaContainers = new ArrayList<>();
    }

    @Override
    protected void buildMain() {

        try {
            this.diasAula = ServiceProvider
                    .getInstance()
                    .getDiaAulaService()
                    .listByPeriodoCorrente();

            // Labels
            Dimension labelColumnOneDimension = ComponentUtils.getLabelDimension(this.getWidth(), 2, Dimens.WEIGHT_66);
            Dimension labelColumnTwoDimension = ComponentUtils.getLabelDimension(this.getWidth(), 2, Dimens.WEIGHT_33);
            Dimension checkboxDimension = ComponentUtils.getCheckboxDimension(this.getWidth(), 2, Dimens.WEIGHT_66);
            Dimension textFieldDimension = ComponentUtils.getTextFieldDimension(this.getWidth(), 2, Dimens.WEIGHT_33);

            this.getMain().add(LabelFactory.getInstance().getLabel("Dia", labelColumnOneDimension));
            this.getMain().add(LabelFactory.getInstance().getLabel("Total de aulas", labelColumnTwoDimension));

            // Checkboxes com os dias da semana
            for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
                DiaAulaContainer diaAulaContainer = new DiaAulaContainer(
                        CheckboxFactory.getInstance().getCheckbox(DateTimeUtils.getDayName(dayOfWeek), checkboxDimension),
                        TextFieldFactory.getInstance().getNumberTextField(textFieldDimension, 2)
                );
                // Atribuir novo dia de aula
                diaAulaContainer.diaAula = new DiaAula();
                diaAulaContainer.diaAula.setDiaDaSemana(dayOfWeek);
                diaAulaContainer.diaAula.setPeriodo(Session.getInstance().getEscola().getPeriodoCorrente());

                // Percorrer dias de aula ja cadastrados
                for (DiaAula diaAula : this.diasAula) {
                    if (diaAula.getDiaDaSemana().equals(dayOfWeek)) {
                        // Checar o checkbox
                        diaAulaContainer.check();
                        // Identificador do dia de aula
                        diaAulaContainer.diaAula.setId(diaAula.getId());
                        // Total de aulas do dia
                        diaAulaContainer.totalAulasTextField.setValue(diaAula.getTotalAulas());
                    }
                }

                this.diaAulaContainers.add(diaAulaContainer);
                this.getMain().add(diaAulaContainer.dayOfWeekCheckbox);
                this.getMain().add(diaAulaContainer.totalAulasTextField);
            }

        } catch (ServiceException ex) {
            Logger.getLogger(DiasAulaContent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<DiaAula> getFilledObject() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void fillFieldsByObject(List<DiaAula> object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Adicionar os botoes de acao da dialog
     */
    private void createActionButtons() {
        // Criar Botoes e Adicionar botoes
        // Acoes da Janela (Lado direito)
        Button closeButton = ButtonFactory.getInstance().getCloseButton(Colors.COLOR_MAIN, new ButtonActionListener() {
            @Override
            public void action() {
                getContainer().close();
            }
        });
        closeButton.setType(Button.ButtonType.OPTION_RIGHT);
        this.getOptions().add(closeButton);

        Button saveButton = ButtonFactory.getInstance().getSaveButton(Colors.COLOR_MAIN, new ButtonActionListener() {
            @Override
            public void action() {
                save();
            }
        });
        saveButton.setType(Button.ButtonType.OPTION_RIGHT);
        this.getOptions().add(saveButton);
    }

    /**
     * Metodo para salvar os dias de aula da escola
     */
    private void save() {
        // Condicao para encerrar a janela
        boolean closeCondition = true;
        // Percorrer os dias de aula da tela
        for (DiaAulaContainer diaAulaContainer : this.diaAulaContainers) {
            if (diaAulaContainer.dayOfWeekCheckbox.isSelected()) {
                // Atribuir os valores
                diaAulaContainer.diaAula.setTotalAulas(diaAulaContainer.totalAulasTextField.getValue());
                // O total de aulas deve ser maior que zero
                if (diaAulaContainer.diaAula.getTotalAulas() > 0) {
                    try {
                        // Salvar dia de aula (Criar caso ainda nao exista, ou atualizar caso ja exista)
                        ServiceProvider.getInstance().getDiaAulaService().save(diaAulaContainer.diaAula);
                    } catch (ServiceException ex) {
                        MessageFactory.showErrorMessage(this, Strings.getReplacedString(Strings.ERROR_MESSAGE_SAVE_DIA_AULA, ex.getMessage()));
                        closeCondition = false;
                    }
                } else {
                    MessageFactory.showErrorMessage(this, Strings.ERROR_MESSAGE_DIA_AULA_SEM_TOTAL);
                    closeCondition = false;
                    break;
                }
            } else // Caso o dia nao esteja selecionado
            // Verificar se o dia ja encontrava-se persistido
            if (diaAulaContainer.diaAula.getId() != null) {
                try {
                    // Caso sim, removê-lo
                    ServiceProvider.getInstance().getDiaAulaService().remove(diaAulaContainer.diaAula);
                } catch (ServiceException ex) {
                    MessageFactory.showErrorMessage(this, Strings.getReplacedString(Strings.ERROR_MESSAGE_REMOVE_DIA_AULA, ex.getMessage()));
                    closeCondition = false;
                }
            }
        }
        // Encerrar a janela caso tenha salvado tudo corretamente
        if (closeCondition) {
            this.getContainer().close();
        }
    }

    private static class DiaAulaContainer {

        DiaAula diaAula;
        Checkbox dayOfWeekCheckbox;
        NumberTextField totalAulasTextField;

        public DiaAulaContainer(Checkbox checkbox, NumberTextField totalAulasTextField) {
            this.dayOfWeekCheckbox = checkbox;
            this.totalAulasTextField = totalAulasTextField;
            this.totalAulasTextField.setEnabled(false);
            this.dayOfWeekCheckbox.addActionListener((ActionEvent e) -> {
                totalAulasTextField.setEnabled(checkbox.isSelected());
            });
        }

        public void check() {
            this.dayOfWeekCheckbox.setSelected(true);
            this.totalAulasTextField.setEnabled(true);
        }

        public void uncheck() {
            this.dayOfWeekCheckbox.setSelected(false);
            this.totalAulasTextField.setEnabled(false);
        }

    }

}
