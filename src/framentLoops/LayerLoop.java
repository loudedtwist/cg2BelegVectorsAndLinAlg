package framentLoops;

import lwjgl.BasisWindow;

public interface LayerLoop {
    default void setWindowObject( BasisWindow window){}
    void loop();
    void init();
}
