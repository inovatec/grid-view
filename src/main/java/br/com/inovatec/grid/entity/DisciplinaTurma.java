package br.com.inovatec.grid.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@DiscriminatorValue("DT")
@NamedQueries(value = {
    @NamedQuery(name = "disciplinaTurma.findAll", query = "SELECT dt FROM DisciplinaTurma dt"),
    @NamedQuery(name = "disciplinaTurma.findByTurma", query = "SELECT dt FROM DisciplinaTurma dt WHERE dt.turma = :turma ORDER BY dt.disciplina.nome")
})
public class DisciplinaTurma extends Gerenciavel implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @ManyToOne
    private Disciplina disciplina;
    
    @ManyToOne
    private Turma turma;
    
    private int cargaHoraria;
    private int cargaHorariaSemanal;
    
    public DisciplinaTurma() {
        super();
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

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public int getCargaHorariaSemanal() {
        return cargaHorariaSemanal;
    }

    public void setCargaHorariaSemanal(int cargaHorariaSemanal) {
        this.cargaHorariaSemanal = cargaHorariaSemanal;
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
        return "DisciplinaTurma{" + super.toString() + ", disciplina=" + disciplina + ", turma=" + turma + ", cargaHoraria=" + cargaHoraria + ", cargaHorariaSemanal=" + cargaHorariaSemanal + '}';
    }

    @Override
    public int compareTo(Gerenciavel o) {
        return this.disciplina.compareTo(((DisciplinaTurma) o).disciplina);
    }    
    
}
