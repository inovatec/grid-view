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
public class RemoveEntityException extends Exception {

    public RemoveEntityException() {
    }

    public RemoveEntityException(String message) {
        super(message);
    }

    public RemoveEntityException(String message, Throwable cause) {
        super(message, cause);
    }

    public RemoveEntityException(Throwable cause) {
        super(cause);
    }

    public RemoveEntityException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
