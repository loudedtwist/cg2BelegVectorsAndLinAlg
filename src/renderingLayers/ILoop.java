package renderingLayers;

import lwjgl.BasisWindow;

 public interface ILoop {
    default void setWindowObject(BasisWindow window){}
    abstract void loop();
    abstract void init();
}
