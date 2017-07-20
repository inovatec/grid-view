/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view;

import br.com.inovatec.grid.entity.Aula;
import br.com.inovatec.grid.entity.DiaAula;
import br.com.inovatec.grid.entity.DisciplinaTurma;
import br.com.inovatec.grid.entity.Horario;
import br.com.inovatec.grid.entity.Professor;
import br.com.inovatec.grid.entity.Turma;
import br.com.inovatec.grid.provider.ServiceProvider;
import br.com.inovatec.grid.service.exception.ServiceException;
import br.com.inovatec.grid.util.object.DisciplinaTurmaAulas;
import br.com.inovatec.grid.view.component.exception.NoItemSelectedException;
import br.com.inovatec.grid.view.component.factory.ButtonFactory;
import br.com.inovatec.grid.view.component.factory.SelectOneMenuFactory;
import br.com.inovatec.grid.view.component.form.Button;
import br.com.inovatec.grid.view.component.form.MultilineLabel;
import br.com.inovatec.grid.view.component.form.SelectOneMenu;
import br.com.inovatec.grid.view.component.listener.ButtonActionListener;
import br.com.inovatec.grid.view.component.main.AulaJPanel;
import br.com.inovatec.grid.view.component.main.DiaAulaJPanel;
import br.com.inovatec.grid.view.component.main.DisciplinaJLabel;
import br.com.inovatec.grid.view.component.main.GradeWarningJPanel;
import br.com.inovatec.grid.view.contract.FrameView;
import br.com.inovatec.grid.view.layout.DisciplinasTurmaView;
import br.com.inovatec.grid.view.layout.ProfessoresCompetentesView;
import br.com.inovatec.grid.view.session.Session;
import br.com.inovatec.grid.view.util.DefaultWindowAdapter;
import br.com.inovatec.grid.view.util.MessageFactory;
import br.com.inovatec.grid.view.values.Colors;
import br.com.inovatec.grid.view.values.Cursors;
import br.com.inovatec.grid.view.values.Dimens;
import br.com.inovatec.grid.view.values.Icons;
import br.com.inovatec.grid.view.values.Strings;
import br.com.inovatec.grid.view.values.Styles;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author zrobe
 */
public class GradeJFrame extends javax.swing.JFrame implements FrameView {

    private static final int MIN_DISCIPLINAS_SIZE = 12;
    private static final int MIN_DIAS_AULA_SIZE = 5;
    
    private boolean modified = false;

    private final MainJFrame mainJFrame;
    private ProfessoresCompetentesView professoresCompetentesView;
    private DisciplinasTurmaView disciplinasTurmaView;

    private Turma selectedTurma;
    private AulaJPanel currentAulaJPanel;
    private DisciplinaJLabel pressedDisciplinaJLabel;
    private Cursor prevCursor;
    private Integer totalAulas;
    private List<DiaAula> diasAula;
    private List<Horario> horarios;
    private List<Professor> todosProfessores;
    private SelectOneMenu<Turma> turmasSelectOneMenu;

    private Button closeButton, saveButton;
    
