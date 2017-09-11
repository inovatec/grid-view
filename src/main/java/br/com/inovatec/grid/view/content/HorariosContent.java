/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.content;

import br.com.inovatec.grid.provider.ServiceProvider;
import br.com.inovatec.grid.entity.DiaAula;
import br.com.inovatec.grid.entity.Gerenciavel;
import br.com.inovatec.grid.entity.Horario;
import br.com.inovatec.grid.service.exception.ServiceException;
import br.com.inovatec.grid.util.DateTimeUtils;
import br.com.inovatec.grid.view.component.DataTableEntity;
import br.com.inovatec.grid.view.component.datamodel.HorarioDataModel;
import br.com.inovatec.grid.view.component.factory.ButtonFactory;
import br.com.inovatec.grid.view.component.factory.LabelFactory;
import br.com.inovatec.grid.view.component.factory.PanelFactory;
import br.com.inovatec.grid.view.component.factory.SelectOneMenuFactory;
import br.com.inovatec.grid.view.component.factory.TextFieldFactory;
import br.com.inovatec.grid.view.component.form.Button;
import br.com.inovatec.grid.view.component.form.MaskedTextField;
import br.com.inovatec.grid.view.component.form.SelectOneMenu;
import br.com.inovatec.grid.view.component.listener.ButtonActionListener;
import br.com.inovatec.grid.view.component.listener.DataTableEntityActionListener;
import br.com.inovatec.grid.view.component.util.ComponentUtils;
import br.com.inovatec.grid.view.content.exception.FormException;
import br.com.inovatec.grid.view.content.template.DefaultFormContent;
import br.com.inovatec.grid.view.content.validation.HorarioFormValidation;
import br.com.inovatec.grid.view.controller.ViewController;
import br.com.inovatec.grid.view.layout.HorariosView;
import br.com.inovatec.grid.view.session.Session;
import br.com.inovatec.grid.view.util.MessageFactory;
import br.com.inovatec.grid.view.values.Colors;
import br.com.inovatec.grid.view.values.Dimens;
import br.com.inovatec.grid.view.values.Icons;
import br.com.inovatec.grid.view.values.Strings;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zrobe
 */
public class HorariosContent extends DefaultFormContent<Gerenciavel> {

    private final List<Horario> horariosForRemove;
    // Visual Components
    private SelectOneMenu<DiaAula> diaAulaSelectOneMenu;
    private MaskedTextField horaInicioMaskedTextField, horaFimMaskedTextField;
    private DataTableEntity<Horario, HorarioDataModel> horariosDataTable;

    private Button editButton, removeButton;

    public HorariosContent(HorariosView container) {
        super(container, Icons.IC_HORARIOS, true);
        // Inicializar
        this.horariosForRemove = new ArrayList<>();
        // Criar botoes da tela
        this.createActionButtons();
    }

    @Override
    public HorariosView getContainer() {
        return (HorariosView) super.getContainer();
    }

