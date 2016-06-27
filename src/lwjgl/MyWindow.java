package lwjgl;

import renderingLayers.BackgoundLayer;
import renderingLayers.RainLayerLoop;
import renderingLayers.ILoop;
import renderingLayers.SwarmLayer;

public class MyWindow extends BasisWindow {
    static public int WINDOW_WIDTH = 640;
    static public int WINDOW_HEIGHT = 480;

    @Override
    public void insideLoop() {
        for (ILoop iLoop : layerLoops) {
            iLoop.loop();
        }
    }

    public MyWindow() {
        super(WINDOW_WIDTH, WINDOW_HEIGHT, "CG 2");

        RainLayerLoop rainLayerLoop = new RainLayerLoop();
        BackgoundLayer backgoundLayer = new BackgoundLayer();
        SwarmLayer swarmLayerLoop = new SwarmLayer(this);
        layerLoops.add(backgoundLayer);
        layerLoops.add(swarmLayerLoop);
        //layerLoops.add(rainLayerLoop);

    }

    public static void main(String[] args) {
        new MyWindow().run();
    }
}
