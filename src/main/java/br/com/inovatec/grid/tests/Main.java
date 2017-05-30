/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.tests;

import br.com.inovatec.grid.entity.Aula;
import br.com.inovatec.grid.entity.DisciplinaTurma;
import br.com.inovatec.grid.entity.Escola;
import br.com.inovatec.grid.entity.Professor;
import br.com.inovatec.grid.entity.Usuario;
import br.com.inovatec.grid.service.AulaService;
import br.com.inovatec.grid.service.DiaAulaService;
import br.com.inovatec.grid.service.DisciplinaService;
import br.com.inovatec.grid.service.DisciplinaTurmaService;
import br.com.inovatec.grid.service.EscolaService;
import br.com.inovatec.grid.service.ProfessorService;
import br.com.inovatec.grid.service.SalaService;
import br.com.inovatec.grid.service.TurmaService;
import br.com.inovatec.grid.service.UsuarioService;
import br.com.inovatec.grid.service.exception.ServiceException;
import br.com.inovatec.grid.service.impl.AulaServiceImpl;
import br.com.inovatec.grid.service.impl.DiaAulaServiceImpl;
import br.com.inovatec.grid.service.impl.DisciplinaServiceImpl;
import br.com.inovatec.grid.service.impl.DisciplinaTurmaServiceImpl;
import br.com.inovatec.grid.service.impl.EscolaServiceImpl;
import br.com.inovatec.grid.service.impl.ProfessorServiceImpl;
import br.com.inovatec.grid.service.impl.SalaServiceImpl;
import br.com.inovatec.grid.service.impl.TurmaServiceImpl;
import br.com.inovatec.grid.service.impl.UsuarioServiceImpl;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zrobe
 */
public class Main {

    private static final int SEMANAS_LETIVAS = 41;
    private static final int[] CARGAS_HORARIAS = {40, 80, 120, 160, 200, 240, 280};
    private static final int TOTAL_DISCIPLINAS = 12;
    private static final int TOTAL_SALAS = 15;

    public static void main(String[] args) {

        // Executar rotinas
        execute();

    }

