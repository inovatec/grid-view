/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.dao;

import br.com.inovatec.grid.dao.exceptions.ListEntityException;
import br.com.inovatec.grid.dao.exceptions.SearchEntityException;
import br.com.inovatec.grid.entity.DiaAula;
import br.com.inovatec.grid.entity.Gerenciavel;
import br.com.inovatec.grid.entity.Horario;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author zrobe
 */
public class HorarioDAO extends GenericDAO<Horario> {

    public HorarioDAO() {
        super(Horario.class);
    }

    @Override
    public List<Horario> findAll() throws ListEntityException {
        return this.list("horario.findAll", null);
    }
    
    public List<Horario> findBy(Gerenciavel gerenciavel, Integer periodo) throws ListEntityException {
        // Limpar o cache
        this.getEm().clear();
        // Query
        Query query = this.getEm().createNamedQuery("horario.findByGerenciavelAndPeriodo", Horario.class);
        query.setParameter("gerenciavel", gerenciavel);
        query.setParameter("periodo", periodo);
        return query.getResultList();
    }
    
    /**
     * Contar horarios pelo dia de aula informado
     * 
     * @param diaAula
     * @return
     * @throws SearchEntityException 
     */
    public Long countHorarios(DiaAula diaAula) throws SearchEntityException {
        try {
            Query query = getEm().createNamedQuery("horario.countByDiaAula", Long.class);
            query.setParameter("diaAula", diaAula);
            return (Long) query.getSingleResult();
        } catch (Exception ex) {
            throw new SearchEntityException(ex);
        }
    }
    
}
