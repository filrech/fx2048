package com.fx2048.Controller;

import com.fx2048.Model.Board;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;

public class Controller {
    public Controller(Scene scene, int size) {
        Board board = new Board(size);
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
