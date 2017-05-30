/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.layout;

import br.com.inovatec.grid.view.content.SalasContent;
import br.com.inovatec.grid.view.contract.FrameView;
import br.com.inovatec.grid.view.layout.template.DefaultView;
import br.com.inovatec.grid.view.values.Dimens;
import br.com.inovatec.grid.view.values.Strings;

/**
 *
 * @author zrobe
 */
public class SalasView extends DefaultView {
    
    private SalasContent content;
    
    public SalasView(FrameView requester) {
        super(requester, Strings.SALAS_DIALOG_TITLE, Strings.SALAS_DIALOG_TITLE, Strings.SALAS_CONTENT_HEADER_TIP, Dimens.SALAS_DIALOG_WIDTH, Dimens.SALAS_DIALOG_HEIGHT);
        this.init();
    }
    
    /**
     * Inicializar componentes
     */
    private void init() {
        this.content = new SalasContent(this);
    }

    @Override
    public SalasContent getContent() {
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
