/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.values;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author zrobe
 */
public class Cursors {

    // Caminho para os icones
    private static final String RESOURCES_PATH = "img/cursor/?.png";

    // Icones da aplicacao
    public static final Cursor CURSOR_HAND_OPEN = getCursor("ic_hand_open");
    public static final Cursor CURSOR_HAND_CLOSED = getCursor("ic_hand_closed");
    public static final Cursor CURSOR_BLOCKED = getCursor("ic_blocked");
    //public static final Cursor CURSOR_FREEDON = getCursor("ic_freedon");

    /**
     * Obter Cursor
     *
     * @param icon
     * @return
     */
    private static Cursor getCursor(String icon) {
        try {
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Image img = ImageIO.read(Cursors.class.getClassLoader().getResource(RESOURCES_PATH.replace("?", icon)));
            return toolkit.createCustomCursor(img, new Point(10, 10), "cursor");
        } catch (IOException ex) {
            return null;
        }
    }

}
