package com.fx2048.Model;

public class Board {
    private int score;
    private Grid grid;
    private boolean isOver;
    private boolean moved;

    public Board(int size) {
        this.score = 0;
        this.grid = new Grid(size);
        this.isOver = false;
        addTile();
        addTile();
    }

    public int getSize() {
        return grid.getSize();
    }

    public int getValue(int x, int y) {
        return grid.getValue(x, y);
    }

    public void setValue(int x, int y, int number) {
        grid.setValue(x, y, number);
    }

    public void showBoard() {
        for (int i = 0; i < grid.getSize(); i++) {
            for (int j = 0; j < grid.getSize(); j++) {
                System.out.print(grid.getValue(i, j) + " ");
            }
            System.out.println("");
        }
    }

    public int getScore() {
        return this.score;
    }

    private void moveTile(int x, int y, int dx, int dy) {
        if (grid.getValue(x, y) > 0) {
            while (grid.getValue(x + dx, y + dy) == 0) {
                grid.setValue(x + dx, y + dy, grid.getValue(x, y));
                grid.setValue(x, y, 0);
                x += dx;
                y += dy;
                moved = true;
            }
        }
    }

    private void mergeTile(int x, int y, int dx, int dy) {
        if (grid.getValue(x, y) > 0) {
            if (grid.getValue(x + dx, y + dy) == grid.getValue(x, y)) {
                grid.setValue(x + dx, y + dy, (grid.getValue(x, y)) * 2);
                this.score += grid.getValue(x, y) * 2;
                while (grid.getValue(x - dx, y - dy) > 0) {
                    grid.setValue(x, y, grid.getValue(x - dx, y - dy));
                    x -= dx;
                    y -= dy;
                    moved = true;
                }
                grid.setValue(x, y, 0);
            }
        }
    }

    public void moveUp() {
        moved = false;
        for (int x = 1; x < grid.getSize(); x++) {
            for (int y = 0; y < grid.getSize(); y++) {
                moveTile(x, y, -1, 0);
            }
        }
        for (int x = 1; x < grid.getSize(); x++) {
            for (int y = 0; y < grid.getSize(); y++) {
                mergeTile(x, y, -1, 0);
            }
        }
        if (moved) addTile();
    }

    public void moveDown() {
        moved = false;
        for (int x = grid.getSize() - 2; x >= 0; x--) {
            for (int y = 0; y < getSize(); y++) {
                moveTile(x, y, 1, 0);
            }
        }
        for (int x = grid.getSize() - 2; x >= 0; x--) {
            for (int y = 0; y < getSize(); y++) {
                mergeTile(x, y, 1, 0);
            }
        }
        if (moved) addTile();
    }


    public void moveLeft() {
        moved = false;
        for (int y = 1; y < grid.getSize(); y++) {
            for (int x = 0; x < grid.getSize(); x++) {
                moveTile(x, y, 0, -1);
            }
        }
        for (int y = 1; y < grid.getSize(); y++) {
            for (int x = 0; x < grid.getSize(); x++) {
                mergeTile(x, y, 0, -1);
            }
        }
        if (moved) addTile();
    }

    public void moveRight() {
        moved = false;
        for (int y = grid.getSize() - 2; y >= 0; y--) {
            for (int x = 0; x < grid.getSize(); x++) {
                moveTile(x, y, 0, 1);
            }
        }
        for (int y = grid.getSize() - 2; y >= 0; y--) {
            for (int x = 0; x < grid.getSize(); x++) {
                mergeTile(x, y, 0, 1);
            }
        }
        if (moved) addTile();
    }

    private void addTile() {
        while (true) {
            int x = (int) Math.floor(Math.random() * 4);
            int y = (int) Math.floor(Math.random() * 4);
            if (spaceAvailable(x, y)) {
                grid.setValue(x, y, Math.random() < 0.9 ? 2 : 4);
                return;
            }
        }
    }

    public boolean spaceAvailable(int x, int y) {
        return grid.getValue(x, y) == 0;
    }

    public boolean isOver() {
        for (int x = 0; x < grid.getSize(); x++) {
            for (int y = 0; y < grid.getSize(); y++) {
                if (spaceAvailable(x,y)) return false;
            }
        }
        for (int x = 0; x < grid.getSize(); x++) {
            for (int y = 0; y < grid.getSize(); y++) {
                if ((grid.getValue(x, y) == grid.getValue(x, y + 1)) || (grid.getValue(x, y) == grid.getValue(x + 1, y)))
                    return false;
            }
        }
        return true;
    }

}