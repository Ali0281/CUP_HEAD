package com.example.demo.Controller;

import com.example.demo.Model.DataBase;
import com.example.demo.View.Menus;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class avatarController {
    DataBase dataBase = DataBase.getInstance();

    @FXML
    private BorderPane pane;

    public void initialize() {
        ProgramController.setupBackGround(pane);
    }

    public void avatar1(MouseEvent mouseEvent) {
        dataBase.getLoggedInUser().setAvatar(1);
        ProgramController.changeMenu(Menus.PROFILE_MENU);
    }

    public void avatar2(MouseEvent mouseEvent) {
        dataBase.getLoggedInUser().setAvatar(2);
        ProgramController.changeMenu(Menus.PROFILE_MENU);
    }

    public void avatar3(MouseEvent mouseEvent) {
        dataBase.getLoggedInUser().setAvatar(3);
        ProgramController.changeMenu(Menus.PROFILE_MENU);
    }

    public void avatar4(MouseEvent mouseEvent) {
        dataBase.getLoggedInUser().setAvatar(4);
        ProgramController.changeMenu(Menus.PROFILE_MENU);
    }

    public void avatar5(MouseEvent mouseEvent) {
        dataBase.getLoggedInUser().setAvatar(5);
        ProgramController.changeMenu(Menus.PROFILE_MENU);
    }

    public void avatar6(MouseEvent mouseEvent) {
        dataBase.getLoggedInUser().setAvatar(6);
        ProgramController.changeMenu(Menus.PROFILE_MENU);
    }
}
