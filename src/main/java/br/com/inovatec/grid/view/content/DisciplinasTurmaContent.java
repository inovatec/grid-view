/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.content;

import br.com.inovatec.grid.entity.Professor;
import br.com.inovatec.grid.util.object.DisciplinaTurmaAulas;
import br.com.inovatec.grid.view.component.factory.ButtonFactory;
import br.com.inovatec.grid.view.component.form.Button;
import br.com.inovatec.grid.view.component.form.MultilineLabel;
import br.com.inovatec.grid.view.component.listener.ButtonActionListener;
import br.com.inovatec.grid.view.component.main.DisciplinaTurmaJPanel;
import br.com.inovatec.grid.view.component.util.ComponentUtils;
import br.com.inovatec.grid.view.content.exception.FormException;
import br.com.inovatec.grid.view.content.template.DefaultFormContent;
import br.com.inovatec.grid.view.layout.DisciplinasTurmaView;
import br.com.inovatec.grid.view.values.Colors;
import br.com.inovatec.grid.view.values.Dimens;
import br.com.inovatec.grid.view.values.Icons;
import br.com.inovatec.grid.view.values.Strings;
import java.awt.Dimension;
import java.awt.event.MouseEvent;

/**
 *
 * @author zrobe
 */
public class DisciplinasTurmaContent extends DefaultFormContent<Professor> {

    private DisciplinaTurmaJPanel selectedDisciplinaTurmaJPanel;
    private Button okButton, cancelButton;

    public DisciplinasTurmaContent(DisciplinasTurmaView container) {
        super(container, Icons.IC_DISCIPLINA, true);
        this.createActionButtons();
    }

    @Override
    public DisciplinasTurmaView getContainer() {
        return (DisciplinasTurmaView) super.getContainer(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void buildMain() {
        // Add campos do formulario [BEGIN]
        // Verificar se foram resgatados DisciplinasTurma
        if (!getContainer().getDisciplinaTurmaAulas().isEmpty()) {
            // Dimensoes dos cards
            Dimension d = ComponentUtils.getDefaultComponentDimension(getContainer().getWidth(), Dimens.DISCIPLINAS_TURMA_AULA_ITEM_HEIGHT, 5, Dimens.WEIGHT_20);
            // Mostrar os DisciplinasTurma
            for (DisciplinaTurmaAulas dta : getContainer().getDisciplinaTurmaAulas()) {
                // Professor
                DisciplinaTurmaJPanel dtjp = new DisciplinaTurmaJPanel(dta);
                dtjp.setSize(d);
                dtjp.setPreferredSize(d);
                // Verificar se o professor da aula eh igual ao professor atual do loop
                if (dta.getDisciplinaTurma().getDisciplina().equals(getContainer().getAula().getDisciplina())) {
                    // Checar
                    dtjp.check(true);
                    // Atribuir o painel atual ao painel selecionado
                    this.selectedDisciplinaTurmaJPanel = dtjp;
                }
                // Adicionar Listener de clique
                dtjp.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        // Verificar se o painel do Professor está habilitado
                        if (dtjp.isReleased()) {
                            // Checar painel
                            dtjp.check();
                            // Verificar se já existe painel de professor competente selecionado
                            if (!dtjp.equals(selectedDisciplinaTurmaJPanel)) {
                                // Desmarcar o anterior
                                if (selectedDisciplinaTurmaJPanel != null) {
                                    selectedDisciplinaTurmaJPanel.check(false);
                                }
                                // Definir o painel selecionado como o atual
                                selectedDisciplinaTurmaJPanel = dtjp;
                            } else {
                                // Verificar se foi desmarcado
                                if (!dtjp.isChecked()) {
                                    selectedDisciplinaTurmaJPanel = null;
                                }
                            }
                        }
                    }
                });
                // Verificar a disponibilidade do Professor
                if (dta.getTotalAulas() > 0) {
                    dtjp.release();
                } else {
                    dtjp.block();
                }
                this.getMain().add(dtjp);
            }
        } else {
            // Dimensoes dos cards
            Dimension d = ComponentUtils.getDefaultComponentDimension(getContainer().getWidth(), Dimens.DEFAULT_MULTILINE_HEIGHT, 1, Dimens.WEIGHT_100);
            // Aviso para ausência de professores para aquela disciplina
            MultilineLabel warn = new MultilineLabel(Strings.GRADE_HORARIOS_TURMA_SEM_DISCIPLINAS);
            warn.setSize(d);
            // Adicionar ao painel de conteúdo
            this.getMain().add(warn);
        }
    }

    @Override
    public Professor getFilledObject() throws FormException {
        return null;
    }

    @Override
    public void fillFieldsByObject(Professor object) {
    }

    /**
     * Adicionar os botoes de acao da dialog
     */
    private void createActionButtons() {
        // Acoes da Janela (Lado direito)
        this.cancelButton = ButtonFactory.getInstance().getCancelButton(Colors.COLOR_MAIN, new ButtonActionListener() {
            @Override
            public void action() {
                getContainer().close();
            }
        });
        cancelButton.setType(Button.ButtonType.OPTION_RIGHT);
        this.getOptions().add(cancelButton);

        this.okButton = ButtonFactory.getInstance().getOkButton(Colors.COLOR_MAIN, new ButtonActionListener() {
            @Override
            public void action() {
                save();
            }
        });
        this.okButton.setType(Button.ButtonType.OPTION_RIGHT);
        this.getOptions().add(this.okButton);
    }

    /**
     * Acao do botao salvar
     */
    private void save() {
        // Atribuir a disciplina selecionada ao objeto do container
        if (this.selectedDisciplinaTurmaJPanel != null) {
            getContainer().getAula().setDisciplina(this.selectedDisciplinaTurmaJPanel.getDisciplinaTurmaAulas().getDisciplinaTurma().getDisciplina());
        } else {
            getContainer().getAula().setDisciplina(null);
        }
        getContainer().close();
    }

}
