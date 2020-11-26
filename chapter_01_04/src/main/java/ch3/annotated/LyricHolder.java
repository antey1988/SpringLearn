package ch3.annotated;

import org.springframework.stereotype.Service;

@Service("lyricHolder")
public class LyricHolder {
    private String value = "'You bе the DJ, I'll bе the driver'";

    @Override public String toString() {
        return "LyricHolder: { " + value + "} ";
    }
}
