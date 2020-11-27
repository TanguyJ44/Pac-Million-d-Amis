package fr.supinfo.java.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PaintBackground extends JPanel {

    public void paintComponent(Graphics g){
        try {
            Image img = ImageIO.read(new File("/home/tanguy/Developpement/Java/Projet 2JAVA/supinfo.java.main/src/imgs/plate.png"));
            g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}