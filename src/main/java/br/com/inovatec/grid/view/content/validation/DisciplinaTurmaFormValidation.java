/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.content.validation;

import br.com.inovatec.grid.entity.Disciplina;
import br.com.inovatec.grid.entity.DisciplinaTurma;
import br.com.inovatec.grid.view.component.exception.NoItemSelectedException;
import br.com.inovatec.grid.view.component.form.NumberTextField;
import br.com.inovatec.grid.view.component.form.SelectOneMenu;
import br.com.inovatec.grid.view.content.exception.FormException;
import br.com.inovatec.grid.view.values.Strings;
import java.util.List;

/**
 *
 * @author zrobe
 */
public class DisciplinaTurmaFormValidation {

    /**
     * Obter uma DisciplinaTurma de acordo com o preenchido pelo formulario
     *
     * @param disciplinasTurma
     * @param disciplinaSelectOneMenu
     * @param aulasSemanaTotalNumberTextField
     * @param cargaHorariaTotalNumberTextField
     * @param index
     * @return
     * @throws br.com.inovatec.grid.view.content.exception.FormException
     */
    public static DisciplinaTurma getDisciplinaTurmaByForm(List<DisciplinaTurma> disciplinasTurma, int index, SelectOneMenu<Disciplina> disciplinaSelectOneMenu, NumberTextField aulasSemanaTotalNumberTextField, NumberTextField cargaHorariaTotalNumberTextField) throws FormException {

        DisciplinaTurma disciplinaTurma = new DisciplinaTurma();

        try {
            if (disciplinaSelectOneMenu != null) {
                disciplinaTurma.setDisciplina(disciplinaSelectOneMenu.getSelectedItem());
            }
        } catch (NoItemSelectedException ex) {
            throw new FormException(Strings.VALIDATION_MESSAGE_TURMA_DISCIPLINA_NOT_NULL);
        }

        if (aulasSemanaTotalNumberTextField.getValue() > 0) {
            disciplinaTurma.setAulasSemanaTotal(aulasSemanaTotalNumberTextField.getValue());
        } else {
            throw new FormException(Strings.VALIDATION_MESSAGE_TURMA_DISCIPLINA_AULAS_SEMANA_NOT_NULL);
        }
        
        if (cargaHorariaTotalNumberTextField.getValue() > 0) {
            disciplinaTurma.setCargaHorariaTotal(cargaHorariaTotalNumberTextField.getValue());
        } else {
            throw new FormException(Strings.VALIDATION_MESSAGE_TURMA_DISCIPLINA_CARGA_HORARIA_TOTAL_NOT_NULL);
        }
        
        if (!newDisciplinaTurmaIsValid(disciplinaTurma, disciplinasTurma, index)) {
            throw new FormException(Strings.VALIDATION_MESSAGE_TURMA_DISCIPLINA_INVALID);
        }
        
        return disciplinaTurma;

    }

    /**
     * Verifica se a nova Disciplina da Turma ja foi informada
     *
     * @param disciplinaTurma
     * @param disciplinasTurma
     * @param index
     *
     * @return
     */
    private static boolean newDisciplinaTurmaIsValid(DisciplinaTurma disciplinaTurma, List<DisciplinaTurma> disciplinasTurma, int index) {
        for (int i = 0; i < disciplinasTurma.size(); i++) {
            DisciplinaTurma dt = disciplinasTurma.get(i);
            // Verificar se a disciplinaTurma eh igual a alguma ja existente
            if (disciplinaTurma.equals(dt) && i != index) {
                return false;
            }
        }
        return true;
    }

}
