/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.service.exception;

/**
 *
 * @author zrobe
 */
public class EntityConstraintViolationException extends Exception {

    public EntityConstraintViolationException() {
    }

    public EntityConstraintViolationException(String message) {
        super(message);
    }

    public EntityConstraintViolationException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityConstraintViolationException(Throwable cause) {
        super(cause);
    }

    public EntityConstraintViolationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
