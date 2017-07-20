/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.content.template;

import br.com.inovatec.grid.view.component.FooterOptionsLayout;
import br.com.inovatec.grid.view.component.HeaderLayout;
import br.com.inovatec.grid.view.component.form.Button;
import br.com.inovatec.grid.view.layout.template.DefaultView;
import br.com.inovatec.grid.view.values.Colors;
import br.com.inovatec.grid.view.values.Dimens;
import br.com.inovatec.grid.view.values.Styles;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Classe base para layouts de formulario
 * 
 * @author zrobe
 * @param <T>
 */
public abstract class DefaultStaticContent<T> extends DefaultContent {

    private final List<Button> options;
    private JPanel header, main, footer;
    private final ImageIcon headerIcon;
    
    public DefaultStaticContent(DefaultView container, ImageIcon headerIcon, boolean showHeader) {
        super(container, showHeader);
        this.options = new ArrayList<>();
        this.headerIcon = headerIcon;
    }

    @Override
    public void initComponents() {
        super.initComponents();
        // Inicializar componentes especificos
        // Cabecalho
        if (isShowHeader()) {
            this.header = new HeaderLayout(this.getContainer().getHeaderTitle(), this.getContainer().getHeaderTip(), this.headerIcon);
        }
        // Conteudo
        this.main = new JPanel();
        this.main.setLayout(new BorderLayout());
        this.main.setBackground(Colors.COLOR_MAIN);
        this.main.setFont(Styles.FONT_FAMILY);
        this.main.setBorder(new EmptyBorder(0, Dimens.DEFAULT_PADDING, 0, Dimens.DEFAULT_PADDING));
        // Adicionar as caracteristicas do Main informadas no conteudo herdeiro
        this.buildMain();
        // Rodape
        this.footer = new FooterOptionsLayout(this.options);
    }
    
    @Override
    public JPanel getHeader() {
        return this.header;
    }

    @Override
    public JPanel getMain() {
        return this.main;
    }

    @Override
    public JPanel getFooter() {
        return this.footer;
    }

    public List<Button> getOptions() {
        return options;
    }
    
    /**
     * Metodo responsavel por preencher o conteudo principal da tela
     * 
     */
    protected abstract void buildMain();
    
}
