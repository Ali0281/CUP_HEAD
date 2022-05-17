package com.example.demo.Controller;

import com.example.demo.Model.Airplane;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

public class AirplaneAnimation extends Transition {
    Airplane airplane = Airplane.getInstance();

    AirplaneAnimation() {
        this.setCycleDuration(Duration.millis(2000)); // random
        this.setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {
        airplane.move();
        if (airplane.collisionWithMiniBoss() != null) return; // delete bullet -- del mini boss ?
        if (airplane.collisionWithBigBossBullet() != null) return; // delete both bullets
        if (airplane.collisionWithBigBoss()) return; //damage -- delete bullet
    }
}
