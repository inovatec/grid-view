/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.layout;

import br.com.inovatec.grid.entity.Disciplina;
import br.com.inovatec.grid.view.content.DisciplinaContent;
import br.com.inovatec.grid.view.contract.DialogView;
import br.com.inovatec.grid.view.layout.template.DefaultView;
import br.com.inovatec.grid.view.values.Dimens;
import br.com.inovatec.grid.view.values.Strings;

/**
 *
 * @author zrobe
 */
public class DisciplinaView extends DefaultView {

    private Disciplina disciplina;
    private DisciplinaContent content;
    private boolean forEdition;

    public DisciplinaView(DialogView requester, boolean forEdition, Disciplina disciplina) {
        super(requester,
                Strings.DISCIPLINA_DIALOG_TITLE,
                Strings.DISCIPLINA_DIALOG_TITLE,
                disciplina != null ? Strings.DISCIPLINA_CONTENT_HEADER_TIP : Strings.DISCIPLINA_NEW_CONTENT_HEADER_TIP,
                Dimens.DISCIPLINA_DIALOG_WIDTH,
                Dimens.DISCIPLINA_DIALOG_HEIGHT
        );
        this.forEdition = forEdition;
        this.disciplina = disciplina;
        this.init();
    }

    public boolean isForEdition() {
        return forEdition;
    }

    public void setForEdition(boolean forEdition) {
        this.forEdition = forEdition;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    /**
     * Inicializar componentes
     */
    private void init() {
        this.content = new DisciplinaContent(this);
    }

    @Override
    public DisciplinaContent getContent() {
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
