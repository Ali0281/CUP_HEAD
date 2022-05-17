package com.example.demo.Controller;

import com.example.demo.Model.BigBossBullet;
import com.example.demo.Model.Bullet;
import javafx.animation.Transition;
import javafx.util.Duration;

import java.util.ArrayList;

public class BigBossBulletAnimation extends Transition {
    ArrayList<BigBossBullet> bigBossBullets = BigBossBullet.getBullets();

    public BigBossBulletAnimation() {
        this.setCycleDuration(Duration.millis(7000)); // random
        this.setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {
        for (BigBossBullet bigBossBullet : bigBossBullets) {
            bigBossBullet.move();
        }
    }
}
