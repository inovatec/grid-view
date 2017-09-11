/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.util.object;

/**
 *
 * @author zrobe
 */
public class CriteriaParam {
    
    private String clause;
    private String paramName;
    private Object paramValue;

    public CriteriaParam(String clause, String paramName, Object paramValue) {
        this.clause = clause;
        this.paramName = paramName;
        this.paramValue = paramValue;
    }

    public String getClause() {
        return clause;
    }

    public void setClause(String clause) {
        this.clause = clause;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public Object getParamValue() {
        return paramValue;
    }

    public void setParamValue(Object paramValue) {
        this.paramValue = paramValue;
    }
        
}
