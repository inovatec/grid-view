package br.com.inovatec.grid.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OrderBy;

@Entity
@DiscriminatorValue("P")
@NamedQueries(value = {
    @NamedQuery(name = "professor.findAll", query = "SELECT p FROM Professor p ORDER BY p.nome"),
    @NamedQuery(name = "professor.findByNome", query = "SELECT p FROM Professor p WHERE UPPER(p.nome) LIKE UPPER(:nome) ORDER BY p.nome"),
    @NamedQuery(name = "professor.findByDisciplina", query = "SELECT p FROM Professor p WHERE :disciplina MEMBER OF p.disciplinas")
})
public class Professor extends Gerenciavel implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Column(length = 100)
    private String nome;
    @Column(length = 50)
    private String rg;
    @Column(length = 11)
    private String cpf;
    @ElementCollection
    private List<String> telefones;
    @Column(length = 100)
    private String email;
    
    @Embedded
    private Endereco endereco;
    
    @ManyToMany
    @OrderBy("nome ASC")
    private List<Disciplina> disciplinas;

    public Professor() {
        super();
        this.endereco = new Endereco();
        this.telefones = new ArrayList<>();
        this.disciplinas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<String> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<String> telefones) {
        this.telefones = telefones;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    @Override
    public String toString() {
        return "Professor{" + super.toString() + ", nome=" + nome + ", rg=" + rg + ", cpf=" + cpf + ", telefones=" + telefones + ", email=" + email + ", endereco=" + endereco + '}';
    }
    
    public String getAnyTelefone() {
        if (this.telefones != null && !this.telefones.isEmpty()) {
            return this.telefones.get(0);
        }
        return null;
    }
    
    public String getTelefone(int index) {
        if (this.telefones != null && !this.telefones.isEmpty() && index < this.telefones.size()) {
            return this.telefones.get(index);
        }
        return null;
    }
    
    public void setTelefone(int index, String telefone) {
        if (this.telefones != null && !this.telefones.isEmpty() && index < this.telefones.size()) {
            this.telefones.add(index, telefone);
        } else {
            this.telefones.add(telefone);
        }
    }
    
    public String getTelefone() {
        return this.getTelefone(0);
    }
    
    public String getCelular() {
        return this.getTelefone(1);
    }
    
    public void setTelefone(String telefone) {
        this.setTelefone(0, telefone);
    }
    
    public void setCelular(String celular) {
        this.setTelefone(1, celular);
    }

    /**
     * Obter o nome reduzido do Professor
     * 
     * @return 
     */
    public String getShortNome() {
        String[] nomeSplitered = this.nome.trim().split(" ");
        if (nomeSplitered.length > 1) {
            return nomeSplitered[0] + " " + nomeSplitered[1];
        } else {
            return nomeSplitered[0];
        }
    }

}
