/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.layout;

import br.com.inovatec.grid.view.content.DiasAulaContent;
import br.com.inovatec.grid.view.contract.DialogView;
import br.com.inovatec.grid.view.layout.template.DefaultView;
import br.com.inovatec.grid.view.values.Dimens;
import br.com.inovatec.grid.view.values.Strings;

/**
 *
 * @author zrobe
 */
public class DiasAulaView extends DefaultView {
    
    private DiasAulaContent content;
    
    public DiasAulaView(DialogView requester) {
        super(requester, Strings.DIAS_AULA_DIALOG_TITLE, Strings.DIAS_AULA_DIALOG_TITLE, Strings.DIAS_AULA_CONTENT_HEADER_TIP, Dimens.DIAS_AULA_DIALOG_WIDTH, Dimens.DIAS_AULA_DIALOG_HEIGHT);
        this.init();
    }
    
    /**
     * Inicializar componentes
     */
    private void init() {
        this.content = new DiasAulaContent(this);
    }

    @Override
    public DiasAulaContent getContent() {
        return this.content;
    }
    
    @Override
    public void refreshViewRequester() {
        this.getRequester().refresh();
    }

    @Override
    public void refresh() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
