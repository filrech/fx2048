package com.fx2048.Model;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class Tile extends Label {
    private int value;

    public int getValue() {
        return this.value;
    }

    public void setValue(int number) {
        this.value = number;
    }
}
