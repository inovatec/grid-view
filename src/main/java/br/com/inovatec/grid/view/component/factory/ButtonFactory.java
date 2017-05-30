/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.component.factory;

import br.com.inovatec.grid.view.component.form.Button;
import br.com.inovatec.grid.view.component.listener.ButtonActionListener;
import br.com.inovatec.grid.view.values.Colors;
import br.com.inovatec.grid.view.values.Commands;
import br.com.inovatec.grid.view.values.Dimens;
import br.com.inovatec.grid.view.values.Drawable;
import br.com.inovatec.grid.view.values.Icons;
import br.com.inovatec.grid.view.values.Strings;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author zrobe
 */
public class ButtonFactory {

    private static ButtonFactory instance;

    private ButtonFactory() {
    }

    public static ButtonFactory getInstance() {
        if (instance == null) {
            instance = new ButtonFactory();
        }
        return instance;
    }

    public Button getLoginButton(Dimension dimension, Color containerColor, ButtonActionListener actionListener) {
        return buildWithDimensions(
                buildGreenButton(Strings.LOGIN_BUTTON, null, containerColor, actionListener),
                dimension.width,
                dimension.height
        );
    }

    public Button getConfirmButton(Color containerColor, ButtonActionListener actionListener) {
        return buildWithDefaultDimensions(buildGreenButton("Confirmar", null, containerColor, actionListener));
    }
    
    public Button getSaveButton(Color containerColor, ButtonActionListener actionListener) {
        return buildWithDefaultDimensions(buildGreenButton("Salvar", null, containerColor, actionListener));
    }
    
    public Button getSaveButton(Dimension dimension, Color containerColor, ButtonActionListener actionListener) {
        return buildWithDimensions(
                buildGreenButton("Salvar", null, containerColor, actionListener),
                dimension.width,
                dimension.height
        );
    }

    public Button getCreateButton(Color containerColor, ButtonActionListener actionListener) {
        return buildWithDefaultDimensions(buildGreenButton("Adicionar", null, containerColor, actionListener));
    }
    
    public Button getEditButton(Color containerColor, ButtonActionListener actionListener) {
        return buildWithDefaultDimensions(buildOrangeButton("Editar", null, containerColor, actionListener));
    }
    
    public Button getFilterButton(Color containerColor, ButtonActionListener actionListener) {
        return buildWithDefaultDimensions(buildOrangeButton("Filtrar", null, containerColor, actionListener));
    }
    
    public Button getViewButton(Color containerColor, ButtonActionListener actionListener) {
        return buildWithDefaultDimensions(buildBlueButton("Visualizar", null, containerColor, actionListener));
    }
    
    public Button getDeleteButton(Color containerColor, ButtonActionListener actionListener) {
        return buildWithDefaultDimensions(buildRedButton("Excluir", null, containerColor, actionListener));
    }

    public Button getOkButton(Color containerColor, ButtonActionListener actionListener) {
        return buildWithDefaultDimensions(buildGreenButton("OK", null, containerColor, actionListener));
    }

    public Button getCloseButton(Color containerColor, ButtonActionListener actionListener) {
        return buildWithDefaultDimensions(buildGreyButton("Fechar", null, containerColor, actionListener));
    }
    
    /**
     * Obter botao de horarios
     * 
     * @param containerColor
     * @param actionListener
     * @return 
     */
    public Button getHorariosButton(Color containerColor, ButtonActionListener actionListener) {
        return buildWithDefaultDimensions(buildBlueButton("Horários", null, containerColor, actionListener));
    }

    public Button getCancelButton(Color containerColor, ButtonActionListener actionListener) {
        return buildWithDefaultDimensions(buildGreyButton("Cancelar", null, containerColor, actionListener));
    }

    public Button getCancelButton(Dimension dimension, Color containerColor, ButtonActionListener actionListener) {
        return buildWithDimensions(
                buildGreyButton(Strings.CANCEL_BUTTON, null, containerColor, actionListener),
                dimension.width,
                dimension.height);
    }

    public Button getDefaultFormButton(String text, Color containerColor, ButtonActionListener actionListener) {
        return buildWithDefaultDimensions(buildBlueButton(text, null, containerColor, actionListener));
    }

    public Button getDefaultFormButton(String text, int width, Color containerColor, ButtonActionListener actionListener) {
        return buildWithDimensions(buildBlueButton(text, null, containerColor, actionListener), width, Dimens.DEFAULT_BUTTON_HEIGHT);
    }

    public Button getDefaultFormButton(String text, Dimension dimension, Color containerColor, ButtonActionListener actionListener) {
        return buildWithDimensions(buildBlueButton(text, null, containerColor, actionListener), dimension.width, dimension.height);
    }

    public Button getCreateActionButton(ButtonActionListener actionListener) {
        return buildWithDimensions(
                buildDrawableButton(Drawable.BG_CREATE, actionListener, Commands.CREATE),
                Dimens.DEFAULT_BUTTON_ICON_SIZE,
                Dimens.DEFAULT_BUTTON_ICON_SIZE
        );
    }

    public Button getEditActionButton(ButtonActionListener actionListener) {
        return buildWithDimensions(
                buildDrawableButton(Drawable.BG_EDIT, actionListener, Commands.EDIT),
                Dimens.DEFAULT_BUTTON_ICON_SIZE,
                Dimens.DEFAULT_BUTTON_ICON_SIZE
        );
    }

