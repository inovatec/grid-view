/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.layout;

import br.com.inovatec.grid.view.content.EscolaContent;
import br.com.inovatec.grid.view.contract.FrameView;
import br.com.inovatec.grid.view.layout.template.DefaultView;
import br.com.inovatec.grid.view.values.Dimens;
import br.com.inovatec.grid.view.values.Strings;

/**
 *
 * @author zrobe
 */
public class EscolaView extends DefaultView {
    
    private EscolaContent content;
    private boolean forEdition;
    
    public EscolaView(FrameView requester, boolean forEdition) {
        super(requester, Strings.ESCOLA_DIALOG_TITLE, Strings.ESCOLA_CONTENT_HEADER_TITLE, null, Dimens.ESCOLA_DIALOG_WIDTH, Dimens.ESCOLA_DIALOG_HEIGHT);
        this.forEdition = forEdition;
        this.init();
    }

    public boolean isForEdition() {
        return forEdition;
    }

    public void setForEdition(boolean forEdition) {
        this.forEdition = forEdition;
    }
    
    /**
     * Inicializar componentes
     */
    private void init() {
        this.content = new EscolaContent(this);
    }

    @Override
    public EscolaContent getContent() {
        return this.content;
    }
    
    @Override
    public void refreshViewRequester() {
        this.getRequester().refresh();
    }

    @Override
    public void refresh() {
        // Adicionar avisos
        this.getContent().refresh();
    }

    /**
     * Sobrescrever metodo de encerramento para chamar o evento configurado no
     * content
     * 
     */
    @Override
    public void close() {
        if (this.getContent().allowClosing()) {
            super.close();
        }
    }    
    
}
