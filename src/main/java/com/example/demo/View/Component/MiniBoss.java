package com.example.demo.View.Component;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class MiniBoss extends Rectangle {
    private static ArrayList<MiniBoss> miniBosses = new ArrayList<>();

    private int health = 2;

    public MiniBoss(int random) {
        super(1280, random , 60, 60);
        miniBosses.add(this);

    }

    public static ArrayList<MiniBoss> getMiniBosses() {
        return miniBosses;
    }

    public static void setMiniBosses(ArrayList<MiniBoss> miniBosses) {
        MiniBoss.miniBosses = miniBosses;
    }

    public void move() {
        setX(getX() - 5);
    }


    public boolean collisionWithAirplane() {
        return getBoundsInParent().intersects(Airplane.getInstance().getBoundsInParent());
    }

    public void suicideBomb() {
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

    public boolean isDead() {
        return getHealth() <= 0;
    }

    public void died() {
        setHealth(0);
        setFill(Color.TRANSPARENT);
    }
}
