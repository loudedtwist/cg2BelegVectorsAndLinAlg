package framentLoops;

import lwjgl.BasisWindow;

 public interface LayerLoop {
    default void setWindowObject(BasisWindow window){}
    abstract void loop();
    abstract void init();
}
