package br.com.inovatec.grid.entity;

import br.com.inovatec.grid.view.contract.Selectable;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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
    @NamedQuery(name = "disciplina.findAll", query = "SELECT d FROM Disciplina d ORDER BY d.nome"),
    @NamedQuery(name = "disciplina.findByTurma", query = "SELECT d FROM Disciplina d, DisciplinaTurma dt WHERE dt.disciplina = d AND dt.turma = :turma")
})
public class Disciplina implements Entidade<Long, Disciplina>, Serializable, Selectable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @SequenceGenerator(name = "DISCIPLINA_ID", initialValue = 1, allocationSize = 1, sequenceName = "DISCIPLINA_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DISCIPLINA_ID")
    private Long id;

    @Column(length = 100)
    private String nome;
    @Column(length = 255)
    private String descricao;
    
    @OneToMany(mappedBy = "disciplina", cascade = CascadeType.ALL)
    private List<DisciplinaTurma> disciplinaTurmas;

    public Disciplina() {
        super();
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public List<DisciplinaTurma> getDisciplinaTurmas() {
        return disciplinaTurmas;
    }

    public void setDisciplinaTurma(List<DisciplinaTurma> disciplinaTurmas) {
        this.disciplinaTurmas = disciplinaTurmas;
    }

    @Override
    public String toString() {
        return "Disciplina{" + super.toString() + ", nome=" + nome + ", descricao=" + descricao + '}';
    }
    
    @Override
    public int compareTo(Disciplina o) {
        return this.nome.compareTo(o.nome);
    }

    @Override
    public String getLabel() {
        return this.nome;
    }

}
