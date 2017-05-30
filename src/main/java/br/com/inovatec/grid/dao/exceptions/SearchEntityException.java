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
public class SearchEntityException extends Exception {

    public SearchEntityException() {
    }

    public SearchEntityException(String message) {
        super(message);
    }

    public SearchEntityException(String message, Throwable cause) {
        super(message, cause);
    }

    public SearchEntityException(Throwable cause) {
        super(cause);
    }

    public SearchEntityException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
