/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.content;

import br.com.inovatec.grid.entity.Disciplina;
import br.com.inovatec.grid.provider.ServiceProvider;
import br.com.inovatec.grid.entity.Professor;
import br.com.inovatec.grid.service.exception.ServiceException;
import br.com.inovatec.grid.view.component.DataTableEntity;
import br.com.inovatec.grid.view.component.datamodel.DisciplinaDataModel;
import br.com.inovatec.grid.view.component.exception.NoItemSelectedException;
import br.com.inovatec.grid.view.component.factory.ButtonFactory;
import br.com.inovatec.grid.view.component.factory.LabelFactory;
import br.com.inovatec.grid.view.component.factory.PanelFactory;
import br.com.inovatec.grid.view.component.factory.SelectOneMenuFactory;
import br.com.inovatec.grid.view.component.form.Button;
import br.com.inovatec.grid.view.component.form.SelectOneMenu;
import br.com.inovatec.grid.view.component.listener.ButtonActionListener;
import br.com.inovatec.grid.view.component.listener.DataTableEntityActionListener;
import br.com.inovatec.grid.view.component.util.ComponentUtils;
import br.com.inovatec.grid.view.content.exception.FormException;
import br.com.inovatec.grid.view.content.template.DefaultFormContent;
import br.com.inovatec.grid.view.layout.CompetenciasView;
import br.com.inovatec.grid.view.util.MessageFactory;
import br.com.inovatec.grid.view.values.Colors;
import br.com.inovatec.grid.view.values.Dimens;
import br.com.inovatec.grid.view.values.Icons;
import br.com.inovatec.grid.view.values.Strings;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zrobe
 */
public class CompetenciasContent extends DefaultFormContent<Professor> {

    private SelectOneMenu<Disciplina> disciplinaSelectOneMenu;
    private DataTableEntity<Disciplina, DisciplinaDataModel> disciplinasDataTable;
    private Button addButton, saveButton, cancelButton, removeButton;

    public CompetenciasContent(CompetenciasView container) {
        super(container, Icons.IC_DISCIPLINA, true);
        this.createActionButtons();
    }

    @Override
    public CompetenciasView getContainer() {
        return (CompetenciasView) super.getContainer(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void buildMain() {
        try {
            // Todas as disciplinas
            List<Disciplina> competencias = ServiceProvider.getInstance().getDisciplinaService().findAll();
            // Remover das competencias as disciplinas que o professor ja leciona
            competencias.removeAll(getContainer().getProfessor().getDisciplinas());

            // Linha 1
            this.getMain().add(LabelFactory.getInstance().getLabel(Strings.COMPETENCIAS_DIALOG_DISCIPLINA_FIELD, this.getWidth(), 2, Dimens.WEIGHT_90));
            // Dimensao do painel vazio da primeira linha
            Dimension emptyPanelLineOneDimension = ComponentUtils.getLabelDimension(this.getWidth(), 2, Dimens.WEIGHT_10);
            // Adicionar Painel vazio a primeira linha
            this.getMain().add(PanelFactory.getInstance().getEmptyPanel(emptyPanelLineOneDimension));
            // Linha 2
            this.disciplinaSelectOneMenu = SelectOneMenuFactory
                    .getInstance()
                    .getSelectOneMenu(this.getWidth(), 2, Dimens.WEIGHT_90, competencias);
            this.getMain().add(this.disciplinaSelectOneMenu);
            // Dimensoes do Botao de adicionar
            Dimension buttonLineOneDimension = ComponentUtils.getFormButtonDimension(this.getWidth(), 2, Dimens.WEIGHT_10);
            this.addButton = ButtonFactory.getInstance().getAddButton(buttonLineOneDimension, Colors.COLOR_MAIN, new ButtonActionListener() {
                @Override
                public void action() {
                    addNovaCompetencia();
                }
            });
            this.getMain().add(addButton);

            // Tabela com as competencias
            // Dimensoes da Tabela de Competências
            Dimension tableDimension = ComponentUtils.getDefaultComponentDimension(this.getWidth(), Dimens.COMPETENCIAS_TABLE_HEIGHT);
            // Tabela com as competencias
            this.disciplinasDataTable = new DataTableEntity<>(
                    new DisciplinaDataModel(new ArrayList<>(this.getContainer().getProfessor().getDisciplinas())),
                    tableDimension,
                    new DisciplinaDataTableEntityActionListener(),
                    true
            );
            // Adicionar tabela ao conteudo
            this.getMain().add(this.disciplinasDataTable);
        } catch (ServiceException ex) {
            this.showInternalErrorMessage(ex);
        }
    }

    /**
     * Adicionar nova competencia para o professor
     */
    private void addNovaCompetencia() {
        try {
            if (this.disciplinaSelectOneMenu.getSelectedItem() != null) {
                this.disciplinasDataTable.addItem(this.disciplinaSelectOneMenu.getSelectedItem());
                this.disciplinaSelectOneMenu.removeItem(this.disciplinaSelectOneMenu.getSelectedItem());
            }
        } catch (NoItemSelectedException ex) {
            MessageFactory.showWarnMessage(this, Strings.VALIDATION_MESSAGE_COMPETENCIAS_DISCIPLINA_NOT_NULL);
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
                disciplinasDataTable.removeSelected();
            }
        });
        this.removeButton.setType(Button.ButtonType.OPTION_LEFT);
        this.removeButton.setEnabled(false);
        this.getOptions().add(this.removeButton);

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
        try {
            // Modificar lista de competencias do professor
            this.getContainer().getProfessor().setDisciplinas(this.disciplinasDataTable.getData());
            // Verificar se o professor ja esta na base de dados
            if (this.getContainer().getProfessor().getId() != null) {
                // Salvar ou atualizar Professor
                ServiceProvider.getInstance().getProfessorService().save(this.getContainer().getProfessor());
            }
            // Fechar janela
            getContainer().close();
        } catch (ServiceException ex) {
            this.showInternalErrorMessage(ex);
        }
    }

    @Override
    public Professor getFilledObject() throws FormException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void fillFieldsByObject(Professor object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private class DisciplinaDataTableEntityActionListener implements DataTableEntityActionListener<Disciplina> {

        @Override
        public void mainAction(Disciplina t, int index) {
            // Nothing to do
        }

        @Override
        public void clickRowAction() {
            removeButton.setEnabled(true);
        }

        @Override
        public boolean deleteAction(Disciplina t) {
            disciplinaSelectOneMenu.addItem(t);
            return true;
        }

    }

}
