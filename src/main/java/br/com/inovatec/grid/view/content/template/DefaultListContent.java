/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.content.template;

import br.com.inovatec.grid.entity.Entidade;
import br.com.inovatec.grid.service.exception.ServiceException;
import br.com.inovatec.grid.service.GenericService;
import br.com.inovatec.grid.view.component.DataTableEntity;
import br.com.inovatec.grid.view.component.FooterOptionsLayout;
import br.com.inovatec.grid.view.component.HeaderLayout;
import br.com.inovatec.grid.view.component.factory.ButtonFactory;
import br.com.inovatec.grid.view.component.form.Button;
import br.com.inovatec.grid.view.component.form.util.FilterListener;
import br.com.inovatec.grid.view.component.listener.ButtonActionListener;
import br.com.inovatec.grid.view.component.listener.DataTableEntityActionListener;
import br.com.inovatec.grid.view.layout.template.DefaultView;
import br.com.inovatec.grid.view.layout.template.FilterView;
import br.com.inovatec.grid.view.util.MessageFactory;
import br.com.inovatec.grid.view.values.Colors;
import br.com.inovatec.grid.view.values.Dimens;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Classe base para layouts de formulario
 *
 * @author zrobe
 * @param <T>
 */
public abstract class DefaultListContent<T extends Entidade> extends DefaultContent {

    private boolean filterSearch;
    private List<T> data;
    private final FilterView filterView;
    private final GenericService<T> service;

    private final List<Button> options;
    private Button viewButton, filterButton, newButton, closeButton;
    private JPanel header, main, footer;
    private final ImageIcon headerIcon;
    private final boolean full;

    public DefaultListContent(DefaultView container, ImageIcon headerIcon, GenericService<T> service, FilterView filterView, boolean full) {
        super(container, true);
        this.headerIcon = headerIcon;
        this.service = service;
        this.filterView = filterView;
        this.full = full;
        this.options = new ArrayList<>();
        this.fillData();
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

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

        if (this.full) {
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
    }

    /**
     * Recarregar dados
     */
    public void refreshContent() {
        // Bloquear botao de visualizacao
        this.viewButton.setEnabled(false);
        // Preencher conteudo
        this.fillData();
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
     * Popular dados da lista
     */
    protected void fillData() {
        try {
            setData(filterSearch ? service.findAll(filterView.getCriteriaParams()) : this.service.findAll());
        } catch (ServiceException ex) {
            this.showInternalErrorMessage(ex);
        }
    }

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
    public void filterAction() {
        this.filterView.setFilterListener(new FilterListener() {
            @Override
            public void filter() {
                filterSearch = true;
            }

            @Override
            public void reset() {
                filterSearch = false;
            }
        });
        this.filterView.display();
    }

    /**
     * Obter o Listener padrao
     *
     * @return
     */
    protected DataTableEntityActionListener<T> getDataTableEntityActionListener() {
        return new DataTableEntityActionListener<T>() {

            @Override
            public void mainAction(T t, int index) {
                viewAction();
            }

            @Override
            public void clickRowAction() {
                getViewButton().setEnabled(true);
            }

            @Override
            public boolean deleteAction(T t) {
                try {
                    service.remove(t);
                    return true;
                } catch (ServiceException ex) {
                    Logger.getLogger(DefaultListContent.class.getName()).log(Level.SEVERE, null, ex);
                    MessageFactory.showErrorMessage(null, ex.getMessage());
                }
                return false;
            }

        };
    }

}
