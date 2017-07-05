/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.provider;

import br.com.inovatec.grid.service.AulaService;
import br.com.inovatec.grid.service.DiaAulaService;
import br.com.inovatec.grid.service.DisciplinaService;
import br.com.inovatec.grid.service.DisciplinaTurmaService;
import br.com.inovatec.grid.service.EscolaService;
import br.com.inovatec.grid.service.HorarioService;
import br.com.inovatec.grid.service.ProfessorService;
import br.com.inovatec.grid.service.SalaService;
import br.com.inovatec.grid.service.TurmaService;
import br.com.inovatec.grid.service.UsuarioService;
import br.com.inovatec.grid.service.impl.AulaServiceImpl;
import br.com.inovatec.grid.service.impl.DiaAulaServiceImpl;
import br.com.inovatec.grid.service.impl.DisciplinaServiceImpl;
import br.com.inovatec.grid.service.impl.DisciplinaTurmaServiceImpl;
import br.com.inovatec.grid.service.impl.EscolaServiceImpl;
import br.com.inovatec.grid.service.impl.HorarioServiceImpl;
import br.com.inovatec.grid.service.impl.ProfessorServiceImpl;
import br.com.inovatec.grid.service.impl.SalaServiceImpl;
import br.com.inovatec.grid.service.impl.TurmaServiceImpl;
import br.com.inovatec.grid.service.impl.UsuarioServiceImpl;

/**
 *
 * @author zrobe
 */
public class ServiceProvider {
    
    private static ServiceProvider instance;
    
    private final HorarioService horarioService;
    private final EscolaService escolaService;
    private final DiaAulaService diaAulaService;
    private final UsuarioService usuarioService;
    private final DisciplinaService disciplinaService;
    private final SalaService salaService;
    private final TurmaService turmaService;
    private final DisciplinaTurmaService disciplinaTurmaService;
    private final ProfessorService professorService;
    private final AulaService aulaService;

    private ServiceProvider() {
        this.horarioService = new HorarioServiceImpl();
        this.escolaService = new EscolaServiceImpl();
        this.diaAulaService = new DiaAulaServiceImpl();
        this.usuarioService = new UsuarioServiceImpl();
        this.disciplinaService = new DisciplinaServiceImpl();
        this.salaService = new SalaServiceImpl();
        this.turmaService = new TurmaServiceImpl();
        this.disciplinaTurmaService = new DisciplinaTurmaServiceImpl();
        this.professorService = new ProfessorServiceImpl();
        this.aulaService = new AulaServiceImpl();
    }

    public static ServiceProvider getInstance() {
        if (instance == null) {
            instance = new ServiceProvider();
        }
        return instance;
    }

    public HorarioService getHorarioService() {
        return horarioService;
    }

    public EscolaService getEscolaService() {
        return escolaService;
    }

    public DiaAulaService getDiaAulaService() {
        return diaAulaService;
    }

    public UsuarioService getUsuarioService() {
        return usuarioService;
    }

    public DisciplinaService getDisciplinaService() {
        return disciplinaService;
    }

    public SalaService getSalaService() {
        return salaService;
    }

    public TurmaService getTurmaService() {
        return turmaService;
    }

    public DisciplinaTurmaService getDisciplinaTurmaService() {
        return disciplinaTurmaService;
    }

    public ProfessorService getProfessorService() {
        return professorService;
    }

    public AulaService getAulaService() {
        return aulaService;
    }
    
}
