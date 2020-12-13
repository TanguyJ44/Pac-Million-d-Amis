/*
 * Nom de classe : Frame
 * Description   : Gestion de la fenêtre du jeu ainsi que des éléments graphiques
 * Version       : 1.2.1
 * Date          : 10/12/2020
 * Copyright     : Supinfo As.c 2 Nantes
 */

package fr.supinfo.java.gui;

import fr.supinfo.java.physical.Collides;
import fr.supinfo.java.physical.Motor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class Frame extends JFrame {

    int currentFPS = 0;
    String currentStat = "RUNNING";

    /**
     * Constructeur de Frame
     *
     * @return      void : la méthode ne retourne rien
     *
     * @see     Frame#Frame()
     * @author  Supinfo As.c 2 Nantes
     **/
    public Frame() {
        initComponents();
        setSize(1200, 837);
        setLocationRelativeTo(null);
    }

    /**
     * Initialisation de tous les composants JFrame utile
     *
     * @return      void : la méthode ne retourne rien
     *
     * @see     Frame#initComponents() 
     * @author  Supinfo As.c 2 Nantes
     **/
    @SuppressWarnings("unchecked")
    private void initComponents() {

        btnOn = new javax.swing.JButton();
        btnOff = new javax.swing.JButton();
        background = new javax.swing.JLabel();
        collide01 = new javax.swing.JLabel();
        collide02 = new javax.swing.JLabel();
        collide03 = new javax.swing.JLabel();
        collide04 = new javax.swing.JLabel();
        collide05 = new javax.swing.JLabel();
        collideTop = new javax.swing.JLabel();
        collideRight = new javax.swing.JLabel();
        collideBottom = new javax.swing.JLabel();
        collideLeft = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Pac'million d'Amis - 2JAVA | RUNNING | X FPS");
        setResizable(false);
        getContentPane().setLayout(null);

        btnOn.setBorderPainted(false);
        btnOn.setContentAreaFilled(false);
        btnOn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOn.setDefaultCapable(false);
        btnOn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOnActionPerformed(evt);
            }
        });
        getContentPane().add(btnOn);
        btnOn.setBounds(20, 50, 20, 20);

        btnOff.setBorderPainted(false);
        btnOff.setContentAreaFilled(false);
        btnOff.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOff.setDefaultCapable(false);
        btnOff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOffActionPerformed(evt);
            }
        });
        getContentPane().add(btnOff);
        btnOff.setBounds(20, 80, 20, 20);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/plate.png"))); // NOI18N
        getContentPane().add(background);
        background.setBounds(0, 0, 1200, 800);
        background.setVisible(false);
        getContentPane().add(collide01);
        collide01.setBounds(220, 150, 50, 50);
        getContentPane().add(collide02);
        collide02.setBounds(500, 240, 40, 40);
        getContentPane().add(collide03);
        collide03.setBounds(750, 330, 40, 40);
        getContentPane().add(collide04);
        collide04.setBounds(300, 500, 40, 50);
        getContentPane().add(collide05);
        collide05.setBounds(880, 600, 40, 50);
        getContentPane().add(collideTop);
        collideTop.setBounds(40, 24, 1120, 20);
        getContentPane().add(collideRight);
        collideRight.setBounds(1150, 50, 20, 710);
        getContentPane().add(collideBottom);
        collideBottom.setBounds(40, 750, 1120, 20);
        getContentPane().add(collideLeft);
        collideLeft.setBounds(40, 50, 10, 700);

        pack();
    }

    /**
     * Action bouton ON
     *
     * @return      void : la méthode ne retourne rien
     * @param       evt : instance du type d'event sur le composant
     *
     * @see     Frame#btnOnActionPerformed(ActionEvent) 
     * @author  Supinfo As.c 2 Nantes
     **/
    private void btnOnActionPerformed(java.awt.event.ActionEvent evt) {
        if (Motor.isRunning == false) {
            Motor.isRunning = true;
            updateStat(true);
            Motor.start();
            Collides.start();
        }
    }

    /**
     * Action bouton OFF
     *
     * @return      void : la méthode ne retourne rien
     * @param       evt : instance du type d'event sur le composant
     *
     * @see     Frame#btnOffActionPerformed(ActionEvent)
     * @author  Supinfo As.c 2 Nantes
     **/
    private void btnOffActionPerformed(java.awt.event.ActionEvent evt) {
        if (Motor.isRunning == true) {
            Motor.isRunning = false;
            updateStat(false);
            updateFPS(0);
            Motor.stop();
            Collides.stop();
        }
    }

    /**
     * Mise à jour du compteur de FPS
     *
     * @return      void : la méthode ne retourne rien
     * @param       newFPS : nouvelle moyenne de FPS
     *
     * @see     Frame#updateFPS(int) 
     * @author  Supinfo As.c 2 Nantes
     **/
    public void updateFPS(int newFPS) {
        currentFPS = newFPS;
        this.setTitle("Pac'million d'Amis - 2JAVA | " + currentStat + " | " + newFPS + " FPS");
    }

    /**
     * Mise à jour du status de la simulation
     *
     * @return      void : la méthode ne retourne rien
     * @param       isRun : true si la simulation est en cours, false si elle est à l'arrêt
     *
     * @see     Frame#updateStat(boolean)
     * @author  Supinfo As.c 2 Nantes
     **/
    public void updateStat(boolean isRun) {
        if (isRun == true) {
            currentStat = "RUNNING";
            this.setTitle("Pac'million d'Amis - 2JAVA | RUNNING | " + currentFPS + " FPS");
        } else {
            currentStat = "ON BREAK";
            this.setTitle("Pac'million d'Amis - 2JAVA | ON BREAK | " + currentFPS + " FPS");
        }
    }

    public static javax.swing.JLabel background;
    public static javax.swing.JButton btnOff;
    public static javax.swing.JButton btnOn;
    public static javax.swing.JLabel collide01;
    public static javax.swing.JLabel collide02;
    public static javax.swing.JLabel collide03;
    public static javax.swing.JLabel collide04;
    public static javax.swing.JLabel collide05;
    public static javax.swing.JLabel collideBottom;
    public static javax.swing.JLabel collideLeft;
    public static javax.swing.JLabel collideRight;
    public static javax.swing.JLabel collideTop;

}
