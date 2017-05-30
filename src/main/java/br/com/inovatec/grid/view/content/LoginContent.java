/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.content;

import br.com.inovatec.grid.provider.ServiceProvider;
import br.com.inovatec.grid.entity.Usuario;
import br.com.inovatec.grid.service.exception.ServiceException;
import br.com.inovatec.grid.view.component.FooterOptionsLayout;
import br.com.inovatec.grid.view.component.factory.ButtonFactory;
import br.com.inovatec.grid.view.component.factory.LabelFactory;
import br.com.inovatec.grid.view.component.factory.TextFieldFactory;
import br.com.inovatec.grid.view.component.form.Button;
import br.com.inovatec.grid.view.component.form.Button.ButtonType;
import br.com.inovatec.grid.view.component.form.PasswordTextField;
import br.com.inovatec.grid.view.component.form.TextField;
import br.com.inovatec.grid.view.component.listener.ButtonActionListener;
import br.com.inovatec.grid.view.component.util.ComponentUtils;
import br.com.inovatec.grid.view.content.template.DefaultFormContent;
import br.com.inovatec.grid.view.layout.LoginView;
import br.com.inovatec.grid.view.session.Session;
import br.com.inovatec.grid.view.util.MessageFactory;
import br.com.inovatec.grid.view.values.Colors;
import br.com.inovatec.grid.view.values.Dimens;
import br.com.inovatec.grid.view.values.Icons;
import br.com.inovatec.grid.view.values.Strings;
import java.awt.Dimension;

/**
 *
 * @author zrobe
 */
public class LoginContent extends DefaultFormContent<Usuario> {

    private TextField usuarioTextField;
    private PasswordTextField senhaTextField;

    public LoginContent(LoginView container) {
        super(container, Icons.IC_LOGIN, true);
        this.createActionButtons();
    }

    @Override
    public void initComponents() {
        super.initComponents();
        ((FooterOptionsLayout) this.getFooter()).hideOptionsLeftPanel();
        ((FooterOptionsLayout) this.getFooter()).hideOptionsRightPanel();
    }

    @Override
    protected void buildMain() {
        // Add campos do formulario [BEGIN]
        // Dimensoes dos Labels da primeira linha
        Dimension labelsLineOneDimension = ComponentUtils.getLabelDimension(this.getWidth(), 1, Dimens.WEIGHT_100);
        // Adicionar Labels da primeira linha
        this.getMain().add(LabelFactory.getInstance().getLabel(Strings.LOGIN_DIALOG_USUARIO_FIELD, labelsLineOneDimension));
        // Dimensoes dos TextFields da primeira linha
        Dimension fieldsLineOneDimension = ComponentUtils.getTextFieldDimension(this.getWidth(), 1, Dimens.WEIGHT_100);
        // Adicionar TextFields da primeira linha
        this.usuarioTextField = TextFieldFactory.getInstance().getTextField(fieldsLineOneDimension);
        // TODO: Remover a linha abaixo depois
        this.usuarioTextField.setText("admin");
        this.getMain().add(this.usuarioTextField);
        // Dimensoes dos Labels da segunda linha
        Dimension labelsLineTwoDimension = ComponentUtils.getLabelDimension(this.getWidth(), 1, Dimens.WEIGHT_100);
        // Adicionar Labels da segunda linha
        this.getMain().add(LabelFactory.getInstance().getLabel(Strings.LOGIN_DIALOG_SENHA_FIELD, labelsLineTwoDimension));
        // Dimensoes dos TextFields da segunda linha
        Dimension fieldsLineTwoDimension = ComponentUtils.getTextFieldDimension(this.getWidth(), 1, Dimens.WEIGHT_100);
        // Adicionar TextFields da segunda linha
        this.senhaTextField = TextFieldFactory.getInstance().getPasswordTextField(fieldsLineTwoDimension);
        // TODO: Remover a linha abaixo depois
        this.senhaTextField.setText("123");
        this.getMain().add(this.senhaTextField);
    }

    @Override
    public Usuario getFilledObject() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void fillFieldsByObject(Usuario object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Adicionar os botoes de acao da dialog
     */
    private void createActionButtons() {
        // Criar Botoes e Adicionar botoes
        // Acoes da Janela (Centro)
        // Dimensoes do botao
        Dimension buttonLineDimension = ComponentUtils.getFormButtonDimension(this.getWidth(), 2, Dimens.WEIGHT_50);
        // Botao de cancelar
        Button cancelButton = ButtonFactory.getInstance().getCancelButton(buttonLineDimension, Colors.COLOR_MAIN, new ButtonActionListener() {
            @Override
            public void action() {
                getContainer().close();
            }
        });
        cancelButton.setType(ButtonType.OPTION_CENTER);
        this.getOptions().add(cancelButton);
        // Botao de realizar login
        Button loginButton = ButtonFactory.getInstance().getLoginButton(buttonLineDimension, Colors.COLOR_MAIN, new ButtonActionListener() {
            @Override
            public void action() {
                login();
            }
        });
        loginButton.setType(ButtonType.OPTION_CENTER);
        this.getOptions().add(loginButton);
        // Definir botao de login como o principal
        this.getContainer().getDialog().getRootPane().setDefaultButton(loginButton);
    }

    /**
     * Metodo para realizar login
     */
    public void login() {
        try {
            // Adicionar usuario na sessao
            Session.getInstance().setUsuario(ServiceProvider.getInstance().getUsuarioService().authenticate(
                            this.usuarioTextField.getText(), this.senhaTextField.getText()
                    )
            );
            // Fechar janela
            this.getContainer().close();
        } catch (ServiceException ex) {
            MessageFactory.showErrorMessage(this, ex.getCause().getMessage());
        }
    }

}
