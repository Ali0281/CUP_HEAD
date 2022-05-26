package com.example.demo.View.sceneController;

import com.example.demo.Controller.ProgramController;
import com.example.demo.Model.DataBase;
import com.example.demo.View.Menus;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ProfilePageController {
    private final DataBase dataBase = DataBase.getInstance();

    @FXML
    private BorderPane pane;
    @FXML
    private VBox vBox;

    private TextField username = null;
    private TextField password = null;



    public void initialize() {
        ProgramController.setupBackGround(pane);
    }


    public void changeUserAndPass(MouseEvent mouseEvent) {
        if (username == null && password == null) {
            username = ProgramController.creatTextField("enter new username");
            password = ProgramController.creatTextField("enter new password");
            vBox.getChildren().add(username);
            vBox.getChildren().add(password);
        } else {
            dataBase.getLoggedInUser().setUsername(username.getText());
            dataBase.getLoggedInUser().setPassword(password.getText());
            vBox.getChildren().remove(username);
            vBox.getChildren().remove(password);
            username = null;
            password = null;
        }
    }

    public void deleteAccount(MouseEvent mouseEvent) throws IOException {
        dataBase.removeLoggedInUser();
        ProgramController.changeMenu(Menus.REGISTER_MENU);
    }

    public void logout(MouseEvent mouseEvent) {
        dataBase.setLoggedInUser(null);
        ProgramController.changeMenu(Menus.REGISTER_MENU);
    }

    public void changeUserAvatar(MouseEvent mouseEvent) {
        ProgramController.changeMenu(Menus.AVATAR_SELECTION);
    }
}
