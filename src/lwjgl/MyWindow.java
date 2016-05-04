package lwjgl;

import framentLoops.FirstLayerLoop;
import framentLoops.SwarmLayerLoop;

public class MyWindow extends BasisWindow {
    static public int WINDOW_WIDTH = 640;
    static public int WINDOW_HEIGHT = 480;

    @Override
    public void insideLoop() {
        if (layerLoop != null)
            layerLoop.loop();
    }

    public MyWindow() {
        super(WINDOW_WIDTH, WINDOW_HEIGHT, "CG 2");

        FirstLayerLoop firstLayerLoop = new FirstLayerLoop();
        SwarmLayerLoop swarmLayerLoop = new SwarmLayerLoop(this);
        //setLayerLoop(firstLayerLoop);
        setLayerLoop(swarmLayerLoop);

        this.run();
    }

    public static void main(String[] args) {
        MyWindow myWindow = new MyWindow();
    }
}
