package lwjgl;

import framentLoops.BackgoundLayer;
import framentLoops.FirstLayerLoop;
import framentLoops.LayerLoop;
import framentLoops.SwarmLayerLoop;

public class MyWindow extends BasisWindow {
    static public int WINDOW_WIDTH = 640;
    static public int WINDOW_HEIGHT = 480;

    @Override
    public void insideLoop() {
        for (LayerLoop layerLoop : layerLoops) {
            layerLoop.loop();
        }
    }

    public MyWindow() {
        super(WINDOW_WIDTH, WINDOW_HEIGHT, "CG 2");

        FirstLayerLoop firstLayerLoop = new FirstLayerLoop();
        BackgoundLayer backgoundLayer = new BackgoundLayer();
        SwarmLayerLoop swarmLayerLoop = new SwarmLayerLoop(this);
        //setLayerLoops(firstLayerLoop);
        layerLoops.add(backgoundLayer);
        layerLoops.add(swarmLayerLoop);

        this.run();
    }

    public static void main(String[] args) {
        MyWindow myWindow = new MyWindow();
    }
}
