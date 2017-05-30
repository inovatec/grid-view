/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.layout;

import br.com.inovatec.grid.entity.Sala;
import br.com.inovatec.grid.view.content.SalaContent;
import br.com.inovatec.grid.view.contract.DialogView;
import br.com.inovatec.grid.view.layout.template.DefaultView;
import br.com.inovatec.grid.view.values.Dimens;
import br.com.inovatec.grid.view.values.Strings;

/**
 *
 * @author zrobe
 */
public class SalaView extends DefaultView {

    private Sala sala;
    private SalaContent content;
    private boolean forEdition;

    public SalaView(DialogView requester, boolean forEdition, Sala sala) {
        super(requester,
                Strings.SALA_DIALOG_TITLE,
                Strings.SALA_DIALOG_TITLE,
                sala != null ? Strings.SALA_CONTENT_HEADER_TIP : Strings.SALA_NEW_CONTENT_HEADER_TIP,
                Dimens.SALA_DIALOG_WIDTH,
                Dimens.SALA_DIALOG_HEIGHT
        );
        this.forEdition = forEdition;
        this.sala = sala;
        this.init();
    }

    public boolean isForEdition() {
        return forEdition;
    }

    public void setForEdition(boolean forEdition) {
        this.forEdition = forEdition;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    /**
     * Inicializar componentes
     */
    private void init() {
        this.content = new SalaContent(this);
    }

    @Override
    public SalaContent getContent() {
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
