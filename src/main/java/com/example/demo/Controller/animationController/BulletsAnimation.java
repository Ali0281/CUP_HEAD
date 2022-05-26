package com.example.demo.Controller.animationController;

import com.example.demo.Model.*;
import com.example.demo.View.Component.BigBoss;
import com.example.demo.View.Component.BigBossBullet;
import com.example.demo.View.Component.Bullet;
import com.example.demo.View.Component.MiniBoss;
import javafx.animation.Transition;
import javafx.util.Duration;

import java.util.ArrayList;

public class BulletsAnimation extends Transition {
    ArrayList<Bullet> bullets = Bullet.getBullets();

    public BulletsAnimation() {
        this.setCycleDuration(Duration.millis(7000)); // random
        this.setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {
        MiniBoss tempMiniBoss;
        BigBossBullet tempBigBossBullet;
        for (Bullet bullet : bullets) {
            if (bullet.isDamaged()) continue;
            bullet.move();
            if ((tempMiniBoss = bullet.collisionWithMiniBoss()) != null) collisionMiniBoss(tempMiniBoss, bullet);
            else if ((tempBigBossBullet = bullet.collisionWithBigBossBullet()) != null)
                collisionBigBossBullet(tempBigBossBullet, bullet);
            else if (bullet.collisionWithBigBoss()) collisionBigBoss(bullet);
        }
    }

    private void collisionBigBoss(Bullet bullet) {
        bullet.disappear();
        BigBoss.getInstance().takeDamage(1);
    }

    private void collisionBigBossBullet(BigBossBullet tempBigBossBullet, Bullet bullet) {
        bullet.disappear();

        tempBigBossBullet.disappear();
        DataBase.getInstance().getLastGameData().setScore(DataBase.getInstance().getLastGameData().getScore() + 10);

    }

    private void collisionMiniBoss(MiniBoss tempMiniBoss, Bullet bullet) {
        bullet.disappear();

        tempMiniBoss.takeDamage(1);
        if (tempMiniBoss.isDead()) {
            tempMiniBoss.died();
            DataBase.getInstance().getLastGameData().setScore(DataBase.getInstance().getLastGameData().getScore() + 20);
        }

    }

}