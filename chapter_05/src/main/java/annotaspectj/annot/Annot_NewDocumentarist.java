package annotaspectj.annot;

import aopbasics.advisorandpointcut.namepointcut.Guitar;
import carcassservices.Documentarist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("documentarist")
public class Annot_NewDocumentarist extends Documentarist {
    protected Annot_GrammyGuitarist guitarist;

    @Override
    public void execute() {
        guitarist.sing();
        Guitar guitar = new Guitar();
        guitar.setBrand("Gibson");
        guitarist.sing(guitar);
        guitarist.sing(new Guitar());
        guitarist.talk();
        System.out.println("");
    }

    @Autowired
    @Qualifier("johnMayer")
    public void setGuitarist(Annot_GrammyGuitarist guitarist)  {
        this.guitarist = guitarist;
    }
}
