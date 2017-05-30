/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.layout;

import br.com.inovatec.grid.view.content.LoginContent;
import br.com.inovatec.grid.view.contract.FrameView;
import br.com.inovatec.grid.view.layout.template.DefaultView;
import br.com.inovatec.grid.view.values.Dimens;
import br.com.inovatec.grid.view.values.Strings;

/**
 *
 * @author zrobe
 */
public class LoginView extends DefaultView {

    private LoginContent content;

    public LoginView(FrameView requester) {
        super(requester, Strings.LOGIN_DIALOG_TITLE, Strings.LOGIN_DIALOG_TITLE, Strings.LOGIN_DIALOG_HEADER_TIP, Dimens.LOGIN_DIALOG_WIDTH, Dimens.LOGIN_DIALOG_HEIGHT);
        this.init();
    }

    /**
     * Inicializar componentes
     */
    private void init() {
        this.content = new LoginContent(this);
    }

    @Override
    public LoginContent getContent() {
        return this.content;
    }

    @Override
    public void refreshViewRequester() {
        getRequester().refresh();
    }

    @Override
    public void refresh() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}