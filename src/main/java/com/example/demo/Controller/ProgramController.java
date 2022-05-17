package com.example.demo.Controller;

import com.example.demo.CupHead;
import com.example.demo.Model.*;
import com.example.demo.View.Menus;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ProgramController {

    public static void changeMenu(Menus menu) {
        Scene scene = null;
        if (menu != Menus.GAME_MENU) {
            try {
                scene = new Scene(new FXMLLoader(CupHead.class.getResource(menu.getName())).load(), 1280, 720);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (scene == null) return;
            CupHead.stage.setScene(scene);
            CupHead.stage.show();
            return;
        }
        ProgramController.playGameSong();
        Pane pane = null;
        try {
            pane = FXMLLoader.load(CupHead.class.getResource(menu.getName()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (pane == null) return;
        setupGamePane(pane);
        scene = new Scene(pane);
        CupHead.stage.setScene(scene);
        CupHead.stage.show();
        pane.getChildren().get(0).requestFocus();

    }

    private static void setupGamePane(Pane pane) {
        Airplane airplane = Airplane.getInstance();

        pane.getChildren().add(Airplane.getInstance());
        pane.getChildren().add(BigBoss.getInstance());

        pane.getChildren().get(0).setOnKeyPressed(
                new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent keyEvent) {
                        switch (keyEvent.getCode()) {
                            case DOWN, S -> airplane.setGoingDown(true);
                            case UP, W -> airplane.setGoingUp(true);
                            case RIGHT, D -> airplane.setGoingRight(true);
                            case LEFT, A -> airplane.setGoingLeft(true);
                            case SPACE -> {airplane.setShooting(true); airplane.setPressedSpace(true);}
                        }
                    }
                });
        pane.getChildren().get(0).setOnKeyReleased(
                new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent keyEvent) {
                        switch (keyEvent.getCode()) {
                            case DOWN, S -> airplane.setGoingDown(false);
                            case UP, W -> airplane.setGoingUp(false);
                            case RIGHT, D -> airplane.setGoingRight(false);
                            case LEFT, A -> airplane.setGoingLeft(false);
                            case SPACE -> airplane.setShooting(false);
                        }
                    }
                });
    }

    public static FXMLLoader getFXML(Menus menu) {
        return new FXMLLoader(CupHead.class.getResource(menu.getName()));
    }

    public static TextField creatTextField(String text) {
        TextField textField = new TextField();
        textField.setPromptText(text);
        return textField;
    }


    public static void setupBackGround(Pane pane) {
        DoubleProperty xPosition = new SimpleDoubleProperty(0);
        xPosition.addListener((observable, oldValue, newValue) -> setBackgroundPositions(pane, xPosition.get()));
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(xPosition, 0)),
                new KeyFrame(Duration.seconds(200), new KeyValue(xPosition, -15000))
        );
        timeline.play();
    }

    public static void setupBackGround(BorderPane pane) {
        DoubleProperty xPosition = new SimpleDoubleProperty(0);
        xPosition.addListener((observable, oldValue, newValue) -> setBackgroundPositions(pane, xPosition.get()));
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(xPosition, 0)),
                new KeyFrame(Duration.seconds(200), new KeyValue(xPosition, -15000))
        );
        timeline.play();
    }

    static void setBackgroundPositions(Region region, double xPosition) {
        String style = "-fx-background-position: " +
                "left " + xPosition / 6 + "px bottom," +
                "left " + xPosition / 5 + "px bottom," +
                "left " + xPosition / 4 + "px bottom," +
                "left " + xPosition / 3 + "px bottom," +
                "left " + xPosition / 2 + "px bottom," +
                "left " + xPosition + "px bottom;";
        region.setStyle(style);
    }

    public static void playMenuSong() {
//        final File file = new File("src/main/resources/com/example/demo/music/menuMusic.mp3");
//        if (CupHead.mediaPlayer != null) CupHead.mediaPlayer.pause();
//        CupHead.mediaPlayer = new MediaPlayer(new Media(file.toURI().toString()));
//        CupHead.mediaPlayer.setCycleCount(-1);
//        CupHead.mediaPlayer.play();
    }

    public static void playGameSong() {
//        final File file = new File("src/main/resources/com/example/demo/music/gameMusic.mp3");
//        if (CupHead.mediaPlayer != null) CupHead.mediaPlayer.pause();
//        CupHead.mediaPlayer = new MediaPlayer(new Media(file.toURI().toString()));
//        CupHead.mediaPlayer.setCycleCount(-1);
//        CupHead.mediaPlayer.play();
    }

    public static void saveUsersData() {
        ArrayList<User> temp = createAClone();
        try {
            FileWriter myWriter = new FileWriter("src/main/resources/com/example/demo/savedData/users.json");
            myWriter.write(new GsonBuilder().create().toJson(temp));
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadUsersData() {
        ArrayList<User> temp = new ArrayList<>();
        try {
            String users = new String(Files.readAllBytes(Paths.get("src/main/resources/com/example/demo/savedData/users.json")));
            temp = new GsonBuilder().create().fromJson(users, new TypeToken<List<User>>() {
            }.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        setupDataBase(temp);
    }

    private static void setupDataBase(ArrayList<User> temp) {
        for (User user : temp) {
            DataBase.getInstance().getUsers().add(user);
        }
    }

    private static ArrayList<User> createAClone() {
//        ArrayList<User> temp = new ArrayList<>();
//        for (User user : DataBase.getInstance().getUsers()) {
//            temp.add(user);
//        }
//        return temp;

        return (ArrayList<User>) DataBase.getInstance().getUsers().clone();
    }
}