    /**
     * Creates new form GradeJFrame
     *
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

        addWindowListener(new DefaultWindowAdapter(this));

        this.disciplinasContainerJPanel.setVisible(false);

        this.closeButton = ButtonFactory.getInstance().getCloseButton(Colors.COLOR_MAIN, new ButtonActionListener() {
            @Override
            public void action() {
                close();
            }
        });
        this.actionsJPanel.add(this.closeButton);
        
        this.saveButton = ButtonFactory.getInstance().getSaveButton(Colors.COLOR_MAIN, new ButtonActionListener() {
            @Override
            public void action() {
                save();
            }
        });
        this.actionsJPanel.add(this.saveButton);

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
                    this.selectedTurma = turmasSelectOneMenu.getSelectedItem();
                    showTurma();
                } catch (NoItemSelectedException ex) {
                    reset();
                    setGradeBorderLayout();
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
            // Todos os professores
            this.todosProfessores = ServiceProvider.getInstance().getProfessorService().findAll();
        } catch (ServiceException ex) {
            Logger.getLogger(GradeJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        setGradeBorderLayout();
    }

    public boolean isModified() {
        return modified;
    }

    public void setModified(boolean modified) {
        this.modified = modified;
        this.turmasSelectOneMenu.setEnabled(!modified);
        this.saveButton.setEnabled(modified);
    }

    /**
     * Salvar Grade de Horario
     */
    private void save() {
        // Iterar pelos componentes e salvar as aulas
        for (Component c : this.gradeJPanel.getComponents()) {
            if (c instanceof DiaAulaJPanel) {
                for (Aula aula : ((DiaAulaJPanel) c).getAulas()) {
                    try {
                        ServiceProvider.getInstance().getAulaService().save(aula);
                    } catch (ServiceException ex) {
                        Logger.getLogger(GradeJFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        setModified(false);
        // Exibir mensagem de sucesso
        MessageFactory.showInfoMessage(this, Strings.GRADE_HORARIOS_SAVE_SUCCESS);
    }

    /**
     * Exibir as informacoes da turma
     */
    private void showTurma() {
        // Resetar
        this.reset();
        // Exibir painel de disciplinas
        this.disciplinasContainerJPanel.setVisible(true);
        // Verificar se existem disciplinas para essa turma
        if (this.selectedTurma.getDisciplinaTurmas().size() > 0) {
            // Modificar o layout do container para comportar o total de disciplinas da turma
            this.disciplinasInnerContainerJPanel.setLayout(new java.awt.GridLayout(
                    this.selectedTurma.getDisciplinaTurmas().size() > MIN_DISCIPLINAS_SIZE ? this.selectedTurma.getDisciplinaTurmas().size() : MIN_DISCIPLINAS_SIZE,
                    1,
                    Dimens.DEFAULT_MIN_PADDING,
                    Dimens.DEFAULT_MIN_PADDING
            ));
            // Listeners para Disciplinas
            GradeJFrame.DisciplinaMouseListener dml = new GradeJFrame.DisciplinaMouseListener();
            // Adicionar as disciplinas da turma ao layout
            for (DisciplinaTurma dt : this.selectedTurma.getDisciplinaTurmas()) {
                DisciplinaJLabel dl = new DisciplinaJLabel(dt);
                dl.addMouseListener(dml);
                this.disciplinasInnerContainerJPanel.add(dl);
            }
        } else {
            // Modificar o layout do container para comportar o total de disciplinas da turma
            this.disciplinasInnerContainerJPanel.setLayout(new java.awt.GridLayout(1, 1, 0, 0));
            // Label de aviso
            MultilineLabel disciplinasJlabel = new MultilineLabel(Strings.GRADE_HORARIOS_TURMA_SEM_DISCIPLINAS);
            disciplinasJlabel.setForeground(Colors.COLOR_WHITE);
            disciplinasJlabel.setBorder(new EmptyBorder(5, 0, 0, 0));
            this.disciplinasInnerContainerJPanel.add(disciplinasJlabel);
        }
        // Modificar o layout da grade
        setGradeJPanelLayout();
        // Listener para as aulas
        MouseListener aml = new GradeJFrame.AulaMouseListener();
        // Iterar pelos dias de aula e preencher o layout
        this.diasAula.forEach(da -> {
            try {
                DiaAulaJPanel diaAulaJPanel = new DiaAulaJPanel(
                        totalAulas,
                        da,
                        ServiceProvider.getInstance().getAulaService().findAll(this.selectedTurma, da),
                        horarios.stream().filter(h -> h.getDiaAula().equals(da)).collect(Collectors.toList()),
                        aml);
                this.gradeJPanel.add(diaAulaJPanel);
            } catch (ServiceException ex) {
                Logger.getLogger(GradeJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
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
        this.disciplinasContainerJPanel.setVisible(false);

        // Revalidar
        revalidate();
        repaint();
    }

    @Override
    public void display() {
        this.setVisible(true);
    }

    @Override
    public JFrame getFrame() {
        return this;
    }

    @Override
    public void refresh() {
        if (this.currentAulaJPanel != null) {
            // Redesenhar o painel da aula
            this.currentAulaJPanel.draw();
            // Verificar se veio da janela de disciplinas
            if (this.disciplinasTurmaView != null) {
                // Iterar pelos labels de DisciplinasTurmas para encontrar o referente ao obtido
                for (Component c : disciplinasInnerContainerJPanel.getComponents()) {
                    // Verificar se eh mesmo um componente de DisciplinaTurma
                    if (c instanceof DisciplinaJLabel) {
                        // Verificar se a DisciplinaTurma eh igual a obtida pelo painel
                        if (((DisciplinaJLabel) c).getDisciplinaTurmaAulas().getDisciplinaTurma().equals(currentAulaJPanel.getAula().getDisciplinaTurma())) {
                            // Incrementar a DisciplinaTurma novamente
                            ((DisciplinaJLabel) c).decrement();
                            // Encerrar o laco
                            break;
                        }
                    }
                }
                // Resetar a janela de disciplinas
                this.disciplinasTurmaView = null;
            } else {
                // Resetar a janela de professores
                this.professoresCompetentesView = null;
            }
            // Indicar que houve alteracao
            setModified(true);
            // Resetar o painel da aula
            this.currentAulaJPanel = null;
        }
    }

    @Override
    public void close() {
        // Esconder janela
        this.dispose();
        // Resetar a janela
        this.mainJFrame.setGradeJFrame(null);
        // Trazer a janela principal para a frente
        this.mainJFrame.toFront();
        this.mainJFrame.repaint();
        // Habilitar a janela novamente
        this.mainJFrame.setEnabled(true);
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
                // Obter a DisciplinaTurma do componente
                DisciplinaTurma disciplinaTurma = currentAulaJPanel.getAula().getDisciplinaTurma();
                // Iterar pelos labels de DisciplinasTurmas para encontrar o referente ao obtido
                for (Component c : disciplinasInnerContainerJPanel.getComponents()) {
                    // Verificar se eh mesmo um componente de DisciplinaTurma
                    if (c instanceof DisciplinaJLabel) {
                        // Verificar se a DisciplinaTurma eh igual a obtida pelo painel
                        if (((DisciplinaJLabel) c).getDisciplinaTurmaAulas().getDisciplinaTurma().equals(disciplinaTurma)) {
                            // Incrementar a DisciplinaTurma novamente
                            ((DisciplinaJLabel) c).increment();
                            // Encerrar o laco
                            break;
                        }
                    }
                }
                // Indicar que houve modificacao
                setModified(true);
                // Adicionar disciplina turma a aula
                currentAulaJPanel.getAula().setDisciplinaTurma(pressedDisciplinaJLabel.getDisciplinaTurmaAulas().getDisciplinaTurma());
                // Atualizar o componente de disciplina
                pressedDisciplinaJLabel.decrement();
                // Resetar o painel da aula
                currentAulaJPanel.draw();
                // Mostrar janela de selecao do Professor
                showProfessoresCompetentesView(currentAulaJPanel);
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
            // Obter o painel da aula clicado
            currentAulaJPanel = ((AulaJPanel) e.getSource());
            // Verificar se ja existe disciplinaTurma preenchida
            if (currentAulaJPanel.getAula().getDisciplinaTurma() != null) {
                showProfessoresCompetentesView(currentAulaJPanel);
            } else {
                showDisciplinasTurmaView(currentAulaJPanel);
            }
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
                if (currentAulaJPanel != null) {
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

    /**
     * Exibir janela de professores competentes
     *
     * @param aulaJPanel
     */
    private void showProfessoresCompetentesView(AulaJPanel aulaJPanel) {
        // Exibir janela dos professores da disciplina
        this.professoresCompetentesView = new ProfessoresCompetentesView(GradeJFrame.this, aulaJPanel.getAula());
        this.professoresCompetentesView.display();
    }

    /**
     * Exibir janela de disciplinas da turma
     *
     * @param aulaJPanel
     */
    private void showDisciplinasTurmaView(AulaJPanel aulaJPanel) {
        // Lista de disciplinas com o total de aulas ja adicionadas
        List<DisciplinaTurmaAulas> disciplinaTurmaAulas = new ArrayList<>();
        // Percorrer pelos labels das disciplinas para obter as disciplinas com seus totais de aula atualizados
        for (Component c : this.disciplinasInnerContainerJPanel.getComponents()) {
            if (c instanceof DisciplinaJLabel) {
                disciplinaTurmaAulas.add(((DisciplinaJLabel) c).getDisciplinaTurmaAulas());
            }
        }
        // Exibir janela das disciplinas da turma
        this.disciplinasTurmaView = new DisciplinasTurmaView(GradeJFrame.this, aulaJPanel.getAula(), this.selectedTurma, disciplinaTurmaAulas);
        this.disciplinasTurmaView.display();
    }

    private void setGradeJPanelLayout() {
        try {
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
        } catch (ServiceException ex) {
            Logger.getLogger(GradeJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setGradeBorderLayout() {
        this.gradeJPanel.setLayout(new BorderLayout());
        this.gradeJPanel.add(new GradeWarningJPanel(), java.awt.BorderLayout.CENTER);
        this.saveButton.setEnabled(false);
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
        disciplinasContainerTitleJPanel = new javax.swing.JPanel();
        disciplinasTitleJLabel = new javax.swing.JLabel();
        disciplinasInnerContainerJPanel = new javax.swing.JPanel();
        centerJPanel = new javax.swing.JPanel();
        gradeJPanel = new javax.swing.JPanel();
        footerJPanel = new javax.swing.JPanel();
        warningsJPanel = new javax.swing.JPanel();
        actionsJPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Grade de Horários");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setFont(Styles.FONT_FAMILY);

        contentJPanel.setBackground(Colors.COLOR_MAIN);
        contentJPanel.setLayout(new java.awt.BorderLayout());

        leftJPanel.setBackground(Colors.COLOR_GRADE_LEFT_PANEL);
        leftJPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        leftJPanel.setPreferredSize(new java.awt.Dimension(250, 400));
        leftJPanel.setLayout(new java.awt.BorderLayout());

        turmaJPanel.setBackground(Colors.COLOR_GRADE_LEFT_PANEL);
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

        turmasSelectOneMenuContainerJPanel.setBackground(Colors.COLOR_GRADE_LEFT_PANEL);
        turmasSelectOneMenuContainerJPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 0, 5, 0));
        turmasSelectOneMenuContainerJPanel.setLayout(new java.awt.BorderLayout());
        turmaJPanel.add(turmasSelectOneMenuContainerJPanel, java.awt.BorderLayout.CENTER);

        leftJPanel.add(turmaJPanel, java.awt.BorderLayout.PAGE_START);

        disciplinasContainerJPanel.setBackground(Colors.COLOR_GRADE_LEFT_PANEL);
        disciplinasContainerJPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        disciplinasContainerJPanel.setLayout(new java.awt.BorderLayout());

        disciplinasContainerTitleJPanel.setBackground(Colors.COLOR_GRADE_LEFT_PANEL);
        disciplinasContainerTitleJPanel.setPreferredSize(new java.awt.Dimension(42, 35));
        disciplinasContainerTitleJPanel.setLayout(new java.awt.BorderLayout());

        disciplinasTitleJLabel.setBackground(Colors.COLOR_MENU_BAR);
        disciplinasTitleJLabel.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        disciplinasTitleJLabel.setForeground(Colors.COLOR_WHITE);
        disciplinasTitleJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        disciplinasTitleJLabel.setText("Disciplinas");
        disciplinasTitleJLabel.setOpaque(true);
        disciplinasTitleJLabel.setPreferredSize(new java.awt.Dimension(42, 30));
        disciplinasContainerTitleJPanel.add(disciplinasTitleJLabel, java.awt.BorderLayout.CENTER);

        disciplinasContainerJPanel.add(disciplinasContainerTitleJPanel, java.awt.BorderLayout.PAGE_START);

        disciplinasInnerContainerJPanel.setBackground(Colors.COLOR_GRADE_LEFT_PANEL);
        disciplinasInnerContainerJPanel.setMaximumSize(new java.awt.Dimension(190, 32767));
        disciplinasInnerContainerJPanel.setMinimumSize(new java.awt.Dimension(190, 33));
        disciplinasInnerContainerJPanel.setLayout(new java.awt.GridLayout(16, 1, 5, 5));
        disciplinasContainerJPanel.add(disciplinasInnerContainerJPanel, java.awt.BorderLayout.CENTER);

        leftJPanel.add(disciplinasContainerJPanel, java.awt.BorderLayout.CENTER);

        contentJPanel.add(leftJPanel, java.awt.BorderLayout.WEST);

        centerJPanel.setLayout(new java.awt.BorderLayout());

        gradeJPanel.setBackground(Colors.COLOR_MAIN);
        gradeJPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));

        javax.swing.GroupLayout gradeJPanelLayout = new javax.swing.GroupLayout(gradeJPanel);
        gradeJPanel.setLayout(gradeJPanelLayout);
        gradeJPanelLayout.setHorizontalGroup(
            gradeJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 130, Short.MAX_VALUE)
        );
        gradeJPanelLayout.setVerticalGroup(
            gradeJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 220, Short.MAX_VALUE)
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
    private javax.swing.JPanel disciplinasContainerTitleJPanel;
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
}
