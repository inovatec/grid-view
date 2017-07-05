/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view;

import br.com.inovatec.grid.entity.DiaAula;
import br.com.inovatec.grid.entity.DisciplinaTurma;
import br.com.inovatec.grid.entity.Horario;
import br.com.inovatec.grid.entity.Professor;
import br.com.inovatec.grid.entity.Turma;
import br.com.inovatec.grid.provider.ServiceProvider;
import br.com.inovatec.grid.service.exception.ServiceException;
import br.com.inovatec.grid.view.component.exception.NoItemSelectedException;
import br.com.inovatec.grid.view.component.factory.ButtonFactory;
import br.com.inovatec.grid.view.component.factory.SelectOneMenuFactory;
import br.com.inovatec.grid.view.component.form.SelectOneMenu;
import br.com.inovatec.grid.view.component.main.AulaJPanel;
import br.com.inovatec.grid.view.component.main.DiaAulaJPanel;
import br.com.inovatec.grid.view.component.main.DisciplinaJLabel;
import br.com.inovatec.grid.view.component.form.MultilineLabel;
import br.com.inovatec.grid.view.component.listener.ButtonActionListener;
import br.com.inovatec.grid.view.contract.FrameView;
import br.com.inovatec.grid.view.session.Session;
import br.com.inovatec.grid.view.values.Colors;
import br.com.inovatec.grid.view.values.Cursors;
import br.com.inovatec.grid.view.values.Dimens;
import br.com.inovatec.grid.view.values.Icons;
import br.com.inovatec.grid.view.values.Strings;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.JFrame;

/**
 *
 * @author zrobe
 */
public class GradeJFrame extends javax.swing.JFrame implements FrameView {

    private static final int MIN_DISCIPLINAS_SIZE = 12;
    private static final int MIN_DIAS_AULA_SIZE = 5;

    private final MainJFrame mainJFrame;
    
    private AulaJPanel currentAulaJPanel;
    private DisciplinaJLabel pressedDisciplinaJLabel;
    private Cursor prevCursor;
    private Integer totalAulas;
    private List<DiaAula> diasAula;
    private List<Horario> horarios;
    private List<Professor> todosProfessores;
    private SelectOneMenu<Turma> turmasSelectOneMenu;

    /**
     * Creates new form GradeJFrame
     * @param mainJFrame
     */
    public GradeJFrame(MainJFrame mainJFrame) {
        this.mainJFrame = mainJFrame;
        initComponents();
        // Icones da Aplicacao
        this.setIconImages(Icons.getAppIcons());
        // Inicilaizar
        this.init();
    }

