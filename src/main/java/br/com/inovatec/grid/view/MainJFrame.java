/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view;

import br.com.inovatec.grid.dao.connection.ConnectionFactory;
import br.com.inovatec.grid.provider.ServiceProvider;
import br.com.inovatec.grid.dao.exceptions.SearchEntityException;
import br.com.inovatec.grid.reports.util.ReportsController;
import br.com.inovatec.grid.service.exception.ServiceException;
import br.com.inovatec.grid.view.component.AppMenuBar;
import br.com.inovatec.grid.view.contract.FrameView;
import br.com.inovatec.grid.view.controller.ViewController;
import br.com.inovatec.grid.view.session.Session;
import br.com.inovatec.grid.view.util.DummyFrame;
import br.com.inovatec.grid.view.util.MessageFactory;
import br.com.inovatec.grid.view.util.ResultAction;
import br.com.inovatec.grid.view.values.Colors;
import br.com.inovatec.grid.view.values.Dimens;
import br.com.inovatec.grid.view.values.Drawable;
import javax.swing.JFrame;
import br.com.inovatec.grid.view.values.Icons;
import br.com.inovatec.grid.view.values.Strings;
import br.com.inovatec.grid.view.values.Styles;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author zrobe
 */
public class MainJFrame extends JFrame implements FrameView {

    // Instancias de Janelas
    private ViewGradeJFrame viewGradeJFrame;
    private GradeJFrame gradeJFrame;

    private final DummyFrame dummyFrame;
    private boolean renderComponentsCondition = false;

    /**
     * Creates new form MainJFrame
     */
    public MainJFrame() {
        // Frame escondido
        this.dummyFrame = new DummyFrame("Grid", this);
    }

    public GradeJFrame getGradeJFrame() {
        return gradeJFrame;
    }

    public void setGradeJFrame(GradeJFrame gradeJFrame) {
        this.gradeJFrame = gradeJFrame;
    }

    public ViewGradeJFrame getViewGradeJFrame() {
        return viewGradeJFrame;
    }

    public void setViewGradeJFrame(ViewGradeJFrame viewGradeJFrame) {
        this.viewGradeJFrame = viewGradeJFrame;
    }

    /**
     * Caso passe da etapa de login
     */
    public void postLogin() {
        // Inicializar componentes
        initComponents();
        // Icones da Aplicacao
        this.setIconImages(Icons.getAppIcons());
        // Maximizar
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        mainJMenuBar = new AppMenuBar();
        arquivoJMenu = new javax.swing.JMenu();
        imprimirJMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        sairJMenuItem = new javax.swing.JMenuItem();
        gerenciarJMenu = new javax.swing.JMenu();
        aulasJMenuItem = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        professoresJMenuItem = new javax.swing.JMenuItem();
        turmasJMenuItem = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        escolaJMenuItem = new javax.swing.JMenuItem();
        disciplinasJMenuItem = new javax.swing.JMenuItem();
        salasJMenuItem = new javax.swing.JMenuItem();
        gradeHorariosJMenu = new javax.swing.JMenu();
        gradeHorariosJMenuItem = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        gerarGradeHorariosAutoJMenuItem = new javax.swing.JMenuItem();
        gerarGradeHorariosManualJMenuItem = new javax.swing.JMenuItem();
        relatoriosJMenu = new javax.swing.JMenu();
        gradeCompletaJMenuItem = new javax.swing.JMenuItem();
        gradePorProfessorJMenuItem = new javax.swing.JMenuItem();
        gradePorTurmaJMenuItem = new javax.swing.JMenuItem();
        gradePorDisciplinaJMenuItem = new javax.swing.JMenuItem();
        ajudaJMenu = new javax.swing.JMenu();
        dicasJMenuItem = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        sobreJMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Grid");
        setBackground(Colors.COLOR_INDEX_BACK);
        setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new java.awt.Dimension(Dimens.MAIN_MINIMUN_SIZE_WIDTH, Dimens.MAIN_MINIMUN_SIZE_HEIGHT));
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jPanel1.setBackground(Colors.COLOR_INDEX_BACK);
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(Colors.COLOR_INDEX_BACK);
        jPanel2.setPreferredSize(new java.awt.Dimension(800, 40));
        jPanel2.setLayout(new java.awt.GridLayout(1, 2, 10, 0));

