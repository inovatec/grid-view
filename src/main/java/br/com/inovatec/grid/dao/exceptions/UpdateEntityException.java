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
public class UpdateEntityException extends Exception {

    public UpdateEntityException() {
    }

    public UpdateEntityException(String message) {
        super(message);
    }

    public UpdateEntityException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateEntityException(Throwable cause) {
        super(cause);
    }

    public UpdateEntityException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
