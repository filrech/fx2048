package com.fx2048.View;

import com.fx2048.Controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {
    Stage stage;

    @Override
    public void start(Stage stage) {
        stage.setTitle("2048");
        this.stage = stage;
        try {
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/icon.png")));
        } catch (Exception e) {
            System.out.println("Нет иконки");
        }
        stage.setResizable(false);

        StackPane root = new StackPane();
        Scene scene = new Scene(root, 450, 530);
        Controller controller = new Controller(root, scene, 4, this);

        stage.setScene(scene);
        stage.show();
    }

    public void restart() {
        start(stage);
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}