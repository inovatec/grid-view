/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.component.datamodel;

import br.com.inovatec.grid.entity.Sala;
import br.com.inovatec.grid.view.component.model.AbstractTableDataModel;
import java.util.List;

/**
 *
 * @author zrobe
 */
public class SalaDataModel extends AbstractTableDataModel<Sala> {

    public SalaDataModel(List<Sala> dados) {
        super(dados);
    }

    @Override
    public String[] getColumns() {
        return new String[]{"Nome", "Tipo", "Situação"};
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch (coluna) {
            case 0:
                return this.getDados().get(linha).getNome();
            case 1:
                return this.getDados().get(linha).getTipoSala().toString();
            case 2:
                return this.getDados().get(linha).getStatus().toString();
        }
        return null;
    }

}
