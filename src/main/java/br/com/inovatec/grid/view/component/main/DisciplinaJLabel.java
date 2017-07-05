/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.component.main;

import br.com.inovatec.grid.entity.DisciplinaTurma;
import br.com.inovatec.grid.view.values.Colors;
import br.com.inovatec.grid.view.values.Dimens;
import br.com.inovatec.grid.view.values.Styles;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author zrobe
 */
public class DisciplinaJLabel extends javax.swing.JLabel {

    private final DisciplinaTurma disciplinaTurma;
    private int qtde;

    public DisciplinaJLabel(DisciplinaTurma disciplinaTurma) {
        super();
        this.disciplinaTurma = disciplinaTurma;
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
        setBackground(Colors.COLOR_GREEN);
        setBorder(new EmptyBorder(
                Dimens.DEFAULT_PADDING,
                Dimens.DEFAULT_PADDING,
                Dimens.DEFAULT_PADDING,
                Dimens.DEFAULT_PADDING)
        );
        setOpaque(true);
        // Quantidade da Disciplina na semana
        this.qtde = this.disciplinaTurma.getAulasSemanaTotal() - this.disciplinaTurma.getAulas().size();
        // Modificar o texto
        setText();
    }
    
    /**
     * Modificar o texto do label
     */
    private void setText() {
        setText(this.disciplinaTurma.getDisciplina().getNome() + "(" + this.qtde + ")");
    }

    /**
     * Incrementar a quantidade da disciplina e atualizar o componente
     */
    public void increment() {
        // Verificar se ja eh igual a zero
        ++this.qtde;
        setText();
        setBackground(Colors.COLOR_GREEN);
    }
    
    /**
     * Decrementar a quantidade da disciplina e atualizar o componente
     */
    public void decrement() {
        // Verificar se ja eh igual a zero
        if (this.qtde > 0) {
            --this.qtde;
        }
        
        setText();
        
        if (this.qtde == 0) {
            setBackground(Colors.COLOR_GREY);
        }
    }

    public DisciplinaTurma getDisciplinaTurma() {
        return disciplinaTurma;
    }

    public int getQtde() {
        return qtde;
    }

    /**
     * Verificar se o componente esta liberado
     * 
     * @return 
     */
    public boolean isReleased() {
        return this.qtde > 0;
    }
    
}