    /**
     * Inicializar componentes e variaveis
     */
    private void init() {
        
        addWindowListener(new DefaultWindowAdapter());
        
        this.actionsJPanel.add(ButtonFactory.getInstance().getCancelButton(Colors.COLOR_MAIN, new ButtonActionListener() {
            @Override
            public void action() {
                close();
            }
        }));
        this.actionsJPanel.add(ButtonFactory.getInstance().getSaveButton(Colors.COLOR_MAIN, new ButtonActionListener() {
            @Override
            public void action() {
                System.out.println("Salvar");
            }
        }));        
        
        try {
            // Lista de Turmas do Periodo
            List<Turma> turmas = ServiceProvider.getInstance().getTurmaService().findAllByPeriodoCorrente();
            // SelectOneMenu de Turmas
            this.turmasSelectOneMenu = SelectOneMenuFactory.getInstance().getSelectOneMenu(
                    Dimens.TURMAS_LEFT_PANEL_WIDTH, 1, Dimens.WEIGHT_100, turmas
            );
            // Adicionar Listener ao select de turmas
            this.turmasSelectOneMenu.addSelectActionListener((ActionEvent e) -> {
                try {
                    showTurma(turmasSelectOneMenu.getSelectedItem());
                } catch (NoItemSelectedException ex) {
                    reset();
                }
            });
            // Adicionar select de turmas
            this.turmasSelectOneMenuContainerJPanel.add(this.turmasSelectOneMenu, java.awt.BorderLayout.CENTER);
            // Numero maximo de aulas na semana
            this.totalAulas = ServiceProvider.getInstance().getDiaAulaService().getMaxQuantidadeAulas();
            // Horarios da escola
            this.horarios = ServiceProvider.getInstance().getHorarioService().getOfPeriodoCorrente(
                    Session.getInstance().getEscola()
            );
            // Dias de Aula
            this.diasAula = ServiceProvider
                    .getInstance()
                    .getDiaAulaService()
                    .listByPeriodoCorrente();
            // Container dos dias de aula
            this.gradeJPanel.setLayout(new java.awt.GridLayout(
                    1,
                    diasAula.size() > MIN_DIAS_AULA_SIZE ? diasAula.size() : MIN_DIAS_AULA_SIZE,
                    Dimens.DEFAULT_MIN_PADDING,
                    Dimens.DEFAULT_MIN_PADDING
            ));
            // Todos os professores
            this.todosProfessores = ServiceProvider.getInstance().getProfessorService().findAll();
        } catch (ServiceException ex) {
            Logger.getLogger(GradeJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Salvar Grade de Horario
     */
    private void save() {
        System.out.println("salvou");
    }

    /**
     * Exibir as informacoes da turma
     */
    private void showTurma(Turma turma) {
        // Resetar
        this.reset();
        // Verificar se existem disciplinas para essa turma
        if (turma.getDisciplinaTurmas().size() > 0) {
            // Modificar o layout do container para comportar o total de disciplinas da turma
            this.disciplinasInnerContainerJPanel.setLayout(new java.awt.GridLayout(
                    turma.getDisciplinaTurmas().size() > MIN_DISCIPLINAS_SIZE ? turma.getDisciplinaTurmas().size() : MIN_DISCIPLINAS_SIZE,
                    1,
                    Dimens.DEFAULT_MIN_PADDING,
                    Dimens.DEFAULT_MIN_PADDING
            ));
            // Listeners para Disciplinas
            DisciplinaMouseListener dml = new DisciplinaMouseListener();
            // Adicionar as disciplinas da turma ao layout
            for (DisciplinaTurma dt : turma.getDisciplinaTurmas()) {
                DisciplinaJLabel dl = new DisciplinaJLabel(dt);
                dl.addMouseListener(dml);
                this.disciplinasInnerContainerJPanel.add(dl);
            }
        } else {
            // Modificar o layout do container para comportar o total de disciplinas da turma
            this.disciplinasInnerContainerJPanel.setLayout(new java.awt.GridLayout(1, 1, 0, 0));
            // Label de aviso
            MultilineLabel disciplinasJlabel = new MultilineLabel(Strings.GRADE_HORARIOS_TURMA_SEM_DISCIPLINAS);
            this.disciplinasInnerContainerJPanel.add(disciplinasJlabel);
        }
        // Listener para as aulas
        MouseListener aml = new AulaMouseListener();
        // Iterar pelos dias de aula e preencher o layout
        this.diasAula.forEach(da -> {
            DiaAulaJPanel diaAulaJPanel = new DiaAulaJPanel(
                    totalAulas,
                    da,
                    new ArrayList<>(),
                    horarios.stream().filter(h -> h.getDiaAula().equals(da)).collect(Collectors.toList()),
                    aml);
            this.gradeJPanel.add(diaAulaJPanel);
        });

        revalidate();
        repaint();
    }

    /**
     * Resetar componentes relacionados a turma
     */
    private void reset() {
        this.disciplinasInnerContainerJPanel.removeAll();
        this.gradeJPanel.removeAll();

        // Revalidar
        revalidate();
        repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        contentJPanel = new javax.swing.JPanel();
        leftJPanel = new javax.swing.JPanel();
        turmaJPanel = new javax.swing.JPanel();
        turmaTitleJLabel = new javax.swing.JLabel();
        turmasSelectOneMenuContainerJPanel = new javax.swing.JPanel();
        disciplinasContainerJPanel = new javax.swing.JPanel();
        disciplinasTitleJLabel = new javax.swing.JLabel();
        disciplinasInnerContainerJPanel = new javax.swing.JPanel();
        centerJPanel = new javax.swing.JPanel();
        gradeJPanel = new javax.swing.JPanel();
        footerJPanel = new javax.swing.JPanel();
        warningsJPanel = new javax.swing.JPanel();
        actionsJPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Gerar Grade de Horários");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new java.awt.Dimension(620, 410));

        contentJPanel.setBackground(Colors.COLOR_MAIN);
        contentJPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        contentJPanel.setLayout(new java.awt.BorderLayout());

        leftJPanel.setBackground(Colors.COLOR_MAIN);
        leftJPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        leftJPanel.setPreferredSize(new java.awt.Dimension(220, 400));
        leftJPanel.setLayout(new java.awt.BorderLayout());

        turmaJPanel.setBackground(Colors.COLOR_MAIN);
        turmaJPanel.setPreferredSize(new java.awt.Dimension(100, 80));
        turmaJPanel.setLayout(new java.awt.BorderLayout());

        turmaTitleJLabel.setBackground(Colors.COLOR_MENU_BAR);
        turmaTitleJLabel.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        turmaTitleJLabel.setForeground(Colors.COLOR_WHITE);
        turmaTitleJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        turmaTitleJLabel.setText("Turma");
        turmaTitleJLabel.setOpaque(true);
        turmaTitleJLabel.setPreferredSize(new java.awt.Dimension(42, 30));
        turmaJPanel.add(turmaTitleJLabel, java.awt.BorderLayout.PAGE_START);

        turmasSelectOneMenuContainerJPanel.setBackground(Colors.COLOR_WHITE);
        turmasSelectOneMenuContainerJPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        turmasSelectOneMenuContainerJPanel.setLayout(new java.awt.BorderLayout());
        turmaJPanel.add(turmasSelectOneMenuContainerJPanel, java.awt.BorderLayout.CENTER);

        leftJPanel.add(turmaJPanel, java.awt.BorderLayout.PAGE_START);

        disciplinasContainerJPanel.setBackground(Colors.COLOR_MAIN);
        disciplinasContainerJPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 0, 0, 0));
        disciplinasContainerJPanel.setLayout(new java.awt.BorderLayout());

        disciplinasTitleJLabel.setBackground(Colors.COLOR_MENU_BAR);
        disciplinasTitleJLabel.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        disciplinasTitleJLabel.setForeground(Colors.COLOR_WHITE);
        disciplinasTitleJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        disciplinasTitleJLabel.setText("Disciplinas");
        disciplinasTitleJLabel.setOpaque(true);
        disciplinasTitleJLabel.setPreferredSize(new java.awt.Dimension(42, 30));
        disciplinasContainerJPanel.add(disciplinasTitleJLabel, java.awt.BorderLayout.PAGE_START);

        disciplinasInnerContainerJPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        disciplinasInnerContainerJPanel.setMaximumSize(new java.awt.Dimension(190, 32767));
        disciplinasInnerContainerJPanel.setMinimumSize(new java.awt.Dimension(190, 33));
        disciplinasInnerContainerJPanel.setLayout(new java.awt.GridLayout(16, 1, 5, 5));
        disciplinasContainerJPanel.add(disciplinasInnerContainerJPanel, java.awt.BorderLayout.CENTER);

        leftJPanel.add(disciplinasContainerJPanel, java.awt.BorderLayout.CENTER);

        contentJPanel.add(leftJPanel, java.awt.BorderLayout.WEST);

        centerJPanel.setLayout(new java.awt.BorderLayout());

        gradeJPanel.setBackground(Colors.COLOR_MAIN);
        gradeJPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));

