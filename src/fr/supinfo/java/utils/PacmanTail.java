/*
 * Nom de classe : PacmanTail (version bêta)
 * Description   : Gestion de la "queuleuleu" après un sifflement
 * Version       : 1.2.1
 * Date          : 10/12/2020
 * Copyright     : Supinfo As.c 2 Nantes
 */

package fr.supinfo.java.utils;

import fr.supinfo.java.entity.Pacman;
import fr.supinfo.java.physical.Collides;
import fr.supinfo.java.physical.Motor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PacmanTail {

    public static ScheduledExecutorService executor;
    public static int posAfterLeader = 1;

    public static void init () {
        Pacman leader = null;

        Motor.stop();
        Collides.stop();

        Motor.stopSpawn = true;
        Motor.stopGhost = true;

        for (Pacman pacman : Motor.pacmans) {
            if (pacman.getGlobalSize() == 5) {
                leader = pacman;
            }
        }

        leader.setMoveStop(true);
        leader.setCount(0);
        leader.setDirRight();
        leader.setLocation(800, 100);

        for (int i = 4; i > 0; i--) {
            for (Pacman pacman : Motor.pacmans) {
                if (pacman.getGlobalSize() != 5 && pacman.getGlobalSize() == i) {
                    pacman.setMoveStop(true);
                    pacman.setCount(0);
                    pacman.setDirRight();
                    pacman.setLocation(leader.getX() - (70 * posAfterLeader), leader.getY());
                    posAfterLeader++;
                }
            }
        }

        start();
    }

    public static void start() {
        executor = Executors.newSingleThreadScheduledExecutor();
        Runnable periodicTask = new Runnable() {
            public void run() {
                for (int i = 5; i > 0; i--) {
                    for (Pacman pacman : Motor.pacmans) {
                        if (pacman.getGlobalSize() == i) {
                            if (pacman.getY() == 100 && pacman.getX() < 1000) {
                                pacman.setDirRight();
                                pacman.setLocation(pacman.getX() + 1, pacman.getY());
                            } else if (pacman.getX() > 890 && pacman.getY() < 410) {
                                pacman.setDirBottom();
                                pacman.setLocation(pacman.getX(), pacman.getY() + 1);
                            } else if (pacman.getY() > 400) {
                                pacman.setDirLeft();
                                pacman.setLocation(pacman.getX() - 1, pacman.getY());
                            }
                            if (pacman.getX() < 200) {
                                stop();
                            }
                        }
                    }
                }

            }
        };
        executor.scheduleAtFixedRate(periodicTask, 0, 20, TimeUnit.MILLISECONDS);

    }

    public static void stop() {
        executor.shutdown();

        Motor.start();
        Collides.start();

        Motor.stopSpawn = false;
        Motor.stopGhost = false;

        posAfterLeader = 1;

        for (Pacman pacman : Motor.pacmans) {
            pacman.setMoveStop(false);
        }
    }

}
