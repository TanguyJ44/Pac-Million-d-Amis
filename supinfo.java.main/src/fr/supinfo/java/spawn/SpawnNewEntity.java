package fr.supinfo.java.spawn;

import fr.supinfo.java.main.Main;
import fr.supinfo.java.entity.Ghost;
import fr.supinfo.java.entity.Pacman;
import fr.supinfo.java.physical.Motor;

public class SpawnNewEntity {

    public static void spawnPacman() {
        ObjPosition pacman_location = SpawnPoints.getRandomPacmanSpawnPoint();
        Pacman pacman = new Pacman(pacman_location.getX(), pacman_location.getY(), 2);

        Main.pbg.add(pacman);
        Motor.pacmans.add(pacman);
    }

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
