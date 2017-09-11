/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.content.filters;

import br.com.inovatec.grid.entity.Aula;
import br.com.inovatec.grid.view.content.template.DefaultFilterContent;
import br.com.inovatec.grid.view.layout.template.FilterView;

/**
 *
 * @author zrobe
 */
public class AulasFilterContent extends DefaultFilterContent<Aula> {

    public AulasFilterContent(FilterView container) {
        super(container);
    }
    
    @Override
    protected void buildMain() {
    }
    
    @Override
    public void fillCriteriaParams() {
        
    }

    @Override
    public void resetParams() {
        
    }

}
