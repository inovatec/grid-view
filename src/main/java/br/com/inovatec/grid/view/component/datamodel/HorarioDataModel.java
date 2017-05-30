/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.component.datamodel;

import br.com.inovatec.grid.entity.Horario;
import br.com.inovatec.grid.util.DateTimeUtils;
import br.com.inovatec.grid.view.component.model.AbstractTableDataModel;
import java.util.List;

/**
 *
 * @author zrobe
 */
public class HorarioDataModel extends AbstractTableDataModel<Horario> {

    public HorarioDataModel(List<Horario> dados) {
        super(dados);
    }

    @Override
    public String[] getColumns() {
        return new String[]{"Dia", "Início", "Fim"};
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch (coluna) {
            case 0:
                return this.getDados().get(linha).getDiaAula().getLabel();
            case 1:
                return DateTimeUtils.getMinimalFormattedTime(this.getDados().get(linha).getInicio());
            case 2:
                return DateTimeUtils.getMinimalFormattedTime(this.getDados().get(linha).getFim());
        }
        return null;
    }

}
