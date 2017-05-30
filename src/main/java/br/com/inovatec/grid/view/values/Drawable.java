/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.values;

import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author zrobe
 */
public class Drawable {
    
    // Caminho para os icones
    private static final String RESOURCES_PATH = "img/drawable/?.png";
    
    // Background do frame principal
    public static final ImageIcon BG_INDEX = getDrawable("bg_index");
    // Drawables da aplicacao
    public static final ImageIcon BG_CREATE = getDrawable("bg_create");
    public static final ImageIcon BG_EDIT = getDrawable("bg_edit");
    public static final ImageIcon BG_DELETE = getDrawable("bg_delete");
    
    /**
     * Obter drawable
     * 
     * @param drawable
     * @return 
     */
    public static ImageIcon getDrawable(String drawable) {
        ImageIcon imageIcon = new ImageIcon();
        try {
            Image img = ImageIO.read(Drawable.class.getClassLoader().getResource(RESOURCES_PATH.replace("?", drawable)));
            imageIcon.setImage(img);
        } catch (IOException ex) {
            Logger.getLogger(Drawable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return imageIcon;
    }
    
}
