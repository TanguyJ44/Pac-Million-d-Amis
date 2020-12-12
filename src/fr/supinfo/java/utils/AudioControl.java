/*
 * Nom de classe : AudioControl
 * Description   : Élément de gestion du son entre l'OS et la simulation
 * Version       : 1.2.1
 * Date          : 10/12/2020
 * Copyright     : Supinfo As.c 2 Nantes
 */

package fr.supinfo.java.utils;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioControl implements LineListener {

    private static boolean playCompleted;

    private AudioInputStream audioStream = null;
    private AudioFormat format;
    private DataLine.Info info;
    public static Clip audioClip;

    private static Thread audioThread;

    public static int realTime = 0;

    /**
     * Initialisation de l'audio
     *
     * @return      void : la méthode ne retourne rien
     * @param       audioFilePath : Chemin vers l'audio
     *
     * @exception   UnsupportedAudioFileException
     * @exception   IOException
     * @exception   LineUnavailableException
     *
     * @see     AudioControl#init(File) 
     * @author  Supinfo As.c 2 Nantes
     **/
    public void init(File audioFilePath) {
        try {
            audioStream = AudioSystem.getAudioInputStream(audioFilePath);

            if (audioStream != null) {
                System.out.println("Audio init !");
            }

            format = audioStream.getFormat();

            info = new DataLine.Info(Clip.class, format);

            audioClip = (Clip) AudioSystem.getLine(info);

            audioClip.addLineListener(this);

            audioClip.open(audioStream);

        } catch (UnsupportedAudioFileException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (LineUnavailableException ex) {
            ex.printStackTrace();
        } finally {
            try {
                audioStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Jouer le son initialisé
     *
     * @return      void : la méthode ne retourne rien
     *
     * @see     AudioControl#play()
     * @author  Supinfo As.c 2 Nantes
     **/
    public void play() {
        playCompleted = false;

        audioThread = new Thread(new Runnable() {
            @Override
            public void run() {
                audioClip.setMicrosecondPosition(0);
                audioClip.start();

                while (!playCompleted) {
                    realTime = (int) audioClip.getMicrosecondPosition() / 1000;
                    try {
                        Thread.sleep(400);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }

                audioClip.close();
                audioThread.currentThread().interrupt();
            }
        });
        audioThread.start();

    }

    /**
     * Stopper le son initialisé
     *
     * @return      void : la méthode ne retourne rien
     *
     * @see     AudioControl#stop() 
     * @author  Supinfo As.c 2 Nantes
     **/
    public static void stop() {
        playCompleted = true;
        audioClip.close();
        audioThread.currentThread().interrupt();
    }

    /**
     * Modifier le status de l'audio
     *
     * @return      void : la méthode ne retourne rien
     * @param       event : Status à modifier
     *
     * @see     AudioControl#update(LineEvent) 
     * @author  Supinfo As.c 2 Nantes
     **/
    @Override
    public void update(LineEvent event) {
        LineEvent.Type type = event.getType();

        if (type == LineEvent.Type.START) {
            System.out.println("Playback started.");

        } else if (type == LineEvent.Type.STOP) {
            playCompleted = true;
            System.out.println("Playback completed.");
        }

    }

}