package br.com.inovatec.grid.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
@NamedQueries(value = {
    @NamedQuery(name = "grade.findAll", query = "SELECT g FROM Grade g")
})
public class Grade implements Entidade<Long, Grade>, Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @SequenceGenerator(name = "GRADE_ID", initialValue = 1, allocationSize = 1, sequenceName = "GRADE_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GRADE_ID")
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Aula> aulas;

    public Grade() {
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public List<Aula> getAulas() {
        return aulas;
    }

    public void setAulas(List<Aula> aulas) {
        this.aulas = aulas;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Grade other = (Grade) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Grade{" + "id=" + id + ", aulas=" + aulas.size() + '}';
    }
    
    @Override
    public int compareTo(Grade o) {
        return this.id.compareTo(o.id);
    }

}
