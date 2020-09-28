package com.fx2048.Model;

public class Grid {
    private int size;
    private Tile[][] grid;

    public Grid(int size) {
        this.size = size;
        this.grid = new Tile[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = new Tile();
            }
        }
    }

    public int getSize() {
        return this.size;
    }

    public int getValue(int x, int y) {
        if (inGrid(x, y)) {
            return grid[x][y].getValue();
        }
        return -1;
    }

    public void setValue(int x, int y, int number) {
        if (inGrid(x, y)) {
            grid[x][y].setValue(number);
        }
    }

    private boolean inGrid(int x, int y) {
        return (x >= 0 && x < size) && (y >= 0 && y < size);
    }
}
