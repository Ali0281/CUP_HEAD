package com.example.demo.Model;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class BigBossBullet extends Rectangle {
    private static ArrayList<BigBossBullet> bullets = new ArrayList<>();

    private boolean damaged = false;

    public BigBossBullet(double x, double y) {
        super(x, y, 60, 60);
        try {
            setFill(new ImagePattern(new Image(new FileInputStream("src/main/resources/com/example/demo/airplaneImages/bigBossBullet.png"))));
        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        }
        bullets.add(this);
    }

    public static ArrayList<BigBossBullet> getBullets() {
        return bullets;
    }

    public static void setBullets(ArrayList<BigBossBullet> bullets) {
        BigBossBullet.bullets = bullets;
    }

    public void move() {
        setX(getX() - 5);
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

