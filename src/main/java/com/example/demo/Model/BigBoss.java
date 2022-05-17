package com.example.demo.Model;

import javafx.scene.shape.Rectangle;

public class BigBoss extends Rectangle {
    private static BigBoss instance;
    private int speed = 3;
    private int health = 20;

    private BigBoss() {
        super(1100, Math.floor(Math.random() * (520 + 1) + 40), 180, 180);
    }

    public static BigBoss getInstance() {
        if (instance == null) instance = new BigBoss();
        return instance;
    }

    public void move() {
        setY(getY() + speed);
    }

    public void changeDirection() {
        speed *= -1;
    }

    public boolean collisionWithWall() {
        return getY() > 540 || getY() < 0;
    }

    public BigBossBullet shoot() {
        return new BigBossBullet(getX(), getY() + 90);
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
}
