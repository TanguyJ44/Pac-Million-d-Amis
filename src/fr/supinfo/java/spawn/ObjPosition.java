/*
 * Nom de classe : ObjPosition
 * Description   : Extension des méthodes de position pour les objets afin d'optimiser la simulation
 * Version       : 1.2.1
 * Date          : 10/12/2020
 * Copyright     : Supinfo As.c 2 Nantes
 */

package fr.supinfo.java.spawn;

public class ObjPosition {

    int x;
    int y;

    public ObjPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setX(int newX) {
        x = newX;
    }

    public void setY(int newY) {
        y = newY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
