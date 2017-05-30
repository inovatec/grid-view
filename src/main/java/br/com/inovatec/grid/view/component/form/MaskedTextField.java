/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.component.form;

import br.com.inovatec.grid.view.contract.Field;
import br.com.inovatec.grid.view.values.Colors;
import br.com.inovatec.grid.view.values.Styles;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;

/**
 *
 * @author zrobe
 */
public class MaskedTextField extends javax.swing.JPanel implements Field {

    /**
     * Creates new form TextField
     * @param mask
     */
    public MaskedTextField(String mask) {
        initComponents();
        init(mask);
    }
    
    /**
     * Creates new form TextField
     * 
     * @param text
     * @param mask
     */
    public MaskedTextField(String text, String mask) {
        this(mask);
        this.jFormattedTextField.setText(text);
    }
    
    private void init(String mask) {
        // Adicionar mascara ao campo
        try {
            this.jFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter(mask)));
        } catch (ParseException ex) {
            Logger.getLogger(MaskedTextField.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Adicionar evento 
        this.jFormattedTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                
            }

            @Override
            public void focusLost(FocusEvent e) {
                
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFormattedTextField = new javax.swing.JFormattedTextField();

        setBorder(javax.swing.BorderFactory.createLineBorder(Colors.COLOR_BORDER_INPUT));
        setLayout(new java.awt.BorderLayout());

        jFormattedTextField.setFont(Styles.FONT_FAMILY);
        jFormattedTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(jFormattedTextField, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField jFormattedTextField;
    // End of variables declaration//GEN-END:variables

    public void setText(String text) {
        this.jFormattedTextField.setText(text);
    }

    public String getText() {
        return this.jFormattedTextField.getText();
    }
    
    public String getTextWithoutMask() {
        return this.jFormattedTextField.getText()
                .replace(".", "")
                .replace("-", "")
                .replace("(", "")
                .replace(")", "")
                .replace(" ", "");
    }

    public JFormattedTextField getFormattedTextField() {
        return jFormattedTextField;
    }
    
    @Override
    public void setEnabled(boolean enable) {
        this.jFormattedTextField.setEnabled(enable);
    }
    
    public void addFocusEventListener(FocusListener listener) {
        this.jFormattedTextField.addFocusListener(listener);
    }
    
}
