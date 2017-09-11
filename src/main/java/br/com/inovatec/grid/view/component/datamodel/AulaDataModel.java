/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.component.datamodel;

import br.com.inovatec.grid.entity.Aula;
import br.com.inovatec.grid.view.component.model.AbstractTableDataModel;
import br.com.inovatec.grid.view.values.Strings;
import java.util.List;

/**
 *
 * @author zrobe
 */
public class AulaDataModel extends AbstractTableDataModel<Aula> {

    public AulaDataModel(List<Aula> dados) {
        super(dados);
    }

    @Override
    public String[] getColumns() {
        return new String[]{"Dia", "Horário", "Turma", "Disciplina", "Professor"};
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Aula a = this.getDados().get(linha);
        switch (coluna) {
            case 0:
                return a.getHorario().getDiaAula().getLabel();
            case 1:
                return a.getHorario().getInicio();
            case 2:
                return getTurmaValue(a);
            case 3:
                return getDisciplinaValue(a);
            case 4:
                return getProfessorValue(a);
        }
        return null;
    }
    
    private String getProfessorValue(Aula a) {
        return a.getProfessor() != null ? a.getProfessor().getNomeReduzido() : Strings.GRADE_HORARIOS_AULA_PROFESSOR_NULL;
    }
    
    private String getTurmaValue(Aula a) {
        return a.getTurma() != null ? a.getTurma().getNome() : Strings.GRADE_HORARIOS_TURMA_VAGA;
    }
    
    private String getDisciplinaValue(Aula a) {
        return a.getDisciplina()!= null ? a.getDisciplina().getNome() : Strings.GRADE_HORARIOS_STATUS_VAGO;
    }

}
