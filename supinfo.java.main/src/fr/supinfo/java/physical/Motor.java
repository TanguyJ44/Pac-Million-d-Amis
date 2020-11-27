package fr.supinfo.java.physical;

import fr.supinfo.java.gui.Frame;
import fr.supinfo.java.main.Main;
import fr.supinfo.java.objects.Ghost;
import fr.supinfo.java.objects.Pacman;
import fr.supinfo.java.spawn.SpawnNewEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Motor {

    public static ScheduledExecutorService executor;
    public static ArrayList<Pacman> pacmans;
    public static ArrayList<Long> eatTime;
    public static ArrayList<Ghost> ghosts;
    public static Random rand;

    public static boolean isRunning = true;

    static boolean startRec = false;
    static long startTime;
    static int countFPS = 0;
    static int countPacmans = 1;

    static int timeSpawnNextPacman = 600;
    static int countTimeSpawnPacman = 0;

    static int timeSpawnNextGhost = 300;
    static int countTimeSpawnGhost = 0;

    public static void init() {
        pacmans = new ArrayList<>();
        eatTime = new ArrayList<>();
        ghosts = new ArrayList<>();
        rand = new Random();
    }

    public static void start() {
        executor = Executors.newSingleThreadScheduledExecutor();
        Runnable periodicTask = new Runnable() {
            public void run() {
                for (int i = 0; i < pacmans.size(); i++) {
                    pacmans.get(i).onMove();
                    if ((System.currentTimeMillis() - Motor.eatTime.get(i)) > 10000) {
                        pacmans.get(i).setGlobalSize(pacmans.get(i).getGlobalSize() - 1);
                        eatTime.add(pacmans.indexOf(i), System.currentTimeMillis());
                    }
                }

                if (startRec == false) {
                    startTime = System.currentTimeMillis();
                    startRec = true;
                }
                countFPS++;
                if (startTime + 1000 < System.currentTimeMillis()) {
                    Main.frame.updateFPS(countFPS);
                    countFPS = 0;
                    startRec = false;
                }
                countTimeSpawnPacman += 1;
                if (timeSpawnNextPacman == countTimeSpawnPacman && countPacmans < 7) {
                    SpawnNewEntity.spawnPacman();
                    System.out.println("Spawn new Pacman !");
                    countPacmans++;
                    countTimeSpawnPacman = 0;
                    timeSpawnNextPacman = rand.nextInt(900 - 600 + 1) + 600;
                }
                countTimeSpawnGhost += 1;
                if (timeSpawnNextGhost == countTimeSpawnGhost) {
                    //SpawnNewEntity.spawnGhost();
                    countTimeSpawnGhost = 0;
                    timeSpawnNextGhost = rand.nextInt(800 - 300 + 1) + 300;
                }
            }
        };
        executor.scheduleAtFixedRate(periodicTask, 0, 20, TimeUnit.MILLISECONDS);
    }

    public static void stop() {
        executor.shutdown();
    }

}