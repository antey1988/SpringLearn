package aopbasics.advisorandpointcut.annotationpointcut;

import aopbasics.advice.methodbeforeadvice.Singer;
import aopbasics.advisorandpointcut.namepointcut.Guitar;

public class Guitarist implements Singer {
    @Override
    public void sing() {
        System.out.println("Dream of ways to throw it all away");
    }

    @AdviceRequired
    public void  sing(Guitar guitar) {
        System.out.println("play; " + guitar.play());
    }

    public void rest() {
        System.out.println("zzz");
    }
}
