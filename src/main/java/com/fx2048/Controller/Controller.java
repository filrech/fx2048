package com.fx2048.Controller;

import com.fx2048.Model.Board;
import com.fx2048.View.View;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;

public class Controller {
    private Board board;
    private View view;

    public Controller(StackPane root, Scene scene, int size) {
        board = new Board(size);
        this.view = new View(root, scene, board);
        board.registerObservable(view);

        scene.setOnKeyPressed(keyEvent -> {
            KeyCode keyCode = keyEvent.getCode();
            switch (keyCode) {
                case UP, W -> {
                    board.moveUp();
                    board.showBoard();
                    System.out.println("");
                    System.out.println(board.isOver());
                    System.out.println(board.getScore());
                }
                case LEFT, A -> {
                    board.moveLeft();
                    board.showBoard();
                    System.out.println("");
                    System.out.println(board.isOver());
                    System.out.println(board.getScore());
                }
                case DOWN, S -> {
                    board.moveDown();
                    board.showBoard();
                    System.out.println("");
                    System.out.println(board.isOver());
                    System.out.println(board.getScore());
                }
                case RIGHT, D -> {
                    board.moveRight();
                    board.showBoard();
                    System.out.println("");
                    System.out.println(board.isOver());
                    System.out.println(board.getScore());
                }
            }
        });
    }
}
