/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.values;

import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author zrobe
 */
public class Icons {
    
    // Icone da aplicacao
    private static final String APP_ICON_PATH = "img/favicon-?x?.png";
    
    // Caminho para os icones
    private static final String RESOURCES_PATH = "img/icons/?.png";
    
    // Icones da aplicacao
    public static final ImageIcon IC_LOGIN = getImageIcon("ic_login");
    public static final ImageIcon IC_ESCOLA = getImageIcon("ic_escola");
    public static final ImageIcon IC_DISCIPLINA = getImageIcon("ic_disciplina");
    public static final ImageIcon IC_SALA = getImageIcon("ic_sala");
    public static final ImageIcon IC_TURMA = getImageIcon("ic_turma");
    public static final ImageIcon IC_HORARIOS = getImageIcon("ic_time");
    public static final ImageIcon IC_DIAS_AULA = getImageIcon("ic_calendar");
    public static final ImageIcon IC_PROFESSOR = getImageIcon("ic_professor");
    public static final ImageIcon IC_ADD = getImageIcon("ic_add");
    
    /**
     * Obter icone
     * 
     * @param icon
     * @return 
     */
    private static ImageIcon getImageIcon(String icon) {
        ImageIcon imageIcon = new ImageIcon();
        try {
            Image img = ImageIO.read(Icons.class.getClassLoader().getResource(RESOURCES_PATH.replace("?", icon)));
            imageIcon.setImage(img);
        } catch (IOException ex) {
            Logger.getLogger(Icons.class.getName()).log(Level.SEVERE, null, ex);
        }
        return imageIcon;
    }
    
    /**
     * Obter icone de aplicacao
     * 
     * @param size
     * @return 
     */
    private static Image getAppIcon(String size) {
        ImageIcon imageIcon = new ImageIcon();
        try {
            Image img = ImageIO.read(Icons.class.getClassLoader().getResource(APP_ICON_PATH.replace("?", size)));
            imageIcon.setImage(img);
        } catch (IOException ex) {
            Logger.getLogger(Icons.class.getName()).log(Level.SEVERE, null, ex);
        }
        return imageIcon.getImage();
    }
    
    /**
     * Obter lista de icones padrao para aplicacao
     * 
     * @return 
     */
    public static List<Image> getAppIcons() {
        List<Image> imageIcons = new ArrayList<>();
        imageIcons.add(Icons.getAppIcon("16"));
        imageIcons.add(Icons.getAppIcon("24"));
        imageIcons.add(Icons.getAppIcon("48"));
        imageIcons.add(Icons.getAppIcon("64"));
        imageIcons.add(Icons.getAppIcon("96"));
        imageIcons.add(Icons.getAppIcon("128"));
        imageIcons.add(Icons.getAppIcon("192"));
        imageIcons.add(Icons.getAppIcon("256"));
        return imageIcons;
    }
    
}
