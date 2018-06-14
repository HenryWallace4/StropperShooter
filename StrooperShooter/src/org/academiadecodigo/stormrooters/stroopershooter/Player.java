package org.academiadecodigo.stormrooters.stroopershooter;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.mouse.Mouse;
import org.academiadecodigo.simplegraphics.mouse.MouseEvent;
import org.academiadecodigo.simplegraphics.mouse.MouseEventType;
import org.academiadecodigo.simplegraphics.mouse.MouseHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.stormrooters.stroopershooter.GameObjects.GameObjects;

public class Player {

    private String name;
    private int score;
    private Weapon sniper;
    private int X;
    private int Y;
    private WeaponControl weaponControl;
    private ReloadControl reloadControl;
    private Sound[] sound;

    public Player(String name, Weapon weapon) {

        this.name = name;
        this.sniper = weapon;
        this.weaponControl = new WeaponControl();
        this.reloadControl = new ReloadControl();
        this.sound = new Sound[2];
        sound[0] = new Sound("/blaster.wav");
        sound[1] = new Sound("/nobullets.wav");
    }

    public void shootWeapon() {

        if (sniper.getBulletNumber() > 0) {
            sniper.shoot();
            sound[0].play(true);
        } else {
            sound[1].play(true);
        }
    }

    public void reload() {

        sniper.reload();

    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public void reset() {
        this.X = 0;
        this.Y = 0;
    }

    public int getBulletNumber() {
        return sniper.getBulletNumber();
    }

    public void setScore(int points) {

        this.score += points;
        System.out.println("score: " + score);
    }

    public int getScore() {
        return score;
    }

    public class WeaponControl implements MouseHandler {

        private Mouse handler;

        public WeaponControl() {

            this.handler = new Mouse(this);
            addEventListener();

        }

        public void addEventListener() {

            handler.addEventListener(MouseEventType.MOUSE_CLICKED);
        }

        @Override
        public void mouseClicked(MouseEvent e) {

            X = (int) e.getX();
            Y = (int) e.getY() - 25;
            shootWeapon();
        }

        @Override
        public void mouseMoved(MouseEvent e) {
        }
    }

    public class ReloadControl implements KeyboardHandler {

        public ReloadControl() {

            Keyboard keyboard = new Keyboard(this);

            KeyboardEvent space = new KeyboardEvent();
            space.setKey(KeyboardEvent.KEY_SPACE);
            space.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            keyboard.addEventListener(space);
        }

        @Override
        public void keyPressed(KeyboardEvent e) {
            switch (e.getKey()) {
                case KeyboardEvent.KEY_SPACE:
                    reload();
                    System.out.println("thaReaLoad");
            }
        }

        @Override
        public void keyReleased(KeyboardEvent e) {

        }
    }
}
