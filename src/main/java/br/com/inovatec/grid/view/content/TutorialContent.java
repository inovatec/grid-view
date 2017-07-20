/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.content;

import br.com.inovatec.grid.view.component.factory.ButtonFactory;
import br.com.inovatec.grid.view.component.form.Button;
import br.com.inovatec.grid.view.component.listener.ButtonActionListener;
import br.com.inovatec.grid.view.content.template.DefaultStaticContent;
import br.com.inovatec.grid.view.layout.TutorialView;
import br.com.inovatec.grid.view.values.Colors;
import br.com.inovatec.grid.view.values.Dimens;
import br.com.inovatec.grid.view.values.Icons;
import br.com.inovatec.grid.view.values.Styles;
import br.com.inovatec.grid.view.values.Content;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author zrobe
 */
public class TutorialContent extends DefaultStaticContent {

    private static final int INITIAL_STEPS = 1;
    private static final int MAX_STEPS = Content.TUTORIALS.length;

    private int step = INITIAL_STEPS;

    private Button nextButton, prevButton, closeButton;

    public TutorialContent(TutorialView container) {
        super(container, Icons.IC_INFO, true);
        this.createActionButtons();
    }

    @Override
    protected void buildMain() {
        this.nextButton.setEnabled(this.step != MAX_STEPS);
        this.prevButton.setEnabled(this.step > INITIAL_STEPS);
        // Remover o conteudo anterior
        this.getMain().removeAll();
        // Criar novo conteudo
        JLabel label = new JLabel(Content.TUTORIALS[this.step - 1]);
        label.setFont(Styles.FONT_FAMILY);
        label.setAlignmentY(Component.TOP_ALIGNMENT);
        label.setVerticalTextPosition(SwingConstants.TOP);
        label.setVerticalAlignment(SwingConstants.TOP);
        // Colocar o novo conteudo
        this.getMain().add(label);
        
        this.getMain().repaint();
        this.getMain().revalidate();
    }

    /**
     * Adicionar os botoes de acao da dialog
     */
    private void createActionButtons() {
        // Acoes da Janela (Lado esquerdo)

        this.prevButton = ButtonFactory.getInstance().getDefaultFormButton("Anterior", Colors.COLOR_MAIN, new ButtonActionListener() {
            @Override
            public void action() {
                step--;
                buildMain();
            }
        });
        this.prevButton.setType(Button.ButtonType.OPTION_RIGHT);
        this.getOptions().add(this.prevButton);

        this.nextButton = ButtonFactory.getInstance().getDefaultFormButton("Próximo", Colors.COLOR_MAIN, new ButtonActionListener() {
            @Override
            public void action() {
                step++;
                buildMain();
            }
        });
        this.nextButton.setType(Button.ButtonType.OPTION_RIGHT);
        this.getOptions().add(this.nextButton);
        
        // Acoes da Janela (Lado direito)
        this.closeButton = ButtonFactory.getInstance().getCloseButton(Colors.COLOR_MAIN, new ButtonActionListener() {
            @Override
            public void action() {
                getContainer().close();
            }
        });
        closeButton.setType(Button.ButtonType.OPTION_RIGHT);
        this.getOptions().add(closeButton);
    }

}
