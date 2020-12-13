/*
 * Nom de classe : SpawnNewEntity
 * Description   : Création des entités Pacman et Fantômes sur demande du moteur
 * Version       : 1.2.1
 * Date          : 10/12/2020
 * Copyright     : Supinfo As.c 2 Nantes
 */

package fr.supinfo.java.spawn;

import fr.supinfo.java.main.Main;
import fr.supinfo.java.entity.Ghost;
import fr.supinfo.java.entity.Pacman;
import fr.supinfo.java.physical.Motor;

public class SpawnNewEntity {

    /**
     * Fonction de création d'un nouveau pacman
     *
     * @return      void : la méthode ne retourne rien
     *
     * @exception   null
     *
     * @see     SpawnNewEntity#spawnPacman()
     * @author  Supinfo As.c 2 Nantes
     **/
    public static void spawnPacman() {
        ObjPosition pacman_location = SpawnPoints.getRandomPacmanSpawnPoint();
        Pacman pacman = new Pacman(pacman_location.getX(), pacman_location.getY(), 2);

        Main.pbg.add(pacman);
        Motor.pacmans.add(pacman);

        System.out.println("Spawn new Pacman !");
    }

    /**
     * Fonction de création d'un nouveau Fantôme
     *
     * @return      void : la méthode ne retourne rien
     *
     * @exception   null
     *
     * @see     SpawnNewEntity#spawnGhost()
     * @author  Supinfo As.c 2 Nantes
     **/
    public static void spawnGhost() {
        ObjPosition ghost_location = SpawnPoints.getRandomGhostSpawnPoint();
        Ghost ghost = new Ghost(ghost_location.getX(), ghost_location.getY());
        boolean isGhost = false;

        for (int i = 0; i < Motor.ghosts.size(); i++) {
            if (Motor.ghosts.get(i).getX() == ghost_location.getX() && Motor.ghosts.get(i).getY() == ghost_location.getY()) {
                isGhost = true;
            }
        }

        if (!isGhost) {
            Main.pbg.add(ghost);
            Main.pbg.repaint();
            Motor.ghosts.add(ghost);

            System.out.println("Spawn new Ghost !");
        } else {
            System.out.println("[WARN] No Ghost spawn !");
        }
    }

}
