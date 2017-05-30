/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.content.template;

import br.com.inovatec.grid.view.layout.template.DefaultView;
import br.com.inovatec.grid.view.util.MessageFactory;
import br.com.inovatec.grid.view.values.Colors;
import br.com.inovatec.grid.view.values.Strings;
import br.com.inovatec.grid.view.values.Styles;
import java.awt.BorderLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author zrobe
 */
public abstract class DefaultContent extends JPanel {

    private final boolean showHeader;
    private final DefaultView container;

    public DefaultContent(DefaultView container, boolean showHeader) {
        this.container = container;
        this.showHeader = showHeader;
    }

    @Override
    public int getWidth() {
        return this.container.getWidth();
    }

    public DefaultView getContainer() {
        return container;
    }

    /**
     * Metodo com as configuracoes basicas do Layout Durante a sobrescrita da
     * classe filha, o super devera ser invocado
     */
    public void initComponents() {
        this.setLayout(new BorderLayout());
        this.setBackground(Colors.COLOR_MAIN);
        this.setForeground(Colors.COLOR_FONT);
        this.setFont(Styles.FONT_FAMILY);
    }

    public abstract JPanel getHeader();

    public abstract JPanel getMain();

    public abstract JPanel getFooter();

    /**
     * Constuir painel com conteudo
     *
     * @return
     */
    public DefaultContent build() {
        // Inicializar componentes
        this.initComponents();
        // Adicionar componentes
        if (this.showHeader) {
            this.add(this.getHeader(), java.awt.BorderLayout.PAGE_START);
        }
        this.add(this.getMain(), java.awt.BorderLayout.CENTER);
        this.add(this.getFooter(), java.awt.BorderLayout.PAGE_END);
        // Retornar a propria instancia
        return this;
    }
    
    /**
     * Exibir mensagem de erro padrao
     * 
     * @param ex 
     */
    protected void showInternalErrorMessage(Exception ex) {
        // Log de erro
        Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        // Exibir mensagem generica
        MessageFactory.showErrorMessage(this, Strings.INTERNAL_ERROR_MESSAGE);
    }

    /**
     * Permitir a exibicao do cabecalho
     * 
     * @return 
     */
    public boolean isShowHeader() {
        return showHeader;
    }

}
