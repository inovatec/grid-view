/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.layout;

import br.com.inovatec.grid.entity.Gerenciavel;
import br.com.inovatec.grid.view.content.HorariosContent;
import br.com.inovatec.grid.view.layout.template.DefaultView;
import br.com.inovatec.grid.view.values.Dimens;

/**
 *
 * @author zrobe
 */
public class HorariosView extends DefaultView {

    private final boolean consideraDuracaoAula;
    private final Gerenciavel gerenciavel;
    private HorariosContent content;

    public HorariosView(DefaultView requester, String title, String headerTitle, String headerTip, Gerenciavel gerenciavel, boolean consideraDuracaoAula) {
        super(requester, title, headerTitle, headerTip, Dimens.HORARIOS_DIALOG_WIDTH, Dimens.HORARIOS_DIALOG_HEIGHT);
        this.gerenciavel = gerenciavel;
        this.consideraDuracaoAula = consideraDuracaoAula;
        this.init();
    }

    /**
     * Inicializar componentes
     */
    private void init() {
        this.content = new HorariosContent(this);
    }

    public Gerenciavel getGerenciavel() {
        return gerenciavel;
    }

    public boolean isConsideraDuracaoAula() {
        return consideraDuracaoAula;
    }
    
    @Override
    public HorariosContent getContent() {
        return this.content;
    }

    @Override
    public void refreshViewRequester() {
        this.getRequester().refresh();
    }

    @Override
    public void refresh() {
        this.getContent().refreshContent();
    }

    @Override
    public void close() {
        super.close();
    }

}
