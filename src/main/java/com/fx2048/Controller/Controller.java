package com.fx2048.Controller;

import com.fx2048.Model.Board;
import com.fx2048.View.App;
import com.fx2048.View.View;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;

public class Controller {
    private App app;
    private Board board;
    private View view;
    private StackPane root;
    private int size;
    private Scene scene;

    public Controller(StackPane root, Scene scene, int size, App app) {
        this.board = new Board(size);
        this.view = new View(root, scene, board, this);
        this.size = size;
        this.scene = scene;
        this.app = app;
        board.registerObservable(view);
        board.notifyAllObservers();

        scene.setOnKeyPressed(keyEvent -> {
            KeyCode keyCode = keyEvent.getCode();
            if (!board.isOver()){
                switch (keyCode) {
                    case W -> {
                        board.moveUp();
                    }
                    case A -> {
                        board.moveLeft();
                    }
                    case S -> {
                        board.moveDown();
                    }
                    case D -> {
                        board.moveRight();
                    }
                }
            }
        });
    }

    public void reset() {
        app.restart();
    }
}