    public static void execute() {

        try {

            // Services da Aplicação
            UsuarioService usuarioService = new UsuarioServiceImpl();
            EscolaService escolaService = new EscolaServiceImpl();
            DiaAulaService diaAulaService = new DiaAulaServiceImpl();
            ProfessorService professorService = new ProfessorServiceImpl();
            DisciplinaService disciplinaService = new DisciplinaServiceImpl();
            SalaService salaService = new SalaServiceImpl();
            TurmaService turmaService = new TurmaServiceImpl();
            DisciplinaTurmaService disciplinaTurmaService = new DisciplinaTurmaServiceImpl();
            AulaService aulaService = new AulaServiceImpl();

            // Usuario Administrador do Sistema
            Usuario usuario = new Usuario();
            usuario.setLogin("admin");
            usuario.setSenha("123");
            usuario.setNome("Administrador");

            // Salvar Usuário
            usuarioService.save(usuario);

            // Informações da Escola
            Escola escola = new Escola();
            escola.setNome("Aurélio Buarque");
            escola.setPeriodoCorrente(2017);
            escola.setDuracaoAula(45);
            escola.setSemanasLetivas(SEMANAS_LETIVAS);
            escola.setInicioAula(LocalTime.of(07, 00));
            escolaService.save(escola);
            // Dias de aula da Escola
//            List<DiaAula> diasAula = new ArrayList<>();
//            diasAula.add(new DiaAula(DayOfWeek.MONDAY, 5, escola.getPeriodoCorrente()));
//            diasAula.add(new DiaAula(DayOfWeek.TUESDAY, 4, escola.getPeriodoCorrente()));
//            diasAula.add(new DiaAula(DayOfWeek.WEDNESDAY, 5, escola.getPeriodoCorrente()));
//            diasAula.add(new DiaAula(DayOfWeek.THURSDAY, 4, escola.getPeriodoCorrente()));
//            diasAula.add(new DiaAula(DayOfWeek.FRIDAY, 5, escola.getPeriodoCorrente()));
//            for (DiaAula diaAula : diasAula) {
//                diaAulaService.save(diaAula);
//            }
            
            // Salvar Escola

//            // Disciplinas
//            for (int i = 0; i < TOTAL_DISCIPLINAS; i++) {
//                Disciplina disciplina = new Disciplina();
//                disciplina.setNome("Disciplina " + i);
//                disciplina.setDescricao("Descricao da Disciplina " + i);
//                disciplinaService.save(disciplina);
//            }
//
//            // Salas de Aula
//            for (int i = 0; i < TOTAL_SALAS; i++) {
//                Sala sala = new Sala();
//                int rnd = new Random().nextInt(TipoSala.values().length);
//                sala.setTipoSala(TipoSala.values()[rnd]);
//                sala.setNome("Sala " + i);
//                sala.setDescricao("Descricao da Sala " + i);
//                sala.setStatus(StatusSala.DISPONIVEL);
//
//                // Horarios
//                LocalTime hour = LocalTime.of(7, 0);
//                sala.getHorarios().add(new Horario(
//                        DayOfWeek.MONDAY,
//                        hour,
//                        hour.plusHours(5)
//                ));
//                sala.getHorarios().add(new Horario(
//                        DayOfWeek.TUESDAY,
//                        hour,
//                        hour.plusHours(5)
//                ));
//                sala.getHorarios().add(new Horario(
//                        DayOfWeek.WEDNESDAY,
//                        hour,
//                        hour.plusHours(5)
//                ));
//                sala.getHorarios().add(new Horario(
//                        DayOfWeek.THURSDAY,
//                        hour,
//                        hour.plusHours(5)
//                ));
//                sala.getHorarios().add(new Horario(
//                        DayOfWeek.FRIDAY,
//                        hour,
//                        hour.plusHours(5)
//                ));
//
//                salaService.save(sala);
//            }
//
//            // Professores da Escola
//            for (int i = 0; i < 30; i++) {
//                Professor professor = new Professor();
//                professor.setNome("Professor " + i);
//                professor.setCpf("000000000" + i);
//                professor.setRg("0000" + i);
//                professor.setEmail("email" + i + "@email.com");
//                professor.getTelefones().add("8899999999" + i);
//                professor.getTelefones().add("8898888888" + i);
//
//                Endereco endereco = new Endereco();
//                endereco.setLogradouro("Rua " + i);
//                endereco.setNumero(Integer.toString(i));
//                endereco.setBairro("Bairro " + i);
//                endereco.setCidade("Icó");
//                endereco.setUf("CE");
//                endereco.setPais("Brasil");
//                professor.setEndereco(endereco);
//
//                // Horarios do Professor
//                for (int j = 0; j < 5; j++) {
//                    LocalTime hour = LocalTime.of(new Random().nextInt(5) + 7, new Random().nextInt(60));
//                    professor.getHorarios().add(new Horario(
//                            DayOfWeek.values()[new Random().nextInt(5) + 1],
//                            hour,
//                            hour.plusMinutes(90)
//                    ));
//                }
//
//                // Disciplinas que o professor ministra
//                for (int j = 0; j < 2; j++) {
//                    int rnd = new Random().nextInt(TOTAL_DISCIPLINAS);
//                    Disciplina disciplina = disciplinaService.find(rnd + 1l);
//                    // Verificar se a disciplina ja esta presente na lista de disciplinas do professor
//                    if (!professor.getDisciplinas().contains(disciplina)) {
//                        professor.getDisciplinas().add(disciplina);
//                    } else {
//                        j--;
//                    }
//                }
//
//                // Salvar professor
//                professorService.save(professor);
//
//            }
//
//            // Turmas
//            for (int i = 0; i < 10; i++) {
//                Turma turma = new Turma();
//                turma.setPeriodoCorrente(escolaService.get().getPeriodoCorrente());
//                turma.setAcronimo(Integer.toString(i));
//                turma.setAno(i);
//                turma.setPeriodoCorrente(escola.getPeriodoCorrente());
//                turma.setDescricao("Descricao da Turma " + i);
//
//                LocalTime hour = LocalTime.of(7, 0);
//                turma.getHorarios().add(new Horario(
//                        DayOfWeek.MONDAY,
//                        hour,
//                        hour.plusHours(5)
//                ));
//                turma.getHorarios().add(new Horario(
//                        DayOfWeek.TUESDAY,
//                        hour,
//                        hour.plusHours(4)
//                ));
//                turma.getHorarios().add(new Horario(
//                        DayOfWeek.WEDNESDAY,
//                        hour,
//                        hour.plusHours(5)
//                ));
//                turma.getHorarios().add(new Horario(
//                        DayOfWeek.THURSDAY,
//                        hour,
//                        hour.plusHours(4)
//                ));
//                turma.getHorarios().add(new Horario(
//                        DayOfWeek.FRIDAY,
//                        hour,
//                        hour.plusHours(5)
//                ));
//                turmaService.save(turma);
//
//                // Disciplinas da Turma
//                for (int j = 0; j < 12; j++) {
//                    DisciplinaTurma disciplinaTurma = new DisciplinaTurma();
//                    disciplinaTurma.setDisciplina(disciplinaService.find(j + 1l));
//                    disciplinaTurma.setTurma(turma);
//                    // Cargas horarias das disciplinas
//                    disciplinaTurma.setCargaHoraria(CARGAS_HORARIAS[new Random().nextInt(CARGAS_HORARIAS.length)]);
//                    disciplinaTurma.setCargaHorariaSemanal(disciplinaTurma.getCargaHoraria() / SEMANAS_LETIVAS);
//                    disciplinaTurmaService.save(disciplinaTurma);
//                }
//
//                // Lista das Salas que ja foram alocadas            
//                List<Long> salasIds = new ArrayList<>();
//                // Ficar no loop ate encontrar uma sala disponivel
//                while (true) {
//                    Sala sala = salaService.find(new Random().nextInt(TOTAL_SALAS) + 0l);
//                    // Verificar se a sala ainda nao foi usada por outra turma
//                    if (!salasIds.contains(sala.getId())) {
//                        salasIds.add(sala.getId());
//                        // Atualizar o status da Sala
//                        sala.setStatus(StatusSala.INDISPONIVEL);
//                        // Atualizar os horarios da Sala para os horarios da Turma
//                        sala.setHorarios(turma.getHorarios());
//                        // Atualizar Sala
//                        salaService.update(sala);
//                        // Sair do loop
//                        break;
//                    }
//                }
//
//            }

            // Aulas
        } catch (ServiceException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Gerar aulas
     *
     * @param aulaService
     * @throws ServiceException
     */
    private static void gerarAulas(Escola escola, AulaService aulaService) throws ServiceException {

        List<DisciplinaTurma> disciplinasTurmas = new DisciplinaTurmaServiceImpl().findAll();

        // Iterar pelas Turmas e suas Disciplinas
        for (DisciplinaTurma dt : disciplinasTurmas) {
            // Buscar os Professores que ministram as das Turmas
            List<Professor> professores = new ProfessorServiceImpl().findByDisciplina(dt.getDisciplina());
            // Iterar pelo total de aulas da disciplina
            for (int i = 0; i < dt.getCargaHorariaSemanal() / escola.getDuracaoAula(); i++) {
                Aula aula = new Aula();
                aula.setDisciplinaTurma(dt);
                aula.setProfessor(professores.get(new Random().nextInt(professores.size())));
                aula.setSala(dt.getTurma().getSala());
                // Salvar a aula com o déficite do Horário
                aulaService.save(aula);
            }
        }

    }

}
