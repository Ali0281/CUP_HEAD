package com.example.demo.Controller;

import com.example.demo.Model.*;
import com.example.demo.View.Menus;
import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class Game {
    AudioClip buzzer= new AudioClip(new File("src/main/resources/com/example/demo/music/shoot.wav").toURI().toString());

    private Timeline timelineBackgroundPlayer = new Timeline();
    private Timeline timelineBackgroundBoss = new Timeline();
    private Timeline timelineBackgroundMiniBoss = new Timeline();
    private Timeline garbageCleaner = new Timeline();
    private boolean running = true;

    private long startTime;

    private Airplane airplane = Airplane.getInstance();
    private BigBoss bigBoss = BigBoss.getInstance();

    AirplaneAnimation airplaneAnimation = new AirplaneAnimation();
    BulletsAnimation bulletsAnimation = new BulletsAnimation();
    MiniBossAnimation miniBossAnimation = new MiniBossAnimation();
    BigBossAnimation bigBossAnimation = new BigBossAnimation();
    BigBossBulletAnimation bigBossBulletAnimation = new BigBossBulletAnimation();


    @FXML
    private Pane pane;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Label healthLabel;
    @FXML
    private Label scoreLabel;


    public void initialize() {
        DataBase.getInstance().setLastGameData(new GameData());

        ProgramController.setupBackGround(pane);
        addHealthBar();

        startTime = System.currentTimeMillis();

        airplaneAnimation.play();
        bulletsAnimation.play();
        miniBossAnimation.play();
        bigBossAnimation.play();
        bigBossBulletAnimation.play();

        backgroundTimerPlayer();
        backgroundTimerBoss();
        backgroundTimerMiniBoss();
        backgroundTimerGarbageCleaner();
    }

    private void addHealthBar() {
        for (int i = airplane.getHealth() - 1; i >= 0; i--) {
            Rectangle health = new Rectangle(30, 30);
            health.setX(i * 30);
            try {
                health.setFill(new ImagePattern(new Image(new FileInputStream("src/main/resources/com/example/demo/airplaneImages/health.png"))));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            pane.getChildren().add(health);
        }
    }

    private void backgroundTimerGarbageCleaner() {
        garbageCleaner.setCycleCount(Timeline.INDEFINITE);
        garbageCleaner.getKeyFrames().add(new KeyFrame(Duration.millis(5000), (actionEvent) ->
        {
            for (MiniBoss miniBoss : MiniBoss.getMiniBosses()) {
                if (miniBoss.getX() < 0) pane.getChildren().remove(miniBoss);
                else if (miniBoss.getHealth() <= 0) pane.getChildren().remove(miniBoss);
            }
            for (Bullet bullet : Bullet.getBullets()) {
                if (bullet.getX() > 1280) pane.getChildren().remove(bullet);
                else if (bullet.isDamaged()) pane.getChildren().remove(bullet);
            }
            for (BigBossBullet bullet : BigBossBullet.getBullets()) {
                if (bullet.getX() < 0) pane.getChildren().remove(bullet);
                else if (bullet.isDamaged()) pane.getChildren().remove(bullet);
            }
            MiniBoss.getMiniBosses().removeIf(x -> x.getX() < 0);
            MiniBoss.getMiniBosses().removeIf(x -> x.getHealth() <= 0);

            Bullet.getBullets().removeIf(x -> x.getX() > 1280);
            Bullet.getBullets().removeIf(x -> x.isDamaged());

            BigBossBullet.getBullets().removeIf(x -> x.getX() < 0);
            BigBossBullet.getBullets().removeIf(x -> x.isDamaged());


        }, null, null));

        garbageCleaner.play();

    }

    private void backgroundTimerBoss() {
        timelineBackgroundBoss.setCycleCount(Timeline.INDEFINITE);
        timelineBackgroundBoss.getKeyFrames().add(new KeyFrame(Duration.millis(2000), (actionEvent) ->
        {
            pane.getChildren().add(bigBoss.shoot());
        }, null, null));

        timelineBackgroundBoss.play();

    }

    private void backgroundTimerMiniBoss() {
        timelineBackgroundMiniBoss.setCycleCount(Timeline.INDEFINITE);
        timelineBackgroundMiniBoss.getKeyFrames().add(new KeyFrame(Duration.millis(6000), (actionEvent) ->
        {
            int y_axis = (int) Math.floor(Math.random() * (520 + 1) + 100);
            pane.getChildren().add(new MiniBoss(y_axis));
            pane.getChildren().add(new MiniBoss(y_axis + 60));
            pane.getChildren().add(new MiniBoss(y_axis - 60));


        }, null, null));
        timelineBackgroundMiniBoss.play();

    }


    void backgroundTimerPlayer() {
        timelineBackgroundPlayer.setCycleCount(Timeline.INDEFINITE);
        timelineBackgroundPlayer.getKeyFrames().add(new KeyFrame(Duration.millis(500), (actionEvent) ->
        {
            if (airplane.isShooting() || airplane.isPressedSpace()) {
                pane.getChildren().add(airplane.shoot());
                buzzer.play();
                airplane.setPressedSpace(false);
            }
            // label update
            scoreLabel.setText(String.valueOf(DataBase.getInstance().getLastGameData().getScore()));
            healthLabel.setText(String.valueOf(bigBoss.getHealth()));


            // boss :
            if (bigBoss.getHealth() > 0) progressBar.setProgress(((double) bigBoss.getHealth()) / 20);
            else progressBar.setProgress(((double) bigBoss.getHealth() + 10) / 10);

            // blink
            if (airplane.getBlink_time() == 6) pane.getChildren().remove(3);
            airplane.setBlink_time(airplane.getBlink_time() - 1);
            // end game :

            if (running && bigBoss.getHealth() <= -10) {
                DataBase.getInstance().getLastGameData().setWin(true);
                DataBase.getInstance().getLastGameData().setTime((System.currentTimeMillis() - startTime) / 1000 + 1);
                DataBase.getInstance().getLastGameData().setScore(DataBase.getInstance().getLastGameData().getScore() + 2000 / (int) DataBase.getInstance().getLastGameData().getTime());
                if (DataBase.getInstance().getLoggedInUser() != null && DataBase.getInstance().getLoggedInUser().getHighScore() < DataBase.getInstance().getLastGameData().getScore()) {
                    DataBase.getInstance().getLoggedInUser().setHighScore(DataBase.getInstance().getLastGameData().getScore());
                    DataBase.getInstance().getLoggedInUser().setBestTime((int) DataBase.getInstance().getLastGameData().getTime());
                }

                running = false;


                ProgramController.changeMenu(Menus.SCORE_PAGE);
            } else if (running && airplane.getHealth() <= 0) {
                DataBase.getInstance().getLastGameData().setWin(false);
                DataBase.getInstance().getLastGameData().setTime((System.currentTimeMillis() - startTime) / 1000 + 1);
                if (DataBase.getInstance().getLoggedInUser() != null && DataBase.getInstance().getLoggedInUser().getHighScore() < DataBase.getInstance().getLastGameData().getScore()) {
                    DataBase.getInstance().getLoggedInUser().setHighScore(DataBase.getInstance().getLastGameData().getScore());
                    DataBase.getInstance().getLoggedInUser().setBestTime((int) DataBase.getInstance().getLastGameData().getTime());
                }

                running = false;

                ProgramController.changeMenu(Menus.SCORE_PAGE);
            }

        }, null, null));
        timelineBackgroundPlayer.play();
    }
}
