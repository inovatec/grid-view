/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.content;

import br.com.inovatec.grid.entity.Disciplina;
import br.com.inovatec.grid.entity.DisciplinaTurma;
import br.com.inovatec.grid.provider.ServiceProvider;
import br.com.inovatec.grid.service.exception.ServiceException;
import br.com.inovatec.grid.view.component.DataTableEntity;
import br.com.inovatec.grid.view.component.datamodel.DisciplinaTurmaDataModel;
import br.com.inovatec.grid.view.component.exception.NoItemSelectedException;
import br.com.inovatec.grid.view.component.factory.ButtonFactory;
import br.com.inovatec.grid.view.component.factory.LabelFactory;
import br.com.inovatec.grid.view.component.factory.PanelFactory;
import br.com.inovatec.grid.view.component.factory.SelectOneMenuFactory;
import br.com.inovatec.grid.view.component.factory.TextFieldFactory;
import br.com.inovatec.grid.view.component.form.Button;
import br.com.inovatec.grid.view.component.form.NumberTextField;
import br.com.inovatec.grid.view.component.form.SelectOneMenu;
import br.com.inovatec.grid.view.component.listener.ButtonActionListener;
import br.com.inovatec.grid.view.component.listener.DataTableEntityActionListener;
import br.com.inovatec.grid.view.component.util.ComponentUtils;
import br.com.inovatec.grid.view.content.exception.FormException;
import br.com.inovatec.grid.view.content.template.DefaultFormContent;
import br.com.inovatec.grid.view.content.validation.DisciplinaTurmaFormValidation;
import br.com.inovatec.grid.view.controller.ViewController;
import br.com.inovatec.grid.view.layout.TurmaDisciplinasView;
import br.com.inovatec.grid.view.util.MessageFactory;
import br.com.inovatec.grid.view.values.Colors;
import br.com.inovatec.grid.view.values.Dimens;
import br.com.inovatec.grid.view.values.Icons;
import br.com.inovatec.grid.view.values.Strings;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zrobe
 */
public class TurmaDisciplinasContent extends DefaultFormContent<DisciplinaTurma> {

    private final List<DisciplinaTurma> disciplinasTurmaForRemove;
    // Visual Components
    private SelectOneMenu<Disciplina> disciplinaSelectOneMenu;
    private NumberTextField aulasSemanaTotalNumberTextField, cargaHorariaTotalNumberTextField;
    private DataTableEntity<DisciplinaTurma, DisciplinaTurmaDataModel> disciplinaTurmaDataTable;
    private Button addButton, saveButton, cancelButton, removeButton, editButton, horariosButton;

    public TurmaDisciplinasContent(TurmaDisciplinasView container) {
        super(container, Icons.IC_DISCIPLINA, true);
        // Inicializar
        this.disciplinasTurmaForRemove = new ArrayList<>();
        // Criar botoes da tela
        this.createActionButtons();
    }

