/*
 * Nom de classe : Pacman
 * Description   : Objet pacman permettant la création ainsi que la manipulation des données des pacmans
 * Version       : 1.2.1
 * Date          : 10/12/2020
 * Copyright     : Supinfo As.c 2 Nantes
 */

package fr.supinfo.java.entity;

import fr.supinfo.java.main.Main;
import fr.supinfo.java.physical.Motor;
import fr.supinfo.java.utils.PacmanTail;

import javax.swing.*;
import java.io.File;
import java.util.Random;

public class Pacman extends JLabel {

    Random rand;

    int speed;
    int size = 3;

    boolean collided = false;

    //0 = left, 1 = top, 2 = right, 3 = down
    int dir = 0;
    int oldDir = 2;
    int tempDir = 0;
    int count = 0;
    int maxCount = 100;

    int eatTime = 2000;

    int animation = 0;
    int animTime = 0;

    boolean stopMove = false;

    /**
     * Constructeur de l'objet
     *
     * @return      void : la méthode ne retourne rien
     * @param       x : coordonnée en X
     * @param       y : coordonnée en Y
     * @param       speed : vitesse de déplacement
     *
     * @exception   null
     *
     * @see     Pacman#Pacman(int, int, int)
     * @author  Supinfo As.c 2 Nantes
     **/
    public Pacman(int x, int y, int speed) {
        rand = new Random();

        this.speed = speed;

        setLocation(x, y);
        setDirLeft();
    }

    /**
     * Méthode de déplacement du pacman
     *
     * @return      void : la méthode ne retourne rien
     *
     * @see     Pacman#onMove()
     * @author  Supinfo As.c 2 Nantes
     **/
    public void onMove() {
        if (!collided && !stopMove) {
            if (count == maxCount) {
                oldDir = dir;
                tempDir = rand.nextInt(4);

                if (tempDir == 0 && oldDir == 2) {
                    dir = oldDir;
                } else if (tempDir == 1 && oldDir == 3) {
                    dir = oldDir;
                } else if (tempDir == 2 && oldDir == 0) {
                    dir = oldDir;
                } else if (tempDir == 3 && oldDir == 1) {
                    dir = oldDir;
                } else {
                    dir = tempDir;
                }

                count = 0;
                maxCount = rand.nextInt(150 - 50 + 1) + 50;
            }

            if (dir == 0) {
                setLocation(getX() - speed, getY());
                setDirLeft();
            } else if (dir == 1) {
                setLocation(getX(), getY() - speed);
                setDirTop();
            } else if (dir == 2) {
                setLocation(getX() + speed, getY());
                setDirRight();
            } else if (dir == 3) {
                setLocation(getX(), getY() + speed);
                setDirBottom();
            }

            count++;
        } else {
            reverseDir();
        }
        eatTime--;
        if (eatTime < 1) {
            if (!stopMove) setGlobalSize(size-1);
            hasEat();
        }
        animTime++;
        if (animTime > 40) {
            if (animation == 0) {
                animation = 1;
            } else {
                animation = 0;
            }
            animTime = 0;
        }
    }

    /**
     * Méthode d'inversement de la direction du pacman
     *
     * @return      void : la méthode ne retourne rien
     *
     * @see     Pacman#reverseDir()
     * @author  Supinfo As.c 2 Nantes
     **/
    public void reverseDir() {
        count -= 10;
        if (dir == 0) {
            dir = 2;
            setLocation(getX() + speed, getY());
        } else if (dir == 1) {
            dir = 3;
            setLocation(getX(), getY() + speed);
        } else if (dir == 2) {
            dir = 0;
            setLocation(getX() - speed, getY());
        } else if (dir == 3) {
            dir = 1;
            setLocation(getX(), getY() - speed);
        }
    }

    /**
     * Setter pour activer ou désactiver le déplacement du pacman
     *
     * @return      void : la méthode ne retourne rien
     *
     * @see     Pacman#setMoveStop(boolean) 
     * @author  Supinfo As.c 2 Nantes
     **/
    public void setMoveStop(boolean newMove) {
        stopMove = newMove;
    }

    /**
     * Setter de modification du temps de changement de direction (pour les collisions)
     *
     * @return      void : la méthode ne retourne rien
     *
     * @see     Pacman#setCount(int) 
     * @author  Supinfo As.c 2 Nantes
     **/
    public void setCount(int newCount) {
        count = newCount;
    }

    /**
     * Setter pour modifier l'état de contact du pacman (libre / en collision)
     *
     * @return      void : la méthode ne retourne rien
     *
     * @see     Pacman#setCollided(boolean)
     * @author  Supinfo As.c 2 Nantes
     **/
    public void setCollided(boolean newCollided) {
        collided = newCollided;
    }

