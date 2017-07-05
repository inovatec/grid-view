/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.dao;

import br.com.inovatec.grid.dao.exceptions.ListEntityException;
import br.com.inovatec.grid.dao.exceptions.SearchEntityException;
import br.com.inovatec.grid.entity.DisciplinaTurma;
import br.com.inovatec.grid.entity.Turma;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author zrobe
 */
public class DisciplinaTurmaDAO extends GenericDAO<DisciplinaTurma> {

    public DisciplinaTurmaDAO() {
        super(DisciplinaTurma.class);
    }

    @Override
    public List<DisciplinaTurma> findAll() throws ListEntityException {
        return this.list("disciplinaTurma.findAll", null);
    }
    
    public List<DisciplinaTurma> findByTurma(Turma turma) throws ListEntityException {
        // Limpar o cache
        this.getEm().clear();
        // Query
        Query query = this.getEm().createNamedQuery("disciplinaTurma.findByTurma", DisciplinaTurma.class);
        query.setParameter("turma", turma);
        return query.getResultList();
    }
    
    /**
     * Contar a existencia pelo dia de aula informado
     * 
     * @param disciplinaTurma
     * @return
     * @throws SearchEntityException 
     */
    public Long countInAulas(DisciplinaTurma disciplinaTurma) throws SearchEntityException {
        try {
            Query query = getEm().createNamedQuery("disciplinaTurma.countInAulas", Long.class);
            query.setParameter("disciplinaTurma", disciplinaTurma);
            return (Long) query.getSingleResult();
        } catch (Exception ex) {
            throw new SearchEntityException(ex);
        }
    }
    
}