    @Override
    public TurmaDisciplinasView getContainer() {
        return (TurmaDisciplinasView) super.getContainer(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void buildMain() {
        try {
            // Todas as disciplinas
            List<Disciplina> disciplinas = ServiceProvider.getInstance().getDisciplinaService().findAll();
            // Remover das competencias as disciplinas que o professor ja leciona
            disciplinas.removeAll(ServiceProvider.getInstance().getDisciplinaService().listByTurma(getContainer().getTurma()));

            // Linha 1
            this.getMain().add(LabelFactory.getInstance().getLabel(Strings.TURMA_DISCIPLINAS_DIALOG_DISCIPLINA_FIELD, this.getWidth(), 4, Dimens.WEIGHT_30));
            this.getMain().add(LabelFactory.getInstance().getLabel(Strings.TURMA_DISCIPLINAS_DIALOG_AULAS_SEMANA_FIELD, this.getWidth(), 4, Dimens.WEIGHT_30));
            this.getMain().add(LabelFactory.getInstance().getLabel(Strings.TURMA_DISCIPLINAS_DIALOG_CARGA_HORARIA_TOTAL_FIELD, this.getWidth(), 4, Dimens.WEIGHT_30));
            // Dimensao do painel vazio da primeira linha
            Dimension emptyPanelLineOneDimension = ComponentUtils.getLabelDimension(this.getWidth(), 4, Dimens.WEIGHT_10);
            // Adicionar Painel vazio a primeira linha
            this.getMain().add(PanelFactory.getInstance().getEmptyPanel(emptyPanelLineOneDimension));
            // Linha 2
            this.disciplinaSelectOneMenu = SelectOneMenuFactory
                    .getInstance()
                    .getSelectOneMenu(this.getWidth(), 4, Dimens.WEIGHT_30, disciplinas);
            this.getMain().add(this.disciplinaSelectOneMenu);
            
            this.aulasSemanaTotalNumberTextField = TextFieldFactory.getInstance().getNumberTextField(this.getWidth(), 4, Dimens.WEIGHT_30);
            this.getMain().add(this.aulasSemanaTotalNumberTextField);
            
            this.cargaHorariaTotalNumberTextField = TextFieldFactory.getInstance().getNumberTextField(this.getWidth(), 4, Dimens.WEIGHT_30);
            this.getMain().add(this.cargaHorariaTotalNumberTextField);
            
            // Dimensoes do Botao de adicionar
            Dimension buttonLineOneDimension = ComponentUtils.getFormButtonDimension(this.getWidth(), 2, Dimens.WEIGHT_10);
            this.addButton = ButtonFactory.getInstance().getAddButton(buttonLineOneDimension, Colors.COLOR_MAIN, new ButtonActionListener() {
                @Override
                public void action() {
                    addNovaDisciplina();
                }
            });
            this.getMain().add(addButton);

            // Tabela com as disciplinas da turma
            // Dimensoes da Tabela de Disciplinas
            Dimension tableDimension = ComponentUtils.getDefaultComponentDimension(this.getWidth(), Dimens.TURMA_DISCIPLINAS_TABLE_HEIGHT);
            // Tabela com as competencias
            this.disciplinaTurmaDataTable = new DataTableEntity<>(
                    new DisciplinaTurmaDataModel(
                            ServiceProvider.getInstance()
                            .getDisciplinaTurmaService()
                            .findByTurma(getContainer().getTurma())
                    ),
                    tableDimension,
                    new DisciplinaDataTableEntityActionListener(),
                    true,
                    false
            );
            // Adicionar tabela ao conteudo
            this.getMain().add(this.disciplinaTurmaDataTable);
        } catch (ServiceException ex) {
            this.showInternalErrorMessage(ex);
        }
    }

    /**
     * Adicionar nova disciplina para a turma
     */
    private void addNovaDisciplina() {
        try {
            // DisciplinaTurma do formulario
            DisciplinaTurma disciplinaTurma = DisciplinaTurmaFormValidation.getDisciplinaTurmaByForm(this.disciplinaTurmaDataTable.getData(),
                    this.disciplinaTurmaDataTable.getData().size(),
                    this.disciplinaSelectOneMenu,
                    this.aulasSemanaTotalNumberTextField,
                    this.cargaHorariaTotalNumberTextField
            );
            // Adicionar DisciplinaTurma a tabela
            this.disciplinaTurmaDataTable.addItem(disciplinaTurma);
            try {
                // Remover a disciplina do selectOneMenu
                this.disciplinaSelectOneMenu.removeItem(this.disciplinaSelectOneMenu.getSelectedItem());
            } catch (NoItemSelectedException ex) {
                this.showInternalErrorMessage(ex);
            }
            // Resetar campos do formulario
            resetFields();
        } catch (FormException ex) {
            MessageFactory.showErrorMessage(TurmaDisciplinasContent.this, ex.getMessage());
        }
    }

    /**
     * Resetar campos do formulario
     */
    private void resetFields() {
        this.disciplinaSelectOneMenu.setSelectedItem(null);
        this.aulasSemanaTotalNumberTextField.reset();
        this.cargaHorariaTotalNumberTextField.reset();
        this.removeButton.setEnabled(false);
        this.editButton.setEnabled(false);
        this.horariosButton.setEnabled(false);
    }

    /**
     * Adicionar os botoes de acao da dialog
     */
    private void createActionButtons() {
        // Acoes da Janela (Lado esquerdo)
        this.removeButton = ButtonFactory.getInstance().getDeleteButton(Colors.COLOR_MAIN, new ButtonActionListener() {
            @Override
            public void action() {
                disciplinaTurmaDataTable.removeSelected();
            }
        });
        this.removeButton.setType(Button.ButtonType.OPTION_LEFT);
        this.removeButton.setEnabled(false);
        this.getOptions().add(this.removeButton);
        
        this.editButton = ButtonFactory.getInstance().getEditButton(Colors.COLOR_MAIN, new ButtonActionListener() {
            @Override
            public void action() {
                disciplinaTurmaDataTable.executeMainAction();
            }
        });
        this.editButton.setType(Button.ButtonType.OPTION_LEFT);
        this.editButton.setEnabled(false);
        this.getOptions().add(this.editButton);

        this.horariosButton = ButtonFactory.getInstance().getHorariosButton(Colors.COLOR_MAIN, new ButtonActionListener() {
            @Override
            public void action() {
                ViewController.showHorariosDisciplinaTurmaView(getContainer(), disciplinaTurmaDataTable.getSelected());
            }
        });
        this.horariosButton.setType(Button.ButtonType.OPTION_LEFT);
        this.horariosButton.setEnabled(false);
        this.getOptions().add(this.horariosButton);

        // Acoes da Janela (Lado direito)
        this.cancelButton = ButtonFactory.getInstance().getCancelButton(Colors.COLOR_MAIN, new ButtonActionListener() {
            @Override
            public void action() {
                getContainer().close();
            }
        });
        this.cancelButton.setType(Button.ButtonType.OPTION_RIGHT);
        this.getOptions().add(this.cancelButton);

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
        // Resetar as disciplinas da turma
        getContainer().getTurma().resetDisciplinasTurma();
        // Salvar as Disciplinas oficiais da Turma, atualizando as ja persistidas e persistinda as novas
        this.disciplinaTurmaDataTable.getData().stream().forEach((dt) -> {
            getContainer().getTurma().addDisciplinaTurma(dt);
        });
        try {
            // Atualizar turma
            ServiceProvider.getInstance().getTurmaService().save(getContainer().getTurma());
            // Fechar janela
            this.getContainer().close();
        } catch (ServiceException ex) {
            MessageFactory.showErrorMessage(this, ex.getMessage());
        }
    }

    @Override
    public DisciplinaTurma getFilledObject() throws FormException {
        return null;
    }

    @Override
    public void fillFieldsByObject(DisciplinaTurma object) {
    }

    private class DisciplinaDataTableEntityActionListener implements DataTableEntityActionListener<DisciplinaTurma> {

        @Override
        public void mainAction(DisciplinaTurma dt, int index) {
            ViewController.showTurmaDisciplinaEditView(getContainer(), dt, disciplinaTurmaDataTable.getData(), index);
        }

        @Override
        public void clickRowAction() {
            horariosButton.setEnabled(true);
            editButton.setEnabled(true);
            removeButton.setEnabled(true);
        }

        @Override
        public boolean deleteAction(DisciplinaTurma dt) {
            disciplinasTurmaForRemove.add(dt);
            disciplinaSelectOneMenu.addItem(dt.getDisciplina());
            return true;
        }

    }
    
    /**
     * Atualizar conteudo
     */
    public void refreshContent() {
        this.disciplinaTurmaDataTable.refreshTable();
        this.removeButton.setEnabled(false);
        this.editButton.setEnabled(false);
        this.horariosButton.setEnabled(false);
    }

}
