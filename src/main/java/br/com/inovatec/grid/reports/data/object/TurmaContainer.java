/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.reports.data.object;

import br.com.inovatec.grid.entity.Turma;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zrobe
 */
public class TurmaContainer {
    
    private Turma turma;
    private List<AulasRow> aulasRows;

    public TurmaContainer() {
        this.aulasRows = new ArrayList<>();
    }

    public TurmaContainer(Turma turma, List<AulasRow> horarioRows) {
        this.turma = turma;
        this.aulasRows = horarioRows;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public List<AulasRow> getAulasRows() {
        return aulasRows;
    }

    public void setAulasRows(List<AulasRow> aulasRows) {
        this.aulasRows = aulasRows;
    }            
    
}
