package com.example.demo.Controller;

import com.example.demo.Model.BigBoss;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class BigBossAnimation extends Transition {
    BigBoss bigBoss = BigBoss.getInstance();

    BigBossAnimation() {
        this.setCycleDuration(Duration.millis(7000)); // random
        this.setCycleCount(-1);
    }

    @Override
    protected void interpolate(double frac) {
        int frame = (int) (frac * 60) % 6 + 1;
        try {
            bigBoss.setFill(new ImagePattern(new Image(new FileInputStream("src/main/resources/com/example/demo/airplaneImages/bigBoss/" + frame + ".png"))));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (bigBoss.collisionWithWall()) bigBoss.changeDirection();
        bigBoss.move();
    }

}
