/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.component.main;

import br.com.inovatec.grid.entity.Aula;
import br.com.inovatec.grid.util.DateTimeUtils;
import br.com.inovatec.grid.view.component.form.util.GradeListener;
import br.com.inovatec.grid.view.values.Colors;
import br.com.inovatec.grid.view.values.Dimens;
import br.com.inovatec.grid.view.values.Icons;
import br.com.inovatec.grid.view.values.Strings;
import br.com.inovatec.grid.view.values.Styles;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 *
 * @author zrobe
 */
public class AulaJPanel extends javax.swing.JPanel {

    private Aula aula;
    private final boolean viewOnly;
    private final GradeListener gradeListener;
    private final JLabel horarioJLabel, statusJLabel, disciplinaJLabel, professorJLabel;

    /**
     * Creates new form AulaJPanel
     *
     * @param aula
     * @param viewOnly
     * @param gradeListener
     */
    public AulaJPanel(Aula aula, boolean viewOnly, GradeListener gradeListener) {
        this.aula = aula;
        this.viewOnly = viewOnly;
        this.gradeListener = gradeListener;
        // Incializar componentes
        initComponents();
        // Inicializar variaveis
        this.horarioJLabel = new JLabel();
        this.statusJLabel = new JLabel();
        this.disciplinaJLabel = new JLabel();
        this.professorJLabel = new JLabel();
        // Inicializar componente
        this.init();
        // Desenhar componentes
        this.draw();
    }

    public Aula getAula() {
        return aula;
    }

    public void setAula(Aula aula) {
        this.aula = aula;
    }

