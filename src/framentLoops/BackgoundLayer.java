package framentLoops;

import alg.Vektor2D;
import com.sun.prism.paint.Color;
import objects.Circle;
import objects.MovingObject;

import java.util.ArrayList;
import java.util.Random;

public class BackgoundLayer implements LayerLoop {
    ArrayList<MovingObject> movObjects;

    public BackgoundLayer() {
        movObjects = new ArrayList<>();
    }

    @Override
    public void init() {
        createFigursOfLayer();
    }

    private void createFigursOfLayer() {
        Random rand = new Random();
        for (int i = 0 ;  i< 1000; i++ ){
            Circle circle = new Circle(
                    new Vektor2D((0+i*(5+i*rand.nextFloat()))%640,(0+i*(i+5*rand.nextFloat()))%480),
                    rand.nextFloat(),
                    rand.nextFloat()%.9f+0.3f,
                    rand.nextFloat()*.2f,
                    new Color(1-(rand.nextFloat()%0.1f),1-(rand.nextFloat()%0.1f),1-(rand.nextFloat()%0.1f),1-(rand.nextFloat()%0.1f)));
            addMovingObjectToLayout(circle);
            //circle.addBehavior(new GravityBehavior(circle));
        }

    }

    public void addMovingObjectToLayout(MovingObject movingObject ){
        movObjects.add(movingObject);
    }


    @Override
    public void loop() {
        for (MovingObject movObject : movObjects) {
            movObject.callBehavior();
            movObject.render();
        }
    }
}
