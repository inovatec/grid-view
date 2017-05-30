/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.layout;

import br.com.inovatec.grid.entity.Horario;
import br.com.inovatec.grid.view.content.HorarioEditContent;
import br.com.inovatec.grid.view.contract.DialogView;
import br.com.inovatec.grid.view.layout.template.DefaultView;
import br.com.inovatec.grid.view.values.Dimens;
import br.com.inovatec.grid.view.values.Strings;
import java.util.List;

/**
 *
 * @author zrobe
 */
public class HorarioEditView extends DefaultView {

    private final int index;
    private final Horario horario;
    private final List<Horario> horarios;
    private HorarioEditContent content;
    
    public HorarioEditView(DialogView requester, Horario horario, List<Horario> horarios, int index) {
        super(requester, Strings.HORARIO_EDIT_DIALOG_TITLE, Strings.HORARIO_EDIT_DIALOG_TITLE, null, Dimens.HORARIO_EDIT_DIALOG_WIDTH, Dimens.HORARIO_EDIT_DIALOG_HEIGHT);
        this.horario = horario;
        this.horarios = horarios;
        this.index = index;
        this.init();
    }
    
    /**
     * Inicializar componentes
     */
    private void init() {
        this.content = new HorarioEditContent(this);
    }

    @Override
    public HorarioEditContent getContent() {
        return this.content;
    }

    @Override
    public void refreshViewRequester() {
        getRequester().refresh();
    }

    @Override
    public void refresh() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Horario getHorario() {
        return this.horario;
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    public int getIndex() {
        return index;
    }
    
}
