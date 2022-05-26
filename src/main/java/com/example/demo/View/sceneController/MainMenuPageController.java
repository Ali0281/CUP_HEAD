package com.example.demo.View.sceneController;

import com.example.demo.Controller.ProgramController;
import com.example.demo.Model.DataBase;
import com.example.demo.View.Menus;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MainMenuPageController {
    DataBase dataBase = DataBase.getInstance();

    @FXML
    private BorderPane pane;
    @FXML
    private VBox vBox;
    @FXML
    private Rectangle rectangle;
    private Text error;


    public void initialize() {
        ProgramController.setupBackGround(pane);
        try {
            if (dataBase.getLoggedInUser() == null)
                rectangle.setFill(new ImagePattern(new Image(new FileInputStream("src/main/resources/com/example/demo/avatars/" +((int) (Math.random() * 6) + 1) + ".png"))));
            else
                rectangle.setFill(new ImagePattern(new Image(new FileInputStream("src/main/resources/com/example/demo/avatars/" + dataBase.getLoggedInUser().getAvatar() + ".png"))));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


    public void startNewGame(MouseEvent mouseEvent) {
        if (error != null) {
            vBox.getChildren().remove(error);
            error = null;
        }
        //todo
        ProgramController.changeMenu(Menus.GAME_MENU);
    }

    public void continueGame(MouseEvent mouseEvent) {
        if (error != null) {
            vBox.getChildren().remove(error);
            error = null;
        }
        if (dataBase.getLoggedInUser() == null) {
            error = new Text("you are a guest !");
            vBox.getChildren().add(error);
            return;
        }
        ProgramController.changeMenu(Menus.GAME_MENU);
    }

    public void setUpProfilePage(MouseEvent mouseEvent) {
        if (error != null) {
            vBox.getChildren().remove(error);
            error = null;
        }
        if (dataBase.getLoggedInUser() == null) {
            error = new Text("you are a guest !");
            vBox.getChildren().add(error);
            return;
        }
        ProgramController.changeMenu(Menus.PROFILE_MENU);
    }

    public void setupRankingPage(MouseEvent mouseEvent) {
        if (error != null) {
            vBox.getChildren().remove(error);
            error = null;
        }
        ProgramController.changeMenu(Menus.RANKING_PAGE);
    }

    public void exit(MouseEvent mouseEvent) {
        if (error != null) {
            vBox.getChildren().remove(error);
            error = null;
        }
        Platform.exit();
    }
}
