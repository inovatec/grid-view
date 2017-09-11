/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.layout.template;

import br.com.inovatec.grid.view.content.template.DefaultContent;
import br.com.inovatec.grid.view.values.Colors;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;
import br.com.inovatec.grid.view.contract.DialogView;
import br.com.inovatec.grid.view.contract.FrameView;
import br.com.inovatec.grid.view.contract.View;
import br.com.inovatec.grid.view.values.Icons;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author zrobe
 */
public abstract class DefaultView implements DialogView {

    private boolean constructed = false;
    private final View requester;
    private final JDialog dialog;
    private final int width, height;
    private final String headerTitle, headerTip;

    public DefaultView(DialogView requester, String title, String headerTitle, String headerTip, int width, int height) {
        this.dialog = new JDialog(requester.getDialog(), title, true);
        this.requester = requester;
        this.width = width;
        this.height = height;
        this.headerTitle = headerTitle;
        this.headerTip = headerTip;
        // Inicializar configuracoes padrao
        this.preRenderView();
    }

    public DefaultView(FrameView requester, String title, String headerTitle, String headerTip, int width, int height) {
        this.dialog = new JDialog(requester.getFrame(), title, true);
        this.requester = requester;
        this.width = width;
        this.height = height;
        this.headerTitle = headerTitle;
        this.headerTip = headerTip;
        // Inicializar configuracoes padrao
        this.preRenderView();
    }

    public View getRequester() {
        return requester;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    @Override
    public JDialog getDialog() {
        return this.dialog;
    }

    public String getHeaderTitle() {
        return this.headerTitle;
    }

    public String getHeaderTip() {
        return this.headerTip;
    }

    /**
     * Metodo a ser implementado pela classe filha que retorna o conteudo da
     * view
     *
     * @return
     */
    public abstract DefaultContent getContent();

    /**
     * Metodo com configuracoes anteriores a construcao da view
     */
    public void preRenderView() {
        // Configuracoes basicas
        this.dialog.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        this.dialog.setBackground(Colors.COLOR_MAIN);
        this.dialog.setForeground(Colors.COLOR_FONT);
        this.dialog.setResizable(false);
        this.dialog.setIconImages(Icons.getAppIcons());
        // Dimensoes da janela
        this.dialog.setPreferredSize(new Dimension(this.width, this.height));
        // Adicionar Listener responsavel por eventos ao fechar a pagina
        this.dialog.addWindowListener(new ParentWindowAdapter());
        // Key Listener
        this.dialog.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    close();
                }
            }
        });
    }

    /**
     * Metodo com configuracoes posteriores a construcao da view
     */
    public void postRenderView() {
        // Construir o conteudo
        this.dialog.add(this.getContent().build());
        // Gerar o pack
        this.dialog.pack();
        // Centralizar dialog
        this.dialog.setLocationRelativeTo(null);
    }

    /**
     * Metodo para exibir a view
     */
    @Override
    public void display() {
        // Verificar se ja foi construida
        if (!this.constructed) {
            // Configuracoes apos a construcao da view
            this.postRenderView();
            // configurar que ja foi construida
            this.constructed = true;
        }
        // Exibir a Dialog
        this.dialog.setVisible(true);
    }

    /**
     * Realizar a atualizacao da janela que efetuou a chamada
     */
    public abstract void refreshViewRequester();

    /**
     * Classe para o gerenciamento de eventos da dialog
     */
    private class ParentWindowAdapter extends WindowAdapter {

        @Override
        public void windowClosing(WindowEvent e) {
            // Fechar
            close();
        }

        @Override
        public void windowClosed(WindowEvent e) {
            // Atualizar a view requisitante
            refreshViewRequester();
        }

    }

    @Override
    public void close() {
        this.dialog.dispose();

    }

}
