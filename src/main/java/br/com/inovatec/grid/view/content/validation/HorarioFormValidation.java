/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.content.validation;

import br.com.inovatec.grid.entity.DiaAula;
import br.com.inovatec.grid.entity.Horario;
import br.com.inovatec.grid.util.DateTimeUtils;
import br.com.inovatec.grid.view.component.exception.NoItemSelectedException;
import br.com.inovatec.grid.view.component.form.MaskedTextField;
import br.com.inovatec.grid.view.component.form.SelectOneMenu;
import br.com.inovatec.grid.view.content.exception.FormException;
import br.com.inovatec.grid.view.values.Strings;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 *
 * @author zrobe
 */
public class HorarioFormValidation {
    
    /**
     * Obter um Horario de acordo com o preenchido pelo formulario
     *
     * @param horarios
     * @param diaAulaSelectOneMenu
     * @param horaInicioMaskedTextField
     * @param horaFimMaskedTextField
     * @param index
     * @return
     * @throws br.com.inovatec.grid.view.content.exception.FormException
     */
    public static Horario getHorarioByForm(List<Horario> horarios, int index, SelectOneMenu<DiaAula> diaAulaSelectOneMenu, MaskedTextField horaInicioMaskedTextField, MaskedTextField horaFimMaskedTextField) throws FormException {
        
        Horario horario = new Horario();

        try {
            horario.setDiaAula(diaAulaSelectOneMenu.getSelectedItem());
        } catch (NoItemSelectedException ex) {
            throw new FormException(Strings.VALIDATION_MESSAGE_DIA_AULA_NOT_NULL);
        }

        try {
            horario.setInicio(DateTimeUtils.getTime(horaInicioMaskedTextField.getText()));
        } catch (DateTimeParseException ex) {
            throw new FormException(Strings.getReplacedString(Strings.VALIDATION_MESSAGE_INVALID_TIME, "inicial"));
        }

        try {
            horario.setFim(DateTimeUtils.getTime(horaFimMaskedTextField.getText()));
        } catch (DateTimeParseException ex) {
            throw new FormException(Strings.getReplacedString(Strings.VALIDATION_MESSAGE_INVALID_TIME, "final"));
        }

        if (horario.getInicio().isAfter(horario.getFim())) {
            throw new FormException(Strings.VALIDATION_MESSAGE_BEGIN_TIME_EQUALS_END_TIME);
        }
        
        if (horario.getInicio().equals(horario.getFim())) {
            throw new FormException(Strings.VALIDATION_MESSAGE_BEGIN_TIME_AFTER_END_TIME);
        }

        if (!newHorarioIsValid(horario, horarios, index)) {
            throw new FormException(Strings.ERROR_MESSAGE_ALREADY_HORARIOS);
        }
        
        return horario;

    }
    
    /**
     * Verifica se o novo horario ja foi informado
     *
     * @param horario
     * @param horarios
     * @param index
     * 
     * @return
     */
    private static boolean newHorarioIsValid(Horario horario, List<Horario> horarios, int index) {
        for (int i = 0; i < horarios.size(); i++) {
            Horario h = horarios.get(i);
            // Verificar se o horario eh igual a algum ja existente ou esta no no mesmo intervalo de tempo, desde que nao seja ele mesmo
            if ((horario.equals(h) || horario.between(h)) && i != index) {
                return false;
            }
        }
        return true;
    }
    
}