        jLabel2.setFont(Styles.FONT_FAMILY);
        jLabel2.setForeground(Colors.COLOR_WHITE);
        jLabel2.setText("Suporte: suporte@inovatecsolutions.com.br");
        jLabel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10));
        jPanel2.add(jLabel2);

        jLabel3.setFont(Styles.FONT_FAMILY);
        jLabel3.setForeground(Colors.COLOR_WHITE);
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Desenvolvido por InovaTec © 2017. Todos os direitos reservados.");
        jLabel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10));
        jPanel2.add(jLabel3);

        jPanel1.add(jPanel2, java.awt.BorderLayout.PAGE_END);

        jPanel3.setBackground(Colors.COLOR_INDEX_BACK);
        jPanel3.setMaximumSize(new java.awt.Dimension(0, 0));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jLabel1.setBackground(Colors.COLOR_INDEX_BACK);
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(Drawable.BG_INDEX);
        jPanel3.add(jLabel1, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel3, java.awt.BorderLayout.CENTER);

        jPanel4.setBackground(Colors.COLOR_INDEX_BACK);
        jPanel4.setPreferredSize(new java.awt.Dimension(800, 30));
        jPanel4.setLayout(new java.awt.BorderLayout());
        jPanel1.add(jPanel4, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(jPanel1);

        mainJMenuBar.setBackground(Colors.COLOR_MENU_BAR);
        mainJMenuBar.setBorder(null);
        mainJMenuBar.setPreferredSize(new java.awt.Dimension(413, 35));

        arquivoJMenu.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        arquivoJMenu.setForeground(Colors.COLOR_WHITE);
        arquivoJMenu.setText("Arquivo");
        arquivoJMenu.setFont(Styles.FONT_FAMILY);

        imprimirJMenuItem.setFont(Styles.FONT_FAMILY);
        imprimirJMenuItem.setText("Imprimir");
        imprimirJMenuItem.setMinimumSize(new java.awt.Dimension(240, 25));
        imprimirJMenuItem.setPreferredSize(new java.awt.Dimension(240, 25));
        arquivoJMenu.add(imprimirJMenuItem);
        arquivoJMenu.add(jSeparator1);

        sairJMenuItem.setFont(Styles.FONT_FAMILY);
        sairJMenuItem.setText("Sair");
        sairJMenuItem.setMinimumSize(new java.awt.Dimension(240, 25));
        sairJMenuItem.setPreferredSize(new java.awt.Dimension(240, 25));
        sairJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sairJMenuItemActionPerformed(evt);
            }
        });
        arquivoJMenu.add(sairJMenuItem);

        mainJMenuBar.add(arquivoJMenu);

        gerenciarJMenu.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        gerenciarJMenu.setForeground(Colors.COLOR_WHITE);
        gerenciarJMenu.setText("Gerenciar");
        gerenciarJMenu.setFont(Styles.FONT_FAMILY);

        aulasJMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F8, 0));
        aulasJMenuItem.setFont(Styles.FONT_FAMILY);
        aulasJMenuItem.setText("Aulas");
        aulasJMenuItem.setPreferredSize(new java.awt.Dimension(240, 25));
        aulasJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aulasJMenuItemActionPerformed(evt);
            }
        });
        gerenciarJMenu.add(aulasJMenuItem);
        gerenciarJMenu.add(jSeparator3);

        professoresJMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        professoresJMenuItem.setFont(Styles.FONT_FAMILY);
        professoresJMenuItem.setText("Professores");
        professoresJMenuItem.setPreferredSize(new java.awt.Dimension(240, 25));
        professoresJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                professoresJMenuItemActionPerformed(evt);
            }
        });
        gerenciarJMenu.add(professoresJMenuItem);

        turmasJMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F6, 0));
        turmasJMenuItem.setFont(Styles.FONT_FAMILY);
        turmasJMenuItem.setText("Turmas");
        turmasJMenuItem.setPreferredSize(new java.awt.Dimension(240, 25));
        turmasJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                turmasJMenuItemActionPerformed(evt);
            }
        });
        gerenciarJMenu.add(turmasJMenuItem);
        gerenciarJMenu.add(jSeparator2);

        escolaJMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        escolaJMenuItem.setFont(Styles.FONT_FAMILY);
        escolaJMenuItem.setText("Escola");
        escolaJMenuItem.setPreferredSize(new java.awt.Dimension(240, 25));
        escolaJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                escolaJMenuItemActionPerformed(evt);
            }
        });
        gerenciarJMenu.add(escolaJMenuItem);

        disciplinasJMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
        disciplinasJMenuItem.setFont(Styles.FONT_FAMILY);
        disciplinasJMenuItem.setText("Disciplinas");
        disciplinasJMenuItem.setPreferredSize(new java.awt.Dimension(240, 25));
        disciplinasJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                disciplinasJMenuItemActionPerformed(evt);
            }
        });
        gerenciarJMenu.add(disciplinasJMenuItem);

        salasJMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
        salasJMenuItem.setFont(Styles.FONT_FAMILY);
        salasJMenuItem.setText("Salas de Aula");
        salasJMenuItem.setPreferredSize(new java.awt.Dimension(240, 25));
        salasJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salasJMenuItemActionPerformed(evt);
            }
        });
        gerenciarJMenu.add(salasJMenuItem);

        mainJMenuBar.add(gerenciarJMenu);

        gradeHorariosJMenu.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        gradeHorariosJMenu.setForeground(Colors.COLOR_WHITE);
        gradeHorariosJMenu.setText("Grade de Horários");
        gradeHorariosJMenu.setFont(Styles.FONT_FAMILY);

        gradeHorariosJMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        gradeHorariosJMenuItem.setFont(Styles.FONT_FAMILY);
        gradeHorariosJMenuItem.setText("Grade de Horários Atual");
        gradeHorariosJMenuItem.setMinimumSize(new java.awt.Dimension(240, 25));
        gradeHorariosJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gradeHorariosJMenuItemActionPerformed(evt);
            }
        });
        gradeHorariosJMenu.add(gradeHorariosJMenuItem);
        gradeHorariosJMenu.add(jSeparator4);

        gerarGradeHorariosAutoJMenuItem.setFont(Styles.FONT_FAMILY);
        gerarGradeHorariosAutoJMenuItem.setText("Gerar Grade de Horários [Auto]");
        gerarGradeHorariosAutoJMenuItem.setEnabled(false);
        gerarGradeHorariosAutoJMenuItem.setMinimumSize(new java.awt.Dimension(240, 25));
        gradeHorariosJMenu.add(gerarGradeHorariosAutoJMenuItem);

        gerarGradeHorariosManualJMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        gerarGradeHorariosManualJMenuItem.setFont(Styles.FONT_FAMILY);
        gerarGradeHorariosManualJMenuItem.setText("Gerar Grade de Horários [Manual]");
        gerarGradeHorariosManualJMenuItem.setMinimumSize(new java.awt.Dimension(240, 25));
        gerarGradeHorariosManualJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gerarGradeHorariosManualJMenuItemActionPerformed(evt);
            }
        });
        gradeHorariosJMenu.add(gerarGradeHorariosManualJMenuItem);

        mainJMenuBar.add(gradeHorariosJMenu);

        relatoriosJMenu.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        relatoriosJMenu.setForeground(Colors.COLOR_WHITE);
        relatoriosJMenu.setText("Relatórios");
        relatoriosJMenu.setFocusable(false);
        relatoriosJMenu.setFont(Styles.FONT_FAMILY);
        relatoriosJMenu.setMinimumSize(new java.awt.Dimension(240, 25));

        gradeCompletaJMenuItem.setFont(Styles.FONT_FAMILY);
        gradeCompletaJMenuItem.setText("Grade Completa");
        gradeCompletaJMenuItem.setMinimumSize(new java.awt.Dimension(240, 25));
        gradeCompletaJMenuItem.setPreferredSize(new java.awt.Dimension(240, 25));
        gradeCompletaJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gradeCompletaJMenuItemActionPerformed(evt);
            }
        });
        relatoriosJMenu.add(gradeCompletaJMenuItem);

        gradePorProfessorJMenuItem.setFont(Styles.FONT_FAMILY);
        gradePorProfessorJMenuItem.setText("Grade por Professor");
        gradePorProfessorJMenuItem.setMinimumSize(new java.awt.Dimension(240, 25));
        gradePorProfessorJMenuItem.setPreferredSize(new java.awt.Dimension(240, 25));
        relatoriosJMenu.add(gradePorProfessorJMenuItem);

        gradePorTurmaJMenuItem.setFont(Styles.FONT_FAMILY);
        gradePorTurmaJMenuItem.setText("Grade por Turma");
        gradePorTurmaJMenuItem.setMinimumSize(new java.awt.Dimension(240, 25));
        gradePorTurmaJMenuItem.setPreferredSize(new java.awt.Dimension(240, 25));
        relatoriosJMenu.add(gradePorTurmaJMenuItem);

        gradePorDisciplinaJMenuItem.setFont(Styles.FONT_FAMILY);
        gradePorDisciplinaJMenuItem.setText("Grade por Disciplina");
        gradePorDisciplinaJMenuItem.setMinimumSize(new java.awt.Dimension(240, 25));
        gradePorDisciplinaJMenuItem.setPreferredSize(new java.awt.Dimension(240, 25));
        relatoriosJMenu.add(gradePorDisciplinaJMenuItem);

        mainJMenuBar.add(relatoriosJMenu);

        ajudaJMenu.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        ajudaJMenu.setForeground(Colors.COLOR_WHITE);
        ajudaJMenu.setText("Ajuda");
        ajudaJMenu.setFont(Styles.FONT_FAMILY);

        dicasJMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        dicasJMenuItem.setFont(Styles.FONT_FAMILY);
        dicasJMenuItem.setText("Dicas");
        dicasJMenuItem.setMinimumSize(new java.awt.Dimension(240, 25));
        dicasJMenuItem.setPreferredSize(new java.awt.Dimension(240, 25));
        dicasJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dicasJMenuItemActionPerformed(evt);
            }
        });
        ajudaJMenu.add(dicasJMenuItem);
        ajudaJMenu.add(jSeparator5);

        sobreJMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, java.awt.event.InputEvent.CTRL_MASK));
        sobreJMenuItem.setFont(Styles.FONT_FAMILY);
        sobreJMenuItem.setText("Sobre");
        sobreJMenuItem.setMinimumSize(new java.awt.Dimension(240, 25));
        sobreJMenuItem.setPreferredSize(new java.awt.Dimension(240, 25));
        sobreJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sobreJMenuItemActionPerformed(evt);
            }
        });
        ajudaJMenu.add(sobreJMenuItem);

        mainJMenuBar.add(ajudaJMenu);

        setJMenuBar(mainJMenuBar);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Exibir View de Escola
     *
     * @param evt
     */
    private void escolaJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_escolaJMenuItemActionPerformed
        ViewController.showEscolaDialog(this, Session.getInstance().getEscola() == null);
    }//GEN-LAST:event_escolaJMenuItemActionPerformed

    private void professoresJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_professoresJMenuItemActionPerformed
        ViewController.showProfessoresView(this);
    }//GEN-LAST:event_professoresJMenuItemActionPerformed

    private void turmasJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_turmasJMenuItemActionPerformed
        ViewController.showTurmasView(this);
    }//GEN-LAST:event_turmasJMenuItemActionPerformed

    private void sairJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sairJMenuItemActionPerformed
        this.close();
    }//GEN-LAST:event_sairJMenuItemActionPerformed

    private void disciplinasJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_disciplinasJMenuItemActionPerformed
        ViewController.showDisciplinasView(this);
    }//GEN-LAST:event_disciplinasJMenuItemActionPerformed

    private void salasJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salasJMenuItemActionPerformed
        ViewController.showSalasView(this);
    }//GEN-LAST:event_salasJMenuItemActionPerformed

    private void gerarGradeHorariosManualJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gerarGradeHorariosManualJMenuItemActionPerformed
        if (this.gradeJFrame == null) {
            this.gradeJFrame = new GradeJFrame(this);
        }
        // Desabilitar a janela
        this.setEnabled(false);
        // Mostrar a janela de grade
        this.gradeJFrame.display();
    }//GEN-LAST:event_gerarGradeHorariosManualJMenuItemActionPerformed

    private void gradeHorariosJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gradeHorariosJMenuItemActionPerformed
        if (this.viewGradeJFrame == null) {
            this.viewGradeJFrame = new ViewGradeJFrame(this);
        }
        // Desabilitar a janela
        this.setEnabled(false);
        // Mostrar a janela de grade
        this.viewGradeJFrame.display();
    }//GEN-LAST:event_gradeHorariosJMenuItemActionPerformed

    private void dicasJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dicasJMenuItemActionPerformed
        ViewController.showTutorialView(this);
    }//GEN-LAST:event_dicasJMenuItemActionPerformed

    private void sobreJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sobreJMenuItemActionPerformed
        ViewController.showSobreView(this);
    }//GEN-LAST:event_sobreJMenuItemActionPerformed

    private void aulasJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aulasJMenuItemActionPerformed
        ViewController.showAulasView(this);
    }//GEN-LAST:event_aulasJMenuItemActionPerformed

    private void gradeCompletaJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gradeCompletaJMenuItemActionPerformed
        ReportsController.showGradeHorarios();
    }//GEN-LAST:event_gradeCompletaJMenuItemActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        // Alterar o tema utilizado
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            // Criar instancias
            MainJFrame mainJFrame = new MainJFrame();
            SplashJFrame splash = new SplashJFrame(mainJFrame.dummyFrame);

            java.awt.EventQueue.invokeLater(() -> {
                // Realizar testes iniciais
                mainJFrame.initialTests();
                // Esconder a Janela
                splash.setVisible(false);
                // Exibir a Janela Principal da aplicação
                mainJFrame.display();
            });
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu ajudaJMenu;
    private javax.swing.JMenu arquivoJMenu;
    private javax.swing.JMenuItem aulasJMenuItem;
    private javax.swing.JMenuItem dicasJMenuItem;
    private javax.swing.JMenuItem disciplinasJMenuItem;
    private javax.swing.JMenuItem escolaJMenuItem;
    private javax.swing.JMenuItem gerarGradeHorariosAutoJMenuItem;
    private javax.swing.JMenuItem gerarGradeHorariosManualJMenuItem;
    private javax.swing.JMenu gerenciarJMenu;
    private javax.swing.JMenuItem gradeCompletaJMenuItem;
    private javax.swing.JMenu gradeHorariosJMenu;
    private javax.swing.JMenuItem gradeHorariosJMenuItem;
    private javax.swing.JMenuItem gradePorDisciplinaJMenuItem;
    private javax.swing.JMenuItem gradePorProfessorJMenuItem;
    private javax.swing.JMenuItem gradePorTurmaJMenuItem;
    private javax.swing.JMenuItem imprimirJMenuItem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JMenuBar mainJMenuBar;
    private javax.swing.JMenuItem professoresJMenuItem;
    private javax.swing.JMenu relatoriosJMenu;
    private javax.swing.JMenuItem sairJMenuItem;
    private javax.swing.JMenuItem salasJMenuItem;
    private javax.swing.JMenuItem sobreJMenuItem;
    private javax.swing.JMenuItem turmasJMenuItem;
    // End of variables declaration//GEN-END:variables

    @Override
    public JFrame getFrame() {
        return this;
    }

    @Override
    public void refresh() {
        // Verificar se existe usuario logado
        if (Session.getInstance().isLogged()) {
            // Verificar se a janela ainda nao esta visivel
            if (this.renderComponentsCondition) {
                postLogin();
                // Tornar janela visivel
                this.setVisible(true);
                // Esconder o dummy
                this.dummyFrame.dispose();
                // Bloquear permissao de renderizar componentes
                this.renderComponentsCondition = false;
            }
            // Verificar se existe escola configurada
            if (Session.getInstance().getEscola() == null) {
                try {
                    // Resgatar escola cadastrada na base de dados
                    Session.getInstance().setEscola(ServiceProvider.getInstance().getEscolaService().get());
                    // Validar dados da escola
                    escolaValidate();
                } catch (ServiceException ex) {
                    // Verificar se a causa do resultado foi nao ter nenhuma escola cadastrada
                    if (ex.getCause().getClass().equals(SearchEntityException.class)) {
                        MessageFactory.showOkMessage(this, Strings.MESSAGE_NENHUMA_ESCOLA_CONFIGURADA, new EscolaValidationMessageResultAction());
                    }
                }
            } else {
                // Validar dados da escola
                escolaValidate();
            }
        } else { // Exibir tela de login caso nao exista usuario na sessao
            this.close();
        }
    }

    @Override
    public void display() {
        // Verificar se existe usuario logado
        if (Session.getInstance().isLogged()) {
            this.refresh();
        } else { // Exibir tela de login caso nao exista usuario na sessao
            // Modificar propriedade que indica a renderizacao dos componentes
            this.renderComponentsCondition = true;
            // Mostrar tela de login
            ViewController.showLoginDialog(this.dummyFrame);
        }
    }

    @Override
    public void close() {
        System.exit(0);
    }

    /**
     * Validar dados da Escola
     */
    private void escolaValidate() {
        // Verificar se os demais dados necessarios foram informados
        try {
            // Verificar se existem dias de aula cadastrados
            // Verificar se os horarios da escola já foram definidos
            if (ServiceProvider.getInstance()
                    .getDiaAulaService()
                    .listBy(Session.getInstance().getEscola().getPeriodoCorrente())
                    .isEmpty()
                    || ServiceProvider.getInstance()
                            .getHorarioService()
                            .getOfPeriodoCorrente(Session.getInstance().getEscola())
                            .isEmpty()) {
                // Exibir mensagem de dados incompletos
                MessageFactory.showOkMessage(this, Strings.MESSAGE_DADOS_ESCOLA_INCOMPLETOS, new EscolaValidationMessageResultAction());
            }
        } catch (ServiceException ex) {
            Logger.getLogger(MainJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private class EscolaValidationMessageResultAction extends ResultAction {

        @Override
        public void confirm() {
            ViewController.showEscolaDialog(MainJFrame.this, true);
        }

        @Override
        public void cancel() {
            // Encerrar aplicação
            close();
        }

    }

    /**
     * Realizar testes iniciais
     */
    private void initialTests() {
        try {
            // Verificar conexao
            ConnectionFactory.getInstance().testConnection();
            // Mensagem de Log de sucesso
            Logger.getLogger(MainJFrame.class.getName()).fine(Strings.DATABASE_CONNECTION_SUCCESS_MESSAGE);
        } catch (Exception ex) {
            // Mostrar mensagem de erro
            MessageFactory.showErrorMessage(this, Strings.DATABASE_CONNECTION_ERROR_MESSAGE);
            // Print stacktrace do erro
            Logger.getLogger(MainJFrame.class.getName()).log(Level.SEVERE, null, ex);
            // Fechar janela
            this.close();
        }
    }

}
