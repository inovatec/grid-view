/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.values;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zrobe
 */
public class Content {

    private static final String TUTORIALS_PATH = "tutorial/tutorial-?.html";
    private static final String ABOUT_PATH = "content/?.html";

    public static final String SOBRE = getContent(ABOUT_PATH, "sobre");

    public static final String[] TUTORIALS = {
        getTutorialContent("1"),
        getTutorialContent("1.1"),
        getTutorialContent("2"),
        getTutorialContent("3"),
        getTutorialContent("4"),
        getTutorialContent("5"),
        getTutorialContent("6"),
        getTutorialContent("7"),
        getTutorialContent("8"),
        getTutorialContent("9"),
        getTutorialContent("10")
    };

    private static String getTutorialContent(String tutorialNumber) {
        return getContent(TUTORIALS_PATH, tutorialNumber);
    }

    /*private static String getContent(String path, String fileName) {
        try {
            ClassLoader classLoader = Content.class.getClassLoader();
            File file = new File(classLoader.getResource(path.replace("?", fileName)).getFile());
            return new String(Files.readAllBytes(file.toPath()), "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Content.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Content.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }*/
    private static String getContent(String path, String fileName) {
        try {
            InputStream is = new BufferedInputStream(Content.class.getClassLoader().getResourceAsStream(path.replace("?", fileName)));
            byte[] targetArray = new byte[is.available()];
            is.read(targetArray);
            // Retornar conteudo do arquivo
            return new String(targetArray, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Content.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Content.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
