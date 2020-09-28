package com.fx2048.View;

import com.fx2048.Controller.Controller;
import com.fx2048.Model.Board;
import com.fx2048.Model.Grid;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("2048");
        try {
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/icon.png")));
        } catch (Exception e) {
            System.out.println("Нет иконки");
        }
        stage.setResizable(false);
        stage.setX(400);
        stage.setY(400);

        Group root = new Group();
        Scene scene = new Scene(root, 400, 400);
        Controller controller = new Controller(scene, 4);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}