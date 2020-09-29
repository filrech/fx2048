package com.fx2048.View;

import com.fx2048.Model.Observable;

public interface Observer {
    void notification(Observable observable);
}
