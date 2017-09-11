/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.dao;

import br.com.inovatec.grid.dao.exceptions.ListEntityException;
import br.com.inovatec.grid.entity.Disciplina;
import br.com.inovatec.grid.entity.Turma;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author zrobe
 */
public class DisciplinaDAO extends GenericDAO<Disciplina> {

    public DisciplinaDAO() {
        super(Disciplina.class);
    }

    @Override
    public List<Disciplina> findAll() throws ListEntityException {
        return this.list("disciplina.findAll", null);
    }
    
    @Override
    public String getGenericQuery() {
        return "select d from Disciplina d where $1";
    }

    /**
     * Buscar disciplinas de uma Turma
     *
     * @param turma
     * @return
     * @throws ListEntityException
     */
    public List<Disciplina> findBy(Turma turma) throws ListEntityException {
        Query query = this.getEm().createNamedQuery("disciplina.findByTurma", Disciplina.class);
        query.setParameter("turma", turma);
        return query.getResultList();
    }

}
