package br.com.inovatec.grid.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

/**
 * Classe que responsavel por indicar que a entidade filha é gerenciada pelo
 * módulo de geração da grade de horários. As classes que herdarem desta farão
 * parte da entidade Aula, a qual compõe a grade de horários presente na 
 * entidade Grade.
 * 
 * @author zrobe
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Gerenciavel implements Entidade<Long, Gerenciavel>, Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @SequenceGenerator(name = "GERENCIAVEL_ID", initialValue = 1, allocationSize = 1, sequenceName = "GERENCIAVEL_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GERENCIAVEL_ID")
    private Long id;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gerenciavel", fetch = FetchType.LAZY)
    private List<Horario> horarios;

    public Gerenciavel() {
        this.horarios = new ArrayList<>();
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + Objects.hashCode(this.getId());
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
        final Gerenciavel other = (Gerenciavel) obj;
        if (!Objects.equals(this.getId(), other.getId())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Gerenciavel{" + "id=" + this.getId() + '}';
    }
    
    @Override
    public int compareTo(Gerenciavel o) {
        return this.getId().compareTo(o.getId());
    }

}
