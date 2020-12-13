/*
 * Nom de classe : ObjPosition
 * Description   : Extension des méthodes de position pour les objets afin d'optimiser la simulation
 * Version       : 1.2.1
 * Date          : 10/12/2020
 * Copyright     : Supinfo As.c 2 Nantes
 */

package fr.supinfo.java.spawn;

import fr.supinfo.java.physical.Motor;

public class ObjPosition {

    int x;
    int y;

    /**
     * Constructeur de la classe
     *
     * @return      void : la méthode ne retourne rien
     * @param       x : Position en X
     * @param       y : Position en Y
     *
     * @exception   null
     *
     * @see     ObjPosition#ObjPosition(int, int)
     * @author  Supinfo As.c 2 Nantes
     **/
    public ObjPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Getter de récupération de X
     *
     * @return      int : position en X
     *
     * @see     ObjPosition#getX()
     * @author  Supinfo As.c 2 Nantes
     **/
    public int getX() {
        return x;
    }

    /**
     * Getter de récupération de Y
     *
     * @return      int : position en Y
     *
     * @see     ObjPosition#getY()
     * @author  Supinfo As.c 2 Nantes
     **/
    public int getY() {
        return y;
    }

}
