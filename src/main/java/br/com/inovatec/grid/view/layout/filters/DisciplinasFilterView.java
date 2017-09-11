/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.layout.filters;

import br.com.inovatec.grid.view.content.filters.DisciplinasFilterContent;
import br.com.inovatec.grid.view.contract.DialogView;
import br.com.inovatec.grid.view.layout.template.FilterView;
import br.com.inovatec.grid.view.values.Dimens;
import br.com.inovatec.grid.view.values.Strings;

/**
 *
 * @author zrobe
 */
public class DisciplinasFilterView extends FilterView {
    
    public DisciplinasFilterView(DialogView requester) {
        super(requester, Strings.DISCIPLINAS_FILTER_TITLE, Dimens.DISCIPLINAS_FILTER_DIALOG_WIDTH, Dimens.DISCIPLINAS_FILTER_DIALOG_HEIGHT);
        setContent(new DisciplinasFilterContent(this));
    }
    
}
