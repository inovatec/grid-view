/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.content;

import br.com.inovatec.grid.entity.Disciplina;
import br.com.inovatec.grid.entity.DisciplinaTurma;
import br.com.inovatec.grid.view.component.FooterOptionsLayout;
import br.com.inovatec.grid.view.component.factory.ButtonFactory;
import br.com.inovatec.grid.view.component.factory.LabelFactory;
import br.com.inovatec.grid.view.component.factory.SelectOneMenuFactory;
import br.com.inovatec.grid.view.component.factory.TextFieldFactory;
import br.com.inovatec.grid.view.component.form.Button;
import br.com.inovatec.grid.view.component.form.Button.ButtonType;
import br.com.inovatec.grid.view.component.form.NumberTextField;
import br.com.inovatec.grid.view.component.form.SelectOneMenu;
import br.com.inovatec.grid.view.component.listener.ButtonActionListener;
import br.com.inovatec.grid.view.component.util.ComponentUtils;
import br.com.inovatec.grid.view.content.exception.FormException;
import br.com.inovatec.grid.view.content.template.DefaultFormContent;
import br.com.inovatec.grid.view.content.validation.DisciplinaTurmaFormValidation;
import br.com.inovatec.grid.view.layout.TurmaDisciplinaEditView;
import br.com.inovatec.grid.view.session.Session;
import br.com.inovatec.grid.view.util.MessageFactory;
import br.com.inovatec.grid.view.values.Colors;
import br.com.inovatec.grid.view.values.Dimens;
import br.com.inovatec.grid.view.values.Strings;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Arrays;

/**
 *
 * @author zrobe
 */
public class TurmaDisciplinaEditContent extends DefaultFormContent<DisciplinaTurma> {

    // Visual Components
    private SelectOneMenu<Disciplina> disciplinaSelectOneMenu;
    private NumberTextField aulasSemanaTotalNumberTextField, cargaHorariaTotalNumberTextField;

    public TurmaDisciplinaEditContent(TurmaDisciplinaEditView container) {
        super(container, null, false);
        this.createActionButtons();
    }

    @Override
    public TurmaDisciplinaEditView getContainer() {
        return (TurmaDisciplinaEditView) super.getContainer(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void initComponents() {
        super.initComponents();
        ((FooterOptionsLayout) this.getFooter()).hideOptionsLeftPanel();
        ((FooterOptionsLayout) this.getFooter()).hideOptionsRightPanel();
    }

    @Override
    protected void buildMain() {
        // Linha 1
        this.getMain().add(LabelFactory.getInstance().getLabel(Strings.TURMA_DISCIPLINAS_DIALOG_DISCIPLINA_FIELD, this.getWidth(), 1, Dimens.WEIGHT_100));
        this.disciplinaSelectOneMenu = SelectOneMenuFactory
                .getInstance()
                .getSelectOneMenu(this.getWidth(), 1, Dimens.WEIGHT_100, Arrays.asList(getContainer().getDisciplinaTurma().getDisciplina()));
        // Remover o primeiro elemento
        this.disciplinaSelectOneMenu.clearFirstElement();
        // Desabilitar para que nao seja possivel modificacao
        this.disciplinaSelectOneMenu.setEnabled(false);
        this.getMain().add(this.disciplinaSelectOneMenu);
        // Linha 2
        this.getMain().add(LabelFactory.getInstance().getLabel(Strings.TURMA_DISCIPLINAS_DIALOG_AULAS_SEMANA_FIELD, this.getWidth(), 1, Dimens.WEIGHT_100));
        this.aulasSemanaTotalNumberTextField = TextFieldFactory.getInstance().getNumberTextField(this.getWidth(), 1, Dimens.WEIGHT_100);
        this.aulasSemanaTotalNumberTextField.setValue(getContainer().getDisciplinaTurma().getAulasSemanaTotal());
        this.getMain().add(this.aulasSemanaTotalNumberTextField);
        // Linha 3
        this.getMain().add(LabelFactory.getInstance().getLabel(Strings.TURMA_DISCIPLINAS_DIALOG_CARGA_HORARIA_TOTAL_FIELD, this.getWidth(), 1, Dimens.WEIGHT_100));
        this.cargaHorariaTotalNumberTextField = TextFieldFactory.getInstance().getNumberTextField(this.getWidth(), 1, Dimens.WEIGHT_100);
        this.cargaHorariaTotalNumberTextField.setValue(getContainer().getDisciplinaTurma().getCargaHorariaTotal());
        this.getMain().add(this.cargaHorariaTotalNumberTextField);
    }

    @Override
    public DisciplinaTurma getFilledObject() {
        return null;
    }

    @Override
    public void fillFieldsByObject(DisciplinaTurma object) {
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
            // DisciplinaTurma do formulario
            DisciplinaTurma disciplinaTurma = DisciplinaTurmaFormValidation.getDisciplinaTurmaByForm(this.getContainer().getDisciplinasTurma(),
                    this.getContainer().getIndex(),
                    null,
                    this.aulasSemanaTotalNumberTextField,
                    this.cargaHorariaTotalNumberTextField
            );
            // Atualizar os dados do Horario
            getContainer().getDisciplinaTurma().setAulasSemanaTotal(disciplinaTurma.getAulasSemanaTotal());
            getContainer().getDisciplinaTurma().setCargaHorariaTotal(disciplinaTurma.getCargaHorariaTotal());
            // Fechar a janela
            getContainer().close();
        } catch (FormException ex) {
            MessageFactory.showErrorMessage(TurmaDisciplinaEditContent.this, ex.getMessage());
        }
    }
    
}