    /**
     * Getter pour récupérer la taille du pacman
     *
     * @return      int : taille du pacman
     *
     * @see     Pacman#getGlobalSize()
     * @author  Supinfo As.c 2 Nantes
     **/
    public int getGlobalSize() {
        return size;
    }

    /**
     * Setter de modification de la taille du pacman
     *
     * @return      void : la méthode ne retourne rien
     * @param       newSize : nouvelle taille du pacman
     *
     * @see     Pacman#setGlobalSize(int)
     * @author  Supinfo As.c 2 Nantes
     **/
    public void setGlobalSize(int newSize) {
        if (newSize < 6) {
            size = newSize;
            if (newSize == 5) {
                System.out.println("(!) Sifflement");
                PacmanTail.init();
                Main.audioControl.init(new File("./sound/whistling.wav"));
                Main.audioControl.play();
            }
            if (newSize == 0) {
                System.out.println("[!] Pacman dead !");
                Main.pbg.remove(this);
                Motor.pacmans.remove(this);

                Main.audioControl.init(new File("./sound/pacman_dead.wav"));
                Main.audioControl.play();
            }
        }
    }

    /**
     * Méthode tampon pour définir le pacman comme rassasié
     *
     * @return      void : la méthode ne retourne rien
     *
     * @see     Pacman#hasEat()
     * @author  Supinfo As.c 2 Nantes
     **/
    public void hasEat() {
        eatTime = 2000;
    }

    /**
     * Setter de direction vers le haut
     *
     * @return      void : la méthode ne retourne rien
     *
     * @see     Pacman#setDirTop()
     * @author  Supinfo As.c 2 Nantes
     **/
    public void setDirTop() {
        if (size == 1) {
            setSize(27, 27);
            if (animation == 0) {
                setIcon(new ImageIcon(getClass().getResource("/imgs/pacman01/pacmanTopSS101.png")));
            } else {
                setIcon(new ImageIcon(getClass().getResource("/imgs/pacman01/pacmanTopSS201.png")));
            }
        } else if (size == 2) {
            setSize(41, 41);
            if (animation == 0) {
                setIcon(new ImageIcon(getClass().getResource("/imgs/pacman02/pacmanTopSS102.png")));
            } else {
                setIcon(new ImageIcon(getClass().getResource("/imgs/pacman02/pacmanTopSS202.png")));
            }
        } else if (size == 3) {
            setSize(50, 50);
            if (animation == 0) {
                setIcon(new ImageIcon(getClass().getResource("/imgs/pacman03/pacmanTopSS103.png")));
            } else {
                setIcon(new ImageIcon(getClass().getResource("/imgs/pacman03/pacmanTopSS203.png")));
            }
        } else if (size == 4) {
            setSize(58, 58);
            if (animation == 0) {
                setIcon(new ImageIcon(getClass().getResource("/imgs/pacman04/pacmanTopSS104.png")));
            } else {
                setIcon(new ImageIcon(getClass().getResource("/imgs/pacman04/pacmanTopSS204.png")));
            }
        } else if (size == 5) {
            setSize(64, 64);
            if (animation == 0) {
                setIcon(new ImageIcon(getClass().getResource("/imgs/pacman05/pacmanTopSS105.png")));
            } else {
                setIcon(new ImageIcon(getClass().getResource("/imgs/pacman05/pacmanTopSS205.png")));
            }
        }
    }

    /**
     * Setter de direction vers la droite
     *
     * @return      void : la méthode ne retourne rien
     *
     * @see     Pacman#setDirRight()
     * @author  Supinfo As.c 2 Nantes
     **/
    public void setDirRight() {
        if (size == 1) {
            setSize(27, 27);
            if (animation == 0) {
                setIcon(new ImageIcon(getClass().getResource("/imgs/pacman01/pacmanRightSS101.png")));
            } else {
                setIcon(new ImageIcon(getClass().getResource("/imgs/pacman01/pacmanRightSS201.png")));
            }
        } else if (size == 2) {
            setSize(41, 41);
            if (animation == 0) {
                setIcon(new ImageIcon(getClass().getResource("/imgs/pacman02/pacmanRightSS102.png")));
            } else {
                setIcon(new ImageIcon(getClass().getResource("/imgs/pacman02/pacmanRightSS202.png")));
            }
        } else if (size == 3) {
            setSize(50, 50);
            if (animation == 0) {
                setIcon(new ImageIcon(getClass().getResource("/imgs/pacman03/pacmanRightSS103.png")));
            } else {
                setIcon(new ImageIcon(getClass().getResource("/imgs/pacman03/pacmanRightSS203.png")));
            }
        } else if (size == 4) {
            setSize(58, 58);
            if (animation == 0) {
                setIcon(new ImageIcon(getClass().getResource("/imgs/pacman04/pacmanRightSS104.png")));
            } else {
                setIcon(new ImageIcon(getClass().getResource("/imgs/pacman04/pacmanRightSS204.png")));
            }
        } else if (size == 5) {
            setSize(64, 64);
            if (animation == 0) {
                setIcon(new ImageIcon(getClass().getResource("/imgs/pacman05/pacmanRightSS105.png")));
            } else {
                setIcon(new ImageIcon(getClass().getResource("/imgs/pacman05/pacmanRightSS205.png")));
            }
        }
    }

