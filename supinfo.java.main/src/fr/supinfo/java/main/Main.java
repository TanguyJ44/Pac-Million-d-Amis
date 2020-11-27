package fr.supinfo.java.main;

import fr.supinfo.java.gui.Frame;
import fr.supinfo.java.gui.PaintBackground;
import fr.supinfo.java.objects.Ghost;
import fr.supinfo.java.objects.Pacman;
import fr.supinfo.java.physical.Motor;
import fr.supinfo.java.spawn.ObjPosition;
import fr.supinfo.java.spawn.SpawnPoints;

public class Main {

    public static Frame frame;
    public static PaintBackground pbg;

    public static void main(String[] args) {

        frame = new Frame();

        pbg = new PaintBackground();
        pbg.setLayout(null);

        SpawnPoints.init();

        ObjPosition posPacman = SpawnPoints.getRandomPacmanSpawnPoint();
        ObjPosition posGhost1 = SpawnPoints.getRandomGhostSpawnPoint();
        ObjPosition posGhost2 = SpawnPoints.getRandomGhostSpawnPoint();

        Pacman pacman1 = new Pacman(posPacman.getX(), posPacman.getY(), 2);
        Ghost ghost1 = new Ghost(posGhost1.getX(), posGhost1.getY());
        Ghost ghost2 = new Ghost(posGhost2.getX(), posGhost2.getY());
        pbg.add(pacman1);
        pbg.add(ghost1);
        pbg.add(ghost2);

        Motor.init();
        Motor.pacmans.add(pacman1);
        Motor.ghosts.add(ghost1);
        Motor.ghosts.add(ghost2);
        Motor.start();

        pbg.setSize(1200, 800);
        frame.getContentPane().add(pbg);
        pbg.setVisible(true);

        frame.background.setVisible(false);
        frame.setVisible(true);

    }

}
