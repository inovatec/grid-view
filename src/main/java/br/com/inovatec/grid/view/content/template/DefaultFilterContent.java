/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.content.template;

import br.com.inovatec.grid.view.component.factory.ButtonFactory;
import br.com.inovatec.grid.view.component.form.Button;
import br.com.inovatec.grid.view.component.listener.ButtonActionListener;
import br.com.inovatec.grid.view.content.exception.FormException;
import br.com.inovatec.grid.view.layout.template.FilterView;
import br.com.inovatec.grid.view.values.Colors;

/**
 * Classe base para layouts de formulario
 *
 * @author zrobe
 * @param <T>
 */
public abstract class DefaultFilterContent<T> extends DefaultFormContent<T> {

    private Button cancelButton, filterButton, resetButton;

    public DefaultFilterContent(FilterView container) {
        super(container, null, false);
        this.createActionButtons();
    }

    @Override
    public FilterView getContainer() {
        return (FilterView) super.getContainer();
    }

    /**
     * Adicionar os botoes de acao da dialog
     */
    private void createActionButtons() {
        // Acoes da Janela (Lado esquerdo)
        this.cancelButton = ButtonFactory.getInstance().getCancelButton(Colors.COLOR_MAIN, new ButtonActionListener() {
            @Override
            public void action() {
                getContainer().close();
            }
        });
        cancelButton.setType(Button.ButtonType.OPTION_LEFT);
        this.getOptions().add(this.cancelButton);

        // Acoes da Janela (Lado direito)
        this.resetButton = ButtonFactory.getInstance().getResetFiltersButton(Colors.COLOR_MAIN, new ButtonActionListener() {
            @Override
            public void action() {
                // Preencher criterios de consulta
                reset();
                // Realizar filtragem
                ((FilterView) getContainer()).getFilterListener().reset();
                // Fechar a janela
                getContainer().close();
            }
        });
        this.resetButton.setType(Button.ButtonType.OPTION_RIGHT);
        this.getOptions().add(this.resetButton);
        
        this.filterButton = ButtonFactory.getInstance().getApplyFiltersButton(Colors.COLOR_MAIN, new ButtonActionListener() {
            @Override
            public void action() {
                // Preencher criterios de consulta
                fillCriteriaParams();
                // Realizar filtragem
                ((FilterView) getContainer()).getFilterListener().filter();
                // Fechar a janela
                getContainer().close();
            }
        });
        filterButton.setType(Button.ButtonType.OPTION_RIGHT);
        this.getOptions().add(filterButton);
    }

    @Override
    public T getFilledObject() throws FormException {
        return null;
    }

    @Override
    public void fillFieldsByObject(T object) {
    }

    /**
     * Metodo para preencher os parametros de filtro com os nomes para a consulta
     */
    public abstract void fillCriteriaParams();
    
    /**
     * Limpar os parametros da pesquisa
     */
    public abstract void resetParams();
    
    public void reset() {
        ((FilterView) getContainer()).getCriteriaParams().clear();
        this.resetParams();
    }

}
