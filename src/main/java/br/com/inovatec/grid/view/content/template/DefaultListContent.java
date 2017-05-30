/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.content.template;

import br.com.inovatec.grid.view.component.DataTableEntity;
import br.com.inovatec.grid.view.component.FooterOptionsLayout;
import br.com.inovatec.grid.view.component.HeaderLayout;
import br.com.inovatec.grid.view.component.factory.ButtonFactory;
import br.com.inovatec.grid.view.component.form.Button;
import br.com.inovatec.grid.view.component.listener.ButtonActionListener;
import br.com.inovatec.grid.view.layout.template.DefaultView;
import br.com.inovatec.grid.view.values.Colors;
import br.com.inovatec.grid.view.values.Dimens;
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
public abstract class DefaultListContent<T> extends DefaultContent {

    private final List<Button> options;
    private Button viewButton, filterButton, newButton, closeButton;
    private JPanel header, main, footer;
    private final ImageIcon headerIcon;
    
    public DefaultListContent(DefaultView container, ImageIcon headerIcon) {
        super(container, true);
        this.headerIcon = headerIcon;
        this.options = new ArrayList<>();
    }

    public abstract List<T> getData();
            
    public Button getNewButton() {
        return newButton;
    }

    public Button getViewButton() {
        return viewButton;
    }

    public Button getFilterButton() {
        return filterButton;
    }

    public Button getCloseButton() {
        return closeButton;
    }

    @Override
    public void initComponents() {
        super.initComponents();
        // Inicializar componentes especificos
        // Cabecalho
        this.header = new HeaderLayout(this.getContainer().getHeaderTitle(), this.getContainer().getHeaderTip(), this.headerIcon);
        // Conteudo
        this.main = new JPanel();
        this.main.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, Dimens.DEFAULT_PADDING, Dimens.DEFAULT_PADDING));
        this.main.setBackground(Colors.COLOR_MAIN);
        this.main.setFont(new java.awt.Font("Tahoma", 0, 12));
        this.main.setBorder(new EmptyBorder(Dimens.DEFAULT_MIN_PADDING, Dimens.DEFAULT_MIN_PADDING, Dimens.DEFAULT_MIN_PADDING, Dimens.DEFAULT_MIN_PADDING));
        // Adicionar as caracteristicas do Main informadas no conteudo herdeiro
        this.main.add(this.getDataTable());
        // Criar botoes
        this.createActionButtons();
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
    
    protected void createActionButtons() {
        // Acoes da Janela (Lado direito)
        this.closeButton = ButtonFactory.getInstance().getCloseButton(Colors.COLOR_MAIN, new ButtonActionListener() {
            @Override
            public void action() {
                getContainer().close();
            }
        });
        this.closeButton.setType(Button.ButtonType.OPTION_RIGHT);
        this.getOptions().add(this.closeButton);
        
        // Acoes da Janela (Lado esquerdo)
        // Filtrar
        this.filterButton = ButtonFactory.getInstance().getFilterButton(Colors.COLOR_MAIN, new ButtonActionListener() {
            @Override
            public void action() {
                filterAction();
            }
        });
        this.filterButton.setType(Button.ButtonType.OPTION_LEFT);
        this.getOptions().add(this.filterButton);
        // Visualizar
        this.viewButton = ButtonFactory.getInstance().getViewButton(Colors.COLOR_MAIN, new ButtonActionListener() {
            @Override
            public void action() {
                viewAction();
            }
        });
        this.viewButton.setType(Button.ButtonType.OPTION_LEFT);
        this.viewButton.setEnabled(false);
        this.getOptions().add(this.viewButton);
        // Novo
        this.newButton = ButtonFactory.getInstance().getCreateButton(Colors.COLOR_MAIN, new ButtonActionListener() {
            @Override
            public void action() {
                newAction();
            }
        });
        this.newButton.setType(Button.ButtonType.OPTION_LEFT);
        this.getOptions().add(this.newButton);
    }

    /**
     * Recarregar dados
     */
    public void refreshContent() {
        // Bloquear botao de visualizacao
        this.viewButton.setEnabled(false);
        // Preencher tabela
        this.getDataTable().getModel().setDados(this.getData());
        // Atualizar exibição da tabela
        this.getDataTable().refreshTable();
    }
    
    /**
     * Metodo responsavel por retornar a tabela de dados
     * 
     * @return 
     */
    protected abstract DataTableEntity getDataTable();
    
    /**
     * Acao do botao Salvar
     */
    public abstract void newAction();
    
    /**
     * Acao do botao editar
     */
    public abstract void viewAction();
    
    /**
     * Acao do botao filtrar
     */
    public abstract void filterAction();
    
}
