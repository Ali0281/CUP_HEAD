package com.example.demo.Controller;

import com.example.demo.Model.DataBase;
import com.example.demo.Model.GameData;
import com.example.demo.View.Menus;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ScorePageController {
    @FXML
    private BorderPane pane;
    @FXML
    private VBox vBox;

    public void initialize() {
        ProgramController.setupBackGround(pane);
        GameData gameData = DataBase.getInstance().getLastGameData();
        if (gameData.isWin()) vBox.getChildren().add(new Text("you won!!"));
        else vBox.getChildren().add(new Text("you lost!!"));
        vBox.getChildren().add(new Text("score : " + gameData.getScore()));
        vBox.getChildren().add(new Text("time : " + gameData.getTime()));
    }

    public void gotoMainMenu(MouseEvent mouseEvent) {
        ProgramController.changeMenu(Menus.MAIN_MENU);
    }
}
