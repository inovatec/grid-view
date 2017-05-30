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
public class ListEntityException extends Exception {

    public ListEntityException() {
    }

    public ListEntityException(String message) {
        super(message);
    }

    public ListEntityException(String message, Throwable cause) {
        super(message, cause);
    }

    public ListEntityException(Throwable cause) {
        super(cause);
    }

    public ListEntityException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
