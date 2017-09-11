/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.layout.filters;

import br.com.inovatec.grid.view.content.filters.AulasFilterContent;
import br.com.inovatec.grid.view.contract.DialogView;
import br.com.inovatec.grid.view.layout.template.FilterView;
import br.com.inovatec.grid.view.values.Dimens;
import br.com.inovatec.grid.view.values.Strings;

/**
 *
 * @author zrobe
 */
public class AulasFilterView extends FilterView {
    
    public AulasFilterView(DialogView requester) {
        super(requester, Strings.AULAS_FILTER_TITLE, Dimens.AULAS_FILTER_DIALOG_WIDTH, Dimens.AULAS_FILTER_DIALOG_HEIGHT);
        setContent(new AulasFilterContent(this));
    }
    
}
