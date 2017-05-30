/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.service.impl;

import br.com.inovatec.grid.dao.HorarioDAO;
import br.com.inovatec.grid.dao.exceptions.ListEntityException;
import br.com.inovatec.grid.dao.exceptions.SearchEntityException;
import br.com.inovatec.grid.entity.DiaAula;
import br.com.inovatec.grid.entity.Gerenciavel;
import br.com.inovatec.grid.entity.Horario;
import br.com.inovatec.grid.service.HorarioService;
import br.com.inovatec.grid.service.exception.ServiceException;
import br.com.inovatec.grid.view.session.Session;
import java.util.List;

/**
 *
 * @author zrobe
 */
public class HorarioServiceImpl extends AbstractService<Horario, HorarioDAO> implements HorarioService {

    private final HorarioDAO horarioDAO;

    public HorarioServiceImpl() {
        this.horarioDAO = new HorarioDAO();
    }

    @Override
    public HorarioDAO getGenericDAO() {
        return this.horarioDAO;
    }

    @Override
    public List<Horario> findBy(Gerenciavel gerenciavel, Integer periodo) throws ServiceException {
        try {
            return this.horarioDAO.findBy(gerenciavel, periodo);
        } catch (ListEntityException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public List<Horario> getOfPeriodoCorrente(Gerenciavel gerenciavel) throws ServiceException {
        return this.findBy(gerenciavel, Session.getInstance().getEscola().getPeriodoCorrente());
    }

    /**
     * Verificar se existem horarios com o dia de aula informado
     * 
     * @param diaAula
     * @return
     * @throws ServiceException 
     */
    @Override
    public Boolean havingHorario(DiaAula diaAula) throws ServiceException {
        try {
            return this.horarioDAO.countHorarios(diaAula) > 0;
        } catch (SearchEntityException ex) {
            throw new ServiceException(ex);
        }
    }

}
