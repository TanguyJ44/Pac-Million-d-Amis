package fr.supinfo.java.entity;

import fr.supinfo.java.main.Main;
import fr.supinfo.java.physical.Collides;
import fr.supinfo.java.physical.Motor;

import javax.swing.*;
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

    public Pacman(int x, int y, int speed) {
        rand = new Random();

        this.speed = speed;

        setLocation(x, y);
        setDirLeft();
    }

    public void onMove() {
        if (!collided) {
            if (dir == 0) {
                setLocation((int) getX() - speed, (int) getY());
                setDirLeft();
            } else if (dir == 1) {
                setLocation((int) getX(), (int) getY() - speed);
                setDirTop();
            } else if (dir == 2) {
                setLocation((int) getX() + speed, (int) getY());
                setDirRight();
            } else if (dir == 3) {
                setLocation((int) getX(), (int) getY() + speed);
                setDirBottom();
            }
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
            count++;
        } else {
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
        eatTime--;
        if (eatTime < 1) {
            setGlobalSize(size-1);
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

    public boolean getCollided() {
        return collided;
    }

    public void setCollided(boolean newCollided) {
        collided = newCollided;
    }

    public int getGlobalSize() {
        return size;
    }

    public void setGlobalSize(int newSize) {
        if (newSize < 6) {
            size = newSize;
            if (newSize == 5) {
                System.out.println("(!) Sifflement");
                Main.audioControl.play();
            }
            if (newSize == 0) {
                System.out.println("[!] Pacman dead !");
                Main.pbg.remove(this);
                Motor.pacmans.remove(this);
                System.out.println("PACMANS : " + Motor.pacmans.size());
            }
        }
    }

    public void hasEat() {
        eatTime = 2000;
    }

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