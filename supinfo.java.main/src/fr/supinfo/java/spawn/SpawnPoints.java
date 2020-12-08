package fr.supinfo.java.spawn;

import java.util.ArrayList;
import java.util.Random;

public class SpawnPoints {

    public static ArrayList<ObjPosition> pacmanSpawnPoints;
    public static ArrayList<ObjPosition> ghostSpawnPoints;
    public static Random random;

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

    public static ObjPosition getRandomPacmanSpawnPoint() {
        return pacmanSpawnPoints.get(random.nextInt(4 - 0 + 1) + 0);
    }
    public static ObjPosition getRandomGhostSpawnPoint() {
        return ghostSpawnPoints.get(random.nextInt(9 - 0 + 1) + 0);
    }

}
