/*
 * Nom de classe : SpawnPoints
 * Description   : Initialisation des diffèrents points de spawn sur le plateau
 * Version       : 1.2.1
 * Date          : 10/12/2020
 * Copyright     : Supinfo As.c 2 Nantes
 */

package fr.supinfo.java.spawn;

import java.util.ArrayList;
import java.util.Random;

public class SpawnPoints {

    public static ArrayList<ObjPosition> pacmanSpawnPoints;
    public static ArrayList<ObjPosition> ghostSpawnPoints;
    public static Random random;

    /**
     * Fonction d'initialisation des points de spawn Pacman / Ghost
     *
     * @return      void : la méthode ne retourne rien
     *
     * @exception   null
     *
     * @see     SpawnPoints#init()
     * @author  Supinfo As.c 2 Nantes
     **/
    public static void init() {
        pacmanSpawnPoints = new ArrayList<>();
        ghostSpawnPoints = new ArrayList<>();
        random = new Random();

        pacmanSpawnPoints.add(new ObjPosition(750, 150));
        pacmanSpawnPoints.add(new ObjPosition(150, 600));
        pacmanSpawnPoints.add(new ObjPosition(1000, 600));
        pacmanSpawnPoints.add(new ObjPosition(600, 400));
        pacmanSpawnPoints.add(new ObjPosition(500, 600));

        ghostSpawnPoints.add(new ObjPosition(800, 550));
        ghostSpawnPoints.add(new ObjPosition(180, 600));
        ghostSpawnPoints.add(new ObjPosition(1000, 200));
        ghostSpawnPoints.add(new ObjPosition(500, 400));
        ghostSpawnPoints.add(new ObjPosition(150, 400));
        ghostSpawnPoints.add(new ObjPosition(1050, 650));
        ghostSpawnPoints.add(new ObjPosition(600, 650));
        ghostSpawnPoints.add(new ObjPosition(600, 100));
        ghostSpawnPoints.add(new ObjPosition(630, 300));
        ghostSpawnPoints.add(new ObjPosition(850, 450));
    }

    /**
     * Getter de position d'un point de spawn pacman aléatoire
     *
     * @return      ObjPosition : position du point de spawn
     *
     * @exception   null
     *
     * @see     SpawnPoints#getRandomPacmanSpawnPoint()
     * @author  Supinfo As.c 2 Nantes
     **/
    public static ObjPosition getRandomPacmanSpawnPoint() {
        return pacmanSpawnPoints.get(random.nextInt(4 - 0 + 1) + 0);
    }

    /**
     * Getter de position d'un point de spawn ghost aléatoire
     *
     * @return      ObjPosition : position du point de spawn
     *
     * @exception   null
     *
     * @see     SpawnPoints#getRandomGhostSpawnPoint()
     * @author  Supinfo As.c 2 Nantes
     **/
    public static ObjPosition getRandomGhostSpawnPoint() {
        return ghostSpawnPoints.get(random.nextInt(9 - 0 + 1) + 0);
    }

}
