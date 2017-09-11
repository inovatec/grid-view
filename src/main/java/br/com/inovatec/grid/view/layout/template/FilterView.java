/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.layout.template;

import br.com.inovatec.grid.util.object.CriteriaParam;
import br.com.inovatec.grid.view.component.form.util.FilterListener;
import br.com.inovatec.grid.view.content.template.DefaultFilterContent;
import br.com.inovatec.grid.view.contract.DialogView;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zrobe
 */
public abstract class FilterView extends DefaultView {
    
    private final List<CriteriaParam> criteriaParams = new ArrayList<>();
    private DefaultFilterContent content;
    private FilterListener filterListener;
    
    public FilterView(DialogView requester, String title, int width, int height) {
        super(requester, title, null, null, width, height);
    }

    public FilterListener getFilterListener() {
        return filterListener;
    }

    public void setFilterListener(FilterListener filterListener) {
        this.filterListener = filterListener;
    }

    @Override
    public DefaultFilterContent getContent() {
        return content;
    }

    public void setContent(DefaultFilterContent content) {
        this.content = content;
    }
    
    @Override
    public void refreshViewRequester() {
        getRequester().refresh();
    }

    @Override
    public void refresh() {
    }

    public List<CriteriaParam> getCriteriaParams() {
        return criteriaParams;
    }

}
