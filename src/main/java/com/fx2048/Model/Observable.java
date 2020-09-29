package com.fx2048.Model;

import com.fx2048.View.Observer;

public interface Observable {
    void registerObservable(Observer observer);

    void notifyAllObservers();
}
