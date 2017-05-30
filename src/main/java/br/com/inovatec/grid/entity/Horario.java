package br.com.inovatec.grid.entity;

import br.com.inovatec.grid.entity.converter.LocalTimeAttributeConverter;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;

@Entity
@NamedQueries({
    @NamedQuery(name = "horario.findAll", query = "SELECT h FROM Horario h"),
    @NamedQuery(name = "horario.findByGerenciavelAndPeriodo", query = "SELECT h FROM Horario h WHERE h.gerenciavel = :gerenciavel AND h.diaAula.periodo = :periodo ORDER BY h.diaAula, h.inicio"),
    @NamedQuery(name = "horario.countByDiaAula", query = "SELECT COUNT(h.id) FROM Horario h WHERE h.diaAula = :diaAula")
})
public class Horario implements Entidade<Long, Horario>, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "HORARIO_ID", initialValue = 1, allocationSize = 1, sequenceName = "HORARIO_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HORARIO_ID")
    private Long id;

    @OrderBy
    @JoinColumn(name = "DIA_AULA_ID")
    @ManyToOne
    private DiaAula diaAula;

    @Column
    @Convert(converter = LocalTimeAttributeConverter.class)
    private LocalTime inicio;

    @Column
    @Convert(converter = LocalTimeAttributeConverter.class)
    private LocalTime fim;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Gerenciavel gerenciavel;

    public Horario() {
    }

    public Horario(DiaAula diaAula, LocalTime inicio, LocalTime fim, Gerenciavel gerenciavel) {
        this.diaAula = diaAula;
        this.inicio = inicio;
        this.fim = fim;
        this.gerenciavel = gerenciavel;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public DiaAula getDiaAula() {
        return diaAula;
    }

    public void setDiaAula(DiaAula diaAula) {
        this.diaAula = diaAula;
    }

    public LocalTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalTime inicio) {
        this.inicio = inicio;
    }

    public LocalTime getFim() {
        return fim;
    }

    public void setFim(LocalTime fim) {
        this.fim = fim;
    }

    public Gerenciavel getGerenciavel() {
        return gerenciavel;
    }

    public void setGerenciavel(Gerenciavel gerenciavel) {
        this.gerenciavel = gerenciavel;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.diaAula);
        hash = 41 * hash + Objects.hashCode(this.inicio);
        hash = 41 * hash + Objects.hashCode(this.fim);
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
        final Horario other = (Horario) obj;
        if (!Objects.equals(this.diaAula, other.diaAula)) {
            return false;
        }
        if (!Objects.equals(this.inicio, other.inicio)) {
            return false;
        }
        if (!Objects.equals(this.fim, other.fim)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Horario{" + "id=" + id + ", diaAula=" + diaAula + ", inicio=" + inicio + ", fim=" + fim + '}';
    }

    @Override
    public int compareTo(Horario h) {
        int result = this.diaAula.getDiaDaSemana().compareTo(h.diaAula.getDiaDaSemana());
        // Verificar se o dia eh igual
        if (result == 0) {
            result = this.inicio.compareTo(h.inicio);
            // Verificar se a hora inicial eh igual
            if (result == 0) {
                result = this.fim.compareTo(h.fim);
            }
        }
        return result;
    }

    /**
     * Verificar se o horario esta entre o intervalo de horas
     *
     * @param horario
     * @return
     */
    public boolean between(Horario horario) {
        return ((this.inicio.equals(horario.inicio)
                || (this.inicio.isAfter(horario.inicio) && this.inicio.isBefore(horario.fim))
                || (this.fim.isAfter(horario.inicio) && this.fim.isBefore(horario.fim))) 
                && this.diaAula.equals(horario.diaAula));
    }

}
