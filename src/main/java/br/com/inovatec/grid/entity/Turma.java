package br.com.inovatec.grid.entity;

import br.com.inovatec.grid.view.contract.Selectable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
    @Column(length = 30)
    private String acronimo;
    @Column(length = 255)
    private String descricao;
    
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private Sala sala;
    
    @OneToMany(mappedBy = "turma", cascade = CascadeType.ALL)
    private List<DisciplinaTurma> disciplinasTurma;
    
    @OneToMany(mappedBy = "turma", fetch = FetchType.LAZY)
    private List<Aula> aulas;
    
    public Turma() {
        super();
        this.disciplinasTurma = new ArrayList<>();
        this.aulas = new ArrayList<>();
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

    public List<DisciplinaTurma> getDisciplinasTurma() {
        return disciplinasTurma;
    }

    public void setDisciplinasTurma(List<DisciplinaTurma> disciplinasTurma) {
        this.disciplinasTurma = disciplinasTurma;
    }

    public List<Aula> getAulas() {
        return aulas;
    }

    public void setAulas(List<Aula> aulas) {
        this.aulas = aulas;
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
    
    /**
     * Adicionar disciplinaTurma
     * 
     * @param disciplinaTurma 
     */
    public void addDisciplinaTurma(DisciplinaTurma disciplinaTurma) {
        this.disciplinasTurma.add(disciplinaTurma);
        if (disciplinaTurma.getTurma() != this) {
            disciplinaTurma.setTurma(this);
        }
    }
    
    public void resetDisciplinasTurma() {
        this.disciplinasTurma = new ArrayList<>();
    }
    
}
