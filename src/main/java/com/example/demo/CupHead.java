package com.example.demo;

import com.example.demo.Controller.ProgramController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;

public class CupHead extends Application {
    public static Stage stage;
    public static MediaPlayer mediaPlayer;

    @Override
    public void start(Stage stage) throws IOException {
//        ProgramController.loadUsersData();

        FXMLLoader fxmlLoader = new FXMLLoader(CupHead.class.getResource("loginPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);

        ProgramController.playMenuSong();

        CupHead.stage = stage;

        stage.setResizable(false);
        stage.setTitle("cup-head");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() {
//        ProgramController.saveUsersData();
        // save game ?!?!
    }

    public static void main(String[] args) {
        launch();
    }
}
