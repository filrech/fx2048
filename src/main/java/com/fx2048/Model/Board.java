package com.fx2048.Model;

import com.fx2048.View.Observer;

import java.util.ArrayList;
import java.util.List;

public class Board implements Observable {
    private int score;
    private Grid grid;
    private boolean isOver;
    private boolean moved;
    private List<Observer> observers;

    public Board() {
        this.score = 0;
        this.grid = new Grid(4);
        this.isOver = false;
        this.observers = new ArrayList<Observer>();
    }

    public Board(int size) {
        this.score = 0;
        this.grid = new Grid(size);
        this.isOver = false;
        this.observers = new ArrayList<Observer>();
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

    public int getScore() {
        return this.score;
    }

    private void moveTile(int x, int y, int dx, int dy) {
        if (grid.getValue(x, y) > 0) {
            while (spaceAvailable(x + dx, y + dy)) {
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
                if (grid.getValue(x, y) * 2 >= 2048) this.isOver = true;
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
        notifyAllObservers();
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
        notifyAllObservers();
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
        notifyAllObservers();
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
        notifyAllObservers();
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
        if (this.isOver == true) return true;
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

    @Override
    public void registerObservable(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyAllObservers() {
        for (Observer observers: observers) {
            try {
                observers.notification(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
