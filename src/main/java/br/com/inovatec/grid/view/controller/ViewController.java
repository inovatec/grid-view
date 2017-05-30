/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.controller;

import br.com.inovatec.grid.entity.Disciplina;
import br.com.inovatec.grid.entity.DisciplinaTurma;
import br.com.inovatec.grid.entity.Horario;
import br.com.inovatec.grid.entity.Professor;
import br.com.inovatec.grid.entity.Sala;
import br.com.inovatec.grid.entity.Turma;
import br.com.inovatec.grid.entity.enums.TipoSala;
import br.com.inovatec.grid.view.layout.EscolaView;
import br.com.inovatec.grid.view.layout.HorariosView;
import br.com.inovatec.grid.view.layout.template.DefaultView;
import br.com.inovatec.grid.view.values.Strings;
import br.com.inovatec.grid.view.contract.DialogView;
import br.com.inovatec.grid.view.contract.FrameView;
import br.com.inovatec.grid.view.layout.CompetenciasView;
import br.com.inovatec.grid.view.layout.DiasAulaView;
import br.com.inovatec.grid.view.layout.DisciplinaView;
import br.com.inovatec.grid.view.layout.DisciplinasView;
import br.com.inovatec.grid.view.layout.HorarioEditView;
import br.com.inovatec.grid.view.layout.LoginView;
import br.com.inovatec.grid.view.layout.ProfessorView;
import br.com.inovatec.grid.view.layout.ProfessoresView;
import br.com.inovatec.grid.view.layout.SalaView;
import br.com.inovatec.grid.view.layout.SalasView;
import br.com.inovatec.grid.view.layout.TurmaDisciplinaEditView;
import br.com.inovatec.grid.view.layout.TurmaDisciplinasView;
import br.com.inovatec.grid.view.layout.TurmaView;
import br.com.inovatec.grid.view.layout.TurmasView;
import br.com.inovatec.grid.view.session.Session;
import br.com.inovatec.grid.view.util.DummyFrame;
import java.util.List;

/**
 *
 * @author zrobe
 */
public class ViewController {

    public static void showLoginDialog(DummyFrame dummyFrame) {
        showView(new LoginView(dummyFrame));
    }

    public static void showEscolaDialog(FrameView mainJframe, boolean forEdition) {
        showView(new EscolaView(mainJframe, forEdition));
    }

    public static void showDiasAulaDialog(DialogView requesterView) {
        showView(new DiasAulaView(requesterView));
    }

    public static void showDisciplinasView(FrameView mainJframe) {
        showView(new DisciplinasView(mainJframe));
    }

    public static void showDisciplinaView(DialogView requesterView, boolean forEdition, Disciplina disciplina) {
        showView(new DisciplinaView(requesterView, forEdition, disciplina));
    }

    /**
     * Exibir Tela das Turmas
     *
     * @param mainJframe
     */
    public static void showTurmasView(FrameView mainJframe) {
        showView(new TurmasView(mainJframe));
    }

    /**
     * Exibir Tela de Turma
     *
     * @param requesterView
     * @param forEdition
     * @param turma
     */
    public static void showTurmaView(DialogView requesterView, boolean forEdition, Turma turma) {
        showView(new TurmaView(requesterView, forEdition, turma));
    }

    /**
     * Exibir Tela das Disciplinas da Turma
     *
     * @param requesterView
     * @param turma
     */
    public static void showTurmaDisciplinasView(DialogView requesterView, Turma turma) {
        showView(new TurmaDisciplinasView(requesterView, turma));
    }

    /**
     * Exibir Tela de Edição da Disciplina da Turma
     * 
     * @param requesterView
     * @param disciplinaTurma
     * @param disciplinaTurmas
     * @param index 
     */
    public static void showTurmaDisciplinaEditView(
            TurmaDisciplinasView requesterView,
            DisciplinaTurma disciplinaTurma,
            List<DisciplinaTurma> disciplinaTurmas,
            int index) {
        showView(new TurmaDisciplinaEditView(requesterView, disciplinaTurma, disciplinaTurmas, index));
    }

    /**
     * Exibir Tela de Salas de Aula
     *
     * @param mainJframe
     */
    public static void showSalasView(FrameView mainJframe) {
        showView(new SalasView(mainJframe));
    }

