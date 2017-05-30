/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.layout;

import br.com.inovatec.grid.view.content.TurmasContent;
import br.com.inovatec.grid.view.contract.FrameView;
import br.com.inovatec.grid.view.layout.template.DefaultView;
import br.com.inovatec.grid.view.session.Session;
import br.com.inovatec.grid.view.values.Dimens;
import br.com.inovatec.grid.view.values.Strings;

/**
 *
 * @author zrobe
 */
public class TurmasView extends DefaultView {

    private TurmasContent content;

    public TurmasView(FrameView requester) {
        super(requester,
                Strings.TURMAS_DIALOG_TITLE,
                Strings.TURMAS_DIALOG_TITLE,
                Strings.getReplacedString(Strings.TURMAS_CONTENT_HEADER_TIP, Session.getInstance().getEscola().getPeriodoCorrente().toString()),
                Dimens.TURMAS_DIALOG_WIDTH,
                Dimens.TURMAS_DIALOG_HEIGHT
        );
        this.init();
    }

    /**
     * Inicializar componentes
     */
    private void init() {
        this.content = new TurmasContent(this);
    }

    @Override
    public TurmasContent getContent() {
        return this.content;
    }

    @Override
    public void refreshViewRequester() {
    }

    @Override
    public void refresh() {
        this.getContent().refreshContent();
    }

}
