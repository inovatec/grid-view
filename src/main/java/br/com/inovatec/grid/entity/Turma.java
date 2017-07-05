package br.com.inovatec.grid.entity;

import br.com.inovatec.grid.view.contract.Selectable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("T")
@NamedQueries(value = {
    @NamedQuery(name = "turma.findAll", query = "SELECT t FROM Turma t ORDER BY t.ano"),
    @NamedQuery(name = "turma.findAllByPeriodo", query = "SELECT t FROM Turma t WHERE t.periodoCorrente = :periodoCorrente ORDER BY t.ano"),
    @NamedQuery(name = "turma.findAllByDescricao", query = "SELECT t FROM Turma t WHERE t.periodoCorrente = :periodoCorrente AND t.descricao like :descricao ORDER BY t.ano")
})
public class Turma extends Gerenciavel implements Selectable {
    
    private static final long serialVersionUID = 1L;
    
    @Column(length = 6)
    private Integer periodoCorrente;
    private int ano;
    @Column(length = 2)
    private String acronimo;
    @Column(length = 255)
    private String descricao;
    
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private Sala sala;
    
    @OneToMany(mappedBy = "turma", cascade = CascadeType.ALL)
    private List<DisciplinaTurma> disciplinaTurmas;
    
    public Turma() {
        super();
    }

    public Integer getPeriodoCorrente() {
        return periodoCorrente;
    }

    public void setPeriodoCorrente(Integer periodoCorrente) {
        this.periodoCorrente = periodoCorrente;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getAcronimo() {
        return acronimo;
    }

    public void setAcronimo(String acronimo) {
        this.acronimo = acronimo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public List<DisciplinaTurma> getDisciplinaTurmas() {
        return disciplinaTurmas;
    }

    public void setDisciplinaTurmas(List<DisciplinaTurma> disciplinaTurmas) {
        this.disciplinaTurmas = disciplinaTurmas;
    }

    @Override
    public String toString() {
        return "Turma{" + super.toString() + ", ano=" + ano + ", acronimo=" + acronimo + ", descricao=" + descricao + ", sala=" + sala + '}';
    }
    
    public String getNome() {
        return this.ano + "º ano " + this.acronimo.toUpperCase();
    }

    @Override
    public String getLabel() {
        return this.getNome();
    }
    
}
