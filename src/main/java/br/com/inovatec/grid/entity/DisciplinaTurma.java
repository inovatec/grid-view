package br.com.inovatec.grid.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("DT")
@NamedQueries(value = {
    @NamedQuery(name = "disciplinaTurma.findAll", query = "SELECT dt FROM DisciplinaTurma dt"),
    @NamedQuery(name = "disciplinaTurma.findByTurma", query = "SELECT dt FROM DisciplinaTurma dt WHERE dt.turma = :turma ORDER BY dt.disciplina.nome"),
    @NamedQuery(name = "disciplinaTurma.countInAulas", query = "SELECT COUNT(a) FROM DisciplinaTurma dt, Aula a WHERE a.disciplinaTurma = dt AND dt = :disciplinaTurma")
})
public class DisciplinaTurma extends Gerenciavel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    private Disciplina disciplina;

    @ManyToOne
    private Turma turma;

    private Integer cargaHorariaTotal;
    private Integer aulasSemanaTotal;
    
    @OneToMany(mappedBy = "disciplinaTurma", fetch = FetchType.LAZY)
    private List<Aula> aulas;

    public DisciplinaTurma() {
        super();
        this.aulas = new ArrayList<>();
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public Integer getCargaHorariaTotal() {
        return cargaHorariaTotal;
    }

    public void setCargaHorariaTotal(Integer cargaHorariaTotal) {
        this.cargaHorariaTotal = cargaHorariaTotal;
    }

    public Integer getAulasSemanaTotal() {
        return aulasSemanaTotal;
    }

    public void setAulasSemanaTotal(Integer aulasSemanaTotal) {
        this.aulasSemanaTotal = aulasSemanaTotal;
    }

    public List<Aula> getAulas() {
        return aulas;
    }

    public void setAulas(List<Aula> aulas) {
        this.aulas = aulas;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.disciplina);
        hash = 29 * hash + Objects.hashCode(this.turma);
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
        final DisciplinaTurma other = (DisciplinaTurma) obj;
        if (!Objects.equals(this.disciplina, other.disciplina)) {
            return false;
        }
        if (!Objects.equals(this.turma, other.turma)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DisciplinaTurma{" + "disciplina=" + disciplina + ", turma=" + turma + ", cargaHoraria=" + cargaHorariaTotal + ", totalAulasSemana=" + aulasSemanaTotal + '}';
    }

    @Override
    public int compareTo(Gerenciavel o) {
        return this.disciplina.compareTo(((DisciplinaTurma) o).disciplina);
    }

}
