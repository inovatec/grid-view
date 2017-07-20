/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.layout;

import br.com.inovatec.grid.view.content.TutorialContent;
import br.com.inovatec.grid.view.contract.FrameView;
import br.com.inovatec.grid.view.layout.template.DefaultView;
import br.com.inovatec.grid.view.values.Dimens;
import br.com.inovatec.grid.view.values.Strings;

/**
 *
 * @author zrobe
 */
public class TutorialView extends DefaultView {

    private TutorialContent content;

    public TutorialView(FrameView requester) {
        super(requester,
                Strings.TUTORIAL_TITLE,
                Strings.TUTORIAL_TITLE,
                Strings.TUTORIAL_CONTENT_HEADER_TIP,
                Dimens.TUTORIAL_DIALOG_WIDTH,
                Dimens.TUTORIAL_DIALOG_HEIGHT
        );
        this.init();
    }

    /**
     * Inicializar componentes
     */
    private void init() {
        this.content = new TutorialContent(this);
    }

    @Override
    public TutorialContent getContent() {
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
