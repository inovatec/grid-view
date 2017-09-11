/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.layout.filters;

import br.com.inovatec.grid.view.content.filters.SalasFilterContent;
import br.com.inovatec.grid.view.contract.DialogView;
import br.com.inovatec.grid.view.layout.template.FilterView;
import br.com.inovatec.grid.view.values.Dimens;
import br.com.inovatec.grid.view.values.Strings;

/**
 *
 * @author zrobe
 */
public class SalasFilterView extends FilterView {
    
    public SalasFilterView(DialogView requester) {
        super(requester, Strings.SALAS_FILTER_TITLE, Dimens.SALAS_FILTER_DIALOG_WIDTH, Dimens.SALAS_FILTER_DIALOG_HEIGHT);
        setContent(new SalasFilterContent(this));
    }
    
}
