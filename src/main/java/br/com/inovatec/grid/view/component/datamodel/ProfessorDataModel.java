/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.component.datamodel;

import br.com.inovatec.grid.entity.Professor;
import br.com.inovatec.grid.util.StringUtils;
import br.com.inovatec.grid.view.component.model.AbstractTableDataModel;
import java.text.ParseException;
import java.util.List;

/**
 *
 * @author zrobe
 */
public class ProfessorDataModel extends AbstractTableDataModel<Professor> {

    public ProfessorDataModel(List<Professor> dados) {
        super(dados);
    }

    @Override
    public String[] getColumns() {
        return new String[]{"Nome", "E-mail", "Telefone/Celular"};
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Professor p = this.getDados().get(linha);
        switch (coluna) {
            case 0:
                return p.getNome();
            case 1:
                return p.getEmail();
            case 2: {
                if (StringUtils.isValid(p.getTelefone())) {
                    try {
                        return StringUtils.formatString(p.getTelefone(), StringUtils.TELEFONE_MASK);
                    } catch (ParseException ex) {
                        return null;
                    }
                } else {
                    try {
                        return StringUtils.formatString(p.getCelular(), StringUtils.CELULAR_MASK);
                    } catch (ParseException ex) {
                        return null;
                    }
                }
            }
        }
        return null;
    }

}
