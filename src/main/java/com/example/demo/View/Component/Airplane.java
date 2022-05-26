package com.example.demo.View.Component;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Airplane extends Rectangle {
    private static Airplane instance;

    private int health = 5;

    private boolean goingRight = false;
    private boolean goingLeft = false;
    private boolean goingUp = false;
    private boolean goingDown = false;
    private boolean shooting = false;
    private boolean pressedSpace = false;

    private int blink_time = 0;

    private Airplane() {
        super(50, 300, 120, 120);
        try {
            setFill(new ImagePattern(new Image(new FileInputStream("src/main/resources/com/example/demo/airplaneImages/redAirplane.png"))));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Airplane getInstance() {
        if (instance == null) instance = new Airplane();
        return instance;
    }

    public void move() {
        int y = 0, x = 0;
        if (goingUp) y -= 1;
        if (goingDown) y += 1;
        if (goingLeft) x -= 1;
        if (goingRight) x += 1;

        x *= 4;
        y *= 4;

        if (!(getX() + x > 1160 || getX() + x < 0)) setX(getX() + x);
        if (!(getY() + y > 600 || getY() + y < 0)) setY(getY() + y);
    }

    public boolean isGoingRight() {
        return goingRight;
    }

    public void setGoingRight(boolean goingRight) {
        this.goingRight = goingRight;
    }

    public boolean isGoingLeft() {
        return goingLeft;
    }

    public void setGoingLeft(boolean goingLeft) {
        this.goingLeft = goingLeft;
    }

    public boolean isGoingUp() {
        return goingUp;
    }

    public void setGoingUp(boolean goingUp) {
        this.goingUp = goingUp;
    }

    public boolean isGoingDown() {
        return goingDown;
    }

    public void setGoingDown(boolean goingDown) {
        this.goingDown = goingDown;
    }

    public boolean isShooting() {
        return shooting;
    }

    public void setShooting(boolean shooting) {
        this.shooting = shooting;
    }

    public Bullet shoot() {
        return new Bullet(getX() + 120, getY() + 60);
    }

    public boolean isPressedSpace() {
        return pressedSpace;
    }

    public void setPressedSpace(boolean pressedSpace) {
        this.pressedSpace = pressedSpace;
    }

    public boolean collisionWithBigBoss() {
        return BigBoss.getInstance().getBoundsInParent().intersects(this.getBoundsInParent());
    }

    public BigBossBullet collisionWithBigBossBullet() {
        for (BigBossBullet bullet : BigBossBullet.getBullets()) {
            if (bullet.isDamaged()) continue;
            if (bullet.getBoundsInParent().intersects(this.getBoundsInParent())) return bullet;
        }
        return null;
    }

    public MiniBoss collisionWithMiniBoss() {
        for (MiniBoss miniBoss : MiniBoss.getMiniBosses()) {
            if (miniBoss.getFill() == Color.TRANSPARENT) continue;
            if (miniBoss.getBoundsInParent().intersects(this.getBoundsInParent())) return miniBoss;
        }
        return null;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void takeDamage(int amount) {
        setHealth(getHealth() - amount);
    }

    public boolean isBlink() {
        return blink_time > 0;
    }

    public void blink() {
        setBlink_time(6);
    }

    public int getBlink_time() {
        return blink_time;
    }

    public void setBlink_time(int blink_time) {
        this.blink_time = blink_time;
    }
}
