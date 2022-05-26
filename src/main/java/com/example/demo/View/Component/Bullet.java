package com.example.demo.View.Component;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Bullet extends Rectangle {
    private static ArrayList<Bullet> bullets = new ArrayList<>();

    private boolean damaged = false;

    public Bullet(double x, double y) {
        super(x, y, 30, 30);
        try {
            setFill(new ImagePattern(new Image(new FileInputStream("src/main/resources/com/example/demo/airplaneImages/bullet.png"))));
        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        }
        bullets.add(this);
    }

    public static ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public static void setBullets(ArrayList<Bullet> bullets) {
        Bullet.bullets = bullets;
    }

    public void move() {
        setX(getX() + 5);
    }

    public MiniBoss collisionWithMiniBoss() {
        for (MiniBoss miniBoss : MiniBoss.getMiniBosses()) {
            if (miniBoss.getFill() == Color.TRANSPARENT) continue;
            if (miniBoss.getBoundsInParent().intersects(this.getBoundsInParent()))
                return miniBoss;
        }
        return null;
    }

    public boolean collisionWithBigBoss() {
        return BigBoss.getInstance().getBoundsInParent().intersects(this.getBoundsInParent());
    }

    public BigBossBullet collisionWithBigBossBullet() {
        for (BigBossBullet bullet : BigBossBullet.getBullets()) {
            if (bullet.isDamaged()) continue;
            if (bullet.getBoundsInParent().intersects(this.getBoundsInParent()))
                return bullet;
        }
        return null;
    }

    public boolean isDamaged() {
        return damaged;
    }

    public void setDamaged(boolean damaged) {
        this.damaged = damaged;
    }

    public void disappear() {
        setDamaged(true);
        setFill(Color.TRANSPARENT);
    }
}