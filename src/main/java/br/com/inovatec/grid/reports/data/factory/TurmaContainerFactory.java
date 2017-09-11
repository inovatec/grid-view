/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.reports.data.factory;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.inovatec.grid.entity.Aula;
import br.com.inovatec.grid.entity.Disciplina;
import br.com.inovatec.grid.entity.Horario;
import br.com.inovatec.grid.entity.Professor;
import br.com.inovatec.grid.entity.Sala;
import br.com.inovatec.grid.entity.Turma;
import br.com.inovatec.grid.provider.ServiceProvider;
import br.com.inovatec.grid.reports.data.object.AulasRow;
import br.com.inovatec.grid.reports.data.object.TurmaContainer;
import br.com.inovatec.grid.service.exception.ServiceException;
import br.com.inovatec.grid.view.session.Session;
import java.time.DayOfWeek;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author zrobe
 */
public class TurmaContainerFactory {

    public static List<TurmaContainer> getTurmasContainersByPeriodoCorrente() {
        return getTurmasContainers(Session.getInstance().getEscola().getPeriodoCorrente());
    }

    public static List<TurmaContainer> getTurmasContainers(Integer periodo) {
        // Lista a ser retornada contendo as aulas
        List<TurmaContainer> turmasContainers = new ArrayList<>();
        try {
            // Buscar as turmas do periodo corrente
            ServiceProvider.getInstance().getTurmaService().findAllByPeriodoCorrente().forEach(t -> {
                TurmaContainer turmaContainer = new TurmaContainer();
                turmaContainer.setTurma(t);

                filteredByInicio(t.getAulas()).forEach(h -> {
                    // Obter a aula dos dias correspondentes
                    // Adicionar nova linha de aula
                    turmaContainer.getAulasRows().add(
                            new AulasRow(
                                    h, 
                                    findAulaBy(t.getAulas(), h, DayOfWeek.MONDAY), 
                                    findAulaBy(t.getAulas(), h, DayOfWeek.TUESDAY), 
                                    findAulaBy(t.getAulas(), h, DayOfWeek.WEDNESDAY), 
                                    findAulaBy(t.getAulas(), h, DayOfWeek.THURSDAY), 
                                    findAulaBy(t.getAulas(), h, DayOfWeek.FRIDAY)
                            )
                    );
                });

                turmasContainers.add(turmaContainer);
            });
        } catch (ServiceException ex) {
            Logger.getLogger(TurmaContainerFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return turmasContainers;
    }

    /*
     * Metodo que auxilia na filtragem por horario
     */
    private static List<Horario> filteredByInicio(List<Aula> aulas) {
        List<Horario> horarios = new ArrayList<>();
        for (Aula aula : aulas) {
            // Condicao para inserir o horario na lista
            boolean condition = true;
            // Iterar pelos horarios para detectar se o mesmo ja foi inserido
            for (Horario h : horarios) {
                if (aula.getHorario().getInicio().equals(h.getInicio())) {
                    condition = false;
                    break;
                }
            }
            if (condition) {
                horarios.add(aula.getHorario());
            }
        }
        // Ordenar pelo inicio da aula
        Collections.sort(horarios, (Horario o1, Horario o2) -> {
            if (o1.getInicio().isBefore(o2.getInicio())) {
                return -1;
            } else {
                return 1;
            }
        });
        return horarios;
    }

    /**
     * Retornar a aula pelo horario de inicio e pelo dia
     *
     * @param horario
     * @param dayOfWeek
     * @return
     */
    private static Aula findAulaBy(List<Aula> aulas, Horario horario, DayOfWeek dayOfWeek) {
        for (Aula aula : aulas) {
            if (aula.getHorario().getInicio().equals(horario.getInicio())
                    && aula.getHorario().getDiaAula().getDiaDaSemana().equals(dayOfWeek)) {
                return aula;
            }
        }
        return null;
    }

    /**
     * Metodo com propositos de teste
     *
     * @return
     */
    public static List<TurmaContainer> load() {
        List<TurmaContainer> list = new ArrayList<>();
        // Turmas
        for (int i = 0; i < 3; i++) {
            Turma turma = new Turma();
            turma.setAno(i + 1);
            turma.setAcronimo("A");

            List<AulasRow> aulas = new ArrayList<>();

            // Horarios da turma (cada iteracao representa uma linha na tabela)
            for (int j = 0; j < 5; j++) {

                Horario h = new Horario();
                h.setInicio(LocalTime.of(i, j));

                // Aulas para este horario
                Aula[] aulasArray = new Aula[5];

                // Aulas na semana (cada iteracao representa um aula num dia da semana 
                // Naquele horario
                for (int k = 0; k < 5; k++) {
                    Aula a = new Aula();

                    a.setTurma(turma);
                    a.setHorario(h);

                    Professor p = new Professor();
                    p.setNome("Prof " + (k + 1));

                    Disciplina d = new Disciplina();
                    d.setNome("Disciplina " + (k + 1));

                    Sala s = new Sala();
                    s.setNome("Sala " + (k + 1));

                    a.setProfessor(p);
                    a.setDisciplina(d);
                    a.setSala(s);

                    aulasArray[k] = a;
                }

                AulasRow ar = new AulasRow(h, aulasArray[0], aulasArray[1], aulasArray[2], aulasArray[3], aulasArray[4]);
                aulas.add(ar);
            }

            list.add(new TurmaContainer(turma, aulas));
        }
        return list;
    }

}
