/*
 * Nom de classe : Main
 * Description   : Classe principale contenant le point d'entrer du programme
 * Version       : 1.2.1
 * Date          : 10/12/2020
 * Copyright     : Supinfo As.c 2 Nantes
 */

package fr.supinfo.java.main;

import fr.supinfo.java.gui.Frame;
import fr.supinfo.java.gui.PaintBackground;
import fr.supinfo.java.physical.Collides;
import fr.supinfo.java.physical.Motor;
import fr.supinfo.java.spawn.SpawnNewEntity;
import fr.supinfo.java.spawn.SpawnPoints;
import fr.supinfo.java.utils.AudioControl;

public class Main {

    public static Frame frame;
    public static PaintBackground pbg;
    public static AudioControl audioControl;

    /**
     * Point d'entré du programme
     *
     * @return      void : la méthode ne retourne rien
     * @param       args : tableau de String non utilisé
     *
     * @exception   null
     *
     * @see     Main#main(String[])
     * @author  Supinfo As.c 2 Nantes
     **/
    public static void main(String[] args) {

        frame = new Frame();

        audioControl = new AudioControl();

        pbg = new PaintBackground();
        pbg.setLayout(null);

        SpawnPoints.init();

        Motor.init();

        SpawnNewEntity.spawnPacman();
        SpawnNewEntity.spawnGhost();
        SpawnNewEntity.spawnGhost();

        Motor.start();
        Collides.start();

        pbg.setSize(1200, 800);
        frame.getContentPane().add(pbg);
        pbg.setVisible(true);

        frame.background.setVisible(false);
        frame.setVisible(true);
    }

}
