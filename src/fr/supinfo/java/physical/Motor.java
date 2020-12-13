/*
 * Nom de classe : Motor
 * Description   : Moteur du jeu comprenant la boucle principal utile aux déplacements et aux interactions
 * Version       : 1.2.1
 * Date          : 10/12/2020
 * Copyright     : Supinfo As.c 2 Nantes
 */

package fr.supinfo.java.physical;

import fr.supinfo.java.main.Main;
import fr.supinfo.java.entity.Ghost;
import fr.supinfo.java.entity.Pacman;
import fr.supinfo.java.spawn.SpawnNewEntity;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Motor {

    public static ScheduledExecutorService executor;
    public static ArrayList<Pacman> pacmans;
    public static ArrayList<Ghost> ghosts;
    public static Random rand;

    public static boolean isRunning = true;
    public static boolean stopSpawn = false;
    public static boolean stopGhost = false;

    static boolean startRec = false;
    static long startTime;
    static int countFPS = 0;

    static int timeSpawnNextPacman = 600;
    static int countTimeSpawnPacman = 0;

    static int timeSpawnNextGhost = 300;
    static int countTimeSpawnGhost = 0;

    /**
     * Fonction d'initialisation du moteur
     *
     * @return      void : la fonction ne retourne rien
     *
     * @exception   null
     *
     * @see     Motor#init()
     * @author  Supinfo As.c 2 Nantes
     **/
    public static void init() {
        pacmans = new ArrayList<>();
        ghosts = new ArrayList<>();
        rand = new Random();
    }

    /**
     * Fonction de lancement du moteur
     *
     * @return      void : la méthode ne retourne rien
     *
     * @exception   null
     *
     * @see     Motor#start()
     * @author  Supinfo As.c 2 Nantes
     **/
    public static void start() {
        executor = Executors.newSingleThreadScheduledExecutor();
        Runnable periodicTask = new Runnable() {
            public void run() {
                for (int i = 0; i < pacmans.size(); i++) {
                    pacmans.get(i).onMove();
                }
                Main.pbg.repaint();
                Main.frame.repaint();
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
                if (timeSpawnNextPacman < countTimeSpawnPacman && !stopSpawn) {
                    if (pacmans.size() < 7) {
                        SpawnNewEntity.spawnPacman();
                        countTimeSpawnPacman = 0;
                        timeSpawnNextPacman = rand.nextInt(900 - 600 + 1) + 600;
                    } else {
                        countTimeSpawnPacman = 0;
                    }
                }
                countTimeSpawnGhost += 1;
                if (timeSpawnNextGhost == countTimeSpawnGhost && !stopGhost) {
                    SpawnNewEntity.spawnGhost();
                    countTimeSpawnGhost = 0;
                    timeSpawnNextGhost = rand.nextInt(800 - 300 + 1) + 300;
                }
            }
        };
        executor.scheduleAtFixedRate(periodicTask, 0, 20, TimeUnit.MILLISECONDS);
    }

    /**
     * Fonction d'arrêt du moteur'
     *
     * @return      void : la méthode ne retourne rien
     *
     * @exception   null
     *
     * @see     Motor#stop()
     * @author  Supinfo As.c 2 Nantes
     **/
    public static void stop() {
        executor.shutdown();
    }

}
