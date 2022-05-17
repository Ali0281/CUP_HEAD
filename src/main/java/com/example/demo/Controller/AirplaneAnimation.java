package com.example.demo.Controller;

import com.example.demo.Model.Airplane;
import com.example.demo.Model.BigBoss;
import com.example.demo.Model.BigBossBullet;
import com.example.demo.Model.MiniBoss;
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
        MiniBoss tempMiniBoss;
        BigBossBullet tempBigBossBullet;
        airplane.move();
        if ((tempMiniBoss = airplane.collisionWithMiniBoss()) != null) collisionMiniBoss(tempMiniBoss);
        else if ((tempBigBossBullet = airplane.collisionWithBigBossBullet()) != null)
            collisionBigBossBullet(tempBigBossBullet);
        else if (airplane.collisionWithBigBoss()) collisionBigBoss();
    }

    private void collisionBigBossBullet(BigBossBullet tempBigBossBullet) {
        airplane.takeDamage(1); // ?!?

        tempBigBossBullet.disappear();
    }

    private void collisionBigBoss() {
        airplane.takeDamage(1); // ??! can end the game !!?

        BigBoss.getInstance().takeDamage(1); // !?!?
    }

    private void collisionMiniBoss(MiniBoss tempMiniBoss) {
        airplane.takeDamage(1); // ?!?!

        tempMiniBoss.died(); // asdklflds
    }
}
