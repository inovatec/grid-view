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
import br.com.inovatec.grid.view.layout.SobreView;
import br.com.inovatec.grid.view.values.Colors;
import br.com.inovatec.grid.view.values.Icons;
import br.com.inovatec.grid.view.values.Styles;
import br.com.inovatec.grid.view.values.Content;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 * @author zrobe
 */
public class SobreContent extends DefaultStaticContent {

    private Button okButton;

    public SobreContent(SobreView container) {
        super(container, Icons.IC_GRID, true);
        this.createActionButtons();
    }

    @Override
    protected void buildMain() {
        // Criar novo conteudo
        JLabel label = new JLabel(Content.SOBRE);
        label.setFont(Styles.FONT_FAMILY);
        label.setAlignmentY(Component.TOP_ALIGNMENT);
        label.setVerticalTextPosition(SwingConstants.TOP);
        label.setVerticalAlignment(SwingConstants.TOP);
        // Colocar o novo conteudo
        this.getMain().add(label);
        // Redesenhar o painel
        this.getMain().repaint();
        this.getMain().revalidate();
    }

    /**
     * Adicionar os botoes de acao da dialog
     */
    private void createActionButtons() {        
        // Acoes da Janela (Lado direito)
        this.okButton = ButtonFactory.getInstance().getOkButton(Colors.COLOR_MAIN, new ButtonActionListener() {
            @Override
            public void action() {
                getContainer().close();
            }
        });
        okButton.setType(Button.ButtonType.OPTION_RIGHT);
        this.getOptions().add(okButton);
    }

}
