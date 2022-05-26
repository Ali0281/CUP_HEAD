package com.example.demo.Controller.animationController;

import com.example.demo.View.Component.BigBoss;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class BigBossAnimation extends Transition {
    BigBoss bigBoss = BigBoss.getInstance();

    public BigBossAnimation() {
        this.setCycleDuration(Duration.millis(7000)); // random
        this.setCycleCount(-1);
    }

    @Override
    protected void interpolate(double frac) {
        if (bigBoss.getFill() == Color.TRANSPARENT) return; //debugged

        int frame = (int) (frac * 60) % 6 + 1;
        try {
            if (bigBoss.getHealth() > 10)
                bigBoss.setFill(new ImagePattern(new Image(new FileInputStream("src/main/resources/com/example/demo/airplaneImages/bigBoss/phase1/" + frame + ".png"))));
            else if (bigBoss.getHealth() > 0)
                bigBoss.setFill(new ImagePattern(new Image(new FileInputStream("src/main/resources/com/example/demo/airplaneImages/bigBoss/phase2/" + frame + ".png"))));
            else if (bigBoss.getHealth() > -10)
                bigBoss.setFill(new ImagePattern(new Image(new FileInputStream("src/main/resources/com/example/demo/airplaneImages/bigBoss/phase3/" + frame + ".png"))));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (bigBoss.getHealth() > 10) {
            if (bigBoss.phase1Collision()) bigBoss.changeDirection();
            bigBoss.phase1Move();
        } else if (bigBoss.getHealth() > 0) {
            bigBoss.phase2Move();
        } else if (bigBoss.getHealth() > -10) {
            if (bigBoss.phase3Collision()) bigBoss.changeDirection();
            bigBoss.phase3Move();
        }
    }

}
