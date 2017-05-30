/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.layout;

import br.com.inovatec.grid.view.content.ProfessoresContent;
import br.com.inovatec.grid.view.contract.FrameView;
import br.com.inovatec.grid.view.layout.template.DefaultView;
import br.com.inovatec.grid.view.values.Dimens;
import br.com.inovatec.grid.view.values.Strings;

/**
 *
 * @author zrobe
 */
public class ProfessoresView extends DefaultView {
    
    private ProfessoresContent content;
    
    public ProfessoresView(FrameView requester) {
        super(requester, Strings.PROFESSORES_DIALOG_TITLE, Strings.PROFESSORES_DIALOG_TITLE, Strings.PROFESSORES_CONTENT_HEADER_TIP, Dimens.PROFESSORES_DIALOG_WIDTH, Dimens.PROFESSORES_DIALOG_HEIGHT);
        this.init();
    }
    
    /**
     * Inicializar componentes
     */
    private void init() {
        this.content = new ProfessoresContent(this);
    }

    @Override
    public ProfessoresContent getContent() {
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
