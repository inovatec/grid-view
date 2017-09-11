/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.layout;

import br.com.inovatec.grid.entity.Aula;
import br.com.inovatec.grid.util.DateTimeUtils;
import br.com.inovatec.grid.view.content.ProfessoresCompetentesContent;
import br.com.inovatec.grid.view.contract.FrameView;
import br.com.inovatec.grid.view.layout.template.DefaultView;
import br.com.inovatec.grid.view.values.Dimens;
import br.com.inovatec.grid.view.values.Strings;

/**
 *
 * @author zrobe
 */
public class ProfessoresCompetentesView extends DefaultView {

    private final Aula aula;
    private ProfessoresCompetentesContent content;

    public ProfessoresCompetentesView(FrameView requester, Aula aula) {
        super(requester,
                Strings.getReplacedString(Strings.PROFESSORES_COMPETENTES_DIALOG_TITLE, aula.getDisciplina().getNome()),
                Strings.getReplacedString(Strings.PROFESSORES_COMPETENTES_DIALOG_TITLE, aula.getDisciplina().getNome()),
                Strings.getReplacedString(
                        Strings.PROFESSORES_COMPETENTES_CONTENT_HEADER_TIP,
                        DateTimeUtils.getMinimalFormattedTime(aula.getHorario().getInicio()),
                        DateTimeUtils.getMinimalFormattedTime(aula.getHorario().getFim())
                ),
                Dimens.PROFESSORES_COMPETENTES_DIALOG_WIDTH,
                Dimens.PROFESSORES_COMPETENTES_DIALOG_HEIGHT
        );
        this.aula = aula;
        this.init();
    }

    /**
     * Inicializar componentes
     */
    private void init() {
        this.content = new ProfessoresCompetentesContent(this);
    }

    public Aula getAula() {
        return aula;
    }

    @Override
    public ProfessoresCompetentesContent getContent() {
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
