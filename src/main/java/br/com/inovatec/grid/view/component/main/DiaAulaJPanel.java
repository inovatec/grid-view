/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.component.main;

import br.com.inovatec.grid.entity.Aula;
import br.com.inovatec.grid.entity.DiaAula;
import br.com.inovatec.grid.entity.Horario;
import br.com.inovatec.grid.view.values.Colors;
import br.com.inovatec.grid.view.values.Styles;
import java.awt.event.MouseListener;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author zrobe
 */
public class DiaAulaJPanel extends JPanel {
    
    private final DiaAula diaAula;
    private final List<Aula> aulas;
    private final JPanel aulasContainerJPanel;

    public DiaAulaJPanel(Integer totalAulas, DiaAula diaAula, List<Aula> aulas, List<Horario> horarios, MouseListener ml) {
        this.diaAula = diaAula;
        this.aulas = aulas;
        this.aulasContainerJPanel = new javax.swing.JPanel();
        
        setBackground(null);
        setLayout(new java.awt.BorderLayout());
        
        JLabel diaAulaTitleJLabel = new JLabel(
                diaAula.getDiaDaSemana().getDisplayName(TextStyle.SHORT, Locale.getDefault())
        );
        diaAulaTitleJLabel.setBackground(Colors.COLOR_MENU_BAR);
        diaAulaTitleJLabel.setFont(Styles.FONT_FAMILY);
        diaAulaTitleJLabel.setForeground(Colors.COLOR_WHITE);
        diaAulaTitleJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        diaAulaTitleJLabel.setOpaque(true);
        diaAulaTitleJLabel.setPreferredSize(new java.awt.Dimension(42, 30));
        add(diaAulaTitleJLabel, java.awt.BorderLayout.PAGE_START);

        this.aulasContainerJPanel.setBackground(null);
        this.aulasContainerJPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 0, 0, 0));
        this.aulasContainerJPanel.setLayout(new java.awt.GridLayout(totalAulas, 1, 5, 5));
        
        // Iterar pela lista de Aulas
        horarios.forEach(h -> {
            // Aula da iteracao
            Aula aula = null;
            // Obter as aulas que tem o horario da iteracao. Idealmente, deve haver apenas uma aula com esses criterios
            List<Aula> aulasResultList = aulas.stream().filter(x -> x.getHorario().equals(h)).collect(Collectors.toList());
            // Verificar se ha resultado
            if (aulasResultList.size() > 0) {
                aula = aulasResultList.get(0);
            }
            // Criar painel com as informacoes obtidas
            AulaJPanel aulaJPanel = new AulaJPanel(aula, h);
            aulaJPanel.addMouseListener(ml);
            // Adicionar painel da aula
            aulasContainerJPanel.add(aulaJPanel);
        });
        
        add(this.aulasContainerJPanel, java.awt.BorderLayout.CENTER);
    }

    public JPanel getAulasContainerJPanel() {
        return aulasContainerJPanel;
    }

    public DiaAula getDiaAula() {
        return diaAula;
    }

    public List<Aula> getAulas() {
        return aulas;
    }
    
}
