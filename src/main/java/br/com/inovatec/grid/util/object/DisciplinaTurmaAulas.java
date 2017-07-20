/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.util.object;

import br.com.inovatec.grid.entity.DisciplinaTurma;

/**
 *
 * @author zrobe
 */
public class DisciplinaTurmaAulas {

    private DisciplinaTurma disciplinaTurma;
    private int totalAulas;

    public DisciplinaTurmaAulas(DisciplinaTurma disciplinaTurma, int totalAulas) {
        this.disciplinaTurma = disciplinaTurma;
        this.totalAulas = totalAulas;
    }

    public DisciplinaTurma getDisciplinaTurma() {
        return disciplinaTurma;
    }

    public void setDisciplinaTurma(DisciplinaTurma disciplinaTurma) {
        this.disciplinaTurma = disciplinaTurma;
    }

    public int getTotalAulas() {
        return totalAulas;
    }

    public void setTotalAulas(int totalAulas) {
        this.totalAulas = totalAulas;
    }

    public void incrementTotalAulas() {
        ++this.totalAulas;
    }

    public void decrementTotalAulas() {
        if (this.totalAulas != 0) {
            --this.totalAulas;
        }
    }

}
