/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.component.datamodel;

import br.com.inovatec.grid.entity.DisciplinaTurma;
import br.com.inovatec.grid.view.component.model.AbstractTableDataModel;
import java.util.List;

/**
 *
 * @author zrobe
 */
public class DisciplinaTurmaDataModel extends AbstractTableDataModel<DisciplinaTurma> {

    public DisciplinaTurmaDataModel(List<DisciplinaTurma> dados) {
        super(dados);
    }

    @Override
    public String[] getColumns() {
        return new String[]{"Disciplina", "Aulas por semana", "Carga Horária Total"};
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch (coluna) {
            case 0:
                return this.getDados().get(linha).getDisciplina().getNome();
            case 1:
                return this.getDados().get(linha).getAulasSemanaTotal();
            case 2:
                return this.getDados().get(linha).getCargaHorariaTotal();
        }
        return null;
    }

}
