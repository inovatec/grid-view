/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.content.template;

import br.com.inovatec.grid.view.component.FooterOptionsLayout;
import br.com.inovatec.grid.view.component.HeaderLayout;
import br.com.inovatec.grid.view.component.form.Button;
import br.com.inovatec.grid.view.content.exception.FormException;
import br.com.inovatec.grid.view.contract.Field;
import br.com.inovatec.grid.view.layout.template.DefaultView;
import br.com.inovatec.grid.view.values.Colors;
import br.com.inovatec.grid.view.values.Dimens;
import java.awt.Component;
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
public abstract class DefaultFormContent<T> extends DefaultContent {

    private final List<Button> options;
    private JPanel header, main, footer;
    private final ImageIcon headerIcon;
    
    public DefaultFormContent(DefaultView container, ImageIcon headerIcon, boolean showHeader) {
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
        this.main.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, Dimens.DEFAULT_PADDING, Dimens.DEFAULT_PADDING));
        this.main.setBackground(Colors.COLOR_MAIN);
        this.main.setFont(new java.awt.Font("Tahoma", 0, 12));
        this.main.setBorder(new EmptyBorder(Dimens.DEFAULT_MIN_PADDING, Dimens.DEFAULT_MIN_PADDING, Dimens.DEFAULT_MIN_PADDING, Dimens.DEFAULT_MIN_PADDING));
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
     * Habilitar os campos do form
     * 
     * @param enable 
     */
    public void enableFields(boolean enable) {
        for (Component c : this.main.getComponents()) {
            if (c instanceof Field) {
                c.setEnabled(enable);
            }
        }
    }
    
    /**
     * Metodo responsavel por preencher o conteudo principal da tela
     * 
     */
    protected abstract void buildMain();
    
    /**
     * Retornar o objeto preenchido pelo form
     * 
     * @return 
     * @throws br.com.inovatec.grid.view.content.exception.FormException 
     */
    public abstract T getFilledObject() throws FormException;
    
    /**
     * Preencher o formulario de acordo com o objeto passado
     * 
     * @param object
     */
    public abstract void fillFieldsByObject(T object);
    
}