    private void init() {

        this.reset();

        if (!this.viewOnly) {
            this.deleteJLabel.setIcon(Icons.IC_DELETE);
            this.deleteJLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    deleteJLabel.setVisible(!viewOnly && aula.getDisciplina() != null);
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    if (gradeListener != null) {
                        gradeListener.action(AulaJPanel.this);
                    }
                    aula.setDisciplina(null);
                    aula.setProfessor(null);
                    draw();
                }
            });
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseExited(MouseEvent e) {
                    deleteJLabel.setVisible(false);
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    deleteJLabel.setVisible(!viewOnly && aula.getDisciplina() != null);
                }
            });
        }

        this.horarioJLabel.setFont(Styles.FONT_FAMILY_BOLD);
        this.horarioJLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.horarioJLabel.setForeground(Colors.COLOR_FONT);

        this.statusJLabel.setFont(Styles.FONT_FAMILY);
        this.statusJLabel.setForeground(Colors.COLOR_FONT);
        this.statusJLabel.setText(Strings.GRADE_HORARIOS_STATUS_VAGO);
        this.statusJLabel.setHorizontalAlignment(SwingConstants.CENTER);

        this.disciplinaJLabel.setFont(Styles.FONT_FAMILY);
        this.disciplinaJLabel.setForeground(Colors.COLOR_FONT);
        this.disciplinaJLabel.setText(Strings.GRADE_HORARIOS_STATUS_VAGO);
        this.disciplinaJLabel.setHorizontalAlignment(SwingConstants.CENTER);

        this.professorJLabel.setFont(Styles.FONT_FAMILY);
        this.professorJLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.professorJLabel.setForeground(Colors.COLOR_FONT);
    }

    /**
     * Adicionar horas de inicio e de fim da aula ao Horario
     *
     */
    public void showHorario() {
        if (this.aula.getHorario() != null) {
            this.horarioJLabel.setText(
                    DateTimeUtils.getMinimalFormattedTime(this.aula.getHorario().getInicio())
                    + " - "
                    + DateTimeUtils.getMinimalFormattedTime(this.aula.getHorario().getFim())
            );
            this.add(this.horarioJLabel);
        }
    }

    /**
     * Adicionar informacao do Status do Horario
     *
     */
    public void showStatusVago() {
        // Remover labels nao usados
        this.remove(this.disciplinaJLabel);
        this.remove(this.professorJLabel);
        this.add(this.statusJLabel);
    }

    /**
     * Adicionar informacao da Disciplina ao Horario
     *
     */
    public void showDisciplina() {
        if (this.aula.getDisciplina() != null) {
            // Remover labels nao usados
            this.remove(this.statusJLabel);
            // Modificar cor do label
            setBackground(Colors.COLOR_GRADE_DISCIPLINA_CENTER);
            // Modificar cor da fonte
            this.horarioJLabel.setForeground(Colors.COLOR_WHITE);
            this.professorJLabel.setForeground(Colors.COLOR_WHITE);
            this.disciplinaJLabel.setForeground(Colors.COLOR_WHITE);
            // Mostrar Disciplina
            this.disciplinaJLabel.setText(this.aula.getDisciplina().getNome());
            this.add(this.disciplinaJLabel);
        } else {
            setBackground(Colors.COLOR_AULA_CONTAINER);
            this.horarioJLabel.setForeground(Colors.COLOR_FONT);
            this.statusJLabel.setForeground(Colors.COLOR_FONT);
            this.disciplinaJLabel.setForeground(Colors.COLOR_FONT);
            this.professorJLabel.setForeground(Colors.COLOR_FONT);
        }
    }

    /**
     * Adicionar informacao do Professor a Exibição
     *
     */
    public void showProfessor() {
        if (this.aula.getProfessor() != null) {
            // Modificar labels
            this.professorJLabel.setText(this.aula.getProfessor().getNomeReduzido());
            // Modificar cor do label
            setBackground(Colors.COLOR_GRADE_AULA);
        } else {
            this.professorJLabel.setText(Strings.GRADE_HORARIOS_AULA_PROFESSOR_NULL);
        }
        this.add(this.professorJLabel);
    }

    /**
     * Atualizar o componente
     *
     */
    public void draw() {
        reset();

        this.remove(this.elementFooterJPanel);
        
        showHorario();
        showStatusVago();
        showDisciplina();
        showProfessor();
        
        this.add(this.elementFooterJPanel);
    }

    /**
     * Modificar as propriedades do painel
     *
     */
    public void change() {
        setBorder(new LineBorder(Colors.COLOR_GRADE_DISCIPLINA_LEFT, Dimens.AULA_BORDER_SIZE));
        this.deleteJLabel.setVisible(false);
    }

    /**
     * Resetar para os valores padrao do componente
     */
    public void reset() {
        setBorder(new EmptyBorder(Dimens.AULA_BORDER_SIZE, Dimens.AULA_BORDER_SIZE, Dimens.AULA_BORDER_SIZE, Dimens.AULA_BORDER_SIZE));
        this.deleteJLabel.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        deleteJPanel = new javax.swing.JPanel();
        deleteJLabel = new javax.swing.JLabel();
        elementFooterJPanel = new javax.swing.JPanel();

        setLayout(new java.awt.GridLayout(5, 1));

        deleteJPanel.setBackground(null);
        deleteJPanel.setMinimumSize(new java.awt.Dimension(100, 20));
        deleteJPanel.setPreferredSize(new java.awt.Dimension(100, 15));
        deleteJPanel.setLayout(new java.awt.BorderLayout());

        deleteJLabel.setMinimumSize(new java.awt.Dimension(15, 15));
        deleteJLabel.setPreferredSize(new java.awt.Dimension(15, 15));
        deleteJPanel.add(deleteJLabel, java.awt.BorderLayout.EAST);

        add(deleteJPanel);

        elementFooterJPanel.setBackground(null);
        elementFooterJPanel.setMinimumSize(new java.awt.Dimension(100, 20));
        elementFooterJPanel.setPreferredSize(new java.awt.Dimension(100, 15));
        elementFooterJPanel.setLayout(null);
        add(elementFooterJPanel);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel deleteJLabel;
    private javax.swing.JPanel deleteJPanel;
    private javax.swing.JPanel elementFooterJPanel;
    // End of variables declaration//GEN-END:variables
}
