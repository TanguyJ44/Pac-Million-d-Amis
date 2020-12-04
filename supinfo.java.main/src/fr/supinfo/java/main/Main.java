package fr.supinfo.java.main;

import fr.supinfo.java.gui.Frame;
import fr.supinfo.java.gui.PaintBackground;
import fr.supinfo.java.physical.Collides;
import fr.supinfo.java.physical.Motor;
import fr.supinfo.java.spawn.SpawnNewEntity;
import fr.supinfo.java.spawn.SpawnPoints;
import fr.supinfo.java.utils.AudioControl;

import java.io.File;

public class Main {

    public static Frame frame;
    public static PaintBackground pbg;
    public static AudioControl audioControl;

    public static void main(String[] args) {

        frame = new Frame();

        audioControl = new AudioControl();

        pbg = new PaintBackground();
        pbg.setLayout(null);

        SpawnPoints.init();

        Motor.init();

        SpawnNewEntity.spawnPacman();
        SpawnNewEntity.spawnGhost();
        SpawnNewEntity.spawnGhost();

        Motor.start();
        Collides.start();

        pbg.setSize(1200, 800);
        frame.getContentPane().add(pbg);
        pbg.setVisible(true);

        frame.background.setVisible(false);
        frame.setVisible(true);

    }

}