    /**
     * Setter de direction vers le bas
     *
     * @return      void : la méthode ne retourne rien
     *
     * @see     Pacman#setDirBottom()
     * @author  Supinfo As.c 2 Nantes
     **/
    public void setDirBottom() {
        if (size == 1) {
            setSize(27, 27);
            if (animation == 0) {
                setIcon(new ImageIcon(getClass().getResource("/imgs/pacman01/pacmanBottomSS101.png")));
            } else {
                setIcon(new ImageIcon(getClass().getResource("/imgs/pacman01/pacmanBottomSS201.png")));
            }
        } else if (size == 2) {
            setSize(41, 41);
            if (animation == 0) {
                setIcon(new ImageIcon(getClass().getResource("/imgs/pacman02/pacmanBottomSS102.png")));
            } else {
                setIcon(new ImageIcon(getClass().getResource("/imgs/pacman02/pacmanBottomSS202.png")));
            }
        } else if (size == 3) {
            setSize(50, 50);
            if (animation == 0) {
                setIcon(new ImageIcon(getClass().getResource("/imgs/pacman03/pacmanBottomSS103.png")));
            } else {
                setIcon(new ImageIcon(getClass().getResource("/imgs/pacman03/pacmanBottomSS203.png")));
            }
        } else if (size == 4) {
            setSize(58, 58);
            if (animation == 0) {
                setIcon(new ImageIcon(getClass().getResource("/imgs/pacman04/pacmanBottomSS104.png")));
            } else {
                setIcon(new ImageIcon(getClass().getResource("/imgs/pacman04/pacmanBottomSS204.png")));
            }
        } else if (size == 5) {
            setSize(64, 64);
            if (animation == 0) {
                setIcon(new ImageIcon(getClass().getResource("/imgs/pacman05/pacmanBottomSS105.png")));
            } else {
                setIcon(new ImageIcon(getClass().getResource("/imgs/pacman05/pacmanBottomSS205.png")));
            }
        }
    }

    /**
     * Setter de direction vers la gauche
     *
     * @return      void : la méthode ne retourne rien
     *
     * @see     Pacman#setDirLeft()
     * @author  Supinfo As.c 2 Nantes
     **/
    public void setDirLeft() {
        if (size == 1) {
            setSize(27, 27);
            if (animation == 0) {
                setIcon(new ImageIcon(getClass().getResource("/imgs/pacman01/pacmanLeftSS101.png")));
            } else {
                setIcon(new ImageIcon(getClass().getResource("/imgs/pacman01/pacmanLeftSS201.png")));
            }
        } else if (size == 2) {
            setSize(41, 41);
            if (animation == 0) {
                setIcon(new ImageIcon(getClass().getResource("/imgs/pacman02/pacmanLeftSS102.png")));
            } else {
                setIcon(new ImageIcon(getClass().getResource("/imgs/pacman02/pacmanLeftSS202.png")));
            }
        } else if (size == 3) {
            setSize(50, 50);
            if (animation == 0) {
                setIcon(new ImageIcon(getClass().getResource("/imgs/pacman03/pacmanLeftSS103.png")));
            } else {
                setIcon(new ImageIcon(getClass().getResource("/imgs/pacman03/pacmanLeftSS203.png")));
            }
        } else if (size == 4) {
            setSize(58, 58);
            if (animation == 0) {
                setIcon(new ImageIcon(getClass().getResource("/imgs/pacman04/pacmanLeftSS104.png")));
            } else {
                setIcon(new ImageIcon(getClass().getResource("/imgs/pacman04/pacmanLeftSS204.png")));
            }
        } else if (size == 5) {
            setSize(64, 64);
            if (animation == 0) {
                setIcon(new ImageIcon(getClass().getResource("/imgs/pacman05/pacmanLeftSS105.png")));
            } else {
                setIcon(new ImageIcon(getClass().getResource("/imgs/pacman05/pacmanLeftSS205.png")));
            }
        }
    }

}