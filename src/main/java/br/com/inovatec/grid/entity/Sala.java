package br.com.inovatec.grid.entity;

import br.com.inovatec.grid.entity.enums.StatusSala;
import br.com.inovatec.grid.entity.enums.TipoSala;
import br.com.inovatec.grid.view.contract.Selectable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@DiscriminatorValue("S")
@NamedQueries(value = {
    @NamedQuery(name = "sala.findAll", query = "SELECT s FROM Sala s"),
    @NamedQuery(name = "sala.findAllByStatus", query = "SELECT s FROM Sala s WHERE s.status = :status")
})
public class Sala extends Gerenciavel implements Selectable {
    
    private static final long serialVersionUID = 1L;

    @Column(length = 100)
    private String nome;
    @Column(length = 255)
    private String descricao;
    
    @Enumerated(EnumType.STRING)
    private StatusSala status;
    
    @Enumerated(EnumType.STRING)
    private TipoSala tipoSala;

    public Sala() {
        super();
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

    public StatusSala getStatus() {
        return status;
    }

    public void setStatus(StatusSala status) {
        this.status = status;
    }

    public TipoSala getTipoSala() {
        return tipoSala;
    }

    public void setTipoSala(TipoSala tipoSala) {
        this.tipoSala = tipoSala;
    }

    @Override
    public String toString() {
        return "Sala{" + super.toString() + ", nome=" + nome + ", descricao=" + descricao + ", status=" + status + ", tipoSala=" + tipoSala + '}';
    }

    @Override
    public int compareTo(Gerenciavel o) {
        return this.nome.compareTo(((Sala) o).nome);
    }

    @Override
    public String getLabel() {
        return this.nome;
    }

}
