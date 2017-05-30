/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.layout;

import br.com.inovatec.grid.entity.Turma;
import br.com.inovatec.grid.view.content.TurmaContent;
import br.com.inovatec.grid.view.contract.DialogView;
import br.com.inovatec.grid.view.layout.template.DefaultView;
import br.com.inovatec.grid.view.values.Dimens;
import br.com.inovatec.grid.view.values.Strings;

/**
 *
 * @author zrobe
 */
public class TurmaView extends DefaultView {

    private Turma turma;
    private TurmaContent content;
    private boolean forEdition;

    public TurmaView(DialogView requester, boolean forEdition, Turma turma) {
        super(requester,
                Strings.TURMA_DIALOG_TITLE,
                Strings.TURMA_DIALOG_TITLE,
                turma != null ? Strings.TURMA_CONTENT_HEADER_TIP : Strings.TURMA_NEW_CONTENT_HEADER_TIP,
                Dimens.TURMA_DIALOG_WIDTH,
                Dimens.TURMA_DIALOG_HEIGHT
        );
        this.forEdition = forEdition;
        this.turma = turma;
        this.init();
    }

    public boolean isForEdition() {
        return forEdition;
    }

    public void setForEdition(boolean forEdition) {
        this.forEdition = forEdition;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    /**
     * Inicializar componentes
     */
    private void init() {
        this.content = new TurmaContent(this);
    }

    @Override
    public TurmaContent getContent() {
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
