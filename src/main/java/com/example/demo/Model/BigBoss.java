package com.example.demo.Model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BigBoss extends Rectangle {
    private static BigBoss instance;
    private int speed = 3;
    private int direction = 1;
    private int health = 20;

    private BigBoss() {
        super(1100, Math.floor(Math.random() * (520 + 1) + 40), 180, 180);
    }

    public static BigBoss getInstance() {
        if (instance == null) instance = new BigBoss();
        return instance;
    }

    public void phase2Move() {
        if (getX() - Airplane.getInstance().getX() > 800) moveLeft();
        if (getX() - Airplane.getInstance().getX() < 200) moveRight();
        if (getY() > Airplane.getInstance().getY()) moveUp();
        if (getY() < Airplane.getInstance().getY()) moveDown();
    }

    private void moveRight() {
        if (getX() >= 1100) return;
        setX(getX() + speed);
    }

    private void moveLeft() {
        if (getX() <= 0) return;
        setX(getX() - speed);
    }

    private void moveDown() {
        if (getY() >= 540) return;
        setY(getY() + speed);
    }

    private void moveUp() {
        if (getY() <= 0) return;
        setY(getY() - speed);
    }

    public void phase1Move() {
        setY(getY() + speed * direction);
    }

    public void changeDirection() {
        direction *= -1;
    }

    public boolean phase1Collision() {
        return getY() > 540 || getY() < 0;
    }

    public boolean phase3Collision() {
        return getX() < 0 || getX() > 1100;
    }


    public BigBossBullet shoot() {
        if (getHealth() > 0) return new BigBossBullet(getX(), getY() + 90);
        else return new BigBossBullet(getX() + 90, getY());
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void takeDamage(int i) {
        setHealth(getHealth() - i);
    }

    public boolean idDead() {
        return getHealth() <= 0;
    }

    public void died() {
        setFill(Color.TRANSPARENT);
    }

    public void phase3Move() {
        if (getY() < 540) setY(getY() + speed);
        else setX(getX() + speed * direction);
    }
}
