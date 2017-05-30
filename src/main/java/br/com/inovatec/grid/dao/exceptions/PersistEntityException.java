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
public class PersistEntityException extends Exception {

    public PersistEntityException() {
    }

    public PersistEntityException(String message) {
        super(message);
    }

    public PersistEntityException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersistEntityException(Throwable cause) {
        super(cause);
    }

    public PersistEntityException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
