/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.reports.data.object;

import br.com.inovatec.grid.entity.Aula;
import br.com.inovatec.grid.entity.Horario;

/**
 *
 * @author zrobe
 */
public class AulasRow {

    private final Horario horario;
    private final Aula segunda;
    private final Aula terca;
    private final Aula quarta;
    private final Aula quinta;
    private final Aula sexta;

    public AulasRow(Horario horario, Aula segunda, Aula terca, Aula quarta, Aula quinta, Aula sexta) {
        this.horario = horario;
        this.segunda = segunda;
        this.terca = terca;
        this.quarta = quarta;
        this.quinta = quinta;
        this.sexta = sexta;
    }

    public Horario getHorario() {
        return horario;
    }

    public Aula getSegunda() {
        return segunda;
    }

    public Aula getTerca() {
        return terca;
    }

    public Aula getQuarta() {
        return quarta;
    }

    public Aula getQuinta() {
        return quinta;
    }

    public Aula getSexta() {
        return sexta;
    }

    @Override
    public String toString() {
        return "AulasRow{" + "horario=" + horario + ", segunda=" + segunda + ", terca=" + terca + ", quarta=" + quarta + ", quinta=" + quinta + ", sexta=" + sexta + '}';
    }
    
}
