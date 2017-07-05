/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view;

import br.com.inovatec.grid.entity.Aula;
import br.com.inovatec.grid.entity.Professor;
import br.com.inovatec.grid.provider.ServiceProvider;
import br.com.inovatec.grid.service.exception.ServiceException;
import br.com.inovatec.grid.view.component.form.MultilineLabel;
import br.com.inovatec.grid.view.component.main.ProfessorCompetenteJLabel;
import br.com.inovatec.grid.view.contract.DialogView;
import br.com.inovatec.grid.view.values.Colors;
import br.com.inovatec.grid.view.values.Dimens;
import br.com.inovatec.grid.view.values.Strings;
import br.com.inovatec.grid.view.values.Styles;
import java.awt.GridBagConstraints;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.JDialog;

/**
 *
 * @author zrobe
 */
public class ProfessoresCompetentesJDialog extends javax.swing.JDialog implements DialogView {
    
    private final Aula aula;
    private final List<Professor> todosProfessores;

    /**
     * Creates new form SelectProfessorJDialog
     *
     * @param parent
     * @param aula
     * @param todosProfessores
     */
    public ProfessoresCompetentesJDialog(java.awt.Frame parent, Aula aula, List<Professor> todosProfessores) {
        super(parent, true);
        this.aula = aula;
        this.todosProfessores = todosProfessores;
        initComponents();
        init();
    }

    /**
     * Inicializacao personalizada
     */
    private void init() {
        // Titulo da janela
        this.titleJLabel.setText(Strings.getReplacedString(Strings.GRADE_HORARIOS_PROFESSORES_COMPETENTES_TITLE, this.aula.getDisciplinaTurma().getDisciplina().getNome()));
        final int frameWidth = 480;
        // Tamanho inicial: tamanho do titulo
        int height = 85;
        try {
            // Buscar Professores competentes para a disciplina passada
            List<Professor> professores = this.todosProfessores.stream()
                    .filter(p -> p.getDisciplinas().contains(this.aula.getDisciplinaTurma().getDisciplina()))
                    .collect(Collectors.toList());
            // Verificar se foram resgatados Professores
            if (!professores.isEmpty()) {
                // Modificar o layout do container para comportar os professores
                this.professoresJPanel.setLayout(new java.awt.GridLayout(
                        professores.size(),
                        1,
                        Dimens.DEFAULT_MIN_PADDING,
                        Dimens.DEFAULT_MIN_PADDING
                ));
                // Mostrar os Professores
                for (Professor p : professores) {
                    // Medida das linhas de professores mais a diferença entre as linas
                    height += Dimens.PROFESSORES_COMPETENTES_LINE_HEIGHT;
                    // Professor
                    ProfessorCompetenteJLabel pcjl = new ProfessorCompetenteJLabel(p);
                    pcjl.addMouseListener(new java.awt.event.MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            close();
                        }
                    });
                    // Verificar a disponibilidade do Professor
                    if (!ServiceProvider.getInstance().getProfessorService().isFreeFor(p, this.aula.getHorario())) {
                        pcjl.block();
                    }
                    this.professoresJPanel.add(pcjl);
                }
            } else {
                // Tamanho do conteudo adicional
                height += Dimens.PROFESSORES_COMPETENTES_LINE_HEIGHT;
                // Modificar o layout do container para comportar a mensagem de aviso
                this.professoresJPanel.setLayout(new java.awt.GridLayout(1, 1, 0, 0));
                // Adicionar aviso
                GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
                gridBagConstraints.gridx = 0;
                gridBagConstraints.gridy = 0;
                this.professoresJPanel.add(new MultilineLabel(Strings.GRADE_HORARIOS_PROFESSORES_COMPETENTES_EMPTY), gridBagConstraints);
            }
            
            revalidate();
            repaint();

            // OBS: 50 representa o tamanho do titulo (30) mais a borda vazia do painel pai (10 * 2)
            setSize(frameWidth, height);
            setLocationRelativeTo(null);
            
        } catch (ServiceException ex) {
            Logger.getLogger(ProfessoresCompetentesJDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    /**
     * Evento do professor selecionado
     */
    private void onClick() {
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titleJLabel = new javax.swing.JLabel();
        professoresJPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Professores Habilitados");
        setBackground(Colors.COLOR_MAIN);
        setFont(Styles.FONT_FAMILY);
        setForeground(Colors.COLOR_FONT);
        setResizable(false);

        titleJLabel.setBackground(Colors.COLOR_MAIN);
        titleJLabel.setFont(Styles.FONT_FAMILY_BOLD);
        titleJLabel.setForeground(Colors.COLOR_FONT);
        titleJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleJLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        titleJLabel.setMaximumSize(new java.awt.Dimension(20, 70));
        titleJLabel.setMinimumSize(new java.awt.Dimension(20, 40));
        titleJLabel.setOpaque(true);
        titleJLabel.setPreferredSize(new java.awt.Dimension(103, 40));
        getContentPane().add(titleJLabel, java.awt.BorderLayout.PAGE_START);

        professoresJPanel.setBackground(Colors.COLOR_MAIN);
        professoresJPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 10, 10, 10));
        professoresJPanel.setLayout(null);
        getContentPane().add(professoresJPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel professoresJPanel;
    private javax.swing.JLabel titleJLabel;
    // End of variables declaration//GEN-END:variables

    @Override
    public JDialog getDialog() {
        return this;
    }
    
    @Override
    public void refresh() {
    }
    
    @Override
    public void display() {
        this.setVisible(true);
    }
    
    @Override
    public void close() {
        this.dispose();
    }
}
