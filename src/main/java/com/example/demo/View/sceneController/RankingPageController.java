package com.example.demo.View.sceneController;

import com.example.demo.Controller.ProgramController;
import com.example.demo.Model.DataBase;
import com.example.demo.Model.User;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Collections;


public class RankingPageController {
    DataBase dataBase = DataBase.getInstance();
    @FXML
    private BorderPane pane;
    @FXML
    private VBox username;
    @FXML
    private VBox score;
    @FXML
    private VBox time;


    public void initialize() {
        ProgramController.setupBackGround(pane);
        sortByHighScore(dataBase.getUsers());
        for (int i = 0; i < Math.min(10, dataBase.getUsers().size()); i++) {
            Text usernameText = new Text((i + 1) + ": " + dataBase.getUsers().get(i).getUsername());
            Text scoreText = new Text(Integer.toString(dataBase.getUsers().get(i).getHighScore()));
            Text timeText = new Text(Integer.toString(dataBase.getUsers().get(i).getBestTime()));
            // doesnt work !!!
            switch (i) {
                case 0:
                    usernameText.setStyle("-fx-background-color: gold");
                    scoreText.setStyle("-fx-background-color: gold");
                    timeText.setStyle("-fx-background-color: gold");
                    break;
                case 1:
                    usernameText.setStyle("-fx-background-color: silver");
                    scoreText.setStyle("-fx-background-color: silver");
                    timeText.setStyle("-fx-background-color: silver");
                    break;
                case 2:
                    usernameText.setStyle("-fx-background-color: gray");
                    scoreText.setStyle("-fx-background-color: gray");
                    timeText.setStyle("-fx-background-color: gray");
                    break;
                default:
                    usernameText.setStyle("-fx-background-color: #00ffea");
                    scoreText.setStyle("-fx-background-color: #00ffea");
                    timeText.setStyle("-fx-background-color: #00ffea");
            }
            username.getChildren().add(usernameText);
            score.getChildren().add(scoreText);
            time.getChildren().add(timeText);
        }
    }

    private void sortByHighScore(ArrayList<User> users) {
        for (int i = 0; i < users.size(); i++) {
            for (int j = i + 1; j < users.size(); j++) {
                if (users.get(i).getHighScore() < users.get(j).getHighScore()
                        || (users.get(i).getHighScore() == users.get(j).getHighScore()
                        && users.get(i).getBestTime() > users.get(j).getBestTime()))
                    Collections.swap(users, i, j);
            }
        }
    }
}
