/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.component.main;

import br.com.inovatec.grid.entity.Aula;
import br.com.inovatec.grid.entity.DisciplinaTurma;
import br.com.inovatec.grid.entity.Horario;
import br.com.inovatec.grid.entity.Professor;
import br.com.inovatec.grid.util.DateTimeUtils;
import br.com.inovatec.grid.view.values.Colors;
import br.com.inovatec.grid.view.values.Dimens;
import br.com.inovatec.grid.view.values.Strings;
import br.com.inovatec.grid.view.values.Styles;
import java.awt.Color;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 *
 * @author zrobe
 */
public class AulaJPanel extends javax.swing.JPanel {

    private Horario horario;
    private Aula aula;
    private final JLabel horarioJLabel, statusJLabel, disciplinaJLabel, professorJLabel;

    /**
     * Creates new form AulaJPanel
     * @param aula
     * @param horario
     */
    public AulaJPanel(Aula aula, Horario horario) {
        // Inicializar variaveis
        this.aula = aula != null ? aula : new Aula();
        this.horario = horario;
        // Incializar componentes
        initComponents();
        // Inicializar variaveis
        this.horarioJLabel = new JLabel();
        this.statusJLabel = new JLabel();
        this.disciplinaJLabel = new JLabel();
        this.professorJLabel = new JLabel();
        // Inicializar componente
        this.init();
    }

    public Aula getAula() {
        return aula;
    }

    public void setAula(Aula aula) {
        this.aula = aula;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    private void init() {

        setBackground(Colors.COLOR_AULA_CONTAINER);
        setBorder(new EmptyBorder(Dimens.AULA_BORDER_SIZE, Dimens.AULA_BORDER_SIZE, Dimens.AULA_BORDER_SIZE, Dimens.AULA_BORDER_SIZE));

        // Borda vazia dos componentes
        javax.swing.border.Border border = javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5);

        this.horarioJLabel.setFont(Styles.FONT_FAMILY_BOLD);
        this.horarioJLabel.setForeground(Colors.COLOR_FONT);
        this.horarioJLabel.setBorder(border);

        this.statusJLabel.setFont(Styles.FONT_FAMILY);
        this.statusJLabel.setForeground(Colors.COLOR_FONT);
        this.statusJLabel.setText(Strings.GRADE_HORARIOS_STATUS_VAGO);
        this.statusJLabel.setBorder(border);

        this.disciplinaJLabel.setFont(Styles.FONT_FAMILY);
        this.disciplinaJLabel.setForeground(Colors.COLOR_FONT);
        this.disciplinaJLabel.setText(Strings.GRADE_HORARIOS_STATUS_VAGO);
        this.disciplinaJLabel.setBorder(border);

        this.professorJLabel.setFont(Styles.FONT_FAMILY);
        this.professorJLabel.setForeground(Colors.COLOR_FONT);
        this.professorJLabel.setBorder(border);
        
        // Adicionar horario
        addHorario(this.horario);
        
        // Verificar se tem disciplina alocada
        if (this.aula.getDisciplinaTurma() != null) {
            addDisciplinaTurma(this.aula.getDisciplinaTurma());
        } else {
            addStatusVago();
        }
    }

    /**
     * Adicionar horas de inicio e de fim da aula ao Horario
     *
     * @param horario
     */
    public void addHorario(Horario horario) {
        this.aula.setHorario(horario);
        this.horarioJLabel.setText(
                DateTimeUtils.getMinimalFormattedTime(horario.getInicio())
                + " - "
                + DateTimeUtils.getMinimalFormattedTime(horario.getFim())
        );
        add(this.horarioJLabel, new java.awt.GridBagConstraints());
    }

    /**
     * Adicionar informacao do Status do Horario
     *
     */
    public void addStatusVago() {
        // Remover labels nao usados
        this.remove(this.disciplinaJLabel);
        this.remove(this.professorJLabel);

        GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        this.add(this.statusJLabel, gridBagConstraints);
    }

    /**
     * Adicionar informacao da Disciplina ao Horario
     *
     * @param disciplinaTurma
     */
    public void addDisciplinaTurma(DisciplinaTurma disciplinaTurma) {
        // Adicionar DisciplinaTurma a Aula
        this.aula.setDisciplinaTurma(disciplinaTurma);
        // Remover labels nao usados
        this.remove(this.statusJLabel);

        this.disciplinaJLabel.setText(disciplinaTurma.getDisciplina().getNome());
        GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        this.add(this.disciplinaJLabel, gridBagConstraints);
        
        this.addProfessor(this.aula.getProfessor());
    }

    /**
     * Adicionar informacao do Professor ao Horario
     *
     * @param professor
     */
    public void addProfessor(Professor professor) {
        // Adicionar DisciplinaTurma a Aula
        this.aula.setProfessor(professor);
        // Modificar labels
        this.professorJLabel.setText(professor != null ? professor.getShortNome() : Strings.GRADE_HORARIOS_AULA_PROFESSOR_NULL);
        GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        this.add(this.professorJLabel, gridBagConstraints);
    }

    /**
     * Atualizar o componente
     *
     */
    public void update() {
        reset();
        
        this.horarioJLabel.setForeground(Colors.COLOR_WHITE);
        this.professorJLabel.setForeground(Colors.COLOR_WHITE);
        this.disciplinaJLabel.setForeground(Colors.COLOR_WHITE);
        
        if (this.aula.getProfessor() != null) {
            setBackground(Colors.COLOR_GREEN);
        } else if (this.aula.getDisciplinaTurma() != null) {
            setBackground(Colors.COLOR_ORANGE);
        }
        
    }
    
    /**
     * Modificar as propriedades do painel
     *
     */
    public void change() {
        setBorder(new LineBorder(Colors.COLOR_GREEN, Dimens.AULA_BORDER_SIZE));
    }
    
    /**
     * Resetar para os valores padrao do componente
     */
    public void reset() {
        setBorder(new EmptyBorder(Dimens.AULA_BORDER_SIZE, Dimens.AULA_BORDER_SIZE, Dimens.AULA_BORDER_SIZE, Dimens.AULA_BORDER_SIZE));
    }
    
    /**
     * Verifica se o componente esta liberado para adicionar DisciplinaTurma
     * 
     * @return 
     */
    public boolean canAddDisciplinaTurma() {
        return this.aula != null && this.aula.getDisciplinaTurma() == null;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.GridBagLayout());
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
