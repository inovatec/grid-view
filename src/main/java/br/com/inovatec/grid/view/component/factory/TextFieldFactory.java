/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.component.factory;

import br.com.inovatec.grid.view.component.form.MaskedTextField;
import br.com.inovatec.grid.view.component.form.NumberTextField;
import br.com.inovatec.grid.view.component.form.PasswordTextField;
import br.com.inovatec.grid.view.component.form.TextField;
import br.com.inovatec.grid.view.component.util.ComponentUtils;
import java.awt.Dimension;

/**
 *
 * @author zrobe
 */
public class TextFieldFactory {
    
    private static TextFieldFactory instance;
    
    private TextFieldFactory() {}
    
    public static TextFieldFactory getInstance() {
        if (instance == null) {
            instance = new TextFieldFactory();
        }
        return instance;
    }
    
    public TextField getTextField(Dimension dimension) {
        TextField textField = new TextField();
        textField.setPreferredSize(dimension);
        return textField;
    }
    
    /**
     * Obter TextField com as configurações de dimensões
     * 
     * @param containerWidth
     * @param lineChilds
     * @param weight
     * @return 
     */
    public TextField getTextField(int containerWidth, int lineChilds, Double weight) {
        return getTextField(ComponentUtils.getTextFieldDimension(containerWidth, lineChilds, weight));
    }
    
    public PasswordTextField getPasswordTextField(Dimension dimension) {
        PasswordTextField passwordTextField = new PasswordTextField();
        passwordTextField.setPreferredSize(dimension);
        return passwordTextField;
    }
    
    public MaskedTextField getMaskedTextField(Dimension dimension, String mask) {
        MaskedTextField maskedTextField = new MaskedTextField(mask);
        maskedTextField.setPreferredSize(dimension);
        return maskedTextField;
    }
    
    public MaskedTextField getMaskedTextField(int containerWidth, int lineChilds, Double weight, String mask) {
        return getMaskedTextField(ComponentUtils.getTextFieldDimension(containerWidth, lineChilds, weight), mask);
    }
    
    public MaskedTextField getDateMaskedTextField(Dimension dimension) {
        return getMaskedTextField(dimension, "##/##/####");
    }
    
    public MaskedTextField getTimeMaskedTextField(Dimension dimension) {
        return getMaskedTextField(dimension, "##:##");
    }
    
    public NumberTextField getNumberTextField(Dimension dimension, int size) {
        NumberTextField numberTextField = new NumberTextField(size);
        numberTextField.setPreferredSize(dimension);
        return numberTextField;
    }
    
    public NumberTextField getNumberTextField(Dimension dimension) {
        NumberTextField numberTextField = new NumberTextField();
        numberTextField.setPreferredSize(dimension);
        return numberTextField;
    }
    
    public NumberTextField getNumberTextField(int containerWidth, int lineChilds, Double weight) {
        return getNumberTextField(ComponentUtils.getTextFieldDimension(containerWidth, lineChilds, weight));
    }
    
}
