package com.example.demo.Controller;

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
    private VBox rankingTable;


    public void initialize() {
        ProgramController.setupBackGround(pane);
        sortByHighScore(dataBase.getUsers());
        ArrayList<Text> texts = new ArrayList<>();
        for (User user : dataBase.getUsers()) {
            texts.add(createText(user));
        }
        rankingTable.getChildren().addAll(texts);
    }

    private Text createText(User user) {
        Text text = new Text();
        // set

        // design
        return text;
    }

    private void sortByHighScore(ArrayList<User> users) {
        for (int i = 0; i < users.size(); i++) {
            for (int j = i + 1; j < users.size(); j++) {
                if (users.get(i).getHighScore() < users.get(j).getHighScore()) Collections.swap(users, i, j);
            }
        }
    }
}
