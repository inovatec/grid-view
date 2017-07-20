/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.layout;

import br.com.inovatec.grid.entity.Aula;
import br.com.inovatec.grid.entity.Turma;
import br.com.inovatec.grid.util.DateTimeUtils;
import br.com.inovatec.grid.util.object.DisciplinaTurmaAulas;
import br.com.inovatec.grid.view.content.DisciplinasTurmaContent;
import br.com.inovatec.grid.view.contract.FrameView;
import br.com.inovatec.grid.view.layout.template.DefaultView;
import br.com.inovatec.grid.view.values.Dimens;
import br.com.inovatec.grid.view.values.Strings;
import java.util.List;

/**
 *
 * @author zrobe
 */
public class DisciplinasTurmaView extends DefaultView {

    private final Aula aula;
    private final Turma turma;
    private final List<DisciplinaTurmaAulas> disciplinaTurmaAulas;
    private DisciplinasTurmaContent content;

    public DisciplinasTurmaView(FrameView requester, Aula aula, Turma turma, List<DisciplinaTurmaAulas> disciplinaTurmaAulas) {
        super(requester,
                Strings.getReplacedString(Strings.DISCIPLINAS_TURMA_DIALOG_TITLE, turma.getLabel()),
                Strings.getReplacedString(Strings.DISCIPLINAS_TURMA_DIALOG_TITLE, turma.getLabel()),
                Strings.getReplacedString(
                        Strings.DISCIPLINAS_TURMA_CONTENT_HEADER_TIP,
                        DateTimeUtils.getMinimalFormattedTime(aula.getHorario().getInicio()),
                        DateTimeUtils.getMinimalFormattedTime(aula.getHorario().getFim())
                ),
                Dimens.DISCIPLINAS_TURMA_DIALOG_WIDTH,
                Dimens.DISCIPLINAS_TURMA_DIALOG_HEIGHT
        );
        this.aula = aula;
        this.turma = turma;
        this.disciplinaTurmaAulas = disciplinaTurmaAulas;
        this.init();
    }

    /**
     * Inicializar componentes
     */
    private void init() {
        this.content = new DisciplinasTurmaContent(this);
    }

    public Aula getAula() {
        return aula;
    }

    public Turma getTurma() {
        return turma;
    }

    public List<DisciplinaTurmaAulas> getDisciplinaTurmaAulas() {
        return disciplinaTurmaAulas;
    }

    @Override
    public DisciplinasTurmaContent getContent() {
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
