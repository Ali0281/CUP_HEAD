package com.example.demo.Controller.animationController;

import com.example.demo.Model.*;
import com.example.demo.View.Component.Airplane;
import com.example.demo.View.Component.BigBossBullet;
import com.example.demo.View.Component.MiniBoss;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class AirplaneAnimation extends Transition {
    Airplane airplane = Airplane.getInstance();

    public AirplaneAnimation() {
        this.setCycleDuration(Duration.millis(2000)); // random
        this.setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {
        MiniBoss tempMiniBoss;
        BigBossBullet tempBigBossBullet;
        airplane.move();
        try {
            if (airplane.isBlink()) {
                if (Math.floor(Math.random() + 0.2) == 1) airplane.setFill(Color.TRANSPARENT);
                else {
                    airplane.setFill(new ImagePattern(new Image(new FileInputStream("src/main/resources/com/example/demo/airplaneImages/redAirplane.png"))));
                }
            } else {
                airplane.setFill(new ImagePattern(new Image(new FileInputStream("src/main/resources/com/example/demo/airplaneImages/redAirplane.png"))));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (airplane.isBlink()) return;
        if ((tempMiniBoss = airplane.collisionWithMiniBoss()) != null) collisionMiniBoss(tempMiniBoss);
        else if ((tempBigBossBullet = airplane.collisionWithBigBossBullet()) != null)
            collisionBigBossBullet(tempBigBossBullet);
        else if (airplane.collisionWithBigBoss()) collisionBigBoss();
    }

    private void collisionBigBossBullet(BigBossBullet tempBigBossBullet) {
        airplane.takeDamage(1);
        airplane.blink();
        tempBigBossBullet.disappear();
        DataBase.getInstance().getLastGameData().setScore(DataBase.getInstance().getLastGameData().getScore() + 10);
    }

    private void collisionBigBoss() {
        airplane.takeDamage(1);
        airplane.blink();
    }

    private void collisionMiniBoss(MiniBoss tempMiniBoss) {
        airplane.takeDamage(1);
        airplane.blink();
        tempMiniBoss.died();
        DataBase.getInstance().getLastGameData().setScore(DataBase.getInstance().getLastGameData().getScore() + 20);

    }
}
