/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.layout;

import br.com.inovatec.grid.view.content.SobreContent;
import br.com.inovatec.grid.view.contract.FrameView;
import br.com.inovatec.grid.view.layout.template.DefaultView;
import br.com.inovatec.grid.view.values.Dimens;
import br.com.inovatec.grid.view.values.Strings;

/**
 *
 * @author zrobe
 */
public class SobreView extends DefaultView {

    private SobreContent content;

    public SobreView(FrameView requester) {
        super(requester,
                Strings.SOBRE_TITLE,
                Strings.SOBRE_TITLE,
                Strings.SOBRE_CONTENT_HEADER_TIP,
                Dimens.SOBRE_DIALOG_WIDTH,
                Dimens.SOBRE_DIALOG_HEIGHT
        );
        this.init();
    }

    /**
     * Inicializar componentes
     */
    private void init() {
        this.content = new SobreContent(this);
    }

    @Override
    public SobreContent getContent() {
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
