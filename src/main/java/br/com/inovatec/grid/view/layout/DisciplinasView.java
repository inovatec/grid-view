/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.layout;

import br.com.inovatec.grid.view.content.DisciplinasContent;
import br.com.inovatec.grid.view.contract.FrameView;
import br.com.inovatec.grid.view.layout.template.DefaultView;
import br.com.inovatec.grid.view.values.Dimens;
import br.com.inovatec.grid.view.values.Strings;

/**
 *
 * @author zrobe
 */
public class DisciplinasView extends DefaultView {
    
    private DisciplinasContent content;
    
    public DisciplinasView(FrameView requester) {
        super(requester, Strings.DISCIPLINAS_DIALOG_TITLE, Strings.DISCIPLINAS_DIALOG_TITLE, Strings.DISCIPLINAS_CONTENT_HEADER_TIP, Dimens.DISCIPLINAS_DIALOG_WIDTH, Dimens.DISCIPLINAS_DIALOG_HEIGHT);
        this.init();
    }
    
    /**
     * Inicializar componentes
     */
    private void init() {
        this.content = new DisciplinasContent(this);
    }

    @Override
    public DisciplinasContent getContent() {
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
