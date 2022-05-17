package com.example.demo.Controller;

import com.example.demo.Model.DataBase;
import com.example.demo.Model.User;
import com.example.demo.View.Menus;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;



public class LoginPageController {

    private final DataBase dataBase = DataBase.getInstance();

    @FXML
    private BorderPane pane;
    @FXML
    private VBox vBox;
    @FXML
    private TextField password = null;
    @FXML
    private TextField username = null;
    @FXML
    private Text error = null;
    private boolean onRegister = false;


    public void initialize() {
        ProgramController.setupBackGround(pane);
    }


    public void register(MouseEvent mouseEvent) {
        if (error != null) {
            vBox.getChildren().remove(error);
            error = null;
        }

        if (password == null && username == null) {
            username = ProgramController.creatTextField("enter username - register -");
            password = ProgramController.creatTextField("enter password - register -");
            vBox.getChildren().add(username);
            vBox.getChildren().add(password);
            setOnRegister(true);
        } else if (!isOnRegister()) {
            vBox.getChildren().remove(username);
            vBox.getChildren().remove(password);
            username = ProgramController.creatTextField("enter username - register -");
            password = ProgramController.creatTextField("enter password - register -");
            vBox.getChildren().add(username);
            vBox.getChildren().add(password);
            setOnRegister(true);
        } else {
            if (getDataBase().getUserByName(username.getText()) != null) {
                error = new Text("try another username");
                vBox.getChildren().add(error);
                return;
            }

            User user = new User(username.getText(), password.getText(), (int) (Math.random() * 6) + 1);
            getDataBase().addUser(user);
            vBox.getChildren().remove(username);
            vBox.getChildren().remove(password);
            username = null;
            password = null;
        }
    }

    public void login(MouseEvent mouseEvent) {
        if (error != null) {
            vBox.getChildren().remove(error);
            error = null;
        }
        if (password == null && username == null) {
            username = ProgramController.creatTextField("enter username - login -");
            password = ProgramController.creatTextField("enter password - login -");
            vBox.getChildren().add(username);
            vBox.getChildren().add(password);
            setOnRegister(false);
        } else if (isOnRegister()) {
            vBox.getChildren().remove(username);
            vBox.getChildren().remove(password);
            username = ProgramController.creatTextField("enter username - login -");
            password = ProgramController.creatTextField("enter password - login -");
            vBox.getChildren().add(username);
            vBox.getChildren().add(password);
            setOnRegister(false);
        } else {
            User user = getDataBase().getUserByName(username.getText());
            if (user == null) {
                error = new Text("wrong username/password");
                vBox.getChildren().add(error);
                return;
            }
            if (!user.getPassword().equals(password.getText())) {
                error = new Text("wrong username/password");
                vBox.getChildren().add(error);
                return;
            }
            getDataBase().setLoggedInUser(user);
            ProgramController.changeMenu(Menus.MAIN_MENU);
        }

    }

    public void guest(MouseEvent mouseEvent) {
        if (error != null) {
            vBox.getChildren().remove(error);
            error = null;
        }
        ProgramController.changeMenu(Menus.MAIN_MENU);
    }

    public DataBase getDataBase() {
        return dataBase;
    }

    public boolean isOnRegister() {
        return onRegister;
    }

    public void setOnRegister(boolean onRegister) {
        this.onRegister = onRegister;
    }
}
