/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.content;

import br.com.inovatec.grid.entity.Professor;
import br.com.inovatec.grid.provider.ServiceProvider;
import br.com.inovatec.grid.service.exception.ServiceException;
import br.com.inovatec.grid.view.component.factory.ButtonFactory;
import br.com.inovatec.grid.view.component.form.Button;
import br.com.inovatec.grid.view.component.form.MultilineLabel;
import br.com.inovatec.grid.view.component.listener.ButtonActionListener;
import br.com.inovatec.grid.view.component.main.ProfessorCompetenteJPanel;
import br.com.inovatec.grid.view.component.util.ComponentUtils;
import br.com.inovatec.grid.view.content.exception.FormException;
import br.com.inovatec.grid.view.content.template.DefaultFormContent;
import br.com.inovatec.grid.view.controller.ViewController;
import br.com.inovatec.grid.view.layout.ProfessoresCompetentesView;
import br.com.inovatec.grid.view.values.Colors;
import br.com.inovatec.grid.view.values.Dimens;
import br.com.inovatec.grid.view.values.Icons;
import br.com.inovatec.grid.view.values.Strings;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zrobe
 */
public class ProfessoresCompetentesContent extends DefaultFormContent<Professor> {

    private ProfessorCompetenteJPanel selectedProfessorCompetenteJPanel;
    private Button viewButton, okButton, cancelButton;

    public ProfessoresCompetentesContent(ProfessoresCompetentesView container) {
        super(container, Icons.IC_PROFESSOR, true);
        this.createActionButtons();
    }

    @Override
    public ProfessoresCompetentesView getContainer() {
        return (ProfessoresCompetentesView) super.getContainer(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void buildMain() {
        // Add campos do formulario [BEGIN]
        try {
            // Buscar Professores competentes para a disciplina passada
            List<Professor> professores = ServiceProvider
                    .getInstance()
                    .getProfessorService()
                    .findByDisciplina(
                            this.getContainer()
                                    .getAula()
                                    .getDisciplinaTurma()
                                    .getDisciplina()
                    );
            // Verificar se foram resgatados Professores
            if (!professores.isEmpty()) {
                // Dimensoes dos cards
                Dimension d = ComponentUtils.getDefaultComponentDimension(getContainer().getWidth(), Dimens.PROFESSORES_COMPETENTES_ITEM_HEIGHT, 3, Dimens.WEIGHT_33);
                // Mostrar os Professores
                for (Professor p : professores) {
                    // Professor
                    ProfessorCompetenteJPanel pcjp = new ProfessorCompetenteJPanel(p);
                    pcjp.setSize(d);
                    pcjp.setPreferredSize(d);
                    // Verificar se o professor da aula eh igual ao professor atual do loop
                    if (p.equals(getContainer().getAula().getProfessor())) {
                        // Checar
                        pcjp.check(true);
                        // Atribuir o painel atual ao painel selecionado
                        this.selectedProfessorCompetenteJPanel = pcjp;
                    }
                    // Adicionar Listener de clique
                    pcjp.addMouseListener(new java.awt.event.MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            // Verificar se o painel do Professor está habilitado
                            if (pcjp.isReleased()) {
                                // Checar painel
                                pcjp.check();
                                // Verificar se já existe painel de professor competente selecionado
                                if (!pcjp.equals(selectedProfessorCompetenteJPanel)) {
                                    // Desmarcar o anterior
                                    if (selectedProfessorCompetenteJPanel != null) {
                                        selectedProfessorCompetenteJPanel.check(false);
                                    }
                                    // Definir o painel selecionado como o atual
                                    selectedProfessorCompetenteJPanel = pcjp;
                                    viewButton.setEnabled(true);
                                } else {
                                    // Verificar se foi desmarcado
                                    if (!pcjp.isChecked()) {
                                        selectedProfessorCompetenteJPanel = null;
                                        viewButton.setEnabled(false);
                                    }
                                }
                            }
                        }
                    });
                    // Verificar a disponibilidade do Professor
                    if (ServiceProvider.getInstance().getProfessorService().isFreeFor(p, this.getContainer().getAula().getHorario())) {
                        pcjp.release();
                    } else {
                        pcjp.block();
                    }
                    this.getMain().add(pcjp);
                }
            } else {
                // Dimensoes dos cards
                Dimension d = ComponentUtils.getDefaultComponentDimension(getContainer().getWidth(), Dimens.DEFAULT_MULTILINE_HEIGHT, 1, Dimens.WEIGHT_100);
                // Aviso para ausência de professores para aquela disciplina
                MultilineLabel warn = new MultilineLabel(Strings.GRADE_HORARIOS_PROFESSORES_COMPETENTES_EMPTY);
                warn.setSize(d);
                // Adicionar ao painel de conteúdo
                this.getMain().add(warn);
            }
        } catch (ServiceException ex) {
            Logger.getLogger(ProfessoresCompetentesContent.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Add campos do formulario [END]
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
        // Acoes da Janela (Lado esquerdo)
        // Visualizar
        this.viewButton = ButtonFactory.getInstance().getViewButton(Colors.COLOR_MAIN, new ButtonActionListener() {
            @Override
            public void action() {
                viewAction();
            }
        });
        this.viewButton.setType(Button.ButtonType.OPTION_LEFT);
        this.viewButton.setEnabled(false);        
        //this.getOptions().add(this.viewButton);
        
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
        // Atribuir o professor selecionado ao objeto do container
        if (this.selectedProfessorCompetenteJPanel != null) {
            getContainer().getAula().setProfessor(this.selectedProfessorCompetenteJPanel.getProfessor());
        } else {
            getContainer().getAula().setProfessor(null);
        }
        getContainer().close();
    }
    
    private void viewAction() {
        ViewController.showProfessorView(getContainer(), false, this.selectedProfessorCompetenteJPanel.getProfessor());
    }

}
