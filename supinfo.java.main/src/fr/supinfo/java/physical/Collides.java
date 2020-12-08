package fr.supinfo.java.physical;

import fr.supinfo.java.gui.Frame;
import fr.supinfo.java.main.Main;
import fr.supinfo.java.entity.Pacman;

import javax.swing.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Collides {

    public static ScheduledExecutorService executor;
    public static Thread thread;

    public static void start() {
        checkPacmanCollide();

        executor = Executors.newSingleThreadScheduledExecutor();
        Runnable periodicTask = new Runnable() {
            public void run() {
                boolean returnValue = false;
                for (int i = 0; i < Motor.pacmans.size(); i++) {
                    returnValue = false;
                    if (checkCollide(Motor.pacmans.get(i), Frame.collideTop)) returnValue = true;
                    if (checkCollide(Motor.pacmans.get(i), Frame.collideRight)) returnValue = true;
                    if (checkCollide(Motor.pacmans.get(i), Frame.collideBottom)) returnValue = true;
                    if (checkCollide(Motor.pacmans.get(i), Frame.collideLeft)) returnValue = true;
                    if (checkCollide(Motor.pacmans.get(i), Frame.collide01)) returnValue = true;
                    if (checkCollide(Motor.pacmans.get(i), Frame.collide02)) returnValue = true;
                    if (checkCollide(Motor.pacmans.get(i), Frame.collide03)) returnValue = true;
                    if (checkCollide(Motor.pacmans.get(i), Frame.collide04)) returnValue = true;
                    if (checkCollide(Motor.pacmans.get(i), Frame.collide05)) returnValue = true;

                    checkPacmanGhost(Motor.pacmans.get(i));

                    Motor.pacmans.get(i).setCollided(returnValue);
                }
            }
        };
        executor.scheduleAtFixedRate(periodicTask, 0, 1, TimeUnit.MICROSECONDS);
    }

    public static void stop() {
        executor.shutdown();
        thread.interrupt();
    }

    public static boolean checkCollide(Pacman pacman, JLabel wall) {
        if ( (pacman.getX() >= wall.getX() + wall.getWidth())
                || (pacman.getX() + pacman.getWidth() <= wall.getX())
                || (pacman.getY() >= wall.getY() + wall.getHeight())
                || (pacman.getY() + pacman.getHeight() <= wall.getY())){
            return false;
        }
        return true;
    }

    public static void checkPacmanCollide() {

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    for (int i = 0; i < Motor.pacmans.size(); i++) {
                        for (int j = 0; j < Motor.pacmans.size(); j++) {
                            if (Motor.pacmans.get(i) != Motor.pacmans.get(j)) {
                                if ( (Motor.pacmans.get(i).getX() >= Motor.pacmans.get(j).getX() + Motor.pacmans.get(j).getWidth())
                                        || (Motor.pacmans.get(i).getX() + Motor.pacmans.get(i).getWidth() <= Motor.pacmans.get(j).getX())
                                        || (Motor.pacmans.get(i).getY() >= Motor.pacmans.get(j).getY() + Motor.pacmans.get(j).getHeight())
                                        || (Motor.pacmans.get(i).getY() + Motor.pacmans.get(i).getHeight() <= Motor.pacmans.get(j).getY())){

                                } else {
                                    Motor.pacmans.get(i).reverseDir();
                                    Motor.pacmans.get(j).reverseDir();
                                }
                            }
                        }
                    }

                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }

    public static void checkPacmanGhost(Pacman pacman) {
        for (int i = 0; i < Motor.ghosts.size(); i++) {
            if ( (pacman.getX() >= Motor.ghosts.get(i).getX() + Motor.ghosts.get(i).getWidth())
                    || (pacman.getX() + pacman.getWidth() <= Motor.ghosts.get(i).getX())
                    || (pacman.getY() >= Motor.ghosts.get(i).getY() + Motor.ghosts.get(i).getHeight())
                    || (pacman.getY() + pacman.getHeight() <= Motor.ghosts.get(i).getY())){

            } else {
                pacman.setGlobalSize(pacman.getGlobalSize() + 1);
                pacman.hasEat();
                Main.pbg.remove(Motor.ghosts.get(i));
                Motor.ghosts.remove(Motor.ghosts.get(i));
            }
        }
    }

}