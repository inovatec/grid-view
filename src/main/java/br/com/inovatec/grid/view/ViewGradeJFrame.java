/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view;

import br.com.inovatec.grid.entity.DiaAula;
import br.com.inovatec.grid.entity.Horario;
import br.com.inovatec.grid.entity.Turma;
import br.com.inovatec.grid.provider.ServiceProvider;
import br.com.inovatec.grid.service.exception.ServiceException;
import br.com.inovatec.grid.view.component.HeaderLayout;
import br.com.inovatec.grid.view.component.main.DiaAulaJPanel;
import br.com.inovatec.grid.view.component.main.GradeJPanel;
import br.com.inovatec.grid.view.contract.FrameView;
import br.com.inovatec.grid.view.session.Session;
import br.com.inovatec.grid.view.util.DefaultWindowAdapter;
import br.com.inovatec.grid.view.values.Icons;
import br.com.inovatec.grid.view.values.Strings;
import br.com.inovatec.grid.view.values.Styles;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.JFrame;

/**
 *
 * @author zrobe
 */
public class ViewGradeJFrame extends JFrame implements FrameView {

    private final MainJFrame mainJFrame;

    /**
     * Creates new form ViewGradeJFrame
     *
     * @param mainJFrame
     */
    public ViewGradeJFrame(MainJFrame mainJFrame) {
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

        HeaderLayout headerLayout = new HeaderLayout(
                Strings.VIEW_GRADE_TITLE,
                Strings.getReplacedString(Strings.VIEW_GRADE_CONTENT_HEADER_TIP, Session.getInstance().getEscola().getPeriodoCorrente().toString()),
                Icons.IC_GRID_VIEW
        );
        this.containerJPanel.add(headerLayout, java.awt.BorderLayout.PAGE_START);

        addWindowListener(new DefaultWindowAdapter(this));

        try {
            // Lista de Turmas do Periodo
            List<Turma> turmas = ServiceProvider.getInstance().getTurmaService().findAllByPeriodoCorrente();
            // Verificar se existem turmas cadastradas
            if (!turmas.isEmpty()) {
                // Modificar o layout para comportar o total de resultados
                this.contentJPanel.setLayout(new java.awt.GridLayout(turmas.size(), 0));
                // Total de Aulas da escola
                int totalAulas = ServiceProvider.getInstance().getDiaAulaService().getMaxQuantidadeAulas();
                // Horarios da escola
                List<Horario> horarios = ServiceProvider.getInstance().getHorarioService().getOfPeriodoCorrente(
                        Session.getInstance().getEscola()
                );
                // Dias de aula da escola
                // Dias de Aula
                List<DiaAula> diasAula = ServiceProvider
                        .getInstance()
                        .getDiaAulaService()
                        .listByPeriodoCorrente();
                // Iterar pelas turmas
                turmas.forEach(tu -> {
                    // Container dos dias de aula
                    GradeJPanel gradeJPanel = new GradeJPanel(diasAula.size(), tu);

                    // Iterar pelos dias de aula e preencher o layout
                    diasAula.forEach(da -> {
                        try {
                            DiaAulaJPanel diaAulaJPanel = new DiaAulaJPanel(
                                    totalAulas,
                                    da,
                                    tu,
                                    ServiceProvider.getInstance().getAulaService().findAll(tu, da),
                                    horarios.stream().filter(h -> h.getDiaAula().equals(da)).collect(Collectors.toList()),
                                    null,
                                    true,
                                    null);
                            gradeJPanel.addDiaAulaJPanel(diaAulaJPanel);
                        } catch (ServiceException ex) {
                            Logger.getLogger(GradeJFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });

                    contentJPanel.add(gradeJPanel);
                });
            }
        } catch (ServiceException ex) {
            Logger.getLogger(GradeJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        revalidate();
        repaint();

    }

    /**
     * This method is called from within the c6onstructor to initialize the
     * form. WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        containerJScrollPane = new javax.swing.JScrollPane();
        containerJPanel = new javax.swing.JPanel();
        contentJPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Grade de Horários Atual");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setFont(Styles.FONT_FAMILY);

        containerJPanel.setLayout(new java.awt.BorderLayout());

        contentJPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        contentJPanel.setLayout(new java.awt.GridLayout(1, 0));
        containerJPanel.add(contentJPanel, java.awt.BorderLayout.CENTER);

        containerJScrollPane.setViewportView(containerJPanel);

        getContentPane().add(containerJScrollPane, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void close() {
        // Esconder janela
        this.dispose();
        // Resetar a janela
        this.mainJFrame.setViewGradeJFrame(null);
        // Trazer a janela principal para a frente
        this.mainJFrame.toFront();
        this.mainJFrame.repaint();
        // Habilitar a janela novamente
        this.mainJFrame.setEnabled(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel containerJPanel;
    private javax.swing.JScrollPane containerJScrollPane;
    private javax.swing.JPanel contentJPanel;
    // End of variables declaration//GEN-END:variables
}
