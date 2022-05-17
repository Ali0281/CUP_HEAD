package com.example.demo.Controller;

import com.example.demo.CupHead;
import com.example.demo.Model.DataBase;
import com.example.demo.View.Menus;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MainMenuPageController {
    DataBase dataBase = DataBase.getInstance();

    @FXML
    private BorderPane pane;
    @FXML
    private VBox vBox;
    private Text error;


    public void initialize() {
        ProgramController.setupBackGround(pane);
    }


    public void startNewGame(MouseEvent mouseEvent) {
        if (error!=null) {vBox.getChildren().remove(error); error=null;}
        //todo
        ProgramController.changeMenu(Menus.GAME_MENU);
    }

    public void continueGame(MouseEvent mouseEvent) {
        if (error!=null) {vBox.getChildren().remove(error); error=null;}
        if (dataBase.getLoggedInUser() == null) {
            error = new Text("you are a guest !");
            vBox.getChildren().add(error);
            return;
        }
        ProgramController.changeMenu(Menus.GAME_MENU);
    }

    public void setUpProfilePage(MouseEvent mouseEvent) {
        if (error!=null) {vBox.getChildren().remove(error); error=null;}
        if (dataBase.getLoggedInUser() == null) {
            error = new Text("you are a guest !");
            vBox.getChildren().add(error);
            return;
        }
        ProgramController.changeMenu(Menus.PROFILE_MENU);
    }

    public void setupRankingPage(MouseEvent mouseEvent) {
        if (error!=null) {vBox.getChildren().remove(error); error=null;}
        ProgramController.changeMenu(Menus.RANKING_PAGE);
    }

    public void exit(MouseEvent mouseEvent) {
        if (error!=null) {vBox.getChildren().remove(error); error=null;}
        Platform.exit();
    }
}