        javax.swing.GroupLayout gradeJPanelLayout = new javax.swing.GroupLayout(gradeJPanel);
        gradeJPanel.setLayout(gradeJPanelLayout);
        gradeJPanelLayout.setHorizontalGroup(
            gradeJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 380, Short.MAX_VALUE)
        );
        gradeJPanelLayout.setVerticalGroup(
            gradeJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 330, Short.MAX_VALUE)
        );

        centerJPanel.add(gradeJPanel, java.awt.BorderLayout.CENTER);

        footerJPanel.setBackground(Colors.COLOR_MAIN);
        footerJPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 0));
        footerJPanel.setPreferredSize(new java.awt.Dimension(390, 60));
        footerJPanel.setLayout(new java.awt.BorderLayout());

        warningsJPanel.setBackground(Colors.COLOR_MAIN);
        warningsJPanel.setPreferredSize(new java.awt.Dimension(380, 70));

        javax.swing.GroupLayout warningsJPanelLayout = new javax.swing.GroupLayout(warningsJPanel);
        warningsJPanel.setLayout(warningsJPanelLayout);
        warningsJPanelLayout.setHorizontalGroup(
            warningsJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        warningsJPanelLayout.setVerticalGroup(
            warningsJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        footerJPanel.add(warningsJPanel, java.awt.BorderLayout.CENTER);

        actionsJPanel.setBackground(Colors.COLOR_MAIN);
        actionsJPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 10));
        footerJPanel.add(actionsJPanel, java.awt.BorderLayout.EAST);

        centerJPanel.add(footerJPanel, java.awt.BorderLayout.PAGE_END);

        contentJPanel.add(centerJPanel, java.awt.BorderLayout.CENTER);

        getContentPane().add(contentJPanel, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel actionsJPanel;
    private javax.swing.JPanel centerJPanel;
    private javax.swing.JPanel contentJPanel;
    private javax.swing.JPanel disciplinasContainerJPanel;
    private javax.swing.JPanel disciplinasInnerContainerJPanel;
    private javax.swing.JLabel disciplinasTitleJLabel;
    private javax.swing.JPanel footerJPanel;
    private javax.swing.JPanel gradeJPanel;
    private javax.swing.JPanel leftJPanel;
    private javax.swing.JPanel turmaJPanel;
    private javax.swing.JLabel turmaTitleJLabel;
    private javax.swing.JPanel turmasSelectOneMenuContainerJPanel;
    private javax.swing.JPanel warningsJPanel;
    // End of variables declaration//GEN-END:variables

    @Override
    public void display() {
        this.setVisible(true);
    }

    @Override
    public JFrame getFrame() {
        return null;
    }

    @Override
    public void refresh() {
    }

    @Override
    public void close() {
        this.dispose();
        this.mainJFrame.toFront();
        this.mainJFrame.repaint();
    }

    /**
     * Listener para as disciplinas
     */
    private class DisciplinaMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            // Verificar se o componente esta liberado
            if (((DisciplinaJLabel) e.getSource()).isReleased()) {
                pressedDisciplinaJLabel = (DisciplinaJLabel) e.getSource();
                setCursor(Cursors.CURSOR_HAND_CLOSED);
            } else {
                setCursor(Cursors.CURSOR_BLOCKED);
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            prevCursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
            setCursor(prevCursor);
            // Verificar se existe painel de aula selecionado e se existe disciplina pressionada
            if (currentAulaJPanel != null && pressedDisciplinaJLabel != null) {
                // Verificar se o componente esta liberado ou se ja possui uma disciplina associada
                if (!currentAulaJPanel.canAddDisciplinaTurma()) {
                    // Obter a DisciplinaTurma do componente
                    DisciplinaTurma disciplinaTurma = currentAulaJPanel.getAula().getDisciplinaTurma();
                    // Iterar pelos labels de DisciplinasTurmas para encontrar o referente ao obtido
                    for (Component c : disciplinasInnerContainerJPanel.getComponents()) {
                        // Verificar se eh mesmo um componente de DisciplinaTurma
                        if (c instanceof DisciplinaJLabel) {
                            // Verificar se a DisciplinaTurma eh igual a obtida pelo painel
                            if (((DisciplinaJLabel) c).getDisciplinaTurma().equals(disciplinaTurma)) {
                                // Incrementar a DisciplinaTurma novamente
                                ((DisciplinaJLabel) c).increment();
                                // Encerrar o laco
                                break;
                            }
                        }
                    }
                }
                // Adicionar disciplina turma a aula
                currentAulaJPanel.addDisciplinaTurma(pressedDisciplinaJLabel.getDisciplinaTurma());
                // Atualizar o componente de disciplina
                pressedDisciplinaJLabel.decrement();
                // Resetar o painel da aula
                currentAulaJPanel.update();
                // Mostrar janela de selecao do Professor
                new ProfessoresCompetentesJDialog(GradeJFrame.this, currentAulaJPanel.getAula(), todosProfessores).display();
            }
            // Resetar disciplina pressionada
            pressedDisciplinaJLabel = null;
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if (pressedDisciplinaJLabel == null) {
                // Verificar se o componente esta liberado
                if (((DisciplinaJLabel) e.getSource()).isReleased()) {
                    setCursor(Cursors.CURSOR_HAND_OPEN);
                } else {
                    setCursor(Cursors.CURSOR_BLOCKED);
                }
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (pressedDisciplinaJLabel == null) {
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        }

    }

    /**
     * Listener para as aulas
     */
    private class AulaMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            prevCursor = getCursor();
            // Verificar se ha disciplina pressionada
            if (pressedDisciplinaJLabel != null) {
                // Painel da Aula
                currentAulaJPanel = (AulaJPanel) e.getSource();
                // Verificar se o componente pode ser alocado
                if (currentAulaJPanel.canAddDisciplinaTurma()) {
                    currentAulaJPanel.change();
                } else {
                    setCursor(Cursors.CURSOR_BLOCKED);
                }
            } else {
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            // Verificar se existe container selecionado
            if (currentAulaJPanel != null) {
                currentAulaJPanel.reset();
                setCursor(prevCursor);
                // Verificar se ainda continua pressionado
                if (pressedDisciplinaJLabel != null) {
                    // Se continua pressionado, o painel de aula atual deve ser resetado
                    currentAulaJPanel = null;
                }
            } else {
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        }

    }
    
    private class DefaultWindowAdapter extends WindowAdapter {
        
        @Override
        public void windowClosing(WindowEvent e) {
            close();
        }
        
        @Override
        public void windowClosed(WindowEvent e) {
        }
        
    }

}
