/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.dao;

import br.com.inovatec.grid.dao.exceptions.ListEntityException;
import br.com.inovatec.grid.dao.exceptions.SearchEntityException;
import br.com.inovatec.grid.entity.DiaAula;
import static br.com.inovatec.grid.entity.Horario_.diaAula;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;

/**
 *
 * @author zrobe
 */
public class DiaAulaDAO extends GenericDAO<DiaAula> {

    public DiaAulaDAO() {
        super(DiaAula.class);
    }

    @Override
    public List<DiaAula> findAll() throws ListEntityException {
        return this.list("diaAula.findAll", null);
    }    
    
    @Override
    public String getGenericQuery() {
        return "select da from DiaAula da where $1";
    }

    public List<DiaAula> findAllBy(Integer periodo) throws ListEntityException {
        Map<String, Object> params = new HashMap<>();
        params.put("periodo", periodo);
        return this.list("diaAula.findAllByPeriodo", params);
    }
            
    /**
     * Resgatar o maior numero de aulas de todos os dias da semana de determinado periodo
     * 
     * @param periodo
     * @return
     * @throws SearchEntityException 
     */
    public Integer getMaxQuantidadeAulas(Integer periodo) throws SearchEntityException {
        try {
            Query query = getEm().createNamedQuery("diaAula.maxAulas", Integer.class);
            query.setParameter("periodo", periodo);
            return (Integer) query.getSingleResult();
        } catch (Exception ex) {
            throw new SearchEntityException(ex);
        }
    }
    
}
