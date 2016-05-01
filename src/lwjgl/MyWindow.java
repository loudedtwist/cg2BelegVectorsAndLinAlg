package lwjgl;

import framentLoops.MyFragmentLoop;

public class MyWindow extends BasisWindow{
    public MyWindow() {
        super(640, 480, "CG 2", new MyFragmentLoop());
        this.run();
    }

    public static void main(String[] args) {
        MyWindow myWindow = new MyWindow();
    }
}
