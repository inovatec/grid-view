/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.layout;

import br.com.inovatec.grid.view.content.AulasContent;
import br.com.inovatec.grid.view.contract.FrameView;
import br.com.inovatec.grid.view.layout.template.DefaultView;
import br.com.inovatec.grid.view.session.Session;
import br.com.inovatec.grid.view.values.Dimens;
import br.com.inovatec.grid.view.values.Strings;

/**
 *
 * @author zrobe
 */
public class AulasView extends DefaultView {

    private AulasContent content;

    public AulasView(FrameView requester) {
        super(
                requester,
                Strings.AULAS_DIALOG_TITLE,
                Strings.AULAS_DIALOG_TITLE,
                Strings.getReplacedString(
                        Strings.AULAS_CONTENT_HEADER_TIP,
                        Integer.toString(Session.getInstance().getEscola().getPeriodoCorrente())
                ),
                Dimens.AULAS_DIALOG_WIDTH,
                Dimens.AULAS_DIALOG_HEIGHT
        );
        this.init();
    }

    /**
     * Inicializar componentes
     */
    private void init() {
        this.content = new AulasContent(this);
    }

    @Override
    public AulasContent getContent() {
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
