/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.component.util;

import br.com.inovatec.grid.view.values.Dimens;
import static br.com.inovatec.grid.view.values.Dimens.DEFAULT_FOOTER_HEIGHT;
import static br.com.inovatec.grid.view.values.Dimens.DEFAULT_HEADER_HEIGHT;
import static br.com.inovatec.grid.view.values.Dimens.FORM_ELEMENT_DISCOUNT;
import static br.com.inovatec.grid.view.values.Dimens.WEIGHT_100;
import java.awt.Dimension;

/**
 *
 * @author zrobe
 */
public class ComponentUtils {
    
    public static Dimension getLabelDimension(int containerWidth, int lineChilds, Double weight) {
        return getElementDimension(Dimens.DEFAULT_LABEL_HEIGHT, containerWidth, Dimens.DEFAULT_MIN_PADDING, Dimens.DEFAULT_PADDING, lineChilds, weight, FORM_ELEMENT_DISCOUNT);
    }
    
    public static Dimension getInnerLabelDimension(int containerWidth, int lineChilds, Double weight) {
        return getElementDimension(Dimens.DEFAULT_LABEL_HEIGHT, containerWidth, 0, 0, lineChilds, weight, 0);
    }
    
    public static Dimension getTextFieldDimension(int containerWidth, int lineChilds, Double weight) {
        return getElementDimension(Dimens.DEFAULT_INPUT_HEIGHT, containerWidth, Dimens.DEFAULT_MIN_PADDING, Dimens.DEFAULT_PADDING, lineChilds, weight, FORM_ELEMENT_DISCOUNT);
    }
    
    public static Dimension getInnerTextFieldDimension(int containerWidth, int lineChilds, Double weight) {
        return getElementDimension(Dimens.DEFAULT_INPUT_HEIGHT, containerWidth, 0, 0, lineChilds, weight, 0);
    }
    
    public static Dimension getCheckboxDimension(int containerWidth, int lineChilds, Double weight) {
        return getElementDimension(Dimens.DEFAULT_CHECKBOX_HEIGHT, containerWidth, Dimens.DEFAULT_MIN_PADDING, Dimens.DEFAULT_PADDING, lineChilds, weight, FORM_ELEMENT_DISCOUNT);
    }
    
    public static Dimension getInnerCheckboxDimension(int containerWidth, int lineChilds, Double weight) {
        return getElementDimension(Dimens.DEFAULT_CHECKBOX_HEIGHT, containerWidth, 0, 0, lineChilds, weight, 0);
    }
    
    public static Dimension getTextAreaDimension(int containerWidth, int lineChilds, Double weight) {
        return getElementDimension(Dimens.DEFAULT_TEXTAREA_HEIGHT, containerWidth, Dimens.DEFAULT_MIN_PADDING, Dimens.DEFAULT_PADDING, lineChilds, weight, FORM_ELEMENT_DISCOUNT);
    }
    
    public static Dimension getSelectOneMenuDimension(int containerWidth, int lineChilds, Double weight) {
        return getElementDimension(Dimens.DEFAULT_SELECTONEMENU_HEIGHT, containerWidth, Dimens.DEFAULT_MIN_PADDING, Dimens.DEFAULT_PADDING, lineChilds, weight, FORM_ELEMENT_DISCOUNT);
    }
    
    public static Dimension getDefaultComponentDimension(int containerWidth, int height) {
        return getElementDimension(height, containerWidth, Dimens.DEFAULT_MIN_PADDING, Dimens.DEFAULT_PADDING, 1, Dimens.WEIGHT_100, FORM_ELEMENT_DISCOUNT);
    }
    
    public static Dimension getFormButtonDimension(int containerWidth, int lineChilds, Double weight) {
        return getElementDimension(Dimens.DEFAULT_FORM_BUTTON_HEIGHT, containerWidth, Dimens.DEFAULT_MIN_PADDING, Dimens.DEFAULT_PADDING, lineChilds, weight, FORM_ELEMENT_DISCOUNT);
    }
    
    public static Dimension getContainerFormPanelDimension(int containerWidth, int lineChilds, Double weight) {
        return getElementDimension(Dimens.DEFAULT_CONTAINER_FORM_PANEL_HEIGHT, containerWidth, Dimens.DEFAULT_MIN_PADDING, Dimens.DEFAULT_PADDING, lineChilds, weight, FORM_ELEMENT_DISCOUNT);
    }
    
    /**
     * Obter o tamanho da Dialog de acordo com o seu conteudo
     * 
     * @param contentHeight
     * @return 
     */
    public static int getDialogHeight(int contentHeight) {
        return DEFAULT_HEADER_HEIGHT + contentHeight + DEFAULT_FOOTER_HEIGHT;
    }
    
    /**
     * Tamanho horizontal do elemento com base no tamanho do pai e nos descontos relativos a emptyBorder e ao gap do flowLayout
     * 
     * @param height
     * @param parentWidth
     * @param parentPadding
     * @param flowGapPadding
     * @param lineChilds
     * @param weight
     * @param formElementDiscount
     * @return 
     **/
    public static Dimension getElementDimension(int height, int parentWidth, int parentPadding, int flowGapPadding, int lineChilds, double weight, int formElementDiscount) {
        // Diferença a ser removida de acordo com a quantidade de filhos da linha
        int flowGap = flowGapPadding * (lineChilds + 1);
        // Tamanho horizontal ideal para o componente, reduzindo o padding do layout, assim como demais propriedades
        Double width = ((parentWidth - (parentPadding * 2) - (flowGap) - formElementDiscount) * weight) / WEIGHT_100;
        // Dimensoes do componente
        return new Dimension(width.intValue(), height);
    }
    
}
