/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.component.main;

import br.com.inovatec.grid.entity.DisciplinaTurma;
import br.com.inovatec.grid.util.object.DisciplinaTurmaAulas;
import br.com.inovatec.grid.view.values.Colors;
import br.com.inovatec.grid.view.values.Dimens;
import br.com.inovatec.grid.view.values.Styles;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author zrobe
 */
public class DisciplinaJLabel extends javax.swing.JLabel {

    private final DisciplinaTurmaAulas disciplinaTurmaAulas;

    public DisciplinaJLabel(DisciplinaTurma disciplinaTurma, int totalAlocado) {
        super();
        //this.disciplinaTurmaAulas = new DisciplinaTurmaAulas(disciplinaTurma, disciplinaTurma.getAulasSemanaTotal() - disciplinaTurma.getAulas().size());
        this.disciplinaTurmaAulas = new DisciplinaTurmaAulas(disciplinaTurma, disciplinaTurma.getAulasSemanaTotal() - totalAlocado);
        config();
    }

    /**
     * Configurar classe
     */
    private void config() {
        // Configurar o label de exibição
        setFont(Styles.FONT_FAMILY_BOLD);
        setForeground(Colors.COLOR_WHITE);
        setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        setBackground(Colors.COLOR_GRADE_DISCIPLINA_LEFT);
        setBorder(new EmptyBorder(
                Dimens.DEFAULT_PADDING,
                Dimens.DEFAULT_PADDING,
                Dimens.DEFAULT_PADDING,
                Dimens.DEFAULT_PADDING)
        );
        setOpaque(true);
        // Modificar o texto
        setText();
        // Modificar a cor
        if (this.disciplinaTurmaAulas.getTotalAulas() == 0) {
            setBackground(Colors.COLOR_GREY);
        }
    }
    
    /**
     * Modificar o texto do label
     */
    private void setText() {
        setText(this.disciplinaTurmaAulas.getDisciplinaTurma().getDisciplina().getNome() + "(" + this.disciplinaTurmaAulas.getTotalAulas() + ")");
    }

    /**
     * Incrementar a quantidade da disciplina e atualizar o componente
     */
    public void increment() {
        // Verificar se ja eh igual a zero
        this.disciplinaTurmaAulas.incrementTotalAulas();
        setText();
        setBackground(Colors.COLOR_GRADE_DISCIPLINA_LEFT);
    }
    
    /**
     * Decrementar a quantidade da disciplina e atualizar o componente
     */
    public void decrement() {
        this.disciplinaTurmaAulas.decrementTotalAulas();
        setText();
        if (this.disciplinaTurmaAulas.getTotalAulas() == 0) {
            setBackground(Colors.COLOR_GREY);
        }
    }

    public DisciplinaTurmaAulas getDisciplinaTurmaAulas() {
        return disciplinaTurmaAulas;
    }

    /**
     * Verificar se o componente esta liberado
     * 
     * @return 
     */
    public boolean isReleased() {
        return this.disciplinaTurmaAulas.getTotalAulas() > 0;
    }
    
}
