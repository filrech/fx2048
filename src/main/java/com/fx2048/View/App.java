package com.fx2048.View;

import com.fx2048.Controller.Controller;
import com.fx2048.Model.Board;
import com.fx2048.Model.Grid;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
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

        StackPane root = new StackPane();
        Scene scene = new Scene(root, 450, 530);
        Controller controller = new Controller(root, scene, 4);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}