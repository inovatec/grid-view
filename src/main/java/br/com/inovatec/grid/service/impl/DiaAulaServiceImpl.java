/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.service.impl;

import br.com.inovatec.grid.dao.DiaAulaDAO;
import br.com.inovatec.grid.dao.exceptions.ListEntityException;
import br.com.inovatec.grid.dao.exceptions.SearchEntityException;
import br.com.inovatec.grid.entity.DiaAula;
import br.com.inovatec.grid.provider.ServiceProvider;
import br.com.inovatec.grid.service.DiaAulaService;
import br.com.inovatec.grid.service.exception.ServiceException;
import br.com.inovatec.grid.view.session.Session;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zrobe
 */
public class DiaAulaServiceImpl extends AbstractService<DiaAula, DiaAulaDAO> implements DiaAulaService {

    private final DiaAulaDAO diaAulaDAO;

    public DiaAulaServiceImpl() {
        this.diaAulaDAO = new DiaAulaDAO();
    }
    
    @Override
    public DiaAulaDAO getGenericDAO() {
        return this.diaAulaDAO;
    }

    @Override
    public void remove(DiaAula obj) throws ServiceException {
        if (!ServiceProvider.getInstance().getHorarioService().havingHorario(obj)) {
            super.remove(obj);
        } else {
            throw new ServiceException("O dia de '" + obj.getLabel() + "' possui horários referenciando-o.");
        }
    }    

    @Override
    public List<DiaAula> listBy(Integer periodoCorrente) throws ServiceException {
        try {
            return this.diaAulaDAO.findAllBy(periodoCorrente);
        } catch (ListEntityException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public List<DiaAula> listByPeriodoCorrente() throws ServiceException {
        //TODO: return listBy(Session.getInstance().getEscola().getPeriodoCorrente());
        return listBy(2017);
    }

    @Override
    public Integer getMaxQuantidadeAulas() throws ServiceException {
        try {
            //TODO: return this.diaAulaDAO.getMaxQuantidadeAulas(Session.getInstance().getEscola().getPeriodoCorrente());
            return this.diaAulaDAO.getMaxQuantidadeAulas(2017);
        } catch (SearchEntityException ex) {
            throw new ServiceException(ex);
        }
    }
    
}
