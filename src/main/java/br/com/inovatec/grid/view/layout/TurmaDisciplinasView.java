/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.layout;

import br.com.inovatec.grid.entity.Turma;
import br.com.inovatec.grid.view.content.TurmaDisciplinasContent;
import br.com.inovatec.grid.view.contract.DialogView;
import br.com.inovatec.grid.view.layout.template.DefaultView;
import br.com.inovatec.grid.view.values.Dimens;
import br.com.inovatec.grid.view.values.Strings;

/**
 *
 * @author zrobe
 */
public class TurmaDisciplinasView extends DefaultView {

    private final Turma turma;
    private TurmaDisciplinasContent content;

    public TurmaDisciplinasView(DialogView requester, Turma turma) {
        super(requester,
                Strings.TURMA_DISCIPLINAS_DIALOG_TITLE,
                Strings.TURMA_DISCIPLINAS_DIALOG_TITLE,
                turma.getId() != null ? Strings.getReplacedString(Strings.TURMA_DISCIPLINAS_CONTENT_HEADER_TIP, turma.getNome()) : null,
                Dimens.TURMA_DISCIPLINAS_DIALOG_WIDTH,
                Dimens.TURMA_DISCIPLINAS_DIALOG_HEIGHT
        );
        this.turma = turma;
        this.init();
    }

    public Turma getTurma() {
        return turma;
    }

    /**
     * Inicializar componentes
     */
    private void init() {
        this.content = new TurmaDisciplinasContent(this);
    }

    @Override
    public TurmaDisciplinasContent getContent() {
        return content;
    }

    @Override
    public void refreshViewRequester() {
        this.getRequester().refresh();
    }

    @Override
    public void refresh() {
        this.content.refreshContent();
    }

}