    /**
     * Exibir Tela de Sala de Aula
     *
     * @param requesterView
     * @param forEdition
     * @param sala
     */
    public static void showSalaView(DialogView requesterView, boolean forEdition, Sala sala) {
        showView(new SalaView(requesterView, forEdition, sala));
    }

    /**
     * Exibir Tela de Professores
     *
     * @param mainJframe
     */
    public static void showProfessoresView(FrameView mainJframe) {
        showView(new ProfessoresView(mainJframe));
    }

    /**
     * Exibir Tela de Professores
     *
     * @param requesterView
     * @param forEdition
     * @param professor
     */
    public static void showProfessorView(DialogView requesterView, boolean forEdition, Professor professor) {
        showView(new ProfessorView(requesterView, forEdition, professor));
    }

    public static void showHorarioEditView(HorariosView requesterView, Horario horario, List<Horario> horarios, int index) {
        showView(new HorarioEditView(requesterView, horario, horarios, index));
    }

    public static void showHorariosEscolaView(DefaultView requesterView) {
        showView(new HorariosView(requesterView, Strings.getReplacedString(Strings.HORARIO_DIALOG_TITLE, "de Aula da Escola"), Strings.getReplacedString(Strings.HORARIO_DIALOG_TITLE, "de Aula da Escola"), Strings.ESCOLA_DIALOG_HORAS_AULA_TIP, Session.getInstance().getEscola(), true));
    }

    /**
     * Horarios da Sala de Aula
     *
     * @param requesterView
     * @param sala
     */
    public static void showHorariosSalaView(DefaultView requesterView, Sala sala) {
        String title = Strings.getReplacedString(Strings.HORARIO_DIALOG_TITLE, "da Sala de Aula");
        String headerTip = Strings.getReplacedString(
                sala.getTipoSala().equals(TipoSala.SALA) ? Strings.SALA_DIALOG_HORARIOS_TIPO_SALA_HEADER_TIP : Strings.SALA_DIALOG_HORARIOS_TIPO_LAB_HEADER_TIP,
                sala.getNome()
        );
        showView(new HorariosView(requesterView, title, title, headerTip, sala, true));
    }

    /**
     * Horarios da Turma
     *
     * @param requesterView
     * @param turma
     */
    public static void showHorariosTurmaView(DefaultView requesterView, Turma turma) {
        String title = Strings.getReplacedString(Strings.HORARIO_DIALOG_TITLE, "da Turma");
        String headerTip = Strings.getReplacedString(
                Strings.TURMA_DIALOG_HORARIOS_HEADER_TIP,
                turma.getNome()
        );
        showView(new HorariosView(requesterView, title, title, headerTip, turma, true));
    }

    /**
     * Horarios da Disciplina na Turma
     *
     * @param requesterView
     * @param disciplinaTurma
     */
    public static void showHorariosDisciplinaTurmaView(DefaultView requesterView, DisciplinaTurma disciplinaTurma) {
        String title = Strings.getReplacedString(Strings.HORARIO_DIALOG_TITLE, "da Disciplina da Turma");
        String headerTip = Strings.getReplacedString(
                Strings.TURMA_DISCIPLINAS_DIALOG_HORARIOS_HEADER_TIP,
                disciplinaTurma.getTurma().getNome(),
                disciplinaTurma.getDisciplina().getNome()
        );
        showView(new HorariosView(requesterView, title, title, headerTip, disciplinaTurma, true));
    }

    /**
     * Horarios do Professor
     *
     * @param requesterView
     * @param professor
     */
    public static void showHorariosProfessorView(DefaultView requesterView, Professor professor) {
        String title = Strings.getReplacedString(Strings.HORARIO_DIALOG_TITLE, "do Professor");
        String headerTip = Strings.getReplacedString(
                Strings.PROFESSOR_DIALOG_HORARIOS_HEADER_TIP,
                professor.getNome()
        );
        showView(new HorariosView(requesterView, title, title, headerTip, professor, true));
    }

    /**
     * Competencias do Professor
     *
     * @param requesterView
     */
    public static void showCompetenciasProfessorView(ProfessorView requesterView) {
        showView(new CompetenciasView(requesterView, requesterView.getProfessor()));
    }

    /**
     * Exibir Dialog
     *
     * @param dialogClass
     */
    private static <D extends DefaultView> void showView(D view) {
        view.display();
    }

}
