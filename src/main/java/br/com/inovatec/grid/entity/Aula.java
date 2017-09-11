package br.com.inovatec.grid.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

@Entity
@NamedQueries(value = {
    @NamedQuery(name = "aula.findAll", query = "SELECT a FROM Aula a"),
    @NamedQuery(name = "aula.findAllByProfessor", query = "SELECT a FROM Aula a WHERE a.professor = :professor"),
    @NamedQuery(name = "aula.findAllByTurmaAndDiaAula", query = "SELECT a FROM Aula a WHERE a.turma = :turma AND a.horario.diaAula = :diaAula"),
    @NamedQuery(name = "aula.findAllByTurmaAndPeriodo", query = "SELECT a FROM Aula a WHERE a.turma = :turma AND a.horario.diaAula.periodo = :periodo ORDER BY a.horario.diaAula, a.horario.inicio"),
    @NamedQuery(name = "aula.findAllByPeriodo", query = "SELECT a FROM Aula a WHERE a.horario.diaAula.periodo = :periodo ORDER BY a.horario.diaAula, a.horario.inicio, a.turma.ano, a.turma.acronimo, a.disciplina.nome")
})
public class Aula implements Entidade<Long, Aula>, Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @SequenceGenerator(name = "AULA_ID", initialValue = 1, allocationSize = 1, sequenceName = "AULA_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AULA_ID")
    private Long id;
    
    @ManyToOne
    private Professor professor;
    
    @ManyToOne
    private Sala sala;
    
    @ManyToOne
    private Turma turma;
    
    @ManyToOne
    private Disciplina disciplina;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Horario horario;

    public Aula() {
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.id);
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
        final Aula other = (Aula) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Aula{" + "id=" + id + ", professor=" + professor + ", sala=" + sala + ", turma=" + turma + ", disciplina=" + disciplina + ", horario=" + horario + '}';
    }
    
    @Override
    public int compareTo(Aula o) {
        return this.id.compareTo(o.id);
    }

}
