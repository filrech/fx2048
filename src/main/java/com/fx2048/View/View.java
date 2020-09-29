package com.fx2048.View;

import com.fx2048.Model.Board;
import com.fx2048.Model.Observable;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class View implements Observer {
    private StackPane root;
    private Scene scene;
    private Board board;

    public View(StackPane root, Scene scene, Board board) {
        this.root = root;
        this.scene = scene;
        this.board = board;

    }

    @Override
    public void notification(Observable observable) {
        root.getChildren().clear();
        root.setStyle("-fx-background-color: #FAF8EF");
        Label score = new Label();
        score.setText("Score: " + board.getScore());
        score.setStyle("-fx-text-fill: #776E65; -fx-font-size: 30");
        root.getChildren().add(0, score);
        root.setAlignment(score, Pos.TOP_LEFT);
        root.setMargin(score, new Insets(30, 0, 0, 30));

        Button button = new Button();
        button.setText("Reset");
        button.setStyle("-fx-text-fill: #776E65; -fx-background-color: #BBADA0; -fx-font-size: 30");
        root.getChildren().add(1, button);
        root.setAlignment(button, Pos.TOP_RIGHT);
        root.setMargin(button, new Insets(20, 30, 0, 0));
        root.setMinHeight(400);
        root.setMinWidth(400);

        board.setValue(2,2,2048);

        GridPane gridPane = new GridPane();
        for (int i = 0; i < 4; i++) {
            gridPane.getColumnConstraints().add(new ColumnConstraints(100));
            gridPane.getRowConstraints().add(new RowConstraints(100));
        }
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                Label label = new Label();
                label.setText(String.valueOf(board.getValue(j, i)));
                label.setMinHeight(95);
                label.setMinWidth(95);
                String color = switch (board.getValue(j, i)) {
                    case 0 -> "-fx-background-color: #CDC1B4; -fx-font-size: 50;";
                    case 2 -> "-fx-background-color:  #EEE4DA; -fx-font-size: 50;";
                    case 4 -> "-fx-background-color:  #EDE0C7; -fx-font-size: 50;";
                    case 8 -> "-fx-background-color: #F2B179; -fx-font-size: 50;";
                    case 16 -> "-fx-background-color:  #F59563; -fx-font-size: 50;";
                    case 32 -> "-fx-background-color: #F67C5F; -fx-font-size: 50;";
                    case 64 -> "-fx-background-color: #F65E3B; -fx-font-size: 50;";
                    case 128 -> "-fx-background-color: #EDCf72; -fx-font-size: 50;";
                    case 256 -> "-fx-background-color: #FAD14E; -fx-font-size: 50;";
                    case 512 -> "-fx-background-color: #E3A427;-fx-font-size: 50;";
                    case 1024 -> "-fx-background-color: #9EF867; -fx-font-size: 40;";
                    case 2048 -> "-fx-background-color: #55FA38; -fx-font-size: 40;";
                    default -> throw new IllegalStateException("Unexpected value: " + board.getValue(j, i));
                };
                label.setAlignment(Pos.CENTER);
                label.setStyle("-fx-text-fill: #776E65; -fx-background-radius: 6; " + color + "  ");
                gridPane.add(label, i, j);
                gridPane.setMargin(label, new Insets(5, 0, 0, 2));
            }
        }
        gridPane.setStyle("-fx-background-color: #BBADA0; -fx-background-radius: 6;");
        root.getChildren().add(2, gridPane);
        root.setAlignment(gridPane, Pos.BOTTOM_CENTER);
        root.setMargin(gridPane, new Insets(100, 25, 25, 25));
    }
}
