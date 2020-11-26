package advisorandpointcut.jdkregexppointcut;

import advice.methodbeforeadvice.Singer;

public class Guitarist implements Singer {
    @Override
    public void sing() {
        System.out.println("Just keep me whre the light is");
    }

    public void sing2() {
        System.out.println("Oh gravity, stay the hell away from me");
    }

    public void rest() {
        System.out.println("zzz");
    }
}
