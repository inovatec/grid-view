/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.layout;

import br.com.inovatec.grid.entity.DisciplinaTurma;
import br.com.inovatec.grid.view.content.TurmaDisciplinaEditContent;
import br.com.inovatec.grid.view.contract.DialogView;
import br.com.inovatec.grid.view.layout.template.DefaultView;
import br.com.inovatec.grid.view.values.Dimens;
import br.com.inovatec.grid.view.values.Strings;
import java.util.List;

/**
 *
 * @author zrobe
 */
public class TurmaDisciplinaEditView extends DefaultView {

    private final int index;
    private final DisciplinaTurma disciplinaTurma;
    private final List<DisciplinaTurma> disciplinasTurma;
    private TurmaDisciplinaEditContent content;
    
    public TurmaDisciplinaEditView(DialogView requester, DisciplinaTurma turmaDisciplina, List<DisciplinaTurma> turmaDisciplinas, int index) {
        super(requester, Strings.TURMA_DISCIPLINA_EDIT_DIALOG_TITLE, Strings.TURMA_DISCIPLINA_EDIT_DIALOG_TITLE, null, Dimens.TURMA_DISCIPLINA_EDIT_DIALOG_WIDTH, Dimens.TURMA_DISCIPLINA_EDIT_DIALOG_HEIGHT);
        this.disciplinaTurma = turmaDisciplina;
        this.disciplinasTurma = turmaDisciplinas;
        this.index = index;
        this.init();
    }
    
    /**
     * Inicializar componentes
     */
    private void init() {
        this.content = new TurmaDisciplinaEditContent(this);
    }

    @Override
    public TurmaDisciplinaEditContent getContent() {
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

    public DisciplinaTurma getDisciplinaTurma() {
        return this.disciplinaTurma;
    }

    public List<DisciplinaTurma> getDisciplinasTurma() {
        return disciplinasTurma;
    }

    public int getIndex() {
        return index;
    }
    
}
