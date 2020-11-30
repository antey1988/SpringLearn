package aopbasics.otherpointcut.composablepointcut;

import aopbasics.advice.methodbeforeadvice.Singer;
import aopbasics.advisorandpointcut.namepointcut.Guitar;

public class GrammyGuitarist implements Singer {
    @Override
    public void sing() {
        System.out.println("sing: Gravity is working agains me\n" +
                "And gravity wants to bring me down");
    }

    public void sing(Guitar guitar) {
        System.out.println("play: " + guitar.play());
    }

    public void rest() {
        System.out.println("zzz");
    }

    public void talk() {
        System.out.println("talk");
    }
}
