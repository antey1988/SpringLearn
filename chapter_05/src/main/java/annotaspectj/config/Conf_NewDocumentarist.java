package annotaspectj.config;

import aopbasics.advisorandpointcut.namepointcut.Guitar;
import carcassservices.Documentarist;

public class Conf_NewDocumentarist extends Documentarist {
    protected Conf_GrammyGuitarist guitarist;

    @Override
    public void execute() {
        guitarist.sing();
        Guitar guitar = new Guitar();
        guitar.setBrand("Gibson");
        guitarist.sing(guitar);
        guitarist.sing(new Guitar());
        guitarist.talk();
    }

    public void setGuitarist(Conf_GrammyGuitarist guitarist)  {
        this.guitarist = guitarist;
    }
}
