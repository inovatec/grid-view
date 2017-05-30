/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.entity;

import br.com.inovatec.grid.entity.converter.LocalTimeAttributeConverter;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

/**
 *
 * @author zrobe
 */
@Entity
@DiscriminatorValue("E")
@NamedQueries(value = {
    @NamedQuery(name = "escola.findAll", query = "SELECT e FROM Escola e")
})
public class Escola extends Gerenciavel implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Column(length = 100)
    private String nome;
    @Column(length = 6)
    private Integer periodoCorrente;
    private Integer duracaoAula; // em minutos
    private Integer semanasLetivas;
    
    @Column
    @Convert(converter = LocalTimeAttributeConverter.class)
    private LocalTime inicioAula;
    
    @Transient
    private List<DiaAula> diasAula;

    public Escola() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getPeriodoCorrente() {
        return periodoCorrente;
    }

    public void setPeriodoCorrente(Integer periodoCorrente) {
        this.periodoCorrente = periodoCorrente;
    }

    public Integer getDuracaoAula() {
        return duracaoAula;
    }

    public void setDuracaoAula(Integer duracaoAula) {
        this.duracaoAula = duracaoAula;
    }

    public Integer getSemanasLetivas() {
        return semanasLetivas;
    }

    public void setSemanasLetivas(Integer semanasLetivas) {
        this.semanasLetivas = semanasLetivas;
    }

    public LocalTime getInicioAula() {
        return inicioAula;
    }

    public void setInicioAula(LocalTime inicioAula) {
        this.inicioAula = inicioAula;
    }

    public List<DiaAula> getDiasAula() {
        return diasAula;
    }

    public void setDiasAula(List<DiaAula> diasAula) {
        this.diasAula = diasAula;
    }

    @Override
    public String toString() {
        return "Escola{" + super.toString() + ", nome=" + nome + ", periodoCorrente=" + periodoCorrente + ", duracaoAula=" + duracaoAula + ", semanasLetivas=" + semanasLetivas + ", inicioAula=" + inicioAula + '}';
    }
    
}