/*
 * Nom de classe : Ghost
 * Description   : Objet ghost permettant la création ainsi que la manipulation des données des fantômes
 * Version       : 1.2.1
 * Date          : 10/12/2020
 * Copyright     : Supinfo As.c 2 Nantes
 */

package fr.supinfo.java.entity;

import javax.swing.*;
import java.util.Random;

public class Ghost extends JLabel {

    int x;
    int y;

    Random rand;
    int colorRand;

    public Ghost(int x, int y) {
        this.x = x;
        this.y = y;

        rand = new Random();

        setLocation(x, y);
        setSize(31, 30);

        colorRand = rand.nextInt(4 - 0 + 1) + 0;
        switch (colorRand) {
            case 0:
                setBlueGhost();
                break;
            case 1:
                setOrangeGhost();
                break;
            case 2:
                setPurpleGhost();
                break;
            case 3:
                setRedGhost();
                break;
            case 4:
                setYellowGhost();
                break;
            default:
                break;
        }
    }

    public void setBlueGhost() {
        setIcon(new ImageIcon(getClass().getResource("/imgs/ghost/ghostBlue.png")));
    }

    public void setOrangeGhost() {
        setIcon(new ImageIcon(getClass().getResource("/imgs/ghost/ghostOrange.png")));
    }

    public void setPurpleGhost() {
        setIcon(new ImageIcon(getClass().getResource("/imgs/ghost/ghostPurple.png")));
    }

    public void setRedGhost() {
        setIcon(new ImageIcon(getClass().getResource("/imgs/ghost/ghostRed.png")));
    }

    public void setYellowGhost() {
        setIcon(new ImageIcon(getClass().getResource("/imgs/ghost/ghostYellow.png")));
    }

}
