package aopbasics.advisorandpointcut.staticpointcut;

import aopbasics.advice.methodbeforeadvice.Singer;

public class GreatGuitarist implements Singer {
    @Override
    public void sing() {
        System.out.println("I shot the sheriff,\nBut I did not shoot the deputy");
    }
}
