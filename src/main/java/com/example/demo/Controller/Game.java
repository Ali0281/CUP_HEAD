package com.example.demo.Controller;

import com.example.demo.Model.*;
import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.util.Duration;


public class Game {
    private Timeline timelineBackgroundPlayer = new Timeline();
    private Timeline timelineBackgroundBoss = new Timeline();
    private Timeline timelineBackgroundMiniBoss = new Timeline();
    private Timeline garbageCleaner = new Timeline();


    private Airplane airplane = Airplane.getInstance();
    private BigBoss bigBoss = BigBoss.getInstance();

    @FXML
    private Pane pane;


    public void initialize() {
        ProgramController.setupBackGround(pane);

        AirplaneAnimation airplaneAnimation = new AirplaneAnimation();
        // hit --> miniBoss bigBoss bigBossBullet
        airplaneAnimation.play();

        BulletsAnimation bulletsAnimation = new BulletsAnimation();
        // hit --> miniBoss bigBoss bigBossBullet
        bulletsAnimation.play();

        MiniBossAnimation miniBossAnimation = new MiniBossAnimation();
        miniBossAnimation.play();

        BigBossAnimation bigBossAnimation = new BigBossAnimation();
        bigBossAnimation.play();

        BigBossBulletAnimation bigBossBulletAnimation = new BigBossBulletAnimation();
        bigBossBulletAnimation.play();

        backgroundTimerPlayer();
        backgroundTimerBoss();
        backgroundTimerMiniBoss();
        backgroundTimerGarbageCleaner();
    }

    private void backgroundTimerGarbageCleaner() {
        garbageCleaner.setCycleCount(Timeline.INDEFINITE);
        garbageCleaner.getKeyFrames().add(new KeyFrame(Duration.millis(5000), (actionEvent) ->
        {
            for (MiniBoss miniBoss : MiniBoss.getMiniBosses()) {
                if (miniBoss.getX() < 0) pane.getChildren().remove(miniBoss);
            }
            for (Bullet bullet : Bullet.getBullets()) {
                if (bullet.getX() > 1280) pane.getChildren().remove(bullet);
            }
            for (BigBossBullet bullet : BigBossBullet.getBullets()) {
                if (bullet.getX() < 0) pane.getChildren().remove(bullet);
            }
            MiniBoss.getMiniBosses().removeIf(x -> x.getX() < 0);
            Bullet.getBullets().removeIf(x -> x.getX() > 1280);
            BigBossBullet.getBullets().removeIf(x -> x.getX() < 0);

        }, null, null));

        garbageCleaner.play();
    }

    private void backgroundTimerBoss() {
        timelineBackgroundBoss.setCycleCount(Timeline.INDEFINITE);
        timelineBackgroundBoss.getKeyFrames().add(new KeyFrame(Duration.millis(2000), (actionEvent) ->
        {
            pane.getChildren().add(new MiniBoss());
        }, null, null));

        timelineBackgroundBoss.play();
    }

    private void backgroundTimerMiniBoss() {
        timelineBackgroundMiniBoss.setCycleCount(Timeline.INDEFINITE);
        timelineBackgroundMiniBoss.getKeyFrames().add(new KeyFrame(Duration.millis(3000), (actionEvent) ->
        {
            pane.getChildren().add(bigBoss.shoot());
        }, null, null));

        timelineBackgroundMiniBoss.play();
    }


    void backgroundTimerPlayer() {
        timelineBackgroundPlayer.setCycleCount(Timeline.INDEFINITE);
        timelineBackgroundPlayer.getKeyFrames().add(new KeyFrame(Duration.millis(500), (actionEvent) ->
        {
            if (airplane.isShooting() || airplane.isPressedSpace()) {
                pane.getChildren().add(airplane.shoot());
                airplane.setPressedSpace(false);
            }
        }, null, null));

        timelineBackgroundPlayer.play();
    }
}
