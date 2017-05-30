/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.entity;

import br.com.inovatec.grid.view.contract.Selectable;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author zrobe
 */
@Entity
@Table(uniqueConstraints={
    @UniqueConstraint(columnNames = {"diaDaSemana", "periodo"})
})
@NamedQueries(value = {
    @NamedQuery(name = "diaAula.findAll", query = "SELECT da FROM DiaAula da"),
    @NamedQuery(name = "diaAula.findAllByPeriodo", query = "SELECT da FROM DiaAula da WHERE da.periodo = :periodo ORDER BY da.diaDaSemana")
})
public class DiaAula implements Entidade<Long, DiaAula>, Serializable, Selectable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @SequenceGenerator(name = "DIA_AULA_ID", initialValue = 1, allocationSize = 1, sequenceName = "DIA_AULA_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DIA_AULA_ID")
    private Long id;
    
    @OrderBy
    @Enumerated(EnumType.ORDINAL)
    private DayOfWeek diaDaSemana;
    private int totalAulas;
    
    @OrderBy
    @Column(length = 6)
    private Integer periodo;

    public DiaAula() {
    }

    public DiaAula(DayOfWeek diaDaSemana, int totalAulas, Integer periodo) {
        this.diaDaSemana = diaDaSemana;
        this.totalAulas = totalAulas;
        this.periodo = periodo;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public DayOfWeek getDiaDaSemana() {
        return diaDaSemana;
    }

    public void setDiaDaSemana(DayOfWeek diaDaSemana) {
        this.diaDaSemana = diaDaSemana;
    }

    public int getTotalAulas() {
        return totalAulas;
    }

    public Integer getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Integer periodo) {
        this.periodo = periodo;
    }

    public void setTotalAulas(int totalAulas) {
        this.totalAulas = totalAulas;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.diaDaSemana);
        hash = 71 * hash + Objects.hashCode(this.periodo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DiaAula other = (DiaAula) obj;
        if (this.diaDaSemana != other.diaDaSemana) {
            return false;
        }
        if (!Objects.equals(this.periodo, other.periodo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DiaAula{" + "id=" + id + ", diaDaSemana=" + diaDaSemana + ", totalAulas=" + totalAulas + ", periodoCorrente=" + periodo + '}';
    }

    @Override
    public String getLabel() {
        return this.diaDaSemana.getDisplayName(TextStyle.FULL, Locale.getDefault());
    }
    
    @Override
    public int compareTo(DiaAula o) {
        return this.diaDaSemana.compareTo(o.diaDaSemana);
    }
    
}
