package framentLoops;

import alg.Vektor2D;
import behaviors.Behavior;
import behaviors.FlummiBehavior;
import behaviors.GravityBehavior;
import behaviors.WindBehavior;
import com.sun.prism.paint.Color;
import objects.Circle;
import objects.MovingObject;

import java.util.ArrayList;
import java.util.Random;

public class FirstLayerLoop implements LayerLoop {
    ArrayList<MovingObject> movObjects;


    Behavior flummi;
    Behavior wind;
    Behavior gravity;

    public FirstLayerLoop() {
        movObjects = new ArrayList<>();
    }

    @Override
    public void init() {
        createFigursOfLayer();
    }

    private void createFigursOfLayer() {
        Random rand = new Random();
        for (int i = 0 ;  i< 5000; i++ ){
            Circle circle = new Circle(
                    new Vektor2D((0+i*(5+i*rand.nextFloat()))%640,(0+i*(i+5*rand.nextFloat()))%480),
                    (40)*rand.nextFloat(),
                    4.5f*rand.nextFloat(),
                    new Color(rand.nextFloat(),rand.nextFloat(),rand.nextFloat(),rand.nextFloat())
            );
            addMovingObject(circle);
            flummi = new FlummiBehavior(circle);
            wind = new WindBehavior(circle);
            gravity = new GravityBehavior(circle);

            circle.addBehavior(flummi);
            circle.addBehavior(gravity);
            circle.addBehavior(wind);
        }
    }

    public void addMovingObject(MovingObject movingObject ){
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