    @Override
    protected void buildMain() {
        try {
            // Dias de aula da escola
            List<DiaAula> diasAula = ServiceProvider.getInstance().getDiaAulaService().listBy(Session.getInstance().getEscola().getPeriodoCorrente());
            Collections.sort(diasAula);
            // Dimensoes dos Labels da primeira linha
            Dimension labelsLineOneDimension = ComponentUtils.getLabelDimension(this.getWidth(), 4, Dimens.WEIGHT_30);
            // Adicionar Labels da primeira linha
            this.getMain().add(LabelFactory.getInstance().getLabel(Strings.HORARIOS_DIALOG_DIA_LABEL, labelsLineOneDimension));
            this.getMain().add(LabelFactory.getInstance().getLabel(Strings.HORARIOS_DIALOG_HORA_INICIO_LABEL, labelsLineOneDimension));
            this.getMain().add(LabelFactory.getInstance().getLabel(Strings.HORARIOS_DIALOG_HORA_FIM_LABEL, labelsLineOneDimension));
            // Dimensao do painel vazio da primeira linha
            Dimension emptyPanelLineOneDimension = ComponentUtils.getLabelDimension(this.getWidth(), 4, Dimens.WEIGHT_10);
            // Adicionar Painel vazio a primeira linha
            this.getMain().add(PanelFactory.getInstance().getEmptyPanel(emptyPanelLineOneDimension));
            // Dimensoes dos Fields da segunda linha
            Dimension selectLineOneDimension = ComponentUtils.getSelectOneMenuDimension(this.getWidth(), 4, Dimens.WEIGHT_30);
            Dimension fieldsLineOneDimension = ComponentUtils.getTextFieldDimension(this.getWidth(), 4, Dimens.WEIGHT_30);
            // Adicionar Fields da segunda linha
            this.diaAulaSelectOneMenu = SelectOneMenuFactory.getInstance().getSelectOneMenu(selectLineOneDimension, diasAula);
            this.diaAulaSelectOneMenu.addSelectActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    initTimes();
                }
            });
            this.getMain().add(this.diaAulaSelectOneMenu);
            this.horaInicioMaskedTextField = TextFieldFactory.getInstance().getTimeMaskedTextField(fieldsLineOneDimension);
            this.horaFimMaskedTextField = TextFieldFactory.getInstance().getTimeMaskedTextField(fieldsLineOneDimension);
            this.horaFimMaskedTextField.addFocusEventListener(new HoraFimFocusEventListener());
            this.getMain().add(this.horaInicioMaskedTextField);
            this.getMain().add(this.horaFimMaskedTextField);
            // Dimensoes do Botao de adicionar
            Dimension buttonLineOneDimension = ComponentUtils.getFormButtonDimension(this.getWidth(), 4, Dimens.WEIGHT_10);
            Button addButton = ButtonFactory.getInstance().getAddButton(buttonLineOneDimension, Colors.COLOR_MAIN, new ButtonActionListener() {
                @Override
                public void action() {
                    addNovoHorario();
                }
            });
            this.getMain().add(addButton);

            // Inicializar horas
            this.initTimes();

            // Dimensoes da Tabela de Horários
            Dimension tableDimension = ComponentUtils.getDefaultComponentDimension(this.getWidth(), Dimens.HORARIOS_TABLE_HEIGHT);
            // Tabela com os horarios
            this.horariosDataTable = new DataTableEntity<>(
                    new HorarioDataModel(
                            this.getContainer().getGerenciavel().getId() != null
                                    ? // Verificar se o Gerenciavel ja se encontra salvo
                                    ServiceProvider.getInstance().getHorarioService().getOfPeriodoCorrente(this.getContainer().getGerenciavel())
                                    : this.getContainer().getGerenciavel().getHorarios()
                    ),
                    tableDimension,
                    new HorarioDataTableEntityActionListener(),
                    true,
                    false
            );
            // Adicionar tabela ao conteudo
            this.getMain().add(this.horariosDataTable);
        } catch (ServiceException ex) {
            this.showInternalErrorMessage(ex);
        }
    }

    @Override
    public Gerenciavel getFilledObject() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void fillFieldsByObject(Gerenciavel object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Adicionar os botoes de acao da dialog
     */
    private void createActionButtons() {
        // Acoes da Janela (Lado esquerdo)
        this.editButton = ButtonFactory.getInstance().getEditButton(Colors.COLOR_MAIN, new ButtonActionListener() {
            @Override
            public void action() {
                horariosDataTable.executeMainAction();
            }
        });
        this.editButton.setType(Button.ButtonType.OPTION_LEFT);
        this.editButton.setEnabled(false);
        this.getOptions().add(this.editButton);

        this.removeButton = ButtonFactory.getInstance().getDeleteButton(Colors.COLOR_MAIN, new ButtonActionListener() {
            @Override
            public void action() {
                horariosDataTable.removeSelected();
            }
        });
        this.removeButton.setType(Button.ButtonType.OPTION_LEFT);
        this.removeButton.setEnabled(false);
        this.getOptions().add(this.removeButton);

        // Criar e adicionar botoes
        // Acoes da entidade
        if (this.getContainer().isConsideraDuracaoAula()) {
            Button gerarHorariosButton = ButtonFactory.getInstance().increase(Dimens.PLUS_40,
                    ButtonFactory.getInstance().getDefaultFormButton(Strings.HORARIOS_DIALOG_GERAR_HORARIOS_AUTO_BUTTON, Colors.COLOR_MAIN, new ButtonActionListener() {
                        @Override
                        public void action() {
                            generateHorariosAuto();
                        }
                    })
            );
            gerarHorariosButton.setType(Button.ButtonType.OPTION_CENTER);
            this.getOptions().add(gerarHorariosButton);
        }

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
     * Acao do botao de adicionar novo horario
     */
    private void addNovoHorario() {
        try {
            // Horario do formulario
            Horario horario = HorarioFormValidation.getHorarioByForm(
                    horariosDataTable.getModel().getDados(),
                    horariosDataTable.getModel().getDados().size(),
                    diaAulaSelectOneMenu,
                    horaInicioMaskedTextField,
                    horaFimMaskedTextField
            );
            // Adicionar horario ao gerenciavel
            horario.setGerenciavel(getContainer().getGerenciavel());
            // Adicionar horario a tabela
            horariosDataTable.addItem(horario);
            // Modificar hora final
            changeTimes();
        } catch (FormException ex) {
            MessageFactory.showErrorMessage(HorariosContent.this, ex.getMessage());
        }
    }

    /**
     * Metodo salvar a entidade gerenciavel com os novos horarios
     */
    private void save() {
        // Verificar se o Gerenciavel ja se encontra salvo na base de dados
        if (this.getContainer().getGerenciavel().getId() != null) {
            // Salvar os horarios oficiais do gerenciavel, atualizando os ja persistidos e persistindo os novos
            this.horariosDataTable.getData().stream().forEach((h) -> {
                try {
                    ServiceProvider.getInstance().getHorarioService().save(h);
                } catch (ServiceException ex) {
                    Logger.getLogger(HorariosContent.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            // Remover horarios da lista de para remocao
            this.horariosForRemove.stream().forEach((h) -> {
                try {
                    ServiceProvider.getInstance().getHorarioService().remove(h);
                    getContainer().getGerenciavel().getHorarios().remove(h);
                } catch (ServiceException ex) {
                    Logger.getLogger(HorariosContent.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }
        // Fechar janela
        this.getContainer().close();
    }

    /**
     * Modificar a hora final caso necessario
     */
    private void changeEndTime() {
        if (getContainer().isConsideraDuracaoAula()) {
            try {
                LocalTime beginTime = DateTimeUtils.getTime(horaInicioMaskedTextField.getText());
                this.horaFimMaskedTextField.setText(DateTimeUtils.getMinimalFormattedTime(beginTime.plusMinutes(Session.getInstance().getEscola().getDuracaoAula())));
            } catch (DateTimeParseException ex) {
            }
        }
    }

    /**
     * Modificar a hora final caso necessario
     */
    private void initTimes() {
        if (getContainer().isConsideraDuracaoAula()) {
            try {
                this.horaInicioMaskedTextField.setText(DateTimeUtils.getMinimalFormattedTime(Session.getInstance().getEscola().getInicioAula()));
                this.horaFimMaskedTextField.setText(DateTimeUtils.getMinimalFormattedTime(Session.getInstance().getEscola().getInicioAula().plusMinutes(Session.getInstance().getEscola().getDuracaoAula())));
            } catch (DateTimeParseException ex) {
            }
        }
    }

    /**
     * Modificar as horas apos insercao
     */
    private void changeTimes() {
        if (getContainer().isConsideraDuracaoAula()) {
            try {
                LocalTime beginTime = DateTimeUtils.getTime(horaInicioMaskedTextField.getText());
                this.horaInicioMaskedTextField.setText(DateTimeUtils.getMinimalFormattedTime(beginTime.plusMinutes(Session.getInstance().getEscola().getDuracaoAula())));
                this.horaFimMaskedTextField.setText(DateTimeUtils.getMinimalFormattedTime(beginTime.plusMinutes(Session.getInstance().getEscola().getDuracaoAula() * 2)));
            } catch (DateTimeParseException ex) {
            }
        }
    }

    /**
     * Classe Listener para o evento de focus do input de hora final
     */
    private class HoraFimFocusEventListener implements FocusListener {

        @Override
        public void focusGained(FocusEvent e) {
            changeEndTime();
        }

        @Override
        public void focusLost(FocusEvent e) {
        }

    }

    /**
     * Gerar os horarios automaticamente com base nos dados da entidade
     * gerenciavel
     */
    private void generateHorariosAuto() {
        try {
            // Dias de aula da escola
            List<DiaAula> diasAula = ServiceProvider.getInstance().getDiaAulaService().listBy(Session.getInstance().getEscola().getPeriodoCorrente());
            // Lista de horarios
            List<Horario> generatedHorarios = this.horariosDataTable.getData();
            int plusMinutes = Session.getInstance().getEscola().getDuracaoAula();
            // Para os dias com aula
            for (DiaAula da : diasAula) {
                // Hora inicial da aula
                LocalTime horaInicioLocalTime = Session.getInstance().getEscola().getInicioAula();
                // Iterar pelo total de aulas do dia
                for (int i = 0; i < da.getTotalAulas(); i++) {
                    // Adicionar novo horario
                    Horario horario = new Horario(
                            da,
                            horaInicioLocalTime,
                            horaInicioLocalTime.plusMinutes(plusMinutes),
                            getContainer().getGerenciavel().getId() != null ? getContainer().getGerenciavel() : null
                    );
                    // Verificar existencia do horario
                    boolean condition = true;
                    // Iterar pelos horarios ja existentes da entidade
                    for (Horario temp : this.horariosDataTable.getData()) {
                        if (horario.equals(temp) || horario.between(temp)) {
                            condition = false;
                            break;
                        }
                    }
                    // Verificar se a condicao de adicao foi atendida
                    if (condition) {
                        generatedHorarios.add(horario);
                    }
                    // Modificar informacoes para proxima iteracao
                    horaInicioLocalTime = horaInicioLocalTime.plusMinutes(plusMinutes);
                }
            }
            // Adicionar os horarios a tabela
            this.horariosDataTable.setData(generatedHorarios);
        } catch (ServiceException ex) {
            Logger.getLogger(HorariosContent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void refreshContent() {
        this.horariosDataTable.refreshTable();
    }

    private class HorarioDataTableEntityActionListener implements DataTableEntityActionListener<Horario> {

        @Override
        public void mainAction(Horario t, int index) {
            ViewController.showHorarioEditView(getContainer(), t, horariosDataTable.getModel().getDados(), index);
        }

        @Override
        public boolean deleteAction(Horario t) {
            if (t.getId() != null) {
                horariosForRemove.add(t);
            }
            return true;
        }

        @Override
        public void clickRowAction() {
            editButton.setEnabled(true);
            removeButton.setEnabled(true);
        }

    }

}
