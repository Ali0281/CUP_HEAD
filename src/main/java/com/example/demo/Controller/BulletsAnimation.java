package com.example.demo.Controller;

import com.example.demo.Model.BigBoss;
import com.example.demo.Model.BigBossBullet;
import com.example.demo.Model.Bullet;
import com.example.demo.Model.MiniBoss;
import javafx.animation.Transition;
import javafx.scene.paint.Color;
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
            bullet.move();
            if ((tempMiniBoss = bullet.collisionWithMiniBoss()) != null) collisionMiniBoss(tempMiniBoss, bullet);
            else if ((tempBigBossBullet = bullet.collisionWithBigBossBullet()) != null)
                collisionBigBossBullet(tempBigBossBullet, bullet);
            else if (bullet.collisionWithBigBoss()) collisionBigBoss(bullet);
        }
    }

    private void collisionBigBoss(Bullet bullet) {
        bullet.setDamaged(true);
        bullet.setFill(Color.TRANSPARENT);

        BigBoss.getInstance().takeDamage(1);
        if (BigBoss.getInstance().idDead()) return; //todo
    }

    private void collisionBigBossBullet(BigBossBullet tempBigBossBullet, Bullet bullet) {
        bullet.setDamaged(true);
        bullet.setFill(Color.TRANSPARENT);

        tempBigBossBullet.setDamaged(true);
        tempBigBossBullet.setFill(Color.TRANSPARENT);
    }

    private void collisionMiniBoss(MiniBoss tempMiniBoss, Bullet bullet) {
        bullet.setDamaged(true);
        bullet.setFill(Color.TRANSPARENT);

        tempMiniBoss.takeDamage(1);
        if (tempMiniBoss.isDead()) tempMiniBoss.died();

    }

}
