package ch3.annotated;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("singer")
@Scope("prototype")
public class Singer {
    private String name = "unknow";

    public Singer(@Value("John Mayer") String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
