package fr.supinfo.java.gui;

import java.awt.*;
import java.io.File;
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