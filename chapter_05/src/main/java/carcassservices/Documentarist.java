package carcassservices;

import advisorandpointcut.namepointcut.GrammyGuitarist;

public class Documentarist {
    private GrammyGuitarist guitarist;

    public void execute() {
        guitarist.sing();
        guitarist.talk();
    }

    public void setGuitarist(GrammyGuitarist guitarist) {
        this.guitarist = guitarist;
    }
}
