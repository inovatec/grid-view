/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.layout;

import br.com.inovatec.grid.entity.Professor;
import br.com.inovatec.grid.view.content.CompetenciasContent;
import br.com.inovatec.grid.view.contract.DialogView;
import br.com.inovatec.grid.view.layout.template.DefaultView;
import br.com.inovatec.grid.view.values.Dimens;
import br.com.inovatec.grid.view.values.Strings;

/**
 *
 * @author zrobe
 */
public class CompetenciasView extends DefaultView {

    private final Professor professor;
    private CompetenciasContent content;

    public CompetenciasView(DialogView requester, Professor professor) {
        super(requester,
                Strings.COMPETENCIAS_DIALOG_TITLE,
                Strings.COMPETENCIAS_DIALOG_TITLE,
                professor.getId() != null ? Strings.getReplacedString(Strings.COMPETENCIAS_PROFESSOR_CONTENT_HEADER_TIP, professor.getNome()) : Strings.COMPETENCIAS_CONTENT_HEADER_TIP,
                Dimens.COMPETENCIAS_DIALOG_WIDTH,
                Dimens.COMPETENCIAS_DIALOG_HEIGHT
        );
        this.professor = professor;
        this.init();
    }

    public Professor getProfessor() {
        return professor;
    }

    /**
     * Inicializar componentes
     */
    private void init() {
        this.content = new CompetenciasContent(this);
    }

    @Override
    public CompetenciasContent getContent() {
        return content;
    }

    @Override
    public void refreshViewRequester() {
        this.getRequester().refresh();
    }

    @Override
    public void refresh() {
    }

}
