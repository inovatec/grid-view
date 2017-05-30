/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.layout;

import br.com.inovatec.grid.entity.Professor;
import br.com.inovatec.grid.view.content.ProfessorContent;
import br.com.inovatec.grid.view.contract.DialogView;
import br.com.inovatec.grid.view.layout.template.DefaultView;
import br.com.inovatec.grid.view.values.Dimens;
import br.com.inovatec.grid.view.values.Strings;

/**
 *
 * @author zrobe
 */
public class ProfessorView extends DefaultView {

    private Professor professor;
    private ProfessorContent content;
    private boolean forEdition;

    public ProfessorView(DialogView requester, boolean forEdition, Professor professor) {
        super(requester,
                Strings.PROFESSOR_DIALOG_TITLE,
                Strings.PROFESSOR_DIALOG_TITLE,
                professor != null ? Strings.PROFESSOR_CONTENT_HEADER_TIP : Strings.PROFESSOR_NEW_CONTENT_HEADER_TIP,
                Dimens.PROFESSOR_DIALOG_WIDTH,
                Dimens.PROFESSOR_DIALOG_HEIGHT
        );
        this.forEdition = forEdition;
        this.professor = professor != null ? professor : new Professor();
        this.init();
    }

    public boolean isForEdition() {
        return forEdition;
    }

    public void setForEdition(boolean forEdition) {
        this.forEdition = forEdition;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    /**
     * Inicializar componentes
     */
    private void init() {
        this.content = new ProfessorContent(this);
    }

    @Override
    public ProfessorContent getContent() {
        return this.content;
    }

    @Override
    public void refreshViewRequester() {
        this.getRequester().refresh();
    }

    @Override
    public void refresh() {
    }

}
