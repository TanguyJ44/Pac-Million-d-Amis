/*
 * Nom de classe : PaintBackground
 * Description   : Extension de la classe Frame pour la gestion d'un arrière-plan sur-mesure
 * Version       : 1.2.1
 * Date          : 10/12/2020
 * Copyright     : Supinfo As.c 2 Nantes
 */

package fr.supinfo.java.gui;

import java.awt.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class PaintBackground extends JPanel {

    public void paintComponent(Graphics g){
        Image img = null;
        try {
            img = ImageIO.read(getClass().getResource("/imgs/plate.png"));

            g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}