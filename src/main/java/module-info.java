module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires javafx.media;
    requires com.google.gson;


    opens com.example.demo to javafx.fxml, com.google.gson;
    exports com.example.demo;
    exports com.example.demo.Controller;
    opens com.example.demo.Controller to javafx.fxml, com.google.gson;
    exports com.example.demo.View.sceneController;
    opens com.example.demo.View.sceneController to com.google.gson, javafx.fxml;
    exports com.example.demo.Controller.animationController;
    opens com.example.demo.Controller.animationController to com.google.gson, javafx.fxml;

}