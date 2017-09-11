/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.dao.exceptions;

/**
 *
 * @author zrobe
 */
public class RefreshEntityException extends Exception {

    public RefreshEntityException() {
    }

    public RefreshEntityException(String message) {
        super(message);
    }

    public RefreshEntityException(String message, Throwable cause) {
        super(message, cause);
    }

    public RefreshEntityException(Throwable cause) {
        super(cause);
    }

    public RefreshEntityException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
