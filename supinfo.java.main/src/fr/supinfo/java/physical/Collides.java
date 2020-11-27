package fr.supinfo.java.physical;

import fr.supinfo.java.gui.Frame;
import fr.supinfo.java.main.Main;
import fr.supinfo.java.objects.Pacman;

import javax.swing.*;

public class Collides {

    public static boolean getCollide(Pacman pacman) {
        if (checkCollide(pacman, Frame.collideTop)) return true;
        if (checkCollide(pacman, Frame.collideRight)) return true;
        if (checkCollide(pacman, Frame.collideBottom)) return true;
        if (checkCollide(pacman, Frame.collideLeft)) return true;
        if (checkCollide(pacman, Frame.collide01)) return true;
        if (checkCollide(pacman, Frame.collide02)) return true;
        if (checkCollide(pacman, Frame.collide03)) return true;
        if (checkCollide(pacman, Frame.collide04)) return true;
        if (checkCollide(pacman, Frame.collide05)) return true;

        if (checkPacmanCollide(pacman)) return true;

        checkPacmanGhost(pacman);

        return false;
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

    public static boolean checkPacmanCollide(Pacman pacman) {
        for (int i = 0; i < Motor.pacmans.size(); i++) {
            if (pacman != Motor.pacmans.get(i)) {
                if ( (pacman.getX() >= Motor.pacmans.get(i).getX() + Motor.pacmans.get(i).getWidth())
                        || (pacman.getX() + pacman.getWidth() <= Motor.pacmans.get(i).getX())
                        || (pacman.getY() >= Motor.pacmans.get(i).getY() + Motor.pacmans.get(i).getHeight())
                        || (pacman.getY() + pacman.getHeight() <= Motor.pacmans.get(i).getY())){
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    public static void checkPacmanGhost(Pacman pacman) {
        for (int i = 0; i < Motor.ghosts.size(); i++) {
            if ( (pacman.getX() >= Motor.ghosts.get(i).getX() + Motor.ghosts.get(i).getWidth())
                    || (pacman.getX() + pacman.getWidth() <= Motor.ghosts.get(i).getX())
                    || (pacman.getY() >= Motor.ghosts.get(i).getY() + Motor.ghosts.get(i).getHeight())
                    || (pacman.getY() + pacman.getHeight() <= Motor.ghosts.get(i).getY())){

            } else {
                pacman.setGlobalSize(pacman.getGlobalSize() + 1);
                Main.pbg.remove(Motor.ghosts.get(i));
                Motor.ghosts.remove(Motor.ghosts.get(i));
                Motor.eatTime.add(Motor.pacmans.indexOf(pacman), System.currentTimeMillis());
            }
        }
    }

}