    public Button getDeleteActionButton(ButtonActionListener actionListener) {
        return buildWithDimensions(
                buildDrawableButton(Drawable.BG_DELETE, actionListener, Commands.DELETE),
                Dimens.DEFAULT_BUTTON_ICON_SIZE,
                Dimens.DEFAULT_BUTTON_ICON_SIZE
        );
    }

    public Button getAddButton(Dimension dimension, Color containerColor, ButtonActionListener actionListener) {
        return buildWithDimensions(
                buildGreenButton(null, Icons.IC_ADD, containerColor, actionListener),
                dimension.width,
                dimension.height
        );
    }

    /**
     * Aumentar o tamanho do botao
     *
     * @param button
     * @param plusWidth
     *
     * @return
     */
    public Button increase(int plusWidth, Button button) {
        Dimension d = button.getPreferredSize();
        d.setSize(d.width + plusWidth, d.height);
        button.setPreferredSize(d);
        return button;
    }

    /**
     * Aumentar o tamanho do botao em 20
     *
     * @param button
     * @return
     */
    public Button increase(Button button) {
        return increase(Dimens.DEFAULT_PADDING * 2, button);
    }

    /**
     * Botao verde
     *
     * @param text
     * @param icon
     * @return
     */
    private Button buildGreenButton(String text, Icon icon, Color containerColor, ButtonActionListener actionListener) {
        Button button = new Button(Colors.COLOR_GREEN, Colors.COLOR_GREEN_HOVER, Colors.COLOR_GREEN_PRESS, containerColor, Colors.COLOR_GREEN_BORDER, Colors.COLOR_WHITE, text, icon, false);
        button.addActionListener(actionListener);
        button.addKeyListener(actionListener);
        return button;
    }

    /**
     * Botao verde
     *
     * @param text
     * @param icon
     * @return
     */
    private Button buildOrangeButton(String text, Icon icon, Color containerColor, ButtonActionListener actionListener) {
        Button button = new Button(Colors.COLOR_ORANGE, Colors.COLOR_ORANGE_HOVER, Colors.COLOR_ORANGE_PRESS, containerColor, Colors.COLOR_ORANGE_BORDER, Colors.COLOR_WHITE, text, icon, false);
        button.addActionListener(actionListener);
        button.addKeyListener(actionListener);
        return button;
    }

    /**
     * Botao cinza
     *
     * @param text
     * @param icon
     * @return
     */
    private Button buildGreyButton(String text, Icon icon, Color containerColor, ButtonActionListener actionListener) {
        Button button = new Button(Colors.COLOR_GREY, Colors.COLOR_GREY_HOVER, Colors.COLOR_GREY_PRESS, containerColor, Colors.COLOR_GREY_BORDER, Colors.COLOR_FONT, text, icon, false);
        button.addActionListener(actionListener);
        button.addKeyListener(actionListener);
        return button;
    }

    /**
     * Botao azul
     *
     * @param text
     * @param icon
     * @return
     */
    private Button buildBlueButton(String text, Icon icon, Color containerColor, ButtonActionListener actionListener) {
        Button button = new Button(Colors.COLOR_BLUE, Colors.COLOR_BLUE_HOVER, Colors.COLOR_BLUE_PRESS, containerColor, Colors.COLOR_BLUE_BORDER, Colors.COLOR_WHITE, text, icon, false);
        button.addActionListener(actionListener);
        button.addKeyListener(actionListener);
        return button;
    }
    
    /**
     * Botao vermelho
     *
     * @param text
     * @param icon
     * @return
     */
    private Button buildRedButton(String text, Icon icon, Color containerColor, ButtonActionListener actionListener) {
        Button button = new Button(Colors.COLOR_RED, Colors.COLOR_RED_HOVER, Colors.COLOR_RED_PRESS, containerColor, Colors.COLOR_RED_BORDER, Colors.COLOR_WHITE, text, icon, false);
        button.addActionListener(actionListener);
        button.addKeyListener(actionListener);
        return button;
    }

    private Button buildIconButton(ImageIcon icon, Color background, Color hoverBackground, Color pressBackground, Color disabledColor, Color borderColor, Color foregroundColor) {
        return new Button(background, hoverBackground, pressBackground, disabledColor, borderColor, foregroundColor, null, icon, false);
    }

    private Button buildDrawableButton(ImageIcon drawable, ButtonActionListener actionListener, String actionCommand) {
        Button button = new Button(null, null, null, null, null, null, null, drawable, true);
        button.addActionListener(actionListener);
        button.addKeyListener(actionListener);
        button.setActionCommand(actionCommand);
        return button;
    }

    /**
     * Retornar uma instancia com as dimensoes padrao
     *
     * @param button
     * @return
     */
    private Button buildWithDefaultDimensions(Button button) {
        button.setPreferredSize(new Dimension(Dimens.DEFAULT_BUTTON_WIDTH, Dimens.DEFAULT_BUTTON_HEIGHT));
        return button;
    }

    /**
     * Retornar uma instancia com as dimensoes padrao
     *
     * @param button
     * @return
     */
    private Button buildWithDimensions(Button button, int width, int height) {
        button.setPreferredSize(new Dimension(width, height));
        return button;
    }

}
