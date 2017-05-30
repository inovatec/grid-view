/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.util;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author zrobe
 */
public class MessageFactory {
    
    public static void showInfoMessage(Component parentComponent, String message) {
        JOptionPane.showMessageDialog(parentComponent, message, "Informação", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void showWarnMessage(Component parentComponent, String message) {
        JOptionPane.showMessageDialog(parentComponent, message, "Alerta", JOptionPane.WARNING_MESSAGE);
    }
    
    public static void showErrorMessage(Component parentComponent, String message) {
        JOptionPane.showMessageDialog(parentComponent, message, "Erro", JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Exibe questionamento e executa automaticamente os metodos de acordo com a opçao selecionada
     * 
     * @param parentComponent
     * @param question
     * @param messageResultAction 
     */
    public static void showConfirmMessage(Component parentComponent, String question, MessageResultAction messageResultAction) {
        int result = JOptionPane.showConfirmDialog(parentComponent, question, "Confirmação", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        switch (result) {
            case JOptionPane.YES_OPTION:
                messageResultAction.confirm();
                break;
            case JOptionPane.NO_OPTION:
                messageResultAction.recuse();
                break;
            default:
                messageResultAction.cancel();
                break;
        }
    }
    
    /**
     * Exibe questionamento e executa automaticamente os metodos de acordo com a opçao selecionada,
     * exibindo apenas o botao de ok e cancelar
     * 
     * @param parentComponent
     * @param question
     * @param messageResultAction 
     */
    public static void showOkMessage(Component parentComponent, String question, MessageResultAction messageResultAction) {
        int result = JOptionPane.showConfirmDialog(parentComponent, question, "Confirmação", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        switch (result) {
            case JOptionPane.YES_OPTION:
                messageResultAction.confirm();
                break;
            default:
                messageResultAction.cancel();
                break;
        }
    }
    
    /**
     * Exibe questionamento e executa automaticamente os metodos de acordo com a opçao selecionada,
     * exibindo apenas o botao de ok e cancelar
     * 
     * @param parentComponent
     * @param question
     * @param messageResultAction 
     */
    public static void showYesNoMessage(Component parentComponent, String question, MessageResultAction messageResultAction) {
        int result = JOptionPane.showConfirmDialog(parentComponent, question, "Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        switch (result) {
            case JOptionPane.YES_OPTION:
                messageResultAction.confirm();
                break;
            default:
                messageResultAction.recuse();
                break;
        }
    }
    
}
